<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Global Setting</title>
<script type='text/javascript' src='${contextPath}/dwr/engine.js'></script>
<script type='text/javascript' src='${contextPath}/dwr/interface/DwrSettingService.js'></script>
<script type='text/javascript' src='${contextPath}/dwr/util.js'></script>
<script src='${contextPath}/assets/javascripts/admin/global_setting.js' type='text/javascript'></script>
</head>
<body class='contrast-red'>
	<div class='row-fluid'>
		<div class='span12'>
			<div class='page-header'>
				<h1 class='pull-left'>
					<i class='icon-edit'></i> <span>Global Setting</span>
				</h1>
				<div class='pull-right'>
					<ul class='breadcrumb'>
						<li><a href="/admin"><i class='icon-bar-chart'></i> </a></li>
						<li class='separator'><i class='icon-angle-right'></i></li>
						<li>Global Setting</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class='row-fluid'>
		<div class='span12 box'>
			<div class='box-content box-padding'>
				<c:forEach var="settingGroupBean" items="${settingGroupBeans}">
					<div class="box setting-group" settingGroupId="${settingGroupBean.settingGroup.settingGroupId}">
						<div class="box-header"><h4> ${settingGroupBean.settingGroup.settingGroupName}</h4></div>
						<div class="box-content">
							<c:forEach var="setting" items="${settingGroupBean.settings}">
								<div class="form-group">
									<label for="setting-value-${setting.settingId}">${setting.settingName}</label>
									<input type="text" id="setting-value-${setting.settingId}" class="form-control setting-value" 
										settingId="${setting.settingId}" value="${setting.settingValue}" currentValue="${setting.settingValue}"/>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>
