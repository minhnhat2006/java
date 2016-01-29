<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Import Advisors</title>
</head>
<body class='contrast-red'>
	<div class='row-fluid'>
		<div class='span12'>
			<div class='page-header'>
				<h1 class='pull-left'>
					<i class='icon-file'></i> <span>Import Advisors from CSV
						file</span>
				</h1>
				<div class='pull-right'>
					<ul class='breadcrumb'>
						<li><a href="/admin"><i class='icon-bar-chart'></i> </a></li>
						<li class='separator'><i class='icon-angle-right'></i></li>
						<li>Import Advisors</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<c:if test="${not empty successMsg}">				
		<div class="alert alert-success alert-dismissable">
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>
			${successMsg}
		</div>
	</c:if>
	
	<div class='row-fluid'>
		<div class='span12 box'>
			<div class='box-content box-padding'>
				<div class='fuelux'>
					<div class="step-content">
						<form:form accept-charset="UTF-8" class="form form-striped"
							method="post" enctype="multipart/form-data" commandName="importAdvisorForm">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<div class='box'>
								<div class="box-header">
									<div class="title">Select file</div>
								</div>
								<div class="box-content">
									<div class="row-fluid">
										<input type="file" name="csvFile" style='line-height:0'>
									</div>
								</div>
							</div>

							<div class='box'>
								<hr class="hr-normal">
								<div class="text-right">
									<button type="submit" class="btn btn-primary btn-large">Import</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<c:forEach items="${advisors}" var="advisor">
	<div class='row-fluid'>
		<div class='span12 box'>
			<div class='box-content'>
				<div class="row-fluid">
					<ul class="inline">
						<li><small>Advisor Id: ${advisor.advisorId}</small></li>
						<li><small>|</small></li>
						<li><small>Ticket Id: ${advisor.ticketId}</small></li>
						<li><small>|</small></li>
						<li><small>Token: ${advisor.token}</small></li>
						<li><small>|</small></li>
						<li><small>From Date: ${advisor.fromDate}</small></li>
						<li><small>|</small></li>
						<li><small>To Date: ${advisor.toDate}</small></li>
						<li><small>Articles: ${advisor.urls}</small></li>
						<li><small>|</small></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	</c:forEach>
</body>
</html>
