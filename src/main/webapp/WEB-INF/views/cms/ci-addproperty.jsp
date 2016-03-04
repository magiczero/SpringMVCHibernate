<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>配置管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
    	// 获取ci属性的JSON值
    	var propertiesdata = null; 
    	propertiesdata = '${ci.propertiesData }';
    		
    	$(document).ready(function () {
        	$(".header").load("${contextPath }/header?t="+pm_random());
            $(".menu").load("${contextPath }/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
            $(".breadLine .buttons").load("${contextPath }/contentbuttons?t="+pm_random());

            // 将属性值附加到表单中
            if(propertiesdata!="")
            {   propertiesdata = $.parseJSON(propertiesdata);	
	        	$.each(propertiesdata, function(k,v){
	        		$("input[name='"+k+"']").val(v);
	        	});	
    		}
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
									<c:if test="${map.value.size()==0 }">
										<div class="row-form clearfix"><span style="color:#999;">未定义属性</span></div>
									</c:if>
									<c:if test="${map.value.size()>0 }">
									<c:forEach items="${map.value }" var="property">
										<div class="row-form clearfix">
											<div class="col-md-3">${property.propertyName}:</div>
											<div class="col-md-9">
												<input id="${property.propertyId }" name="${property.propertyId }" type="text" />
											</div>
										</div>
									</c:forEach>
									</c:if>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="row tac">
						<button class="btn btn-primary btn_block"> 保 存 </button>
					</div>
				</form>
			</div>
			<!--workplace end-->
		</div>
	</div>
</body>
</html>