<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp" %>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp" %>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Resumes</title>
    <script src='${contextPath}/assets/javascripts/account.js' type='text/javascript'></script>
</head>
<body class='contrast-red '>
	<div class='page-header'>
		<h1 class='pull-left'>
			<i class='icon-search'></i> <span>Resumes</span>
		</h1>
	</div>
	<c:forEach items="${resumes}" var="resume">
	<div class='row-fluid'>
		<div class='span12 box'>
			<div class='box-content'>
				<h4>
					<a href="${contextPath}/admin/resume/${resume.resumeId}/view.in" class="text-contrast">${resume.name}</a>
				</h4>
				<div class="row-fluid">
					<p>${resume.summarySkill}</p>
				</div>
				<div class="row-fluid">
					<p>${resume.objectives}</p>
				</div>
				<div class="row-fluid">
					<p>${resume.experience}</p>
				</div>
				<div class="row-fluid">
					<p>${resume.education}</p>
				</div>
				<div class="row-fluid">
					<p>${resume.address}</p>
				</div>
				<div class="row-fluid">
					<p>${resume.phone}</p>
				</div>
				<div class="row-fluid">
					<p>${resume.achievement}</p>
				</div>
				<div class="row-fluid">
					<p class="form-control-static">
						<a href="${contextPath}/admin/resume/resume_cv.in?id=${resume.resumeId}&isEncrypt=false" target="_blank">Your CV</a>
					</p>
				</div>
			<%-- 	<div class="row-fluid">
					<div class="photo-uploaded"
						style="background-image: url('${contextPath}/admin/resume_image.in?id=${resume.resumeId}&isEncrypt=false')">
					</div> 
				</div>--%>
				<div class="row-fluid">
					<ul class="inline">
						<li><small>Current Title: ${resume.currentTitle}</small></li>
						<li><small>|</small></li>
						<li><small>Created Date :${resume.createdDate}</small></li>
						<li><small><a href="${contextPath}/admin/resume/${resume.resumeId}/delete.in">Delete</a></small></li>
						<c:choose>
						    <c:when test="${resume.ticket == null}">
						        <li><small><a href="${contextPath}/admin/ticket/resume/${resume.resumeId}/add.in">Enhance Ticket</a></small></li>
						    </c:when>    
						    <c:otherwise>
						        <li><small><a href="${contextPath}/admin/ticket/${resume.ticket.ticketId}/review.in">Enhance Ticket</a></small></li>
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
		<c:url var="prevUrl" value="/admin/resume/${currentIndex - 1}/list.in" />
		<c:url var="nextUrl" value="/admin/resume/${currentIndex + 1}/list.in" />
			<c:choose>
				<c:when test="${currentIndex == 1}">
					<li class="disabled"><a href="#"><i class='icon-chevron-left'></i></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${prevUrl}"><i class='icon-chevron-left'></i></a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
				<c:url var="pageUrl" value="/admin/resume/${i}/list.in" />
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
