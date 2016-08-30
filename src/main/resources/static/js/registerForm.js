var stompClient = null;
var selectedShip;

function loadShipType(){
	selectedShip = document.getElementById("shipSelector").value;
}

function connect() {
	var socket = new SockJS('/registerPlayer');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/playerRegistered', responseArrivedForRegisterPlayer);
		registerPlayerWithName();
	});
}

function updateShipImage(){
	selectedShip = document.getElementById("shipSelector").value;
	console.log("##############" + selectedShip);
	switch(selectedShip){
	case "Quicksilver":
		document.getElementById("ship").src = "img/ship_1_128.png";
		break;
	case "Mercury":
		document.getElementById("ship").src = "img/ship_2_128.png";
		break;
	case "Interceptor":
		document.getElementById("ship").src = "img/ship_3_128.png";
		break;
	case "Deltawing":
		document.getElementById("ship").src = "img/ship_4_128.png";
		break;
	}
	
}

function responseArrivedForRegisterPlayer(playerStatus) {
	var QualifiedPlayerData = JSON.parse(playerStatus.body); 
	if (QualifiedPlayerData.registered) {
		var id = QualifiedPlayerData.playerData.id;
		var connectionId = QualifiedPlayerData.playerData.connectionId;
		
		window.sessionStorage.setItem("playerId", id);
		window.sessionStorage.setItem("connectionId", connectionId);
		//window.sessionStorage.setItem("color", $("#colorPicker").value);		
		
		var name = document.getElementById('name').value;
		window.sessionStorage.setItem("playerName", name);
		window.sessionStorage.setItem("stageData", JSON.stringify(QualifiedPlayerData.stageData));
		
		document.getElementById("response").innerHTML = "Redirecting to game area, please wait...";
		window.location.replace("/game_area.html");

	} else {
		document.getElementById("response").innerHTML = "Player registration error!";
	}
}

function registerPlayerWithName() {
	var name = document.getElementById('name').value;
	var color = document.getElementById("colorPicker").value;
	var data = {};
	data.name = name;
	data.color = color;
	data.shipType = selectedShip;
	
	stompClient.send("/app/registerPlayer", {}, JSON.stringify(data));
}