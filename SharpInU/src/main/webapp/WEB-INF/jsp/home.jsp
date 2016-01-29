<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>HOME</title>
<link type="text/css" rel="stylesheet"
	href="${contextPath}/assets/stylesheets/login.css" />
<script type="text/javascript"
	src="${assetsPath}/javascripts/plugins/jquery_bxslider/jquery.bxslider.js"></script>
<script type="text/javascript" src="${assetsPath}/javascripts/home.js"></script>
</head>
<body>
	<section id="content">
		<aside id="content_left">
			<div id="content_left_picture">
				<ul class="menuSlider">
					<li class="return-default"><a class="custom-link "
						data-slide-caro="2" data-target="#myCarousel" data-slide-index="0"
						href="#"><div class="menu-item-0"
								style="width: 193px; height: 125px"></div></a></li>
					<li><a class="custom-link" data-slide-caro="0"
						data-target="#myCarousel" data-slide-index="1" href="#"><div
								class="menu-item-1 active-slide"
								style="width: 193px; height: 125px"></div></a></li>
					<li><a class="custom-link" data-slide-caro="1"
						data-target="#myCarousel" data-slide-index="2" href="#"><div
								class="menu-item-2" style="width: 193px; height: 125px"></div></a></li>
				</ul>
			</div>

		</aside>

		<article id="content_right" class="container">
			<div class="row">
				<div data-ride="carousel" class="carousel slide" id="myCarousel">
					<div role="listbox" class="carousel-inner">
						<div class="item active" data-index="0" data-slide-index="1">
							<a href="${contextPath}/ask_for_advice.in?page=career"><img
								alt="900x500" data-src="holder.js/900x500/auto/#777:#777"
								src="${assetsPath}/images/main1.png" data-holder-rendered="true">
								<div class="carousel-caption">
									<h3></h3>
									<p></p>
								</div></a>
						</div>
						<div class="item  " data-index="1" data-slide-index="2">
							<a href="${contextPath}/ask_for_advice.in?page=company"><img
								alt="900x500" data-src="holder.js/900x500/auto/#666:#666"
								src="${assetsPath}/images/main2.png" data-holder-rendered="true">
								<div class="carousel-caption company-caption">
									<h3></h3>
									<p>
										Breath With You, Understand<br /> Problems of Your<br /> <span
											style="color: yellow">COMPANY </span> And Overcome<br />
										Together
									</p>
								</div></a>
						</div>
						<div class="item " data-index="2" data-slide-index="0">
							<a href="${contextPath}/ask_for_advice.in?page=resume"><img
								alt="900x500" data-src="holder.js/900x500/auto/#555:#5555"
								src="${assetsPath}/images/main3.png" data-holder-rendered="true">
								<div class="carousel-caption">
									<h3></h3>
									<p>
										Boost your <br> <span style="color: #31849b;">RESUME</span>
										<br> To Maximize Job<br> Opportunity
									</p>
								</div></a>
						</div>
					</div>
					<a data-slide="prev" role="button" href="#myCarousel"
						class="left carousel-control"> <span aria-hidden="true"
						class="glyphicon glyphicon-chevron-left"></span> <span
						class="sr-only">Previous</span>
					</a> <a data-slide="next" role="button" href="#myCarousel"
						class="right carousel-control"> <span aria-hidden="true"
						class="glyphicon glyphicon-chevron-right"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>

			</div>
		</article>
	</section>

	<section id="bottom">
		<div id="bot">
			<div id="bot_left" class="box">
				<div id="news" class="inner-box">
					<div class="col-lg-12 header-box">
						<span class="title-box"><a
							href="${weeklyTopicUrl}">WEEKLY HIGHLIGHT</a></span>
					</div>
					<img src="${weeklyTopicImageUrl}" id="img_twoman"
						style="height: 105px; width: 150px; margin-top: -5px" />
					<p>
						<br> <a href="${weeklyTopicUrl}">${weeklyTopicTitle}</a>
					</p>
				</div>

			</div>
			<div id="bot_mid" class="box">
				<div id="thinking" class="inner-box">
					<div class="col-lg-12 header-box">
						<span class="title-box"><a href="${testTrendTopicUrl}">TESTING
								TREND</a></span>
					</div>
					<img src="${testTrendTopicImageUrl}" id="img_thinking"
						style="height: 83px; width: 80px" />
					<p>
						<a href="${testTrendTopicUrl}">${testTrendTopicTitle}<br />
						</a>
					</p>
				</div>
			</div>
			<div id="bot_right" class="box">
				<div id="boosting" class="inner-box">
					<div class="col-lg-12 header-box spec-header-box">
						<a href="/"><img src="${assetsPath}/images/ptsU1.png"
							class="img_bot"
							style="height: 43px; width: 50px; margin-left: 20px;" /><span
							class="logo-text">Sharp-In</span></a>
					</div>
					<div class="col-lg-12 content-box">
						<p>
							<a href="/our_practice.in"> Our Practices </a>
						</p>
						<p>
							<a href=""> Social media </a>
						</p>
						<ul>
							<li><a href="https://twitter.com/"><img
									src="${assetsPath}/images/twitter.png" id="twitter"
									class="icon_bot"></a></li>
							<li><a href="https://www.facebook.com/SharpInU.Me "><img
									src="${assetsPath}/images/facebook.png" id="facebook"
									class="icon_bot" /></a></li>
							<li><a
								href="https://vn.linkedin.com/pub/sharp-in-u/107/789/b4a"><img
									src="${assetsPath}/images/linkedin.png" id="linkedin"
									class="icon_bot" /></a></li>
							<li><a href="https://www.youtube.com/"><img
									src="${assetsPath}/images/youtube.png" id="youtube"></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<c:if test="${showChangedPW}">
			<div class="modal" id="changedPasswordModal" tabindex="-1"
				role="dialog" aria-labelledby="changedPasswordModalLabel"
				aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content model-siu-content pw-changed-content">
						<div class="modal-header">
							<a href="#" class='model-siu-logo'><img
								src="${contextPath}/assets/images/ptsU1.png" class="img_bot"
								style="height: 43px; width: 50px; margin-left: 20px;"><span
								class="logo-text">Sharp-In</span></a>
							<p class="modal-title">Great! You've changed your password</p>
						</div>
						<div class="modal-body">
							<div class="container-fluid">
								<div class="row">
									<div>
										<button type="button" class="btn btn-primary btn-sendMessage"
											data-dismiss="modal" aria-label="Close">Continue to
											Sharp-In-U</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<script type="text/javascript">
				$(document).ready(function() {
					$('#changedPasswordModal').modal('show');
				});
			</script>
		</c:if>
	</section>
</body>
</html>
