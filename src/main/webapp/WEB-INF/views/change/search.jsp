<%@page import="com.cngc.utils.activiti.ProcessDefinitionCache,org.activiti.engine.RepositoryService,org.activiti.engine.RuntimeService"%>
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
    
    <link href="${contextPath }/resources/css/stylesheets.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    <link rel='stylesheet' type='text/css' href='${contextPath }/resources/css/fullcalendar.print.css' media='print' />
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.stack.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.pie.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.resize.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/sparklines/jquery.sparkline.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fullcalendar/fullcalendar.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-en.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/animatedprogressbar/animated_progressbar.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cleditor/jquery.cleditor.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
        
    <!-- <script type='text/javascript' src='../../../bp.yahooapis.com/2.4.21/browserplus-min.js'></script> -->

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.gears.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.silverlight.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.flash.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.browserplus.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.html4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.html5.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/jquery.plupload.queue/jquery.plupload.queue.js'></script>    
    
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/cookies.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/myactions.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/charts.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/settings.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/faq.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
            $(document).ready(function () {
                $("#myTable").dataTable({"aaSorting":[[3,'desc']]});
                $(".header").load("${contextPath}/header");
                $(".menu").load("${contextPath}/menu", function () { $(".navigation > li:eq(3)").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons");
                $(".dateISO").datepicker();
                var starttime,endtime,risk,description,applyuser,engineer;
                starttime = pm_getQueryString("startTime");
                endtime = pm_getQueryString("endTime");
                risk = pm_getQueryString("risk");
                description = pm_getQueryString("description");
                applyuser = pm_getQueryString("applyUser");
                engineer = pm_getQueryString("engineer");
                
                if(description!=null)
                	$("input[name='description']").attr("value",description);
                
                if(applyuser!=null)
                	$("input[name='applyUser']").attr("value",applyuser);
                if(engineer!=null)
                	$("input[name='engineer']").attr("value",engineer);
                
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
                    <li><a href="${contextPath }/Asset/list">变更管理</a> <span class="divider">></span></li>       
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
	                                <div class="col-md-3"><input type="text" name="applyUser" /></div>
	                                <div class="col-md-1">受派人</div>
	                                <div class="col-md-3"><input type="text" name="engineer" /></div>
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
	                            <div class="footer tac">
	                            	<button class="btn btn-primary"> 查询 </button>
	                            </div>
	                            </form>
                		</div>                    
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>已关闭变更</h1>  

                            <ul class="buttons">
                                <li>
                                    <a href="${contextPath }/change/add" class="isw-text_document tipb" title="新变更"></a>
                                </li>                            
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                        <li><a href="#"><span class="isw-list"></span> 查看全部</a></li>
                                        <li><a href="#"><span class="isw-ok"></span> 查看未指派</a></li>
                                        <li><a href="#"><span class="isw-minus"></span> 查看已超期事件</a></li>
                                        <li><a href="#"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table cellpadding="0" cellspacing="0" width="100%" class="table" id="myTable">
                                <thead>
                                    <tr>
                                        <th>摘要</th>
                                        <th width="90px">变更申请人</th>
                                        <th width="80px">风险等级</th>
                                        <th width="140px">申请日期</th>
                                        <th width="140px">完成日期</th>
                                        <th width="60px">操作</th>                                  
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list }" var="change">
                                	<c:set var="pdid" value="${change.processInstanceId }" />
                                    <tr>
                                        <td>
	                                        <span class="label label-warning tipb" title="优先级">${change.priorityName }</span>
	                                        ${change.description}
                                        </td>
                                         <td>${change.applyUserName }</td>
                                        <td>${change.riskName }</td>
                                       
                                        <td><fmt:formatDate value="${change.applyTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><fmt:formatDate value="${change.endTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td>
                                        	<a href="${contextPath }/change/deal/${change.id}">查看</a>
                                        </td>
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