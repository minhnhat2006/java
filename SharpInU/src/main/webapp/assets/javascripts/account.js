$(document).ready(function () {
	$('#newPasswordForm').on('submit', function() {
		if (!$('#password').val()) {
			alert('Password not inputted');
			return false;
		}
		if ($('#password').val() != $('#confirmPassword').val()) {
			alert('Password not matches');
			return false;
		}
		return true;
	});
});
