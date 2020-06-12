<%@ page language="java" contentType="text/html;charset=UTF-8"
	import="java.util.*,com.lcpan.bean.MemberBean"
	import="com.lcpan.bean.UserBean"%>
<%
	UserBean user = (UserBean) session.getAttribute("user");
%>
<html lang="zh-Hant-TW">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智能零售v5.6.3-新增會員</title>
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
										<i class="pe-7s-users icon-gradient bg-mean-fruit"> </i>
									</div>
									<div>會員修改</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="main-card mb-3 card">
								<div class="card-header"></div>
								<div class="table-responsive">
									<form method="post" action="../member/UpdateMember"
										onSubmit="return CheckModify();" enctype="multipart/form-data">
										<table
											class="align-middle mb-0 table table-borderless table-striped table-hover">
											<thead>
												<tr>
													<th class="text-center">編號</th>
													<th class="text-center">等級</th>
													<th class="text-center">照片</th>
													<th class="text-center">姓名</th>
													<th class="text-center">生日</th>
													<th class="text-center">性別</th>
													<th class="text-center">偏好</th>
													<th class="text-center">電話</th>
													<th class="text-center">email</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td class="text-center"><input type="text" readonly
														value="<%=member.getMemberNo()%>" name="memberNo"
														class="text-center" style="width: 50px"></td>
													<td class="text-center"><select class="text-center"
														style="width: 80px" name="memberLevel" id="memberLevel">
															<option value="Silver">Silver</option>
															<option value="Gold">Gold</option>
															<option value="Diamond">Diamond</option>
													</select></td>

													<td class="text-center"><input class="text-center"
														style="width: 200px" type="file" name="memberImage" /><br>
													</td>
													<td class="text-center"><input type="text"
														value="<%=member.getMemberName()%>" name="memberName"
														class="text-center" style="width: 150px" required>
													</td>
													<td class="text-center"><input class="text-center"
														style="width: 100px" id="datepicker" type="text"
														name="memberBirth" required></td>
													<td class="text-center"><select class="text-center"
														style="width: 60px" name="memberGender" id="memberGender">
															<option value="male">男</option>
															<option value="female">女</option>
													</select></td>
													<td class="text-center"><input type="text"
														value="<%=member.getMemberPreferences()%>"
														name="memberPreferences" class="text-center"
														style="width: 60px"></td>
													<td class="text-center"><input type="text"
														value="<%=member.getMemberPhone()%>" name="memberPhone"
														class="text-center" style="width: 100px" required>
													</td>
													<td class="text-center"><input type="text"
														value="<%=member.getMemberEmail()%>" name="memberEmail"
														class="text-center" style="width: 150px" required>
													</td>
												</tr>
											</tbody>
										</table>

										<div class="d-block text-center card-footer">
											<button class="btn-wide btn btn-success" type="submit">儲存</button>
											<button
												class="mr-2 btn-icon btn-icon-only btn btn-outline-danger"
												type="button" onclick="toGetAllMembers()">取消</button>
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
      
    	const level = "<%=member.getMemberLevel()%>";
    	switch (level) {
    	  case 'Silver': 
    		  //document.getElementById("memberLevel").options[0].selected = true; // javascript
    		  $('#memberLevel option:eq(0)').attr('selected', true);       // jQuery
    		  break;
    	  case 'Gold': 
    		  $('#memberLevel option:eq(1)').attr('selected', true);
    		  break;
    	  case 'Diamond': 
    		  $('#memberLevel option:eq(2)').attr('selected', true);
    		  break;
    	  default:
    	    console.log('Level value error');
    	}
    	
    	const gender = "<%=member.getMemberGender()%>"
    		switch (gender) {
      	  case 'male': 
      		$('#memberGender option:eq(0)').attr('selected', true);
      		  break;
      	  case 'female': 
      		$('#memberGender option:eq(1)').attr('selected', true);
      		  break;
      	  default:
      	    console.log('Gender value error');
      	}
    	
    	var myDate = $.datepicker.parseDate("yy-mm-dd", "<%=member.getMemberBirth()%>");
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