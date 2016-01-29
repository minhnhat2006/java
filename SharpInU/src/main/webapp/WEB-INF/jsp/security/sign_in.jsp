<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<link type="text/css" rel="stylesheet"
	href="${contextPath}/assets/stylesheets/login.css" />
<script type='text/javascript' src='${contextPath}/dwr/engine.js'></script>
<script type='text/javascript'
	src='${contextPath}/dwr/interface/DwrUserService.js'></script>
<script type='text/javascript' src='${contextPath}/dwr/util.js'></script>
<script type='text/javascript'
	src='${contextPath}/assets/javascripts/user/user.js'></script>
</head>
<body>
	<div id="content" class="row login-form">
		<div class="inner-content-left col-sm-7">
			<div class="content-right row">
				<div class="form-header col-sm-8 col-sm-offset-1">
					<h1>Log In</h1>
				</div>
				<div class="form-container col-sm-8 col-sm-offset-1">
					<p>If you have an existing account, enter your username and
						password below.</p>
					<form:form class="form-horizontal" role="form"
						action="${contextPath}/sec/security_check.in" method="post"
						commandName="userSignInForm">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<form:errors path="*" cssClass="help-block error" />
						<div class="form-group required">
							<div class="col-sm-12">
								<form:input type="email" class="form-control" id="email"
									path="email" placeholder="User name" />
								<spring:bind path="email">
									<c:if test="${status.error}">
										<div class="alert alert-error error">${status.errorMessage}</div>
									</c:if>
								</spring:bind>
							</div>
						</div>
						<div class="form-group required">

							<div class="col-sm-12">
								<form:password class="form-control" id="password"
									path="password" placeholder="Password" />
								<spring:bind path="password">
									<c:if test="${status.error}">
										<div class="alert alert-error error">${status.errorMessage}</div>
									</c:if>
								</spring:bind>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-5 col-sm-7">
								<a class="for-got-pass" href="javascript:;" data-toggle="modal"
									data-target="#forgotPasswordModal"><i class='icon-lock'></i>
									Forgot your password? </a>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-6">
								<button type="submit" class="btn btn-default">Log in</button>
							</div>
						</div>
					</form:form>
				</div>
				<div class="form-header col-sm-8 col-sm-offset-1">
					<h1>New User</h1>
				</div>
				<div class="form-container col-sm-8 col-sm-offset-1">
					<p>If you are new to Sharp-In-U, create an account now using
						our online registration process.</p>
					<div>
						<a href="${contextPath}/sec/sign_up.in"
							class="btn btn-default btn-sign-in">Create an account</a>
					</div>
				</div>
			</div>
		</div>
		<div class="inner-content-right col-sm-5">
			<div class="content-left">
				<img width="100%" src="${contextPath}/assets/images/rubik.png"
					class="login-background">
			</div>
		</div>
	</div>
	<div class="modal" id="forgotPasswordModal" tabindex="-1" role="dialog"
		aria-labelledby="forgotPasswordModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content model-siu-content">
				<div class="modal-header">
					<a href="#" class='model-siu-logo'><img
						src="${contextPath}/assets/images/ptsU1.png" class="img_bot"><span
						class="logo-text">Sharp-In</span></a>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="forgotPasswordModalLabel">Change
						your password</h3>
					<p class="modal-title">Let's find your account</p>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="row">
							<div class="col-sm-9">
								<span class="alert alert-error error modal-error"></span>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-9">
								<input type="text" class="form-control email-address"
									placeholder="Email Address" />
							</div>
							<div>
								<button type="button" class="btn btn-primary btn-sendMessage">Continue</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="passwordSentModal" tabindex="-1" role="dialog"
		aria-labelledby="passwordSentModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content model-siu-content">
				<div class="modal-header">
					<a href="#" class='model-siu-logo'><img
						src="${contextPath}/assets/images/ptsU1.png" class="img_bot"><span
						class="logo-text">Sharp-In</span></a>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title">Success!</h3>
				</div>
				<div class="modal-body">
					<p>We've sent you a link to change your password</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">Ok</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>