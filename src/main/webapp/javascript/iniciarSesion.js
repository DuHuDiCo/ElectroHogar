/* global resp,respSesion, Swal */

function iniciarSesion() {

    var datos = {};

    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;




    $.ajax({
        method: "POST",
        url: "ServletControlador?accion=iniciarSesion",
        data: datos,
        dataType: 'JSON'
    }).done(function (data) {

        var json = JSON.stringify(data);

        if (json !== null) {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Inicio Exitoso',
                showConfirmButton: false,
                timer: 6500

            });
            window.location.replace("inicio.html");
        }










        // imprimimos la respuesta
    }).fail(function () {

        window.location.href = "index.html";
    }).always(function () {

    });





}


function cerrarSesion() {
    $.ajax({
        url: "ServletControlador?accion=cerrarSesion"

    }).done(function (data) {

        var resp = data;


        Swal.fire({
            title: 'Estas Seguro?',
            text: "No podras revertir esto.!",
            icon: 'Advertencia',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si, Cerrar Sesion!'
        }).then((result) => {
            if (result.isConfirmed) {

                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Sesion Cerrada',
                    showConfirmButton: false,
                    timer: 4000
                });
                window.location.replace("login.html");
            }
        });

        // imprimimos la respuesta
    }).fail(function () {

    }).always(function () {


    });
}




function obtenerSesion() {



    $.ajax({
        url: "ServletControlador?accion=sesion"

    }).done(function (data) {

        var respSesion = data;


        if (respSesion === "null") {

            window.location.replace("login.html");

        }



        // imprimimos la respuesta
    }).fail(function () {

        window.location.reload();
    }).always(function () {


    });



    // simulamos tiempo de carga

}









