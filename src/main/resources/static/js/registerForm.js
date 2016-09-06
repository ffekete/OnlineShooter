var stompClient = null;
var selectedShip;
var shipsDetails;

function loadShipType() {
	getShipDetails();
}

function getShipDetails() {
	var socket = new SockJS('/getShipDetails');
	stompClient = Stomp.over(socket);

	stompClient.connect({}, function(frame) {
		stompClient.subscribe('/sendShipsDetails',
				responseArrivedForGetShipDetails);
		updateShipDetailsWithShipDetails();
	});
}

function responseArrivedForGetShipDetails(message) {
	shipsDetails = JSON.parse(message.body);
	updateShipData();
}

function updateShipDetailsWithShipDetails() {
	stompClient.send("/app/getShipDetails", {});
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

function updateShipDetails() {
	updateShipImage();
	updateShipData();
}

function updateShipImage() {
	selectedShip = document.getElementById("shipSelector").value;

	switch (selectedShip) {
		case "Quicksilver" :
			document.getElementById("shipImage").src = "img/ship_1_128.png";
			break;
		case "Mercury" :
			document.getElementById("shipImage").src = "img/ship_2_128.png";
			break;
		case "Interceptor" :
			document.getElementById("shipImage").src = "img/ship_3_128.png";
			break;
		case "Deltawing" :
			document.getElementById("shipImage").src = "img/ship_4_128.png";
			break;
	}
}

function updateShipData() {
	selectedShip = document.getElementById("shipSelector").value;

	switch (selectedShip) {
		case "Quicksilver" :
			document.getElementById("weaponName").innerHTML = shipsDetails.Quicksilver.weapon;
			document.getElementById("speedValue").innerHTML = shipsDetails.Quicksilver.speed;
			document.getElementById("healthValue").innerHTML = shipsDetails.Quicksilver.hp;
			document.getElementById("maneuverabilityValue").innerHTML = shipsDetails.Quicksilver.maneuverability;
			document.getElementById("cargoCapacity").innerHTML = shipsDetails.Quicksilver.cargoCapacity;
			break;
		case "Mercury" :
			document.getElementById("weaponName").innerHTML = shipsDetails.Mercury.weapon;
			document.getElementById("speedValue").innerHTML = shipsDetails.Mercury.speed;
			document.getElementById("healthValue").innerHTML = shipsDetails.Mercury.hp;
			document.getElementById("maneuverabilityValue").innerHTML = shipsDetails.Mercury.maneuverability;
			document.getElementById("cargoCapacity").innerHTML = shipsDetails.Mercury.cargoCapacity;
			break;
		case "Interceptor" :
			document.getElementById("weaponName").innerHTML = shipsDetails.Interceptor.weapon;
			document.getElementById("speedValue").innerHTML = shipsDetails.Interceptor.speed;
			document.getElementById("healthValue").innerHTML = shipsDetails.Interceptor.hp;
			document.getElementById("maneuverabilityValue").innerHTML = shipsDetails.Interceptor.maneuverability;
			document.getElementById("cargoCapacity").innerHTML = shipsDetails.Interceptor.cargoCapacity;
			break;
		case "Deltawing" :
			document.getElementById("weaponName").innerHTML = shipsDetails.Deltawing.weapon;
			document.getElementById("speedValue").innerHTML = shipsDetails.Deltawing.speed;
			document.getElementById("healthValue").innerHTML = shipsDetails.Deltawing.hp;
			document.getElementById("maneuverabilityValue").innerHTML = shipsDetails.Deltawing.maneuverability;
			document.getElementById("cargoCapacity").innerHTML = shipsDetails.Deltawing.cargoCapacity;
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