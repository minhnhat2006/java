<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Sign-up Sharp In U</title>
<script type="text/javascript">
	setTimeout(function() {
		window.location = "${contextPath}/home.in";
	}, 3000);
</script>
<script src='${contextPath}/assets/javascripts/account.js'
	type='text/javascript'></script>
</head>
<body class='contrast-red sign-up contrast-background'>
	<div id="content" style='min-height: 0;'>
		<div class="row inner-content">
			<div class="form-header" style='border-bottom: 0;'>
				<h4 style='text-align: center;'>
					<span class="text-contrast">Thanks for joining Sharp In U.</span><br>Your
					account has been created!
				</h4>
			</div>
		</div>
	</div>
</body>
</html>
