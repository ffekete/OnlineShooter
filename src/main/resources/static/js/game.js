
var screen_x = $("#gameArea").width();
var screen_y = $("#gameArea").height();

/**/
var STAGE_X_MIN_LIMIT = 0;
var STAGE_Y_MIN_LIMIT = 0;

var STAGE_X_MAX_LIMIT = 0;
var STAGE_Y_MAX_LIMIT = 0;

var stompClient = null;

var shootAmmoSwitch = false;

var canvas;
var canvasContext;

var MINIMAP_WIDTH = 200;
var MINIMAP_HEIGHT = 200;

var STAGE_MAX_WIDTH = 0;
var STAGE_MAX_HEIGHT = 0;

var STAGE_HALF_WIDTH = 0;
var STAGE_HALF_HEIGHT = 0;

/*
 * Array of particles (global variable)
 */
var particles = [];

var asteroid_rotation = 0;

var ui_color = "white";

var playerData = {
	name : window.sessionStorage.getItem("playerName"),
	id : window.sessionStorage.getItem("playerId"),
	// color: window.sessionStorage.getItem("color"),
	connectionId : window.sessionStorage.getItem("connectionId"),
	mouseX : 0,
	mouseY : 0,
	shipAngle : 0.0
};

var playerDataToSend = {
	id : playerData.id,
	mouseX : playerData.mouseX,
	mouseY : playerData.mouseY,
	canvasWidth : playerData.canvasWidth,
	canvasHeight : playerData.canvasHeight,
	weaponIndex : 0
};

/*
 * A single explosion particle
 */
function Particle() {
	this.scale = 1.0;
	this.x = 0;
	this.y = 0;
	this.radius = 20;
	this.color = "#000";
	this.velocityX = 0;
	this.velocityY = 0;
	this.scaleSpeed = 0.5;

	this.update = function(ms) {
		// shrinking
		this.scale -= this.scaleSpeed * ms / 1000.0;

		if (this.scale <= 0) {
			this.scale = 0;
		}
		// moving away from explosion center
		this.x += this.velocityX * ms / 1000.0;
		this.y += this.velocityY * ms / 1000.0;
	};

	this.draw = function(context2D, offsettedX, offsettedY) {
		// translating the 2D context to the particle coordinates
		context2D.save();
		context2D.translate(offsettedX, offsettedY);
		context2D.scale(this.scale, this.scale);

		// drawing a filled circle in the particle's local space
		context2D.beginPath();
		context2D.arc(0, 0, this.radius, 0, Math.PI * 2, true);
		context2D.closePath();

		context2D.fillStyle = this.color;
		context2D.fill();

		context2D.restore();
	};
}

function randomFloat(min, max) {
	return min + Math.random() * (max - min);
}

/*
 * Advanced Explosion effect Each particle has a different size, move speed and
 * scale speed.
 * 
 * Parameters: x, y - explosion center color - particles' color
 */
function createExplosion(x, y, color) {
	var minSize = 10;
	var maxSize = 30;
	var count = 10;
	var minSpeed = 60.0;
	var maxSpeed = 200.0;
	var minScaleSpeed = 1.0;
	var maxScaleSpeed = 4.0;

	for (var angle = 0; angle < 360; angle += Math.round(360 / count)) {
		var particle = new Particle();

		particle.x = x;
		particle.y = y;

		particle.radius = randomFloat(minSize, maxSize);

		particle.color = color;

		particle.scaleSpeed = randomFloat(minScaleSpeed, maxScaleSpeed);

		var speed = randomFloat(minSpeed, maxSpeed);

		particle.velocityX = speed * Math.cos(angle * Math.PI / 180.0);
		particle.velocityY = speed * Math.sin(angle * Math.PI / 180.0);

		particles.push(particle);
	}
}

/*
 * Basic Explosion, all particles move and shrink at the same speed.
 * 
 * Parameter : explosion center
 */
function createBasicExplosion(x, y) {
	// creating 4 particles that scatter at 0, 90, 180 and 270 degrees
	for (var angle = 0; angle < 360; angle += 90) {
		var particle = new Particle();

		// particle will start at explosion center
		particle.x = x;
		particle.y = y;

		particle.color = "#99CCFF";

		var speed = 50.0;

		// velocity is rotated by "angle"
		particle.velocityX = speed * Math.cos(angle * Math.PI / 180.0);
		particle.velocityY = speed * Math.sin(angle * Math.PI / 180.0);
		particle.radius = 3;

		// adding the newly created particle to the "particles" array
		particles.push(particle);
	}
}

