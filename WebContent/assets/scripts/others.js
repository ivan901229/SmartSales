 var xmlhttp;  //for getOnSiteMemberCount
 function loadXMLDoc(url,cfunc)
 {
 	if (window.XMLHttpRequest)
 	{// IE7+, Firefox, Chrome, Opera, Safari 代码
 		xmlhttp=new XMLHttpRequest();
 	}
 	else
 	{// IE6, IE5 代码
 		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
 	}
 	xmlhttp.onreadystatechange=cfunc;
 	xmlhttp.open("GET",url,true);
 	xmlhttp.send();
 }

var xmlhttp1;  //for memberonsitelist & scanNewRFID
 function loadXMLDoc1(url,cfunc)
 {
 	if (window.XMLHttpRequest)
 	{// IE7+, Firefox, Chrome, Opera, Safari 代码
 		xmlhttp1=new XMLHttpRequest();
 	}
 	else
 	{// IE6, IE5 代码
 		xmlhttp1=new ActiveXObject("Microsoft.XMLHTTP");
 	}
 	xmlhttp1.onreadystatechange=cfunc;
 	xmlhttp1.open("GET",url,true);
 	xmlhttp1.send();
 }
 
 var xmlhttp2;  //for paylist
 function loadXMLDoc2(url,cfunc)
 {
 	if (window.XMLHttpRequest)
 	{// IE7+, Firefox, Chrome, Opera, Safari 代码
 		xmlhttp2=new XMLHttpRequest();
 	}
 	else
 	{// IE6, IE5 代码
 		xmlhttp2=new ActiveXObject("Microsoft.XMLHTTP");
 	}
 	xmlhttp2.onreadystatechange=cfunc;
 	xmlhttp2.open("GET",url,true);
 	xmlhttp2.send();
 }
 
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
		setTimeout(function() {	memberOnSiteList();},3000); 
 }
 
 
 
 function scanNewRFID(){
	 loadXMLDoc1("../Ajax/scanNewRFID",
			 function(){
				  	if (xmlhttp1.readyState==4 && xmlhttp1.status==200)
				    {	
				  		$("#newProductRFID").html("");
				  		var RFID = xmlhttp1.responseText;
			            $("#newProductRFID").append("<input class='text-center' readonly value='"+RFID+"' style='width: 100px' type='text' name='rfid' readonly>");
				  		}
				});
	 setTimeout(function() {scanNewRFID();},1000);
 }

 
 function memberOnSiteCount(){
	 loadXMLDoc("../Ajax/OnSiteMemberCount",function()
				{
				  	if (xmlhttp.readyState==4 && xmlhttp.status==200)
				    {
				    	document.getElementById("memberOnSiteCount").innerHTML=xmlhttp.responseText;
				    }
				});
	 setTimeout(function() {
		 memberOnSiteCount();
		}, 3000);
 }
 
 var updateMember=function (memberNo) {
 	
		window.location.href = "../member/UpdateGetMemberNo?memberNo="+memberNo;
	
}

var delMember=function (memberNo) {
	if(confirm("確認刪除？")){ 
		window.location.href = "../member/DeleteMember?memberNo="+memberNo;
	}
}

var searchMember=function () {
    if($("#search-wrapper").hasClass("active")){
           var keyword = document.getElementById("search-keyword").value ;
           window.location.href = "../member/SearchMember?keyword="+keyword;
   }
    else{
            $("#search-keyword").focus();
    }
}  

var toGetAllMembers=function () {
	
	window.location.href = "../member/GetAllMembers?f5V3Ra";

}

var showPhoto=function (memberNo) {
//window.alert(memberNo);
//window.location.href = "../image_test.jsp?memberNo="+memberNo;
	window.open("../image_test.jsp?memberNo="+memberNo);
}

function CheckModify()
{
        if(confirm("確認修改？")==true)   
        	return true;
        else  
        	return false;
}   

function CheckSubmit()
{
        if(confirm("確認新增？")==true)   
        	return true;
        else  
        	return false;
}

function MakeID(){

	var text = "";
	var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	for (var i = 0; i < 5; i++)
		text += possible.charAt(Math.floor(Math.random() * possible.length));
	
	window.location.href=('../member/GetAllMembers?'+text+"&currentpageno=1")
}

function MakeID_2(){

	var text = "";
	var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	for (var i = 0; i < 5; i++)
		text += possible.charAt(Math.floor(Math.random() * possible.length));
	
	window.location.href=('../SmartSales/member/GetAllMembers?'+text)
}

function updateSalesRecord(orderNumber) {     // 更改銷售紀錄
	
	window.location.href = "../salesrecord/UpdateGetSalesRecordNo?orderNumber="+orderNumber;
}	

function delSalesRecord(orderNumber) {       // 刪除銷售紀錄
   if(confirm("確認刪除？")){ 
	   window.location.href = "../salesrecord/DleteSalesRecord?orderNumber="+orderNumber;
   }
}

var toGetAllSalesRecord=function () {
	
	window.location.href = "../salesrecord/GetAllSalesRecord";

}

