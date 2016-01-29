<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>

<html>
<head>
<link type="text/css" rel="stylesheet"
	href="${contextPath}/assets/stylesheets/stay-on-edge.css">
<link type="text/css" rel="stylesheet"
	href="${contextPath}/assets/stylesheets/post.css">
<script type='text/javascript' src='${contextPath}/dwr/engine.js'></script>
<script type='text/javascript'
	src='${contextPath}/dwr/interface/DwrStayOnEdgeService.js'></script>
<script type='text/javascript' src='${contextPath}/dwr/util.js'></script>
<script type='text/javascript'
	src='${contextPath}/assets/javascripts/stay_on_the_edge/stay_on_the_edge.js'></script>
<script type='text/javascript'
	src='${contextPath}/assets/javascripts/stay_on_the_edge/search.js'></script>
<title>Searching Articles</title>
</head>
<body>
	<div id="content_edge">
		<aside id="about_up" class="page-title">
			<p>
				<b>Stay On The Edge</b>
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
				    <c:choose>
					   <c:when test="${stayOnEdgeBean.postBean != null}">
					       <h3>${stayOnEdgeBean.postBean.summary}</h3>
							<div id="edge_left_container">
								<p>${stayOnEdgeBean.postBean.content}</p>
							</div>
					   </c:when>    
					   <c:otherwise>
					       <h4>No records found.</h4>
					   </c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="edge_right">
				<div class="edge_right_up">
					<ul>
						<li><a href="${contextPath}/stay_on_the_edge/posts/1/list.in">Most
								Recent</a></li>
						<li><a href="${contextPath}/stay_on_the_edge.in">By
								Category </a></li>
						<li><a
							href="${contextPath}/stay_on_the_edge/categories/posts/1/list.in">All
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
						<c:if test="${stayOnEdgeBean.postBeans != null}">
							<c:forEach items="${stayOnEdgeBean.postBeans}" var="post">
								<p class='post-name'>
									<a href="javascript:StayOTheEdge.getPost(${post.postId});"><u>${post.summary}</u></a>
								</p>
							</c:forEach>
						</c:if>
						</div>
					</div>
				</div>

				<c:if test="${stayOnEdgeBean.hasMorePost}">
					<div class="edge_right_footer">
						<p class="blue">
							<a
								onclick="javascript:StayOTheEdge.Search.searchPosts('${term}', this); return false;"
								href="#" offset="${offset}"><u>More Articles</u></a>
						</p>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
