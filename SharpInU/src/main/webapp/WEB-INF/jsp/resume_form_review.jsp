<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Resume Form</title>
<script type="text/javascript" src="${assetsPath}/javascripts/resume.js"></script>
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
		<div class="row inner-content resume-content">
			<div class="form-header">
				<h1>Resume</h1>
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
					<label class="control-label col-sm-3" for="res_name">Name:</label>

					<div class="col-sm-6">
						<div>
							<p class="form-control-static warp-text">${resume.name}</p>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="res_name">Phone:</label>

					<div class="col-sm-6">
						<div>
							<p class="form-control-static warp-text">${resume.phone}</p>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="res_name">Address:</label>

					<div class="col-sm-6">
						<div>
							<p class="form-control-static warp-text">${resume.address}</p>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="res_title">Current
						Job/Title:</label>
					<div class="col-sm-6">
						<p class="form-control-static warp-text">${resume.currentTitle}
						<p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="res_objective">Objectives:</label>
					<div class="col-sm-9">
						<p class="form-control-static warp-text">${resume.objectives}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="res_summary">Summary
						Skill:</label>
					<div class="col-sm-6">
						<p class="form-control-static warp-text">${resume.summarySkill}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="res_experience">Working Experience:</label>
					<div class="col-sm-9">
						<p class="form-control-static warp-text">${resume.experience}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="res_title">Achievement:</label>
					<div class="col-sm-6">
						<p class="form-control-static warp-text">${resume.achievement}
						<p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="res_education">Education:</label>
					<div class="col-sm-9">
						<p class="form-control-static warp-text">${resume.education}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="res_cv">CV
						Attachment:</label>
					<div class="col-sm-9">
						<p class="form-control-static">
							<a href="${contextPath}/resume_cv.in?id=${idEncrypt}" target="_blank">Your CV</a>
						</p>
					</div>
				</div>
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
