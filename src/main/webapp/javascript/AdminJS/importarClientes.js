
/* global Swal */

const $formC = document.querySelector('#formObligaciones');

$formC.addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);



    $.ajax({
        method: "POST",
        url: "ServletControladorFiles?accion=guardartxt",
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
          
        }


        // imprimimos la respuesta
    }).fail(function () {

        window.location.replace("login.html");
    }).always(function () {

    });
});
