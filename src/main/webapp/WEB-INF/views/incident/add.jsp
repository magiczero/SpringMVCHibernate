<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/stylesheets.css" rel="stylesheet" type="text/css" />
	
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    <link rel='stylesheet' type='text/css' href='${contextPath }/resources/css/fullcalendar.print.css' media='print' />
    <link rel='stylesheet' type='text/css' href='${contextPath }/resources/css/bootstrap-treeview.css' media='print' />
    
   <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/sparklines/jquery.sparkline.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fullcalendar/fullcalendar.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    
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
    
    <script type='text/javascript' src='${contextPath }/resources/plupload/js/i18n/zh_CN.js'></script>   
        
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/treeview/bootstrap-treeview.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/cookies.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/myactions.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/charts.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/settings.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/faq.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-select.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
        $(document).ready(function () {
          	$(".header").load("${contextPath }/header");
            $(".menu").load("${contextPath }/menu", function () { $(".navigation > li:eq(2)").addClass("active"); });
            $(".breadLine .buttons").load("${contextPath }/contentbuttons");
            $("input[name='category']").bind("click",function(){
               	act_dialog_select('INCIDENT_CATEGORY','category');
            });
			init();
        });
        function init()
        {
        	$("select[name='influence'] option[value='04']").attr("selected","selected");
        	$("select[name='critical'] option[value='04']").attr("selected","selected");
        	$("select[name='priority'] option[value='04']").attr("selected","selected");
        }
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
                    <li><a href="#">事件管理</a> <span class="divider">></span></li>         
                    <li class="active">创建事件</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">

                <div class="row">
                	<div class="col-md-1"></div>
	                <div class="col-md-10">
	                        <div class="head clearfix">
	                            <div class="isw-documents"></div>
	                            <h1>事件信息录入</h1>
	                        </div>
	                        <c:url var="addAction" value="/incident/save" ></c:url>
	                        <form:form action="${addAction}" commandName="incident" method="post">
	                        <div class="block-fluid">                        
	                            <div class="row-form clearfix">
	                                <div class="col-md-1"><form:label path="abs">摘要:</form:label></div>
	                                <div class="col-md-11"><form:input path="abs"></form:input></div>
	                             </div>
	                            <div class="row-form clearfix">
	                                <div class="col-md-1"><form:label path="detail">描述:</form:label></div>
	                                <div class="col-md-11"><form:textarea path="detail"></form:textarea>
	                                </div>
	                            </div>
	                            <div class="row-form clearfix">
	                                <div class="col-md-1"><form:label path="applyUser">申请人:</form:label></div>
	                                <div class="col-md-3"><form:input path="applyUser"></form:input></div>
	                                <div class="col-md-1"><form:label path="phoneNumber">电话:</form:label></div>
	                                <div class="col-md-3"><form:input path="phoneNumber"></form:input></div>
	                            </div> 

	                            <div class="row-form clearfix">
	                           	 	<div class="col-md-1"><form:label path="category">分类:</form:label></div>
	                                <div class="col-md-3"><form:input path="category"></form:input></div>
	                            	<div class="col-md-1"><form:label path="type">类型:</form:label></div>
	                            	<div class="col-md-3"><form:select path="type" items="${type }" itemLabel="codeName" itemValue="code"></form:select></div>
	                            	<div class="col-md-1"><form:label path="source">来源:</form:label></div>
	                                <div class="col-md-3"><form:select path="source" items="${source }" itemLabel="codeName" itemValue="code"></form:select></div>
	                            </div>      
	                            <div class="row-form clearfix">
	                                <div class="col-md-1"><form:label path="influence">影响度:</form:label></div>
	                                <div class="col-md-3"><form:select path="influence" items="${influence }" itemLabel="codeName" itemValue="code"></form:select></div>
	                                <div class="col-md-1"><form:label path="critical">紧急度:</form:label></div>
	                                <div class="col-md-3"><form:select path="critical" items="${critical }" itemLabel="codeName" itemValue="code"></form:select></div>
	                                <div class="col-md-1"><form:label path="priority">优先级:</form:label></div>
	                                <div class="col-md-3"><form:select path="priority" items="${priority }" itemLabel="codeName" itemValue="code"></form:select></div>
	                            </div>                                                       
	  
	                            <div class="footer tar">
	                                <button class="btn btn-primary center-block"> 保 存 </button>
	                            </div>                            
	                        </div>
	                   		</form:form>
	                    </div>
	                <div class="col-md-1"></div>
                </div>

            </div>
            <!--workplace end-->
        </div>  
        <!-- 事件分类 -->
    	<div class="dialog" id="b_popup_select" style="display: none;" title="事件分类"></div> 
    </div>
    
</body>

</html>