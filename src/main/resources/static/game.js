var screen_x = $("#gameArea").width();
var screen_y = $("#gameArea").height();

var X_MIN_LIMIT = -1000;
var Y_MIN_LIMIT = -1000;

var X_MAX_LIMIT = 1000;
var Y_MAX_LIMIT = 1000;

var stompClient = null;

var shootBulletSwitch=false;

var invCtr = 0;

var playerDataFromServer = {
	
};

var lastCalledTime;
var fps;

function requestAnimFrame() {

  if(!lastCalledTime) {
     lastCalledTime = Date.now();
     fps = 0;
     return;
  }
  delta = (Date.now() - lastCalledTime)/1000;
  lastCalledTime = Date.now();
  fps = 1/delta;
  
  return fps;
} 

var playerData = {
	name: window.sessionStorage.getItem("playerName"), 
	id: window.sessionStorage.getItem("playerId"),
	connectionId: window.sessionStorage.getItem("connectionId"),
	mouseX : 0,
	mouseY : 0,
	shipAngle: 0.0,
};

function draw(){
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");
	drawBackground();
	drawBorder();
	
	
	if(playerData.respawnTime == 0){
		drawShip(screen_x / 2 + 10, screen_y /2 +10, playerData.shipAngle, playerData.name, playerData.hp, playerData.invulnerable, playerData.shieldAmount, playerData.maxShieldAmount);
	}
	else
	{
		ctx.save();
		ctx.textAlign ="center";
		ctx.font="40px Arial";
		ctx.fillText("Respawning in seconds: " + playerData.respawnTime / 100 + 1, screen_x / 2 + 10, screen_y /2 +10);
		ctx.restore();
	}
	
	if(playerData.otherPlayers){
		for(var i in playerData.otherPlayers){
			var actualShip = playerData.otherPlayers[i];
			
			var dx = playerData.x - actualShip.x;
			var dy = playerData.y - actualShip.y;
			drawShip((screen_x / 2 + 10)-dx,(screen_y / 2 + 10)-dy, actualShip.shipAngle, actualShip.name, actualShip.hp, actualShip.invulnerable, actualShip.shield.protection, actualShip.shield.maxProtectionValue);
		}
	}
	drawBullets();
	drawItems();
	drawHighScores();
}

function connect() {
	var socket = new SockJS('/requestPlayerData_node' + playerData.connectionId);
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		
		stompClient.subscribe('/providePlayerData_node' + playerData.connectionId, playerDataArrived);
	});
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
}

function drawBorder(){
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");
	
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
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");
	ctx.fillText("High score table: ", 10 ,25);
	var y = 35;
	for(var i in playerData.highScores){
		ctx.fillText(playerData.highScores[i], 10 ,y);
		y+=10;
	}
}

function drawBackground(){
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");
	var img = document.getElementById("bg");
	for(var i = -2; i < 6; i++)
	for(var j = -2; j < 6; j++)
		{
		ctx.drawImage(img, 0 + j* 250-(playerData.x % 250), 0 + i* 246-(playerData.y % 246));
		}
	
	ctx.fillText("Your score: " + playerData.score, 10,15);
	//ctx.fillText("Shield: " + playerData.shieldAmount + " / " + playerData.maxShieldAmount, 100,15);
	ctx.save();
	
	//ctx.translate(playerData.x, playerData.y);	
	
	var dx = Math.abs(playerData.x - X_MIN_LIMIT);
	var dy = Math.abs(playerData.y - Y_MIN_LIMIT);
	
	var dxm = Math.abs(X_MIN_LIMIT) + X_MAX_LIMIT;
	var dym = Math.abs(Y_MIN_LIMIT) + Y_MAX_LIMIT;
	
	
	ctx.rect((screen_x / 2) - dx, (screen_y / 2) - dy, dxm, dym);
	
	ctx.restore();
}

function drawItems(){
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");
	
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
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");
	
	ctx.save();
	
	for(var bullets in playerData.bullets){
		ctx.beginPath();
		
		var dx = playerData.x - playerData.bullets[bullets].x;
		var dy = playerData.y - playerData.bullets[bullets].y;
		
		ctx.arc((screen_x / 2 + 10) - dx,(screen_y / 2 + 10) - dy, 2, 0, 2*Math.PI);
		ctx.stroke();
	}
	
	ctx.restore();
}

function drawShip(x, y, angle, name, hp, invulnerability, shield, maxShield){
	
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");

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
	
	ctx.moveTo(-15, -10);
	ctx.lineTo(10, 0);
	
	ctx.moveTo(-15,-10)
	ctx.lineTo(-5, 0);
	
	ctx.moveTo(-15,10)
	ctx.lineTo(-5, 0);
	
	ctx.moveTo(-15, 10);
	ctx.lineTo(10, 0);

	ctx.stroke();
	
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
			mouseY : playerData.mouseY
	};
	
	stompClient.send("/app/updatePlayerData", {}, JSON.stringify(playerDataToSend));
}

function start(){
	drawBorder();
	connect();
	setInterval(draw, 20);
	setInterval(updatePlayerData, 25);
	setInterval(pollPlayerData, 25);
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");
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

function updateMouseCoordinates(event){
	playerData.mouseX = event.clientX - 10; // -10: the canvas starts with coordinates x = 10,y = 10
	playerData.mouseY = event.clientY - 10;
}