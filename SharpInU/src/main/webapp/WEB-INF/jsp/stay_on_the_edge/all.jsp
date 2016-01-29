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
	src='${contextPath}/assets/javascripts/stay_on_the_edge/all.js'></script>
<title>Stay On The Edge</title>
</head>
<body>
	<div id="content_edge">
		<aside id="about_up" class="page-title">
			<p>
				<b>Stay On The Edge</b>
			</p>
			<div id="search">
				<input type="text" placeholder="Search"> <label id="lookfor">
					<a href="#"><img src="${contextPath}/assets/images/search2.png" /></a>
				</label> </input>
			</div>
		</aside>
		<div class='sote-post col-lg-12'>
			<div id="edge_left">
				<c:forEach items="${stayOnEdgeBean.postBeans}" var="postBean">
					<div class="post-item">
						<a href="#"
							onclick="javascript:StayOTheEdge.All.removeOtherPostItems();StayOTheEdge.getPost(${postBean.postId});"><u>${postBean.summary}</u></a>
						<div id="edge_left_container">
							<p class="post-content">${postBean.content}</p>
						</div>
					</div>
				</c:forEach>
				<c:if test="${stayOnEdgeBean.hasMorePost}">
					<div class="edge_right_footer">
						<p class="blue">
							<a
								onclick="javascript:StayOTheEdge.loadMorePostsAll(this); return false;"
								href="#" offset="${offset}"><u>More Articles</u></a>
						</p>
					</div>
				</c:if>
			</div>
			<div class="edge_right">
				<div class="edge_right_up">

					<ul>
						<li><a href="${contextPath}/stay_on_the_edge/posts/1/list.in">Most
								Recent </a></li>
						<li><a href="${contextPath}/stay_on_the_edge.in">By
								Category </a></li>
						<li><a
							href="${contextPath}/stay_on_the_edge/categories/posts/1/list.in">All
						</a></li>

					</ul>
				</div>
				<div class="edge_right_down">
					<c:forEach items="${stayOnEdgeBean.categoryBeans}" var="category">
						<div class="edge-container">
							<div class="edge-text">
								<c:if test="${not empty category.postBeans}">
									<span class='category-name'>${category.categoryName}</span>
									<c:forEach items="${category.postBeans}" var="post">
										<p class='post-name'>
											<a
												href="javascript:StayOTheEdge.All.removeOtherPostItems();StayOTheEdge.getPost(${post.postId});"><u>${post.summary}</u></a>
										</p>
									</c:forEach>
									<c:if test="${category.hasMorePost}">
										<p class="blue">
											<a
												onclick="javascript:StayOTheEdge.loadMorePosts(${category.categoryId}, this); return false;"
												href="#" page="1"><u>More Articles</u></a>
										</p>
									</c:if>
								</c:if>
							</div>
						</div>
					</c:forEach>
				</div>

				<c:if test="${stayOnEdgeBean.hasMoreCategory}">
					<div class="edge_right_footer">
						<p class="blue">
							<a
								onclick="javascript:StayOTheEdge.loadMoreCategories(this); return false;"
								href="#"><u>More Categories</u></a>
						</p>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
