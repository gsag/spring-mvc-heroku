/**
 * Created by guilherme on 24/09/16.
 */
$(document).ready(function() {
    "use strict";

    var instance = CKEDITOR.replace('ckeditor');

    // instance.on('focus', function(event) {
    //     var editor = CKEDITOR.instances['ckeditor'];
    //     if(editor.plugins.a11ychecker){
    //         var a11ycheckerCommand = new CKEDITOR.command(editor, {
    //             exec: function( editor ) {
    //                 editor.execCommand("a11ychecker");
    //             }
    //         });
    //         a11ycheckerCommand.setState(CKEDITOR.TRISTATE_ON);
    //         a11ycheckerCommand.exec(editor);
    //     }
    // });

    //DEBUG
    instance.on('key', function(event) {
        console.log('Inconformidades encontradas: '+issueListLength);
        var btn = $("#saveBtn");
        (issueListLength>0)? btn.prop('disabled', true) : btn.prop('disabled', false);
    });

    $("#saveBtn").on("click", function (event) {
        var editorData = CKEDITOR.instances['ckeditor'].getData();
        var json = {"data":editorData, "_editor":"ckeditor", "_user":$("#user").val()};
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: '/editores',
            data: JSON.stringify(json),
            dataType: 'text',
            type: "POST",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "text/plain");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token);
            },
            success: function(data){
                alert(data);
                $("#saveBtn").prop('disabled', true);
            }
        });
        event.preventDefault();
    });
});