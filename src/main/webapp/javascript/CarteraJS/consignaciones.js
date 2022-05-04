
/* global Swal */

$(function () {
    $('.input-file__input').on('change', function () {
        if ($(this)[0].files[0]) {
            $(this).prev().text($(this)[0].files[0].name);
        }
    });
});

const $form = document.querySelector('#formConsignacion');

$form.addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);



    $.ajax({
        method: "POST",
        url: "ServletControladorCartera?accion=guardarConsignacion",
        data: formData,
        processData: false,
        contentType: false

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
});





function listarConsignaciones() {


    $.ajax({
        method: "GET",
        url: "ServletControladorConsignaciones?accion=listarConsignaciones"

    }).done(function (data) {
        var datos = JSON.stringify(data);
        var json = JSON.parse(datos);
        var html = "";

        var contador = 1;

        for (var con of json) {



            var estadoHtml = '<tr> <td>' + contador + '</td><td>' + con.num_recibo + '</td><td>' + con.fecha_pago + '</td><td>' + con.fecha_creacion + '</td><td>' + con.valor + '</td><td>' + con.nombre_estado + '</td><td>' + con.nombre_plataforma + '</td></tr>';
            html += estadoHtml;
            contador = contador + 1;


        }
        console.log(json);

        document.querySelector('#dataTable tbody').outerHTML = html;




    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });

}





function cargarDatos() {




    event.preventDefault();

    $.ajax({
        method: "GET",
        url: "ServletControladorCartera?accion=llenarBanco"

    }).done(function (data) {

        var datos = JSON.stringify(data);
        var json = JSON.parse(datos);
        var html = "";

        $.each(json, function (key, value) {
            $("#sltBancoCartera").append('<option value="' + value.idPlataforma + '" >' + value.nombre_plataforma + '--' + value.tipo_pago + '</option>');
        });

        listarConsignaciones();
        cargarEstados();



    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });


}

function cargarEstados() {
    $.ajax({
        method: "GET",
        url: "ServletControladorEstados?accion=cargarEstados"

    }).done(function (data) {
        var datos = JSON.stringify(data);
        var json = JSON.parse(datos);
        var html = "";

        $.each(json, function (key, value) {
            $("#sltEstadoConsignacion").append('<option value="' + value.nombre_estado + '" > ' + value.nombre_estado + '</option>');
        });




    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });

}

var select = document.getElementById('sltEstadoConsignacion');

select.addEventListener('change', (event) => {
    event.preventDefault();
    var valor = document.getElementById('sltEstadoConsignacion').value;
    alert(valor);

   


    $.ajax({
        method: "GET",
        url: "ServletControladorConsignaciones?accion=listarConsignacionesByEstado&estado=" + valor

    }).done(function (data) {
        var datos = JSON.stringify(data);
        var json = JSON.parse(datos);
        var html = "";
        var estadoHtml = "";
        alert(json);

        var contador = 1;

        for (var con of json) {
            alert("entro");
            estadoHtml = '<tr> <td>' + contador + '</td><td>' + con.num_recibo + '</td><td>' + con.fecha_pago + '</td><td>' + con.fecha_creacion + '</td><td>' + con.valor + '</td><td>' + con.nombre_estado + '</td><td>' + con.nombre_plataforma + '</td></tr>';
            html += estadoHtml;
            contador = contador + 1;


        }

        console.log(json);

        document.querySelector('#dataTable tbody').outerHTML = html;
        window.location.href = "consignacionesCartera.html";







    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });

});











