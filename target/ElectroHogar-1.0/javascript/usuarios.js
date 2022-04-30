/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/* global Swal */

//for(let usuario of usuarios){
//            let btnEliminar = '<td><a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
//            let usuarioHtml = '<tr> <td>'+usuario.id+'</td><td>'+usuario.nombre+' '+usuario.apellido+'</td><td>'+usuario.email+'</td><td>'+usuario.telefono+'</td>'+btnEliminar+'</td></tr>';
//            listadoHtml += usuarioHtml;
//      }


function registrarUsuario() {
    alert("entro a registrar usuario js");
    var datos = {};

    datos.nombre = document.getElementById('nombre').value;
    datos.Identificacion = document.getElementById('idcli').value;
    datos.Email = document.getElementById('email').value;
    datos.telefono = document.getElementById('telefono').value;
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
