<%@ page language="java" contentType="text/html;charset=UTF-8"
	import="java.util.*,com.lcpan.bean.MemberBean,com.lcpan.bean.InventoryBean,com.lcpan.bean.UserBean"%>
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
<title>智能零售v5.6.3-庫存總覽</title>
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
						<div>
							<div class="page-title-wrapper">
								<div class="page-title-heading">
									<div class="page-title-icon">
										<i class="pe-7s-cart icon-gradient bg-happy-itmeo"> </i>
									</div>
									<div>庫存總覽</div>
								</div>
							</div>
							<div class="page-title-actions"></div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-6">
							<div class="main-card mb-3 card">
								<div class="card-body">
									<!--  <h5 class="card-title"></h5> -->
									<table class="mb-0 table table-bordered text-center">
										<thead>
											<tr>
												<th>商品編號</th>
												<th>商品名稱</th>
												<th>架上庫存</th>
												<th>倉庫庫存</th>
												<th>總庫存量</th>
												<th>進貨</th>
											</tr>
										</thead>
										<tbody>
											<%
												List<InventoryBean> inventories = (ArrayList<InventoryBean>) request.getAttribute("inventories");
												for (int i = 0; i < inventories.size(); i++) {
											%>

											<tr>
												<td class="text-center"><%=(inventories.get(i)).getProductNo()%>
												<td class="text-center"><%=(inventories.get(i)).getProductName()%>
												<td class="text-center"><%=(inventories.get(i)).getShelves()%>
												<td class="text-center"><%=(inventories.get(i)).getInWarehouse()%>
												<td class="text-center"><%=(inventories.get(i)).getTotalAmount()%>
												<td class="text-center">
													<button
														onclick="updateInventory('<%=(inventories.get(i)).getProductNo()%>')">
														<i class="metismenu-icon pe-7s-cart"> </i>
													</button>
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
					<div class="row"></div>
				</div>
			</div>
			<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
		</div>
	</div>
	<script type="text/javascript" src="../assets/scripts/main.js"></script>
	<script type="text/javascript" src="../assets/scripts/others.js"></script>
	<script>
		memberOnSiteCount();
	</script>
</body>

</html>