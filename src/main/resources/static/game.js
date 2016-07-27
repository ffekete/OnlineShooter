
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

function draw(){
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");
	drawBackground();
	drawBorder();
	drawShip(410, 310, playerData.shipAngle, playerData.name, playerData.hp, playerData.invulnerable);
	
	if(playerData.otherPlayers){
		for(var i in playerData.otherPlayers){
			var actualShip = playerData.otherPlayers[i];
			
			var dx = playerData.x - actualShip.x;
			var dy = playerData.y - actualShip.y;
			drawShip(410-dx,310-dy, actualShip.shipAngle, actualShip.name, actualShip.hp, actualShip.invulnerable);
		}
	}
	drawBullets();
}

function drawBackground(){
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");
	var img = document.getElementById("bg");
	for(var i = -3; i < 6; i++)
	for(var j = -3; j < 6; j++)
		{
		ctx.drawImage(img, 0 + j* 250-(playerData.x % 250), 0 + i* 246-(playerData.y % 246));
		}
	
	ctx.fillText("Fps: " + requestAnimFrame(), 10,10);
	
	
}

function drawBullets(){
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");
	
	ctx.save();
	
	//ctx.fillText(playerData.x + " " + playerData.y, 10,10);
	
	for(var bullets in playerData.bullets){
		ctx.beginPath();
		
		var dx = playerData.x - playerData.bullets[bullets].x;
		var dy = playerData.y - playerData.bullets[bullets].y;
		
		ctx.arc(410 - dx,310 - dy, 5, 0, 2*Math.PI);
		ctx.stroke();
	}
	
	ctx.restore();
}

function drawShip(x, y, angle, name, hp, invulnerability){
	
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");

	ctx.save();
	
	ctx.fillStyle = "red";	
	ctx.translate(x,y);
	ctx.rotate(angle * Math.PI / 180);
	
	ctx.fillStyle = "red";
	ctx.fillRect(-37,-11,8,22);
	
	
	ctx.fillStyle = "green";
	ctx.fillRect(-35,-10,5,hp);

	
	invCtr = (invCtr + 1) % 20;
	
	if(invulnerability == false){
		ctx.strokeStyle = "black";
	}
	else
	{
		ctx.strokeStyle = "aqua";
	}
	ctx.beginPath();
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
	ctx.fillText(name, 0, 25);

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
	setInterval(draw, 25);
	setInterval(updatePlayerData, 20);
	setInterval(pollPlayerData, 10);
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
	
	$('#bg').css("display", "none"); // hide the background image
}

function updateMouseCoordinates(event){
	playerData.mouseX = event.clientX - 10; // -10: the canvas starts with coordinates x = 10,y = 10
	playerData.mouseY = event.clientY - 10;
}