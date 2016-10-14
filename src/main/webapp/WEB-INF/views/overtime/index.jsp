<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>领导交办管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/js/plugins/datetimepicker/datetimepicker.min.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/js/plugins/select2/select2.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/datetimepicker/datetimepicker.min.js' charset="UTF-8"></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/datetimepicker/datetimepicker.zh-CN.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/activiti-form.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/activiti-history.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/activiti-comment.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-workflow.js'></script>
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
 			},"aaSorting":[[6,'asc']]});
            $(".header").load("${contextPath}/header?t="+pm_random());
            $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active");  });
            $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
            $(".confirm").bind("click",function(){
               	if(!confirm("确定要执行该操作?"))
               		return false;
            });
            
            $("#lnk_start_overtime").bind("click",function(){
               	act_form_openStartDialog("发起领导交办任务","OVERTIME","/overtime");
            });
            <%--
            function act_form_openStartDialog(processName,processDefinitionKey,redirectAddress) {
            	// 对话框打开时执行
            	$('#dynamicForm').html(
            			"<div class='modal-dialog'>"
            			+"<div class='modal-content'>"
            			+"<div class='modal-header'>"
            			+"<button type='button' class='close' data-dismiss='modal'>"
            			+"<span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span></button>"                        
                        +"<h4>"+processName+"</h4>"
                        +"</div>"
                        +"<div class='dynamic-form-dialog'></div>"
            			+"</div>"
            			+"</div>"
            	);
            	$('#dynamicForm').unbind('shown.bs.modal');
            	$('#dynamicForm').on('shown.bs.modal', function() {
            		// 获取json格式的表单数据，就是流程定义中的所有field
            		act_form_getStartDialog(processDefinitionKey,redirectAddress);
            	});
            	
            	// 打开动态表单对话框
            	$("#dynamicForm").modal();
            }
            
            function act_form_getStartDialog(processDefinitionKey,redirectAddress) {
            	// 清空对话框内容
            	$('.dynamic-form-dialog').html("<form class='dynamic-form' method='post'><div id='dynamic-form-table' class='modal-body modal-body-np'></div></form>");
            	var $form = $('.dynamic-form');
            	// 设置表单提交id
            	$form.attr('action', ctx + '/workflow/dynamicform/startprocessbykey/' + processDefinitionKey);

            	// 读取启动时的表单
            	$.getJSON(ctx + '/workflow/dynamicform/getformbykey/start/' + processDefinitionKey + '?t=' + pm_random(),function(data) {
            		act_form_getDialogFields(data,redirectAddress);
            	});
            }
            --%>
            $(".dateISO").datepicker(); 
            
            var starttime,endtime;
            starttime = pm_getQueryString("startTime");
            endtime = pm_getQueryString("endTime");
            
            if(starttime!=null)
            	$("input[name='startTime']").attr("value",starttime);
            else
            	$("input[name='startTime']").attr("value",new Date().format("yyyy") +"-01-01");
            
            if(endtime!=null)
            	$("input[name='endTime']").attr("value",endtime);
            else
            	$("input[name='endTime']").attr("value",new Date().format("yyyy-MM-dd"));
            
            $("#lnk_start_leadertask").bind("click",function(){
               	act_form_openStartDialog("发起领导交办任务","LEADERTASK","/leadertask/list");
            });
            
            pm_workflow_inittracedialog();
            act_commont_initdialog();
            act_history_initdialog();
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
                    <li><a href="#">运维工作管理</a> <span class="divider">></span></li>       
                    <li class="active">加班</li>
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
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>加班列表</h1>  
							<ul class="buttons">
                                <li>
                                    <a href="#" class="isw-zoom tipb" data-toggle="modal" data-target="#myModal" title="查询"></a>
                                </li>                            
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                    	<li><a href="#" id="lnk_start_overtime"><span class="isw-plus"></span> 发起加班申请</a></li>
                                        <li><a href="#" data-toggle="modal" data-target="#myModal"><span class="isw-zoom"></span> 任务查询</a></li>
                                        <li><a href="#" onclick="pm_refresh()"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="eventTable">
                                <thead>
                                	<tr>
                                		<th width="90px">流程号</th>
                                		<th width="105px">交办领导</th>
										<th >任务标题</th>
										<th width="100px">受派人</th>
										<th width="110px">提交时间</th>
										<th width="110px">到期时间</th>
										<th width="130px">状态</th>
										<th width="150px">操作</th>
									</tr>
                                </thead>
                                <tbody>
                                	<c:set var="currentTime">
                                		<%=new java.text.SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())%>
                                	</c:set>
                                	<c:forEach items="${list}" var="leaderTask">
									<c:set var="task" value="${tasks[leaderTask.processInstanceId]}" />
									<c:set var="mytask" value="${mytasks[leaderTask.processInstanceId]}" />
									<tr>
										<td>${leaderTask.processInstanceId }</td>
										<td>${leaderTask.fromUserName }</td>
										<td>${leaderTask.taskTitle }</td>
										<td>${leaderTask.toUserName }</td>
										<td><fmt:formatDate value="${leaderTask.applyTime }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
										<td><fmt:formatDate value="${leaderTask.dueTime }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
										<td>
											<c:if test="${not empty mytask }">
												<a class="lnk_trace" href='#' pid="${leaderTask.processInstanceId }" pdid="${mytask.processDefinitionId }" title="点击查看流程图">
													${mytask.name }
												</a>
											</c:if>
											<c:if test="${empty mytask && not empty task }">
												<a class="lnk_trace" href='#' pid="${leaderTask.processInstanceId }" pdid="${task.processDefinitionId }" title="点击查看流程图">
													${task.name }
												</a>
											</c:if>
											<c:if test="${empty task && empty mytask }">
												<c:if test="${leaderTask.endbyuser }">已完成</c:if>
												<c:if test="${!leaderTask.endbyuser }">由系统关闭</c:if>
											</c:if>
											<c:if test="${leaderTask.dueTime < currentTime }">
											<span class="label label-danger">已超时</span>
											</c:if>
										</td>
										<td>
											<c:if test="${empty task && empty mytask }">
												<a href="javascript:void(0);" onclick="act_comment_open('${leaderTask.processInstanceId}',true)"><span class="glyphicon glyphicon-edit"></span> 意见</a>
											</c:if>
											<c:if test="${not empty task || not empty mytask }">
												<a href="javascript:void(0);" onclick="act_comment_open('${leaderTask.processInstanceId}',false)"><span class="glyphicon glyphicon-edit"></span> 意见</a>
											</c:if>
											<a href="javascript:void(0);" onclick="act_history_open('${leaderTask.processInstanceId}')"><span class="glyphicon glyphicon-list-alt"></span> 历史</a>
											<c:if test="${not empty mytask }">
												<c:if test="${empty mytask.assignee }">
													<a class="claim confirm" href="${contextPath }/workflow/task/claim/${mytask.id}"><span class="glyphicon glyphicon-edit"></span> 签收</a>
												</c:if>
												<c:if test="${not empty mytask.assignee }">
													<a href="${contextPath }/leadertask/deal/${leaderTask.id}/${mytask.id}"><span class="glyphicon glyphicon-edit"></span> 办理</a>
												</c:if>
											</c:if>
											<c:if test="${empty mytask }">
												<a href="${contextPath }/leadertask/view/${leaderTask.id}"  target="_blank"><span class="glyphicon glyphicon-search"></span> 详情</a>
											</c:if>
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
        <!-- 动态表单 -->
        <div class="modal fade" id="dynamicForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    	<!-- 动态表单 end --> 
    	<!-- 查询 modal form -->
    	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>查询</h4>
                    </div>
                    <form action="${contextPath }/leadertask/list">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">开始时间:</div>
                                    <div class="col-md-9"><input type="text" name="startTime" class="dateISO"/></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">结束时间:</div>
                                    <div class="col-md-9"><input type="text" name="endTime" class="dateISO"/></div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" id="btn_set_role"> 查询 </button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    </form>
                </div>
            </div>
        </div>
    	<!-- 查询 end from -->
    </div>
</body>

</html>
