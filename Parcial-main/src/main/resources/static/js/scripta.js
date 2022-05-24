$(document).ready(function () {
    listar();
});

function listar() {
    $.ajax({
        url: "/au/all",
        type: 'GET',
        success: function (x) {
            $("#tablita tbody tr").remove();
            for (var i = 0; i < x.length; i++) {
                $("#tablita").append(
                        "<tr><td>" + (i + 1) + "</td><td>" + x[i].idautor + "</td><td>" + x[i].nombre
                        + "</td><td>" + x[i].apellido + "</td><td>" + x[i].correo + "</td><td><a href='#' onclick='editar("
                        + x[i].idautor + ")'><i class='fa-solid fa-pen-to-square yelow'></i></a></td><td><a href='#' onclick='eliminar(" + x[i].idautor + ")'><i class='fa-solid fa-trash-can red'></i></a></td></tr>");
            }
        }
    });
}

function editar(id) {
    $.ajax({
        url: "/au/" + id,
        type: 'GET',
        success: function (w) {
            $("#editar_titulo").val(w.nombre);
            $("#editar_descripcion").val(w.apellido);
            $("#editar_correo").val(w.correo);
            $("#idpost").val(w.idautor);
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
                    url: "/au/" + id,
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
function limpiar() {
    $("#titulo").val("");
    $("#descripcion").val("");
}

$("#guardar").click(function () {
    var titulo = $("#titulo").val();
    var desc = $("#descripcion").val();
    var co = $("#correo").val();
    $.ajax({
        url: "/au/add",
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({'nombre': titulo, 'apellido': desc, 'correo': co}),
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
    var desc = $("#editar_descripcion").val();
    var co = $("#editar_correo").val();
    var id = $("#idpost").val();
    alert (" Nombre : " + titulo + "  Apellido :" + desc + " Correo :" + co + " id :"+ id);
    
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
                    url: "/au/edit",
                    type: 'PUT',
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify({'idautor': id, 'nombre': titulo, 'apellido': desc, 'correo': co}),
                    cache: false,
                    success: function (w) {
                        bootbox.alert({
                            message: "Registro Modificado correctamente...!",
                            callback: function () {
                                console.log('This was logged in the callback!');
                            }
                        });
                        limpiar();
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