<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hant-TW">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智能零售v5.6.3-客流分析</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
<meta name="description"
	content="This is an example dashboard created using build-in elements and components.">
<meta name="msapplication-tap-highlight" content="no">
<!--
    =========================================================
    * ArchitectUI HTML Theme Dashboard - v1.0.0
    =========================================================
    * Product Page: https://dashboardpack.com
    * Copyright 2019 DashboardPack (https://dashboardpack.com)
    * Licensed under MIT (https://github.com/DashboardPack/architectui-html-theme-free/blob/master/LICENSE)
    =========================================================
    * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
    -->
<link href="../main.css" rel="stylesheet">
<link href="https://unpkg.com/video.js@6.11.0/dist/video-js.min.css" rel="stylesheet">
<script src="https://unpkg.com/video.js@6.11.0/dist/video.min.js"></script>
<script src="https://unpkg.com/videojs-flash/dist/videojs-flash.js"></script>
<script src="https://unpkg.com/videojs-contrib-hls/dist/videojs-contrib-hls.js"></script>
</head>

<body>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<div
		class="app-container app-theme-white body-tabs-shadow fixed-header fixed-sidebar">
		<jsp:include page="include.jsp" />
		<div class="app-main">
			<jsp:include page="include2.jsp" />
			<div class="app-main__outer">
				<div class="app-main__inner">
					<jsp:include page="include3.jsp" />
					<div class="app-page-title">
						<div class="page-title-wrapper">
							<div class="page-title-heading">
								<div class="page-title-icon">
									<i class="pe-7s-graph icon-gradient bg-mean-fruit"> </i>
								</div>
								<div>影像串流</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-lg-6">
							<div class="mb-3 card" >
								<div class="card-header-tab card-header">
									<div class="card-header-title">
										<i
											class="header-icon lnr-rocket icon-gradient bg-tempting-azure">
										</i> 串流1
									</div>
								</div>
								<div class="tab-content">
									<div class="tab-pane fade active show" id="tab-eg-55">
										<div class="widget-chart p-3">
											<div style="height: 270px;">
												<video id="my-player1" class="video-js" controls style="margin:auto;">
													<source src="rtmp://35.229.214.198:8000/live/test"
														type="rtmp/flv">
												</video>
											</div>
										</div>
									</div>
									<div class="pt-2 card-body">
										<div class="row">
											<div class="col-md-6">
												<div class="widget-content">
													<div class="widget-content-outer">
														<div class="widget-content-wrapper">
														</div>
														<div class="widget-progress-wrapper mt-1">
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="widget-content">
													<div class="widget-content-outer">
														<div class="widget-content-wrapper">
														</div>
														<div class="widget-progress-wrapper mt-1">
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12 col-lg-6">
							<div class="mb-3 card">
								<div class="card-header-tab card-header">
									<div class="card-header-title">
										<i
											class="header-icon lnr-rocket icon-gradient bg-tempting-azure">
										</i> 串流2
									</div>
								</div>
								<div class="tab-content">
									<div class="tab-pane fade active show" id="tab-eg-55">
										<div class="widget-chart p-3">
											<div style="height: 270px;">
												<video id="my-player2" class="video-js" controls style="margin:auto;">
													<source src="rtmp://35.229.214.198:8000/live/test"
														type="rtmp/flv">
												</video>
											</div>
										</div>
									</div>
									<div class="pt-2 card-body">
										<div class="row">
											<div class="col-md-6">
												<div class="widget-content">
													<div class="widget-content-outer">
														<div class="widget-content-wrapper">
														</div>
														<div class="widget-progress-wrapper mt-1">
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="widget-content">
													<div class="widget-content-outer">
														<div class="widget-content-wrapper">
														</div>
														<div class="widget-progress-wrapper mt-1">
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row"></div>
	<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
	<script type="text/javascript" src="../assets/scripts/main.js"></script>
	<script type="text/javascript" src="../assets/scripts/others.js"></script>
	<script>
		memberOnSiteCount();
		salesTotalPrice();
	</script>
	<script type="text/javascript">
      var player = videojs('my-player1',{
        autoplay: true,
        width:576,
        heigh:432
      });
    </script>
    <script type="text/javascript">
      var player = videojs('my-player2',{
        autoplay: true,
        width:576,
        heigh:432
      });
    </script>
</body>

</html>