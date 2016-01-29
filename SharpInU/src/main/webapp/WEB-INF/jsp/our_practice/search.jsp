<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>

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
<script type='text/javascript'
	src='${contextPath}/assets/javascripts/our_practice/search.js'></script>
<title>Searching Practices</title>
</head>
<body>
	<div id="content_edge">
		<aside id="about_up" class="page-title">
			<p>
				<b>Our Practices</b>
			</p>
			<div id="search">
				<input type="text" placeholder="Search" value='${term}'> <label
					id="lookfor"> <a href="#"><img
						src="${contextPath}/assets/images/search2.png" /></a>
				</label> </input>
			</div>
		</aside>
		<div class='sote-post col-lg-12'>
			<div id="edge_left">
				<div class="post-item">
					<h3>${ourPracticePageBean.ourPracticeBean.summary}</h3>
					<div id="edge_left_container">
						<p>${ourPracticePageBean.ourPracticeBean.content}</p>
					</div>
				</div>
			</div>
			<div class="edge_right">
				<div class="edge_right_up">
					<ul>
						<li><a
							href="${contextPath}/our_practice/our_practice/1/list.in">Most
								Recent</a></li>
						<li><a href="${contextPath}/our_practice.in">By Category
						</a></li>
						<li><a href="${contextPath}/our_practice/all/1/list.in">All
						</a></li>
					</ul>
				</div>
				<div class="edge_right_down">
					<c:if test="${empty search}">
						<c:url var="searchParam" value="" />
					</c:if>
					<c:if test="${not empty search}">
						<c:url var="searchParam" value="?search=${search}" />
					</c:if>

					<div class="edge-container">
						<div class="edge-text">
							<c:forEach items="${ourPracticePageBean.ourPracticeBeans}"
								var="ourPractice">
								<p class='post-name'>
									<a
										href="javascript:OurPractice.getOurPractice(${ourPractice.ourPracticeId});"><u>${ourPractice.summary}</u></a>
								</p>
							</c:forEach>
						</div>
					</div>

					<c:if test="${ourPracticePageBean.hasMoreOurPractice}">
						<div class="edge_right_footer">
							<p class="blue">
								<a
									onclick="javascript:OurPractice.Search.searchPractices('${term}', this); return false;"
									href="#" offset="${offset}"><u>More Practices</u></a>
							</p>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
