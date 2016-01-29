<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Trends</title>
<script src='${contextPath}/assets/javascripts/admin/trend.js'
	type='text/javascript'></script>
</head>
<body class='contrast-red '>
	<div class='page-header'>
		<h1 class='pull-left'>
			<i class='icon-search'></i> <span>Trends</span>
		</h1>
	</div>
	<c:forEach items="${trends}" var="trend">
		<div class='row-fluid'>
			<div class='span12 box'>
				<div class='box-content'>
					<h4>
						<a href="${contextPath}/admin/trend/${trend.trendId}/review.in"
							class="text-contrast">${trend.summary}</a>
					</h4>
					<div class="row-fluid limit-content-post">
						<a href='#' class='read-more'>Read More</a>
						<p>${trend.content}</p>
					</div>
					<div class="row-fluid">
						<ul class="inline">
							<li><small>Trend Id: ${trend.trendId}</small></li>
							<li><small>|</small></li>
							<li><small>Author :${trend.userEmail}</small></li>
							<li><small>|</small></li>
							<li><small>Created Date :${trend.createdDate}</small></li>
							<li><small>|</small></li>
							<li><small>Updated Date :${trend.updatedDate}</small></li>
							<li><small><a
									href="${contextPath}/admin/trend/${trend.trendId}/edit.in">Edit</a></small></li>
							<li><small><a
									href="${contextPath}/admin/trend/${trend.trendId}/delete.in">Delete</a></small></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<div class='pagination pagination-centered'>
		<ul>
			<c:url var="prevUrl" value="/admin/trend/${currentIndex - 1}/list.in" />
			<c:url var="nextUrl" value="/admin/trend/${currentIndex + 1}/list.in" />
			<c:choose>
				<c:when test="${currentIndex == 1}">
					<li class="disabled"><a href="#"><i
							class='icon-chevron-left'></i></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${prevUrl}"><i class='icon-chevron-left'></i></a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
				<c:url var="pageUrl" value="/admin/trend/${i}/list.in" />
				<c:choose>
					<c:when test="${i == currentIndex}">
						<li class="active"><a href="${pageUrl}"><c:out
									value="${i}" /></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${currentIndex == page.totalPages}">
					<li class="disabled"><a href="#"><i
							class='icon-chevron-right'></i></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${nextUrl}"><i class='icon-chevron-right'></i></a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</body>
</html>
