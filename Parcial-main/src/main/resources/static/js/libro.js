$(document).ready(function () {
    listar();
    llenar();
    llenare();
});


function llenar() {
    $.ajax({
        url: "/au/all",
        type: 'GET',
        success: function (x) {

            for (var i = 0; i < x.length; i++) {
                $("#autor").append(
                        "<option value='" + x[i].idautor + "'> " + x[i].nombre + "</option>");
                $("#editar_autor").append(
                        "<option value='" + x[i].idautor + "'> " + x[i].nombre + "</option>");
            }
        }
    });

}
function llenare() {
    $.ajax({
        url: "/edi/all",
        type: 'GET',
        success: function (x) {

            for (var i = 0; i < x.length; i++) {
                $("#editorial").append(
                        "<option value='" + x[i].ideditorial + "'> " + x[i].nombre + "</option>");
                $("#editar_editorial").append(
                        "<option value='" + x[i].ideditorial + "'> " + x[i].nombre + "</option>");
            }
        }
    });

}


function listar() {
    $.ajax({
        url: "/li/all",
        type: 'GET',
        success: function (x) {
            $("#tablita tbody tr").remove();
            for (var i = 0; i < x.length; i++) {
                $("#tablita").append(
                        "<tr><td>" + (i + 1) + "</td><td>" + x[i].idlibro + "</td><td>" + x[i].titulo
                        + "</td><td>" + x[i].idioma + "</td><td>" + (i+20)
                        + "</td><td>" + x[i].descripcion + "</td><td>" + x[i].idautor
                        + "</td><td>" + x[i].ideditorial 
                        + "</td><td><a href='#' onclick='editar("
                        + x[i].idlibro + ")'><i class='fa-solid fa-pen-to-square yelow'></i></a></td><td><a href='#' onclick='eliminar(" + x[i].idlibro + ")'><i class='fa-solid fa-trash-can red'></i></a></td></tr>");
            }
        }
    });
}

function editar(id) {
    $.ajax({
        url: "/li/" + id,
        type: 'GET',
        success: function (w) {
            $("#editar_titulo").val(w.titulo);
            $("#editar_idioma").val(w.idioma);
            $("#editar_paginas").val(w.paginas);
            $("#editar_descripcion").val(w.descripcion);
            $("#editar_autor").val(w.idautor);
            $("#editar_editorial").val(w.ideditorial);
            $("#autor").val(w.idlibro);
        }
    });
    $("#modalEditar").modal('show');
}

function eliminar(id) {

    bootbox.confirm({
        message: "Realmente desea Eliminar?",
        buttons: {
            confirm: {
                label: 'SI',
                className: 'btn-success'
            },
            cancel: {
                label: 'NO',
                className: 'btn-danger'
            }
        },
        callback: function (result) {
            if (result) {
                $.ajax({
                    url: "/li/" + id,
                    type: 'DELETE',
                    success: function (w) {
                        bootbox.alert({
                            message: "Registro eliminado correctamente...!",
                            callback: function () {
                                console.log('This was logged in the callback!');
                            }
                        });
                        listar();
                    }
                });
            } else {
                bootbox.alert({
                    message: "Registro no eliminado!",
                    size: 'small'
                });
            }
        }
    });
}


$("#guardar").click(function () {
    
    var titulo = $("#titulo").val();
    var idioma = $("#idioma").val();
    var pag = $("#pag").val();
    var descripcion = $("#descripcion ").val();
    var idautor = $("#autor").val();
    var ideditorial = $("#editorial").val();
 
    $.ajax({
        url: "/li/add",
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({'titulo': titulo, 'idioma': idioma, 'paginas': pag,'descripcion': descripcion,'idautor': idautor,'ideditorial': ideditorial}),
        cache: false,
        success: function (w) {
            bootbox.alert({
                message: "Registro guardado correctamente...!",
                callback: function () {
                    console.log('This was logged in the callback!');
                }
            });
            limpiar();
            listar();
        }
    });
    $("#modalGuardar").modal('hide');
});

$("#modificar").click(function () {
    var titulo = $("#editar_titulo").val();
    var idioma = $("#editar_idioma").val();
    var pag = $("#editar_paginas").val();
    var desc = $("#editar_descripcion").val();
    var idautor = $("#editar_autor").val();
    var ideditorial = $("#editar_editorial").val();
    var idlibro = $("#autor").val();
    
    
    bootbox.confirm({
        message: "Realmente desea Modificar?",
        buttons: {
            confirm: {
                label: 'SI',
                className: 'btn-success'
            },
            cancel: {
                label: 'NO',
                className: 'btn-danger'
            }
        },
        callback: function (result) {
            if (result) {
                $.ajax({
                    url: "/li/edit",
                    type: 'PUT',
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify({'titulo': titulo, 'idioma': idioma, 'paginas': pag, 'descripcion': desc, 'idautor': idautor, 'ideditorial': ideditorial, 'idlibro': idlibro}),
                    cache: false,
                    success: function (w) {
                        bootbox.alert({
                            message: "Registro Modificado correctamente...!",
                            callback: function () {
                                console.log('This was logged in the callback!');
                            }
                        });
                        listar();
                    }
                });
                $("#modalEditar").modal('hide');
            } else {
                bootbox.alert({
                    message: "Registro no Modificado!",
                    size: 'small'
                });
            }
        }
    });
});