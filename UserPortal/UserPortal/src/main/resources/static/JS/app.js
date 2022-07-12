var ws;
var socket;
var sessionId = "";

function connect() {
    socket = new SockJS('/secured/room');
    ws = Stomp.over(socket);

    ws.connect({}, function(frame) {
        var url = ws.ws._transport.url;
        url = url.replace(
            "ws://localhost:8080/secured/room/",  "");
        url = url.replace("/websocket", "");
        url = url.replace(/^[0-9]+\//, "");
        console.log("Your current session is: " + url);
        sessionId = url;

        ws.subscribe('/secured/user/queue/specific-user'+'-user'+sessionId, function(message) {
            console.log(message);
        });
    }, function(error) {
        alert("STOMP error " + error);
    });
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendData() {
    var data = JSON.stringify({
        'currencyName' : $('#CurrencyType')[0].value,
        'startTimestampString' : $('#StartDate')[0].value,
        'stopTimestampString' : $('#StopDate')[0].value
    })
    //console.log(data);
    ws.send("/spring-security-mvc-socket/secured/room", {}, data);
}

window.onload = function () {
    connect();
    $('#send')[0].onclick = sendData;
}