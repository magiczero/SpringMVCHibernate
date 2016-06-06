<%@page import="com.cngc.utils.activiti.ProcessDefinitionCache,org.activiti.engine.RepositoryService"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<!--[if gt IE 8]>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<![endif]-->

<title>工程师工作态势--运维管理系统</title>

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

<script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/excanvas.min.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.stack.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.pie.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.resize.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/chart.min.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/modernizr.min.js'></script>

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

	$(document)
			.ready(
					function() {
						$("#eventTable")
						.dataTable(
								{
									"oLanguage" : {
										"sUrl" : "${contextPath}/resources/json/Chinese.json"
									},
									"aaSorting" : [ [ 5, 'desc' ] ]
								});
						$(".header").load(
								"${contextPath}/header?t=" + pm_random());
						$(".menu").load(
								"${contextPath}/menu?t=" + pm_random(),
								function() {
									$(".navigation > li:eq(0)").addClass(
											"active");
								});

						$(".breadLine .buttons").load(
								"${contextPath}/contentbuttons?t="
										+ pm_random());

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
						$(".lnk_trace")
								.click(
										function() {
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
					<li class="active">工程师工作态势</li>
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
				<div class="row">
					<div class="col-md-6">
						<div class="col-md-3">
							<div class="ucard clearfix">
								<div class="right">
									<h4>${username }</h4>
									<div class="image">
										<a href="#"><img src="${contextPath }/resources/img/users/user_profile.jpg" class="img-thumbnail"></a>
									</div>
									<ul class="control">
										<li><span class="glyphicon glyphicon-user"></span> <a href="ui.html">在岗</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div class="col-md-9">
							<div class="block-fluid ucard">

								<div class="info">
									<ul class="rows">
										<li class="heading">参与运维工作统计</li>
										<li>
											<div class="title">事件:</div>
											<div class="text">1</div>
										</li>
										<li>
											<div class="title">变更:</div>
											<div class="text">2</div>
										</li>
										<li>
											<div class="title">领导交办:</div>
											<div class="text">3</div>
										</li>
										<li>
											<div class="title">日常巡检:</div>
											<div class="text">4</div>
										</li>
										<li>
											<div class="title">三员工作:</div>
											<div class="text">5</div>
										</li>
										<li>
											<div class="title">下载升级:</div>
											<div class="text">6</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="head clearfix">
							<div class="isw-favorite"></div>
							<h1>当前待办任务 (共${tasks.size() }项)</h1>
						</div>
						<div class="block-fluid scrollBox withList">
							<div class="scroll" style="height: 220px;">
								<ul class="list">
									<c:forEach items="${tasks }" var="task">
										<c:set var="pdid" value="${task.processDefinitionId }" />
										<c:set var="pid" value="${task.processInstanceId }" />
										<c:set var="processName" value="${task.processDefinitionId.substring(0,task.processDefinitionId.indexOf(':')) }" />
										<li><span class="date"><b><fmt:formatDate value="${task.createTime }" pattern="MM-dd" /></b> <fmt:formatDate value="${task.createTime }" pattern="HH:mm" /></span> <a href=#>${startusers[task.processInstanceId]==null?"系统":startusers[task.processInstanceId] }</a>发起的 <a href="#"><%=ProcessDefinitionCache.getProcessName(pageContext.getAttribute("pdid").toString())%></a> [流水号:22424]
											，当前步骤为：<a href="#">${task.name }</a></li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
					</div>
					<div class="row">
						<div class="col-md-9">
						<div class="head clearfix">
							<div class="isw-grid"></div>
							<h1>本月参与办结的任务</h1>
						</div>
						<div class="block-fluid table-sorting clearfix">
							<table class="table" id="eventTable">
								<thead>
									<tr>
										<th width="60px">流水号</th>
										<th>流程名称</th>
										<th width="80px">发起者</th>
										<th width="100px">创建时间</th>
										<th width="100px">结束时间</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${processes }" var="process">
										<c:set var="pdid" value="${process.processDefinitionId }" />
										<c:set var="processName" value="${process.processDefinitionId.substring(0,process.processDefinitionId.indexOf(':')) }" />
										<tr>
											<td>${process.processInstanceId }</td>
											<td><c:if test="${processName=='INCIDENT'||processName=='CHANGE'||processName=='LEADERTASK' }">
													<a class="tipr" title="查看详情" href="${contextPath }/${processName.toLowerCase()}/viewbyprocess/${process.processInstanceId}" target="_blank">
												</c:if>
												<c:if test="${processName=='KNOWLEDGE' }">
													<a class="tipr" title="查看详情" href="${contextPath }/${processName.toLowerCase()}/list">
												</c:if>
												<c:if test="${processName=='INSPECTION' || processName=='UPDATE' || processName=='SECJOB' }">
													<a class="tipr" title="查看详情" href="${contextPath }/record/${processName.toLowerCase()}">
												</c:if>
												<%=ProcessDefinitionCache.getProcessName(pageContext.getAttribute("pdid").toString())%></a>
											</td>
											<td>${startusers[process.processInstanceId]==null?"系统":startusers[process.processInstanceId] }</td>
											<td><fmt:formatDate value="${process.startTime }" pattern="yyyy/MM/dd HH:mm" /></td>
											<td><fmt:formatDate value="${process.endTime }" pattern="yyyy/MM/dd HH:mm" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
						<div class="col-md-3">
						<div class="head clearfix">
							<div class="isw-picture"></div>
							<h1>上传文档</h1>
						</div>
						<div class="block uploads">
							<div class="item">
								<p>
									<span class="glyphicon glyphicon-picture"></span> SP-20031.jpg
								</p>
							</div>
							<div class="item">
								<p>
									<span class="glyphicon glyphicon-film"></span> MOV-80131.mov
								</p>

							</div>
						</div>
						</div>
					</div>
					
				<div class="dr">
					<span></span>
				</div>
				<div class="row"></div>
			</div>
			<!--workplace end-->
		</div>
	</div>
</body>

</html>
