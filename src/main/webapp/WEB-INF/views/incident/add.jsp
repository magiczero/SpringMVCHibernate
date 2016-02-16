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

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/select2.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link rel='stylesheet' type='text/css' href='${contextPath }/resources/css/bootstrap-treeview.css' media='print' />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/treeview/bootstrap-treeview.min.js'></script>
      
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
          	$(".header").load("${contextPath }/header?t=" + pm_random());
            $(".menu").load("${contextPath }/menu?t=" + pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
            $(".breadLine .buttons").load("${contextPath }/contentbuttons?t=" + pm_random());
          	
            //表单验证
            $("#validation").validationEngine({promptPosition : "topLeft", scroll: true});
          
            //选择事件分类
            act_dialog_category_init();
            $("input[name='category']").bind("click",function(){
            	act_dialog_category_select('INCIDENT_CATEGORY','category');
            });
            //输入摘要是自动补全描述字段
            $("#abs").bind("blur",function(){
            	if( $("#detail").val()=="" )
            		$("#detail").text($("#abs").val());
            });
            //初始化表单信息
			initForm();
            //申请人下拉框效果
			$("#applyUser").select2();
        });
        function initForm()
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
                    <li><a href="${contextPath }/incident/list">事件管理</a> <span class="divider">></span></li>   
                    <c:if test="${not empty incident.id }" >     
                    	<li class="active">修改事件信息</li>
                    </c:if>
                    <c:if test="${empty incident.id }" >     
                    	<li class="active">创建新事件</li>
                    </c:if>
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
	                            <div class="isw-empty_document"></div>
	                            <h1>事件信息录入</h1>
	                        </div>
	                        <c:url var="addAction" value="/incident/save" ></c:url>
	                        <form:form action="${addAction}" commandName="incident" method="post" id="validation">
	                        <form:hidden path="id" />
	                        <div class="block-fluid">                        
	                            <div class="row-form clearfix">
	                                <div class="col-md-1"><form:label path="abs">摘要:</form:label></div>
	                                <div class="col-md-11"><form:input path="abs" class="validate[required,maxSize[50]]"></form:input></div>
	                             </div>
	                            <div class="row-form clearfix">
	                                <div class="col-md-1"><form:label path="detail">描述:</form:label></div>
	                                <div class="col-md-11"><form:textarea path="detail" class="validate[required,maxSize[255]]"></form:textarea>
	                                </div>
	                            </div>
	                            <div class="row-form clearfix">
	                                <div class="col-md-1"><form:label path="applyUser">申请人:</form:label></div>
	                                <div class="col-md-3"><form:select path="applyUser" items="${users }" itemLabel="name" itemValue="username" cssStyle="width:100%"></form:select></div>
	                                <div class="col-md-1"><form:label path="phoneNumber">电话:</form:label></div>
	                                <div class="col-md-3"><form:input path="phoneNumber"></form:input></div>
	                            </div> 

	                            <div class="row-form clearfix">
	                           	 	<div class="col-md-1"><form:label path="category">分类:</form:label></div>
	                                <div class="col-md-3"><form:input path="category" class="validate[required,maxSize[50]]"></form:input></div>
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
	                            <div class="footer">
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
    </div>
</body>
</html>