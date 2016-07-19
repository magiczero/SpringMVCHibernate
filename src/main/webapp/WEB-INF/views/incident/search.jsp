<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
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
    <!-- <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.table2excel.min.js'></script> -->  
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
                //$("#myTable").dataTable({"aaSorting":[[4,'desc']]});
                $(".header").load("${contextPath}/header?t="+pm_random());
                $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
               
                $(".dateISO").datepicker();
                
                var starttime,endtime,satisfaction,abs,applyUser,engineer,unit;
                starttime = pm_getQueryString("startTime");
                endtime = pm_getQueryString("endTime");
                satisfaction = pm_getQueryString("satisfaction");
                abs = pm_getQueryString("abstract");
                applyUser = pm_getQueryString("applyUser");
                engineer = pm_getQueryString("engineer");
                unit = pm_getQueryString("unit");
                
                if(abs!=null)
                	$("input[name='abstract']").attr("value",abs);
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
                if(satisfaction!=null)
                	$("#satisfaction option[value='"+satisfaction+"']").attr("selected", "selected");
                if(unit!=null)
                	$("#unit option[value='"+unit+"']").attr("selected", "selected");
                
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
                    <li><a href="${contextPath }/incident/list">事件管理</a> <span class="divider">></span></li>       
                    <li class="active">历史事件查询</li>
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
                            	<form action="${contextPath }/incident/search">
	                            <div class="toolbar nopadding-toolbar clearfix">
                                	<h4>查询条件</h4>
                           		</div>
                           		<div class="row-form clearfix">
                           			<div class="col-md-1">单位</div>
                           			<div class="col-md-11"><select id="unit" name="unit" style="width:100%">
	                                	<option value="00">全部</option>
	                                	<c:forEach items="${units }" var="group">
	                                		<option value="${group.id }">${group.groupName }</option>
	                                	</c:forEach>
	                                	</select></div>
                           		</div>
                           		<div class="row-form clearfix">
                           			<div class="col-md-1">摘要</div>
	                                <div class="col-md-3"><input type="text" name="abstract" /></div>
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
	                                <div class="col-md-1">满意度</div>
	                                <div class="col-md-3">
	                                	<select name="satisfaction" id="satisfaction">
										<option value="00">全部</option>
										<c:forEach items="${satisfaction }" var="code">
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
                            <h1>已关闭事件</h1>                             
                        </div>
                        <div class="block-fluid clearfix">
                            <table class="table" id="myTable">
                                <thead>
                                    <tr>
                                        <th>摘要</th>
                                        <th width="100px">申请人</th>
                                        <th width="100px">受派者</th>
                                        <th width="80px">满意度</th>
                                        <th width="120px">申请时间</th>
                                        <th width="120px">关闭时间</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list }" var="incident">
                                	<c:set var="pdid" value="${incident.processInstanceId }" />
                                    <tr>
                                        <td>
                                        	<span class="label label-warning tipb" title="优先级">${incident.priorityName }</span>
                                        	<a href="${contextPath }/incident/view/${incident.id}" title="查看详情" class="tipr" target="_blank">${incident.abs }</a>
                                        </td>
                                        <td>${incident.applyUserName }</td>
                                        <td>${incident.currentDelegateUserName }</td>
                                        <td>${incident.satisfactionName }</td>
                                        <td><fmt:formatDate value="${incident.applyTime }" pattern="yyyy-MM-dd HH:mm" /></td>
                                        <td><fmt:formatDate value="${incident.recoverTime }" pattern="yyyy-MM-dd HH:mm" /></td>
                                    </tr>
                                   </c:forEach>
                                </tbody>
                            </table>
                            <div class="toolbar bottom-toolbar clearfix">
                            	共 ${count } 个结果
                                <tag:paginate max="5" offset="${offset}" count="${count}" uri="${url }" />
                            </div>
                        </div>
                    </div>  
                </div>
            </div>
            <!--workplace end-->
        </div> 
    </div>
</body>

</html>