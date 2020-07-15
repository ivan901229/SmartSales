<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,com.lcpan.bean.AnalysisBean"%>

<!doctype html>
<html lang="zh-Hant-TW">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智能零售v5.6.3-銷售分析</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
<meta name="description"
	content="Huge selection of charts created with the React ChartJS Plugin">
<meta name="msapplication-tap-highlight" content="no">
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
									<div>銷售分析</div>

								</div>
							</div>
						</div>
					</div>
					<div class="table-responsive">
						<table
							class="align-middle mb-0 table table-borderless table-striped table-hover"
							id="table3">


						</table>
					</div>
					<div class="tab-content">
						<div class="tab-pane tabs-animation fade show active"
							id="tab-content-0" role="tabpanel">
							<div class="row">
								<div class="col-md-6">
								
									<div class="main-card mb-3 card">
									
										<div class="card-body">
											<h5 class="card-title">商品購買比例</h5>
											<canvas id="pieSum"></canvas>
										</div>
										
										
									</div>
									<button type="button" id="bt" value="hide">Hide Detail</button>
									<div class="container">
								<table id="sumPie" class="mb-0 table table-sm table-dark text-center" style="display:block;">
										<thead>
											<tr>
												<th>商品</th>
												<th>數量</th>
												
											</tr>
										</thead>
										<tbody>
											<%
												List<AnalysisBean> sum = (ArrayList<AnalysisBean>) request.getAttribute("sum");
												for (int i = 0; i < sum.size(); i++) {
											%>

											<tr>
												<td class="text-center" id="pieN<%=i%>"><%=(sum.get(i)).getProductNo()%></td>
												<td class="text-center"id="pieV<%=i%>"><%=(sum.get(i)).getProductSum()%></td>
												
												
											</tr>
											<%
												}
											%>
										</tbody>
									</table>
									</div>
								</div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="app-wrapper-footer">
				<div class="app-footer"></div>
			</div>
		</div>

	</div>
	<script type="text/javascript" src="../assets/scripts/main.js"></script>
	<script type="text/javascript" src="../assets/scripts/others.js"></script>
	<script>
		memberOnSiteCount();
		salesTotalPrice();
		window.onload=function(){
			obt=document.getElementById("bt");
		    flowclick=document.getElementById("sumPie");
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
		var date = [];
		var pievalue=[];
		var bkclorM = [];
		var bkclorF = [];
		var bkclorT = [];
		setTimeout(function(){
			var rows = document.getElementById("sumPie").rows.length-1; //獲得行數
			console.log(rows)
			
			for(i=0;i<rows;i++){
				var d = $('#pieN'+i).html();
				var j = $('#pieV'+i).html();
				
				//console.log(j)
				date.push(d);
				pievalue.push(j)
				
				bkclorM.push('rgba(255, 99, 132, 1)','rgba(54, 162, 235, 1)','rgba(255, 206, 86, 1)');
				
			
			}
			
			
		var ctx = document.getElementById('pieSum').getContext('2d');
		var chart = new Chart(ctx, {
			
			type: 'pie',
		    data: {
		    	
		        labels: date,
				datasets: [{
		            label: '男性',
		            backgroundColor: bkclorM,
		            data: pievalue
		            
		        }
		        ]
		    }
		});
		
		},500);
	</script>
</body>
</html>