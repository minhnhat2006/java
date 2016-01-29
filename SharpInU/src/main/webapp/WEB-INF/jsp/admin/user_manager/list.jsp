<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Users</title>
<script src='${contextPath}/assets/javascripts/account.js'
	type='text/javascript'></script>
</head>
<body class='contrast-red '>
	<div class='page-header'>
		<h1 class='pull-left'>
			<i class='icon-search'></i> <span>Users</span>
		</h1>
	</div>
	<c:forEach items="${users}" var="user">
		<div class='row-fluid'>
			<div class='span12 box'>
				<div class='box-content'>
					<h4>
						<a href="${contextPath}/admin/user/${user.userId}/review.in"
							class="text-contrast">${user.firstName} ${user.lastName}</a>
					</h4>
					<div class="row-fluid">
						<ul class="inline">
							<li><small>User Id: ${user.userId}</small></li>
							<li><small>|</small></li>
							<li><small>Email: ${user.userEmail}</small></li>
							<li><small>|</small></li>
							<li><small>Created Date :${user.dateCreated}</small></li>
							<li><small><a
									href="${contextPath}/admin/ticket/${user.userId}/1/list.in">View Tickets</a></small></li>
							<li><small><a
									href="${contextPath}/admin/user/${user.userId}/delete.in">Delete</a></small></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<div class='pagination pagination-centered'>
		<ul>
			<c:url var="prevUrl"
				value="/admin/user/${currentIndex - 1}/list.in" />
			<c:url var="nextUrl"
				value="/admin/user/${currentIndex + 1}/list.in" />
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
				<c:url var="pageUrl"
					value="/admin/user/${i}/list.in" />
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
