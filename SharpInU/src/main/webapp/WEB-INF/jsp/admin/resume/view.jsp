<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <title>View Resume</title>
        <script type="text/javascript" src="${assetsPath}/javascripts/resume.js"></script>

    </head>
    <body>
        <div id="content">
            <div class="row inner-content resume-content">
                <div class="form-header">
                    <h1>Resume</h1>
                </div>

                <form class="form-horizontal" role="form"></form>
            </div>
        </div>
        <div class='row-fluid'>
            <div class='span12 box'>
                <div class='box-content box-padding'>
                    <div class='fuelux'>
                        <div class="step-content">
                            <form:form accept-charset="UTF-8"
                                       action="${contextPath}/admin/resume/add.in"
                                       class="form form-striped" method="get" commandName="companyForm">
                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}" />
                                <div class='box'>
                                    <div class="box-header">
                                        <div class="title">Name</div>
                                    </div>
                                    <div class="box-content">
                                        <div class="row-fluid">${resume.name}</div>
                                    </div>
                                    <div class="box-header">
                                        <div class="title">Phone:</div>
                                    </div>
                                    <div class="box-content">
                                        <div class="row-fluid">${resume.phone}</div>
                                    </div>
                                    <div class="box-header">
                                        <div class="title">Address:</div>
                                    </div>
                                    <div class="box-content">
                                        <div class="row-fluid">${resume.address}</div>
                                    </div>
                                    <div class="box-header">
                                        <div class="title">Current Title:</div>
                                    </div>
                                    <div class="box-content">
                                        <div class="row-fluid">${resume.currentTitle}</div>
                                    </div>
                                    <div class="box-header">
                                        <div class="title">Objectives:</div>
                                    </div>
                                    <div class="box-content">
                                        <div class="row-fluid">${resume.objectives}</div>
                                    </div>
                                    <div class="box-header">
                                        <div class="title">Summary Skill:</div>
                                    </div>
                                    <div class="box-content">
                                        <div class="row-fluid">${resume.summarySkill}</div>
                                    </div>
                                    <div class="box-header">
                                        <div class="title">Working Experience:</div>
                                    </div>
                                    <div class="box-content">
                                        <div class="row-fluid">${resume.experience}</div>
                                    </div>
                                    <div class="box-header">
                                        <div class="title">Achievement:</div>
                                    </div>
                                    <div class="box-content">
                                        <div class="row-fluid">${resume.achievement}</div>
                                    </div>
                                    <div class="box-header">
                                        <div class="title">Education:</div>
                                    </div>
                                    <div class="box-content">
                                        <div class="row-fluid">${resume.education}</div>
                                    </div>
                                    <div class="box-header">
                                        <div class="title">Attachment</div>
                                    </div>
                                    <div class="box-content">
                                        <div class="row-fluid">
                                            <p class="form-control-static">
                                                <a
                                                    href="${contextPath}/admin/resume/resume_cv.in?id=${idEncrypt}"
                                                    target="_blank">Your CV</a>
                                            </p>
                                        </div>
                                    </div>
                                    <%-- <div class="box-header">
                                            <div class="title">Your Photo</div>
                                    </div>
                                    <div class="box-content">
                                            <div class="row-fluid">
                                                    <div class="photo-uploaded"
                                                            style="background-image: url('${contextPath}/admin/resume/resume_image.in?id=${idEncrypt}')">
                                                    </div>
                                            </div>
                                    </div> --%>
                                </div>

                                <div class='box'>
                                    <hr class="hr-normal">
                                    <div class="text-right">
                                    <c:choose>
									    <c:when test="${resume.ticket == null}">
									        <a class="btn btn-primary btn-large" href="${contextPath}/admin/ticket/resume/${resume.resumeId}/add.in">Enhance Ticket</a>
									    </c:when>    
									    <c:otherwise>
									        <a class="btn btn-primary btn-large" href="${contextPath}/admin/ticket/${resume.ticket.ticketId}/review.in">Enhance Ticket</a>
									    </c:otherwise>
									</c:choose>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
