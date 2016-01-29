<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Advisor token required</title>
<script type='text/javascript' src='${contextPath}/dwr/engine.js'></script>
<script type='text/javascript' src='${contextPath}/dwr/util.js'></script>
</head>
<body>
	<div id="content" class="row login-form">
		<div class="inner-content-left col-sm-7">
			<div class="content-right row">
				<div class="form-header col-sm-8 col-sm-offset-1">
					<h1>Token Required</h1>
				</div>
				<div class="form-container col-sm-8 col-sm-offset-1">
					<p>Please enter Token for Advisor</p>
					<form:form class="form-horizontal" role="form"
						commandName="advisorForm">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<form:errors path="*" cssClass="help-block error" />
						<c:choose>
							<c:when test="${not empty  errorMsg}">
								<span class="help-block error">${errorMsg}</span>
							</c:when>
						</c:choose>
						<div class="form-group required">
							<div class="col-sm-12">
								<form:input type="token" class="form-control" id="token"
									path="token" placeholder="Input Token" />
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-6">
								<button type="submit" class="btn btn-default">Go</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div class="inner-content-right col-sm-5">
			<div class="content-left">
				<img width="100%"
					src="${contextPath}/assets/images/advisor_token_required.jpg"
					class="login-background">
			</div>
		</div>
	</div>
</body>
</html>