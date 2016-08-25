
var screen_x = $("#gameArea").width();
var screen_y = $("#gameArea").height();

/**/
var STAGE_X_MIN_LIMIT = 0;
var STAGE_Y_MIN_LIMIT = 0;

var STAGE_X_MAX_LIMIT = 0;
var STAGE_Y_MAX_LIMIT = 0;

var stompClient = null;

var shootBulletSwitch=false;

var canvas;
var canvasContext;

/*
 * Array of particles (global variable)
 */
var particles = [];

var playerData = {
	name: window.sessionStorage.getItem("playerName"), 
	id: window.sessionStorage.getItem("playerId"),
	// color: window.sessionStorage.getItem("color"),
	connectionId: window.sessionStorage.getItem("connectionId"),
	mouseX : 0,
	mouseY : 0,
	shipAngle: 0.0
};

var playerDataToSend = {
	id: playerData.id,
	mouseX : playerData.mouseX,
	mouseY : playerData.mouseY,
	canvasWidth: playerData.canvasWidth,
	canvasHeight: playerData.canvasHeight,
	weaponIndex : 0
};

/*
 * A single explosion particle
 */
function Particle ()
{
	this.scale = 1.0;
	this.x = 0;
	this.y = 0;
	this.radius = 20;
	this.color = "#000";
	this.velocityX = 0;
	this.velocityY = 0;
	this.scaleSpeed = 0.5;

	this.update = function(ms)
	{
		// shrinking
		this.scale -= this.scaleSpeed * ms / 1000.0;

		if (this.scale <= 0)
		{
			this.scale = 0;
		}
		// moving away from explosion center
		this.x += this.velocityX * ms/1000.0;
		this.y += this.velocityY * ms/1000.0;
	};

	this.draw = function(context2D, offsettedX, offsettedY)
	{
		// translating the 2D context to the particle coordinates
		context2D.save();
		context2D.translate(offsettedX, offsettedY);
		context2D.scale(this.scale, this.scale);

		// drawing a filled circle in the particle's local space
		context2D.beginPath();
		context2D.arc(0, 0, this.radius, 0, Math.PI*2, true);
		context2D.closePath();

		context2D.fillStyle = this.color;
		context2D.fill();

		context2D.restore();
	};
}

function randomFloat (min, max)
{
	return min + Math.random()*(max-min);
}

/*
 * Advanced Explosion effect Each particle has a different size, move speed and
 * scale speed.
 * 
 * Parameters: x, y - explosion center color - particles' color
 */
