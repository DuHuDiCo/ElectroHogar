/* global Swal */




$(function () {
    $('.input-file__input').on('change', function () {
        if ($(this)[0].files[0]) {
            $(this).prev().text($(this)[0].files[0].name);
        }
    });
});

const $form = document.querySelector('#formConsignacion');



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


        cargarEstados('sltEstadoConsignacion');
        cargarConsignacionesGeneral();




    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });


}

function cargarBancos(id, dato) {
    $.ajax({
        method: "GET",
        url: "ServletControladorCartera?accion=llenarBanco"

    }).done(function (data) {

        var datos = JSON.stringify(data);
        var json = JSON.parse(datos);


        $.each(json, function (key, value) {
            if (value.nombre_plataforma === dato) {
                $("#" + id).append('<option value="' + value.idPlataforma + '" selected>' + value.nombre_plataforma + '--' + value.tipo_pago + '</option>');
            } else {
                $("#" + id).append('<option value="' + value.idPlataforma + '" >' + value.nombre_plataforma + '--' + value.tipo_pago + '</option>');
            }




        });



    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });

}


function cargarEstados(idSelect) {

    $.ajax({
        method: "GET",
        url: "ServletControladorEstados?accion=cargarEstados"

    }).done(function (data) {
        var datos = JSON.stringify(data);
        var json = JSON.parse(datos);
        var html = "";



        $.each(json, function (key, value) {
            if (value.idEstado === 1) {
                $("#" + idSelect).append('<option value="' + value.nombre_estado + '" selected> ' + value.nombre_estado + '</option>');
            } else {
                $("#" + idSelect).append('<option value="' + value.nombre_estado + '" > ' + value.nombre_estado + '</option>');
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

        alert(json);
        $("#dataTable tbody").empty();

        var contador = 1;

        $.each(json, function (key, value) {
            if (value.nombre_estado === "Devuelta") {
                var observa = '<a href="#" id="btn_observa" onclick="abrirModalObservacionesCaja(' + value.idConsignacion + ');" class="btn btn-info btn-sm"><i class="fas fa-eye"></i></a>';
                var accion = "<td><a href='#' class='btn btn-primary btn-sm' onclick='editarConsignacion(" + value.idConsignacion + ")'><i class='fas fa-pen'></i></a>" + observa + "</td>";
                $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.num_recibo + '</td><td>' + value.nombre_titular + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_sede + '</td><td>' + value.nombre_plataforma + '</td>'+accion+'</tr>');
                contador = contador + 1;
            } else {
                if (value.nombre_estado === "Pendiente") {
                    var accion = "<td><a href='#' class='btn btn-primary btn-sm' onclick='editarConsignacion(" + value.idConsignacion + ")'><i class='fas fa-pen'></i></a></td>";
                    $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.num_recibo + '</td><td>' + value.nombre_titular + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_sede + '</td><td>' + value.nombre_plataforma + '</td>'+accion+'</tr>');
                    contador = contador + 1;
                } else {
                    var observa = '<a href="#" id="btn_observa" onclick="abrirModalObservacionesCaja(' + value.idConsignacion + ');" class="btn btn-info btn-sm"><i class="fas fa-eye"></i></a>';
                    $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.num_recibo + '</td><td>' + value.nombre_titular + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_sede + '</td><td>' + value.nombre_plataforma + '</td><td>' + observa + '</td></tr>');
                    contador = contador + 1;
                }
            }


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

            var accion = "<td><a href='#' class='btn btn-primary btn-sm' onclick='editarConsignacion(" + value.idConsignacion + ")'><i class='fas fa-pen'></i></a></td>";

            $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.num_recibo + '</td><td>' + value.nombre_titular + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_sede + '</td><td>' + value.nombre_plataforma + '</td>' + accion + '</tr>');
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

        if (json.length > 0) {
            var contador = 1;

            $.each(json, function (key, value) {

                $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.num_recibo + '</td><td>' + value.nombre_titular + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_sede + '</td><td>' + value.nombre_plataforma + '</td></tr>');
                contador = contador + 1;
            });




            console.log(json);

        } else {
            Swal.fire({
                icon: 'error',
                title: 'Error al Consultar la Cedula',
                text: 'No existe una Consignacion Relacionada a la Cedula Ingresada',
                footer: '<a href="">Why do I have this issue?</a>'

            });

            cargarConsignacionesGeneral();
        }




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
            document.getElementById('nuevoCliente').style.display = "none";

            var contador = 1;

            $.each(json, function (key, value) {

                $("#tblCliente tbody").append('<tr> <td><input type="checkbox" value=' + value.idObligacion + ' id="obligacion" name="obligacion" required></td><td>' + value.nombre_titular + '</td><td>' + value.saldo_capital + '</td><td>' + value.fecha_obligacion + '</td><td>' + value.nombre_sede + '</td></tr>');
                contador = contador + 1;
            });

            document.getElementById('tblCliente').style.display = "block";




            console.log(json);

        } else {
            document.getElementById('tblCliente').style.display = "none";
            document.getElementById('nuevoCliente').style.display = "block";
            document.getElementById('sltSedeCon').style.display = "block";
            document.getElementById('cedulaCliente').style.display = "block";
            cargarSedes('sltSedeCon');
            Swal.fire({
                icon: 'error',
                title: 'El Cliente no Existe',
                text: 'No se encontro un cliente relacionado con el documento ingresado'
            });
        }




    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });



}

function editarConsignacion(idConsignacion) {
    alert("entro");
    $.ajax({
        method: "GET",
        url: "ServletControladorConsignaciones?accion=editarConsignacion&idConsignacion=" + idConsignacion


    }).done(function (data) {

        var datos = JSON.stringify(data);
        var json = JSON.parse(datos);




        if (Object.keys(json).length > 0) {
            $('#modalEditarConsignacion').modal('show');

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







