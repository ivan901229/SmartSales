<%@ page language="java" contentType="text/html;charset=UTF-8"
	import="java.util.*,com.lcpan.bean.MemberBean,com.lcpan.bean.UserBean"%>
<%
	UserBean user = (UserBean) session.getAttribute("user");
%>
<%!@SuppressWarnings("unchecked")%>
<html lang="zh-Hant-TW">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智能零售v5.6.3-會員總覽</title>
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
											src="../assets/images/avatars/<%=user.getUserId()%>.jpg"
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
								class="hamburger close-sidebar-btn hamburger--elastic is-active"
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
							<li><a href="GetOnsiteMembers"> <i
									class="metismenu-icon pe-7s-home"></i> 首頁
							</a></li>
							<li class="app-sidebar__heading">分析</li>
							<li><a href="../sales_analysis.jsp"> <i
									class="metismenu-icon pe-7s-graph"></i> 銷售分析
							</a></li>
							<li><a href="../flow.jsp"> <i
									class="metismenu-icon pe-7s-graph2"></i> 客流分析
							</a></li>
							<li class="app-sidebar__heading">商品</li>
							<li><a href="../new_product.jsp"> <i
									class="metismenu-icon pe-7s-pendrive"></i> 新增商品
							</a></li>
							<li><a href="../inventory/ProductList"> <i
									class="metismenu-icon pe-7s-pendrive"></i> 商品列表
							</a></li>
							<li class="app-sidebar__heading">庫存</li>
							<li><a href="../inventory/GetAllInventory"> <i
									class="metismenu-icon pe-7s-cart"></i> 庫存總覽
							</a></li>
							<li class="app-sidebar__heading">會員</li>
							<li><a href="javascript: MakeID()" class="mm-active"> <i
									class="metismenu-icon pe-7s-users"></i> 會員總覽
							</a></li>
							<li><a href="InsertGetMember"> <i
									class="metismenu-icon pe-7s-add-user"></i> 新增會員
							</a></li>
							<li style="display: none;"><a href="#"> <i
									class="metismenu-icon pe-7s-look"></i> 現場會員
							</a></li>
							<li class="app-sidebar__heading">銷售</li>
							<li><a href="../salesrecord/GetAllSalesRecord"> <i
									class="metismenu-icon pe-7s-note2"></i> 銷售紀錄
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
						<div>
							<div class="page-title-wrapper">
								<div class="page-title-heading">
									<div class="page-title-icon">
										<i class="pe-7s-users icon-gradient bg-mean-fruit"> </i>
									</div>
									<div>會員總覽</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="main-card mb-3 card">
								<div class="card-header">
									<div class="card-header">
										<div class="input-group">
											<div class="input-group-prepend">
												<span class="input-group-text"> 搜尋 </span>
											</div>
											<input placeholder="type to search" type="text" id="keyword"
												class="form-control" style="width: 200px;">
										</div>
									</div>
									<div class="btn-actions-pane-right">
										<div role="group" class="btn-group-sm btn-group">
											<button class="btn btn-primary" id="remake">&nbsp;&nbsp;All&nbsp;&nbsp;</button>
											<button class="btn btn-info" id="diamond">Diamond</button>
											<button class="btn btn-warning" id="gold">Gold</button>
											<button class="btn btn-light" id="silver">Silver</button>
										</div>
									</div>
								</div>
								<div class="table-responsive">
									<table
										class="align-middle mb-0 table table-borderless table-striped table-hover"
										id="table3">
										<thead>
											<tr>
												<th class="text-center">編號</th>
												<th class="text-center">等級</th>
												<th class="text-center">照片</th>
												<th class="text-center">姓名</th>
												<th class="text-center">生日</th>
												<th class="text-center">年齡</th>
												<th class="text-center">性別</th>
												<th class="text-center">偏好</th>
												<th class="text-center">電話</th>
												<th class="text-center">email</th>
												<th class="text-center">on-site</th>
												<th class="text-center">修改</th>
												<th class="text-center">刪除</th>
											</tr>
										</thead>
										<tbody>
											<%
												List<MemberBean> members = (ArrayList<MemberBean>) request.getAttribute("members");
												for (int i = 0; i < members.size(); i++) {
											%>
											<tr class="datarow">
												<td class="text-center"><%=(members.get(i)).getMemberNo()%></td>
												<td class="text-center"><%=(members.get(i)).getMemberLevel()%></td>
												<td class="text-center">
													<button
														onclick="showPhoto('<%=(members.get(i)).getMemberNo()%>')">
														<!-- <img src="../assets/images/member_photo/15.jpg" style="height: 60px"/> -->
														<img
															src="/member_photo/<%=(members.get(i)).getMemberNo()%>.jpg"
															style="height: 60px" />
													</button>
												</td>
												<td class="text-center"><%=(members.get(i)).getMemberName()%></td>
												<td class="text-center"><%=(members.get(i)).getMemberBirth()%></td>
												<td class="text-center"><%=(members.get(i)).getMemberAge()%></td>
												<td class="text-center"><%=(members.get(i)).getMemberGender()%></td>
												<td class="text-center"><%=(members.get(i)).getMemberPreferences()%></td>
												<td class="text-center"><%=(members.get(i)).getMemberPhone()%></td>
												<td class="text-center"><%=(members.get(i)).getMemberEmail()%></td>
												<td class="text-center"><%=(members.get(i)).getMemberOnsite()%></td>
												<td class="text-center">
													<button
														onclick="updateMember('<%=(members.get(i)).getMemberNo()%>')">
														<i class="pe-7s-config btn-icon-wrapper"> </i>
													</button>
												</td>
												<td class="text-center">
													<button
														onclick="delMember('<%=(members.get(i)).getMemberNo()%>')">
														<i class="pe-7s-trash btn-icon-wrapper"> </i>
													</button>
												</td>
											</tr>
											<%
												}
											%>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
		</div>
	</div>
	<script type="text/javascript" src="../assets/scripts/main.js"></script>
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