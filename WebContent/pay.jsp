<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.lcpan.bean.UserBean"%>
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
<title>智能零售v5.6.3-結帳</title>
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
										<i class="pe-7s-cart icon-gradient bg-mean-fruit"> </i>
									</div>
									<div>結帳</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="main-card mb-3 card">
								<div class="card-header"></div>
								<div class="table-responsive">
									<table
										class="align-middle mb-0 table table-borderless table-striped table-hover"
										id="table3">
										<thead>
											<tr>
												<th class="text-center">編號</th>
												<th class="text-center">商品名稱</th>
												<th class="text-center">單價</th>
												<th class="text-center">數量</th>
												<th class="text-center">小計</th>

												<th class="text-center">刪除</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td class=text-center>A01</td>
												<td class=text-center>上衣</td>
												<td class=text-center>$1000</td>
												<td class=text-center>2</td>
												<td class=text-center>$2000</td>
												<td class=text-center>
													<button>
														<i class="pe-7s-trash btn-icon-wrapper"> </i>
													</button>
												</td>
											</tr>
											<tr>
												<td class=text-center>A02</td>
												<td class=text-center>上衣</td>
												<td class=text-center>$1500</td>
												<td class=text-center>3</td>
												<td class=text-center>$3500</td>
												<td class=text-center>
													<button>
														<i class="pe-7s-trash btn-icon-wrapper"> </i>
													</button>
												</td>
											</tr>
											<tr>
												<td class=text-center>A03</td>
												<td class=text-center>上衣</td>
												<td class=text-center>$2000</td>
												<td class=text-center>4</td>
												<td class=text-center>$8000</td>
												<td class=text-center>
													<button>
														<i class="pe-7s-trash btn-icon-wrapper"> </i>
													</button>
												</td>
											</tr>
											<tr>
												<td class=text-center>A04</td>
												<td class=text-center>上衣</td>
												<td class=text-center>$2500</td>
												<td class=text-center>5</td>
												<td class=text-center>$12500</td>
												<td class=text-center>
													<button>
														<i class="pe-7s-trash btn-icon-wrapper"> </i>
													</button>
												</td>
											</tr>
											<tr>
												<td class=text-center>A05</td>
												<td class=text-center>上衣</td>
												<td class=text-center>$3000</td>
												<td class=text-center>6</td>
												<td class=text-center>$18000</td>
												<td class=text-center>
													<button>
														<i class="pe-7s-trash btn-icon-wrapper"> </i>
													</button>
												</td>
											</tr>
											<tr>
												<td class=text-center></td>
												<td class=text-center></td>
												<td class=text-center></td>
												<td class=text-center></td>
												<td class=text-center></td>
												<td class=text-center></td>
											</tr>

											<tr>
												<td class=text-center></td>
												<td class=text-center></td>
												<td class=text-center></td>
												<td class=text-center>總數</td>
												<td class=text-center>總金額</td>
												<td class=text-center>結帳</td>
											</tr>
											<tr>
												<td class=text-center></td>
												<td class=text-center></td>
												<td class=text-center></td>
												<td class=text-center>20</td>
												<td class=text-center>$44000</td>
												<td class=text-center>
													<button>
														<i class="pe-7s-gift btn-icon-wrapper"> </i>
													</button>
												</td>
											</tr>

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
	<script type="text/javascript" src="../assets/scripts/others.js"></script>
	
	<script>
		memberOnSiteCount();
	</script>
</body>

</html>