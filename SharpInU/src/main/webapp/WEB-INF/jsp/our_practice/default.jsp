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
		<!-- 		<aside id="about_up" class="page-title"> -->
		<!-- 			<p> -->
		<!-- 				<b>Community</b> -->
		<!-- 			</p> -->
		<!-- 			<div id="search"> -->
		<!-- 				<input type="text" placeholder="Search" /> <label id="lookfor"> -->
		<%-- 					<a href="#"><img src="${contextPath}/assets/images/search2.png" /></a> --%>
		<!-- 				</label> -->
		<!-- 			</div> -->
		<!-- 		</aside> -->

		<div class='col-lg-12'>
			<c:forEach items="${ourPracticePageBean.ourPracticeBeans}"
				var="ourPractice">

				<div class='item-article'>
					<div class='ia-image'>
						<img src='${contextPath}/assets/images/community/p_avatar.png' />
					</div>
					<div class='ia-summary'>
						<div class='ias-container'>
							<div class='ias-title'>
								<h4>
									<b>${ourPractice.summary}</b>
								</h4>
							</div>
							<div class='ias-text'>
								${ourPractice.content}
							</div>
							<a href='/our_practice/${ourPractice.slug}/view.in'>>
								Explore more.</a>
						</div>
					</div>
				</div>
			</c:forEach>

			<c:if test="${ourPracticePageBean.hasMoreOurPractice}">
				<p class="blue">
					<a
						onclick="javascript:OurPractice.loadMorePostsMostRecent(this); return false;"
						href="" offset="${offset}">> More Programs.</a>
				</p>
			</c:if>
		</div>

	</div>
</body>
</html>
