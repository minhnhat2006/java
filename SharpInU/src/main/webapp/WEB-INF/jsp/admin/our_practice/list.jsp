<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Our Practices</title>
<script src='${contextPath}/assets/javascripts/admin/our_practices.js'
	type='text/javascript'></script>
</head>
<body class='contrast-red '>
	<div class='page-header'>
		<h1 class='pull-left'>
			<i class='icon-search'></i> <span>Our Practices</span>
		</h1>
	</div>
	<c:forEach items="${ourPractices}" var="ourPractice">
		<div class='row-fluid'>
			<div class='span12 box'>
				<div class='box-content'>
					<h4>
						<a href="${contextPath}/admin/our_practice/${ourPractice.ourPracticeId}/review.in"
							class="text-contrast">${ourPractice.summary}</a>
					</h4>
					<div class="row-fluid limit-content-post">
						<a href='#' class='read-more'>Read More</a>
						<p>${ourPractice.content}</p>
					</div>
					<div class="row-fluid">
						<ul class="inline">
							<li><small>Our Practice Id: ${ourPractice.ourPracticeId}</small></li>
							<li><small>|</small></li>
							<li><small>Category: ${ourPractice.ourPracticeCategoryName}</small></li>
							<li><small>|</small></li>
							<li><small>Author :${ourPractice.userEmail}</small></li>
							<li><small>|</small></li>
							<li><small>Created Date :${ourPractice.createdDate}</small></li>
							<li><small>|</small></li>
							<li><small>Updated Date :${ourPractice.updatedDate}</small></li>
							<li><small><a
									href="${contextPath}/admin/our_practice/${ourPractice.ourPracticeId}/edit.in">Edit</a></small></li>
							<li><small><a
									href="${contextPath}/admin/our_practice/${ourPractice.ourPracticeId}/delete.in">Delete</a></small></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<div class='pagination pagination-centered'>
		<ul>
			<c:url var="prevUrl" value="/admin/our_practice/${currentIndex - 1}/list.in" />
			<c:url var="nextUrl" value="/admin/our_practice/${currentIndex + 1}/list.in" />
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
				<c:url var="pageUrl" value="/admin/our_practice/${i}/list.in" />
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
