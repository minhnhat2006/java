<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="cache-control" content="no-store" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="content-language" content="vi" />

<title><decorator:title default="Sharp In U" /></title>

<!--[if lt IE 9]>
            <script src='${contextPath}/assets/javascripts/html5shiv.js' type='text/javascript'></script>
            <![endif]-->
<link href='${contextPath}/assets/stylesheets/bootstrap/bootstrap.css'
	media='all' rel='stylesheet' type='text/css' />
<link
	href='${contextPath}/assets/stylesheets/bootstrap/bootstrap-responsive.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / jquery ui -->
<link type="text/css" rel="stylesheet"
	href="${contextPath}/assets/stylesheets/bootstrap/css/bootstrap.min.css" />
<link
	href='${contextPath}/assets/stylesheets/jquery_ui/jquery-ui-1.10.0.custom.css'
	media='all' rel='stylesheet' type='text/css' />
<link
	href='${contextPath}/assets/stylesheets/jquery_ui/jquery.ui.1.10.0.ie.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / switch buttons -->
<link
	href='${contextPath}/assets/stylesheets/plugins/bootstrap_switch/bootstrap-switch.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / xeditable -->
<link
	href='${contextPath}/assets/stylesheets/plugins/xeditable/bootstrap-editable.css'
	media='all' rel='stylesheet' type='text/css' />
<link
	href='${contextPath}/assets/stylesheets/plugins/common/bootstrap-wysihtml5.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / wysihtml5 (wysywig) -->
<link
	href='${contextPath}/assets/stylesheets/plugins/common/bootstrap-wysihtml5.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / jquery file upload -->
<link
	href='${contextPath}/assets/stylesheets/plugins/jquery_fileupload/jquery.fileupload-ui.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / full calendar -->
<link
	href='${contextPath}/assets/stylesheets/plugins/fullcalendar/fullcalendar.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / select2 -->
<link
	href='${contextPath}/assets/stylesheets/plugins/select2/select2.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / mention -->
<link
	href='${contextPath}/assets/stylesheets/plugins/mention/mention.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / tabdrop (responsive tabs) -->
<link
	href='${contextPath}/assets/stylesheets/plugins/tabdrop/tabdrop.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / jgrowl notifications -->
<link
	href='${contextPath}/assets/stylesheets/plugins/jgrowl/jquery.jgrowl.min.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / datatables -->
<link
	href='${contextPath}/assets/stylesheets/plugins/datatables/bootstrap-datatable.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / dynatrees (file trees) -->
<link
	href='${contextPath}/assets/stylesheets/plugins/dynatree/ui.dynatree.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / color picker -->
<link
	href='${contextPath}/assets/stylesheets/plugins/bootstrap_colorpicker/bootstrap-colorpicker.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / datetime picker -->
<link
	href='${contextPath}/assets/stylesheets/plugins/bootstrap_datetimepicker/bootstrap-datetimepicker.min.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / daterange picker) -->
<link
	href='${contextPath}/assets/stylesheets/plugins/bootstrap_daterangepicker/bootstrap-daterangepicker.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / flags (country flags) -->
<link href='${contextPath}/assets/stylesheets/plugins/flags/flags.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / slider nav (address book) -->
<link
	href='${contextPath}/assets/stylesheets/plugins/slider_nav/slidernav.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / fuelux (wizard) -->
<link href='${contextPath}/assets/stylesheets/plugins/fuelux/wizard.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- / flatty theme -->
<link href='${contextPath}/assets/stylesheets/light-theme.css'
	id='color-settings-body-color' media='all' rel='stylesheet'
	type='text/css' />
<!-- / demo -->
<link href='${contextPath}/assets/stylesheets/javawork.css' media='all'
	rel='stylesheet' type='text/css' />
<link rel="stylesheet"
	href="${contextPath}/assets/stylesheets/admin.css" />
<!-- / jquery -->
<script src='${contextPath}/assets/javascripts/jquery/jquery.min.js'
	type='text/javascript'></script>
<!-- / jquery mobile events (for touch and slide) -->
<script
	src='${contextPath}/assets/javascripts/plugins/mobile_events/jquery.mobile-events.min.js'
	type='text/javascript'></script>
