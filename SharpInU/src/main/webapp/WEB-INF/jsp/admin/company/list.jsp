<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp" %>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp" %>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Companies</title>
    <script src='${contextPath}/assets/javascripts/account.js' type='text/javascript'></script>
</head>
<body class='contrast-red '>
	<div class='page-header'>
		<h1 class='pull-left'>
			<i class='icon-search'></i> <span>Companies</span>
		</h1>
	</div>
	<c:forEach items="${companies}" var="company">
	<div class='row-fluid'>
		<div class='span12 box'>
			<div class='box-content'>
				<h4>
					<a href="${contextPath}/admin/company/${company.companyId}/view.in" class="text-contrast">${company.website}</a>
				</h4>
				<div class="row-fluid">
					<p>${company.info}</p>
				</div>
				<div class="row-fluid">
					<p>${company.situation}</p>
				</div>
				<div class="row-fluid">
					<p>${company.expectation}</p>
				</div>
				<div class="row-fluid">
					<ul class="inline">
						<li><small>Company Id: ${company.companyId}</small></li>
						<li><small>|</small></li>
						<li><small>Email: ${company.email}</small></li>
						<li><small>|</small></li>
						<li><small>Phone :${company.phone}</small></li>
						<li><small>|</small></li>
						<li><small>Created Date :${company.createdDate}</small></li>
						<li><small><a href="${contextPath}/admin/company/${company.companyId}/delete.in">Delete</a></small></li>
						<c:choose>
						    <c:when test="${company.ticket == null}">
						        <li><small><a href="${contextPath}/admin/ticket/company/${company.companyId}/add.in">Enhance Ticket</a></small></li>
						    </c:when>    
						    <c:otherwise>
						        <li><small><a href="${contextPath}/admin/ticket/${company.ticket.ticketId}/review.in">Enhance Ticket</a></small></li>
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
		<c:url var="prevUrl" value="/admin/company/${currentIndex - 1}/list.in" />
		<c:url var="nextUrl" value="/admin/company/${currentIndex + 1}/list.in" />
			<c:choose>
				<c:when test="${currentIndex == 1}">
					<li class="disabled"><a href="#"><i class='icon-chevron-left'></i></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${prevUrl}"><i class='icon-chevron-left'></i></a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
				<c:url var="pageUrl" value="/admin/company/${i}/list.in" />
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
