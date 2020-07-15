<%@ page language="java" contentType="text/html;charset=UTF-8"
	import="java.util.*,com.lcpan.bean.MemberBean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<div class="app-sidebar sidebar-shadow bg-heavy-rain sidebar-text-dark">
		<div class="app-header__logo">
			<div class="logo-src"></div>
			<div class="header__pane ml-auto">
				<div>
					<button type="button"
						class="hamburger close-sidebar-btn hamburger--elastic is-active"
						data-class="closed-sidebar">
						<span class="hamburger-box"> <span class="hamburger-inner"></span>
						</span>
					</button>
				</div>
			</div>
		</div>
		<div class="app-header__mobile-menu">
			<div>
				<button type="button"
					class="hamburger hamburger--elastic mobile-toggle-nav">
					<span class="hamburger-box"> <span class="hamburger-inner"></span>
					</span>
				</button>
			</div>
		</div>
		<div class="app-header__menu">
			<span>
				<button type="button"
					class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
					<span class="btn-icon-wrapper"> <i
						class="fa fa-ellipsis-v fa-w-6"></i>
					</span>
				</button>
			</span>
		</div>
		<div class="scrollbar-sidebar">
			<div class="app-sidebar__inner">
				<ul class="vertical-nav-menu">
					<li class="app-sidebar__heading">首頁</li>
					<li><a href="../member/GetOnsiteMembers"> <i
							class="metismenu-icon pe-7s-home"></i> 首頁
					</a></li>
					<li class="app-sidebar__heading">分析</li>
					<li><a href="../analysis/GetPurchaseRatio"> <i
							class="metismenu-icon pe-7s-graph"></i> 銷售分析
					</a></li>
					<li><a href="../analysis/FlowOfCustomer"> <i
							class="metismenu-icon pe-7s-graph2"></i> 客流分析
					</a></li>
					<li class="app-sidebar__heading">商品</li>
					<li><a href="../inventory/NewProductJSP"> <i
							class="metismenu-icon pe-7s-pendrive"></i> 新增商品
					</a></li>
					<li><a href="../inventory/ProductList"> <i
							class="metismenu-icon pe-7s-pendrive"></i> 商品列表
					</a></li>
					<li class="app-sidebar__heading">庫存</li>
					<li><a href="../inventory/GetAllInventory"> <i
							class="metismenu-icon pe-7s-cart"></i> 庫存總覽
					</a></li>
					<li class="app-sidebar__heading">會員</li>
					<li><a href="javascript: MakeID()"> <i
							class="metismenu-icon pe-7s-users"></i> 會員總覽
					</a></li>
					<li><a href="../member/InsertGetMember"> <i
							class="metismenu-icon pe-7s-add-user"></i> 新增會員
					</a></li>
					<li style="display: none"><a href="#"> <i
							class="metismenu-icon pe-7s-look"></i> 現場會員
					</a></li>
					<li class="app-sidebar__heading">銷售</li>
					<li><a href="../salesrecord/GetAllSalesRecord?currentpageno=1"> <i
							class="metismenu-icon pe-7s-note2"></i> 銷售紀錄
					</a></li>
					<li class="app-sidebar__heading">影像</li>
					<li><a href="../analysis/live"> <i
							class="metismenu-icon pe-7s-note2"></i> 影像串流
					</a></li>
				</ul>
				<!-- class="mm-active" -->
			</div>
		</div>
	</div>
</body>
<script>
</script>
</html>