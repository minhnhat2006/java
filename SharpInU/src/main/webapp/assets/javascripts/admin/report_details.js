$(function() {
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var dateRangeTitle = "";
	if (fromDate == "all" && toDate == "all") {
		dateRangeTitle = "View All";
		fromDate = moment();
		toDate = moment().subtract(1,"days");
	} else {
		dateRangeTitle = moment(fromDate).format('MMMM D, YYYY') + ' - ' + moment(toDate).format('MMMM D, YYYY');
	}
	$('#reportrange span').html(dateRangeTitle);

	$('#reportrange')
			.daterangepicker(
					{
						format : 'MM/DD/YYYY',
						startDate : moment(fromDate),
						endDate : moment(toDate),
						ranges : {
							'Last 7 Days' : [ moment().subtract(6, 'days'),
									moment() ],
							'Last 30 Days' : [ moment().subtract(29, 'days'),
									moment() ],
							'View All' : [moment(), moment().subtract(1,"days")]
						},
						opens : 'right',
						drops : 'down',
						buttonClasses : [ 'btn', 'btn-sm' ],
						applyClass : 'btn-primary',
						cancelClass : 'btn-default',
						separator : ' to ',
						locale : {
							applyLabel : 'Submit',
							cancelLabel : 'Cancel',
							fromLabel : 'From',
							toLabel : 'To',
							customRangeLabel : 'Custom',
							daysOfWeek : [ 'Su', 'Mo', 'Tu', 'We', 'Th', 'Fr',
									'Sa' ],
							monthNames : [ 'January', 'February', 'March',
									'April', 'May', 'June', 'July', 'August',
									'September', 'October', 'November',
									'December' ],
							firstDay : 1
						}
					},
					function(start, end, label) {
						if (label == "View All") {
							$("#fromDate").val("all");
							$("#toDate").val("all");
						} else {
							$("#fromDate").val(start.format('MM/DD/YYYY'));
							$("#toDate").val(end.format('MM/DD/YYYY'));
						}
						$("#reportFilterForm").submit();
					});

});

function openViewDetailReport(element) {
	element = $(element);
	$("#pageTitle").val(element.data("pageTitle"));
	$("#reportType").val(7);
	$("#reportFilterForm").submit();
}

$(document).ready(function() {
	var reportType = $("#reportType").val();
	if (reportType != '6') {
		$("table").dataTable();
	}
})
