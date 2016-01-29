<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Career Form</title>
        <script type="text/javascript">
            $(document).ready(
                    function () {
                        $('form').get(0).reset(); //clear form data on page load
                        $(document).on('change', '.btn-file :file', function () {
                            var input = $(this),
                                    numFiles = input.get(0).files ? input.get(0).files.length : 1,
                                    label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
                            input.trigger('fileselect', [numFiles, label]);
                        });
                        $('.btn-file :file').on('fileselect', function (event, numFiles, label) {
                            var input = $(this).parents('.input-group').find(':text'),
                                    log = numFiles > 1 ? numFiles + ' files selected' : label;
                            if (input.length && log !== '') {
                                input.val(log);
                            } else {
                                if (log)
                                    alert(log);
                            }

                        });
                    });
        </script>
    </head>
    <body>
        <div id="content">
            <div class="row inner-content">
                <div class="form-header">
                    <h1>Career Asking</h1>
                </div>
                <div class="fom-content">
                    <div class="col-sm-4 form-content-left car-content-left">
                        <div class="col-sm-12 image-left">
                            <img src="${assetsPath}/images/twogirl.jpg" width="100%"/>
                        </div>
                        <div class="slogan-left  col-sm-offset-2 col-sm-10">
                            <span>
                                "....<br> 
Don't hesitate to come with us - sharing your wish, telling yourselves and moving forward with us."
</span>                         
                            <br>
                            <span class="spec-content"> -Sharp-In-U- </span>
                        </div>
                    </div>
                    <div class="col-sm-8 form-content-right car-content-right">
                        <form:form class="form-horizontal" role="form"
                                   action="${contextPath}/career_form.in" method="post"
                                   commandName="careerForm" accept-charset="UTF-8" enctype="multipart/form-data">
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}" />
                            <div class="form-group ">                                
                                <div class="col-sm-12">
                                    <form:input class="form-control" id="per_name" path="name"
                                                placeholder="Name *" />
                                    <spring:bind path="name">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>
                            </div>
                            <div class="form-group ">                                
                                <div class="col-sm-12">
                                    <form:input type="email" class="form-control" id="per_email"
                                                path="email" placeholder="Email *" />
                                    <spring:bind path="email">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>
                            </div>
                            <div class="form-group ">                                
                                <div class="col-sm-12">
                                    <form:input type="tel" class="form-control" path="phone"
                                                id="per_phone" placeholder="+84 909 123456" />
                                    <spring:bind path="phone">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>
                            </div>
                            <div class="form-group ">

                                <div class="col-sm-12">
                                     <form:textarea rows="3" class="form-control" id="per_situation"
                                                   path="situation" placeholder="Situation * 
Ex: I've graduated at University ABC, but not good at grade, and looking forward a QA job"/>
                                    <form:errors path="situation" cssClass="alert alert-error error" />
                                </div>
                            </div>
                            <div class="form-group required">

                                <div class="col-sm-12">
                                    <form:textarea rows="3" class="form-control" id="per_expectation"
                                                   path="expectation" placeholder="Expectation *
Ex: I want to get a QA job in next 1 year and become a QA Lead in next 3 years." />
                                    <spring:bind path="expectation">
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
                                                    type="file" name="additionInfoFile">
                                            </span>
                                        </span> <input type="text" class="form-control" placeholder="Additional Info" readonly>
                                    </div>

                                </div>

                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <button type="submit" class="btn btn-default">Submit</button>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
