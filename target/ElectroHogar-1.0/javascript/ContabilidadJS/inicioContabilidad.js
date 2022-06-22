function conectarWebSocket(){
    alert("entro a webSocket");
    var uriWs = "ws://localhost:8080/ElectroHogar/socket";
    var miWebsocket = new WebSocket(uriWs);
    console.log(miWebsocket);
    
    miWebsocket.onopen = function (evento){
      console.log("abierto");
    };
    
    miWebsocket.onmessage=function (evento){
      console.log(evento.data);  
    };
}
