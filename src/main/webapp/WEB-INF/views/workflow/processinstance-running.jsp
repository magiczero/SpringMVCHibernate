<%@page import="com.cngc.utils.activiti.ProcessDefinitionCache,org.activiti.engine.RepositoryService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="java.util.*,org.apache.commons.lang3.StringUtils,org.apache.commons.lang3.ObjectUtils" %>
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
    
    <title>流程管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    <link rel="icon" type="image/ico" href="favicon.ico"/>
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/uniform.default.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
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
                $("#eventTable").dataTable();
                $(".header").load("${contextPath}/header?t="+pm_random());
                $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $(".navigation > li:eq(10)").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
                $(".confirm").bind("click",function(){
                	if(!confirm("确定要执行该操作?"))
                		return false;
                });
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
        function addReson(id)
        {
        	$("#fm_processInstanceId").attr('value',id);
    		$("#reasonFormDialog").modal('show');
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
		
		<!-- content -->
        <div class="content">
            <!--breadline-->
            <div class="breadLine">

                <ul class="breadcrumb">
                    <li><a href="#">运维管理系统</a> <span class="divider">></span></li>
                    <li><a href="${contextPath }/workflow/process/list">工作流管理</a> <span class="divider">></span></li>       
                    <li class="active">运行中的流程</li>
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
                            <h1>运行中的流程</h1>  

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
										<th width="100px">流程实例ID</th>
										<th>流程名称</th>
										<th width="150px">当前节点</th>
										<th width="80px">状态</th>
										<th width="120px">操作</th>
									</tr>
                                </thead>
                                <tbody>
                                		<c:forEach items="${list}" var="p">
										<c:set var="pdid" value="${p.processDefinitionId }" />
										<c:set var="activityId" value="${p.activityId }" />
										<tr>
											<td>${p.processInstanceId }</td>
											<td><%=ProcessDefinitionCache.getProcessName(pageContext.getAttribute("pdid").toString()) %></td>
											<td><a class="lnk_trace" href='#' pid="${p.processInstanceId }" pdid="${p.processDefinitionId}" title="点击查看流程图">
												<%=ProcessDefinitionCache.getActivityName(pageContext.getAttribute("pdid").toString(), ObjectUtils.toString(pageContext.getAttribute("activityId"))) %></a>
											</td>
											<td>
												<c:if test="${p.suspended=='false' }">
													<span class="label label-success">正常</span>
												</c:if>
												<c:if test="${p.suspended=='true' }">
													<span class="label label-warning">已挂起</span>
												</c:if>
											</td>
											<td>
												<c:if test="${p.suspended }">
													<a class="confirm" href="update/active/${p.processInstanceId}"><span class="glyphicon glyphicon-repeat"></span> 激活</a>
												</c:if>
												<c:if test="${!p.suspended }">
													<a class="confirm" href="update/suspend/${p.processInstanceId}"> <span class="glyphicon glyphicon-ban-circle"></span> 挂起</a>
												</c:if>
													&nbsp;&nbsp;<a href="#" onclick="addReson('${p.processInstanceId}')"><span class="glyphicon glyphicon-off"></span> 结束</a>
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
        <!-- modal form -->
        <div class="modal fade" id="reasonFormDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4 id="dialogTitle">流程结束</h4>
                    </div>
                    <form id="userForm" action="${contextPath}//workflow/processinstance/delete" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">结束原因:</div>
                                    <div class="col-md-9"><textarea id="fm_reason" name="fm_reason" ></textarea></div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" aria-hidden="true">提交</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    <input type="hidden" id="fm_processInstanceId"  name="fm_processInstanceId" value="0" /> 
                    </form>
                </div>
            </div>
        </div>
    	<!-- content end -->
    	<!-- 流程跟踪 -->
    	<div class="dialog" id="b_popup_trace" style="display: none;" title="流程跟踪">
	    	<div class="block dialog_block  uploads" id="trace_content">
			                                
			</div>
    	</div>
    </div>
</body>

</html>