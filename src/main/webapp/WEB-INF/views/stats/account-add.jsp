<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>报表与统计管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/multi-select.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.edit.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.async.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-select.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-account.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
    	var data = [];
    	var data_index=0;
        $(document).ready(function () {
            	$(".header").load("${contextPath }/header?t="+pm_random());
            	$(".menu").load("${contextPath }/menu?t="+pm_random(), function() {$("#node_${moduleId}").addClass("active");});
            	$(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
                act_dialog_ci_init();
                $("input[name='category']").bind("click",function(){
                	act_dialog_ci_select("category",false);
                });
                
                $("#btn_save").bind("click",function(){
                	$("#fm_fields").attr("value",$("#sel_fields").val());
                	$("#fm_properties").attr("value",$("#sel_properties").val());
                });
                
                $("#sel_fields").multiSelect("init");
        });
        
        function initTable(code) {
        	$("input[name='category']").attr("value",code);
    		pm_account_setProperty(code);
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
                    <li><a href="#">报表与统计管理</a> <span class="divider">></span></li>       
                    <li class="active">台账</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">             

				<div class="alert alert-danger hide">                
                    <h4>错误!</h4>请至少选择一项
                </div> 
                <div class="row">
                	<div class="col-md-3">
                        <div class="head clearfix">
                            <div class="isw-list"></div>
                            <h1>台账管理</h1>
                        </div>
                        <div class="block-fluid accordion">
                            <h3>台账</h3>
                            <div class="active">
                                 <ul>
                                 	<c:forEach items="${accounts }" var="account">
                                 	<li><a href="${contextPath }/stats/account/get/${account.id}">${account.name }</a></li>
                                 	</c:forEach>
                                </ul>                                               
                            </div>

                            <h3>自定义</h3>
                            <div>
                                <ul>
                                	<li><a href="${contextPath }/stats/account/list">台账信息列表</a></li>
                                    <li><a href="${contextPath }/stats/account/setting">自定义台账</a></li>
                                </ul>                                                
                            </div>                    
                        </div>
                    </div> 
	                <div class="col-md-9">
	                	<div class="head clearfix">
	                    	<div class="isw-documents"></div>
	                        <h1>设置台账信息</h1>
	                     </div>
	                     <div class="block-fluid">
	                     	<form action="${contextPath}/stats/account/save" method="post">
	                     	<div class="row-form clearfix">
		                    	<div class="col-md-2">台账名称</div>
		                        <div class="col-md-10"><input name="fm_name" id="fm_name" type="text"></div>
		                	</div>
		                	<div class="row-form clearfix">
		                    	<div class="col-md-2">选择类别</div>
		                        <div class="col-md-10"><input name="category" id="category" type="text"></div>
		                	</div>
		                	<div class="row-form clearfix">
		                    	<div class="col-md-2">选择字段</div>
		                        <div class="col-md-10">
		                        	<select multiple class="multiselect" id="sel_fields" name="sel_fields">
		                        		<c:forEach items="${fields }" var="property">
		                        		<option value="${property.propertyId }">${property.propertyName }</option>
		                        		</c:forEach>                              
                                    </select>
		                        </div>
		                	</div>
		                	<div class="row-form clearfix">
		                    	<div class="col-md-2">选择属性</div>
		                        <div class="col-md-10">
		                        	<select multiple class="multiselect" id="sel_properties" name="sel_properties"></select>
		                        </div>
		                	</div>
		                	<div class="footer">
		                		<input type="hidden" id="fm_fields" name="fm_fields" value="0" />
		                		<input type="hidden" id="fm_properties" name="fm_properties" value="0" />
	                        	<button class="btn btn-primary center-block" id="btn_save"> 保 存 </button>
	                        </div>
	                        </form>
	                	</div>
	                </div>
                </div>
            </div>
            <!--workplace end-->
        </div> 
    </div>
</body>

</html>