<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
    </head>
    <body>
        <div id="content" class="row signup-form">
            <div class="inner-content-left col-sm-7">
                <div class="content-right row">
                    <div class="form-header col-sm-8 col-sm-offset-1">
                        <h1>Register Information</h1>
                    </div>
                    <div class="form-container col-sm-8 col-sm-offset-1">
                        <form:form class="form-horizontal" role="form"
                                   action="${contextPath}/sec/sign_up.in" method="post"
                                   commandName="userSignUpForm">
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}" />
                            <div class="form-group required">

                                <div class="col-sm-6">
                                    <form:input class="form-control" id="first_name"
                                                path="firstName" placeholder="First name *" />
                                    </td>
                                    <spring:bind path="firstName">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>
                                <div class="col-sm-6">
                                    <form:input type="text" class="form-control" path="lastName"
                                                id="last_name" placeholder="Last name *" />
                                    <spring:bind path="lastName">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>
                            </div>

                            <div class="form-group required">
                                
                                <div class="col-sm-12">
                                    <form:input type="email" class="form-control" id="email"
                                                path="email" placeholder="Email address *" />
                                    <spring:bind path="email">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>
                            </div>
                            <div class="form-group required">
                                
                                <div class="col-sm-12">
                                    <form:password class="form-control" id="password" path="password"
                                                   placeholder="Password *" />
                                    <spring:bind path="password">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>
                            </div>
                            <div class="form-group required">
                                
                                <div class="col-sm-12">
                                    <form:password class="form-control" id="confirm_password"
                                                   path="confirmPassword" placeholder="Confirm password *" />
                                    <spring:bind path="confirmPassword">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-error error">${status.errorMessage}</div>
                                        </c:if>
                                    </spring:bind>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class=" col-sm-12">
                                    <p class='p-term-agree'>
                                        By clicking Join now, you agree to <span class='s-term-agree'>Sharp-In-U's User Agreement</span>
                                    </p>
                                   
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-6">
                                    <button type="submit" class="btn btn-default">Join now</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
            <div class="inner-content-right col-sm-5">
                <div class="content-left">
                    <img width="100%" src="${contextPath}/assets/images/rubik.png" class="login-background">
                </div>
            </div>
        </div>
    </body>
</html>
