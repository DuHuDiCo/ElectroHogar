
/* global Swal */

$(function () {
    $('.input-file__input').on('change', function () {
        if ($(this)[0].files[0]) {
            $(this).prev().text($(this)[0].files[0].name);
        }
    });
});



function guardarConsignacion() {
    alert("hola");

    var datos = {};
    
    datos.num_recibo = document.getElementById('txtNumRecibo').value;
    datos.valor = document.getElementById('txtValor').value;
    datos.fecha_pago = document.getElementById('dateCreacion').value;
    datos.plataforma = document.getElementById('sltBancoCartera').value;
    datos.file = document.getElementById('file').files;
    
    $.ajax({
        method: "POST",
        url: "ServletControladorCartera?accion=guardarConsignacion",
        data: datos,
        contentType: 'Json'
        
    }).done(function (data) {

        var datos = data;
        alert(datos);


        if (datos !== null) {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Inicio Exitoso',
                showConfirmButton: false,
                timer: 6500

            });
            alert(datos.nombre_rol);
            roles(datos.nombre_rol);
            


        }

  
        // imprimimos la respuesta
    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });



}

//function cargarDatosPago() {
//
//    event.preventDefault();
//
//    $.ajax({
//        method: "GET",
//        url: "ServletControladorAdministrador?accion=llenarTipoPago"
//
//    }).done(function (data) {
//        var datos = JSON.stringify(data);
//        var json = JSON.parse(datos);
//        var html = "";
//
//        $.each(json, function (key, value) {
//            $("#sltTipoPagoCartera").append('<option value="' + value.idTipoPago + '" >' + value.tipo_pago + '</option>');
//        });
//        
//
//
//
//    }).fail(function () {
//
//        window.location.replace("login.html");
//    }).always(function () {
//
//    });
//
//
//}


function cargarDatosBanco() {

    event.preventDefault();

    $.ajax({
        method: "GET",
        url: "ServletControladorCartera?accion=llenarBanco"

    }).done(function (data) {
        var datos = JSON.stringify(data);
        var json = JSON.parse(datos);
        var html = "";

        $.each(json, function (key, value) {
            $("#sltBancoCartera").append('<option value="' + value.idPlataforma + '" >' + value.nombre_plataforma+'--'+value.tipo_pago + '</option>');
        });
        



    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });


}









