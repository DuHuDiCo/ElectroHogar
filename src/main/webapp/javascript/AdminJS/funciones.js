function obtenerNombreUsuario() {
    $.ajax({
        method: "POST",
        url: "ServletControlador?accion=obtenerNombreUsuario"
       

    }).done(function (data) {

        var datos = data;
        
        
        document.getElementById("username").innerHTML = datos;
      


     
    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });

}