/**
 * Hanlde events from Global Setting page
 */
$(document).ready(function() {
	$('input.setting-value').blur(function(e) {
		var $self = $(this);
		var settingId = $self.attr('settingId');
		var currentValue = $self.attr('currentValue').trim();
		var newValue = $self.val().trim();
		if (newValue != currentValue) {
			DwrSettingService.updateGlobalSetting(settingId, newValue, {
				callback : function(result) {
					if (result) {
						log.error("Updated setting successful");
					} else {
						log.error("Failed to update setting");
					}
				},
				errorHandler : function(message) {
					console.log(message);
				}
			});
		}
	});
	
});