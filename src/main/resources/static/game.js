
var stompClient = null;

var shootBulletSwitch=false;

var playerDataFromServer = {
	
};

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
}

function drawBorder(){
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");
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
}

function draw(){
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");
	ctx.clearRect(0,0, c.width, c.height);
	drawBorder();
	drawShip(410, 310, playerData.shipAngle, playerData.name);
	if(playerData.otherPlayers){
		for(var i in playerData.otherPlayers){
			var actualShip = playerData.otherPlayers[i];
			
			var dx = playerData.x - actualShip.x;
			var dy = playerData.y - actualShip.y;
			drawShip(410-dx,310-dy, actualShip.shipAngle, actualShip.name);
		}
	}
	drawBullets();
}

function drawBullets(){
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");
	
	ctx.fillText(playerData.x + " " + playerData.y, 10,10);
	
	for(var bullets in playerData.bullets){
		ctx.beginPath();
		
		var dx = playerData.x - playerData.bullets[bullets].x;
		var dy = playerData.y - playerData.bullets[bullets].y;
		
		ctx.arc(410 - dx,310 - dy, 5, 0, 2*Math.PI);
		ctx.stroke();
	}
}

function drawShip(x, y, angle, name){
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");
	ctx.save();
	
	ctx.fillStyle = "red";	
	ctx.translate(x,y);
	ctx.rotate(angle * Math.PI / 180);
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
	ctx.fillText(name, 0, 30);
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
	setInterval(updatePlayerData, 20);
	setInterval(pollPlayerData, 20);
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
}

function updateMouseCoordinates(event){
	playerData.mouseX = event.clientX - 10; // -10: the canvas starts with coordinates x = 10,y = 10
	playerData.mouseY = event.clientY - 10;
}