<!-- / jquery migrate (for compatibility with new jquery) -->
<script
	src='${contextPath}/assets/javascripts/jquery/jquery-migrate.min.js'
	type='text/javascript'></script>
<!-- / jquery ui -->
<script
	src='${contextPath}/assets/javascripts/jquery_ui/jquery-ui.min.js'
	type='text/javascript'></script>
<!-- / bootstrap -->
<script
	src='${contextPath}/assets/javascripts/bootstrap/bootstrap.min.js'
	type='text/javascript'></script>
<script src='${contextPath}/assets/javascripts/plugins/flot/excanvas.js'
	type='text/javascript'></script>
<!-- / sparklines -->
<script
	src='${contextPath}/assets/javascripts/plugins/sparklines/jquery.sparkline.min.js'
	type='text/javascript'></script>
<!-- / flot charts -->
<script src='${contextPath}/assets/javascripts/plugins/flot/flot.min.js'
	type='text/javascript'></script>
<script
	src='${contextPath}/assets/javascripts/plugins/flot/flot.resize.js'
	type='text/javascript'></script>
<script src='${contextPath}/assets/javascripts/plugins/flot/flot.pie.js'
	type='text/javascript'></script>
<!-- / bootstrap switch -->
<script
	src='${contextPath}/assets/javascripts/plugins/bootstrap_switch/bootstrapSwitch.min.js'
	type='text/javascript'></script>
<!-- / fullcalendar -->
<script
	src='${contextPath}/assets/javascripts/plugins/fullcalendar/fullcalendar.min.js'
	type='text/javascript'></script>
<!-- / datatables -->
<script
	src='${contextPath}/assets/javascripts/plugins/datatables/jquery.dataTables.min.js'
	type='text/javascript'></script>
<script
	src='${contextPath}/assets/javascripts/plugins/datatables/jquery.dataTables.columnFilter.js'
	type='text/javascript'></script>
<!-- / wysihtml5 -->
<script
	src='${contextPath}/assets/javascripts/plugins/common/wysihtml5.min.js'
	type='text/javascript'></script>
<script
	src='${contextPath}/assets/javascripts/plugins/common/bootstrap-wysihtml5.js'
	type='text/javascript'></script>
<!-- / select2 -->
<script
	src='${contextPath}/assets/javascripts/plugins/select2/select2.js'
	type='text/javascript'></script>
<!-- / color picker -->
<script
	src='${contextPath}/assets/javascripts/plugins/bootstrap_colorpicker/bootstrap-colorpicker.min.js'
	type='text/javascript'></script>
<!-- / mention -->
<script
	src='${contextPath}/assets/javascripts/plugins/mention/mention.min.js'
	type='text/javascript'></script>
<!-- / input mask -->

<!-- / modernizr -->
<script
	src='${contextPath}/assets/javascripts/plugins/modernizr/modernizr.min.js'
	type='text/javascript'></script>
<!-- / retina -->
<script src='${contextPath}/assets/javascripts/plugins/retina/retina.js'
	type='text/javascript'></script>
<!-- / timeago -->
<script
	src='${contextPath}/assets/javascripts/plugins/timeago/jquery.timeago.js'
	type='text/javascript'></script>
<!-- / slimscroll -->
<script
	src='${contextPath}/assets/javascripts/plugins/slimscroll/jquery.slimscroll.min.js'
	type='text/javascript'></script>
<!-- / autosize (for textareas) -->
<script
	src='${contextPath}/assets/javascripts/plugins/autosize/jquery.autosize-min.js'
	type='text/javascript'></script>
<!-- / charCount -->
<script
	src='${contextPath}/assets/javascripts/plugins/charCount/charCount.js'
	type='text/javascript'></script>
<!-- / validate -->
<script
	src='${contextPath}/assets/javascripts/plugins/validate/jquery.validate.min.js'
	type='text/javascript'></script>
<script
	src='${contextPath}/assets/javascripts/plugins/validate/additional-methods.js'
	type='text/javascript'></script>
<!-- / naked password -->
<script
	src='${contextPath}/assets/javascripts/plugins/naked_password/naked_password-0.2.4.min.js'
	type='text/javascript'></script>
