<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
		<link rel="stylesheet" type="text/css" href="css/global.css"/>
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
	</head>
	<body>
		<div class="loginBg">
			<img src="image/loginbg.jpg"/>
		</div>
		<div class="loginCase">
			<div class="lc-logo" style="margin-left: 170px">
				<img src="image/loginLogo.jpg"/>
			</div>
			<div class="lc-inp">
				<p class="lc-account">
					<em></em>
					<input type="" name="loginAccount" value="" placeholder="登录账号" />
				</p>
				<p class="lc-password">
					<em></em>
					<input type="" name="password" value="" placeholder="登录密码" />
				</p>
				<div class="lc-code clearfix">
					<p>
						<em></em>
						<input type="" name="captcha" value="" placeholder="图形验证码" />
					</p>
					<img src=""/>
				</div>
				<button class="login-btn">登录</button>
			</div>
		</div>
		<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
//			验证码
 			function getCaptcha(){
				$.ajax({
					//publicPath+"
			        url: publicPath+"/api/user/getCaptcha",
			        type: "post",
			        data: {},
			        dataType: "json",
			        success: function success(data) {
			            if(data.success){
			                if(data.code == 0){
			                	var dataJD = data.jsonData;
			                	var img = "data:image/jpeg;base64,"+dataJD.captcha;
			                	$(".lc-code img").attr("src",img);
			                	console.log(data.msg);
			                }else{
			                    console.log(data.msg);
			                }
			            }else {
			            	console.log(data.msg);
			            }
			        }
			    });
			} 
		
			$(function(){
				 getCaptcha(); 
					$(".lc-code img").click(function(){
						getCaptcha()
					});
//				登录
				$(".login-btn").click(function(){
				var loginAccount = $("input[name='loginAccount']").val();
					var password = $("input[name='password']").val();
					var captcha = $("input[name='captcha']").val();
					$.ajax({
						//publicPath+
				        url: publicPath+"/api/user/login",
				        type: "post",
				        data: {loginAccount:loginAccount,password:password,captcha:captcha},
				        dataType: "json",
				        success: function success(data) {
				            if(data.success){
				                if(data.code == 0){
				                	localStorage.setItem("loginAccount",loginAccount);
				                	location.href="index.jsp";
				                	console.log(data.msg);
				                }else{
				                    console.log(data.msg);
				                }
				            }else if(data.code==100008){
				            	console.log(data.msg);
				                alert("账户不存在！");
				                getCaptcha();
				            }else if(data.code==100007){
				            	console.log(data.msg);
				                alert("密码不正确！");
				                getCaptcha();
				            }else if(data.code==100006){
				            	console.log(data.msg);
				                alert("验证码过期！请重新输入！");
				                getCaptcha();
				            }else if(data.code==100005){
				            	console.log(data.msg);
				            	alert("验证码错误！请重新输入！");
				            	getCaptcha();
				            }
				        }
				    }); 
				});
				$("body").keydown(function(event){
					if (event.keyCode == "13") {
						$(".login-btn").trigger("click");
					}
				});
			});
		</script>
	</body>
</html>
