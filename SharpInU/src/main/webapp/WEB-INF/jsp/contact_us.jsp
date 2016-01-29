<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Contact Us</title>
<link type="text/css" rel="stylesheet"
	href="${contextPath}/assets/stylesheets/contact_us.css">
<script type="text/javascript">
	$(document).ready(function() {
		$('form').get(0).reset(); //clear form data on page load
	});
</script>
</head>
<body>
	<div id="content">
		<div class="row inner-content">
			<div class="form-header">
				<h1>Contact Us</h1>
			</div>
			<div class="form-content row">
				<div class="form-content-left contact-content-left col-sm-3">

					<div class=" slogan-left col-sm-offset-2">
						<span> "Let Help Us Solve Your Problem."</span> <br> <span
							class="spec-content"> -Sharp-In-U- </span>
					</div>

				</div>
				<div class="form-content-right col-sm-9">
					<div class="col-sm-12 form-header-des">
						<span>Interested in learning more about working with <span
							style="font-weight: bold">Sharp-In-U?</span> Tell us about your
							problem and we'll be in touch.
						</span>
					</div>

					<form:form accept-charset="UTF-8"
						action="${contextPath}/contact_us/post.in" class="form-horizontal"
						role="form" enctype="multipart/form-data"
						commandName="contactUsForm" method="post">
						<div class="form-group required">
							<div class="col-sm-12">
								<form:input class='form-control' id='contr_name'
									placeholder="Name *" path="name" />
								<spring:bind path="name">
									<c:if test="${status.error}">
										<div class="alert alert-error error">${status.errorMessage}</div>
									</c:if>
								</spring:bind>
							</div>

						</div>
						<div class="form-group required">
							<div class="col-sm-12">
								<form:input class='form-control' id='contr_company'
									placeholder="Company" path="company" />
								<spring:bind path="company">
									<c:if test="${status.error}">
										<div class="alert alert-error error">${status.errorMessage}</div>
									</c:if>
								</spring:bind>
							</div>

						</div>
						<div class="form-group required">
							<div class="col-sm-12">
								<form:input class='form-control' id='contr_email'
									placeholder="Email *" path="email" type='email'/>
								<spring:bind path="email">
									<c:if test="${status.error}">
										<div class="alert alert-error error">${status.errorMessage}</div>
									</c:if>
								</spring:bind>
							</div>

						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<form:input class='form-control' id='res_name'
									placeholder='Phone *' path="phone" type='tel'/>
								<spring:bind path="phone">
									<c:if test="${status.error}">
										<div class="alert alert-error error">${status.errorMessage}</div>
									</c:if>
								</spring:bind>
							</div>

						</div>
						<div class="form-group required">

							<div class="col-sm-12">
								<form:textarea class='form-control' id='contr_message'
									placeholder="Describe your request *" rows='12' path="message" />
								<spring:bind path="message">
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
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
