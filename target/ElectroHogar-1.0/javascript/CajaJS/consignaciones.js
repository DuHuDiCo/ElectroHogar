/* global Swal */

function cargarDatosContabilidad() {
    alert("entro");
    cargarEstados();
    listarConsignacionesCaja();
}

function listarConsignacionesCaja() {
    alert("entro");
    $.ajax({
        method: "GET",
        url: "ServletControladorConsignaciones?accion=listarConsignacionesCaja"

    }).done(function (data) {
        var datos = JSON.stringify(data);
        var json = JSON.parse(datos);

        alert(json);
        $("#dataTable tbody").empty();

        var contador = 1;

        $.each(json, function (key, value) {
            var accion = '<td><a href="#" onclick="aplicarConsignacion('+value.idConsignacion+');" class="btn btn-primary btn-sm"><i class="fas fa-check"></i>&nbsp;Aplicar</a></td>';
            $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.nombre_titular + '</td><td>' + value.num_recibo + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_plataforma + '</td>' + accion + '</tr>');
            contador = contador + 1;
        });




        console.log(json);


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
            if (valor !== 'Comprobado') {
                var accion = '<td><a href="#" onclick="" class="btn btn-primary btn-sm disabled" ><i class="fas fa-check"></i>&nbsp;Comprobar</a></td>';
                $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.nombre_titular + '</td><td>' + value.num_recibo + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_plataforma + '</td>' + accion + '</tr>');
                contador = contador + 1;
            } else {
                var accion = '<td><a href="#" onclick="aplicarConsignacion('+value.idConsignacion+')" class="btn btn-primary btn-sm"><i class="fas fa-check"></i>&nbsp;Aplicar</a></td>';
                $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.nombre_titular + '</td><td>' + value.num_recibo + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_plataforma + '</td>' + accion + '</tr>');
                contador = contador + 1;
            }

        });




        console.log(json);


    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });

});


function aplicarConsignacion(id_consignacion){
    alert("entro");
    var datos = {};
    datos.idConsignacion = id_consignacion;
    
    var valor = "Aplicado";
    
     $.ajax({
        method: "POST",
        url: "ServletControladorConsignaciones?accion=actualizarConsignacion&estado="+valor,
        data: datos,
        dataType: 'JSON'
    }).done(function (data) {

        var json = JSON.stringify(data);
        var datos = JSON.parse(json);



        if (datos.nombre_rol !== "null") {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Consignacion Comprobada Correctamente',
                showConfirmButton: false,
                timer: 6500
            });
            

            
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Error al Comprobar la Consignacion',
                text: 'Error Desconocido Reporte el Error',
                footer: '<a href="">Why do I have this issue?</a>'
            });
        }
        
        setTimeout(recargarPagina, 6500);

        // imprimimos la respuesta
    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });
}


function recargarPagina(){
    window.location.reload();
}

function consignacionesByCedulaCaja() {
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
                if (value.nombre_estado !== 'Comprobado') {
                    var accion = '<td><a href="#" onclick="" class="btn btn-primary btn-sm disabled" ><i class="fas fa-ban"></i></a></td>';
                    $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.nombre_titular + '</td><td>' + value.num_recibo + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_plataforma + '</td>' + accion + '</tr>');
                    contador = contador + 1;
                } else {
                    var accion = '<td><a href="#" onclick="" class="btn btn-primary btn-sm"><i class="fas fa-check"></i><br/>Aplicar</a></td>';
                    $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.nombre_titular + '</td><td>' + value.num_recibo + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_plataforma + '</td>' + accion + '</tr>');
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