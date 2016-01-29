<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>

<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="${contextPath}/assets/stylesheets/our_practices.css">
<link type="text/css" rel="stylesheet"
	href="${contextPath}/assets/stylesheets/post.css">
<script type='text/javascript' src='${contextPath}/dwr/engine.js'></script>
<script type='text/javascript'
	src='${contextPath}/dwr/interface/DwrOurPracticeService.js'></script>
<script type='text/javascript' src='${contextPath}/dwr/util.js'></script>
<script type='text/javascript'
	src='${contextPath}/assets/javascripts/our_practice/our_practices.js'></script>
<title>Community</title>
</head>

<body>
	<div id="content_edge">
		<aside id="about_up" class="page-title">
			<p>
				<b>${ourPractice.summary}</b>
			</p>
		</aside>

		<div class='col-lg-12 row'>
			<p>${ourPractice.content}</p>
		</div>

	</div>
</body>
</html>
