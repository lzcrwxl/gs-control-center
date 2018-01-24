//	路径
var publicPath="http://"+window.location.host+"/fbzkConsole_web";
function height(){
	var dh = $(document).height()-88;
	$(".naviLeft").css("height",dh);
}

var toast = function(){
	$(document.body).append("<div id='toast'>保存成功</div>");
	setTimeout("$('#toast').remove()",3000);
};

//金额处理
function RetainedDecimalPlaces(num) {
		num = parseFloat(num).toFixed(2); 
		var source = String(num).split(".");
		source[0] = source[0].replace(new RegExp('(\\d)(?=(\\d{3})+$)', 'ig'), "$1,");
		return source.join(".");
	};
$(function(){
//	登录账户名
	/*var userName=localStorage.getItem("loginAccount");
	if (userName) {
		$(".account .login").html("<a>欢迎您,"+userName+"</a>")
	};*/

	setTimeout("height()",100);
	
	$(".naviLeft ul li").click(function(){
		$(this).addClass("nl_on").siblings().removeClass("nl_on");
	});
	
	$(".naviLeft ul .beeshare-btn").click(function(){
		var divS = $(this).children("div");
		if (divS.css("display")=="none"){
			divS.slideDown();
		} else {
			divS.slideUp();
		}
	});
	
	
//	开关
	$(".checkbox input").each(function(){
		var State = $(this).prop("checked");
		if (State){
			$(this).val(1).siblings("em").css("left","17px").parent().css("background-color","#0A7EE3");
		} else {
			$(this).val(0).siblings("em").css("left","1px").parent().css("background-color","#B3B3B3");
		}
	});
	
	$(".checkbox input").click(function(){
		var state = $(this).prop("checked");
		if (state){
			$(this).val(1).siblings("em").stop().animate({left:"17px"}).parent().css("background-color","#0A7EE3");
		} else {
			$(this).val(0).siblings("em").stop().animate({left:"1px"}).parent().css("background-color","#B3B3B3");
		}
	});
	
	$("body").on("change",".pic-manage input[type='radio']",function(){
		var checked = $(this).parents(".pic-manage").find(".custom").prop("checked");
		if (checked) {
			$(this).parent().siblings(".upload-sel").children("a").css({"background-color":"#0A7EE3","cursor":"pointer"}).children().css("cursor","pointer").removeAttr("disabled");
			$(this).parent().siblings(".limit").css("color","#333333").children("i").css("color","#FF411B");
		} else {
			$(this).parent().siblings(".upload-sel").children("a").css({"background-color":"#DBDBDB","cursor":"default"}).children().css("cursor","default").attr("disabled","disabled");
			$(this).parent().siblings(".limit").css("color","#DBDBDB").children("i").css("color","#DBDBDB");
		};
	});
	
	//	退出登录
	$(".account ul li:last").click(function(){
		$.ajax({
	        url: "/fbzkConsole_web/api/user/logout",
	        type: "post",
	        data: {},
	        dataType: "json",
	        success: function success(data) {
	            if(data.success){
	                if(data.code == 0){
	                	location.href="login.jsp"
	                	console.log(data.msg);
	                }else{
	                    console.log(data.msg);
	                }
	            }else{
	                console.log(data.msg);
	            }
	        }
	    });
	});
});