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
    
    <title>变更管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/activiti-comment.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/activiti-history.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-change.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
   		var tkey = '${task.taskDefinitionKey }';
   		var tname = '${task.name }';
   		var taskid = '${task.id }';
   		var ctx = "${contextPath}"; 
   		var changeid = '${change.id}';
   		var processInstanceId = "${change.processInstanceId}";
   		var p_processInstanceId = "${change.processInstanceId}";
   		var status = "${change.status}";
   		
            $(document).ready(function () {
                $(".header").load("${contextPath}/header?t="+pm_random());
                
                act_comment_getlist(processInstanceId,taskid);
                act_history_init();
                $("#form_items").load("${contextPath}/change/items/${change.id}?t="+pm_random(),function(){
            		$("#div_save_button").hide();
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

        <div class="content wide">
            <!--breadline-->
            <div class="breadLine">

                <ul class="breadcrumb">
                    <li><a href="#">运维管理系统</a> <span class="divider">></span></li>
                    <li><a href="${contextPath }/change/list">变更管理</a> <span class="divider">></span></li>       
                    <li class="active">查看变更信息</li>
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
               	
               	  <div class="col-md-4">
                         <div class="headInfo">
                            <div class="toolbar nopadding-toolbar clear clearfix">
                                <div class="left"><h4>变更信息</h4></div>
                            </div>                                  
                        </div>
                         <div class="info">                                                                
                            <ul class="rows">
                            <li>
                                <div class="title">流程号:</div>
                                <div class="text">${change.processInstanceId}</div>
                            </li>
                             <li>
                                <div class="title">变更描述:</div>
                                <div class="text">${change.description} </div>
                            </li>
                            <li>
                                <div class="title">影响度:</div>
                                <div class="text">${change.influenceName }</div>
                            </li> 
                            <li>
                                <div class="title">紧急性:</div>
                                <div class="text">${change.criticalName }</div>
                            </li>
                            <li>
                                <div class="title">优先级:</div>
                                <div class="text">${change.priorityName }</div>
                            </li>
                            <li>
                                <div class="title">风险程度:</div>
                                <div class="text">${change.riskName }</div>
                            </li>
                            <li>
                                <div class="title">变更分类:</div>
                                <div class="text">${change.typeName }</div>
                            </li>
                            <li>
                                <div class="title">变更申请人:</div>
                                <div class="text">${change.applyUserName }</div>
                            </li>
                            <li>
                                <div class="title">申报日期:</div>
                                <div class="text"><fmt:formatDate value="${change.applyTime }" pattern="yyyy-MM-dd HH:mm:ss" /></div>
                            </li>   
                            </ul>                                                      
                        </div>                     
               
                    </div>

                  <div class="col-md-8">
                  		<div class="head clearfix">
                            <div class="isw-chats"></div>
                            <h1>变更处理信息</h1>
                        </div>             
                        <div class="block-fluid clearfix" > 
                        	<table cellpadding="0" cellspacing="0" width="100%" class="table">
                        		<tr>
									<td width="100px">状态：</td>
									<td>${change.statusName }</td>
									<td width="100px">结束时间：</td>
									<td>
										<c:if test="${not empty change.endTime }">
											<fmt:formatDate value="${change.endTime }" pattern="yyyy年MM月dd日 HH:mm:ss"></fmt:formatDate>
										</c:if>
										<c:if test="${empty change.endTime }">
										-
										</c:if>
									</td>
								</tr>
								<tr>
									<td width="100px">变更开始时间</td>
									<td>
										<c:if test="${not empty change.planStartTime }">
											<fmt:formatDate value="${change.planStartTime }" pattern="yyyy年MM月dd日 HH:mm:ss"></fmt:formatDate>
										</c:if>
										<c:if test="${empty change.planStartTime }">
										-
										</c:if>
									</td>
									<td width="100px">变更结束时间</td>
									<td>
										<c:if test="${not empty change.planEndTime }">
											<fmt:formatDate value="${change.planEndTime }" pattern="yyyy年MM月dd日 HH:mm:ss"></fmt:formatDate>
										</c:if>
										<c:if test="${empty change.planEndTime }">
										-
										</c:if>
									</td>
								</tr>
								<tr>
									<td width="100px">变更方案</td>
									<td  colspan="3">${change.solution==null?"-":change.solution }</td>
								</tr>
								<tr>
									<td width="100px">回退方案</td>
									<td  colspan="3">${change.fallback==null?"-":change.fallback }</td>
								</tr>
								<tr>
									<td width="100px">变更实施人</td>
									<td>${change.delegateUser==null?"-":change.delegateUserName }</td>
									<td width="100px">变更审批</td>
									<td>${change.approve==null?"-":change.approve }</td>
								</tr>
								<tr>
									<td width="100px">变更结果</td>
									<td  colspan="3">${change.result==null?"-":change.result }</td>
								</tr>
							</table>
                        </div>
                        
                        <div class="head clearfix">
                            <div class="isw-chats"></div>
                            <h1>变更详情</h1>
                        </div>
                        <div id="div_items" class="block clearfix" >
                         	<form id="form_items" action="${contextPath }/change/${change.id}/saveitems/" method="post"></form>
                        </div>
                        <div class="head clearfix">
                            <div class="isw-sync"></div>
                            <h1>操作历史</h1>
                            <ul class="buttons">        
                                <li class="toggle active"><a href="#"></a></li>
                            </ul> 
                        </div>             
                        <div class="block history" > 
                        	<div id='scroll_history' class='scroll' style='height:260px;display:none;'></div>
                        </div>
						<div class="head clearfix">
                            <div class="isw-mail"></div>                        
                            <h1>意见列表 <span id="commentTitle" class="label label-success"></span></h1>
                            <ul class="buttons">
                                <li class="toggle"><a href="#"></a></li>                                                    
                            </ul>
                        </div>
                        <div>
							<div class="block messages scrollBox">
								<div id="commentList" class="scroll" style="height: 250px;"></div>

								<div class="toolbar bottom-toolbar clearfix">
									<div class="left">
										<c:if test="${change.status!='08' }">
											<button href="#newForm" role="button" data-toggle="modal" class="btn btn-default">添加意见</button>
										</c:if>
										<c:if test="${change.status=='08' }">
											<button href="#newForm" role="button" data-toggle="modal" class="btn btn-default" disabled>添加意见</button>
										</c:if>
									</div>
								</div>
							</div>
						</div>
                    	
                    </div>
               	
               	</div>
            </div>
            <!--workplace end-->
        </div>   
        <div class="modal fade" id="newForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>添加意见</h4>
                    </div>
                    <form action="${contextPath}/workflow/task/comment/save" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">意见：</div>
                                    <div class="col-md-9"><textarea name="fp_message" id="fp_message"></textarea></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                        	&nbsp;&nbsp;&nbsp;&nbsp;<label class='checkbox checkbox-inline'><input type='checkbox' name='isnotify' checked='checked' value='true'/> 提醒任务办理人 </label>
                    		</div>
                    	</div>
                    </div>   
                    <div class="modal-footer">
                    	<input type="hidden" name="redirectAddress" value="/change/view/${change.id}" />
                    	<input type="hidden" name="fp_taskId" value="${task.id }" />
                    	<input type="hidden" name="fp_processInstanceId" value="${change.processInstanceId }" />
                        <button class="btn btn-primary" aria-hidden="true">提交</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    </form>
                </div>
            </div>
        </div><!-- comment -->
        
        <div class="dialog" id="b_popup_knowledge" style="display: none" title="知识库"></div>
        
    </div>
</body>

</html>