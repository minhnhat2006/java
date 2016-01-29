<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Update Ticket</title>
<script src='${contextPath}/assets/javascripts/admin/ticket.js'
	type='text/javascript'></script>
</head>
<body class='contrast-red'>
	<div class='row-fluid'>
		<div class='span12'>
			<div class='page-header'>
				<h1 class='pull-left'>
					<i class='icon-edit'></i> <span>Update Ticket</span>
				</h1>
				<div class='pull-right'>
					<ul class='breadcrumb'>
						<li><a href="/admin"><i class='icon-bar-chart'></i> </a></li>
						<li class='separator'><i class='icon-angle-right'></i></li>
						<li>Ticket</li>
						<li class='separator'><i class='icon-angle-right'></i></li>
						<li class='active'>Update</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class='row-fluid'>
		<div class='span12 box'>
			<div class='box-content box-padding'>
				<div class='fuelux'>
					<div class='step-content'>
						<form:form accept-charset="UTF-8"
							action="${contextPath}/admin/ticket/${ticketForm.ticketId}/edit.in"
							class="form form-striped" method="POST" commandName="ticketForm">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<div class='step-pane active' id='step1'>
								<div class="control-group">
									<label for="selStatus" class="control-label">Change
										Status</label>
									<div class="controls">
										<form:select id="selStatus" path="status">
											<c:if test="${ticketForm.status == 0}">
												<form:option value="0" selected="selected">Open</form:option>
												<form:option value="1">Close</form:option>
											</c:if>
											<c:if test="${ticketForm.status == 1}">
												<form:option value="0">Open</form:option>
												<form:option value="1" selected="selected">Close</form:option>
											</c:if>
										</form:select>
									</div>
									<form:errors path="status" cssClass="help-block error" />
								</div>
								<div class='control-group'>
									<label class='control-label' for='txtTitle'>Title</label>
									<div class='controls'>
										<form:input class='input-block-level' id='txtTitle'
											placeholder='Title Ticket' path="title" />
									</div>
									<form:errors path="title" cssClass="help-block error" />
								</div>
								<div class='control-group'>
									<label class='control-label' for='txtContent'>Content</label>
									<div class='controls'>
										<form:textarea class='input-block-level' id='txtContent'
											placeholder='Content of Ticket' rows='20' path="content" />
									</div>
									<form:errors path="content" cssClass="help-block error" />
								</div>

								<div class="text-right">
									<a href="${contextPath}/admin/ticket/${type}/1/list.in"
										class="btn btn-danger btn-large"> <i class="icon-remove"></i>
										Cancel
									</a>
									<button type="submit" class="btn btn-primary btn-large">
										<i class="icon-check"></i> Submit
									</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input id="fileupload" type="file" name="files[]" data-url="${contextPath}/upload/image.in" multiple style="display:none"/>
</body>

</html>
