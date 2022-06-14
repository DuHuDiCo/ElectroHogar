/* global Swal */

function editarConsignacion(idConsignacion) {
    alert("entro");
    $.ajax({
        method: "GET",
        url: "ServletControladorConsignaciones?accion=editarConsignacion&idConsignacion=" + idConsignacion


    }).done(function (data) {

        var datos = JSON.stringify(data);
        var json = JSON.parse(datos);




        if (Object.keys(json).length > 0) {
            $('#modalEditarConsignacionAdmin').modal('show');

            document.getElementById('txtIdCon').value = json.idConsignacion;
            document.getElementById('txtNumReciboModal').value = json.num_recibo;
            document.getElementById('txtValorModal').value = json.valor;
            document.getElementById('dateCreacionModal').value = json.fecha_pago;
            cargarBancos('sltBancoCarteraModal', json.nombre_plataforma);
            document.getElementById('dateCreacionModal').value = json.fecha_pago;





        } else {
            Swal.fire({
                icon: 'error',
                title: 'Error al Editar la consignacion',
                text: 'No se logro Editar la consignacion, por favor reporte el error',
                footer: '<a href="">Why do I have this issue?</a>'
            });
        }


    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });
}

function abrirModalObservaciones() {
    var recibo = document.getElementById('txtNumRecibo').value;
    var valor = document.getElementById('txtValor').value;
    var fecha = document.getElementById('dateCreacion').value;
    var sede = document.getElementById('sltBancoCartera').value;
    var file = document.getElementById('file').files;
    if (recibo === "" || valor === "" || fecha === "" || sede === "" || file.length === 0) {
        Swal.fire({
            icon: 'error',
            title: 'Error al guardar la consignacion',
            text: 'Alguno de los Campos estan Vacios',
            footer: '<a href="">Why do I have this issue?</a>'
        });
        recibo.focus();
    } else {
        $('#modalConsignacion').modal('show');
    }

}

function crearObservacion() {
    var obser = document.getElementById('observacionGuardarConsig').value;
    if (obser === "") {
        Swal.fire({
            icon: 'error',
            title: 'Error al guardar la consignacion',
            text: 'Campo de Observacion Vacio, Por Favor Ingrese una Observacion',
            footer: '<a href="">Why do I have this issue?</a>'
        });
    } else {
        guardarConsig();

        var datos = {};

        datos.observacion = obser;
        alert(datos.observacion);

        $.ajax({
            method: "POST",
            url: "ServletObservaciones?accion=guardarObservacion",
            data: datos,
            dataType: 'JSON'

        }).done(function (data) {

            var datos = data;
            alert(datos);

            window.location.reload();

            if (datos !== 0) {
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Consignacion Guardada Exitosamente',
                    showConfirmButton: false,
                    timer: 2000


                });
                alert(datos.nombre_rol);
                roles(datos.nombre_rol);




            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Error al guardar la consignacion',
                    text: 'No se logro guardar la consignacion, por favor revise bien la informacion o reporte el error',
                    footer: '<a href="">Why do I have this issue?</a>'
                });
            }

            window.location.reload();


            // imprimimos la respuesta
        }).fail(function () {

            window.location.replace("login.html");
        }).always(function () {

        });

    }
}


function noCrearObservacion() {
    alert("entro");
    guardarConsig();
}

function guardarConsig() {

    var form = document.getElementById('formConsignacion');
    var formData = new FormData(form);


    $.ajax({
        method: "POST",
        url: "ServletControladorCartera?accion=guardarConsignacion",
        data: formData,
        processData: false,
        contentType: false

    }).done(function (data) {

        var datos = data;
        alert(datos);

        window.location.reload();

        if (datos !== 0) {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Consignacion Guardada Exitosamente',
                showConfirmButton: false,
                timer: 2000


            });
            alert(datos.nombre_rol);
            roles(datos.nombre_rol);




        } else {
            Swal.fire({
                icon: 'error',
                title: 'Error al guardar la consignacion',
                text: 'No se logro guardar la consignacion, por favor revise bien la informacion o reporte el error',
                footer: '<a href="">Why do I have this issue?</a>'
            });
        }

        window.location.reload();


        // imprimimos la respuesta
    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });

}


