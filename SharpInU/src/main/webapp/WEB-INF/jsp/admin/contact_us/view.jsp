<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>View Contact Us</title>
</head>
<body class='contrast-red'>
	<div class='row-fluid'>
		<div class='span12'>
			<div class='page-header'>
				<h1 class='pull-left'>
					<i class='icon-edit'></i> <span>Contact Us</span>
				</h1>
				<div class='pull-right'>
					<ul class='breadcrumb'>
						<li><a href="/admin"><i class='icon-bar-chart'></i> </a></li>
						<li class='separator'><i class='icon-angle-right'></i></li>
						<li>Contact Us</li>
						<li class='separator'><i class='icon-angle-right'></i></li>
						<li class='active'>View</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class='row-fluid'>
		<div class='span12 box'>
			<div class='box-content box-padding'>
				<div class='fuelux'>
					<div class="step-content">
						<form:form accept-charset="UTF-8"
							action="${contextPath}/admin/contactus/add.in"
							class="form form-striped" method="get"
							commandName="contactusForm">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<div class='box'>
								<div class="box-header">
									<div class="title">Name</div>
								</div>
								<div class="box-content">
									<div class="row-fluid">${contactus.name}</div>
								</div>
								<div class="box-header">
									<div class="title">Phone</div>
								</div>
								<div class="box-content">
									<div class="row-fluid">${contactus.phone}</div>
								</div>
								<div class="box-header">
									<div class="title">Email</div>
								</div>
								<div class="box-content">
									<div class="row-fluid">${contactus.email}</div>
								</div>
								<div class="box-header">
									<div class="title">Company</div>
								</div>
								<div class="box-content">
									<div class="row-fluid">${contactus.companyId}</div>
								</div>
								<div class="box-header">
									<div class="title">Message</div>
								</div>
								<div class="box-content">
									<div class="row-fluid">${contactus.message}</div>
								</div>
								<div class="box-header">
									<div class="title">Created Date</div>
								</div>
								<div class="box-content">
									<div class="row-fluid">${contactus.createdDate}</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
