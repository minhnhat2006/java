<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Category Form</title>
</head>
<body>
	<div id="content">
		<div class="row inner-content">
			<div class="form-header">
				<h1>Personal Information</h1>
			</div>
			<form:form class="form-horizontal" role="form"
				action="${contextPath}/category_form.in" method="post"
				commandName="categoryForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div class="form-group required">
					<label class="control-label col-sm-3" for="per_info">Info:</label>
					<div class="col-sm-9">
						<form:textarea class="form-control" id="per_info" path="info"
							placeholder="" />
						</td>
						<spring:bind path="info">
							<c:if test="${status.error}">
								<div class="alert alert-error error">${status.errorMessage}</div>
							</c:if>
						</spring:bind>
					</div>
				</div>
				<div class="form-group required">
					<label class="control-label col-sm-3" for="per_email">Email:</label>
					<div class="col-sm-9">
						<form:input type="email" class="form-control" id="per_email"
							path="email" placeholder="Enter email" />
						<spring:bind path="email">
							<c:if test="${status.error}">
								<div class="alert alert-error error">${status.errorMessage}</div>
							</c:if>
						</spring:bind>
					</div>
				</div>
				<div class="form-group required">
					<label class="control-label col-sm-3" for="per_phone">Phone:</label>
					<div class="col-sm-9">
						<form:input type="text" class="form-control" path="phone"
							id="per_phone" placeholder="+84 909 123456" />
						<spring:bind path="phone">
							<c:if test="${status.error}">
								<div class="alert alert-error error">${status.errorMessage}</div>
							</c:if>
						</spring:bind>
					</div>
				</div>
				<div class="form-group required">
					<label class="control-label col-sm-3" for="per_situation">Situation:</label>
					<div class="col-sm-9">
						<form:textarea class="form-control" id="per_situation"
							path="situation" placeholder="" />
						<form:errors path="situation" cssClass="help-block error" />
					</div>
				</div>
				<div class="form-group required">
					<label class="control-label col-sm-3" for="per_expectation">Your
						expectation:</label>
					<div class="col-sm-9">
						<form:textarea class="form-control" id="per_expectation"
							path="expectation" placeholder="" />
						<spring:bind path="expectation">
							<c:if test="${status.error}">
								<div class="alert alert-error error">${status.errorMessage}</div>
							</c:if>
						</spring:bind>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<button type="submit" class="btn btn-default">Submit</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>
