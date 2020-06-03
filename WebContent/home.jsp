<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,com.lcpan.bean.MemberBean,com.lcpan.bean.UserBean, java.sql.*, java.io.*"%>
	<% UserBean user = (UserBean) session.getAttribute("user"); %>
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
	<script>
		setTimeout(function() {	memberOnSiteList();},50);            
		setInterval(function() { memberOnSiteList();},3000);     
		
		
		function memberOnSiteList(){
			 loadXMLDoc("../Ajax/OnSiteMemberList",function()
						{
						  	if (xmlhttp.readyState==4 && xmlhttp.status==200)
						    {
						  		$("#memberOnSiteList").html("");
						  		var number,memberLevel,photoURL;
						  		var rsString =eval('(' + xmlhttp.responseText + ')');
						  		// console.log("rsString.length: "+rsString.length);
						  		document.getElementById("memberOnSiteCount").innerHTML=rsString.length;
						  		for(let i=0;i<rsString.length;i++){
						  			number = rsString[i].number;
						  			memberLevel = rsString[i].memberLevel;
						  			photoURL = rsString[i].photoURL;
						  			name = rsString[i].name;
						  			birthday = rsString[i].birthday;
						  			age = rsString[i].age;
						  			gender = rsString[i].gender;
						  			preferences = rsString[i].preferences;
						  			phone = rsString[i].phone;
						  			email = rsString[i].email;
						  			// console.log(number);
					           		$("#memberOnSiteList").append("<tr class='datarow'><td class='text-center'>"+
					           				number+"</td><td class='text-center'>"+
					           				memberLevel+"</td><td class='text-center'>"+
					           				"<button onclick='showPhoto("+number+
					           				")'><img src='../assets/images/member_photo/"+number+
					           				".jpg' style='height: 60px' /></button></td><td class='text-center'>"+
					           				name+"</td><td class='text-center'>"+
					           				birthday+"</td><td class='text-center'>"+
					           				age+"</td><td class='text-center'>"+
					           				gender+"</td><td class='text-center'>"+
					           				preferences+"</td><td class='text-center'>"+
					           				phone+"</td><td class='text-center'>"+
					           				email+"</td></tr>");
						  		}
						    }
						});
		 }
	</script>
</body>

</html>