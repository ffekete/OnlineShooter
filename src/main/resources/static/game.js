var screen_x = $("#gameArea").width();
var screen_y = $("#gameArea").height();

var X_MIN_LIMIT = -1000;
var Y_MIN_LIMIT = -1000;

var X_MAX_LIMIT = 1000;
var Y_MAX_LIMIT = 1000;

var stompClient = null;

var shootBulletSwitch=false;

var invCtr = 0;

var explosions = [];

var playerDataFromServer = {
	
};

var c;
var ctx;

/* Array of particles (global variable)
*/
var particles = [];

var playerData = {
	name: window.sessionStorage.getItem("playerName"), 
	id: window.sessionStorage.getItem("playerId"),
	//color: window.sessionStorage.getItem("color"),
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
 * Advanced Explosion effect
 * Each particle has a different size, move speed and scale speed.
 * 
 * Parameters:
 * 	x, y - explosion center
 * 	color - particles' color
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
	updateParticles(8, ctx);
}

function draw(){
	ctx.canvas.width  = window.innerWidth-20;
	ctx.canvas.height = window.innerHeight-20;
	  
	screen_x = $("#gameArea").width();
	screen_y = $("#gameArea").height();
	
	updateCanwasSize();
	
	drawBackground();
	drawBorder();
	
	
	if(playerData.respawnTime == 0){
		drawShip(screen_x / 2 + 10, screen_y /2 +10, playerData.shipAngle, playerData.name, playerData.hp, playerData.invulnerable, playerData.shieldAmount, playerData.maxShieldAmount, playerData.color);
	}
	else
	{
		ctx.save();
		ctx.textAlign ="center";
		ctx.font="40px Arial";
		ctx.fillText("Respawning in seconds: " + parseInt(playerData.respawnTime / 100 + 1), screen_x / 2 + 10, screen_y /2 +10);
		ctx.restore();
	}
	
	if(playerData.otherPlayers){
		for(var i in playerData.otherPlayers){
			var actualShip = playerData.otherPlayers[i];
			
			var dx = playerData.x - actualShip.x;
			var dy = playerData.y - actualShip.y;
			drawShip((screen_x / 2 + 10)-dx,(screen_y / 2 + 10)-dy, actualShip.shipAngle, actualShip.name, actualShip.hp, actualShip.invulnerable, actualShip.shield.protection, actualShip.shield.maxProtectionValue, actualShip.color);
		}
	}
	drawBullets();
	drawItems();
	drawExplosions();
	drawHighScores();
}

function connect() {
	var socket = new SockJS('/requestPlayerData_node' + playerData.connectionId);
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		
		stompClient.subscribe('/providePlayerData_node' + playerData.connectionId, playerDataArrived);
		stompClient.subscribe('/messages', messageArrived);
		stompClient.subscribe('/events', eventArrived);
	});
}

function eventArrived(event){
	var eventInfo = {};
	
	eventInfo.eventCommand = JSON.parse(event.body).eventCommand;
	eventInfo.eventX = JSON.parse(event.body).event_x;
	eventInfo.eventY = JSON.parse(event.body).event_y;
	
	if(eventInfo.eventCommand === "PLAY_EXPLOSION_ANIM"){
		createExplosion(eventInfo.eventX, eventInfo.eventY, "red");
		createExplosion(eventInfo.eventX, eventInfo.eventY, "yellow");
	}
	if(eventInfo.eventCommand === "PLAY_HIT_ANIM"){
		createBasicExplosion(eventInfo.eventX, eventInfo.eventY);
	}
	
	$("#messagebox").append(eventInfo.eventCommand + " " + ((screen_x / 2 + 10)-dx) + " " + ((screen_y / 2 + 10)-dy) +"\n");
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
	playerData.invulnerable = JSON.parse(playerDataFromServer.body).invulnerable;
	playerData.itemsOnScreen = JSON.parse(playerDataFromServer.body).items;
	playerData.score = JSON.parse(playerDataFromServer.body).score;
	playerData.highScores = JSON.parse(playerDataFromServer.body).scores;
	playerData.shieldAmount = JSON.parse(playerDataFromServer.body).shieldAmount;
	playerData.maxShieldAmount = JSON.parse(playerDataFromServer.body).maxShieldAmount;
	playerData.respawnTime = JSON.parse(playerDataFromServer.body).respawnTime;
	playerData.color = JSON.parse(playerDataFromServer.body).color;
}

function drawBorder(){
	ctx.save();
	
	ctx.fillStyle = "black";
	ctx.moveTo(1,1);
	ctx.lineTo(1, c.height);
	
	ctx.moveTo(1, c.height);
	ctx.lineTo(c.width, c.height);
	
	ctx.moveTo(c.width, c.height);
	ctx.lineTo(c.width, 1);
	
	ctx.moveTo(c.width, 1);
	ctx.lineTo(1, 1);
	
	ctx.stroke();
	
	ctx.restore();
}

