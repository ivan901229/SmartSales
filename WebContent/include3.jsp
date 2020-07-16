<%@ page language="java" contentType="text/html;charset=UTF-8"
	import="java.util.*,com.lcpan.bean.MemberBean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>
	<div class="row">
		<div class="col-md-6 col-xl-4">
			<div class="card mb-3 widget-content bg-midnight-bloom">
				<div class="widget-content-wrapper text-white">
					<div class="widget-content-left">
						<div class="widget-heading">當前人數</div>
						<div class="widget-subheading">現場人數</div>
					</div>
					<div class="widget-content-right">
						<div class="widget-numbers text-white">
							<span id=totalcount>10人</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xl-4">
			<div class="card mb-3 widget-content bg-grow-early">
				<div class="widget-content-wrapper text-white">
					<div class="widget-content-left">
						<div class="widget-heading">會員人數</div>
						<div class="widget-subheading">店內會員人數</div>
					</div>
					<div class="widget-content-right">
						<div class="widget-numbers text-white">
							<span id="memberOnSiteCount"></span>人
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xl-4">
			<div class="card mb-3 widget-content bg-premium-dark">
				<div class="widget-content-wrapper text-white">
					<div class="widget-content-left">
						<div class="widget-heading">營業額</div>
						<div class="widget-subheading">當日營業額</div>
					</div>
					<div class="widget-content-right">
						<div class="widget-numbers text-white">
							$<span id="salesTotalPrice"></span>元
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>