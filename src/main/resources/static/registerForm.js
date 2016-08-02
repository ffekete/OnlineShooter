var stompClient = null;

function connect() {
	var socket = new SockJS('/registerPlayer');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/playerRegistered', responseArrivedForRegisterPlayer);
		registerPlayerWithName();
	});
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
		
		document.getElementById("response").innerHTML = "Redirecting to game area, please wait..." + id;
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
	
	stompClient.send("/app/registerPlayer", {}, JSON.stringify(data));
}

function showStatus(message) {
	var response = document.getElementById('response');
	var p = document.createElement('p');
	p.style.wordWrap = 'break-word';
	p.appendChild(document
			.createTextNode("Player successfully registered with name: "
					+ message));
	response.appendChild(p);
}