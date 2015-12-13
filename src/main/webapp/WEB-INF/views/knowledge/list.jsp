<%@page import="com.cngc.utils.activiti.ProcessDefinitionCache,org.activiti.engine.RepositoryService,org.activiti.engine.RuntimeService,org.activiti.engine.TaskService"%>
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
    
    <title>知识管理--运维管理系统</title>

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
    <script type='text/javascript' src='${contextPath }/resources/js/activiti-form.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-workflow.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
            $(document).ready(function () {
                $("#eventTable").dataTable();
                $(".header").load("${contextPath}/header");
                $(".menu").load("${contextPath}/menu", function () { $(".navigation > li:eq(6)").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons");
                $(".confirm").bind("click",function(){
                	if(!confirm("确定要执行该操作?"))
                		return false;
                });
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
                    <li><a href="${contextPath }/Asset/list">知识管理</a> <span class="divider">></span></li>       
                    <li class="active">知识列表</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">             
			<%
				RuntimeService runtimeService  = (RuntimeService)request.getAttribute("runtime");
				RepositoryService repositoryService = (RepositoryService)request.getAttribute("res");
				TaskService taskService = (TaskService)request.getAttribute("task");
				ProcessDefinitionCache.setRuntimeService(runtimeService);
				ProcessDefinitionCache.setRepositoryService(repositoryService);
				ProcessDefinitionCache.setTaskService(taskService);
			%>
				<div class="alert alert-danger hide">                
                    <h4>错误!</h4>请至少选择一项
                </div> 
                <div class="row">
                    <div class="col-md-9">                    
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>
                                <c:if test="${not empty status }">
                            		${status.codeName }知识
                            	</c:if>
                            	<c:if test="${empty status }">
                            	待处理知识
                            	</c:if>
                            </h1>  

                            <ul class="buttons">    
                            	<li>
                                    <a href="${contextPath }/knowledge/add" class="isw-text_document tipb" title="新知识"></a>
                                </li>                       
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                    	<li><a href="${contextPath}/knowledge/add" role="button" data-toggle="modal"><span class="isw-ok"></span> 创建新知识</a></li>
                                        <li><a href="javascript:void(0);" id="delBtn"><span class="isw-list"></span> 删除</a></li>
                                        <li><a href="#"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="eventTable">
                                <thead>
                                	<tr>
										<th>标题</th>
										<th width="100px">关键字</th>
										<th width="100px">提交者</th>
										<th width="110px">提交时间</th>
										<th width="120px">流程步骤</th>
										<th width="70px">状态</th>
										<th width="150px">操作</th>
									</tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list}" var="knowledge">
									<c:set var="pdid" value="${knowledge.processInstanceId }" />
									<tr>
										<td>${knowledge.title }</td>
										<td>${knowledge.keyword }</td>
										<td>${knowledge.applyUserName }</td>
										<td><fmt:formatDate value="${knowledge.applyTime }" pattern="MM/dd HH:mm:ss" /></td>
										<td>
										<c:if test="${not empty knowledge.processInstanceId }">
											<a class="lnk_trace" href='#' pid="${knowledge.processInstanceId }" pdid="<%=ProcessDefinitionCache.getProcessDefinitionId(pageContext.getAttribute("pdid").toString()) %>" title="点击查看流程图">
												<%=ProcessDefinitionCache.getActivityName(pageContext.getAttribute("pdid").toString()) %>
											</a>
										</c:if>
										<c:if test="${knowledge.status }">已发布</c:if>
										</td>
										<td>${knowledge.statusName }</td>
										<td>
											<c:if test="${!knowledge.locked }">
												<a class="confirm" href='${contextPath }/knowledge/manage/${knowledge.id}/publish'>发布申请</a>
											</c:if>
											<c:if test="${knowledge.status=='04' }">
												<a class="confirm" href='${contextPath }/knowledge/manage/${knowledge.id}/modify'>修改申请</a>
												<a class="confirm" href='${contextPath }/knowledge/manage/${knowledge.id}/delete'>删除申请</a>
											</c:if>
											<c:if test="${!knowledge.locked }">
										 		<a href='${contextPath }/knowledge/modify/${knowledge.id}'>修改</a>
										 	</c:if>
										 	<c:if test="${knowledge.status=='02' }">
												<a href="#" onclick="act_form_openTaskDialog('知识管理','<%=ProcessDefinitionCache.getTaskId(pageContext.getAttribute("pdid").toString()) %>','/knowledge/list')">办理</a>
											</c:if>
										</td>
									</tr>
								</c:forEach>   
                                </tbody>
                            </table>
                        </div>
                    </div>  
                  
                
                    <div class="col-md-3">
                        <div class="head clearfix">
                            <div class="isw-list"></div>
                            <h1>操作</h1>
                        </div>
                        <div class="block-fluid accordion">
                            <h3>计数</h3>
                            <div>
                                <ul>
                                	<c:forEach items="${group }" var="code">
                                		<c:set var="id" value="${code.code }" />
                                		<li><a href="${contextPath }/knowledge/list/${code.code}">${code.codeName }</a>
                                		<span style="float:right;margin-right:20px;">
                                			<c:if test="${empty count[id] }">
                                			0
                                			</c:if>
                                			${count[id] }
                                		</span></li>
                                	</c:forEach>
                                </ul>                                              
                            </div>

                            <h3>功能</h3>
                            <div>
                                <ul>
                                    <li><a href="${contextPath }/knowledge/add">创建新知识</a></li>
                                    <li><a href="${contextPath }/knowledge/list">待处理知识</a></li>
                                </ul>                                                
                            </div>                     

                        </div>
                    </div> 
               
                </div>
                <div class="dr"><span></span></div>
            </div>
            <!--workplace end-->
        </div>   
        <!-- 动态表单 -->
        <div class="modal fade" id="dynamicForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    	<!-- 动态表单 end -->
    </div>
</body>

</html>