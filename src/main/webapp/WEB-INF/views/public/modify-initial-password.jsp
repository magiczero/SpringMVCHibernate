<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>事件管理--运维管理系统</title>

    <!-- <link rel="icon" type="image/ico" href="favicon.ico"/> -->
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
   	<script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
      
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
        $(document).ready(function () {
          	$(".header").load("${contextPath }/header?t=" + pm_random());
            $(".breadLine .buttons").load("${contextPath }/contentbuttons?t=" + pm_random());
          	
            //表单验证
			$("#pwdForm").validationEngine({
				promptPosition : "topRight",
				scroll : true
			});
            
        });
       
    </script>
</head>
<body>
    <div class="wrapper"> 

        <!--header-->
        <div class="header"></div>
        <!--header end-->
        

        <div class="content" style="margin-left:0;">
            <!--breadline-->
            <div class="breadLine">
                <ul class="breadcrumb">
                    <li><a href="#">运维管理系统</a> <span class="divider">></span></li><li class="active">初始密码修改</li>  
                </ul>
                <ul class="buttons"></ul>
            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="row">
                <div class="workplace">
                	<div class="col-md-3"></div>
	                <div class="col-md-6">
	                        <div class="head clearfix">
	                            <div class="isw-empty_document"></div>
	                            <h1>初始密码修改</h1>
	                        </div>
	                       	<form id="pwdForm" action="${contextPath }/user/update-pwd" method="post">
	                        <input name="oldPwd" type="hidden" value="123456" />
	                        <div class="block-fluid">   
	                        	<div class="row-form clearfix">
	                                <div class="col-md-12">密码修改成功后直接跳转到登录页面</div>
	                            </div>                     
	                            <div class="row-form clearfix">
	                                <div class="col-md-3"><label for="user_name">新密码:</label></div>
	                                <div class="col-md-9"><input type="password" id="newPwd" name="newPwd" class="validate[required,custom[password0]]" /></div>
	                            </div>
	                            <div class="row-form clearfix">
	                               	<div class="col-md-3"><label for="cmdb">新密码确认:</label></div>
                                	<div class="col-md-9">                                
                                    <input type="password" id="repeatPwd" name="repeatPwd" class="validate[required,equals[newPwd]]" />
                                </div>
	                            </div> 
	                            <div class="footer">
	                                <button class="btn btn-primary center-block"> 保 存 </button>
	                            </div>                            
	                        </div>
	                   		</form>
	                </div>
	                <div class="col-md-3"></div>
                </div>

            </div>
            <!--workplace end-->
        </div>  
    </div>
</body>
</html>
