<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Company Form</title>
<script type="text/javascript">
	$(document).ready(
			function() {
				$(document).keydown(
						function(e) {
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
				<h1>Company Information</h1>
			</div>
			<c:if test="${not empty successMsg}">
				<div class="alert alert-success alert-dismissable">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					${successMsg}
				</div>
			</c:if>
			<form class="form-horizontal" role="form">
				<div class="form-group">
					<label class="control-label col-sm-3" for="comp_site">Info:</label>
					<div class="col-sm-9">
						<p class="form-control-static warp-text">${company.info}</p>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-3" for="comp_site">Website:</label>
					<div class="col-sm-9">
						<p class="form-control-static warp-text">${company.website}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="comp_email">Email:</label>
					<div class="col-sm-9">
						<p class="form-control-static warp-text">${company.email}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="comp_phone">Phone:</label>
					<div class="col-sm-9">
						<p class="form-control-static warp-text">${company.phone}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="comp_situation">Situation:</label>
					<div class="col-sm-9">
						<p class="form-control-static warp-text">${company.situation}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="comp_expectation">Your
						expectation:</label>
					<div class="col-sm-9">
						<p class="form-control-static warp-text">${company.expectation}</p>
					</div>
				</div>
				<c:if test="${not empty company.additionalInfo}">
					<div class="form-group">
						<label class="control-label col-sm-3" for="res_additional">Additional Info:</label>
						<div class="col-sm-9">
							<p class="form-control-static">
								<a href="${contextPath}/company/additionalFile.in?id=${idEncrypt}" target="_blank">Your attachment</a>
							</p>
						</div>
					</div>
				</c:if>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<a href="${contextPath}/home.in" class="btn btn-default">OK</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
