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
								<div>客流分析</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-lg-6">
							<div class="mb-3 card">
								<div
									class="card-header-tab card-header-tab-animation card-header">
									<div class="card-header-title">
										<i
											class="header-icon lnr-apartment icon-gradient bg-love-kiss">
										</i> 月客流量
									</div>
								</div>
								<div class="card-body">
									<div class="tab-content">
										<div class="tab-pane fade show active" id="tabs-eg-77">
											<div
												class="card mb-3 widget-chart widget-chart2 text-left w-100">
												<div class="widget-chat-wrapper-outer">
													<div
														class="widget-chart-wrapper widget-chart-wrapper-lg opacity-10 m-0">
														<canvas id="canvas"></canvas>
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
										</i> 今日客流量
									</div>

								</div>
								<div class="tab-content">
									<div class="tab-pane fade active show" id="tab-eg-55">
										<div class="widget-chart p-3">
											<div style="height: 350px">
												<canvas id="line-chart"></canvas>
											</div>

										</div>
									</div>
									<div class="pt-2 card-body">
										<div class="row">
											<div class="col-md-6">
												<div class="widget-content">
													<div class="widget-content-outer">
														<div class="widget-content-wrapper">
															<div class="widget-content-left">
																<div class="widget-numbers fsize-3 text-muted">63%</div>
															</div>
															<div class="widget-content-right">
																<div class="text-muted opacity-6">會員比</div>
															</div>
														</div>
														<div class="widget-progress-wrapper mt-1">
															<div
																class="progress-bar-sm progress-bar-animated-alt progress">
																<div class="progress-bar bg-danger" role="progressbar"
																	aria-valuenow="63" aria-valuemin="0"
																	aria-valuemax="100" style="width: 63%;"></div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="widget-content">
													<div class="widget-content-outer">
														<div class="widget-content-wrapper">
															<div class="widget-content-left">
																<div class="widget-numbers fsize-3 text-muted">52%</div>
															</div>
															<div class="widget-content-right">
																<div class="text-muted opacity-6">性別比</div>
															</div>
														</div>
														<div class="widget-progress-wrapper mt-1">
															<div
																class="progress-bar-sm progress-bar-animated-alt progress">
																<div class="progress-bar bg-info" role="progressbar"
																	aria-valuenow="52" aria-valuemin="0"
																	aria-valuemax="100" style="width: 52%;"></div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="main-card mb-3 card">
								<div class="card-body">
									<h5 class="card-title">年齡比</h5>
									<canvas id="chart-horiz-bar"></canvas>
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
	</script>
</body>

</html>