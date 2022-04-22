/* global resp,respSesion */

function iniciarSesion() {

    var datos = {};

    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;

    alert(datos);


    $.ajax({
        method: "POST",
        url: "ServletControlador?accion=iniciarSesion",
        data: datos,
        dataType: 'JSON'
    }).done(function (data) {

        var json = JSON.stringify(data);
        alert(json);



        window.location.href = "home.html";





        // imprimimos la respuesta
    }).fail(function () {

        window.location.href = "index.html";
    }).always(function () {
        alert("Siempre se ejecuta");
    });





}


function cerrarSesion() {
    $.ajax({
        url: "ServletControlador?accion=cerrarSesion"

    }).done(function (data) {

        var resp = data;
        alert(resp);




        window.location.href = "index.html";






        // imprimimos la respuesta
    }).fail(function () {

        window.location.href = "home.html";
    }).always(function () {
        alert("Siempre se ejecuta");

    });
}




function obtenersesion() {


    $.ajax({
        url: "ServletControlador?accion=sesion"

    }).done(function (data) {

        var respSesion = data;


        if (respSesion !== "null") {

            window.location.replace("home.html");
            
            
        } else {

            window.location.replace("index.html");
        }


        // imprimimos la respuesta
    }).fail(function () {

        window.location.reload();
    }).always(function () {


    });



    // simulamos tiempo de carga

}




