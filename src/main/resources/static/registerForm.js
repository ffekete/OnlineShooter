var stompClient = null;

function setConnected(connected) {
	document.getElementById('connect').disabled = connected;
	document.getElementById('disconnect').disabled = !connected;
	document.getElementById('conversationDiv').style.visibility = connected ? 'visible'
			: 'hidden';
	document.getElementById('response').innerHTML = '';
}

function connect() {
	var socket = new SockJS('/registerPlayer');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);

		stompClient.subscribe('/playerRegistered', responseArrivedForRegisterPlayer);

	});
}

function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function responseArrivedForRegisterPlayer(playerStatus) {
	var playerData = JSON.parse(playerStatus.body); 
	if (playerData.registered) {
		//var id = JSON.parse(playerStatus.body).id;
		document.getElementById("response").innerHTML = "Redirecting to game area, please wait...";
		window.location.replace("/game_area.html");

	} else {
		document.getElementById("response").innerHTML = "Player registration error!";
	}
}

function registerPlayerWithName() {
	var name = document.getElementById('name').value;
	stompClient.send("/app/registerPlayer", {}, name);
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