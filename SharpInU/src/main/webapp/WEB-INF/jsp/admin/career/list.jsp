<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp" %>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp" %>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Careers</title>
    <script src='${contextPath}/assets/javascripts/account.js' type='text/javascript'></script>
</head>
<body class='contrast-red '>
	<div class='page-header'>
		<h1 class='pull-left'>
			<i class='icon-search'></i> <span>Careers</span>
		</h1>
	</div>
	<c:forEach items="${careers}" var="career">
	<div class='row-fluid'>
		<div class='span12 box'>
			<div class='box-content'>
				<h4>
					<a href="${contextPath}/admin/career/${career.careerId}/view.in" class="text-contrast">${career.email}</a>
				</h4>
				<div class="row-fluid">
					<p>${career.name}</p>
				</div>
				<div class="row-fluid">
					<p>${career.situation}</p>
				</div>
				<div class="row-fluid">
					<p>${career.expectation}</p>
				</div>
				<c:if test="${not empty career.additionalInfo}">
					<div class="row-fluid">
						<p class="form-control-static">
							<a href="${contextPath}/admin/career/career_additionalFile.in?id=${career.careerId}&isEncrypt=false" target="_blank">Additional Info</a>
						</p>
					</div>
				</c:if>
				<div class="row-fluid">
					<ul class="inline">
						<li><small>Career Id: ${career.careerId}</small></li>
						<li><small>|</small></li>
						<li><small>Phone: ${career.phone}</small></li>
						<li><small>|</small></li>
						<li><small>Created Date :${career.createdDate}</small></li>
						<li><small><a href="${contextPath}/admin/career/${career.careerId}/delete.in">Delete</a></small></li>
						<c:choose>
						    <c:when test="${career.ticket == null}">
						        <li><small><a href="${contextPath}/admin/ticket/career/${career.careerId}/add.in">Enhance Ticket</a></small></li>
						    </c:when>    
						    <c:otherwise>
						        <li><small><a href="${contextPath}/admin/ticket/${career.ticket.ticketId}/review.in">Enhance Ticket</a></small></li>
						    </c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>
	</div>
	</c:forEach>
	<div class='pagination pagination-centered'>
		<ul>
		<c:url var="prevUrl" value="/admin/career/${currentIndex - 1}/list.in" />
		<c:url var="nextUrl" value="/admin/career/${currentIndex + 1}/list.in" />
			<c:choose>
				<c:when test="${currentIndex == 1}">
					<li class="disabled"><a href="#"><i class='icon-chevron-left'></i></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${prevUrl}"><i class='icon-chevron-left'></i></a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
				<c:url var="pageUrl" value="/admin/career/${i}/list.in" />
				<c:choose>
					<c:when test="${i == currentIndex}">
						<li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${currentIndex == page.totalPages}">
					<li class="disabled"><a href="#"><i class='icon-chevron-right'></i></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${nextUrl}"><i class='icon-chevron-right'></i></a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</body>
</html>
