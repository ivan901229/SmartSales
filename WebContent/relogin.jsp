<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
    <title>智能零售</title>

    <!-- Bootstrap -->
    <link href="./assets/css/bootstrap-grid.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="./assets/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="./assets/css/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="./assets/css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="./assets/css/custom.min.css" rel="stylesheet">
	
</head>
<body onunload="if(event.clientY<0) document.location=document.location.href">
	<div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form method="post" action="../SmartSales/LogIn/LogIn">
              <h1>智能零售-登入</h1>
              <div>
                <input type="text" class="form-control" placeholder=" Username" name="userID"  
                	style="height:50px; text-indent:10px;" onfocus="this.placeholder=''" onblur="this.placeholder='Username'"  />
              </div>
              <div>
                <input type="password" class="form-control" placeholder=" Password" name="password"  
               	 	style="height:50px; text-indent:10px;" onfocus="this.placeholder=''" onblur="this.placeholder='Password'" />
              </div>
              <div>
              	<button type="submit" class="btn btn-default submit">Log in</button><br>
              	<h2 style="color:white">${error}</h2>
              	<h2 style="color:white">${relogin}</h2>
                <!--  <a class="" href="./logintest.jsp">Log in</a> -->
                
              </div>

              <div class="clearfix"></div>

              
            </form>
          </section>
        </div>

        
      </div>
    </div>

</body>
</html>