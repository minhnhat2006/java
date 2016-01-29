<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Forgot password</title>
</head>
<body>
	<div id="content">
		<div class="row inner-content">
			<div class="form-header">
				<h1>Forgot password</h1>
			</div>
			<form:form class="form-horizontal" role="form"
				action="${contextPath}/sec/forgot_password.in" method="post"
				commandName="userSignInForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<form:errors path="*" cssClass="help-block error"/>
				<div class="form-group required">
					<label class="control-label col-sm-3" for="email">Email:</label>
					<div class="col-sm-9">
						<form:input type="email" class="form-control" id="email"
							path="email" placeholder="Enter email" />
						<spring:bind path="email">
							<c:if test="${status.error}">
								<div class="alert alert-error error">${status.errorMessage}</div>
							</c:if>
						</spring:bind>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<button type="submit" class="btn btn-default">Send me instructions</button>
					</div>
				</div>
			</form:form>
			<a href="${contextPath}/sec/sign_up.in"><i class='icon-user'></i>New to Sharp In U?<strong>&nbsp;Register</strong></a>&nbsp;
			<a href="${contextPath}/sec/sign_in.in"><i class='icon-lock'></i>I already know my password </a>
		</div>
	</div>
</body>
</html>

