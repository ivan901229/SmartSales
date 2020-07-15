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
			           				")'><img src='"+photoURL+
			           				"' style='height: 60px' /></button></td><td class='text-center'>"+
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
	if(confirm("確認修改？")){
		window.location.href = "../member/UpdateGetMemberNo?memberNo="+memberNo;
	}
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
				  		var memberNumber = $("#memberNumber").html();
				  		var memberName = $("#memberName").html();
				  		var memberLevel = $("#memberLevel").html();
				  		var memberDiscount = $("#memberDiscount").html();
				  		var memberGender =$('input[name=SEX]:checked').val();
				  		if(memberName == undefined){
				  			memberNumber="";
				  			memberName="";
				  			memberLevel="";
				  			memberDiscount="";
				  			memberGender="";
				  		}
				  		$("#pricelist").html("");
				  		$("#totalPrice").html("");
				  		
				  		var productNo,productName,price,picked;
				  		var totalamount = 0;
				  		var defaultprice = 0;
				  		var totalPrice = 0;
				  		var rsString =eval('(' + xmlhttp2.responseText + ')');
				  		if(memberDiscount==''){
				  			// console.log("非會員");
				  			memberDiscount=1;
				  		}
				  		
				  		console.log(totalPrice);
				  		for(let i=0;i<rsString.length;i++){
				  			productNo = rsString[i].productNo;
				  			productName = rsString[i].productName;
				  			price = rsString[i].price;
				  			picked = rsString[i].picked;
				  			totalamount += parseInt(picked);
				  			defaultprice += parseInt(picked)*parseInt(price);
				  			totalPrice += Math.round(parseInt(picked)*parseInt(price)*parseFloat(memberDiscount));
				  		
			           		$("#paylist").append("<tr class='datarow'>" +
			           				"<td class='text-center' name='productNo'>"+productNo+
			           				"</td><td class='text-center' name='productName'>"+productName+
			           				"</td><td class='text-center' name='picked'>"+picked+
			           				"</td><td class='text-center' name='price'>"+price+
			           				"</td><td class='text-center'><a href=\"javascript: delPay(\'"+productNo+
			           				"\')\"><i class='fas fa-trash'></i></a></td></tr>");
				  		}
				  		
				  		$("#pricelist").append("<tr class='datarow'>" +
				  				"<td class='text-center' id='memberNumber'>"+memberNumber+"</td>" +
				  				"<td class='text-center' id='memberName'>"+memberName+"</td>" +
				  				"<td class='text-center' id='memberLevel'>"+memberLevel+"</td>" +
				  				"<td class='text-center' id='totalamount'>"+totalamount+"</td>" +
				  				"<td class='text-center' id='defaultprice'>"+defaultprice+"</td>" +
				  				"<td class='text-center' id='memberDiscount'>"+memberDiscount+"</td>" +
				  				"<td class='text-center' id='memberGender' style='display:none'>"+memberGender+"</td>" +
				  				"</tr>");
				  		//console.log("折扣為:"+memberDiscount);
				  		

				  		$("#totalPrice").append("總計："+totalPrice);
				    }
				});
		//setTimeout(function() {	paylist();},1000); 
}

//結帳頁面-刪除單筆
function delPay(productNo) {
	console.log(productNo);
	if(confirm("確認刪除？")){ 
		window.location.href = "../salesrecord/DelPay?productNo="+productNo;
		console.log(ProductNo);
	}
}


//結帳頁面-清除結帳
function cleanPayAll() {
	if(confirm("確認刪除？")){ 
		window.location.href = "../salesrecord/CleanPayAll";
		console.log("clean");
	}
}

//var keysArr = new Array("productNo", "productName", "amount","price");
var keysArr = new Array("productNo","","amount","price");