function drawHighScores(){
	ctx.fillText("High score table: ", 10 ,25);
	var y = 35;
	for(var i in playerData.highScores){
		ctx.fillText(playerData.highScores[i], 10 ,y);
		y+=10;
	}
}

function drawBackground(){
	var img = document.getElementById("bg");
	for(var i = -5; i < 10; i++)
	for(var j = -5; j < 10; j++)
		{
		ctx.drawImage(img, 0 + j* 250-(playerData.x % 250), 0 + i* 246-(playerData.y % 246));
		}
	
	ctx.fillText("Your score: " + playerData.score, 10,15);
	ctx.save();
	
	var dx = Math.abs(playerData.x - X_MIN_LIMIT);
	var dy = Math.abs(playerData.y - Y_MIN_LIMIT);
	
	var dxm = Math.abs(X_MIN_LIMIT) + X_MAX_LIMIT;
	var dym = Math.abs(Y_MIN_LIMIT) + Y_MAX_LIMIT;
	
	
	ctx.rect((screen_x / 2) - dx, (screen_y / 2) - dy, dxm, dym);
	
	ctx.restore();
}

function drawItems(){
	ctx.save();
	
	for(var items in playerData.itemsOnScreen){
		ctx.beginPath();
		
		var dx = playerData.x - playerData.itemsOnScreen[items].x;
		var dy = playerData.y - playerData.itemsOnScreen[items].y;
		
		ctx.arc((screen_x / 2 + 10) - dx,(screen_y / 2 + 10) - dy, 5, 0, 2*Math.PI);
		ctx.stroke();
		ctx.textAlign ="center";
		ctx.font="15px Arial";
		ctx.fillText(playerData.itemsOnScreen[items].name, (screen_x / 2 + 10) - dx,(screen_y / 2 + 10) - dy + 15);
	}
	
	ctx.restore();
}

function drawBullets(){
	ctx.save();
	
	for(var bullets in playerData.bullets){
		ctx.beginPath();
		
		var dx = playerData.x - playerData.bullets[bullets].x;
		var dy = playerData.y - playerData.bullets[bullets].y;
		ctx.save();
		ctx.fillStyle = "black";
		ctx.arc((screen_x / 2 + 10) - dx,(screen_y / 2 + 10) - dy, 2, 0, 2*Math.PI);
		ctx.fill();
		ctx.restore();
	}
	
	ctx.restore();
}

function drawExplosion(x, y){
	ctx.save();
	ctx.translate(x,y);
	ctx.beginPath();
	ctx.arc(-5, 0, 17, 0, 2*Math.PI);
	ctx.stroke();
	ctx.restore();
}

function drawShip(x, y, angle, name, hp, invulnerability, shield, maxShield, color){
	ctx.save();

	ctx.fillStyle = "red";	
	ctx.translate(x,y);
	ctx.rotate(angle * Math.PI / 180);
	
	ctx.fillStyle = "red";
	ctx.fillRect(-41,-11,8,22);
	
	
	ctx.fillStyle = "green";
	ctx.fillRect(-39,-10,5,hp);

	invCtr = (invCtr + 1) % 20;
	
	if(invulnerability == false){
		ctx.strokeStyle = "black";
	}
	else
	{
		ctx.strokeStyle = "aqua";
	}
	
	ctx.beginPath();
	ctx.arc(-5, 0, 17, 0, (2 * shield / maxShield)*Math.PI);
	ctx.stroke();
	
	ctx.save();
	
	ctx.beginPath();
	ctx.moveTo(-15, -10);
	ctx.lineTo(-5, 0);
	ctx.lineTo(-15, 10);
	ctx.lineTo(10, 0);
	ctx.lineTo(-15, -10);	
	ctx.closePath();
	ctx.fillStyle = color;
	ctx.fill();

	ctx.restore();
	
	ctx.rotate(90 * Math.PI / 180);
	ctx.textAlign ="center";
	ctx.fillText(name, 0, 29);
	ctx.restore();
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
	c = document.getElementById("gameArea");
	ctx = c.getContext("2d");
	
	drawBorder();
	connect();
	setInterval(draw, 5);
	setInterval(updatePlayerData, 10);
	setInterval(pollPlayerData, 10);
	
	ctx.rotate(0*Math.PI*180);
	
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
	
	$('#bg').css("display", "none"); // hide the background image
}

function updateCanwasSize(){
	playerData.canvasHeight = $("#gameArea").height();
	playerData.canvasWidth = $("#gameArea").width();
}

function updateMouseCoordinates(event){
	playerData.mouseX = event.clientX - 10; // -10: the canvas starts with coordinates x = 10,y = 10
	playerData.mouseY = event.clientY - 10;
}