<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Resume Form</title>
        <script type="text/javascript" src="${assetsPath}/javascripts/resume.js"></script>
    </head>
    <body>
        <div id="content">
            <div class="row inner-content resume-content">
                <div class="form-header">
                    <h1>Resume</h1>
                </div>
                <div class="fom-content">
                    <div class="col-sm-5 form-content-left res-content-left">
                        <div class="col-sm-12 res-image-left">
                            <img src="${assetsPath}/images/resum_bg.png" width="100%">
                            </div>
                        <div class="col-sm-12 res-slogan-left">
                            <span>"Have you spotted a job that you want? Do you believe in your CV is attracting employers? If you still concern on your CV, why don't you send it to us to get a new look CV."</span>
                            <br>
                            <span class="res-spec-content">- Sharp-In-U-</span>
                        </div>
                    </div>
                    <div class="col-sm-7 form-content-right">
                        <form:form accept-charset="UTF-8"
                                   action="${contextPath}/resume_form/post.in" class="form-horizontal"
                                   role="form" enctype="multipart/form-data" commandName="resumeForm"
                                   method="post">
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}" />
                            <div class="form-group">                                
                                <div class="col-sm-12">
                                    <form:input class='form-control' id='res_name'
                                                placeholder='Name *' path="name" />
                                    <spring:bind path="name">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>

                            </div>

                            <div class="form-group required">
                                <div class="col-sm-5">
                                    <form:input type='tel' class='form-control' id='res_phone'
                                                placeholder='Phone' path="phone"/>
                                    <spring:bind path="phone">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>
                                <div class="col-sm-7">
                                    <form:input class='form-control' id='res_address'
                                                placeholder='Address' path="address" />
                                    <spring:bind path="address">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>

                            </div>

                            <div class="form-group">

                                <div class="col-sm-12">
                                    <form:input class='form-control' id='res_current_title'
                                                placeholder="Current job/ Title" path="currentTitle" />
                                    <spring:bind path="currentTitle">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>

                            </div>
                            <div class="form-group required">                                
                                <div class="col-sm-12">
                                    <form:textarea class='form-control' id='res_objective'
                                                   placeholder='Objectives *' rows='3' path="objectives" />
                                    <spring:bind path="objectives">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>

                            </div>
                            <div class="form-group required">

                                <div class="col-sm-12">
                                    <form:textarea class='form-control' id='res_summary'
                                                   placeholder='Summary Skill' rows='3' path="summarySkill" />
                                    <spring:bind path="summarySkill">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>

                            </div>
                            <div class="form-group">                                
                                <div class="col-sm-12">
                                    <form:textarea class='form-control' id='res_experience'
                                                placeholder='Working Experience' rows='4' path="experience" />
                                    <spring:bind path="experience">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>

                            </div>
                            <div class="form-group required">
                                
                                <div class="col-sm-12">
                                    <form:input class='form-control' id='res_achievement'
                                                placeholder="Achievement" path="achievement" />
                                    <spring:bind path="achievement">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                
                                <div class="col-sm-12">
                                    <form:textarea class='form-control' id='res_education' row="3" placeholder='Education'
                                                path="education" />
                                    <spring:bind path="education">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>

                            </div>
                            
                            

                            
                            <div class="form-group">                                
                                <div class="col-sm-12">
                                    <div class="input-group">
                                        <span class="input-group-btn"> <span
                                                class="btn btn-primary btn-file"> Browse&hellip; <input
                                                    type="file" name="cvAttachment">
                                            </span>
                                        </span> <input type="text" class="form-control" placeholder="Current CV *" readonly>
                                    </div>
                                    <spring:bind path="cvAttachment">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>

                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <button type="submit" class="btn btn-default">Submit</button>
                                </div>
                            </div>
                            <!-- <div class="res-photo">
                                 <span class="des-photo">Upload Photo</span> <input type="file"
                                                                                    name="uploadPhoto" accept="image/*">
                             </div>

                            <form:errors path="uploadPhoto" cssClass="help-block error"/>-->
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
