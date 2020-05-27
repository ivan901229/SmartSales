<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.lcpan.bean.UserBean,com.lcpan.bean.UserBean"%>
<%
	UserBean user = (UserBean) session.getAttribute("user");
%>
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
<link href="./main.css" rel="stylesheet">
</head>

<body>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<div
		class="app-container app-theme-white body-tabs-shadow fixed-header fixed-sidebar">
		<div class="app-header header-shadow">
			<div class="app-header__logo">
				<div class="logo-src"></div>
				<div class="header__pane ml-auto">
					<div>
						<button type="button"
							class="hamburger close-sidebar-btn hamburger--elastic"
							data-class="closed-sidebar">
							<span class="hamburger-box"> <span class="hamburger-inner"></span>
							</span>
						</button>
					</div>
				</div>
			</div>
			<div class="app-header__mobile-menu">
				<div>
					<button type="button"
						class="hamburger hamburger--elastic mobile-toggle-nav">
						<span class="hamburger-box"> <span class="hamburger-inner"></span>
						</span>
					</button>
				</div>
			</div>
			<div class="app-header__menu">
				<span>
					<button type="button"
						class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
						<span class="btn-icon-wrapper"> <i
							class="fa fa-ellipsis-v fa-w-6"></i>
						</span>
					</button>
				</span>
			</div>
			<div class="app-header__content">
				<div class="app-header-left">
					<div class="search-wrapper" id="search-wrapper">
						<div class="input-holder">
							<input type="text" name="keyword" id="search-keyword"
								class="search-input" placeholder="Type to search member profile">
							<button type="submit" class="search-icon"
								onclick="searchMember()">
								<span></span>
							</button>
						</div>
						<button class="close"></button>
					</div>
				</div>
				<div class="app-header-right">
					<div class="header-btn-lg pr-0">
						<div class="widget-content p-0">
							<div class="widget-content-wrapper">
								<div class="widget-content-left">
									<div class="btn-group">
										<a data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false" class="p-0 btn"> <img width="42"
											class="rounded-circle"
											src="./assets/images/avatars/<%=user.getUserId()%>.jpg"
											alt=""> <i class="fa fa-angle-down ml-2 opacity-8"></i>
										</a>
										<div tabindex="-1" role="menu" aria-hidden="true"
											class="dropdown-menu dropdown-menu-right">

											<button type="button" tabindex="0" class="dropdown-item"
												onclick="javascript:location.href='../LogIn/LogOut'">Logout</button>

										</div>
									</div>
								</div>
								<div class="widget-content-left  ml-3 header-user-info">
									<div class="widget-heading"><%=user.getUserId()%></div>

								</div>
								<div class="widget-content-right header-user-info ml-3">
									<button type="button"
										class="btn-shadow p-1 btn btn-primary btn-sm show-toastr-example">
										<i class="fa text-white fa-calendar pr-1 pl-1"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
					<a href="../pay.jsp">
						<button class="mb-2 mr-2 btn btn-danger">結帳</button>
					</a>
				</div>
			</div>
		</div>
		<div class="ui-theme-settings">
                <div class="scrollbar-container">
                </div>
        </div>
		<div class="app-main">
			<div
				class="app-sidebar sidebar-shadow bg-heavy-rain sidebar-text-dark">
				<div class="app-header__logo">
					<div class="logo-src"></div>
					<div class="header__pane ml-auto">
						<div>
							<button type="button"
								class="hamburger close-sidebar-btn hamburger--elastic"
								data-class="closed-sidebar">
								<span class="hamburger-box"> <span
									class="hamburger-inner"></span>
								</span>
							</button>
						</div>
					</div>
				</div>
				<div class="app-header__mobile-menu">
					<div>
						<button type="button"
							class="hamburger hamburger--elastic mobile-toggle-nav">
							<span class="hamburger-box"> <span class="hamburger-inner"></span>
							</span>
						</button>
					</div>
				</div>
				<div class="app-header__menu">
					<span>
						<button type="button"
							class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
							<span class="btn-icon-wrapper"> <i
								class="fa fa-ellipsis-v fa-w-6"></i>
							</span>
						</button>
					</span>
				</div>
				<div class="scrollbar-sidebar">
					<div class="app-sidebar__inner">
						<ul class="vertical-nav-menu">
							<li class="app-sidebar__heading">首頁</li>
							<li><a href="../SmartSales/member/GetOnsiteMembers"> <i
									class="metismenu-icon pe-7s-home"></i> 首頁
							</a></li>
							<li class="app-sidebar__heading">分析</li>
							<li><a href="sales_analysis.jsp"> <i
									class="metismenu-icon pe-7s-graph"></i> 銷售分析
							</a></li>
							<li><a href="flow.jsp" class="mm-active"> <i
									class="metismenu-icon pe-7s-graph2"></i> 客流分析
							</a></li>
							<li class="app-sidebar__heading">商品</li>
							<li><a href="./new_product.jsp"> <i
									class="metismenu-icon pe-7s-pendrive"></i> 新增商品
							</a></li>
							<li><a href="./inventory/ProductList"> <i
									class="metismenu-icon pe-7s-pendrive"></i> 商品列表
							</a></li>
							<li class="app-sidebar__heading">庫存</li>
							<li><a href="inventory/GetAllInventory"> <i
									class="metismenu-icon pe-7s-cart"></i> 庫存總覽
							</a></li>
							<li class="app-sidebar__heading">會員</li>
							<li><a href="javascript: MakeID_2()"> <i
									class="metismenu-icon pe-7s-users"></i> 會員總覽
							</a></li>
							<li><a href="member/InsertGetMember"> <i
									class="metismenu-icon pe-7s-add-user"></i> 新增會員
							</a></li>
							<li style="display: none;"><a href="#"> <i
									class="metismenu-icon pe-7s-look"></i> 現場會員
							</a></li>
							<li class="app-sidebar__heading">銷售</li>
							<li><a href="../SmartSales/salesrecord/GetAllSalesRecord">
									<i class="metismenu-icon pe-7s-note2"></i> 銷售紀錄
							</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="app-main__outer">
				<div class="app-main__inner">
					<div class="row">
						<div class="col-md-6 col-xl-4">
							<div class="card mb-3 widget-content bg-midnight-bloom">
								<div class="widget-content-wrapper text-white">
									<div class="widget-content-left">
										<div class="widget-heading">當前人數</div>
										<div class="widget-subheading">現場人數</div>
									</div>
									<div class="widget-content-right">
										<div class="widget-numbers text-white">
											<span>10人</span>
										</div>
									</div>
								</div>
							</div>

						</div>
						<div class="col-md-6 col-xl-4">
							<div class="card mb-3 widget-content bg-grow-early">
								<div class="widget-content-wrapper text-white">
									<div class="widget-content-left">
										<div class="widget-heading">會員人數</div>
										<div class="widget-subheading">店內會員人數</div>
									</div>
									<div class="widget-content-right">
										<div class="widget-numbers text-white">
											<span id="memberOnSiteCount"></span>人
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-xl-4">
							<div class="card mb-3 widget-content bg-premium-dark">
								<div class="widget-content-wrapper text-white">
									<div class="widget-content-left">
										<div class="widget-heading">營業額</div>
										<div class="widget-subheading">當日營業額</div>
									</div>
									<div class="widget-content-right">
										<div class="widget-numbers text-white">
											<span>$66600</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
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
	<script type="text/javascript" src="./assets/scripts/main.js"></script>
	<script>
		setTimeout(function() {
			memberOnSiteCount();
		}, 50);
		setInterval(function() {
			memberOnSiteCount();
		}, 3000);
	</script>
</body>

</html>