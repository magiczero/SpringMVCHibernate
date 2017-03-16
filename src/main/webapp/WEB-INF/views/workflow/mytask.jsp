<%@page import="com.cngc.utils.activiti.ProcessDefinitionCache,org.activiti.engine.RepositoryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
        <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
        <meta name="viewport" content="width=device-width, initial-scale=1">


<title>待办任务--运维管理系统</title>
<!-- 


                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  ` - `.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            佛祖保佑       永无BUG  
 -->
<link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/fullcalendar.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/uniform.default.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/mCustomScrollbar.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/cleditor.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/fancybox/jquery.fancybox.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/elfinder.min.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/elfinder.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/multi-select.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/ibutton.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/stepy.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/tagsinput.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/dataTables.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/uploadify.css" rel="stylesheet" type="text/css" />
<link href="${contextPath }/resources/css/stylesheets2.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->
<script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/sparklines/jquery.sparkline.min.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/fullcalendar/fullcalendar.min.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/animatedprogressbar/animated_progressbar.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/cleditor/jquery.cleditor.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
<script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/uploadify/jquery.uploadify.min.js'></script>

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
	$(document).ready(function() {
		$("#eventTable").dataTable({
											"oLanguage" : {
												"sUrl" : "${contextPath}/resources/json/Chinese.json"
											},
											"aaSorting" : [ [ 5, 'desc' ] ]
										});
		$(".header").load("${contextPath}/header?t=" + pm_random());
		$(".menu").load("${contextPath}/menu?t=" + pm_random(),
								function() {
									$(".navigation > li:eq(0)").addClass("active");
								});
		$(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+ pm_random());
		$(".confirm").bind("click", function() {
							if (!confirm("确定要执行该操作?"))
								return false;
						});
						// 流程跟踪
						$("#b_popup_trace").dialog({
							autoOpen : false,
							width : 800,
							height : 400,
							buttons : {
								"关闭" : function() {
									$(this).dialog("close")
								}
							}
						});
						
		$(".lnk_trace").click(function() {
											var src = ctx
													+ '/diagram-viewer/index.html?processDefinitionId='
													+ $(this).attr('pdid')
													+ '&processInstanceId='
													+ $(this).attr('pid');
											$("#trace_content")
													.html(
															"<iframe src='"
																	+ src
																	+ "' width='100%' height='265'/>");
											$("#b_popup_trace").dialog('open');
										});
		
					});
	
	
