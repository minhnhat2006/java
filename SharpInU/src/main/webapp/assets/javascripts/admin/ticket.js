var ADMTicket = {
	C_TEXT_READ_MORE : 'Read More',
	C_TEXT_COLLAPSE : 'Collapse',
	UploadImageCallback: null,

	readMore : function(readMore) {
		var text = $(readMore).text();

		if (text === ADMTicket.C_TEXT_READ_MORE) {
			$(readMore).text(ADMTicket.C_TEXT_COLLAPSE);
			$(readMore).parent().css('max-height', 'none');
		} else {
			$(readMore).text(ADMTicket.C_TEXT_READ_MORE);
			$(readMore).parent().css('max-height', '');
		}

		return false;
	}
}

$(document).ready(function() {
	$('a.read-more').click(function(e) {
		e.preventDefault();
		ADMTicket.readMore(this);
	});

	$('#btnCloseTicket').click(function(e) {
		e.preventDefault();
		var form = $('#frmTicket');
		var action = form.attr("action").replace('/p/', '/c/');
		form.attr("action", action);
		form.submit();
	});
	
	$('#fileupload').fileupload({
		dataType : 'json',
		done : function(e, data) {
			$.each(data.result, function (index, file) {
				ADMTicket.UploadImageCallback(file.fileName);
            }); 
		},
		progressall : function(e, data) {
			var progress = parseInt(data.loaded / data.total * 100, 10);
			var mcetextbox = $('.mce-textbox:first');
			var next = mcetextbox.next();

			if (next.attr('id') != 'progress') {
				mcetextbox.after('<div id="progress"><div style="width:' + progress + '%;"></div></div>');
			} else {
				next.find('div').css('width', progress + '%');
			}
			
			if (progress == 100) {
				mcetextbox.next().remove();
			}
		}
	});
	
	tinymce
	.init({
		selector : "textarea#txtContent",
		plugins : [
				"image imagetools",
				"advlist autolink link lists charmap print preview hr anchor pagebreak spellchecker",
				"searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
				"save table contextmenu directionality emoticons template paste textcolor" ],
		imagetools_cors_hosts : [ 'localhost:8080', 'sharp-in-u.com' ],
		imagetools_proxy : '/admin/upload/image_proxy.in',
		file_picker_callback : function(callback, value, meta) {
			// Provide image and alt text for the image dialog
			if (meta.filetype == 'image') {
				$('#fileupload').click();
				
				ADMTicket.UploadImageCallback = callback;
				
			}
		},
		toolbar : "insertfile undo redo | sizeselect | bold italic | fontselect |  fontsizeselect | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | print preview media fullpage | forecolor backcolor emoticons"
	});
});