<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Update Post</title>
        <script src='${contextPath}/assets/javascripts/admin/post.js' type='text/javascript'></script>
    </head>
    <body class='contrast-red'>
        <div class='row-fluid'>
            <div class='span12'>
                <div class='page-header'>
                    <h1 class='pull-left'>
                        <i class='icon-edit'></i> <span>Update News</span>
                    </h1>
                    <div class='pull-right'>
                        <ul class='breadcrumb'>
                            <li><a href="/admin"><i class='icon-bar-chart'></i> </a></li>
                            <li class='separator'><i class='icon-angle-right'></i></li>
                            <li>News</li>
                            <li class='separator'><i class='icon-angle-right'></i></li>
                            <li class='active'>Update</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class='row-fluid'>
            <div class='span12 box'>
                <div class='box-content box-padding'>
                    <div class='fuelux'>
                        <div class='step-content'>
                            <form:form accept-charset="UTF-8"
                                       action="${contextPath}/admin/news/${newsId}/edit.in"
                                       class="form form-striped" method="POST" commandName="newsForm">
                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}" />
                                <div class='step-pane active' id='step1'>
                                    <div class='control-group'>
                                        <label class='control-label' for='txtTitle'>News
                                            Title</label>
                                        <div class='controls'>
                                            <form:input class='input-block-level' id='txtTitle'
                                                        placeholder='Title' path="title" />
                                        </div>
                                        <form:errors path="title" cssClass="help-block error" />
                                    </div>
                                    <div class='control-group'>
                                        <label class='control-label' for='txtSummary'>News
                                            Summary</label>
                                        <div class='controls'>
                                            <form:input class='input-block-level' id='txtSummary'
                                                        placeholder='Summary News' path="summary" />
                                        </div>
                                        <form:errors path="summary" cssClass="help-block error" />
                                    </div>
                                    <div class='control-group'>
                                        <label class='control-label' for='txtContent'>Content</label>
                                        <div class='controls'>
                                            <form:textarea class='input-block-level' id='txtContent'
                                                           placeholder='Content of News' rows='20' path="content" />
                                        </div>
                                        <form:errors path="content" cssClass="help-block error" />
                                    </div>

                                    <div class="text-right">
                                        <a href="${contextPath}/admin/news/1/list.in"
                                           class="btn btn-danger btn-large"> <i class="icon-remove"></i>
                                            Cancel
                                        </a>
                                        <button type="submit" class="btn btn-primary btn-large">
                                            <i class="icon-check"></i> Submit
                                        </button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <input id="fileupload" type="file" name="files[]" data-url="${contextPath}/upload/image.in" multiple style="display:none"/>
    </body>
</html>
