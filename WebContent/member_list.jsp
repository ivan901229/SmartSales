<%@ page language="java" contentType="text/html;charset=UTF-8"
	import="java.util.*,com.lcpan.bean.MemberBean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
												int p = (Integer) request.getAttribute("totalPage");
												for (int i = 0; i < members.size(); i++) {
											%>
											<tr class="datarow">
												<td class="text-center"><%=(members.get(i)).getMemberNo()%></td>
												<td class="text-center"><%=(members.get(i)).getMemberLevel()%></td>
												<td class="text-center">
													<button
														onclick="showPhoto('<%=(members.get(i)).getMemberNo()%>')">
														<img
															src="../assets/images/member_photo/<%=(members.get(i)).getMemberNo()%>.jpg"
															style="height: 60px" />
														<!-- window的路徑為 /member_photo -->
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
													<a href="javascript: updateMember(<%=(members.get(i)).getMemberNo()%>)">
														<i class="fas fa-edit btn-icon-wrapper"> </i></a>
												</td>
												<td class="text-center">
													<a href="javascript: delMember(<%=(members.get(i)).getMemberNo()%>)">
														<i class="fas fa-trash btn-icon-wrapper"> </i></a>
												</td>
											</tr>
											<%
												}
											%>
										</tbody>
									</table>
									<div class="text-center">
										<c:if test="${currentpageno>1 && currentpageno<totalPage}">
											<!--  <a href="GetAllMembers?currentpageno=1">首頁</a>
										<a href="GetAllMembers?currentpageno=${currentpageno-1}">上一頁</a>
										<a href="GetAllMembers?currentpageno=${currentpageno+1}">下一頁</a>
										<a href="GetAllMembers?currentpageno=${totalPage}">尾頁</a>-->
											<button class="mb-2 mr-2 btn btn-dark" onclick="MakeID()">首頁</button>
											<button class="mb-2 mr-2 btn btn-dark"
												onclick="MakeID_Prev()">上一頁</button>
											<%
												for (int a = 0; a < p; a++) {
													int b=a+1;
											%>
											<button class="btn-pill btn-wide btn btn-outline-alternate btn-sm show"
												onclick="MakeID_page1(<%=b%>)"><%=a+1%></button>
											<%
												}
											%>
											<button class="mb-2 mr-2 btn btn-dark"
												onclick="MakeID_Next()">下一頁</button>
											<button class="mb-2 mr-2 btn btn-dark"
												onclick="MakeID_Last()">尾頁</button>
										</c:if>
										<c:if test="${currentpageno==totalPage  && totalPage!=1}">
											<button class="mb-2 mr-2 btn btn-dark" onclick="MakeID()">首頁</button>
											<%
												for (int a = 0; a < p; a++) {
													int b=a+1;
											%>
								
												<button class="btn-pill btn-wide btn btn-outline-alternate btn-sm show"
												onclick="MakeID_page1(<%=b%>)"><%=a+1%></button>
											<%
												}
											%>
											<button class="mb-2 mr-2 btn btn-dark"
												onclick="MakeID_Prev()">上一頁</button>
											<!-- 	<a href="GetAllMembers?currentpageno=1">首頁</a>
										<a href="GetAllMembers?currentpageno=${currentpageno-1}">上一頁</a>-->
										</c:if>
										<c:if test="${currentpageno==1 && totalPage!=1}">
											<button class="mb-2 mr-2 btn btn-dark"
												onclick="MakeID_Next()">下一頁</button>
											<%
												for (int a = 0; a < p; a++) {
													int b=a+1;
											%>
											<button class="btn-pill btn-wide btn btn-outline-alternate btn-sm show"
												onclick="MakeID_page1(<%=b%>)"><%=a+1%></button>
											<%
												}
											%>
											<button class="mb-2 mr-2 btn btn-dark"
												onclick="MakeID_Last()">尾頁</button>
											<!-- 	<a href="GetAllMembers?currentpageno=${currentpageno+1}">下一頁</a>
										<a href="GetAllMembers?currentpageno=${totalPage}">尾頁</a>-->
										</c:if>
										<c:if test="${currentpageno>totalPage}">
											<script>document.location.href="javascript: MakeID_Last()";</script>
										</c:if>
										<a>第${currentpageno}頁/共${totalPage}頁</a>
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
	salesTotalPrice();
	
		 function MakeID_Next(){
		    	var text = "";
		    	var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		    	for (var i = 0; i < 5; i++)
		    		text += possible.charAt(Math.floor(Math.random() * possible.length));
		    	
		    	window.location.href=('../member/GetAllMembers?'+text+"&currentpageno="+${currentpageno+1})
		    } //下一頁
		    
		   function MakeID_Prev(){
		    	var text = "";
		    	var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		    	for (var i = 0; i < 5; i++)
		    		text += possible.charAt(Math.floor(Math.random() * possible.length));
		    	
		    	window.location.href=('../member/GetAllMembers?'+text+"&currentpageno="+${currentpageno-1})
		    } //上一頁  
		    
		    function MakeID_Last(){
		    	var text = "";
		    	var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		    	for (var i = 0; i < 5; i++)
		    		text += possible.charAt(Math.floor(Math.random() * possible.length));
		    	
		    	window.location.href=('../member/GetAllMembers?'+text+"&currentpageno="+${totalPage})
		    } // 尾頁 
		    
		    function MakeID_Last(){
		    	var text = "";
		    	var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		    	for (var i = 0; i < 5; i++)
		    		text += possible.charAt(Math.floor(Math.random() * possible.length));
		    	
		    	window.location.href=('../member/GetAllMembers?'+text+"&currentpageno="+${totalPage})
		    } // 尾頁 
		   

		function MakeID_page(){
	    	var text = "";
	    	var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	    	for (var i = 0; i < 5; i++)
	    		text += possible.charAt(Math.floor(Math.random() * possible.length));
	    	
	    	window.location.href=('../member/GetAllMembers?'+text+"&currentpageno="+<%=1%>)
	    	
	    } // 分頁
	    
	    function MakeID_page1(page){
	    	var text = "";
	    	var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	    	for (var i = 0; i < 5; i++)
	    		text += possible.charAt(Math.floor(Math.random() * possible.length));
	    	
	    	window.location.href=('../member/GetAllMembers?'+text+"&currentpageno="+page)
	    	
	    } // 分頁
		    
	</script>
</body>

</html>