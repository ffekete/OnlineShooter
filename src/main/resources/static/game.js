
var stompClient = null;

var playerDataFromServer = {
	
};

var playerData = {
	id: window.sessionStorage.getItem("playerId"),
	connectionId: window.sessionStorage.getItem("connectionId"),
	mouseX : 0,
	mouseY : 0,
	shipAngle: 0.0
};

function connect() {
	var socket = new SockJS('/requestPlayerData_node' + playerData.connectionId);
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		
		stompClient.subscribe('/providePlayerData_node' + playerData.connectionId, playerDataArrived);
	});
}

function playerDataArrived(playerDataFromServer){
	playerData.shipAngle = JSON.parse(playerDataFromServer.body).angle;
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
	drawPlayerShip();
}

function drawPlayerShip(){
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");
	ctx.save();
	
	ctx.fillStyle = "red";	
	ctx.translate(410,310);
	ctx.rotate(playerData.shipAngle * Math.PI / 180);
	//ctx.fillRect(-10, -10, 20, 20);
	ctx.beginPath();
	ctx.moveTo(-15, -10);
	ctx.lineTo(10, 0);
	ctx.moveTo(-15, 10);
	ctx.lineTo(10, 0);
	ctx.stroke();
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
	setInterval(draw, 30);
	setInterval(updatePlayerData, 30);
	setInterval(pollPlayerData, 30);
	var c = document.getElementById("gameArea");
	var ctx = c.getContext("2d");
	ctx.rotate(0*Math.PI*180);
}

function updateMouseCoordinates(event){
	playerData.mouseX = event.clientX;
	playerData.mouseY = event.clientY;
}