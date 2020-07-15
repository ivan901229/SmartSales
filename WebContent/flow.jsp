<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,com.lcpan.bean.MemberBean,com.lcpan.bean.AnalysisBean" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hant-TW">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智能零售v5.6.3-客流分析</title>
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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
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
						<div class="page-title-wrapper">
							<div class="page-title-heading">
								<div class="page-title-icon">
									<i class="pe-7s-graph icon-gradient bg-mean-fruit"> </i>
								</div>
								<div>客流分析</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-lg-6">
							<div class="mb-3 card">
							
								<div
									class="card-header-tab card-header-tab-animation card-header">
									<div class="card-header-title">
										<i
											class="header-icon lnr-apartment icon-gradient bg-love-kiss">
										</i> 客流量
									</div>
								</div>
								<div class="card-body">
									<div class="tab-content">
										<div class="tab-pane fade show active" id="tabs-eg-77">
											<div
												class="card mb-3 widget-chart widget-chart2 text-left w-100">
												<div class="widget-chat-wrapper-outer">
													<div
														class="widget-chart-wrapper widget-chart-wrapper-lg opacity-10 m-0">
														<canvas id="chartFlow" width="800" height="600"></canvas>
													</div>
												</div>
											</div>

										</div>
									</div>
								</div>
							</div>
							<button type="button" id="bt" value="hide">Show Detail</button>
							<table id="flowChart" class="mb-0 table table-sm table-dark text-center" style="display:none;">
										<thead>
											<tr>
												<th>日期</th>
												<th>男</th>
												<th>女</th>
												<th>會員</th>
											</tr>
										</thead>
										<tbody>
											<%
												List<AnalysisBean> flow = (ArrayList<AnalysisBean>) request.getAttribute("flow");
												for (int i = 0; i < flow.size(); i++) {
											%>

											<tr>
												<td class="text-center" id="charD<%=i%>"><%=(flow.get(i)).getDate()%></td>
												<td class="text-center"id="charM<%=i%>"><%=(flow.get(i)).getMale()%></td>
												<td class="text-center"id="charF<%=i%>"><%=(flow.get(i)).getFemale()%></td>
												<td class="text-center"id="charMe<%=i%>"><%=(flow.get(i)).getIsMember()%></td>
												
											</tr>
											<%
												}
											%>
										</tbody>
									</table>
									
						</div>
						<div class="col-md-12 col-lg-6">
							
							

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row"></div>
	<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
	<script type="text/javascript" src="../assets/scripts/main.js"></script>
	<script type="text/javascript" src="../assets/scripts/others.js"></script>
	<script>
		memberOnSiteCount();
		salesTotalPrice();
		window.onload=function(){
			obt=document.getElementById("bt");
		    flowclick=document.getElementById("flowChart");
		    obt.onclick=function (){
			if (flowclick.style.display=="none"){
				flowclick.style.display="block";
				obt.value="show";
			}else {
				flowclick.style.display="none"
				obt.value="hide";
			}
		}
		}
		
	</script>
	<script>
	var male = [];
	var female = [];
	var total = [];
	var date = [];
	var bkclorM = [];
	var bkclorF = [];
	var bkclorT = [];
	setTimeout(function(){
		var rows1 = document.getElementById("flowChart").rows.length-1; //獲得行數
		var rows = rows1/10;
		
		for(i=0;i<rows;i++){
			var d = $('#charD'+i).html();
			var j = $('#charM'+i).html();
			var k = $('#charF'+i).html();
			var l = parseInt(j)+parseInt(k);
			//console.log(j)
			date.push(d);
			male.push(j);
			female.push(k);
			total.push(l);
			bkclorM.push('rgba(255, 99, 132, 1)');
			bkclorF.push('rgba(54, 162, 235, 1)');
			bkclorT.push('rgba(255, 206, 86, 1)');
		
		}
		
		
	var ctx = document.getElementById('chartFlow').getContext('2d');
	var chart = new Chart(ctx, {
		
		type: 'bar',
	    data: {
	    	
	        labels: date,
			datasets: [{
	            label: '男性',
	            backgroundColor: bkclorM,
	            data: male
	            
	        },
	        {
	            label: '女性',
	            backgroundColor: bkclorF,
	            data: female
	        },
	        {
	            label: '總來客數',
	            backgroundColor: bkclorT,
	            data: total
	        }
	        ]
	    }
	});
	
	},500);
	
	</script>
</body>

</html>