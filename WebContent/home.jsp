<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,com.lcpan.bean.MemberBean, java.sql.*, java.io.*"%>
<!DOCTYPE html>
<html lang="zh-Hant-TW">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智能零售v5.6.3-首頁</title>
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
	<!-- <script type="text/javascript">window.history.forward(1);</script> 到下一頁後無法回到此頁--> 
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<div
		class="app-container app-theme-white body-tabs-shadow fixed-header fixed-sidebar">
		<jsp:include page="include.jsp" />
		<div class="app-main">
			<jsp:include page="include2.jsp" />
			<div class="app-main__outer">
				<div class="app-main__inner">
					<jsp:include page="include3.jsp" />
					<div class="row">
						<div class="col-md-12">
							<div class="main-card mb-3 card">
								<div class="card-header">
									現場會員資訊
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
												<th class="text-center">會員等級</th>
												<th class="text-center">照片</th>
												<th class="text-center">姓名</th>
												<th class="text-center">生日</th>
												<th class="text-center">年齡</th>
												<th class="text-center">性別</th>
												<th class="text-center">偏好</th>
												<th class="text-center">電話</th>
												<th class="text-center">email</th>
											</tr>
										</thead>
										<tbody id="memberOnSiteList">
											
										</tbody>
									</table>
								</div>
								<div class="d-block text-center card-footer"></div>
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
		memberOnSiteList();
	</script>
</body>

</html>