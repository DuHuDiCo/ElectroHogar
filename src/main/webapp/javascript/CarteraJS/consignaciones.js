
$(function() {
    $('.input-file__input').on('change', function() {
        if($(this)[0].files[0]) {
            $(this).prev().text($(this)[0].files[0].name);
        }
    });
});




var form = document.getElementById('form');

form.onsubmit = e =>{
    var fd = new FormData(form);
    alert(fd);
    e.preventDefault();
    
    
     $.ajax({
        method: "POST",
        url: "ServletControlador?accion=guardarConsignacion",
        data: fd
        
    }).done(function (data) {
        
    };

        
    
    
};

    


