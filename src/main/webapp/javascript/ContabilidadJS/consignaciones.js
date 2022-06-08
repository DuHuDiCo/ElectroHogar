/* global Swal */

function cargarDatosContabilidad() {
    alert("entro");
    cargarEstados();
    listarConsignacionesContabilidad();
}

function listarConsignacionesContabilidad() {
    var valor = "Pendiente";
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
            var devolver = '<a href="#" id="btn_devolver" onclick="abrirModal(' + value.idConsignacion + ');" class="btn btn-warning btn-sm"><i class="fas fa-backward"></i></a>';
            //var modalDevolver = '<button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#staticBackdrop"><i class="fas fa-backward"></i></button>';
            var obser = '<a href="#" id="btn_observa" onclick="observacionesConsignacion(' + value.idConsignacion + ');" class="btn btn-info btn-sm"><i class="fas fa-eye"></i></a>';
            var comprobar = '<td><a href="#" id="btn_comprobar" onclick="comprobarConsignacion(' + value.idConsignacion + ');" class="btn btn-primary btn-sm"><i class="fas fa-check"></i></a>' + devolver + obser + '</td>';

            $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.nombre_titular + '</td><td>' + value.num_recibo + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_plataforma + '</td>' + comprobar + '</tr>');
            contador = contador + 1;
        });




        console.log(json);


    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });
}



function abrirModal(idConsignacion) {

    alert("entro");


    $('#staticBackdrop').modal('show');

    var enviar = document.getElementById('enviarObservacion').addEventListener("click", function () {
        var observa = document.getElementById('observacionDevolucion').value;
        if (observa === "") {
            Swal.fire({
                icon: 'error',
                title: 'El Campo de Observacion esta vacio',
                text: 'Ingrese una Observacion valida',
                footer: '<a href="">Why do I have this issue?</a>'

            });
            observa.focus();
        } else {
            devolverConsignacion(observa, idConsignacion);
        }

    });





}
function devolverConsignacion(mensaje, idConsignacion) {
    alert("entro");

    var datos = {};
    datos.idConsignacion = idConsignacion;
    datos.observacion = mensaje;
    alert(mensaje);


    $.ajax({
        method: "POST",
        url: "ServletControladorConsignaciones?accion=devolverConsignaciones",
        data: datos,
        dataType: 'JSON'

    }).done(function (data) {
        var datos = data;

        if (datos > 0) {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Consignacion Devuelta Correctamente',
                showConfirmButton: false,
                timer: 2000
            });
            $('#staticBackdrop').modal('hide');
            setTimeout(recargarPagina, 2000);
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Error al Devolver la Consignacion',
                text: 'Error Desconocido Reporte el Error',
                footer: '<a href="">Why do I have this issue?</a>'
            });
            $('#staticBackdrop').modal('hide');
            setTimeout(recargarPagina, 2000);
            
        }




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

        alert(json);
        $("#dataTable tbody").empty();

        var contador = 1;

        $.each(json, function (key, value) {
            if (valor !== 'Pendiente') {

                var obser = '<td><a href="#" id="btn_observa" onclick="observacionesConsignacion(' + value.idConsignacion + ');" class="btn btn-info btn-sm"><i class="fas fa-eye"></i></a></td>';
                //var comprobar = '<td><a href="#" id="btn_comprobar" onclick="comprobarConsignacion(' + value.idConsignacion + ');" class="btn btn-primary btn-sm" disabled><i class="fas fa-check"></i></a>' +  obser + '</td>';
                $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.nombre_titular + '</td><td>' + value.num_recibo + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_plataforma + '</td>' + obser + '</tr>');
                contador = contador + 1;
            } else {
                var devolver = '<a href="#" id="btn_devolver" onclick="devolverConsignacion(' + value.idConsignacion + ');" class="btn btn-warning btn-sm"><i class="fas fa-backward"></i></a>';
                var obser = '<a href="#" id="btn_observa" onclick="observacionesConsignacion(' + value.idConsignacion + ');" class="btn btn-info btn-sm"><i class="fas fa-eye"></i></a>';
                var comprobar = '<td><a href="#" id="btn_comprobar" onclick="comprobarConsignacion(' + value.idConsignacion + ');" class="btn btn-primary btn-sm"><i class="fas fa-check"></i></a>' + devolver + obser + '</td>';

                $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.nombre_titular + '</td><td>' + value.num_recibo + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_plataforma + '</td>' + comprobar + '</tr>');
                contador = contador + 1;
            }

        });




        console.log(json);


    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });

});


