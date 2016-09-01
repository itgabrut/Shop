/**
 * Created by ilya on 26.08.2016.
 */

function renderDeleteBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-danger" onclick="deleteRow(' + row.id + ');">Delete</a>';
    }
    return data;
}
function renderEditBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-danger" onclick="updateRow(' + row.id + ');">Edit</a>';
    }
    return data;
}



function add() {
    $('.modal-title').text("Данные клиента");
    $('#id').val(0);
    $('#editRow').modal();
    $('#datetimepicker1').datetimepicker({pickTime: false});
}

function check() {
    if($('#InputPassword').val()!==$('#inputPasswordConfirm').val()){
        $('#passcnf').removeClass('hidden');
     $('#passcnf').addClass('show alert alert-danger');
        return false;
    }
    else{
        $('#passcnf').addClass('hidden');
        return true;
    }
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + '?id='+id,
        type: 'DELETE',
        success: function () {
            updateTable();
            // successNoty('Deleted');
        }
    });
}
function updateRow(id) {
    var form = $('#editRow');
    $.get(ajaxUrl + '?id='+id, function (data) {
        $.each(data, function (key, value) {
            if(key=='adress'){
                var adress = value;
                $.each(adress,function (key2,value2) {
                    form.find("input[name='" + key2 + "']").val(value2);
                })
            }
            else
            form.find("input[name='" + key + "']").val(value);
        });
    });
    form.modal();
    $('#datetimepicker1').datetimepicker({pickTime: false});
}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
    chkbox.closest('tr').css("text-decoration", enabled ? "none" : "line-through");
    $.ajax({
        url: ajaxUrl + id,
        type: 'POST',
        data: 'enabled=' + enabled,
        success: function () {
            // successNoty(enabled ? 'Enabled' : 'Disabled');
        }
    });
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}



var failedNote;

// function closeNoty() {
//     if (failedNote) {
//         failedNote.close();
//         failedNote = undefined;
//     }
// }
//
// function successNoty(text) {
//     closeNoty();
//     noty({
//         text: text,
//         type: 'success',
//         layout: 'bottomRight',
//         timeout: true
//     });
// }

// function failNoty(event, jqXHR, options, jsExc) {
//     closeNoty();
//     failedNote = noty({
//         text: 'Failed: ' + jqXHR.statusText + "<br>",
//         type: 'error',
//         layout: 'bottomRight'
//     });
// }