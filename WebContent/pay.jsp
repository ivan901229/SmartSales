<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,java.io.*,com.lcpan.bean.MemberBean"%>

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
	<jsp:useBean id="member" scope="request"
		class="com.lcpan.bean.MemberBean" />
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
								<div class="card-header">
									<form method="get" action="../member/SearchMemberPhone">
										<div class="input-group" style="width: 300px;">
											<div class="input-group-prepend">
												<span class="input-group-text">會員電話 </span>
											</div>
												<input placeholder="請輸入會員電話" type="text" id="memberPhone"
													class="form-control" name="memberPhone">
												<button type="submit" class="btn-wide btn btn-success">查詢</button>
										</div>

									</form>
									<!-- 
										<a class="btn-wide btn btn-primary" href="../member/SearchMemberFace" role="button">Link</a>
								    -->
									<div id="memberGender" class="text-center" style="margin-left: 26px;">
											客戶性別： 
											<input id="genderMale" class="text-center" type=radio name=SEX value=male>男 
											<input id="genderFemale" class="text-center" type=radio name=SEX value=female>女<br>
									</div>
								</div>
								
								<div class="table-responsive">
									<table
										class="align-middle mb-0 table table-borderless table-striped table-hover"
										id="table3">
										<thead>
											<tr>
												<th class="text-center" width="252">商品編號</th>
												<th class="text-center" width="252">商品名稱</th>
												<th class="text-center" width="252">數量</th>
												<th class="text-center" width="252">單價</th>
												<th class="text-center" width="252">刪除</th>
											</tr>
										</thead>
										<tbody id="paylist"></tbody>
									</table>
									<br><br>
									<table
										class="align-middle mb-0 table table-borderless table-striped table-hover"
										id="table3">
										<thead>
											<tr>
												<th class="text-center" width="168">會員編號</th>
												<th class="text-center" width="168">會員名稱</th>
												<th class="text-center" width="168">會員等級</th>
												<th class="text-center" width="252">總數</th>
												<th class="text-center" width="252">原價</th>
												<th class="text-center" width="252">折扣</th>
											</tr>
										</thead>
										<tbody id="pricelist"></tbody>
									</table>
								</div>
								<div class="totalPrice" id="totalPrice"></div>
								<div class="totalPriceButton">
									
									<button type="button" class=" btn-wide btn btn-success" onclick="payListToJSON()">送出</button>
									<button type="button" class="mr-2 btn-icon btn-icon-only btn btn-outline-danger" onclick="cleanPayAll()">清除</button>
									<h5 class="text-center">${success}</h5>
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
		paylist();
		salesTotalPrice();
		setTimeout(function() {	memberCheck();},200);
		//setInterval(function() {paylist();},500);
		var memberOnCounter = false;
		setTimeout(function() {facialScanCounter();},1000);
		
		function memberCheck(){
			if('<%=request.getAttribute("check")%>'=='ok'){
				$("#memberNumber").append("<%=member.getMemberNo()%>");
				$("#memberName").append("<%=member.getMemberName()%>");
				$("#memberLevel").append("<%=member.getMemberLevel()%>");
				$("#memberDiscount").html("").append("<%=member.getMemberDiscount()%>");
				if("<%=member.getMemberGender()%>"=="male"){
					$('#genderMale').attr('checked', true);
					$('#genderFemale').attr('disabled', true);
				}
				else if("<%=member.getMemberGender()%>"=="female"){
					$('#genderFemale').attr('checked', true);
					$('#genderMale').attr('disabled', true);
				}
			}
		}
		
	</script>
</body>

</html>