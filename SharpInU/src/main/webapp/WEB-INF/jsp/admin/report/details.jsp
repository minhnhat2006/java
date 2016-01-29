<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Categories</title>
<script src='${contextPath}/assets/javascripts/admin/report_details.js'
	type='text/javascript'></script>
<style type="text/css"
	href="${contextPath}/assets/stylesheets/admin/report.css"></style>
</head>
<body class='contrast-red '>
	<div class='page-header'>
		<h1 class='pull-left'>
			<i class='icon-table'></i> <span>${title}</span>
		</h1>
	</div>

	<div class='row-fluid'>
		<div class='span12 box'>
			<div class="box bordered-box blue-border" style="margin-bottom: 0;">
				<div class="box-header">
					<form action="${contextPath}/admin/report/details.in" method="POST"
						id="reportFilterForm">
						<div id="reportrange" class="btn"
							style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
							<i class="glyphicon glyphicon-calendar fa fa-calendar"></i> <span></span>
							<b class="caret"></b>
						</div>
						<input id="fromDate" type="hidden" name="fromDate"
							value="${fromDate}"> <input id="toDate" type="hidden"
							name="toDate" value="${toDate}"> <input id="reportType"
							type="hidden" name="reportType" value="${reportType}">
						<input id="pageTitle" type="hidden" name="pageTitle"
							value="${pageTitle}">
					</form>
				</div>
				<div class="box-content box-no-padding">
					<div class="responsive-table">
						<table
							class="table table-bordered table-hover table-striped"
							style="margin-bottom: 0;">
							<c:choose>
								<c:when test="${reportType == 1}">
									<thead>
										<tr role="row">
											<th role="columnheader" tabindex="0" rowspan="1" colspan="1">Username</th>
											<th role="columnheader" tabindex="0" rowspan="1" colspan="1">Email</th>
											<th role="columnheader" tabindex="0" rowspan="1" colspan="1">Register
												Date</th>
										</tr>
									</thead>

									<tbody role="alert" aria-live="polite" aria-relevant="all">
										<c:forEach items="${statisticBeans}" var="bean">
											<tr>
												<td><a class="text-contrast"
													href="${contextPath}/admin/user/${bean.id}/review.in">${bean.email}</a>
												</td>
												<td>${bean.email}</td>
												<td>${bean.submitDate}</td>
											</tr>
										</c:forEach>
									</tbody>
								</c:when>
								<c:when test="${reportType == 6}">
									<thead>
										<tr role="row">
											<th role="columnheader" tabindex="0" rowspan="1" colspan="1">Page Title</th>
											<th role="columnheader" tabindex="0" rowspan="1" colspan="1">Total View</th>
											<th role="columnheader" tabindex="0" rowspan="1" colspan="1">Last Access</th>
										</tr>
									</thead>

									<tbody role="alert" aria-live="polite" aria-relevant="all">
										<c:forEach items="${statisticBeans}" var="bean">
											<tr>
												<td><a href="#" class="text-contrast" data-page-title="${bean.info}" onclick="openViewDetailReport(this)">${bean.info}</a></td>
												<td>${bean.pageViews}</td>
												<td>${bean.submitDate}</td>
											</tr>
										</c:forEach>
									</tbody>
								</c:when>
								<c:when test="${reportType == 7}">
									<thead>
										<tr role="row">
											<th role="columnheader" tabindex="0" rowspan="1" colspan="1">IP</th>
											<th role="columnheader" tabindex="0" rowspan="1" colspan="1">View Date</th>
										</tr>
									</thead>

									<tbody role="alert" aria-live="polite" aria-relevant="all">
										<c:forEach items="${statisticBeans}" var="bean">
											<tr>
												<td>${bean.info}</td>
												<td>${bean.submitDate}</td>
											</tr>
										</c:forEach>
									</tbody>
								</c:when>
								<c:otherwise>
									<thead>
										<tr role="row">
											<c:choose>
												<c:when test="${reportType == 2}">
													<th role="columnheader" tabindex="0" rowspan="1"
														colspan="1">Company Advice Request</th>
													<th role="columnheader" tabindex="0" rowspan="1"
														colspan="1">Company Info</th>
													<th role="columnheader" tabindex="0" rowspan="1"
														colspan="1">Email</th>
													<th role="columnheader" tabindex="0" rowspan="1"
														colspan="1">Submit Date</th>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${reportType == 3}">
													<th role="columnheader" tabindex="0" rowspan="1"
														colspan="1">Career Advice Request</th>
													<th role="columnheader" tabindex="0" rowspan="1"
														colspan="1">Username</th>
													<th role="columnheader" tabindex="0" rowspan="1"
														colspan="1">Email</th>
													<th role="columnheader" tabindex="0" rowspan="1"
														colspan="1">Submit Date</th>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${reportType == 4}">
													<th role="columnheader" tabindex="0" rowspan="1"
														colspan="1">Resume Request</th>
													<th role="columnheader" tabindex="0" rowspan="1"
														colspan="1">Username</th>
													<th role="columnheader" tabindex="0" rowspan="1"
														colspan="1">Email</th>
													<th role="columnheader" tabindex="0" rowspan="1"
														colspan="1">Submit Date</th>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${reportType == 5}">
													<th role="columnheader" tabindex="0" rowspan="1"
														colspan="1">Contact Us Request</th>
													<th role="columnheader" tabindex="0" rowspan="1"
														colspan="1">Company/Personal Info</th>
													<th role="columnheader" tabindex="0" rowspan="1"
														colspan="1">Email</th>
													<th role="columnheader" tabindex="0" rowspan="1"
														colspan="1">Request Date</th>
												</c:when>
											</c:choose>
										</tr>
									</thead>

									<tbody role="alert" aria-live="polite" aria-relevant="all">
										<c:forEach items="${statisticBeans}" var="bean"
											varStatus="status">
											<tr>
												<c:choose>
													<c:when test="${reportType == 2}">
														<td><a
															href="${contextPath}/admin/company/${bean.id}/view.in"
															class="text-contrast">Request ${status.count}</a></td>
													</c:when>
												</c:choose>
												<c:choose>
													<c:when test="${reportType == 3}">
														<td><a
															href="${contextPath}/admin/career/${bean.id}/view.in"
															class="text-contrast">Request ${status.count}</a></td>
													</c:when>
												</c:choose>
												<c:choose>
													<c:when test="${reportType == 4}">
														<td><a
															href="${contextPath}/admin/resume/${bean.id}/view.in"
															class="text-contrast">Request ${status.count}</a></td>
													</c:when>
												</c:choose>
												<c:choose>
													<c:when test="${reportType == 5}">
														<td><a
															href="${contextPath}/admin/contactus/${bean.id}/view.in"
															class="text-contrast">Request ${status.count}</a></td>
													</c:when>
												</c:choose>
												<td>${bean.info}</td>
												<td>${bean.email}</td>
												<td>${bean.submitDate}</td>
											</tr>
										</c:forEach>
									</tbody>
								</c:otherwise>
							</c:choose>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
