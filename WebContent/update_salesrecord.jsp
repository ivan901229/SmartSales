<%@ page language="java" contentType="text/html;charset=UTF-8"
	import="java.util.*,com.lcpan.bean.SalesRecordBean"%>

<html lang="zh-Hant-TW">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智能零售v5.6.3 更改銷售紀錄</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
<meta name="description"
	content="This is an example dashboard created using build-in elements and components.">
<meta name="msapplication-tap-highlight" content="no">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.4/themes/cupertino/jquery-ui.min.css">
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link href="../main.css" rel="stylesheet">
</head>

<body>
	<jsp:useBean id="salesrecords" scope="request"
		class="com.lcpan.bean.SalesRecordBean" />
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
										<i class="pe-7s-users icon-gradient bg-mean-fruit"> </i>
									</div>
									<div>銷售紀錄修改</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="main-card mb-3 card">
								<div class="card-header"></div>
								<div class="table-responsive">
									<form method="get" action="../salesrecord/UpdateSalesRecord"
										onSubmit="return CheckForm();">
										<table
											class="align-middle mb-0 table table-borderless table-striped table-hover">
											<thead>
												<tr>
													<th class="text-center">商品編號</th>
													<th class="text-center">日期</th>
													<th class="text-center">商品名稱</th>
													<th class="text-center">數量</th>
													<th class="text-center">價格</th>
													<th class="text-center">折扣</th>
													<th class="text-center">總價</th>
													<th class="text-center">性別</th>
													<th class="text-center">會員</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td class="text-center"><input type="text" readonly
														value="<%=salesrecords.getOrderNumber()%>"
														name="orderNumber" class="text-center"
														style="width: 150px"></td>
													<td class="text-center"><input class="text-center"
														style="width: 100px" id="datepicker" Readonly type="text"
														name="date"></td>
													<td class="text-center"><input type="text"
														value="<%=salesrecords.getProductNo()%>"
														name="productNo" class="text-center" style="width: 60px">
													</td>
													<td class="text-center"><input type="text"
														value="<%=salesrecords.getAmount()%>" name="amount"
														class="text-center" style="width: 100px"></td>
													<td class="text-center"><input type="text"
														value="<%=salesrecords.getPrice()%>" name="price"
														class="text-center" style="width: 150px"></td>
													<td class="text-center"><input type="text"
														value="<%=salesrecords.getDiscount()%>"
														name="discount" class="text-center" style="width: 150px">
													</td>
													<td class="text-center"><input type="text"
														value="<%=salesrecords.getTotalPrice()%>"
														name="totalPrice" class="text-center" style="width: 150px">
													</td>
													<td class="text-center"><select name="gender"
														id="gender" class="text-center" style="width: 150px">
															<option value="male">男</option>
															<option value="female">女</option>
													</select></td>
													<td class="text-center"><input type="text"
														value="<%=salesrecords.getNumber()%>" name="number"
														class="text-center" style="width: 150px"></td>
												</tr>
											</tbody>
										</table>

										<div class="d-block text-center card-footer">
											<button class="btn-wide btn btn-success" type="submit">儲存</button>
											<button
												class="mr-2 btn-icon btn-icon-only btn btn-outline-danger"
												type="button" onclick="toGetAllSalesRecord()">取消</button>
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
	<script type="text/javascript" src="../assets/scripts/others.js"></script>
	<script type="text/javascript" src="../assets/scripts/datepicker.js"></script>
	<script>
    
    
    const gender = "<%=salesrecords.getGender()%>"
		switch (gender) {
  	  case 'male': 
  		$('#gender option:eq(0)').attr('selected', true);
  		  break;
  	  case 'female': 
  		$('#gender option:eq(1)').attr('selected', true);
  		  break;
  	  default:
  	    console.log('Gender value error');
  	}
    
    	var myDate = $.datepicker.parseDate("yy-mm-dd", "<%=salesrecords.getDate()%>");
		var setDateTest = $.datepicker.formatDate("yy-mm-dd", myDate);
		$("#datepicker").datepicker({
			changeYear : true,
			changeMonth : true,
			showMonthAfterYear : true,
			showOn : "focus",
			yearRange : "-100:+0",
			dateFormat : "yy-mm-dd",
			minDate : "-100y",
			maxDate : "+0d"
		}).val(setDateTest);

		memberOnSiteCount();
	</script>
</body>

</html>