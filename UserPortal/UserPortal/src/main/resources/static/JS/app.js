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
            let payload = JSON.parse(message.body);

            let resultDiv = $('#result')[0];
            resultDiv.innerHTML = "";
            let resultTable = document.createElement("table");
            resultDiv.append(resultTable);

            let resultNameRos = document.createElement("tr");
            resultTable.append(resultNameRos);

            let resultRate = document.createElement("th");
            resultNameRos.append(resultRate);
            resultRate.innerHTML = "Rate";

            let resultDate = document.createElement("th");
            resultNameRos.append(resultDate);
            resultDate.innerHTML = "Date";

            for(let i = 0; i < payload.length; i++) {
                let row = document.createElement("tr");

                let rate = document.createElement("td");
                row.append(rate);
                rate.innerHTML = payload[i].price;

                let date = document.createElement("td");
                row.append(date);
                date.innerHTML = payload[i].tradeTime;

                resultTable.append(row);
            }
            console.log(message);
        });
    }, function(error) {
        alert("STOMP error " + error);
    });
}

function disconnect() {
    if (ws != null) {
        ws.disconnect();
    }
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

var logout = function() {
    disconnect();
    $.post("/logout", function() {
        $('#user')[0].innerHTML = '';
        window.location.href = '../';
    })
    return true;
}

window.onload = function () {
    connect();
    $('#send')[0].onclick = sendData;

    console.log(window);

    $.ajaxSetup({
        beforeSend : function(xhr, settings) {
            if (settings.type == 'POST' || settings.type == 'PUT'
                || settings.type == 'DELETE') {
                if (!(/^http:.*/.test(settings.url) || /^https:.*/
                    .test(settings.url))) {
                    // Only send the token to relative URLs i.e. locally.
                    xhr.setRequestHeader("X-XSRF-TOKEN",
                        Cookies.get('XSRF-TOKEN'));
                }
            }
        }
    });

    $.get("/user", function(data) {
        $('#user')[0].innerHTML = data.name;
    });

    $('#logout')[0].onclick = logout;
}