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


        if (datos === 1) {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Inicio Exitoso',
                showConfirmButton: false,
                timer: 6500

            });
            alert(datos.nombre_rol);
            roles(datos.nombre_rol);



        }else{
            Swal.fire({
                icon: 'error',
                title: 'Error al guardar la consignacion',
                text: 'No se logro guardar la consignacion, por favor revise bien la informacion o reporte el error',
                footer: '<a href="">Why do I have this issue?</a>'
            });
        }


        // imprimimos la respuesta
    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });
});











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
            if (value.idPlataforma === 1) {
                $("#sltBancoCartera").append('<option value="' + value.idPlataforma + '" >' + value.nombre_plataforma + '--' + value.tipo_pago + '</option>');
            }
            $("#sltBancoCartera").append('<option value="' + value.idPlataforma + '" >' + value.nombre_plataforma + '--' + value.tipo_pago + '</option>');
        });


        cargarEstados();
        cargarConsignacionesGeneral();



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
            if (value.idEstado === 1) {
                $("#sltEstadoConsignacion").append('<option value="' + value.nombre_estado + '" selected> ' + value.nombre_estado + '</option>');
            } else {
                $("#sltEstadoConsignacion").append('<option value="' + value.nombre_estado + '" > ' + value.nombre_estado + '</option>');
            }


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
        $("#dataTable tbody").empty();

        var contador = 1;

        $.each(json, function (key, value) {

            $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.num_recibo + '</td><td>' + value.nombre_titular + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_plataforma + '</td></tr>');
            contador = contador + 1;
        });




        console.log(json);










    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });

});



function cargarConsignacionesGeneral() {



    var rol = document.getElementById('rol').value;
    alert(rol);
    var valor = "";

    if (rol === 'Caja') {
        valor = "Comprobado";
    } else {
        if (rol === 'Cartera') {
            valor = "Aplicada";
        } else {
            valor = "Pendiente";
        }
    }

    alert(valor);




    $.ajax({
        method: "GET",
        url: "ServletControladorConsignaciones?accion=listarConsignacionesByEstado&estado=" + valor

    }).done(function (data) {
        var datos = JSON.stringify(data);
        var json = JSON.parse(datos);

        alert(json);
        $("#dataTable tbody").empty();

        var contador = 1;

        $.each(json, function (key, value) {

            $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.num_recibo + '</td><td>' + value.nombre_titular + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_plataforma + '</td></tr>');
            contador = contador + 1;
        });




        console.log(json);


    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });



}

function consignacionesCedula() {

    var cedula = document.getElementById('txtCedula').value;

    $.ajax({
        method: "GET",
        url: "ServletControladorConsignaciones?accion=listarConsignacionesByCedula&cedula=" + cedula

    }).done(function (data) {
        var datos = JSON.stringify(data);
        var json = JSON.parse(datos);

        alert(json);
        $("#dataTable tbody").empty();

        var contador = 1;

        $.each(json, function (key, value) {

            $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.num_recibo + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_plataforma + '</td></tr>');
            contador = contador + 1;
        });




        console.log(json);


    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });


}


function traerCliente() {

    var cedula = document.getElementById('txtCliente').value;


    $.ajax({
        method: "GET",
        url: "ServletControladorConsignaciones?accion=listarClienteByCedula&cedula=" + cedula

    }).done(function (data) {
        var datos = JSON.stringify(data);
        var json = JSON.parse(datos);

        alert(json);
        $("#tblCliente tbody").empty();


        if (json.length > 0) {


            var contador = 1;

            $.each(json, function (key, value) {

                $("#tblCliente tbody").append('<tr> <td><input type="checkbox" value=' + value.idObligacion + ' id="obligacion" name="obligacion" required></td><td>' + value.nombre_titular + '</td><td>' + value.saldo_capital + '</td><td>' + value.fecha_obligacion + '</td><td>' + value.nombre_sede + '</td></tr>');
                contador = contador + 1;
            });

            document.getElementById('tblCliente').style.display = "block";




            console.log(json);

        } else {
            document.getElementById('tblCliente').style.display = "none";
            Swal.fire({
                icon: 'error',
                title: 'El Cliente no Existe',
                text: 'No se encontro un cliente relacionado con el documento ingresado',
                footer: '<a href="">Why do I have this issue?</a>'
            });
        }




    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });



}






