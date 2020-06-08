<%@ page language="java" contentType="text/html;charset=UTF-8"
	import="java.util.*,com.lcpan.bean.InventoryBean,com.lcpan.bean.UserBean"%>
<%
	UserBean user = (UserBean) session.getAttribute("user");
%>
<html lang="zh-Hant-TW">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智能零售v5.6.3-商品修改</title>
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
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.4/themes/cupertino/jquery-ui.min.css">
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link href="../main.css" rel="stylesheet">
</head>

<body>
	<jsp:useBean id="inventory" scope="request"
		class="com.lcpan.bean.InventoryBean" />
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
										<i class="pe-7s-cart icon-gradient bg-mean-fruit"> </i>
									</div>
									<div>商品修改</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="main-card mb-3 card">
								<div class="card-header"></div>
								<div class="table-responsive">
									<form method="post" action="../inventory/UpdateProduct"
										onSubmit="return CheckForm();" enctype="multipart/form-data">
										<table
											class="align-middle mb-0 table table-borderless table-striped table-hover">
											<thead>
												<tr>
													<th class="text-center">商品編號</th>
													<th class="text-center">商品名稱</th>
													<th class="text-center">商品類別</th>
													<th class="text-center">定價</th>
													<th class="text-center">RFID</th>
													<th class="text-center"></th>

												</tr>
											</thead>
											<tbody>
												<tr>
													<td class="text-center"><input type="text" readonly
														value="<%=inventory.getProductNo()%>" name="productNo"
														class="text-center" style="width: 50px"></td>
													<td class="text-center"><input type="text"
														value="<%=inventory.getProductName()%>"
														name="productName" class="text-center"
														style="width: 150px"></td>
													<td class="text-center"><input type="text"
														value="<%=inventory.getCategory()%>" name="category"
														class="text-center" style="width: 150px"></td>
													<td class="text-center"><input type="text"
														value="<%=inventory.getPrice()%>" name="price"
														class="text-center" style="width: 150px"></td>
													<td class="text-center"><input type="text"
														value="<%=inventory.getRfid()%>" name="rfid"
														class="text-center" style="width: 150px"></td>



												</tr>
											</tbody>
										</table>

										<div class="d-block text-center card-footer">
											<button class="btn-wide btn btn-success" type="submit">儲存</button>
											<button
												class="mr-2 btn-icon btn-icon-only btn btn-outline-danger"
												type="button" onclick="toProductList()">取消</button>
										</div>
									</form>
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