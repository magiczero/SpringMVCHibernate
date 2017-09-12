<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv=“X-UA-Compatible” content="IE=edge,chrome=1">
    <![endif]-->
    
    <title>登录系统</title>

	<link href="/PmSys/resources/css/stylesheets.css" rel="stylesheet" type="text/css" />
    <link href="/PmSys/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="/PmSys/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="/PmSys/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="/PmSys/resources/css/uniform.default.css" rel="stylesheet" type="text/css" />
    <link href="/PmSys/resources/css/login.css" rel="stylesheet" type="text/css" />
    
    <!--[if lt IE 8]>
        <link href="css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    
    <script type='text/javascript' src='/PmSys/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='/PmSys/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='/PmSys/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='/PmSys/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    
    <script type='text/javascript' src='/PmSys/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    
    <script type='text/javascript' src='/PmSys/resources/js/plugins/bootstrap.min.js'></script>
    
    <script type='text/javascript' src='/PmSys/resources/js/plugins/sparklines/jquery.sparkline.min.js'></script>
    
    <script type='text/javascript' src='/PmSys/resources/js/plugins/uniform/uniform.js'></script>
    
    <script type='text/javascript' src='/PmSys/resources/js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>
    
    <script type='text/javascript' src='/PmSys/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
    <script type='text/javascript' src='/PmSys/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    
    <script type='text/javascript' src='/PmSys/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='/PmSys/resources/js/plugins/animatedprogressbar/animated_progressbar.js'></script>
    
    <script type='text/javascript' src='/PmSys/resources/js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>
    
    <script type='text/javascript' src='/PmSys/resources/js/plugins/cleditor/jquery.cleditor.js'></script>
    
    <script type='text/javascript' src='/PmSys/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    
    <script type='text/javascript' src='/PmSys/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
    
    <script type='text/javascript' src='/PmSys/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='/PmSys/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='/PmSys/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="/PmSys/resources/js/html5shiv.js"></script>
      <script src="/PmSys/resources/js/respond.js"></script>
    <![endif]-->
    <script type="text/javascript">
	    $(document).ready(function(){
	    	$("#validation").validationEngine({promptPosition : "topRight", scroll: true});
	    	$(".row-form,.row,.dialog,.loginBox,.block,.block-fluid").find("input:checkbox, input:radio, input:file").not(".skip, input.ibtn").uniform();
	    });
    </script>
</head>
<body>
<div class="loginBlock" id="login" style="display: block;">
        <h1>欢迎使用，请登录</h1>
         
        <div class="dr"><span></span></div>
        <div class="loginForm"> 
            <form class="form-horizontal" action="/PmSys/login" method="POST" id="validation">
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                        <input type="text" name="username" id="inputEmail" placeholder="用户名" class="form-control validate[required]"/>
                    </div>                
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                        <input type="password" name="password" id="inputPassword" placeholder="密码" class="form-control validate[required]"/>
                    </div>
                </div>
                <div class="row">
                   <!--  <div class="col-md-8">
                    <div class="form-group" style="margin-top: 5px;">
                            <label class="checkbox"><input name="remember-me" type="checkbox"/>  记住我的信息</label>                                                
                        </div>  
                    </div> -->
                    <div class="col-md-4"></div>
                    <div class="col-md-4">
                        <button type="submit" class="btn btn-default btn-block">登 录</button>       
                    </div>
                    <div class="col-md-4"></div>
                </div>
            </form>  
            <div class="dr"><span></span></div>
            <div class="controls">
                <div class="row">
                    <div class="col-md-6">&nbsp;</div>                    
                    <div class="col-md-6">&nbsp;</div>
                </div>
            </div>
        </div>
    </div>    

</body>
</html>