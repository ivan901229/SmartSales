<%@ page language="java" contentType="text/html;charset=UTF-8"
	import="java.util.*,com.lcpan.bean.MemberBean,com.lcpan.bean.UserBean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	UserBean user = (UserBean) session.getAttribute("user");
%>
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

</head>
<body>
	<div class="app-header header-shadow">
		<div class="app-header__logo">
			<div class="logo-src"></div>
			<div class="header__pane ml-auto">
				<div>
					<button type="button"
						class="hamburger close-sidebar-btn hamburger--elastic"
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
		<div class="app-header__content">
			<div class="app-header-left">
				<div class="search-wrapper" id="search-wrapper">
					<div class="input-holder">
						<input type="text" name="keyword" id="search-keyword"
							class="search-input" placeholder="Type to search member profile">
						<button type="submit" class="search-icon" onclick="searchMember()">
							<span></span>
						</button>
					</div>
					<button class="close"></button>
				</div>
			</div>
			<div class="app-header-right">
				<div class="header-btn-lg pr-0">
					<div class="widget-content p-0">
						<div class="widget-content-wrapper">
							<div class="widget-content-left">
								<div class="btn-group">
									<a data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false" class="p-0 btn"> <img width="42"
										class="rounded-circle"
										src="../assets/images/avatars/<%=user.getUserId()%>.jpg"
										alt=""> <i class="fa fa-angle-down ml-2 opacity-8"></i>
									</a>
									<div tabindex="-1" role="menu" aria-hidden="true"
										class="dropdown-menu dropdown-menu-right">
										<button type="button" tabindex="0" class="dropdown-item"
											onclick="javascript:location.href='../LogIn/LogOut'">Logout</button>
									</div>
								</div>
							</div>
							<div class="widget-content-left  ml-3 header-user-info">
								<div class="widget-heading"><%=user.getUserId()%></div>
							</div>
							<div class="widget-content-right header-user-info ml-3">
								<button type="button"
									class="btn-shadow p-1 btn btn-primary btn-sm show-toastr-example">
									<i class="fa text-white fa-calendar pr-1 pl-1"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
				<a href="../pay.jsp">
					<button class="mb-2 mr-2 btn btn-danger">結帳</button>
				</a>
			</div>
		</div>
	</div>
	<div class="ui-theme-settings">
		<div class="scrollbar-container"></div>
	</div>
</body>

</html>