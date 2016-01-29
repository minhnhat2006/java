<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Categories</title>
<script src='${contextPath}/assets/javascripts/account.js'
	type='text/javascript'></script>
</head>
<body class='contrast-red '>
	<div class='page-header'>
		<h1 class='pull-left'>
			<i class='icon-table'></i> <span>Website Activities Report</span>
		</h1>
	</div>
	<div class='row-fluid'>
		<div class='span12 box'>
			<div class="box bordered-box blue-border" style="margin-bottom: 0;">
				<div class="box-content box-no-padding">
					<div class="responsive-table">
						<table
							class="data-table table table-bordered table-striped dataTable"
							style="margin-bottom: 0;">
							<thead>
								<tr role="row">
									<th role="columnheader" tabindex="0" rowspan="1" colspan="1">Number</th>
									<th role="columnheader" tabindex="0" rowspan="1" colspan="1">Total</th>
									<th role="columnheader" tabindex="0" rowspan="1" colspan="1">Last
										7 Days</th>
									<th role="columnheader" tabindex="0" rowspan="1" colspan="1">Last
										30 Days</th>
								</tr>
							</thead>

							<tbody role="alert" aria-live="polite" aria-relevant="all">
								<c:forEach items="${statisticBeans}" var="bean">
									<tr>
										<td><a
											href="${contextPath}/admin/report/details.in?reportType=${bean.type}"
											class="text-contrast">${bean.name}</a></td>
										<td>${bean.total}</td>
										<td>${bean.last7Days}</td>
										<td>${bean.last30Days}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		(function() {
			$("#daterange").daterangepicker(
					{
						format : 'MM/DD/YYYY',
						startDate : moment().subtract(6, 'days'),
						endDate : moment(),
						ranges : {
							'Today' : [ moment(), moment() ],
							'Yesterday' : [ moment().subtract(1, 'days'),
									moment().subtract(1, 'days') ],
							'Last 7 Days' : [ moment().subtract(6, 'days'),
									moment() ],
							'Last 30 Days' : [ moment().subtract(29, 'days'),
									moment() ],
							'This Month' : [ moment().startOf('month'),
									moment().endOf('month') ],
							'Last Month' : [
									moment().subtract(1, 'month').startOf(
											'month'),
									moment().subtract(1, 'month')
											.endOf('month') ]
						},
						opens : 'left',
						drops : 'down',
						buttonClasses : [ 'btn', 'btn-sm' ],
						applyClass : 'btn-primary',
						cancelClass : 'btn-danger',
						locale : {
							applyLabel : 'Submit',
						}
					},
					function(start, end) {
						return $("#daterange span").html(
								start.format("MMMM D, YYYY") + " - "
										+ end.format("MMMM D, YYYY"));
					});

		}).call(this);
	</script>
</body>
</html>
