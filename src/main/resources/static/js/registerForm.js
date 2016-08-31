var stompClient = null;
var selectedShip;

function loadShipType() {
	selectedShip = document.getElementById("shipSelector").value;

	updateShipData();
}

function connect() {
	var socket = new SockJS('/registerPlayer');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/playerRegistered',
				responseArrivedForRegisterPlayer);
		registerPlayerWithName();
	});
}

function updateShipImage() {
	selectedShip = document.getElementById("shipSelector").value;

	switch (selectedShip) {
		case "Quicksilver" :
			document.getElementById("shipImage").src = "img/ship01.png";
			break;
		case "Mercury" :
			document.getElementById("shipImage").src = "img/ship02.png";
			break;
		case "Interceptor" :
			document.getElementById("shipImage").src = "img/ship03.png";
			break;
		case "Deltawing" :
			document.getElementById("shipImage").src = "img/ship04.png";
			break;
	}
	updateShipData();
}

function updateShipData() {
	selectedShip = document.getElementById("shipSelector").value;

	switch (selectedShip) {
		case "Quicksilver" :
			document.getElementById("weaponName").innerHTML = "Shotgun";
			document.getElementById("speedValue").innerHTML = "15";
			document.getElementById("healthValue").innerHTML = "6";
			document.getElementById("maneuverabilityValue").innerHTML = "10";
			document.getElementById("cargoCapacity").innerHTML = "0";
			break;
		case "Mercury" :
			document.getElementById("weaponName").innerHTML = "Gatling Gun";
			document.getElementById("speedValue").innerHTML = "25";
			document.getElementById("healthValue").innerHTML = "5";
			document.getElementById("maneuverabilityValue").innerHTML = "12";
			document.getElementById("cargoCapacity").innerHTML = "0";
			break;
		case "Interceptor" :
			document.getElementById("weaponName").innerHTML = "Laser Cannon";
			document.getElementById("speedValue").innerHTML = "20";
			document.getElementById("healthValue").innerHTML = "7";
			document.getElementById("maneuverabilityValue").innerHTML = "14";
			document.getElementById("cargoCapacity").innerHTML = "0";
			break;
		case "Deltawing" :
			document.getElementById("weaponName").innerHTML = "Double Gatling Gun";
			document.getElementById("speedValue").innerHTML = "11";
			document.getElementById("healthValue").innerHTML = "9";
			document.getElementById("maneuverabilityValue").innerHTML = "16";
			document.getElementById("cargoCapacity").innerHTML = "0";
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
		// window.sessionStorage.setItem("color", $("#colorPicker").value);

		var name = document.getElementById('name').value;
		window.sessionStorage.setItem("playerName", name);
		window.sessionStorage.setItem("stageData", JSON
				.stringify(QualifiedPlayerData.stageData));

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

function clearWrongInput() {

}