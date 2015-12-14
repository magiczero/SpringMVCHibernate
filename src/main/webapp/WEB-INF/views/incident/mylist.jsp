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
    
    <title>事件管理--运维管理系统</title>

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
    <script type='text/javascript' src='${contextPath }/resources/js/pm-workflow.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-knowledge.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
            $(document).ready(function () {
                $("#myTable").dataTable({"aaSorting":[[4,'desc']]});
                $(".header").load("${contextPath}/header");
                $(".menu").load("${contextPath}/menu", function () { $(".navigation > li:eq(1)").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons");
                $(".confirm").bind("click",function(){
                	if(!confirm("确定要执行该操作?"))
                		return false;
                });
                pm_knowledge_initdialog();
                pm_workflow_inittracedialog();
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
                    <li class="active">处理中的报修</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">             
			<%
				RuntimeService runtimeService  = (RuntimeService)request.getAttribute("runtime");
				RepositoryService repositoryService = (RepositoryService)request.getAttribute("res");
				ProcessDefinitionCache.setRuntimeService(runtimeService);
				ProcessDefinitionCache.setRepositoryService(repositoryService);
			%>
				<div class="alert alert-danger hide">                
                    <h4>错误!</h4>请至少选择一项
                </div> 
                <div class="row">
                    <div class="col-md-12">                    
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>处理中的报修</h1>  

                            <ul class="buttons">
                                <li>
                                    <a href="${contextPath }/incident/add" class="isw-text_document tipb" title="新事件"></a>
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
                                        <th width="100px">申请人</th>
                                        <th width="100px">受派者</th>
                                        <th width="140px">流程步骤</th>
                                        <th width="110px">申请时间</th>
                                        <th width="80px">状态</th> 
                                        <th width="60px">操作</th>                                    
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list }" var="incident">
                                	<c:set var="pdid" value="${incident.processInstanceId }" />
                                    <tr>
                                        <td>
                                        	<span class="label label-warning tipb" title="优先级">${incident.priorityName }</span>
                                        	${incident.abs }
                                        </td>
                                        <td>${incident.applyUserName }</td>
                                        <td>${incident.currentDelegateUser }</td>
                                        <td><c:if test="${not empty incident.processInstanceId }">
												<a class="lnk_trace" href='#' pid="${incident.processInstanceId }" pdid="<%=ProcessDefinitionCache.getProcessDefinitionId(pageContext.getAttribute("pdid").toString()) %>" title="点击查看流程图">
													<%=ProcessDefinitionCache.getActivityName(pageContext.getAttribute("pdid").toString()) %>
												</a>
											</c:if>
										</td>
                                        <td><fmt:formatDate value="${incident.applyTime }" pattern="MM/dd HH:mm:ss" /></td>
                                        <td>${incident.statusName }</td>
                                        <td>
                                        	<c:if test="${empty incident.recoverTime }">
                                        		<a href="${contextPath }/incident/deal/${incident.id}">办理</a>
                                        	</c:if>
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
    	<div class="dialog" id="b_popup_knowledge" style="display: none" title="知识库"></div>
    </div>
</body>

</html>