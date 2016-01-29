<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Control Panel - Dashboard</title>
<link href='${contextPath}/assets/stylesheets/admin/dashboard.css' media='all' rel='stylesheet' type='text/css' />
</head>

<body class='contrast-sea-blue'>
	<div class='span12'>
		<div class='row-fluid'>
			<div class='span12'>
				<div class='page-header'>
					<h1 class='pull-left'>
						<i class='icon-time'></i> <span>Dashboard</span>
					</h1>
					<div class='pull-right'>
						<ul class='breadcrumb'>
							<li><a href="${contextPath}/admin/dashboard.in"><i class='icon-bar-chart'></i>
							</a></li>
							<li class='separator'><i class='icon-angle-right'></i></li>
							<li class='active'>Dashboard</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class='row-fluid acc-overview'>
			<div class='span12'>
				<ul class='unstyled'>
					<li class="deco">
						<div class='title'>Total Views:</div>
						<div class='content'>${statistic.siteViewTotal}</div>
					</li>

					<li class="deco">
						<div class='title'>Total Regsiters:</div>
						<div class='content'>${statistic.siteUserRegisterTotal}</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
