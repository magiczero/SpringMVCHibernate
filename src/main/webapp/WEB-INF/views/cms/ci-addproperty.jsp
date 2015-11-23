<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>配置管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    <link href="${contextPath }/resources/css/stylesheets.css" rel="stylesheet" type="text/css" />
	<link type="text/css" rel="stylesheet" href="${contextPath }/resources/plupload/js/jquery.plupload.queue/css/jquery.plupload.queue.css" />
	
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->  
    
   	<script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fullcalendar/fullcalendar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/animatedprogressbar/animated_progressbar.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cleditor/jquery.cleditor.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/cookies.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/actions.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/settings.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	// 获取ci属性的JSON值
    	var propertiesdata = ${ci.propertiesData };
    		
    	$(document).ready(function () {
        	$(".header").load("${contextPath }/header");
            $(".menu").load("${contextPath }/menu", function () { $(".navigation > li:eq(3)").addClass("active"); });
            $(".breadLine .buttons").load("${contextPath }/contentbuttons");

            // 将属性值附加到表单中
        	$.each(propertiesdata, function(k,v){
        		$("input[name='"+k+"']").val(v);
        	});	
    	}); 
    </script>
</head>
<body>
	<div class="wrapper">
		<!--header-->
		<div class="header"></div>
		<!--header end-->

		<!--menu-->
		<div class="menu"></div>
		<!--menu end-->

		<div class="content">
			<!--breadline-->
			<div class="breadLine">

				<ul class="breadcrumb">
					<li><a href="#">运维管理系统</a> <span class="divider">></span></li>
					<li><a href="${contextPath }/cms/ci/list">配置管理</a> <span class="divider">></span></li>
					<li class="active">配置项属性维护</li>
				</ul>
				<ul class="buttons"></ul>
			</div>
			<!--breadline end-->

			<!--workplace-->
			<div class="workplace">
				<c:url var="addAction" value="/cms/ci/saveproperty/${ci.id }"></c:url>
				<form action="${addAction}" method="post">
					<div class="row">
						<c:forEach items="${properties}" var="map">
							<div class="col-md-4">
								<div class="head clearfix">
									<div class="isw-list"></div>
									<h1>${map.key }</h1>
								</div>
								<div class="block-fluid">
									<c:forEach items="${map.value }" var="property">
										<div class="row-form clearfix">
											<div class="col-md-3">${property.propertyName}:</div>
											<div class="col-md-9">
												<input id="${property.propertyId }" name="${property.propertyId }" type="text" />
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="dr"><span></span></div>
					<div class="row tac">
						<button class="btn btn-primary btn_block">保 存</button>
						<button class="btn btn-info" onclick="g_history_back()">返 回</button>
					</div>
				</form>
			</div>
			<!--workplace end-->
		</div>
	</div>
</body>
</html>