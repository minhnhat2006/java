<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Review Ticket</title>
<link href='${contextPath}/assets/stylesheets/admin/ticket.css'
	media='all' rel='stylesheet' type='text/css' />
<script src='${contextPath}/assets/javascripts/admin/ticket.js'
	type='text/javascript'></script>
</head>

<body class='contrast-red'>
	<div class='row-fluid'>
		<div class='span12'>
			<div class='page-header'>
				<h1 class='pull-left'>
					<i class='icon-edit'></i> <span>Review Ticket</span>
				</h1>
				<div class='pull-right'>
					<ul class='breadcrumb'>
						<li><a href="/admin"><i class='icon-bar-chart'></i> </a></li>
						<li class='separator'><i class='icon-angle-right'></i></li>
						<li>Ticket</li>
						<li class='separator'><i class='icon-angle-right'></i></li>
						<li class='active'>Review</li>
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
						<div>
							<div class='box ticket-col'>
								<div class="title">Ticket Id</div>
								<div class="row-fluid">${ticket.ticketId}</div>
							</div>
							<div class='box ticket-col'>
								<div class="title">Subject</div>
								<div class="row-fluid">${ticket.subject}</div>
							</div>
							<div class='box ticket-col'>
								<div class="title">Status</div>
								<c:if test="${ticket.status == 0}">
									<div class="row-fluid">Open</div>
								</c:if>
								<c:if test="${ticket.status == 1}">
									<div class="row-fluid">Closed</div>
								</c:if>
							</div>
							<div class='box ticket-col'>
								<div class="title">Title</div>
								<div class="row-fluid">${ticket.title}</div>
							</div>
						</div>
						<div class='ticket-content'>
							<div class="title">${ticket.title}</div>
							<div class="row-fluid">${ticket.content}</div>
						</div>

						<c:forEach var="comment" items="${comments}">
							<div class="ticket-comment">
								<div class="ticket-comment-header">
									<i class=" icon-comment"></i>${comment.userName}<span
										style="float: right;">${comment.createdDate}</span>
								</div>
								<div class='ticket-content'>

									<div class="row-fluid" style="margin-top: 10px;">${comment.content}</div>
								</div>
							</div>
						</c:forEach>

						<div class='ticket-space'></div>

						<form:form id='frmTicket' accept-charset="UTF-8"
							enctype="multipart/form-data"
							action="${contextPath}/admin/ticket/p/${ticket.ticketId}/review.in"
							class="form" method="POST" commandName="commentForm">

							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<form:errors path="*" cssClass="help-block error" />
							<c:choose>
								<c:when test="${not empty errorMsg}">
									<span class="help-block error">${errorMsg}</span>
								</c:when>
							</c:choose>
							<c:if test="${ticket.status == 0}">
								<div class='form-group'>
									<div class='controls'>
										<form:textarea class='input-block-level' id='txtContent'
											placeholder='Add new comment for current ticket' rows='20'
											path="content" />
									</div>
									<form:errors path="content" cssClass="help-block error" />
								</div>
							</c:if>

							<div class='box panel-collapse collapse' id="collapse1">
								<div class="panel-group">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h5 class="panel-title">Upload Advisor</h5>
										</div>
										<div>
											<div class="panel-body">
												<div class="form-group">
													<label for="attachedFile">Select Advisor PDF to upload:</label> <input
														type="file" id="attachedFile" name="attachedFile"
														path="attachedFile" style='line-height: 0'>
												</div>

												<div class="form-group">
													<label for="csvFile">Select CSV file to import:</label> <input
														type="file" id="csvFile" name="csvFile" path="csvFile"
														style='line-height: 0'>
												</div>
											</div>

											<div class="panel-footer">
												<button type="submit" name="import"
													class="btn btn-primary btn-large">Upload</button>
											</div>
										</div>
									</div>
								</div>
							</div>

							<button type="button" class="btn btn-default btn-large">
								<a href="${contextPath}/admin/ticket/${ticket.toUser}/1/list.in"><i
									class="icon-check"></i> Back to Tickets</a>
							</button>

							<c:if test="${ticket.status == 0}">
								<button type="button" id='btnCloseTicket'
									class="btn btn-default btn-large">
									<i class="icon-check"></i> Close Ticket
								</button>
								<button type="button" id='btnImportAdvisor'
									class="btn btn-primary btn-large">
									<a data-toggle="collapse" href="#collapse1"><i
										class="icon-file"></i> Upload Advisor</a>
								</button>
							</c:if>

							<c:if test="${ticket.status == 0}">
								<button type="submit" name="comment"
									class="btn btn-primary btn-large pull-right">
									<i class="icon-check"></i> Post Comment
								</button>
							</c:if>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input id="fileupload" type="file" name="files[]" data-url="${contextPath}/upload/image.in" multiple style="display:none"/>
</body>
</html>