function payListToJSON(){
	var rows = $("#paylist > tr").length; //獲得列數
	var colums = document.getElementById("table3").rows[0].cells.length; //獲得行數
	console.log(rows);
	console.log(colums);
	var json = "[";
	var tdValue;
	for (var i = 0; i < rows; i++) { //每列
		json += "{";
		for (var j = 0; j < colums-1; j++) { //colums-1 不要包含刪除欄位
			tdName = keysArr[j]; //Json資料的鍵
			if(j==1){
				continue;
			}
			json += "\"";
			json += tdName;
			json += "\"";
			json += ":";
			tdValue = document.getElementById('paylist').rows[i].cells[j].innerHTML;//Json資料的值
				json += "\"";
				json += tdValue;
				json += "\"";
				json += ",";
			}
			json = json.substring(0, json.length - 1);
			json += "}";
			json += ",";
		}
		json = json.substring(0, json.length - 1);
		json += "]";
		// console.log(json);
		
		var memberNumber = $("#memberNumber").html();
  		var memberName = $("#memberName").html();
  		var memberLevel = $("#memberLevel").html();
  		var memberDiscount = $("#memberDiscount").html();
  		var memberGender = $('input[name=SEX]:checked').val();
  		var totalPrice = $("#totalPrice").html();
  		if(memberNumber==""){                // 如果非會員，填入-1
  			memberNumber="-1";
  		}
		if(memberGender=="male" || memberGender=="female"){
			if(confirm("確認送出？")){ 
				$.ajax({
					type: "POST", 								    //訪問方式
	            	url: "../salesrecord/PayPageInsertSalesRecord", //訪問路徑
	            	//contentType: "application/json;charset=utf-8",
	            	async: 'false',
	            	data: 'memberNumber='+memberNumber+'&'+
	                  	'memberName='+memberName+'&'+
	                  	'memberDiscount='+memberDiscount+'&'+
	                  	'memberGender='+memberGender+'&'+
	                  	'totalPrice='+totalPrice+'&'+
	            	  	'payListJSON='+json ,                       //傳入服務端的資料
	            	  success: function(data) { 
	            		alert('送出成功');
	            		$("#memberNumber").html(""); 
	            		$("#memberName").html("");                   //清除會員資料
			  			$("#memberLevel").html("");
			  			$("#memberDiscount").html("");
			  			$('#genderMale').prop( "checked", false ).prop( "disabled", false );
						$('#genderFemale').prop( "checked", false ).prop( "disabled", false );
	            	}, 
	            	error: function(data) { 
	            		alert('送出失敗'); 
	            	} 
				}); 
			}
		}
		else{
			alert("此客戶非會員，請勾選客戶性別!");
		}
	}

function salesTotalPrice(){
	 loadXMLDoc3("../Ajax/SalesRecordTotalPrice",function()
				{
				  	if (xmlhttp3.readyState==4 && xmlhttp3.status==200)
				    {
				    	document.getElementById("salesTotalPrice").innerHTML=xmlhttp3.responseText;
				    }
				});
	 setTimeout(function() {
		 salesTotalPrice();
		}, 3000);
 
}

var xmlhttp3;  //for salesTotalPrice
function loadXMLDoc3(url,cfunc)
{
	if (window.XMLHttpRequest)
	{// IE7+, Firefox, Chrome, Opera, Safari 代码
		xmlhttp3=new XMLHttpRequest();
	}
	else
	{// IE6, IE5 代码
		xmlhttp3=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp3.onreadystatechange=cfunc;
	xmlhttp3.open("GET",url,true);
	xmlhttp3.send();
}

function cameraOn_1(){
	$.ajax({
		type: "get", 								    //訪問方式
    	url: "../Ajax/CameraOn_1", //訪問路徑
    	//contentType: "application/json;charset=utf-8",
    	async: 'false',
    	success: function(data) { 
    		alert('開啟中，請稍後');
    	}, 
    	error: function(data) { 
    		alert('開啟錯誤'); 
    	} 
	}); 
}

function cameraOff_1(){
	$.ajax({
		type: "get", 								    //訪問方式
    	url: "../Ajax/CameraOff_1", //訪問路徑
    	//contentType: "application/json;charset=utf-8",
    	async: 'false',
    	success: function(data) { 
    		alert('關閉中，請稍後');
    	}, 
    	error: function(data) { 
    		alert('關閉錯誤'); 
    	} 
	}); 
}

function facialScanON(){
	alert('擷取中，請稍後');
	$.ajax({
		type: "get", //訪問方式
    	url: "../Ajax/FacialScan", //訪問路徑
    	//contentType: "application/json;charset=utf-8",
    	async: 'false',
    	data: 'memberNo='+ $("#memberNo").val(),
    	success: function(data) { 
    		alert('擷取完成');
    	}, 
    	error: function(data) { 
    		alert('擷取錯誤'); 
    	} 
	}); 
}


function facialScanCounter(){        // 櫃台人臉辨識
		var memberName = $("#memberName").html();
		console.log(memberName);
	if(memberOnCounter == false && memberName==""){
		$.ajax({
			type: "get", //訪問方式
	    	url: "../member/searchMemberFace", //訪問路徑
	    	//contentType: "application/json;charset=utf-8",
	    	async: 'false',
	    	success: function(data) { 
	    		if(confirm('偵測到會員，請問是否要帶入會員資料')){
	    			window.location.href = "../member/SearchMemberFace";
	    		}
	    		memberOnCounter = true;
	    	}
		}); 
	}
	
	setTimeout(function() {
		salesTotalPrice();
	}, 1000);
}