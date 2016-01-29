var ResumePage = {
    divImg: null,
    init: function () {
        var _self = this;
        _self.divImg = $('<div class="photo-uploaded" width="100%" height="100%"></div>');
        _self.initEvent();
    },
    initEvent: function () {
        var _self = this;
        $(document).on('change', '.btn-file :file', function () {
            var input = $(this),
                    numFiles = input.get(0).files ? input.get(0).files.length : 1,
                    label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [numFiles, label]);
        });
        $('.btn-file :file').on('fileselect', function (event, numFiles, label) {
            var input = $(this).parents('.input-group').find(':text'),
                    log = numFiles > 1 ? numFiles + ' files selected' : label;
            if (input.length && log !== '') {
                input.val(log);
            } else {
                if (log)
                    alert(log);
            }

        });
        //handle load image for resume
        $(document).on('change', 'div.res-photo :file', function (event) {
            if (_self.supportFileReader()) {
                var files = event.target.files; //FileList object										
                //Only image
                if (files && files[0]) {
                    var file = files[0];
                    if (file.type.match('image') && (file.type.match('jpeg') || file.type.match('jpg') || file.type.match('gif') || file.type.match('png'))) {
                        var imgReader = new FileReader();
                        imgReader.onloadend = function (event) {
                            var img = event.target;
                            $(_self.divImg).css({'background-image': 'url("' + img.result + '")'});
                            $('div.res-photo').remove('div.photo-uploaded');
                            $('div.res-photo').append(_self.divImg);

                        };
                        //Read the image
                        imgReader.readAsDataURL(file);

                    }
                }
            }
        });
        
         $(document).on('mouseover', 'div.res-photo', function (ev) {
            $(_self.divImg).hide();
        });
        $(document).on('mouseout', 'div.res-photo', function (ev) {
            $(_self.divImg).show();
        });
    },
    supportFileReader: function () {
        return window.File && window.FileList && window.FileReader;
    }
};

$(document).ready(function () {
    ResumePage.init();
    $('form').get(0).reset(); //clear form data on page load
});
