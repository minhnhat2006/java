<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>View Company</title>
</head>
<body class='contrast-red'>
	<div class='row-fluid'>
		<div class='span12'>
			<div class='page-header'>
				<h1 class='pull-left'>
					<i class='icon-edit'></i> <span>View Company</span>
				</h1>
				<div class='pull-right'>
					<ul class='breadcrumb'>
						<li><a href="/admin"><i class='icon-bar-chart'></i> </a></li>
						<li class='separator'><i class='icon-angle-right'></i></li>
						<li>Company</li>
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
							action="${contextPath}/admin/company/add.in"
							class="form form-striped" method="get" commandName="companyForm">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<div class='box'>
								<div class="box-header">
									<div class="title">Info</div>
								</div>
								<div class="box-content">
									<div class="row-fluid">${company.info}</div>
								</div>
								<div class="box-header">
									<div class="title">Website</div>
								</div>
								<div class="box-content">
									<div class="row-fluid">${company.website}</div>
								</div>
								<div class="box-header">
									<div class="title">Email</div>
								</div>
								<div class="box-content">
									<div class="row-fluid">${company.email}</div>
								</div>
								<div class="box-header">
									<div class="title">Phone</div>
								</div>
								<div class="box-content">
									<div class="row-fluid">${company.phone}</div>
								</div>
								<div class="box-header">
									<div class="title">Situation</div>
								</div>
								<div class="box-content">
									<div class="row-fluid">${company.situation}</div>
								</div>
								<div class="box-header">
									<div class="title">Expectation</div>
								</div>
								<div class="box-content">
									<div class="row-fluid">${company.expectation}</div>
								</div>
								<div class="box-header">
									<div class="title">Additional Info</div>
								</div>
								<div class="box-content">
									<div class="row-fluid">
										<p class="form-control-static">
											<c:if test="${not empty company.additionalInfo}">
												<a
													href="${contextPath}/admin/company/company_additionalFile.in?id=${idEncrypt}"
													target="_blank">Attachment</a>
											</c:if>
										</p>
									</div>
								</div>
								<div class="box-header">
									<div class="title">Created Date</div>
								</div>
								<div class="box-content">
									<div class="row-fluid">${company.createdDate}</div>
								</div>
							</div>

							<div class='box'>
								<hr class="hr-normal">
								<div class="text-right">
								<c:choose>
								    <c:when test="${company.ticket == null}">
								        <a class="btn btn-primary btn-large" href="${contextPath}/admin/ticket/company/${company.companyId}/add.in">Enhance Ticket</a>
								    </c:when>    
								    <c:otherwise>
								        <a class="btn btn-primary btn-large" href="${contextPath}/admin/ticket/${company.ticket.ticketId}/review.in">Enhance Ticket</a>
								    </c:otherwise>
								</c:choose>									
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