function updateParticles(frameDelay, context2D) {
	// update and draw particles
	for (var i = 0; i < particles.length; i++) {
		var particle = particles[i];

		particle.update(frameDelay);

		var offsetX = playerData.x - particle.x;
		var offsetY = playerData.y - particle.y;
		particle.draw(context2D, (screen_x / 2 + 10) - offsetX, (screen_y / 2 + 10) - offsetY);
	}
}

function drawExplosions() {
	updateParticles(8, canvasContext);
}

function draw() {
	canvasContext.canvas.width = window.innerWidth - 20;
	canvasContext.canvas.height = window.innerHeight - 20;

	screen_x = $("#gameArea").width();
	screen_y = $("#gameArea").height();

	updateCanvasSize();

	drawBackground();
	drawBorder();

	drawItems();
	drawAmmo(playerData.shipAngle);

	if (playerData.respawnTime == 0) {
		drawShip(screen_x / 2 + 10, screen_y / 2 + 10, playerData.shipAngle, playerData.hitRadius, playerData.name, playerData.hp, playerData.maxHp, playerData.invulnerable, playerData.shieldAmount, playerData.maxShieldAmount, playerData.color, playerData.shipType);
	} else {
		canvasContext.save();
		canvasContext.fillStyle = ui_color;
		canvasContext.textAlign = "center";
		canvasContext.font = "40px Arial";
		canvasContext.fillText("Respawning in seconds: " + parseInt(playerData.respawnTime / 100 + 1), screen_x / 2 + 10, screen_y / 2 + 10);
		canvasContext.restore();
	}

	if (playerData.otherPlayers) {
		for (var i in playerData.otherPlayers) {
			var actualShip = playerData.otherPlayers[i];

			var dx = playerData.x - actualShip.x;
			var dy = playerData.y - actualShip.y;
			drawShip((screen_x / 2 + 10) - dx, (screen_y / 2 + 10) - dy, actualShip.shipAngle, actualShip.hitRadius, actualShip.name, actualShip.hp, actualShip.maxHp, actualShip.invulnerable, actualShip.shield.protection, actualShip.shield.maxProtectionValue, actualShip.color, actualShip.shipType);
		}
	}

	drawExplosions();
	drawMinimap();
	drawHighScores();
	drawPlayerData();
	drawWeaponKeys();
}

function connect() {
	var socket = new SockJS('/requestPlayerData_node' + playerData.connectionId);
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		stompClient.subscribe('/providePlayerData_node' + playerData.connectionId, playerDataArrived);
		stompClient.subscribe('/messages', messageArrived);
		stompClient.subscribe('/events', eventArrived);
	});
}

function eventArrived(event) {
	var eventInfo = {};

	eventInfo.eventCommand = JSON.parse(event.body).eventCommand;
	eventInfo.eventX = JSON.parse(event.body).eventX;
	eventInfo.eventY = JSON.parse(event.body).eventY;

	if (eventInfo.eventCommand === "PLAY_EXPLOSION_ANIM") {
		createExplosion(eventInfo.eventX, eventInfo.eventY, "red");
		createExplosion(eventInfo.eventX, eventInfo.eventY, "yellow");
	}
	if (eventInfo.eventCommand === "PLAY_HIT_ANIM") {
		createBasicExplosion(eventInfo.eventX, eventInfo.eventY);
	}
}

function messageArrived(message) {
	console.log(message.body);
}

function shootAmmo() {
	stompClient.send("/app/createAmmo", {}, playerData.id);
}

function playerDataArrived(playerDataFromServer) {
	var parsedPlayerData = JSON.parse(playerDataFromServer.body);
	playerData.shipAngle = parsedPlayerData.shipAngle;
	playerData.hitRadius = parsedPlayerData.hitRadius;
	playerData.ammo = parsedPlayerData.visibleAmmo;
	playerData.otherPlayers = parsedPlayerData.visiblePlayers;
	playerData.x = parsedPlayerData.x;
	playerData.y = parsedPlayerData.y;
	playerData.hp = parsedPlayerData.shipHp;
	playerData.maxHp = parsedPlayerData.shipMaxHp;
	playerData.invulnerable = parsedPlayerData.invulnerable;
	playerData.itemsOnScreen = parsedPlayerData.items;
	playerData.score = parsedPlayerData.score;
	playerData.highScores = parsedPlayerData.scores;
	playerData.shieldAmount = parsedPlayerData.shieldAmount;
	playerData.maxShieldAmount = parsedPlayerData.maxShieldAmount;
	playerData.respawnTime = parsedPlayerData.respawnTime;
	playerData.color = parsedPlayerData.color;
	playerData.shipType = parsedPlayerData.shipType;
	playerData.weapon = parsedPlayerData.weapon;
	playerData.weapons = parsedPlayerData.weapons;
	playerData.ammoCount = parsedPlayerData.ammoCount;
	playerData.allPlayersPosition = parsedPlayerData.allPlayersPosition;
}