<!-- / nestable -->
<script
	src='${contextPath}/assets/javascripts/plugins/nestable/jquery.nestable.js'
	type='text/javascript'></script>
<!-- / tabdrop -->
<script
	src='${contextPath}/assets/javascripts/plugins/tabdrop/bootstrap-tabdrop.js'
	type='text/javascript'></script>
<!-- / jgrowl -->
<script
	src='${contextPath}/assets/javascripts/plugins/jgrowl/jquery.jgrowl.min.js'
	type='text/javascript'></script>
<!-- / bootbox -->
<script
	src='${contextPath}/assets/javascripts/plugins/bootbox/bootbox.min.js'
	type='text/javascript'></script>
<!-- / inplace editing -->
<script
	src='${contextPath}/assets/javascripts/plugins/xeditable/bootstrap-editable.min.js'
	type='text/javascript'></script>
<script
	src='${contextPath}/assets/javascripts/plugins/xeditable/wysihtml5.js'
	type='text/javascript'></script>
<!-- / ckeditor -->
<script
	src='${contextPath}/assets/javascripts/plugins/ckeditor/ckeditor.js'
	type='text/javascript'></script>
<!-- / filetrees -->
<script
	src='${contextPath}/assets/javascripts/plugins/dynatree/jquery.dynatree.min.js'
	type='text/javascript'></script>
<!-- / datetime picker -->
<script
	src='${contextPath}/assets/javascripts/plugins/bootstrap_datetimepicker/bootstrap-datetimepicker.js'
	type='text/javascript'></script>
<!-- / daterange picker -->
<script
	src='${contextPath}/assets/javascripts/plugins/bootstrap_daterangepicker/moment.min.js'
	type='text/javascript'></script>
<script
	src='${contextPath}/assets/javascripts/plugins/bootstrap_daterangepicker/bootstrap-daterangepicker.js'
	type='text/javascript'></script>
<!-- / max length -->
<script
	src='${contextPath}/assets/javascripts/plugins/bootstrap_maxlength/bootstrap-maxlength.min.js'
	type='text/javascript'></script>
<!-- / dropdown hover -->
<script
	src='${contextPath}/assets/javascripts/plugins/bootstrap_hover_dropdown/twitter-bootstrap-hover-dropdown.min.js'
	type='text/javascript'></script>
<!-- / slider nav (address book) -->
<script
	src='${contextPath}/assets/javascripts/plugins/slider_nav/slidernav-min.js'
	type='text/javascript'></script>
<!-- / fuelux -->
<script src='${contextPath}/assets/javascripts/plugins/fuelux/wizard.js'
	type='text/javascript'></script>
<!-- / flatty theme -->
<script src='${contextPath}/assets/javascripts/nav.js'
	type='text/javascript'></script>
<script src='${contextPath}/assets/javascripts/tables.js'
	type='text/javascript'></script>
<script src='${contextPath}/assets/javascripts/theme.js'
	type='text/javascript'></script>
<!-- / demo -->
<script src='${contextPath}/assets/javascripts/demo/jquery.mockjax.js'
	type='text/javascript'></script>
<script src='${contextPath}/assets/javascripts/javawork/java_work.js'
	type='text/javascript'></script>

<!-- File upload -->
<script
	src="${contextPath}/assets/javascripts/plugins/jquery.ui.widget.js"></script>
<script
	src="${contextPath}/assets/javascripts/plugins/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script
	src="${contextPath}/assets/javascripts/plugins/fileupload/jquery.fileupload.min.js"></script>

<script src="//tinymce.cachefly.net/4.3/tinymce.min.js"></script>

<script type='text/javascript'>
	var SIUADM = {
		BASE_PATH : '${contextPath}',
	}
</script>

