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
    
    <title>变更管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/js/plugins/select2/select2.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
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
            $(document).ready(function () {
                $("#myTable").dataTable({"oLanguage": {
         			"sUrl": "${contextPath}/resources/json/Chinese.json"
     			},"aaSorting":[[4,'desc']]});
                $(".header").load("${contextPath}/header?t="+pm_random());
                $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
                $(".dateISO").datepicker();
                
                var starttime,endtime,risk,description,applyUser,engineer;
                starttime = pm_getQueryString("startTime");
                endtime = pm_getQueryString("endTime");
                risk = pm_getQueryString("risk");
                description = pm_getQueryString("description");
                applyUser = pm_getQueryString("applyUser");
                engineer = pm_getQueryString("engineer");
                
                if(description!=null)
                	$("input[name='description']").attr("value",description);
                if(applyUser!=null)
                	$("#applyUser option[value='"+applyUser+"']").attr("selected", "selected");
                if(engineer!=null)
                	$("#engineer option[value='"+engineer+"']").attr("selected", "selected");
                
                if(starttime!=null)
                	$("input[name='startTime']").attr("value",starttime);
                else
                	$("input[name='startTime']").attr("value",new Date().format("yyyy") +"-01-01");
                
                if(endtime!=null)
                	$("input[name='endTime']").attr("value",endtime);
                else
                	$("input[name='endTime']").attr("value",new Date().format("yyyy-MM-dd"));
                if(risk!=null)
                	$("#risk option[value='"+risk+"']").attr("selected", "selected");
                
                $("#applyUser").select2();
                $("#engineer").select2();
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
                    <li><a href="${contextPath }/change/list">变更管理</a> <span class="divider">></span></li>       
                    <li class="active">历史变更查询</li>
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
                    <div class="col-md-12">  
                    <div class="block-fluid without-head">
                            	<form action="${contextPath }/change/search">
	                            <div class="toolbar nopadding-toolbar clearfix">
                                	<h4>查询条件</h4>
                           		</div>
                           		<div class="row-form clearfix">
                           			<div class="col-md-1">摘要</div>
	                                <div class="col-md-3"><input type="text" name="description" /></div>
	                                <div class="col-md-1">申请人</div>
	                                <div class="col-md-3">
	                                	<select id="applyUser" name="applyUser" style="width:100%">
	                                	<option value="00">全部</option>
	                                	<c:forEach items="${users }" var="user">
	                                		<option value="${user.username }">${user.name }</option>
	                                	</c:forEach>
	                                	</select>
	                                </div>
	                                <div class="col-md-1">受派人</div>
	                                <div class="col-md-3">
	                                	<select id="engineer" name="engineer" style="width:100%">
	                                	<option value="00">全部</option>
	                                	<c:forEach items="${engineers }" var="user">
	                                		<option value="${user.username }">${user.name }</option>
	                                	</c:forEach>
	                                	</select>
	                                </div>
	                            </div>
                           		<div class="row-form clearfix">
	                                <div class="col-md-1">风险等级</div>
	                                <div class="col-md-3">
	                                	<select name="risk" id="risk">
										<option value="00">全部</option>
										<c:forEach items="${risk }" var="code">
											<option value="${code.code }">${code.codeName }</option>
										</c:forEach>
										</select>
	                                </div>
	                                <div class="col-md-1">开始时间</div>
	                                <div class="col-md-3"><input type="text" name="startTime" class="dateISO"/></div>
	                                <div class="col-md-1">截至时间</div>
	                                <div class="col-md-3"><input type="text" name="endTime" class="dateISO"/></div>
	                            </div>
	                            <div class="footer">
	                            	<button class="btn btn-primary center-block"> 查 询 </button>
	                            </div>
	                            </form>
                		</div>                    
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>已关闭变更</h1>                              
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table style="cellpadding:0;cellspacing:0;width:100%;" class="table" id="myTable">
                                <thead>
                                    <tr>
                                    	<th width="80px">流水号</th>
                                        <th>摘要</th>
                                        <th width="100px">变更申请人</th>
                                        <th width="90px">风险等级</th>
                                        <th width="130px">申请日期</th>
                                        <th width="130px">完成日期</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list }" var="change">
                                	<c:set var="pdid" value="${change.processInstanceId }" />
                                    <tr>
                                    	<td>${change.processInstanceId }</td>
                                        <td>
	                                        <span class="label label-warning tipb" title="优先级">${change.priorityName }</span>
	                                        <a href="${contextPath }/change/view/${change.id}" title="查看详情" class="tipr" target="_blank">${change.description}</a>
                                        </td>
                                         <td>${change.applyUserName }</td>
                                        <td>${change.riskName }</td>
                                       
                                        <td><fmt:formatDate value="${change.applyTime }" pattern="yyyy-MM-dd HH:mm" /></td>
                                        <td><fmt:formatDate value="${change.endTime }" pattern="yyyy-MM-dd HH:mm" /></td>
                                    </tr>
                                   </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>  
                </div>
                
                
            </div>
            <!--workplace end-->
        </div>   
    </div>
</body>

</html>