<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Company Form</title>
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
                    <h1>Company Information</h1>
                </div>
                <div class="fom-content">
                    <div class="col-sm-4 form-content-left com-content-left">
                        <div class="col-sm-12 image-left">
                            <img src="${assetsPath}/images/man.png" width="100%"/>
                        </div>
                        <div class="slogan-left  col-sm-12">
                            <span>
                                "Is your organization struggling with product quality and need to level up your testing? Or you want to establish a new strategy where your testing would be faster, less expensive and more efficient? Let email us your story, we can tell you what we can help."
                            </span>                         
                            <br>
                            <span class="spec-content"> -Sharp-In-U- </span>
                        </div>
                    </div>
                    <div class="col-sm-8 form-content-right car-content-right">   
                        <form:form class="form-horizontal" role="form"
                                   action="${contextPath}/company_form.in" method="post"
                                   commandName="companyForm" accept-charset="UTF-8" enctype="multipart/form-data">
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}" />
                            <div class="form-group ">
                                
                                <div class="col-sm-12">
                                    <form:textarea class="form-control" id="comp_info" path="info" rows="1"
                                                   placeholder="Info *" />
                                    <spring:bind path="info">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>
                            </div>
                            <div class="form-group required">                                
                                <div class="col-sm-12">
                                    <form:input type='url' class="form-control" id="comp_site"
                                                path="website" placeholder="Website *" maxlength="255"/>
                                    <spring:bind path="website">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>

                                </div>
                            </div>
                            <div class="form-group required">
                                
                                <div class="col-sm-12">
                                    <form:input type="email" class="form-control" id="comp_email"
                                                path="email" placeholder="Email *" maxlength="255"/>
                                    <spring:bind path="email">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>

                                </div>
                            </div>
                            <div class="form-group required">                                
                                <div class="col-sm-12">
                                    <form:input type="tel" class="form-control" path="phone"
                                                id="comp_phone" placeholder="+84 909 123456" />
                                    <spring:bind path="phone">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>
                            </div>
                            <div class="form-group required">                                
                                <div class="col-sm-12">
                                    <form:textarea class="form-control" id="comp_situation"
                                                   path="situation" placeholder="Situation *
Our company get the issue with production quality, >30% leakage defect." />
                                    <spring:bind path="situation">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>

                                </div>
                            </div>
                            <div class="form-group required">
                                
                                <div class="col-sm-12">
                                    <form:textarea class="form-control" id="comp_expectation"
                                                   path="expectation" placeholder="Expectation *
We looking for solution to reduce leakage defect to under 15%" />
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
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
