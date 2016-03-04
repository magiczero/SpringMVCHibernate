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
    
    <title>自助报修--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>  
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>  
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-workflow.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
            $(document).ready(function () {
                $("#myTable").dataTable({
                	"oLanguage": {
             			"sUrl": "${contextPath}/resources/json/Chinese.json"
         			},
                	"aaSorting":[[4,'desc']]});
                $(".header").load("${contextPath }/header?t=" + pm_random());
                $(".menu").load("${contextPath }/menu?t=" + pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath }/contentbuttons?t=" + pm_random());
                
                pm_workflow_inittracedialog(950,350);
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
                    <li><a href="${contextPath }/incident/mylist">自助报修</a> <span class="divider">></span></li>       
                    <li class="active">处理中的报修</li>
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
                            <h1>处理中的报修</h1>  

                            <ul class="buttons">
                                <li>
                                    <a href="${contextPath }/incident/addbyuser" class="isw-text_document tipb" title="新报修"></a>
                                </li>                            
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="myTable">
                                <thead>
                                    <tr>
                                        <th>摘要</th>
                                        <th width="100px">申请人</th>
                                        <th width="100px">受派者</th>
                                        <th width="140px">流程步骤</th>
                                        <th width="140px">申请时间</th>
                                        <th width="80px">状态</th> 
                                        <th width="70px">操作</th>                                    
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list }" var="incident">
	                                	<c:set var="task" value="${tasks[incident.processInstanceId]}" />
	                                	<c:set var="mytask" value="${mytasks[incident.processInstanceId]}" />
	                                    <tr>
	                                        <td>
	                                        	<span class="label label-warning tipb" title="优先级">${incident.priorityName }</span>
	                                        	${incident.abs }
	                                        </td>
	                                        <td>${incident.applyUserName }</td>
	                                        <td>${incident.currentDelegateUserName }</td>
	                                        <td>
												<a class="lnk_trace" href='#' pid="${incident.processInstanceId }" pdid="${task.processDefinitionId }" title="点击查看流程图">
													${task.name }
												</a>
											</td>
	                                        <td><fmt:formatDate value="${incident.applyTime }" pattern="yyyy-MM-dd HH:mm" /></td>
	                                        <td>${incident.statusName }</td>
	                                        <td>
	                                        	<c:if test="${empty mytask }">
	                                        		<a class="claim" href="${contextPath }/incident/view/${incident.id}"><span class="glyphicon glyphicon-search"></span> 查看</a>
												</c:if>
												<c:if test="${not empty mytask }">
													<c:if test="${not empty mytask.assignee }">
														<a href="${contextPath }/incident/deal/${incident.id}/${task.id}"><span class="glyphicon glyphicon-edit"></span> 办理</a>
													</c:if>
													<c:if test="${empty mytask.assignee }">
														<a class="claim confirm" href="${contextPath }/workflow/task/claim/${task.id}"><span class="glyphicon glyphicon-edit"></span> 签收</a>
	                                        		</c:if>
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
    </div>
</body>

</html>