<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>变更管理--运维管理系统</title>

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
    <link rel='stylesheet' type='text/css' href='${contextPath }/resources/css/fullcalendar.print.css' media='print' />
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/submenu/bootstrap-submenu.min.js'></script>
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- <script type='text/javascript' src='${contextPath }/resources/js/pm-workflow.js'></script>-->
    <!-- <script type='text/javascript' src='${contextPath }/resources/js/pm-knowledge.js'></script>-->
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
 			},
 			"aaSorting":[[4,'desc']]});
            $(".header").load("${contextPath}/header?t="+pm_random());
            $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
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
                    <li class="active">变更控制台</li>
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
                    <div class="col-md-10">                    
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>
                            	<c:if test="${not empty status }">
                            		[ ${status.codeName } ] 变更
                            	</c:if>
                            	<c:if test="${empty status }">
                            	待处理变更
                            	</c:if>
                            </h1>  

                            <ul class="buttons">
                                <li>
                                    <a href="${contextPath }/change/add" class="isw-plus tipl" title="创建新变更"></a>
                                </li>                            
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                    	<li><a href="${contextPath }/change/list"><span class="isw-list"></span> 查看待处理变更</a></li>
                                        <li><a href="${contextPath }/change/mydealedlist"><span class="isw-pin"></span> 跟踪处理过的变更</a></li>
                                        <li><a href="${contextPath }/change/search"><span class="isw-zoom"></span> 历史变更查询</a></li>
                                        <li><a href="#" onclick="pm_refresh()"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="myTable">
                                <thead>
                                    <tr>
                                    	<th width="60px">流水号</th>
                                        <th>摘要</th>
                                        <th width="90px">变更申请人</th>
                                        <th width="120px">流程步骤</th>
                                        <th width="120px">申请日期</th>
                                        <th width="120px">状态</th>  
                                        <th width="90px">操作</th>                                  
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list }" var="change">
                                	<c:if test="${not empty mytasks[change.processInstanceId] }">
                                		<c:set var="task" value="${mytasks[change.processInstanceId]}" />
	                                	 <tr>
	                                	 	<td>${change.processInstanceId }</td>
	                                        <td>
		                                        <span class="label label-warning tipb" title="优先级">${change.priorityName }</span>
		                                        <a href="${contextPath }/change/view/${change.id}" title="查看详情" class="tipr" target="_blank">${change.description}</a>
	                                        </td>
	                                         <td>${change.applyUserName }</td>
	                                        <td>
	                                        	<a class="lnk_trace" href='#' pid="${change.processInstanceId }" pdid="${task.processDefinitionId }" title="点击查看流程图">
														${task.name }
												</a>
	                                        </td>
	                                        <td><fmt:formatDate value="${change.applyTime }" pattern="yyyy-MM-dd HH:mm" /></td>
	                                        <td>${change.statusName }</td>
	                                        <td>
	                                        	<c:if test="${empty task.assignee }">
													<a class="claim confirm" href="${contextPath }/workflow/task/claim/${task.id}"><span class="glyphicon glyphicon-edit"></span> 签收</a>
												</c:if>
												<c:if test="${not empty task.assignee }">
	                                        		<a href="${contextPath }/change/deal/${change.id}/${task.id}"><span class="glyphicon glyphicon-edit"></span> 办理</a>
	                                        	</c:if>
	                                        	<c:if test="${not empty ROLE_MODIFY }">
		                                        	<a href="${contextPath }/change/update/${change.id}"><span class="glyphicon glyphicon-pencil"></span> 修改</a>
		                                        </c:if>
	                                        </td>
	                                    </tr>
                                	</c:if>
                                	<c:if test="${empty mytasks[change.processInstanceId] }">
                                		<c:if test="${not empty tasks[change.processInstanceId] }">
                                		<c:set var="task" value="${tasks[change.processInstanceId]}" />
	                                	 <tr>
	                                	 	<td>${change.processInstanceId }</td>
	                                        <td>
		                                        <span class="label label-warning tipb" title="优先级">${change.priorityName }</span>
		                                        <a href="${contextPath }/change/view/${change.id}" title="查看详情" class="tipr" target="_blank">${change.description}</a>
	                                        </td>
	                                         <td>${change.applyUserName }</td>
	                                        <td>
	                                        	<a class="lnk_trace" href='#' pid="${change.processInstanceId }" pdid="${task.processDefinitionId }" title="点击查看流程图">
														${task.name }
												</a>
	                                        </td>
	                                        <td><fmt:formatDate value="${change.applyTime }" pattern="MM/dd HH:mm:ss" /></td>
	                                        <td>${change.statusName }</td>
	                                        <td>
	                                        	<c:if test="${not empty ROLE_MODIFY }">
		                                        	<a href="${contextPath }/change/update/${change.id}"><span class="glyphicon glyphicon-pencil"></span> 修改</a>
		                                        </c:if>
	                                        </td>
	                                    </tr>
	                                    </c:if>
                                	</c:if>
                                   
                                   </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>  

                    <div class="col-md-2">
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
                                		<li><a href="${contextPath }/change/list/${code.code}">${code.codeName }</a>
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
                                    <li><a href="${contextPath }/change/add">创建新变更</a></li>
                                    <li><a href="${contextPath }/change/list">查看待处理变更</a></li>
                                    <li><a href="${contextPath }/change/mydealedlist">跟踪处理过的变更</a></li>
                                    <li><a href="${contextPath }/change/search">历史变更查询</a></li>
                                    <!-- <li><a href="#" id="lnk_knowledge">知识库</a></li> -->
                                </ul>                                                
                            </div>                     

                        </div>

                    </div> 
                </div>
                
            </div>
            <!--workplace end-->
        </div>   
    </div>
   	<!-- 流程跟踪 -->
   	<div class="dialog" id="b_popup_trace" style="display: none;" title="流程跟踪">
    	<div class="block dialog_block  uploads" id="trace_content">
		</div>
   	</div>
</body>

</html>
