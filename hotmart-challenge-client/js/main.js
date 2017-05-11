/*
 * jQuery File Upload Plugin JS Example
 * https://github.com/blueimp/jQuery-File-Upload
 *
 * Copyright 2010, Sebastian Tschan
 * https://blueimp.net
 *
 * Licensed under the MIT license:
 * https://opensource.org/licenses/MIT
 */

/* global $, window */

$(function () {
    'use strict';

    // Initialize the jQuery File Upload widget:
    $('#fileupload').fileupload({
        // Uncomment the following to send cross-domain cookies:
        //xhrFields: {withCredentials: true},
        url: 'http://localhost:8080/hotmart-challenge/fileUploadChunk'
    });

    $('#fileupload').fileupload({
        maxChunkSize: 1024 * 1024
    })
    .on('fileuploaddone', function (e, data) {

    })
    .on('fileuploadchunksend', function (e, data) {
        data.headers.idUser = 'Teste';
    })
    .on('fileuploadchunkdone', function (e, data) {
        
    })
    .on('fileuploadchunkfail', function (e, data) {

    })
    .on('fileuploadchunkalways', function (e, data) {

    });

});
