<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <title>View Career</title>
    </head>
    <body class='contrast-red'>
        <div class='row-fluid'>
            <div class='span12'>
                <div class='page-header'>
                    <h1 class='pull-left'>
                        <i class='icon-edit'></i> <span>View Career</span>
                    </h1>
                    <div class='pull-right'>
                        <ul class='breadcrumb'>
                            <li><a href="/admin"><i class='icon-bar-chart'></i> </a></li>
                            <li class='separator'><i class='icon-angle-right'></i></li>
                            <li>Career</li>
                            <li class='separator'><i class='icon-angle-right'></i></li>
                            <li class='active'>View</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class='row-fluid'>
            <div class='span12 box'>
                <div class='box-content box-padding'>
                    <div class='fuelux'>
                        <div class="step-content">
                            <form:form accept-charset="UTF-8"
                                       action="${contextPath}/admin/career/add.in"
                                       class="form form-striped" method="get" commandName="careerForm">
                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}" />
                                <div class='box'>
                                    <div class="box-header">
                                        <div class="title">Name</div>
                                    </div>
                                    <div class="box-content">
                                        <div class="row-fluid">${career.name}</div>
                                    </div>
                                    <div class="box-header">
                                        <div class="title">Phone</div>
                                    </div>
                                    <div class="box-content">
                                        <div class="row-fluid">${career.phone}</div>
                                    </div>
                                    <div class="box-header">
                                        <div class="title">Email</div>
                                    </div>
                                    <div class="box-content">
                                        <div class="row-fluid">${career.email}</div>
                                    </div>
                                    <div class="box-header">
                                        <div class="title">Created Date</div>
                                    </div>
                                    <div class="box-content">
                                        <div class="row-fluid">${career.createdDate}</div>
                                    </div>
                                    <div class="box-header">
                                        <div class="title">Additional Info</div>
                                    </div>
                                    <div class="box-content">
                                        <div class="row-fluid">
                                            <p class="form-control-static">
                                                <c:if test="${not empty career.additionalInfo}">
                                                    <a
                                                        href="${contextPath}/admin/career/career_additionalFile.in?id=${idEncrypt}"
                                                        target="_blank">Attachment</a>
                                                </c:if>
                                            </p>
                                        </div>
                                    </div>
                                </div>

                                <div class='box'>
                                    <hr class="hr-normal">
                                    <div class="text-right">
                                    <c:choose>
									    <c:when test="${career.ticket == null}">
									        <a class="btn btn-primary btn-large" href="${contextPath}/admin/ticket/career/${career.careerId}/add.in">Enhance Ticket</a>
									    </c:when>    
									    <c:otherwise>
									        <a class="btn btn-primary btn-large" href="${contextPath}/admin/ticket/${career.ticket.ticketId}/review.in">Enhance Ticket</a>
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