function drawHighScores() {
	canvasContext.save();
	canvasContext.fillStyle = ui_color;
	canvasContext.fillText("Your score: " + playerData.score, 10, 15);
	canvasContext.fillText("High score table: ", 10, 25);
	var y = 35;
	for (var i in playerData.highScores) {
		canvasContext.fillText(playerData.highScores[i], 10, y);
		y += 10;
	}
	canvasContext.restore();
}

function drawPlayerData() {
	var fromLeft = 150;
	var fromTop = 15;
	var verticalSpace = 10;
	canvasContext.save();
	canvasContext.fillStyle = ui_color;
	canvasContext.fillText("Player details: ", fromLeft, fromTop);
	canvasContext.fillText("Ship type: " + playerData.shipType, fromLeft, fromTop + verticalSpace);
	canvasContext.fillText("HP: " + playerData.hp, fromLeft, fromTop + verticalSpace * 2);
	canvasContext.fillText("Shield amount: " + playerData.shieldAmount, fromLeft, fromTop + verticalSpace * 3);
	canvasContext.fillText("Weapon: " + playerData.weapon.name, fromLeft, fromTop + verticalSpace * 4);
	canvasContext.fillText("RoF: " + playerData.weapon.rateOfFire, fromLeft, fromTop + verticalSpace * 5);
	canvasContext.fillText("Ammo: " + playerData.ammoCount[playerData.weapon.ammoType], fromLeft, fromTop + verticalSpace * 6);
	canvasContext.fillText("Damage: " + playerData.weapon.damage, fromLeft, fromTop + verticalSpace * 7);
	canvasContext.restore();
}

function drawWeaponKeys() {
	var fromLeft = 300;
	var fromTop = 15;
	var verticalSpace = 10;
	canvasContext.save();
	canvasContext.fillStyle = ui_color;
	$.each(playerData.weapons, function(index, weapon) {
		canvasContext.fillText("[" + (index + 1) + "]: " + weapon.name + " (" + playerData.ammoCount[weapon.ammoType] + ")", fromLeft, fromTop + verticalSpace * index);
	});
	canvasContext.restore();
}

function drawBackground() {
	var img = new Image();
	img.src = "img/space_background.jpg";
	canvasContext.save();
	for (var i = -3; i < 3; i++) {
		for (var j = -3; j < 3; j++) {
			canvasContext.drawImage(img, j * 1024 - (playerData.x % 1024), i * 1024 - (playerData.y % 1024));
		}
	}
	canvasContext.restore();
}

function drawBorder() {
	var dx = Math.abs(playerData.x - STAGE_X_MIN_LIMIT);
	var dy = Math.abs(playerData.y - STAGE_Y_MIN_LIMIT);

	var dxm = Math.abs(STAGE_X_MIN_LIMIT) + STAGE_X_MAX_LIMIT;
	var dym = Math.abs(STAGE_Y_MIN_LIMIT) + STAGE_Y_MAX_LIMIT;

	canvasContext.save();
	canvasContext.strokeStyle = ui_color;

	canvasContext.strokeRect((screen_x / 2) - dx, (screen_y / 2) - dy, dxm, dym);

	canvasContext.restore();
}

function drawItems() {
	canvasContext.save();

	for (var items in playerData.itemsOnScreen) {

		var dx = playerData.x - playerData.itemsOnScreen[items].x;
		var dy = playerData.y - playerData.itemsOnScreen[items].y;

		canvasContext.fillStyle = ui_color;
		canvasContext.strokeStyle = ui_color;
		canvasContext.beginPath();
		canvasContext.arc((screen_x / 2 + 10) - dx, (screen_y / 2 + 10) - dy, 5, 0, 2 * Math.PI);
		canvasContext.closePath();
		canvasContext.stroke();
		canvasContext.textAlign = "center";
		canvasContext.font = "15px Arial";
		canvasContext.fillText(playerData.itemsOnScreen[items].name, (screen_x / 2 + 10) - dx, (screen_y / 2 + 10) - dy + 20);
	}

	canvasContext.restore();
}

