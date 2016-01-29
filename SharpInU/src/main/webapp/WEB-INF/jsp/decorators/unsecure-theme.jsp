<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><decorator:title default="Sharp In U" /></title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport' />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<link type="text/css" rel="stylesheet"
	href="${contextPath}/assets/stylesheets/bootstrap/css/bootstrap.min.css" />
<!-- / jquery ui -->
<link
	href='${contextPath}/assets/stylesheets/jquery_ui/jquery-ui-1.10.0.custom.css'
	media='all' rel='stylesheet' type='text/css' />
<link
	href='${contextPath}/assets/stylesheets/jquery_ui/jquery.ui.1.10.0.ie.css'
	media='all' rel='stylesheet' type='text/css' />

<link type="text/css" rel="stylesheet"
	href="${contextPath}/assets/stylesheets/plugins/jquery_bxslider/jquery.bxslider.css" />
<link rel="stylesheet"
	href="${contextPath}/assets/stylesheets/styles.css" />
<link rel="stylesheet"
	href="${contextPath}/assets/stylesheets/custom-bxslider.css" />
<link href='${contextPath}/assets/stylesheets/light-theme.css'
	id='color-settings-body-color' media='all' rel='stylesheet'
	type='text/css' />


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- / jquery -->
<script src='${contextPath}/assets/javascripts/jquery/jquery.min.js'
	type='text/javascript'></script>
<!-- / jquery ui -->
<script
	src='${contextPath}/assets/javascripts/jquery_ui/jquery-ui.min.js'
	type='text/javascript'></script>
<!-- / bootstrap -->
<script
	src='${contextPath}/assets/javascripts/bootstrap/bootstrap.min.js'
	type='text/javascript'></script>
<script type="text/javascript" defer="defer">
  var _gaq = _gaq || [];
	_gaq.push(
	  ['_setAccount', '${googleTrackingId}'],
	  ['_setCustomVar', 1, 'IP', "<%out.print( request.getRemoteAddr() );%>", 2],
	  ['_trackPageview']
	);
  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>
<decorator:head />
</head>
<body class='contrast-sea-blue'>
	<section id="website">
		<header>
			<div id="header_left">
				<a href="${contextPath}/home.in">
					<p>Sharp-In</p> <img src="${contextPath}/assets/images/ptsU1.png" />
				</a>
			</div>
			<div id="header_right">
				<nav>

					<ul <c:if test="${isLoggedIn}">class='login'</c:if>>
						<li><a href="${contextPath}/ask_for_advice.in">Ask for
								Advice</a></li>
						<li><a href="${contextPath}/stay_on_the_edge.in">Stay on
								the Edge</a></li>
						<li><a href="${contextPath}/our_practice.in">Community</a></li>
						<li><a href="${contextPath}/about_us.in">About Us</a></li>
						<li><a href="${contextPath}/contact_us.in">Contact Us</a></li>
						<c:choose>
							<c:when test="${isLoggedIn}">
								<li class="dropdown medium user-avatar"><a
									class="dropdown-toggle" data-toggle="dropdown" href="#"
									aria-expanded="true"> <img alt="${loggedUserFullName}"
										src="${contextPath}/assets/images/comingsoon.png"><span
										class="user-name hidden-phone">${loggedUserFullName}</span> <b
										class="caret"></b></a>
									<ul class="dropdown-menu">
										<li><a href="${contextPath}/ticket/1/user_tickets.in"> <i
												class="icon-user"></i> Profile
										</a></li>
										<c:if test="${isAdmin}">
											<li><a href="${contextPath}/admin/dashboard.in"
												target="_blank"> <i class="icon-cog"></i> Admin Control
													Panel
											</a></li>
										</c:if>
										<li class="divider"></li>
										<li><a href="${contextPath}/sec/sign_out.in"> <i
												class="icon-signout"></i> Sign out
										</a></li>
									</ul></li>
							</c:when>

						</c:choose>
					</ul>
				</nav>
			</div>
		</header>
		<decorator:body />

		<footer>
			<div id="footer_bottom">
				<div id="footer_bottom_nav" class="bottom-nav">
					<c:choose>
						<c:when test="${!isLoggedIn}">
							<a href="${contextPath}/sec/sign_in.in">Login</a> / <a
								href="${contextPath}/sec/sign_up.in">Register</a>
						</c:when>
					</c:choose>
				</div>
				<p>© 2014 Sharp-In U Worldwide</p>
			</div>
		</footer>
	</section>

</body>
</html>
