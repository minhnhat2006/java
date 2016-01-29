var ResumeAdminPage = {
    divImg: null,
    urlReq: 'http://localhost:8080/SharpInU/ajax/admin/resume/upload',
    init: function () {
        var _self = this;

        _self.initEvent();
    },
    initEvent: function () {
        var _self = this;
        //handle load image for resume
        $(document).on('change', 'div.upload-cv :file', function (event) {
            //if (_self.supportFileReader()) {
            var files = event.target.files; //FileList object										
            if (files && files[0]) {
                var file = files[0];
                var oMyForm = new FormData();
                var id=$("div.upload-cv").find("#res_id").val();
                
                oMyForm.append("file", file);
                oMyForm.append("id",id);
                $.ajax({
                    url: _self.urlReq,
                    data: oMyForm,
                    dataType: 'text',
                    processData: false,
                    contentType: false,
                    type: 'POST',
                    success: function (data) {
                        console.log(data);
                    }
                });
            }
            //}
        });
    },
    supportFileReader: function () {
        return window.File && window.FileList && window.FileReader;
    }
};

$(document).ready(function () {
    ResumeAdminPage.init();

});
