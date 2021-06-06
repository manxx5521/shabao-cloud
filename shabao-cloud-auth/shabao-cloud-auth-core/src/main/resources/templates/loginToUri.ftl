<!DOCTYPE html>
<html lang="en">
<head>
<#assign ctx=request.contextPath>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit" /><!-- 让360浏览器默认选择webkit内核 -->
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="keywords" content="统一登录中心">
<meta name="description" content="统一登录中心">
<meta name="Authorization" content="${Authorization}"/>
<!--[if lt IE 9]>
	<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<title>统一登录中心</title>
<link href="${ctx}/resources/login/css/login.min.css" rel="stylesheet">
<link href="${ctx}/resources/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/resources/plugins/bootstrap/css/font-awesome.min.css" rel="stylesheet">
<link href="${ctx}/resources/plugins/bootstrap/css/animate.min.css" rel="stylesheet">
<script>
	if (window.top !== window.self) {
		window.top.location = window.location
	};
</script>
</head>
<body class="signin">
	<div class="signinpanel">
		<div class="row">
			<div class="col-sm-7">
				<div class="signin-info">
					<div class="logopanel m-b">
						<h1>[ 小沙包，大梦想 ]</h1>
					</div>
					<div class="m-b"></div>
					<h4>
						欢迎使用 <strong>微信后台管理系统</strong>
					</h4>
					<ul class="m-b">
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>
					</ul>
					<strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
				</div>
			</div>
			<div class="col-sm-5">
				<form method="post" name="loginform" id="loginform">
					<h4 class="no-margins">登录：</h4>
					<p class="m-t-md">登录到H+后台主题UI框架</p>
					<input name="username" type="text" class="form-control uname" placeholder="用户名" /> 
					<input name="password" type="password" class="form-control pword m-b" placeholder="密码" /> <a href="">忘记密码了？</a>
					<input name="grant_type" type="hidden" class="form-control pword m-b" value="password"/>
					<button class="btn btn-success btn-block" type="button">登录</button>
				</form>
			</div>
		</div>
		<div class="signup-footer">
			<div class="pull-left">&copy; 2015 All Rights Reserved. ShaBao
			</div>
		</div>
	</div>
	<script src="${ctx}/resources/plugins/jquery/jquery-1.12.4.min.js"></script>
	<script src="${ctx}/resources/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script>
	  	$(function(){
	  		$('#loginform button').click(function(){
	  			var formdata = $("#loginform").serialize();
	  			var Authorization ="Basic "+$("meta[name='Authorization']").attr("content");
	  			$.ajax({
					type : "POST",
					url : "./oauth/token",
					data:formdata,
					dataType : "json",
					beforeSend: function(request) {
						request.setRequestHeader("Authorization", Authorization);
                    },
					success : function(data) {
						if (!!data.error) {
							alert(data.error)
						}else{
							//"http://localhost:8080/shabao-admin/index?access_token="
							window.location.href='${uri}'+'?access_token='+data.access_token;
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown) {
						//console.log(errorThrown)
						alert('系统错误');
					}
				});
	  		})
	  	})
	  
	  
	  </script>
	
</body>
</html>