function createExplosion(x, y, color)
{
	var minSize = 10;
	var maxSize = 30;
	var count = 10;
	var minSpeed = 60.0;
	var maxSpeed = 200.0;
	var minScaleSpeed = 1.0;
	var maxScaleSpeed = 4.0;

	for (var angle=0; angle<360; angle += Math.round(360/count))
	{
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
function createBasicExplosion(x, y)
{
	// creating 4 particles that scatter at 0, 90, 180 and 270 degrees
	for (var angle=0; angle<360; angle+=90)
	{
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

function updateParticles (frameDelay, context2D)
{
	// update and draw particles
	for (var i=0; i<particles.length; i++)
	{
		var particle = particles[i];

		particle.update(frameDelay);

		var offsetX = playerData.x - particle.x; 
		var offsetY =playerData.y - particle.y;
		particle.draw(context2D, (screen_x / 2 + 10) - offsetX, (screen_y / 2 + 10) - offsetY);
	}
}

function drawExplosions(){
	updateParticles(8, canvasContext);
}

function draw(){
	canvasContext.canvas.width  = window.innerWidth-20;
	canvasContext.canvas.height = window.innerHeight-20;
	  
	screen_x = $("#gameArea").width();
	screen_y = $("#gameArea").height();
	
	updateCanwasSize();
	
	drawBackground();
	drawBorder();
	
	
	if(playerData.respawnTime == 0){
		drawShip(screen_x / 2 + 10, screen_y /2 +10, playerData.shipAngle, playerData.name, playerData.hp, playerData.maxHp, playerData.invulnerable, playerData.shieldAmount, playerData.maxShieldAmount, playerData.color, playerData.shipType);
	}
	else
	{
		canvasContext.save();
		canvasContext.textAlign ="center";
		canvasContext.font="40px Arial";
		canvasContext.fillText("Respawning in seconds: " + parseInt(playerData.respawnTime / 100 + 1), screen_x / 2 + 10, screen_y /2 +10);
		canvasContext.restore();
	}
	
	if(playerData.otherPlayers){
		for(var i in playerData.otherPlayers){
			var actualShip = playerData.otherPlayers[i];
			
			var dx = playerData.x - actualShip.x;
			var dy = playerData.y - actualShip.y;
			drawShip((screen_x / 2 + 10)-dx,(screen_y / 2 + 10)-dy, actualShip.shipAngle, actualShip.name, actualShip.hp, actualShip.maxHp, actualShip.invulnerable, actualShip.shield.protection, actualShip.shield.maxProtectionValue, actualShip.color, actualShip.shipType);
		}
	}
	drawBullets();
	drawItems();
	drawExplosions();
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

function eventArrived(event){
	var eventInfo = {};
	
	eventInfo.eventCommand = JSON.parse(event.body).eventCommand;
	eventInfo.eventX = JSON.parse(event.body).eventX;
	eventInfo.eventY = JSON.parse(event.body).eventY;
	
	if(eventInfo.eventCommand === "PLAY_EXPLOSION_ANIM"){
		createExplosion(eventInfo.eventX, eventInfo.eventY, "red");
		createExplosion(eventInfo.eventX, eventInfo.eventY, "yellow");
	}
	if(eventInfo.eventCommand === "PLAY_HIT_ANIM"){
		createBasicExplosion(eventInfo.eventX, eventInfo.eventY);
	}
}

function messageArrived(message){
	console.log(message.body);
}

function shootBullet(){
	stompClient.send("/app/createBullet", {}, playerData.id);
}

function playerDataArrived(playerDataFromServer){
	var parsedPlayerData = JSON.parse(playerDataFromServer.body);
	playerData.shipAngle = parsedPlayerData.shipAngle;	
	playerData.bullets = parsedPlayerData.visibleBullets;
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
}

function drawBorder(){
	canvasContext.save();
	
	canvasContext.fillStyle = "black";
	canvasContext.moveTo(1,1);
	canvasContext.lineTo(1, canvas.height);
	
	canvasContext.moveTo(1, canvas.height);
	canvasContext.lineTo(canvas.width, canvas.height);
	
	canvasContext.moveTo(canvas.width, canvas.height);
	canvasContext.lineTo(canvas.width, 1);
	
	canvasContext.moveTo(canvas.width, 1);
	canvasContext.lineTo(1, 1);
	
	canvasContext.stroke();
	
	canvasContext.restore();
}

function drawHighScores(){
	canvasContext.fillText("High score table: ", 10 ,25);
	var y = 35;
	for(var i in playerData.highScores){
		canvasContext.fillText(playerData.highScores[i], 10, y);
		y += 10;
	}
}

function drawPlayerData(){
	var fromLeft = 150;
	var fromTop = 15;
	var verticalSpace = 10;
	canvasContext.fillText("Player details: ", fromLeft, fromTop);
	canvasContext.fillText("Ship type: " + playerData.shipType, fromLeft, fromTop + verticalSpace);
	canvasContext.fillText("HP: " + playerData.hp, fromLeft, fromTop + verticalSpace * 2);
	canvasContext.fillText("Shield amount: " + playerData.shieldAmount, fromLeft, fromTop + verticalSpace * 3);
	canvasContext.fillText("Weapon: " + playerData.weapon.name, fromLeft, fromTop + verticalSpace * 4);
	canvasContext.fillText("RoF: " + playerData.weapon.rateOfFire, fromLeft, fromTop + verticalSpace * 5);
	canvasContext.fillText("Ammo: " + playerData.weapon.ammo, fromLeft, fromTop + verticalSpace * 6);
	canvasContext.fillText("Damage: " + playerData.weapon.damage, fromLeft, fromTop + verticalSpace * 7);
}

function drawWeaponKeys(){
	var fromLeft = 300;
	var fromTop = 15;
	var verticalSpace = 10;
	$.each(playerData.weapons, function(index, weapon) {
		canvasContext.fillText("[" + (index + 1) + "]: " + weapon.name +" (" + weapon.ammo + ")", fromLeft, fromTop + verticalSpace * index);
	});
}

function drawBackground(){
	var img = $("#bg")[0];
	for(var i = -5; i < 10; i++)
	for(var j = -5; j < 10; j++)
		{
		canvasContext.drawImage(img, 0 + j* 250-(playerData.x % 250), 0 + i* 246-(playerData.y % 246));
		}
	
	canvasContext.fillText("Your score: " + playerData.score, 10,15);
	canvasContext.save();
	
	var dx = Math.abs(playerData.x - STAGE_X_MIN_LIMIT);
	var dy = Math.abs(playerData.y - STAGE_Y_MIN_LIMIT);
	
	var dxm = Math.abs(STAGE_X_MIN_LIMIT) + STAGE_X_MAX_LIMIT;
	var dym = Math.abs(STAGE_Y_MIN_LIMIT) + STAGE_Y_MAX_LIMIT;
	
	
	canvasContext.rect((screen_x / 2) - dx, (screen_y / 2) - dy, dxm, dym);
	
	canvasContext.restore();
}

function drawItems(){
	canvasContext.save();
	
	for(var items in playerData.itemsOnScreen){
		canvasContext.beginPath();
		
		var dx = playerData.x - playerData.itemsOnScreen[items].x;
		var dy = playerData.y - playerData.itemsOnScreen[items].y;
		
		canvasContext.arc((screen_x / 2 + 10) - dx,(screen_y / 2 + 10) - dy, 5, 0, 2*Math.PI);
		canvasContext.stroke();
		canvasContext.textAlign ="center";
		canvasContext.font="15px Arial";
		canvasContext.fillText(playerData.itemsOnScreen[items].name, (screen_x / 2 + 10) - dx,(screen_y / 2 + 10) - dy + 15);
	}
	
	canvasContext.restore();
}

function drawBullets(){
	canvasContext.save();
	
	for(var bullets in playerData.bullets){
		canvasContext.beginPath();
		
		var dx = playerData.x - playerData.bullets[bullets].x;
		var dy = playerData.y - playerData.bullets[bullets].y;
		
		var physicalRepresentation = JSON.parse(playerData.bullets[bullets].physicalRepresentation);
		
		canvasContext.save();
		
		if(physicalRepresentation.shape === "circle"){
			canvasContext.fillStyle = "black";
			canvasContext.arc((screen_x / 2 + 10) - dx,(screen_y / 2 + 10) - dy, 2, 0, 2*Math.PI);
			canvasContext.fill();
		}
		
		if(physicalRepresentation.shape === "line"){
			canvasContext.strokeStyle = "red";

			var dx1 = playerData.x - physicalRepresentation.startx;
			var dy1 = playerData.y - physicalRepresentation.starty;
			
			var dx2 = playerData.x - physicalRepresentation.endx;
			var dy2 = playerData.y - physicalRepresentation.endy;
			
			canvasContext.moveTo((screen_x / 2 + 10) - dx1, (screen_y / 2 + 10) - dy1);
			canvasContext.lineTo((screen_x / 2 + 10) - dx2, (screen_y / 2 + 10) - dy2);
			canvasContext.stroke();
		}
		
		canvasContext.restore();
	}
	
	canvasContext.restore();
}

function drawExplosion(x, y){
	canvasContext.save();
	canvasContext.translate(x,y);
	canvasContext.beginPath();
	canvasContext.arc(-5, 0, 17, 0, 2*Math.PI);
	canvasContext.stroke();
	canvasContext.restore();
}

function drawShip(x, y, angle, name, hp, maxHp, invulnerability, shield, maxShield, color, type){
	canvasContext.save();

	canvasContext.fillStyle = "red";	
	canvasContext.translate(x,y);
	canvasContext.rotate(angle * Math.PI / 180);
	
	canvasContext.fillStyle = "red";
	canvasContext.fillRect(-41,-11,8,22);
	
	
	canvasContext.fillStyle = "green";
	canvasContext.fillRect(-39,-10,5,(21*hp/maxHp));
	
	if(invulnerability == false){
		canvasContext.strokeStyle = "black";
	}
	else
	{
		canvasContext.strokeStyle = "aqua";
	}
	
	canvasContext.beginPath();
	canvasContext.arc(-5, 0, 17, 0, (2 * shield / maxShield)*Math.PI);
	canvasContext.stroke();
	
	canvasContext.save();
	
	if(type === "Mercury")
	{
		canvasContext.beginPath();
		canvasContext.moveTo(-15, -10);
		canvasContext.lineTo(-5, 0);
		canvasContext.lineTo(-15, 10);
		canvasContext.lineTo(10, 0);
		canvasContext.lineTo(-15, -10);	
		canvasContext.closePath();
	}
	if(type === "Quicksilver")
	{
		canvasContext.beginPath();
		canvasContext.moveTo(-13, -10);
		canvasContext.lineTo(-18, 0);
		canvasContext.lineTo(-13, 10);
		canvasContext.lineTo(10, 0);
		canvasContext.lineTo(-13, -10);	
		canvasContext.closePath();
	}
	
	if(type === "Interceptor")
	{
		canvasContext.beginPath();
		canvasContext.moveTo(-15, -15);
		canvasContext.lineTo(-5, -8);
		canvasContext.lineTo(10, -5);
		canvasContext.lineTo(5, -2);
		canvasContext.lineTo(5, 2);
		canvasContext.lineTo(10, 5);
		canvasContext.lineTo(-5, 8);
		canvasContext.lineTo(-15, 15);
		canvasContext.lineTo(-20, 0);
		canvasContext.closePath();
	}
	
	if(type === "Deltawing")
	{
		canvasContext.beginPath();
		canvasContext.moveTo(-15, 0);
		canvasContext.lineTo(-7, -15);
		canvasContext.lineTo(-7, -3);
		canvasContext.bezierCurveTo(-15,-3,15,-3,15,0);
		canvasContext.bezierCurveTo(15,3,-15,3,-7,3);
		canvasContext.lineTo(-7, 15);
		canvasContext.closePath();
	}
	
	canvasContext.fillStyle = color;
	canvasContext.fill();

	canvasContext.restore();
	
	canvasContext.rotate(90 * Math.PI / 180);
	canvasContext.textAlign ="center";
	canvasContext.fillText(name, 0, 29);
	canvasContext.restore();
}

function pollPlayerData(){
	stompClient.send("/app/requestPlayerData_node" + playerData.connectionId, {}, playerData.id);
}

function updatePlayerData(){	
	playerDataToSend.id = playerData.id;
	playerDataToSend.mouseX = playerData.mouseX;
	playerDataToSend.mouseY = playerData.mouseY;
	playerDataToSend.canvasWidth = playerData.canvasWidth;
	playerDataToSend.canvasHeight = playerData.canvasHeight;
	stompClient.send("/app/updatePlayerData", {}, JSON.stringify(playerDataToSend));
}

function start(){
	canvas = $("#gameArea")[0];
	canvasContext = canvas.getContext("2d");
	
	drawBorder();
	connect();
	setInterval(draw, 5);
	setInterval(updatePlayerData, 10);
	setInterval(pollPlayerData, 10);
	
	canvasContext.rotate(0*Math.PI*180);
	
	setInterval(function(){
		if (shootBulletSwitch){
			stompClient.send("/app/createBullet", {}, playerData.id);
		}
	}, 50);

	$('#gameArea').mousedown(function(){
		shootBulletSwitch = true;
	});

	$('#gameArea').mouseup(function(){
		shootBulletSwitch = false;
	});
	
	$(document).keypress(function(e){
		// key pressed is inclusively between '1' and '9'
		if(e.which >= 49 && e.which <= 57) {
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
	
	$('#bg').css("display", "none"); // hide the background image
}

function updateCanwasSize(){
	playerData.canvasHeight = $("#gameArea").height();
	playerData.canvasWidth = $("#gameArea").width();
}

function updateMouseCoordinates(event){
	playerData.mouseX = event.clientX - 10; // -10: the canvas starts with
											// coordinates x = 10,y = 10
	playerData.mouseY = event.clientY - 10;
}