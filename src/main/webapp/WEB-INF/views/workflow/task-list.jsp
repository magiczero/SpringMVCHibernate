<%@page import="com.cngc.utils.activiti.ProcessDefinitionCache,org.activiti.engine.RepositoryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    
    <title>任务管理--运维管理系统</title>

    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/uniform.default.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/js/plugins/select2/select2.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>   
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script> 
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
            $(document).ready(function () {
                $("#eventTable").dataTable({"oLanguage": {
         			"sUrl": "${contextPath}/resources/json/Chinese.json"
     			},"aaSorting":[[6,'desc']]});
                $(".header").load("${contextPath}/header?t="+pm_random());
                $(".menu").load("${contextPath}/menu?t="+pm_random(), function () {$("#node_${moduleId}").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
                $(".confirm").bind("click",function(){
                	if(!confirm("确定要执行该操作?"))
                		return false;
                });
                $("#assignee").select2();
                $("#candidateUser").select2();
                $(".handle").bind("click",task_setting);
                // 流程跟踪
                $("#b_popup_trace").dialog({
                    autoOpen: false,
                    width: 800,
                    height: 400,
                    buttons: { "关闭": function () { $(this).dialog("close") } }
                });
                $(".lnk_trace").click(function () {
                	var src= ctx+ '/diagram-viewer/index.html?processDefinitionId=' 
        			+ $(this).attr('pdid') + '&processInstanceId=' + $(this).attr('pid');
                	$("#trace_content").html("<iframe src='"+ src +"' width='100%' height='265'/>");
                	$("#b_popup_trace").dialog('open');
                });
            });
            function task_setting(){
            	var taskId = $(this).parents('tr').find('.taskid').text();
            	$("#taskId").attr('value',taskId);
                $("#newFormDialog").modal('show');
                return false;
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
                    <li><a href="${contextPath }/workflow/process/list">工作流管理</a> <span class="divider">></span></li>       
                    <li class="active">运行中的任务</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">             
			<%
				RepositoryService repositoryService  = (RepositoryService)request.getAttribute("res");
				ProcessDefinitionCache.setRepositoryService(repositoryService);
			%>
				<div class="alert alert-danger hide">                
                    <h4>错误!</h4>请至少选择一项
                </div> 
                <div class="row">
                    <div class="col-md-12">                    
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>运行中的任务</h1>  

                            <ul class="buttons">                          
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                        <li><a href="#" onclick="pm_refresh()"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="eventTable">
                                <thead>
									<tr>
										<th width="100px" >任务ID</th>
										<th>流程名称</th>
										<th width="150px">任务名称</th>
										<th width="100px">受理人</th>
										<th width="100px">候选人</th>
										<th width="100px">候选组</th>
										<th width="140px">任务创建日期</th>
										<th width="100px">操作</th>
									</tr>
                                </thead>
                                <tbody>
								<c:forEach items="${tasks }" var="task">
								<c:set var="pdid" value="${task.processDefinitionId }" />
								<c:set var="taskid" value="${task.id }" />
								<tr>
									<td class="taskid">${task.id }</td>
									<td><%=ProcessDefinitionCache.getProcessName(pageContext.getAttribute("pdid").toString()) %></td>
									<td><a class="lnk_trace" href='#' pid="${task.processInstanceId }" pdid="${task.processDefinitionId}" title="点击查看流程图">${task.name }</a></td>
									<td>${task.assignee }</td>
									<td>
										<c:forEach items="${candidate[taskid]}" var="i">
										${i.userId }
										</c:forEach>
									</td>
									<td>
									<c:forEach items="${candidate[taskid]}" var="i">
										${i.groupId }
										</c:forEach>
									</td>
									<td><fmt:formatDate value="${task.createTime }" pattern="yyyy/MM/dd HH:mm:ss" /></td>
									<td>
										<a class="handle" tkey='${task.taskDefinitionKey }' tname='${task.name }' tid='${task.id }' href="#"><span class="glyphicon glyphicon-share"></span> 设置转办</a>
									</td>
								</tr>
								</c:forEach> 
                                </tbody>
                            </table>
                        </div>
                    </div>  
                  
                </div>
               <div class="dr"><span></span></div>
            </div>
            <!--workplace end-->
        </div>   
		<!-- 新建用户 modal form -->
        <div class="modal fade" id="newFormDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4 id="dialogTitle">任务转办</h4>
                    </div>
                    <form id="userForm" action="${contextPath}/workflow/task/setuser" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">任务办理人:</div>
                                    <div class="col-md-9">
                                    	<select id="assignee" name="assignee" style="width:100%">
                                    		<option value='00'>请选择</option>
                                    	<c:forEach items="${users }" var="user">
                                    		<option value='${user.username }'>${user.name }</option>
                                    	</c:forEach>
                                    	</select>
                                    </div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">候选人:</div>
                                    <div class="col-md-9">
                                    	<select id="candidateUser" name="candidateUser" style="width:100%" multiple="multiple">
                                    	<c:forEach items="${users }" var="user">
                                    		<option value='${user.username }'>${user.name }</option>
                                    	</c:forEach>
                                    	</select>
                                    </div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">候选组:</div>
                                    <div class="col-md-9"><input id="candidateGroup" name="candidateGroup" type="text"></input></div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" aria-hidden="true">保存</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    <input type="hidden" id="taskId"  name="taskId" value="0" /> 
                    </form>
                </div>
            </div>
        </div>
    	<!-- 新建用户 end from -->
    	<!-- 流程跟踪 -->
    	<div class="dialog" id="b_popup_trace" style="display: none;" title="流程跟踪">
	    	<div class="block dialog_block  uploads" id="trace_content">
			</div>
    	</div>
    </div>
</body>

</html>