var toProductList=function () {
	
	window.location.href = "../inventory/ProductList";

}

var toGetAllInventory=function () {
	
	window.location.href = "../inventory/GetAllInventory";

}

function delProduct(productNo) {
	if(confirm("將連同庫存刪除，確認刪除？")){ 
		window.location.href = "../inventory/DeleteProduct?productNo="+productNo;
	}
}

function updateProduct(productNo) {
	
	window.location.href = "../inventory/UpdateGetProductNo1?productNo="+productNo;

}
function updateInventory(productNo) {
 	
	window.location.href = "../inventory/UpdateGetProductNo?productNo="+productNo;

}

$(document).ready(
		function() {
			$("#diamond").click(
					function() {
						$("#table3 .datarow").hide().filter(
								":contains('Diamond')").show(); // 篩選 鑽石
					});
			$("#gold").click(
					function() {
						$("#table3 .datarow").hide().filter(
								":contains('Gold')").show(); // 篩選 黃金
					});
			$("#silver").click(
					function() {
						$("#table3 .datarow").hide().filter(
								":contains('Silver')").show(); // 篩選 銀
					});
			$("#remake").click(
					function() {
						$("#table3 .datarow").hide().filter(
								":contains('')").show(); // 全部
					});
			$("#keyword").keyup(
					function() {
						$("#table3 .datarow").hide().filter(
								":contains('" + ($(this).val()) + "')")
								.show();
					}).keyup(); // 尋找
			

			$("#getyear").click(
					function() {
						var year = new Date().getFullYear()
						if (year){
							$("#table3 .datarow").hide().filter(
									":contains("+year+'-'+")").show();          // 年+"-"
						} // 找年
					});
			$("#getmonth").click(
					function() {
						var year = new Date().getFullYear()
						var month = (new Date().getMonth()+1 <10 ? '0' : '')+(new Date().getMonth()+1);   // getMonth抓到0~11
																											// <10補0
						if (month){
							$("#table3 .datarow").hide().filter(
									":contains("+year+'-'+month+'-'+")").show();  // 年+"-"+月
							//console.log(month)
						}// 找月
					});
			$("#getday").click(
					function() {
						var year = new Date().getFullYear()
						var month = (new Date().getMonth()+1 <10 ? '0' : '')+(new Date().getMonth()+1);   // getMonth抓到0~11
																											// <10補0
						var day = (new Date().getDate() <10 ? '0' : '')+(new Date().getDate())            // getDate()
																											// <10補0
						if (day){
							$("#table3 .datarow").hide().filter(
									":contains("+year+'-'+month+'-'+day+")").show();   // //
																						// 年+"-"+月+"-"+日
							//console.log(day)
						}// 找日
					});
			
		
		});

function paylist(){
	 loadXMLDoc2("../Ajax/pay",function()
				{
				  	if (xmlhttp2.readyState==4 && xmlhttp2.status==200)
				    {
				  		$("#paylist").html("");
				  		var productNo,productName,price,picked;
				  		var totalamount = 0;
				  		var totalprice = 0;
				  		var rsString =eval('(' + xmlhttp2.responseText + ')');
				  		
				  		for(let i=0;i<rsString.length;i++){
				  			productNo = rsString[i].productNo;
				  			productName = rsString[i].productName;
				  			price = rsString[i].price;
				  			picked = rsString[i].picked;
				  			totalamount += parseInt(picked);
				  			totalprice += parseInt(picked)*parseInt(price);
				  		
				  			 console.log(productNo);
			           		$("#paylist").append("<tr class='datarow'>" +
			           				"<td class='text-center' name='productNo'>"+productNo+
			           				"</td><td class='text-center' name='productName'>"+productName+
			           				"</td><td class='text-center' name='price'>"+price+
			           				"</td><td class='text-center' name='picked'>"+picked+
			           				"</td><td class='text-center'><button onclick=\"delPay(\'"+productNo+
			           				"\')\"></button></td></tr>");
				  		}
				  		
				  		$("#paylist").append("<tr class='datarow'>" +
				  				"<td class='text-center'></td>" +
				  				"<td class='text-center'></td>" +
				  				"<td class='text-center'></td>" +
				  				"<td class='text-center'>總數量</td>" +
				  				"<td class='text-center' style='font-weight:bold'>總價</td>" +
				  				"</tr>");
				  		$("#paylist").append("<tr class='datarow'>" +
				  				"<td class='text-center'></td>" +
				  				"<td class='text-center'></td>" +
				  				"<td class='text-center'></td>" +
				  				"<td class='text-center'>"+totalamount+"</td>" +
				  				"<td class='text-center'>"+totalprice+"</td>" +
				  				"</tr>");
				  		
				    }
				});
		setTimeout(function() {	paylist();},3000); 
}


function delPay(productNo) {
	console.log(productNo);
	if(confirm("確認刪除？")){ 
		window.location.href = "../salesrecord/DelPay?productNo="+productNo;
		console.log(ProductNo);
	}
}

