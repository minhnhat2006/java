<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Review Post</title>
    </head>
    <body class='contrast-red'>
        <div class='row-fluid'>
            <div class='span12'>
                <div class='page-header'>
                    <h1 class='pull-left'>
                        <i class='icon-edit'></i> <span>Review Post</span>
                    </h1>
                    <div class='pull-right'>
                        <ul class='breadcrumb'>
                            <li><a href="/admin"><i class='icon-bar-chart'></i> </a></li>
                            <li class='separator'><i class='icon-angle-right'></i></li>
                            <li>Post</li>
                            <li class='separator'><i class='icon-angle-right'></i></li>
                            <li class='active'>Review</li>
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
                                       action="${contextPath}/admin/news/1/list.in"
                                       class="form form-striped" method="get" commandName="newsForm">
                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}" />
                                <div class='box'>
                                    <div class="box-header">
                                        <div class="title">Title<div>
                                            </div>
                                            <div class="box-content">
                                                <div class="row-fluid">${news.title}</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class='box'>
                                        <div class="box-header">
                                            <div class="title">Summary</div>
                                        </div>
                                        <div class="box-content">
                                            <div class="row-fluid">${news.summary}</div>
                                        </div>
                                    </div>
                                    <div class='box'>
                                        <div class="box-header">
                                            <div class="title">Content</div>
                                        </div>
                                        <div class="box-content">
                                            <div class="row-fluid">${news.content}</div>
                                        </div>
                                    </div>
                                    <div class='box'>
                                        <hr class="hr-normal">
                                        <div class="text-right">
                                            <button type="submit" class="btn btn-primary btn-large">
                                                <i class="icon-check"></i> OK
                                            </button>
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
