function registrarUsuario() {
    alert("entro a registrar usuario js");
    var datos = {};

    datos.nombre = document.getElementById('nombre').value;
    datos.Identificacion = document.getElementById('idcli').value;
    datos.TipoDocumento = document.querySelector('input[name="tipdoc"]:checked').value;
    datos.Email = document.getElementById('email').value;
    datos.telefono = document.getElementById('telefono').value;
    datos.Rol = document.getElementById('sltRol').value;
    datos.Sede = document.getElementById('sltSede').value;
    datos.password = document.getElementById('password').value;
    datos.RepetirPassword = document.getElementById('RepetirPassword').value;

    if (datos.password !== datos.RepetirPassword) {
        alert("contraseñas no coinciden");
    } else {
        $.ajax({
            method: "POST",
            url: "ServletUsuarios?accion=registrarUsuario",
            data: datos,
            dataType: 'JSON'
        }).done(function (data) {
            alert(data);
            var respues = data;
            alert(respues);

            if (respues !== null) {
//            Swal.fire({
//                position: 'top-end',
//                icon: 'success',
//                title: 'claves iguales',
//                showConfirmButton: false,
//                timer: 6500
//            });
                alert(respues);
            }

            // imprimimos la respuesta
        }).fail(function () {

            alert("hay un error");
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
            if(value.nombre_rol !== admin ){
                if(value.nombre_rol !== Sadmin){
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