function drawAmmo(angle) {
	canvasContext.save();

	for (var ammo in playerData.ammo) {

		var dx = playerData.x - playerData.ammo[ammo].x;
		var dy = playerData.y - playerData.ammo[ammo].y;

		var physicalRepresentation = JSON.parse(playerData.ammo[ammo].physicalRepresentation);
		
		switch(physicalRepresentation.shape){
			case "circle":
				canvasContext.fillStyle = ui_color;
				canvasContext.beginPath();
				canvasContext.arc((screen_x / 2 + 10) - dx, (screen_y / 2 + 10) - dy, physicalRepresentation.radius, 0, 2 * Math.PI);
				canvasContext.closePath();
				canvasContext.fill();
				break;
			case "line":
				canvasContext.strokeStyle = "red";

				var dx1 = playerData.x - physicalRepresentation.startx;
				var dy1 = playerData.y - physicalRepresentation.starty;

				var dx2 = playerData.x - physicalRepresentation.endx;
				var dy2 = playerData.y - physicalRepresentation.endy;

				canvasContext.beginPath();
				canvasContext.moveTo((screen_x / 2 + 10) - dx1, (screen_y / 2 + 10) - dy1);
				canvasContext.lineTo((screen_x / 2 + 10) - dx2, (screen_y / 2 + 10) - dy2);
				canvasContext.stroke();
				break;
			case "missile":
				var image = new Image();
				image.src = "img/missile.png";
				canvasContext.drawImage(image, (screen_x / 2 + 10) - dx, (screen_y / 2 + 10) - dy, image.width, image.height);
				break;
		}
	}

	canvasContext.restore();
}

function drawExplosion(x, y) {
	canvasContext.save();
	canvasContext.translate(x, y);
	canvasContext.beginPath();
	canvasContext.arc(-5, 0, 17, 0, 2 * Math.PI);
	canvasContext.closePath();
	canvasContext.stroke();
	canvasContext.restore();
}

function drawShip(x, y, angle, hitRadius, name, hp, maxHp, invulnerability, shield, maxShield, color, type) {
	canvasContext.save();

	canvasContext.translate(x, y);
	canvasContext.rotate(angle * Math.PI / 180);

	if (type != "Asteroid") {
		if (invulnerability == false) {
			canvasContext.strokeStyle = color;
		} else {
			canvasContext.strokeStyle = "red";
		}
		var arc = (2 * shield / maxShield) * Math.PI;
		canvasContext.beginPath();
		canvasContext.arc(-5, 0, hitRadius + 15, -arc / 2, arc / 2);
		canvasContext.stroke();
	}

	canvasContext.fillStyle = "red";
	canvasContext.fillRect(-(hitRadius + 40), -20, 5, 40);
	canvasContext.fillStyle = "green";
	canvasContext.fillRect(-(hitRadius + 40), -20, 5, (40 * hp / maxHp));

	canvasContext.rotate(90 * Math.PI / 180);
	canvasContext.textAlign = "center";
	canvasContext.fillStyle = ui_color;
	canvasContext.fillText(name, 0, hitRadius + 30);

	var image = new Image();

	switch (type) {
		case "Quicksilver" :
			image.src = "img/ship_1_128.png";
			break;
		case "Mercury" :
			image.src = "img/ship_2_128.png";
			break;
		case "Interceptor" :
			image.src = "img/ship_3_128.png";
			break;
		case "Deltawing" :
			image.src = "img/ship_4_128.png";
			break;
		case "Cargoship" :
			image.src = "img/cargo_ship_120.png";
			break;
		case "Asteroid" :
			image.src = "img/asteroid_" + (name.charCodeAt(name.length - 1) % 3 + 1) + "_128.png";
			canvasContext.rotate(asteroid_rotation++ * Math.PI / 180);
			break;
	}

	image.width = 2 * hitRadius + 20;
	image.height = 2 * hitRadius + 20;
	canvasContext.drawImage(image, -image.width / 2, -image.height / 2, image.width, image.height);

	canvasContext.restore();
}

function pollPlayerData() {
	stompClient.send("/app/requestPlayerData_node" + playerData.connectionId, {}, playerData.id);
}

function updatePlayerData() {
	playerDataToSend.id = playerData.id;
	playerDataToSend.mouseX = playerData.mouseX;
	playerDataToSend.mouseY = playerData.mouseY;
	playerDataToSend.canvasWidth = playerData.canvasWidth;
	playerDataToSend.canvasHeight = playerData.canvasHeight;
	stompClient.send("/app/updatePlayerData", {}, JSON.stringify(playerDataToSend));
}