<decorator:head />
</head>
<body class='contrast-sea-blue'>
	<header>
		<div class='navbar'>
			<div class='navbar-inner'>
				<div class='container-fluid'>
					<a class='brand' href='${contextPath}/admin/dashboard.in'> <i
						class='icon-heart-empty'></i> <span class='hidden-phone'>Sharp-In-U
							Admin Control Panel</span>
					</a> <a class='toggle-nav btn pull-left' href='${contextPath}'> <i
						class='icon-reorder'></i>
					</a>
					<ul class='nav pull-right'>
						<li class='dropdown light only-icon widget'><a
							class='dropdown-toggle' data-toggle='dropdown' href='#'> <i
								class='icon-rss'></i>
								<div class='label'>5</div>
						</a>
							<ul class='dropdown-menu'>
								<li class='divider'></li>
								<li class='divider'></li>
								<li><a href='#'>
										<div class='widget-body'>
											<div class='pull-left icon'>
												<i class='icon-comment text-warning'></i>
											</div>
											<div class='pull-left text'>
												J2EE Developer job will expired soon <small class='muted'>1
													hour ago</small>
											</div>
										</div>
								</a></li>
								<li class='divider'></li>
								<li><a href='#'>
										<div class='widget-body'>
											<div class='pull-left icon'>
												<i class='icon-user text-success'></i>
											</div>
											<div class='pull-left text'>
												.NET Developer job is closed. <small class='muted'>last
													week</small>
											</div>
										</div>
								</a></li>
								<li class='divider'></li>
								<li><a href='#'>
										<div class='widget-body'>
											<div class='pull-left icon'>
												<i class='icon-inbox text-error'></i>
											</div>
											<div class='pull-left text'>
												Tom accept interviewed appointment. <small class='muted'>2
													weeks ago</small>
											</div>
										</div>
								</a></li>
								<li class='widget-footer'><a href='#'>All notifications</a>
								</li>
							</ul></li>
						<li class='dropdown medium user-menu'><a
							class='dropdown-toggle' data-toggle='dropdown' href='#'> <img
								alt='Mila Kunis' height='23'
								src='${contextPath}/assets/images/avatar.jpg' width='23' /> <span
								class='user-name hidden-phone'>Mila Kunis</span> <b
								class='caret'></b>
						</a>
							<ul class='dropdown-menu'>
								<li><a href='user_profile.html'> <i class='icon-user'></i>
										Profile
								</a></li>
								<li><a href='user_profile.html'> <i class='icon-cog'></i>
										Settings
								</a></li>
								<li class='divider'></li>
								<li><a href='${contextPath}/sec/sign_out.in'> <i
										class='icon-signout'></i> Sign out
								</a></li>
							</ul></li>
					</ul>
					<form accept-charset="UTF-8" action="search_results.html"
						class="navbar-search pull-right hidden-phone" method="get" />
					<div style="margin: 0; padding: 0; display: inline">
						<input name="utf8" type="hidden" value="&#x2713;" />
					</div>
					<button class="btn btn-link icon-search" name="button"
						type="submit"></button>
					<input autocomplete="off" class="search-query span2" id="q_header"
						name="q" placeholder="Search..." type="text" value="" />
					</form>
				</div>
			</div>
		</div>
	</header>
	<div id='wrapper'>
		<div id='main-nav-bg'></div>
		<nav class='' id='main-nav'>
			<div class='navigation'>
				<div class='search'>
					<form accept-charset="UTF-8" action="search_results.html"
						method="get" />
					<div style="margin: 0; padding: 0; display: inline">
						<input name="utf8" type="hidden" value="&#x2713;" />
					</div>
					<div class='search-wrapper'>
						<input autocomplete="off" class="search-query" id="q" name="q"
							placeholder="Search..." type="text" value="" />
						<button class="btn btn-link icon-search" name="button"
							type="submit"></button>
					</div>
					</form>
				</div>
				<ul class='nav nav-stacked'>
					<li><a class='dropdown-collapse'
						href='${contextPath}/admin/category/1/list.in'> <i
							class='icon-tag'></i> <span>Category</span> <i
							class='icon-angle-down angle-down'></i>
					</a>
						<ul class='nav nav-stacked' style='display: none'>
							<li><a href='${contextPath}/admin/category/1/list.in'> <i
									class='icon-list'></i> <span>View All</span>
							</a></li>
							<li class=''><a href='${contextPath}/admin/category/add.in'>
									<i class=' icon-upload'></i> <span>Add Item</span>
							</a></li>
						</ul></li>

					<li><a class='dropdown-collapse'
						href='${contextPath}/admin/post/1/list.in'> <i
							class='icon-file'></i> <span>Article</span> <i
							class='icon-angle-down angle-down'></i>
					</a>
						<ul class='nav nav-stacked' style='display: none'>
							<li><a href='${contextPath}/admin/post/1/list.in'> <i
									class='icon-list'></i> <span>View All</span>
							</a></li>
							<li class=''><a href='${contextPath}/admin/post/add.in'>
									<i class=' icon-upload'></i> <span>Add Item</span>
							</a></li>
						</ul></li>

					<li><a class='dropdown-collapse' href='#'> <i
							class='icon-file'></i> <span>News</span> <i
							class='icon-angle-down angle-down'></i>
					</a>
						<ul class='nav nav-stacked' style='display: none'>
							<li><a href='${contextPath}/admin/news/1/list.in'> <i
									class='icon-list'></i> <span>View All</span>
							</a></li>
							<li class=''><a href='${contextPath}/admin/news/add.in'>
									<i class=' icon-upload'></i> <span>Add Item</span>
							</a></li>
						</ul></li>

					<li><a class='dropdown-collapse' href='#'> <i
							class='icon-file'></i> <span>Trends</span> <i
							class='icon-angle-down angle-down'></i>
					</a>
						<ul class='nav nav-stacked' style='display: none'>
							<li><a href='${contextPath}/admin/trend/1/list.in'> <i
									class='icon-list'></i> <span>View All</span>
							</a></li>
							<li class=''><a href='${contextPath}/admin/trend/add.in'>
									<i class=' icon-upload'></i> <span>Add Item</span>
							</a></li>
						</ul></li>

					<li><a class='dropdown-collapse'
						href='${contextPath}/admin/our_practice_category/1/list.in'> <i
							class='icon-tag'></i> <span>Our Practices's Category</span> <i
							class='icon-angle-down angle-down'></i>
					</a>
						<ul class='nav nav-stacked' style='display: none'>
							<li><a
								href='${contextPath}/admin/our_practice_category/1/list.in'>
									<i class='icon-list'></i> <span>View All</span>
							</a></li>
							<li class=''><a
								href='${contextPath}/admin/our_practice_category/add.in'> <i
									class=' icon-upload'></i> <span>Add Item</span>
							</a></li>
						</ul></li>

					<li><a class='dropdown-collapse'
						href='${contextPath}/admin/our_practice/1/list.in'> <i
							class='icon-file'></i> <span>Our Practices</span> <i
							class='icon-angle-down angle-down'></i>
					</a>
						<ul class='nav nav-stacked' style='display: none'>
							<li><a href='${contextPath}/admin/our_practice/1/list.in'>
									<i class='icon-list'></i> <span>View All</span>
							</a></li>
							<li class=''><a
								href='${contextPath}/admin/our_practice/add.in'> <i
									class=' icon-upload'></i> <span>Add Item</span>
							</a></li>
						</ul></li>

					<li class=''><a href='${contextPath}/admin/user/1/list.in'>
							<i class=' icon-list'></i> <span>Users</span>
					</a></li>

					<li class=''><a href='${contextPath}/admin/career/1/list.in'>
							<i class=' icon-list'></i> <span>Careers</span>
					</a></li>
					<li class=''><a href='${contextPath}/admin/company/1/list.in'>
							<i class=' icon-list'></i> <span>Companies</span>
					</a></li>
					<li class=''><a href='${contextPath}/admin/resume/1/list.in'>
							<i class=' icon-list'></i> <span>Resumes</span>
					</a></li>
					<li class=''><a
						href='${contextPath}/admin/contactus/1/list.in'> <i
							class=' icon-list'></i> <span>Contacts</span>
					</a></li>
					<li class=''><a
						href='${contextPath}/admin/report/overview.in'> <i
							class='icon-calendar'></i> <span>Report</span>
					</a></li>
					<li class=''><a
						href='${contextPath}/admin/setting/global.in'> <i
							class='icon-gear'></i> <span>Global Setting</span>
					</a></li>

				</ul>
			</div>
		</nav>
		<section id='content'>
			<div class='container-fluid'>
				<div class='row-fluid' id='content-wrapper'>
					<div class='span12'>
						<decorator:body />
					</div>
				</div>
			</div>
		</section>
	</div>

</body>
</html>