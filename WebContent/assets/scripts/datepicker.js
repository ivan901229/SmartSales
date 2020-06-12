    $(function () {
      	 $.datepicker.regional['zh-TW']={
        		   dayNames:["星期日","星期一","星期二","星期三","星期四","星期五","星期六"],
        		   dayNamesMin:["日","一","二","三","四","五","六"],
        		   monthNames:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
        		   monthNamesShort:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
        		   prevText:"上月",
        		   nextText:"次月",
        		   weekHeader:"週"
        		};
        		// 將預設語系設定為中文
        		$.datepicker.setDefaults($.datepicker.regional["zh-TW"]);
        		// 套用到表單
        		$(function() {
        			$( "#日期欄位" ).datepicker({dateFormat: 'yy-mm-dd'});
        		});
          $("#datepicker").datepicker({
        	  changeYear: true,
        	  changeMonth: true,
        	  showMonthAfterYear: true,
              showOn: "focus",
              yearRange: "-100:+0",
              dateFormat: "yy-mm-dd",
              minDate : "-100y",
              maxDate : "+0d"
          });                 
      }); 