$(document).ready(function() {
	$("#passwordSentModal .btn-primary").click(function(e) {
		$('#passwordSentModal').modal('hide')
	});

	$("#forgotPasswordModal .btn-primary").click(function(e) {
		checkAndSendEmail();
	});
});

function checkAndSendEmail() {
	var $forgotPasswordModal = $('#forgotPasswordModal');
	var email = $forgotPasswordModal.find('.email-address').val().trim();
	if (!validateEmail(email)) {
		$forgotPasswordModal.find('.modal-error').text("Invalid email address.");
		return;
	}
	if (!isEmailExisting(email)) {
		var message = "We couldn't find the account associated with '" + email + "'";
		$forgotPasswordModal.find('.modal-error').text(message);
	} else {
		sendEmailForResetPassword(email);
		$forgotPasswordModal.modal('hide');
		$forgotPasswordModal.find('.modal-error').text("");
		$forgotPasswordModal.find('.email-address').val("");
		$('#passwordSentModal').modal('show');
	}
}

function isEmailExisting(email) {
	var isExisting = false;
	DwrUserService.isEmailExisting(email, {
		async : false,
		callback : function(result) {
			isExisting = result;
		},
		errorHandler : function(message) {
			console.log(message);
		}
	});
	return isExisting;
}

function sendEmailForResetPassword(email) {
	var isExisting = false;
	DwrUserService.sendEmailForResetPassword(email, {
		async : false,
		callback : function(result) {
			isExisting = result;
		},
		errorHandler : function(message) {
			console.log(message);
		}
	});
	return isExisting;
}

function validateEmail(email) {
    var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    return re.test(email);
}