<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>

<html>
<head>
<link type="text/css" rel="stylesheet"
	href="${contextPath}/assets/stylesheets/trend.css">
<script type='text/javascript' src='${contextPath}/dwr/engine.js'></script>
<script type='text/javascript'
	src='${contextPath}/dwr/interface/DwrTrendService.js'></script>
<script type='text/javascript' src='${contextPath}/dwr/util.js'></script>
<script type='text/javascript'
	src='${contextPath}/assets/javascripts/trend/trend.js'></script>
<script type='text/javascript'
	src='${contextPath}/assets/javascripts/trend/search.js'></script>
<title>Searching Articles</title>
</head>
<body>
	<div id="content_edge">
		<aside id="about_up" class="page-title">
			<p>
				<b>Trends</b>
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
					<h3>${trendPageBean.trendBean.summary}</h3>
					<div id="edge_left_container">
						<p>${trendPageBean.trendBean.content}</p>
					</div>
				</div>
			</div>
			<div class="edge_right">
				<div class="edge_right_down">
					<div class="edge-container">
						<div class="edge-text">
							<c:forEach items="${trendPageBean.trendBeans}" var="trend">
								<p class="post-name">
									<a href="javascript:Trend.getTrend(${trend.trendId});"><u>${trend.summary}</u></a>
								</p>
							</c:forEach>
						</div>
					</div>
				</div>

				<c:if test="${trendPageBean.hasMoreTrend}">
					<div class="edge_right_footer">
						<p class="blue">
							<a
								onclick="javascript:Trend.searchTrends('${term}', this); return false;"
								href="#" offset="${offset}"><u>More Trends</u></a>
						</p>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