function start() {
	disableScroll();

	canvas = $("#gameArea")[0];
	canvasContext = canvas.getContext("2d");

	connect();
	setInterval(draw, 5);
	setInterval(updatePlayerData, 10);
	setInterval(pollPlayerData, 10);

	canvasContext.rotate(0 * Math.PI * 180);

	setInterval(function() {
		if (shootAmmoSwitch) {
			stompClient.send("/app/createAmmo", {}, playerData.id);
		}
	}, 50);

	$('#gameArea').mousedown(function() {
		shootAmmoSwitch = true;
	});

	$('#gameArea').mouseup(function() {
		shootAmmoSwitch = false;
	});

	$(document).keypress(function(e) {
		// key pressed is inclusively between '1' and '9'
		if (e.which >= 49 && e.which <= 57) {
			playerDataToSend.weaponIndex = e.which - 49;
			stompClient.send("/app/selectWeapon", {}, JSON.stringify(playerDataToSend));
		}
	});

	screen_x = $("#gameArea").width();
	screen_y = $("#gameArea").height();

	var stageData = JSON.parse(window.sessionStorage.getItem("stageData"));

	STAGE_X_MIN_LIMIT = stageData.minCoordinate.x;
	STAGE_Y_MIN_LIMIT = stageData.minCoordinate.y;

	STAGE_X_MAX_LIMIT = stageData.maxCoordinate.x;
	STAGE_Y_MAX_LIMIT = stageData.maxCoordinate.y;

	STAGE_MAX_WIDTH = STAGE_X_MAX_LIMIT - STAGE_X_MIN_LIMIT;
	STAGE_MAX_HEIGHT = STAGE_Y_MAX_LIMIT - STAGE_Y_MIN_LIMIT;

	STAGE_HALF_WIDTH = STAGE_MAX_WIDTH / 2;
	STAGE_HALF_HEIGHT = STAGE_MAX_HEIGHT / 2;
}

var keys = {
	37 : 1,
	38 : 1,
	39 : 1,
	40 : 1
};

function preventDefault(e) {
	e = e || window.event;
	if (e.preventDefault)
		e.preventDefault();
	e.returnValue = false;
}

function preventDefaultForScrollKeys(e) {
	if (keys[e.keyCode]) {
		preventDefault(e);
		return false;
	}
}

function disableScroll() {
	if (window.addEventListener) { // older FF
		window.addEventListener('DOMMouseScroll', preventDefault, false);
	}
	window.onwheel = preventDefault; // modern standard
	window.onmousewheel = document.onmousewheel = preventDefault; // older browsers, IE
	window.ontouchmove = preventDefault; // mobile
	document.onkeydown = preventDefaultForScrollKeys;
}

function updateCanvasSize() {
	playerData.canvasHeight = $("#gameArea").height();
	playerData.canvasWidth = $("#gameArea").width();
}

function updateMouseCoordinates(event) {
	// -10: the canvas starts with coordinates x = 10, y = 10
	playerData.mouseX = event.clientX - 10;
	playerData.mouseY = event.clientY - 10;
}

function drawMinimap() {
	canvasContext.save();

	canvasContext.fillStyle = "white";
	canvasContext.fillRect(screen_x - 10 - MINIMAP_WIDTH, screen_y - 10 - MINIMAP_HEIGHT, MINIMAP_WIDTH, MINIMAP_HEIGHT);

	canvasContext.strokeStyle = "black";
	canvasContext.strokeRect(screen_x - 10 - MINIMAP_WIDTH, screen_y - 10 - MINIMAP_HEIGHT, MINIMAP_WIDTH, MINIMAP_HEIGHT);

	canvasContext.restore();

	if (playerData.allPlayersPosition) {
		for (var i in playerData.allPlayersPosition) {
			var ship = playerData.allPlayersPosition[i];
			placeOnMinimap(ship.x, ship.y, ship.color);
		}
	}
}

function placeOnMinimap(mX, mY, color) {
	xPercent = (STAGE_HALF_WIDTH + mX) / STAGE_MAX_WIDTH * 100;
	yPercent = (STAGE_HALF_HEIGHT + mY) / STAGE_MAX_HEIGHT * 100;

	canvasContext.save();

	canvasContext.fillStyle = color;
	canvasContext.fillRect(screen_x - 10 - MINIMAP_WIDTH + (xPercent * 2) - 2, screen_y - 10 - MINIMAP_HEIGHT + (yPercent * 2) - 2, 4, 4);

	canvasContext.restore();
}