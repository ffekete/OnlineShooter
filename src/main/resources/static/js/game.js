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
	shipAngle: 0.0,
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
	playerData.shipAngle = JSON.parse(playerDataFromServer.body).shipAngle;	
	playerData.bullets = JSON.parse(playerDataFromServer.body).visibleBullets;
	playerData.otherPlayers = JSON.parse(playerDataFromServer.body).visiblePlayers;
	playerData.x = JSON.parse(playerDataFromServer.body).x;
	playerData.y = JSON.parse(playerDataFromServer.body).y;
	playerData.hp = JSON.parse(playerDataFromServer.body).shipHp;
	playerData.maxHp = JSON.parse(playerDataFromServer.body).shipMaxHp;
	playerData.invulnerable = JSON.parse(playerDataFromServer.body).invulnerable;
	playerData.itemsOnScreen = JSON.parse(playerDataFromServer.body).items;
	playerData.score = JSON.parse(playerDataFromServer.body).score;
	playerData.highScores = JSON.parse(playerDataFromServer.body).scores;
	playerData.shieldAmount = JSON.parse(playerDataFromServer.body).shieldAmount;
	playerData.maxShieldAmount = JSON.parse(playerDataFromServer.body).maxShieldAmount;
	playerData.respawnTime = JSON.parse(playerDataFromServer.body).respawnTime;
	playerData.color = JSON.parse(playerDataFromServer.body).color;
	playerData.shipType = JSON.parse(playerDataFromServer.body).shipType;
	playerData.weapon = JSON.parse(playerDataFromServer.body).weapon;
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
		canvasContext.fillText(playerData.highScores[i], 10 ,y);
		y+=10;
	}
}

function drawPlayerData(){
	canvasContext.fillText("Player details: ",150 ,15);
	canvasContext.fillText("Ship type: " + playerData.shipType, 150 ,25);
	canvasContext.fillText("HP: " + playerData.hp, 150, 35);
	canvasContext.fillText("Shield amount: " + playerData.shieldAmount, 150, 45);
	canvasContext.fillText("Weapon: " + playerData.weapon.name, 150, 55);
	canvasContext.fillText("RoF: " + playerData.weapon.rateOfFire, 150, 65);
	canvasContext.fillText("Ammo: " + playerData.weapon.ammo, 150, 75);
	canvasContext.fillText("Damage: " + playerData.weapon.damage, 150, 85);
}

function drawBackground(){
	var img = document.getElementById("bg");
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
	var playerDataToSend = {
			id: playerData.id,
			mouseX : playerData.mouseX,
			mouseY : playerData.mouseY,
			canvasWidth: playerData.canvasWidth,
			canvasHeight: playerData.canvasHeight
	};
	
	stompClient.send("/app/updatePlayerData", {}, JSON.stringify(playerDataToSend));
}

function start(){
	canvas = document.getElementById("gameArea");
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