<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="zh-Hant-TW">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智能零售v5.6.3-新增銷售紀錄</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
<meta name="description"
	content="This is an example dashboard created using build-in elements and components.">
<meta name="msapplication-tap-highlight" content="no">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.4/themes/cupertino/jquery-ui.min.css">
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
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
										<i class="pe-7s-add-user icon-gradient bg-mean-fruit"> </i>
									</div>
									<div>新增銷售紀錄</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="main-card mb-3 card">
								<div class="card-header"></div>
								<form method="get" action="../salesrecord/InsertSalesRecord">
									<div class="table-responsive">
										<table
											class="align-middle mb-0 table table-borderless table-striped table-hover">
											<thead>
												<tr>
													<th class="text-center">商品編號</th>
													<th class="text-center">日期</th>
													<th class="text-center">商品名稱</th>
													<th class="text-center">數量</th>
													<th class="text-center">價格</th>
													<th class="text-center">總價</th>
													<th class="text-center">性別</th>
													<th class="text-center">會員</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td class="text-center"><input type="text"
														class="text-center" style="width: 120px" readonly
														value="<%=request.getAttribute("max")%>"
														name="orderNumber"></td>
													<td class="text-center"><input class="text-center"
														readonly style="width: 100px" id="datepicker" type="text"
														name=date></td>
													<td class="text-center"><input class="text-center"
														style="width: 100px" name="productNo"></td>
													<td class="text-center"><input class="text-center"
														style="width: 100px" name="amount" /><br></td>
													<td class="text-center"><input class="text-center"
														style="width: 100px" name="price"></td>
													<td class="text-center"><input class="text-center"
														style="width: 100px" name="totalPrice"></td>
													<td class="text-center"><select class="text-center"
														style="width: 60px" name="gender">
															<option value="male">男</option>
															<option value="female">女</option>
													</select></td>
													<td class="text-center"><input class="text-center"
														style="width: 60px" type="text" name="number"></td>
												</tr>

											</tbody>
										</table>
									</div>
									<div class="d-block text-center card-footer">
										<button type="submit" class="btn-wide btn btn-success">新增</button>
										<button type="reset"
											class="mr-2 btn-icon btn-icon-only btn btn-outline-danger">清除</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
		</div>
	</div>
	<script type="text/javascript" src="../assets/scripts/main.js"></script>
	<script type="text/javascript" src="../assets/scripts/others.js"></script>
	<script type="text/javascript" src="../assets/scripts/datepicker.js"></script>
	<script>
		memberOnSiteCount();
	</script>

</body>

</html>