const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/channels'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
//    stompClient.subscribe('/channels', (greeting) => {
//        showGreeting(JSON.parse(greeting.body).content);
//    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

var subscribedChannel = null;
function subscribe(channel) {
this.subscribedChannel = channel;
    stompClient.subscribe('/topic/' + channel, (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    });
    console.log("subscribed to", channel);
}

function connect() {
    console.log("trying to connect");
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}


function sendName() {
    var des = this.subscribedChannel;
    console.log(des);
    stompClient.publish({
        destination: "/app/" + des,
        body: JSON.stringify({'content': $("#name").val()})
    });
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
});