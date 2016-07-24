
var stompClient = null;

var playerData = {
	id: window.sessionStorage.getItem("playerId"),
	mouseX : 0,
	mouseY : 0
};

function connect() {
	var socket = new SockJS('/updatePlayerData');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
	});
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
	setInterval(draw, 10);
	setInterval(updatePlayerData, 100);
}

function updateMouseCoordinates(event){
	playerData.mouseX = event.clientX;
	playerData.mouseY = event.clientY;
}