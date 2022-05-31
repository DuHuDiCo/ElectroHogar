function listarObligaciones(){
     $.ajax({
        method: "GET",
        url: "ServletControladorObligaciones?accion=listarObligaciones"

    }).done(function (data) {
        var datos = JSON.stringify(data);
        var json = JSON.parse(datos);
       
        alert(json);
        $("#dataTable tbody").empty();

        var contador = 1;

        $.each(json, function (key, value) {

            $("#dataTable").append('<tr> <td>' + contador + '</td><td>' + value.num_recibo + '</td><td>' + value.nombre_titular + '</td><td>' + value.fecha_pago + '</td><td>' + value.fecha_creacion + '</td><td>' + value.valor + '</td><td>' + value.nombre_estado + '</td><td>' + value.nombre_sede + '</td><td>' + value.nombre_plataforma + '</td></tr>');
            contador = contador + 1;
        });




        console.log(json);

    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });

}