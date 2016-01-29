<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Contact Us</title>
<link type="text/css" rel="stylesheet"
	href="${contextPath}/assets/stylesheets/contact_us.css">
<script type="text/javascript">
	$(document).ready(function() {
		$(document).keydown(function(e) {
			if (e.keyCode == 8) {
				e.preventDefault();
			}
		});
	});
</script>
</head>
<body>
	<div id="content">
		<div class="row inner-content">
			<div class="form-header">
				<h1>Contact Us</h1>
			</div>
			<c:if test="${not empty successMsg}">
				<div class="alert alert-success alert-dismissable">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					${successMsg}
				</div>
			</c:if>
			<form:form accept-charset="UTF-8" action="" class="form-horizontal"
				role="form">
				<div class="form-group">
					<label class="control-label col-sm-3" for="contr_name">Name:</label>
					<div class="col-sm-9">
						<p class="form-control-static warp-text">${contactUs.name}</p>
					</div>

				</div>
				<div class="form-group ">
					<label class="control-label col-sm-3" for="contr_company">Company:</label>
					<div class="col-sm-9">
						<p class="form-control-static warp-text">${contactUs.companyId}</p>
					</div>

				</div>
				<div class="form-group ">
					<label class="control-label col-sm-3" for="contr_email">Email:</label>
					<div class="col-sm-9">
						<p class="form-control-static warp-text">${contactUs.email}</p>
					</div>
				</div>

				<div class="form-group ">
					<label class="control-label col-sm-3" for="contr_message">Message:</label>
					<div class="col-sm-9">
						<p class="form-control-static warp-text">${contactUs.message}</p>
					</div>

				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="contr_phone">Phone:</label>
					<div class="col-sm-9">
						<p class="form-control-static warp-text">${contactUs.phone}</p>
					</div>

				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<a href="${contextPath}/home.in" class="btn btn-default">OK</a>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>
