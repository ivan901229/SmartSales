<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,com.lcpan.bean.SalesRecordBean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%!@SuppressWarnings("unchecked")%>
<!DOCTYPE html>
<html lang="zh-Hant-TW">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智能零售v5.6.3-銷售紀錄</title>
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
										<i class="pe-7s-note2 icon-gradient bg-mean-fruit"> </i>
									</div>
									<div>銷售紀錄</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="main-card mb-3 card">
								<div class="card-header">
									<div class="input-group-prepend"></div>
									<input placeholder="type to search" type="text" id="keyword"
										class="form-control" style="width: 200px;">
									<div class="btn-actions-pane-right">
										<div role="group" class="btn-group-sm btn-group">
											<input type="button" value="新增"
												onclick="location.href='InsertGetSalesRecord'">
											<button class="btn btn-light" id="remake">全部</button>
											<button class="btn btn-light" id="getday">日</button>
											<button class="btn btn-secondary" id="getmonth">月</button>
											<button class="btn btn-dark" id="getyear">年</button>
										</div>
									</div>
								</div>
								<div class="table-responsive">
									<table
										class="align-middle mb-0 table table-borderless table-striped table-hover"
										id="table3">
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
												<th class="text-center">修改</th>
												<th class="text-center">刪除</th>
											</tr>
										</thead>
										<tbody>
											<%
												List<SalesRecordBean> salesrecords = (ArrayList<SalesRecordBean>) request.getAttribute("salesrecords");
											int p = (Integer) request.getAttribute("totalSalesPage");
											for (int i = 0; i < salesrecords.size(); i++) {
											%>
											<tr class="datarow">
												<td class="text-center"><%=(salesrecords.get(i)).getOrderNumber()%></td>
												<td class="text-center"><%=(salesrecords.get(i)).getDate()%></td>
												<td class="text-center"><%=(salesrecords.get(i)).getProductNo()%></td>
												<td class="text-center"><%=(salesrecords.get(i)).getAmount()%></td>
												<td class="text-center"><%=(salesrecords.get(i)).getPrice()%></td>
												<td class="text-center"><%=(salesrecords.get(i)).getDiscount()%></td>
												<td class="text-center"><%=(salesrecords.get(i)).getTotalPrice()%></td>
												<td class="text-center"><%=(salesrecords.get(i)).getGender()%></td>
												<td class="text-center"><%=(salesrecords.get(i)).getNumber()%></td>
												<td class="text-center">
													<button
														onclick="updateSalesRecord('<%=(salesrecords.get(i)).getOrderNumber()%>')">
														<i class="pe-7s-config btn-icon-wrapper"> </i>
													</button>
												</td>
												<td class="text-center">
													<button
														onclick="delSalesRecord('<%=(salesrecords.get(i)).getOrderNumber()%>')">
														<i class="pe-7s-trash btn-icon-wrapper"> </i>
													</button>
												</td>
											</tr>
											<%
												}
											%>
										</tbody>
									</table>
									<div class="text-center">
									<c:if test="${(currentpageno >1) && (currentpageno < totalSalesPage)}">
										<button class="mb-2 mr-2 btn btn-dark" onclick="location.href='GetAllSalesRecord?currentpageno=1'">首頁</button>
   	                                    <button class="mb-2 mr-2 btn btn-dark" onclick="location.href='GetAllSalesRecord?currentpageno=${currentpageno-1}'">上一頁</button>
   	                                    	<%
												for (int a = 0; a < p; a++) {
													int b=a+1;
											%>
											<button class="btn-pill btn-wide btn btn-outline-alternate btn-sm show"
												onclick="location.href='GetAllSalesRecord?currentpageno=<%=b%>'"><%=a+1%></button>
											<%
												}
											%>
   	                                    <button class="mb-2 mr-2 btn btn-dark" onclick="location.href='GetAllSalesRecord?currentpageno=${currentpageno+1}'">下一頁</button>
   	                                    <button class="mb-2 mr-2 btn btn-dark" onclick="location.href='GetAllSalesRecord?currentpageno=${totalSalesPage}'">尾頁</button>
									</c:if>
									<c:if test="${currentpageno==totalSalesPage && totalSalesPage!=1}">
								     	<button class="mb-2 mr-2 btn btn-dark" onclick="location.href='GetAllSalesRecord?currentpageno=1'">首頁</button>
								     		<%
												for (int a = 0; a < p; a++) {
													int b=a+1;
											%>
											<button class="btn-pill btn-wide btn btn-outline-alternate btn-sm show"
												onclick="location.href='GetAllSalesRecord?currentpageno=<%=b%>'"><%=a+1%></button>
											<%
												}
											%>
   	                                    <button class="mb-2 mr-2 btn btn-dark" onclick="location.href='GetAllSalesRecord?currentpageno=${currentpageno-1}'">上一頁</button>
									</c:if>
									<c:if test="${currentpageno==1 && totalSalesPage!=1}">
										<button class="mb-2 mr-2 btn btn-dark" onclick="location.href='GetAllSalesRecord?currentpageno=${currentpageno+1}'">下一頁</button>
											<%
												for (int a = 0; a < p; a++) {
													int b=a+1;
											%>
											<button class="btn-pill btn-wide btn btn-outline-alternate btn-sm show"
												onclick="location.href='GetAllSalesRecord?currentpageno=<%=b%>'"><%=a+1%></button>
											<%
												}
											%>
   	                                    <button class="mb-2 mr-2 btn btn-dark" onclick="location.href='GetAllSalesRecord?currentpageno=${totalSalesPage}'">尾頁</button>
									</c:if>
									</div>
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