function consignacionesByCedula() {
    var cedula = document.getElementById('txtCedula').value;

    $.ajax({
        method: "GET",
        url: "ServletControladorConsignaciones?accion=listarConsignacionesByCedula&cedula=" + cedula

    }).done(function (data) {
        var datos = JSON.stringify(data);
        var json = JSON.parse(datos);

        alert(json);
        $("#dataTable tbody").empty();

        if (json.length > 0) {
            var contador = 1;

            $.each(json, function (key, value) {
                if (value.nombre_estado !== 'Pendiente') {
                    var obser = '<a href="#" id="btn_observa" onclick="observacionesConsignacion(' + value.idConsignacion + ');" class="btn btn-info btn-sm"><i class="fas fa-eye"></i></a>';
                    var accion = '<td><a href="#" onclick=""  class="btn btn-primary btn-sm disabled" ><i class="fas fa-check"></i></a>' + obser + '</td>';
                    $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.nombre_titular + '</td><td>' + value.num_recibo + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_plataforma + '</td>' + accion + '</tr>');
                    contador = contador + 1;
                } else {
                    var devolver = '<a href="#" id="btn_devolver" onclick="devolverConsignacion(' + value.idConsignacion + ');" class="btn btn-warning btn-sm"><i class="fas fa-backward"></i></a>';
                    var obser = '<a href="#" id="btn_observa" onclick="observacionesConsignacion(' + value.idConsignacion + ');" class="btn btn-info btn-sm"><i class="fas fa-eye"></i></a>';
                    var comprobar = '<td><a href="#" id="btn_comprobar" onclick="comprobarConsignacion(' + value.idConsignacion + ');" class="btn btn-primary btn-sm"><i class="fas fa-check"></i></a>' + devolver + obser + '</td>';

                    $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.nombre_titular + '</td><td>' + value.num_recibo + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_plataforma + '</td>' + comprobar + '</tr>');
                    contador = contador + 1;
                }
            });




            console.log(json);

        } else {
            Swal.fire({
                icon: 'error',
                title: 'Error al Consultar la Cedula',
                text: 'No existe una Consignacion Relacionada a la Cedula Ingresada',
                footer: '<a href="">Why do I have this issue?</a>'

            });

            listarConsignacionesContabilidad();
        }




    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });

}

function comprobarConsignacion(id_consignacion) {
    alert("entro");
    var datos = {};
    datos.idConsignacion = id_consignacion;
    alert(datos.idConsignacion);

    $("#btn_comprobar").empty();
    document.getElementById('btn_comprobar').outerHTML = '<a href="#"  class="btn btn-primary btn-sm disabled" ><i class="fas fa-ban"></i>&nbsp;Comprobar</a></td>';

    $.ajax({
        method: "POST",
        url: "ServletControladorConsignaciones?accion=ConsignacionTemporal",
        data: datos,
        dataType: 'JSON'
    }).done(function (data) {

        var json = data;




        if (json > 0) {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Consignacion Comprobada Correctamente',
                showConfirmButton: false,
                timer: 2000
            });


        } else {
            Swal.fire({
                icon: 'error',
                title: 'Error al Comprobar la Consignacion',
                text: 'Error Desconocido Reporte el Error',
                footer: '<a href="">Why do I have this issue?</a>'
            });
        }

        var botonGroup = '<a href="#" class="btn btn-primary" onclick="guardarCambios();">Guardar Cambios</a> <a href="#" class="btn btn-danger" onclick="cancelarCambios();">Cancelar Cambios</a>';
        document.getElementById('btn_group').innerHTML = botonGroup;



        // imprimimos la respuesta
    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });


}




function cancelarCambios() {
    $.ajax({
        method: "GET",
        url: "ServletControladorConsignaciones?accion=cancelarCambios"

    }).done(function (data) {

        var json = data;




        if (json > 0) {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Consignacion Comprobada Correctamente',
                showConfirmButton: false,
                timer: 2000
            });


        } else {
            Swal.fire({
                icon: 'error',
                title: 'Error al Comprobar la Consignacion',
                text: 'Error Desconocido Reporte el Error',
                footer: '<a href="">Why do I have this issue?</a>'
            });
        }

        setTimeout(recargarPagina, 2000);



        // imprimimos la respuesta
    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });
}

function guardarCambios() {
    $.ajax({
        method: "GET",
        url: "ServletControladorConsignaciones?accion=guardarCambios"

    }).done(function (data) {

        var json = data;




        if (json > 0) {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Consignacion Comprobada Correctamente',
                showConfirmButton: false,
                timer: 2000
            });


        } else {
            Swal.fire({
                icon: 'error',
                title: 'Error al Comprobar la Consignacion',
                text: 'Error Desconocido Reporte el Error',
                footer: '<a href="">Why do I have this issue?</a>'
            });
        }

        setTimeout(recargarPagina, 2000);



        // imprimimos la respuesta
    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });
}

function recargarPagina() {
    window.location.reload();
}

