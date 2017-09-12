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

<title>运维控制台--运维管理系统</title>

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
	var mylabels = [<c:forEach items="${incidentStat.column }" var="code" varStatus="status"><c:if test="${status.index>0}">,</c:if>'${code.value}'</c:forEach>];
	var mydata=[<c:forEach items="${incidentStat.column }" var="code" varStatus="status"><c:if test="${status.index>0 }">,</c:if>${incidentStat.counts[code.key]==null?0:incidentStat.counts[code.key]}</c:forEach>];
   
	$(document).ready(function() {

						$("#eventTable").dataTable(
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
									$("#node_${moduleId}").addClass(
											"active");
								});
						<%--$(".breadLine .buttons").load(
								"${contextPath}/contentbuttons?t="
										+ pm_random());--%>
						if( ($.browser.msie&&$.browser.version=="8.0") )
			            {
			            	if($("#barChart").length > 0){       
			                	$("#barChart").parent('div').resize(function(){
			    		            var bctx = $("#barChart").get(0).getContext("2d");
			    		            $("#barChart").attr('width',$("#barChart").parent('div').width()).attr('height',300);
			    		            var barChart = new Chart(bctx).Bar({
			    		            	labels : mylabels,
			    		                datasets : [{fillColor : "rgba(151,187,205,0.5)",strokeColor : "rgba(151,187,205,1)",data : mydata}]
			    		            }, {animation : Modernizr.canvas, 
			    		            	scaleShowGridLines : false, 
			    		            	showLabelsOnBars:true
			    		            	});
			                	});
			                }
			            }else{
			            	if($("#barChart").length > 0){       
			    		    	var bctx = $("#barChart").get(0).getContext("2d");
			    		        $("#barChart").attr('width',$("#barChart").parent('div').width()).attr('height',300);
			    		        var barChart = new Chart(bctx).Bar({
			    		          	labels : mylabels,
			    		            datasets : [{fillColor : "rgba(151,187,205,0.5)",strokeColor : "rgba(151,187,205,1)",data : mydata}]
			    		        }, {animation : Modernizr.canvas, 
			    		        	scaleShowGridLines : false, 
			    		        	showLabelsOnBars:true
			    		        	//
								});
			    		        console.log("adg");
			                }
			            }
					    if ($("#chart-3").length > 0) {

					        var data=[<c:forEach items="${incidentSatisfaction.column }" var="code" varStatus="status">
					        	<c:if test="${status.index>0 }">,</c:if>
					        	{label:"${code.value}",data:${incidentSatisfaction.counts[code.key]==null?0:incidentSatisfaction.counts[code.key]} }
					        	</c:forEach>];
					        if( ($.browser.msie&&$.browser.version=="8.0") )
				            {
					        	$("#chart-3").parent('div').resize(function(){
							        $.plot($("#chart-3"), data,
								    {
								        series: {
								            pie: { show: true }
								        },
								        legend: { show: false }
								    });
					        	});
				            } else {
				            	 $.plot($("#chart-3"), data,
										    {
										        series: {
										            pie: { show: true }
										        },
										        legend: { show: false }
										    });
				            }
						}
						

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
					<li class="active">运维控制台</li>
				</ul>

				<ul class="buttons"></ul>

			</div>
			<!--breadline end-->

			<!--workplace-->
			<div class="workplace">
				<div class="row">
					<div class="col-md-4">
						<div class="head clearfix">
							<div class="isw-graph"></div>
							<h1>本年度事件满意度统计</h1>
						</div>
						<div class="block">
							<div id="chart-3" style="height: 310px;"></div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="head clearfix">
						<div class="isw-graph"></div>
							<h1>本年度事件统计</h1>
						</div>
						<div class="block" >
							<canvas id="barChart"></canvas>
						</div>
					</div>
					<div class="col-md-3">
						<div class="wBlock red clearfix">
							<div class="dSpace">
								<h3>待处理事件</h3>
								<span class="mChartBar" sparkType="bar" sparkBarColor="white">
									<!--130,190,260,230,290,400,340,360,390-->
								</span> 
								<span class="number">
									<%int incident1 = 0,incident2 = 0; 
									for(int i=1;i<=5;i++)
									{
										if(((Map)pageContext.findAttribute("incidentCount")).get("0"+i)!=null)
											incident1 = incident1 + Integer.parseInt(((Map)pageContext.findAttribute("incidentCount")).get("0"+i).toString());
									}
									for(int i=3;i<=5;i++)
									{
										if(((Map)pageContext.findAttribute("incidentCount")).get("0"+i)!=null)
											incident2 = incident2 + Integer.parseInt(((Map)pageContext.findAttribute("incidentCount")).get("0"+i).toString());
									}
                                	%>
                                	<%=incident1 %>
								</span>
							</div>
							<div class="rSpace">
								<span><a title="点击查看" style="color:white" href="${contextPath }/incident/list/01">${incidentCount['01']==null?"0":incidentCount['01'] } <b>未指派</b></a></span> 
								<span><a title="点击查看" style="color:white" href="${contextPath }/incident/list/02">${incidentCount['02']==null?"0":incidentCount['02'] } <b>已指派</b></a></span> 
								<span><a title="点击查看" style="color:white" href="${contextPath }/incident/list/03"><%=incident2%> <b>进行中</b></a></span>
							</div>
						</div>
						<div class="wBlock yellow clearfix">
							<div class="dSpace">
								<h3>待处理变更</h3>
								<span class="mChartBar" sparkType="bar" sparkBarColor="white">
									<!--5,10,15,20,23,21,25,20,15,10,25,20,10-->
								</span> <span class="number">
								<%int change1 = 0,change2 = 0; 
									for(int i=1;i<=7;i++)
									{
										if(((Map)pageContext.findAttribute("changeCount")).get("0"+i)!=null)
											change1 = change1 + Integer.parseInt(((Map)pageContext.findAttribute("changeCount")).get("0"+i).toString());
									}
									for(int i=4;i<=7;i++)
									{
										if(((Map)pageContext.findAttribute("changeCount")).get("0"+i)!=null)
											change2 = change2 + Integer.parseInt(((Map)pageContext.findAttribute("changeCount")).get("0"+i).toString());
									}
                                	%>
                                	<%=change1 %>
								</span>
							</div>
							<div class="rSpace">
								<span><a title="点击查看" style="color:white" href="${contextPath }/change/list/01">${changeCount['01']==null?"0":changeCount['01'] } <b>未指派</b></a></span> 
								<span><a title="点击查看" style="color:white" href="${contextPath }/change/list/03">${changeCount['03']==null?"0":changeCount['03'] } <b>方案构建中</b></a></span> 
								<span><a title="点击查看" style="color:white" href="${contextPath }/change/list/06"><%=change2 %> <b>实施中</b></a></span>
							</div>
						</div>
						<div class="wBlock blue clearfix">
							<div class="dSpace">
								<h3>领导交办</h3>
								<span class="mChartBar" sparkType="bar" sparkBarColor="white">
									<!--5,10,15,20,23,21,25,20,15,10,25,20,10-->
								</span> 
								<span class="number">
								${leadertaskCount==null?"0":leadertaskCount }
								</span>
							</div>
							<div class="rSpace">
								<span><a title="点击查看" style="color:white" href="${contextPath }/leadertask/list">${leadertaskOut } <b>已超时</b></a></span>
								<span><a title="点击查看" style="color:white" href="${contextPath }/leadertask/list">${leadertaskFeed } <b>今日未反馈</b></a></span>
							</div>
						</div>
						<div class="wBlock green clearfix">
							<div class="dSpace">
								<h3>运维工作</h3>
								<span class="mChartBar" sparkType="bar" sparkBarColor="white">
									<!--240,234,150,290,310,240,210,400,320,198,250,222,111,240,221,340,250,190-->
								</span> <span class="number">
								<% 
									int op = 0;
									if(pageContext.findAttribute("inspectionCount")!=null)
										op += Integer.parseInt(pageContext.findAttribute("inspectionCount").toString());
									if(pageContext.findAttribute("secjobCount")!=null)
										op += Integer.parseInt(pageContext.findAttribute("secjobCount").toString());
									if(pageContext.findAttribute("updateCount")!=null)
										op += Integer.parseInt(pageContext.findAttribute("updateCount").toString());
								%>
								<%=op %>
								</span>
							</div>
							<div class="rSpace">
								<span><a title="点击查看" style="color:white" href="${contextPath }/record/inspection">${inspectionCount==null?"0":inspectionCount } <b>日常巡检</b></a></span> 
								<span><a title="点击查看" style="color:white" href="${contextPath }/record/secjob">${secjobCount==null?"0":secjobCount } <b>三员工作</b></a></span>
								<span><a title="点击查看" style="color:white" href="${contextPath }/record/update">${updateCount==null?"0":updateCount } <b>下载升级</b></a></span> 
							</div>
						</div>
					</div>
				</div>
				<div class="dr">
					<span></span>
				</div>
				<div class="row">

					<div class="col-md-4">
						<div class="head clearfix">
							<div class="isw-users"></div>
							<h1>未完成的运维工作</h1>
							<ul class="buttons">
								<li><a href="#" class="isw-settings"></a>
									<ul class="dd-list">
										<li><a href="#"><span class="isw-list"></span> Show all</a></li>
										<li><a href="#"><span class="isw-ok"></span> Approved</a></li>
										<li><a href="#"><span class="isw-minus"></span> Unapproved</a></li>
										<li><a href="#"><span class="isw-refresh"></span> Refresh</a></li>
									</ul></li>
							</ul>
						</div>
						<div class="block-fluid accordion">
							<h3>领导交办 ${leadertasks.size() }项</h3>
							<div class="block-withoutborder news scrollBox">
								<div class="scroll" style="height: 160px;">
								<table style="cellpadding:0;cellspacing:0;width:100%;" class="sOrders">
									<thead>
										<tr>
											<th>任务</th>
											<th width="80">交办时间</th>
											<th width="60">受派者</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${leadertasks }" var="leadertask">
										<tr>
											<td><a target="_blank" href="${contextPath }/leadertask/view/${leadertask.id }">${leadertask.taskTitle }</a></td>
											<td><fmt:formatDate value="${leadertask.applyTime }" pattern="yyyy-MM-dd" /></td>
											<td>${leadertask.toUserName }</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
								</div>
							</div>
							<h3>三员工作 ${secjobs.size()}项</h3>
							<div class="block-withoutborder news scrollBox">
								<div class="scroll" style="height: 160px;">
								<table style="width:100%;" class="sOrders">
									<thead>
										<tr>
											<th>工作名称</th>
											<th width="80">发起时间</th>
											<th width="80">执行人</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${secjobs }" var="secjob">
										<tr>
											<td><a href="${contextPath }/record/secjob">${secjob.typeName }</a></td>
											<td><fmt:formatDate value="${secjob.applyTime }" pattern="yyyy-MM-dd" /></td>
											<td>${secjob.userName }</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
								</div>
							</div>

							<h3>日常巡检 ${inspections.size()}项</h3>
							<div class="block-withoutborder news scrollBox">
								<div class="scroll" style="height: 160px;">
								<table style="width:100%" class="sOrders">
									<thead>
										<tr>
											<th>巡检内容</th>
											<th  width="80">发起时间</th>
											<th  width="60">执行人</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${inspections }" var="inspection">
										<tr>
											<td><a href="${contextPath }/record/inspection">${inspection.templateName }</a></td>
											<td><fmt:formatDate value="${inspection.createdTime }" pattern="yyyy-MM-dd" /></td>
											<td>${inspection.executionUserName }</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
								</div>
							</div>
							<h3>下载升级 ${updates.size()}项</h3>
							<div class="block-withoutborder news scrollBox">
								<div class="scroll" style="height: 160px;">
								<table style="width:100%" class="sOrders">
									<thead>
										<tr>
											<th>升级名称</th>
											<th  width="80">发起时间</th>
											<th  width="60">执行人</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${updates }" var="update">
										<tr>
											<td><a href="${contextPath }/record/update">${update.typeName }</a></td>
											<td><fmt:formatDate value="${update.createdTime }" pattern="yyyy-MM-dd" /></td>
											<td>${update.userName }</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
								</div>
							</div>

						</div>
					</div>

					<div class="col-md-4">
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
							<div class="scroll" style="height: 270px;">
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

					<div class="col-md-4">
						<div class="head clearfix">
							<div class="isw-cloud"></div>
							<h1>工程师工作态势</h1>
							<ul class="buttons">
								<li><a href="#" class="isw-settings"></a>
									<ul class="dd-list">
										<li><a href="#"><span class="isw-list"></span> Show all</a></li>
										<li><a href="#"><span class="isw-mail"></span> Send mail</a></li>
										<li><a href="#"><span class="isw-refresh"></span> Refresh</a></li>
									</ul></li>
							</ul>
						</div>
						<div class="block users scrollBox">

							<div class="scroll" style="height: 270px;">

								<c:forEach items="${engineers}" var="user">
								<div class="item clearfix">
									<div class="image">
										<a href="${contextPath }/performance/user/${user.username}"><img src="${contextPath}/resources/img/users/aqvatarius_s.jpg" width="32" /></a>
									</div>
									<div class="info">
										<a href="${contextPath }/performance/user/${user.username}" class="name">${user.name}</a><span><font class="text-danger">${usertask[user.username]==null?"0":usertask[user.username] }</font>项 待办工作</span>
									</div>
								</div>
								</c:forEach>
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