</script>
<style type="text/css">
    	.uploadify-button-text {color:#fff !important;}
    	
    </style>
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
					<li class="active">待办任务</li>
				</ul>

				<ul class="buttons"></ul>

			</div>
			<!--breadline end-->

			<!--workplace-->
			<div class="workplace">
				<%
					RepositoryService repositoryService = (RepositoryService) request.getAttribute("res");
					ProcessDefinitionCache.setRepositoryService(repositoryService);
				%>
				<div class="alert alert-danger hide">
					<h4>错误!</h4>
					请至少选择一项
				</div>
				<div class="row">
					<%
						int nsigned = 0, nsign = 0, ninspection = 0, nupdate = 0, nleader = 0;
					%>
					<c:forEach items="${tasks }" var="task">
						<c:set var="processName" value="${task.processDefinitionId.substring(0,task.processDefinitionId.indexOf(':')) }" />
						<c:if test="${not empty task.assignee }">
							<% nsigned++; %>
						</c:if>
						<c:if test="${empty task.assignee }">
							<% nsign++;	%>
						</c:if>
						<c:if test="${processName=='INSPECTION' }">
							<% ninspection++;	%>
						</c:if>
						<c:if test="${processName=='UPDATE' }">
							<% nupdate++;	%>
						</c:if>
						<c:if test="${processName=='LEADERTASK' }">
							<% nleader++;	%>
						</c:if>
					</c:forEach>
					<div class="col-md-3">
						<div class="wBlock red clearfix">
							<div class="dSpace">
								<h3>待办任务</h3>
								<span class="mChartBar" sparkType="bar" sparkBarColor="white"> <!--130,190,260,230,290,400,340,360,390-->
								</span> <span class="number">${tasks.size() }</span>
							</div>
							<div class="rSpace">
								<span><%=nsign%> <b>待签收</b></span> <span><%=nsigned%> <b>已签收</b></span>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="wBlock green clearfix">
							<div class="dSpace">
								<h3>今日未完成</h3>
								<span class="mChartBar" sparkType="bar" sparkBarColor="white">
									<!--5,10,15,20,23,21,25,20,15,10,25,20,10-->
								</span> <span class="number"><%=ninspection+nupdate %></span>
							</div>
							<div class="rSpace">
								<span><%=ninspection %> <b>日常巡检</b></span> <span><%=nupdate %> <b>升级管理</b></span>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="wBlock blue clearfix">
							<div class="dSpace">
								<h3>领导交办</h3>
								<span class="mChartBar" sparkType="bar" sparkBarColor="white">
									<!--240,234,150,290,310,240,210,400,320,198,250,222,111,240,221,340,250,190-->
								</span> <span class="number"><%=nleader %></span>
							</div>
							<div class="rSpace">
								<span>${LeaderTask } <b>今日未反馈</b></span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-9">
						<div class="head clearfix">
							<div class="isw-grid"></div>
							<h1>待办任务</h1>
						</div>
						<div class="block-fluid table-sorting clearfix">
							<table class="table" id="eventTable">
								<thead>
									<tr>
										<th width="60px">流水号</th>
										<th width="60px">任务号</th>
										<th>流程名称</th>
										<th width="130px">任务名称</th>
										<th width="80px">发起者</th>
										<th width="100px">创建时间</th>
										<th width="60px">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${tasks }" var="task">
										<c:set var="pdid" value="${task.processDefinitionId }" />
										<c:set var="pid" value="${task.processInstanceId }" />
										<c:set var="processName" value="${task.processDefinitionId.substring(0,task.processDefinitionId.indexOf(':')) }" />
										<tr>
											<td>${task.processInstanceId }</td>
											<td>${task.id }</td>
											<td>
												<c:if test="${processName=='INCIDENT'||processName=='CHANGE'||processName=='LEADERTASK' }">
													<a class="tipr" title="查看详情" href="${contextPath }/${processName.toLowerCase()}/viewbyprocess/${task.processInstanceId}" target="_blank">
												</c:if>
												<c:if test="${processName=='KNOWLEDGE' }">
													<a class="tipr" title="查看详情" href="${contextPath }/${processName.toLowerCase()}/list">
												</c:if>
												<c:if test="${processName=='INSPECTION' || processName=='UPDATE' || processName=='SECJOB' }">
													<a class="tipr" title="查看详情" href="${contextPath }/record/${processName.toLowerCase()}">
												</c:if>
												<%=ProcessDefinitionCache.getProcessName(pageContext.getAttribute("pdid").toString())%></a>
											</td>
											<td><a class="lnk_trace tipb" href='#' pid="${task.processInstanceId }" pdid="${task.processDefinitionId}" title="点击查看流程图">${task.name }</a></td>
											<td>${startusers[task.processInstanceId] }</td>
											<td><fmt:formatDate value="${task.createTime }" pattern="yyyy/MM/dd HH:mm" /></td>
											<td>
												<c:if test="${empty task.assignee }">
													<a class="claim confirm" href="${contextPath }/workflow/task/claim/${task.id}"><span class="glyphicon glyphicon-edit"></span> 签收</a>
												</c:if> 
												<c:if test="${not empty task.assignee }">
													<c:if test="${processName=='UPDATE' || processName=='SECJOB' || processName=='KNOWLEDGE' || processName=='feedback' }">
														<a onclick="act_form_openTaskDialog('${task.name }','${task.id}','/workflow/task/mytask')" href="#"><span class="glyphicon glyphicon-edit"></span> 办理</a>
													</c:if>
													<c:if test="${processName=='INCIDENT'||processName=='CHANGE' || processName=='LEADERTASK' || processName=='OVERTIME' }">
														<a href="${contextPath }/${processName.toLowerCase() }/dealbyprocess/${task.processInstanceId }/${task.id}"><span class="glyphicon glyphicon-edit"></span> 办理</a>
													</c:if>
													<c:if test="${processName=='threeMember' }">
														<a href="${contextPath }/three-member/deal/${task.id}"><span class="glyphicon glyphicon-edit"></span> 办理</a>
													</c:if>
													<c:if test="${processName=='INSPECTION' }">
														<a href="${contextPath }/record/${processName.toLowerCase() }/dealbyprocess/${task.processInstanceId }/${task.id}"><span class="glyphicon glyphicon-edit"></span> 办理</a>
													</c:if>
												</c:if></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="col-md-3">
						<div class="head clearfix">
							<div class="isw-sound"></div>
							<h1>运维公告</h1>
							<ul class="buttons">
								<li><a href="#" class="isw-settings tipl" title="操作"></a>
									<ul class="dd-list">
										<li><a href="${contextPath }/notice/list"><span class="isw-list"></span> 查看全部</a></li>
										<li><a href="${contextPath }/notice/add"><span class="isw-edit"></span> 创建公告</a></li>
									</ul></li>
							</ul>
						</div>
						<div class="block news scrollBox">

							<div class="scroll" style="max-height: 260px;">

								<c:forEach items="${notices }" var="notice">
								<div class="item">
									<span class="glyphicon glyphicon-volume-down"></span> <a href="#" id="lnk_message">${notice.title }</a>
									<p>${notice.content }</p>
									<span class="date"><fmt:formatDate value="${notice.createdTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></span>
								</div>
								</c:forEach>

							</div>

						</div>
					</div>
				</div>
				<div class="dr">
					<span></span>
				</div>
			</div>
			<!--workplace end-->
		</div>
		<!-- 动态表单 -->
		<div class="modal fade" id="dynamicForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4>动态表单</h4>
					</div>
					<div class="dynamic-form-dialog"></div>
				</div>
			</div>
		</div>
		<!-- 动态表单 end -->
		<!-- 流程跟踪 -->
		<div class="dialog" id="b_popup_trace" style="display: none;" title="流程跟踪">
			<div class="block dialog_block  uploads" id="trace_content"></div>
		</div>
	</div>
</body>

</html>
