<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,com.lcpan.bean.SalesRecordBean,com.lcpan.bean.InventoryBean"%>

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
<link href="https://unpkg.com/video.js@6.11.0/dist/video-js.min.css" rel="stylesheet">
    <script src="https://unpkg.com/video.js@6.11.0/dist/video.min.js"></script>
    <script src="https://unpkg.com/videojs-flash/dist/videojs-flash.js"></script>
    <script src="https://unpkg.com/videojs-contrib-hls/dist/videojs-contrib-hls.js"></script>

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
											<canvas id="chart-area"></canvas>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="main-card mb-3 card">
										<div class="card-body">
											<h5 class="card-title">男女購買比例</h5>
											<canvas id="radar-chart"></canvas>
										</div>
									</div>
								</div>
								<video id="my-player" class="video-js" controls>
									<source src="rtmp://35.229.214.198:8000/live/test"
										type="rtmp/flv">
								</video>
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
	</script>
	<script type="text/javascript">
      var player = videojs('my-player',{
        autoplay: true,
        width:640,
        heigh:480
      });
    </script>
</body>
</html>