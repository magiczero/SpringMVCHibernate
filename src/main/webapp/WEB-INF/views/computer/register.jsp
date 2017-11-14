<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv=“X-UA-Compatible” content="IE=edge,chrome=1">
    <![endif]-->
    
    <title>运维客户端注册</title>

	<link href="${contextPath }/resources/css/stylesheets.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/uniform.default.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/login.css" rel="stylesheet" type="text/css" />
    
    <!--[if lt IE 8]>
        <link href="css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/sparklines/jquery.sparkline.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/animatedprogressbar/animated_progressbar.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cleditor/jquery.cleditor.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.js"></script>
    <![endif]-->
    <script type="text/javascript">
	    $(document).ready(function(){
	    	//$("#action").validationEngine({promptPosition : "topRight", scroll: true});
	    	//$(".row-form,.row,.dialog,.loginBox,.block,.block-fluid").find("input:checkbox, input:radio, input:file").not(".skip, input.ibtn").uniform();
	    	$("input,select").width("60%");
	    });
    </script>
</head>
<body>
<div class="col-md-12">
	<form action="${contextPath}/interface/action"  method="post" id="form1" name="form1">
	<input id="action" name="action" value="SaveClient" type="hidden" />
	<div class="col-md-12" style="padding:0px 0px 0px 0px;">
		<div class="block-fluid without-head" >
			<div class="toolbar nopadding-toolbar">
				<h4>终端注册</h4>
			</div> 
			<div class="row-form clearfix">
				<div class="col-md-12">计算机名：
					<input type="text" name="USER_NAME1" class="validate[required,maxSize[50]]"></input>
				</div>
			</div>
			<div class="row-form clearfix">
				<div class="col-md-12">所在部门：
							<select name="group">
								<c:forEach items="${groupList }" var="group">
									<option value="${group.id }">&nbsp;${group.groupName }</option>
									<c:forEach items="${group.child }" var="child">
										<option value="${child.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|
											- ${child.groupName }</option>
										<c:forEach items="${child.child }" var="child1">
											<option value="${child1.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|
												- ${child1.groupName }</option>
										</c:forEach>
									</c:forEach>
								</c:forEach>
							</select>
						</div>
			</div>

			<div class="row-form clearfix">
				<div class="col-md-12">
					磁盘序列号：<input type="text" name="txtDiskNo" class="validate[required,maxSize[50]]"></input>
				</div>
			</div>

			<div class="row-form clearfix">
				<div class="col-md-12">
					MAC地址：<input type="text" name="txtMAC" class="validate[required,maxSize[50]]"></input>
				</div>
			</div>

			<div class="row-form clearfix">
				<div class="col-md-12">
					IP地址:<input type="text" name="txtIP" class="validate[required,maxSize[50]]"></input>
				</div>
			</div>

			<div class="row-form clearfix">
				<div class="col-md-12">
					软件版本：<input type="text" name="txtVersion" class="validate[required,maxSize[50]]"></input>
				</div>
			</div>

			<div class="footer" style="text-align:center">
				<input type="submit" value=" 保 存 "  class="btn btn-primary center-block center"/>
			</div>
		</div>
	</div>
	</form>
</div>
</body>
</html>