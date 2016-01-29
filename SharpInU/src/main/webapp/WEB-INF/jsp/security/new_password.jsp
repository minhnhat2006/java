<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>New password</title>
<script type='text/javascript'
	src='${contextPath}/assets/javascripts/account.js'></script>
</head>
<body>
	<div id="content">
		<div class="row inner-content">
			<div class="form-header">
				<h1>New password</h1>
			</div>
			<form:form class="form-horizontal" role="form"
				action="${contextPath}/sec/new_password.in" method="post"
				id="newPasswordForm" commandName="userSignInForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<form:hidden id="passwordHash" path="passwordHash" />
				<form:errors path="*" cssClass="help-block error" />
				<div class="form-group required">
					<div class="col-sm-6">
						<form:password class="form-control" id="password" path="password"
							placeholder="New password *" />
						<spring:bind path="password">
							<c:if test="${status.error}">
								<div class="alert alert-error error">${status.errorMessage}</div>
							</c:if>
						</spring:bind>
					</div>
				</div>
				<div class="form-group required">
					<div class="col-sm-6">
						<form:password class="form-control" id="confirmPassword"
							path="confirmPassword" placeholder="Re-enter new password *" />
						<spring:bind path="confirmPassword">
							<c:if test="${status.error}">
								<div class="alert alert-error error">${status.errorMessage}</div>
							</c:if>
						</spring:bind>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-6" style='margin-left: 0'>
						<button type="submit" class="btn btn-default">Change</button>
					</div>
				</div>
			</form:form>
			<a href="${contextPath}/sec/sign_in.in" class='for-got-pass'><i
				class='icon-lock'></i> I already know my password </a>
		</div>
	</div>
</body>
</html>

