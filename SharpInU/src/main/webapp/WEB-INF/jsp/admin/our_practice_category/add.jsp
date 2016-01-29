<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Category</title>
</head>
<body class='contrast-red'>
	<div class='row-fluid'>
		<div class='span12'>
			<div class='page-header'>
				<h1 class='pull-left'>
					<i class='icon-edit'></i> <span>Add Category</span>
				</h1>
				<div class='pull-right'>
					<ul class='breadcrumb'>
						<li><a href="/admin"><i class='icon-bar-chart'></i> </a>
						</li>
						<li class='separator'><i class='icon-angle-right'></i></li>
						<li>Category</li>
						<li class='separator'><i class='icon-angle-right'></i></li>
						<li class='active'>Add</li>
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
							action="${contextPath}/admin/our_practice_category/add.in"
							class="form form-striped" method="POST"
							commandName="ourPracticeCategoryForm">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<div class='step-pane active' id='step1'>
								<div class='control-group'>
									<label class='control-label' for='txtName'>Category
										Name</label>
									<div class='controls'>
										<form:input class='input-block-level' id='txtName'
											placeholder='Name Category' path="name" />
									</div>
									<form:errors path="name" cssClass="help-block error" />
								</div>

								<div class="text-right">
									<a href="${contextPath}/admin/our_practice_category/1/list.in"
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
</body>
</html>
