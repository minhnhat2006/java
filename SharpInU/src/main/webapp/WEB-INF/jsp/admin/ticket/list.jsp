<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Tickets</title>
<script src='${contextPath}/assets/javascripts/admin/ticket.js'
	type='text/javascript'></script>
</head>
<body class='contrast-red '>
	<div class='page-header'>
		<h1 class='pull-left'>
			<i class='icon-search'></i> <span>Tickets of user
				${user.firstName} ${user.lastName}</span>
		</h1>
	</div>
	<c:forEach items="${tickets}" var="ticket">
		<div class='row-fluid'>
			<div class='span12 box'>
				<div class='box-content'>
					<h4>
						<a href="${contextPath}/admin/ticket/${ticket.ticketId}/review.in"
							class="text-contrast">${ticket.title}</a>
					</h4>
					<div class="row-fluid limit-content-post">
						<a href='#' class='read-more'>Read More</a>
						<p>${ticket.content}</p>
					</div>
					<div class="row-fluid">
						<ul class="inline">
							<li><small>Ticket Id: ${ticket.ticketId}</small></li>
							<li><small>|</small></li>
							<c:if test="${ticket.status == 0}">
								<li><small>Status: Open</small></li>
							</c:if>
							<c:if test="${ticket.status == 1}">
								<li><small>Status: Closed</small></li>
							</c:if>
							<li><small>|</small></li>
							<li><small>Created Date :${ticket.createdDate}</small></li>
							<li><small><a
									href="${contextPath}/admin/ticket/${ticket.ticketId}/edit.in">Edit</a></small></li>
							<li><small><a
									href="${contextPath}/admin/ticket/${ticket.ticketId}/delete.in">Delete</a></small></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<div class='pagination pagination-centered'>
		<ul>
			<c:url var="prevUrl"
				value="/admin/ticket/${currentIndex - 1}/list.in" />
			<c:url var="nextUrl"
				value="/admin/ticket/${currentIndex + 1}/list.in" />
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
				<c:url var="pageUrl" value="/admin/ticket/${i}/list.in" />
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
				<c:when test="${currentIndex == totalPages}">
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
