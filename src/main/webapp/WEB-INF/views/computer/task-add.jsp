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
    
    <title>终端合规性管理--运维管理系统</title>

    <!-- <link rel="icon" type="image/ico" href="favicon.ico"/> -->
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/uniform.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/multi-select.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/js/plugins/select2/select2.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/uploadify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href='${contextPath }/resources/js/plugins/jstree/jquery.treeview.css' rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uploadify/jquery.uploadify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.js'></script>
     <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
      
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <%--<script type='text/javascript' src='${contextPath }/resources/js/pm-select.js'></script> //IE8+    --%>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
        $(document).ready(function () {
          	$(".header").load("${contextPath }/header?t=" + pm_random());
            $(".menu").load("${contextPath }/menu?t=" + pm_random(), function () { $(".navigation > li:eq(0)").addClass("active"); });
            $(".breadLine .buttons").load("${contextPath }/contentbuttons?t=" + pm_random());
          	
            //表单验证
            $("#validation").validationEngine({promptPosition : "topRight", scroll: true});
            
            $("#btn_save").bind("click",getItem);
            $("#selComputer").multiSelect();
            $("#selItem").multiSelect();
        });
        function getItem(){
        	$("#form_items").attr('value',$("#selItem").val());
        	$("#form_computers").attr('value',$("#selComputer").val());
        	return true;
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
                    <li><a href="#">终端保密检查管理</a> <span class="divider">></span></li>        
                    <li class="active">创建检查任务</li>
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
	                            <h1>检查任务信息</h1>
	                        </div>
	                        <c:url var="addAction" value="/computer/task/save" ></c:url>
	                        <form:form action="${addAction}" commandName="task" method="post" id="validation">
	                        <form:hidden path="id" />
	                        <input id="fileids" name="fileids" type="hidden" />
	                        <div class="block-fluid">                        
	                            <div class="row-form clearfix">
	                                <div class="col-md-2">任务名称:</div>
	                                <div class="col-md-10"><form:input path="taskName" class="validate[required,maxSize[50]]"></form:input></div>
	                             </div>
	                             <div class="row-form clearfix">
	                                <div class="col-md-2">任务提示信息:</div>
	                                <div class="col-md-10"><form:input path="taskInfo" class="validate[required,maxSize[50]]"></form:input></div>
	                             </div>
	                            <div class="row-form clearfix">
	                                <div class="col-md-2">执行客户端:</div>
	                                <div class="col-md-10">
	                                <select multiple class="multiselect" id="selComputer">   
	                                	<c:forEach items="${computers }" var="computer">
										<option value="${computer.id }">[ ${computer.group.groupName} ] ${computer.userName }</option> 
										</c:forEach>
                                    </select>
	                                </div>
	                            </div>
	                            
	                            <div class="row-form clearfix">
	                                <div class="col-md-2">检查指标:</div>
	                                <div class="col-md-10">
	                                <c:forEach items="${targets }" var="target">
	                                <label class="checkbox checkbox-inline">
                                        <input type="radio" name="targetId" value="${target.targetId }"/> ${target.targetName}
                                    </label>
                                    </c:forEach>
	                                </div>
	                            </div>
	                            <div class="row-form clearfix">
	                                <div class="col-md-2">检查项:</div>
	                                <div class="col-md-10">
	                                <label class="checkbox checkbox-inline">
                                        <input type="checkbox" id="chkItem" name="chkItem" value="true"/> 自定义检查项
                                    </label>
	                                <select multiple class="multiselect" id="selItem">   
	                                	<c:forEach items="${items }" var="item">
										<option value="${item.itemId }">${item.itemName }</option> 
										</c:forEach>
                                    </select>
	                                </div>
	                            </div>
                                                 
	                            <div class="footer">
	                                <button class="btn btn-primary center-block" id="btn_save" name="btn_save"> 保 存 </button>
	                            </div>                            
	                        </div>
	                        <input type="hidden" id="form_items" name="form_items" value="0" />
	                        <input type="hidden" id="form_computers" name="form_computers" value="0" />
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
