/* global Swal */

function registrarUsuario() {
    alert("entro a registrar usuario js");
    var datos = {};

    var pass = document.getElementById('password').value;
    var passx2 = document.getElementById('RepetirPassword').value;


    datos.nombre = document.getElementById('nombreUsuario').value;
    datos.Identificacion = document.getElementById('cedulaUsuario').value;
    datos.TipoDocumento = document.querySelector('input[name="tipdoc"]:checked').value;
    datos.Email = document.getElementById('email').value;
    datos.telefono = document.getElementById('telefono').value;
    datos.Rol = document.getElementById('sltRol').value;
    datos.Sede = document.getElementById('sltSede').value;
    datos.password = hex_sha1(pass);
    datos.RepetirPassword = hex_sha1(passx2);

    if (datos.password !== datos.RepetirPassword) {
        Swal.fire({
            icon: 'error',
            title: 'Error al Iniciar Sesion',
            text: 'Las Contraseñas no Coinciden',
            footer: '<a href="">Why do I have this issue?</a>'
        });
    } else {
        $.ajax({
            method: "POST",
            url: "ServletUsuarios?accion=registrarUsuario",
            data: datos,
            dataType: 'JSON'
        }).done(function (data) {
            var respues = data;
            alert(respues);

            if (respues > 0) {
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Usuario Creado Con Exito',
                    showConfirmButton: false,
                    timer: 2500
                });

            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Error al Iniciar Sesion',
                    text: 'Usuario o Contreseña Incorrectos',
                    footer: '<a href="">Why do I have this issue?</a>'
                });
            }
            
            window.location.reload();
            // imprimimos la respuesta
        }).fail(function () {
            Swal.fire({
                    icon: 'error',
                    title: 'Error al Iniciar Sesion',
                    text: 'Error Inesperado, Intente Nuevamente o Reporte el Error',
                    footer: '<a href="">Why do I have this issue?</a>'
                });
            
        }).always(function () {

        });
    }
}

function cargarPagUsuarios() {
    alert("carga carga");

    cargarRoles();
    cargarSedes('sltSede');


}

function cargarRoles() {

    alert("carga roles");




    event.preventDefault();

    var admin = "Administrador";

    var Sadmin = "Super Administrador";

    $.ajax({
        method: "GET",
        url: "ServletRol?accion=listarRol"

    }).done(function (data) {
        var datos = JSON.stringify(data);
        var json = JSON.parse(datos);


        $.each(json, function (key, value) {
            if (value.nombre_rol !== admin) {
                if (value.nombre_rol !== Sadmin) {
                    $("#sltRol").append('<option value="' + value.id_rol + '" >' + value.nombre_rol + '</option>');
                }

            }




        });

    }).fail(function () {
        window.location.replace("login.html");
    }).always(function () {
    });


}

function cargarSedes(id) {
    alert("carga sedes");

    event.preventDefault();
    $.ajax({
        method: "GET",
        url: "ServletSede?accion=listarSede"

    }).done(function (data) {
        var datos = JSON.stringify(data);
        var json = JSON.parse(datos);


        $.each(json, function (key, value) {
            $("#" + id).append('<option value="' + value.idSede + '" >' + value.nombre_sede + '</option>');
        });

    }).fail(function () {
        window.location.replace("login.html");
    }).always(function () {
    });


}


function obtenerRol() {
    alert("carga");


    var rol = $.ajax({
        method: "GET",
        url: "ServletRol?accion=obtenerRol",
        async: false
    });

    return rol.responseText;
}