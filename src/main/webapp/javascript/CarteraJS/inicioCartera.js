




function consignacionesMesCartera(){
    validarSession();
    obtenerNombreUsuario();
    consignacionesDiaCartera();
     $.ajax({
        method: "GET",
        url: "ServletControladorConsignaciones2?accion=consignacionesMesCartera"
       

    }).done(function (data) {

        var datos = data;
        
        
        document.getElementById("conMesCartera").innerHTML = datos;
      


     
    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });
}


function consignacionesDiaCartera(){
    
    obtenerNombreUsuario(); 
     $.ajax({
        method: "GET",
        url: "ServletControladorConsignaciones2?accion=consignacionesDiaCartera"
       

    }).done(function (data) {

        var datos = data;
        
        
        document.getElementById("conDiaCartera").innerHTML = datos;
      


     
    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });
}

