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
    
    <title>领导交办管理--运维管理系统</title>

    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/activiti-comment.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/jquery.form.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
   		var tkey = '${task.taskDefinitionKey }';
   		var tname = '${task.name }';
   		var taskId = '${task.id }';
   		var ctx = "${contextPath}"; 
   		var leaderTaskId = '${leaderTask.id}';
   		var processInstanceId = '${leaderTask.processInstanceId}';
   		
            $(document).ready(function () {
                act_comment_getlist(processInstanceId,taskId);
            });
    </script>
</head>
<body>
    <div class="wrapper"> 

        <div class="content wide">

            <!--workplace-->
            <div class="workplace">             
                <div class="row">
               	
               	  <div class="col-md-5">
                         <div class="headInfo">
                            <div class="toolbar nopadding-toolbar clear clearfix">
                                <div class="left"><h4>交办信息</h4></div>
                            </div>                                  
                        </div>
                         <div class="block-fluid ucard">                                                                
                            <ul class="rows">
                            <li>
                                <div class="title">流程号:</div>
                                <div class="text">${leaderTask.processInstanceId}</div>
                            </li>
                             <li>
                                <div class="title">交办领导:</div>
                                <div class="text">${leaderTask.fromUserName} </div>
                            </li>
                            <li>
                                <div class="title">交办对象:</div>
                                <div class="text">${leaderTask.toUserName }</div>
                            </li>
                            <li>
                                <div class="title">任务内容:</div>
                                <div class="text">${leaderTask.taskTitle }</div>
                            </li>
                            <li>
                                <div class="title">到期时间:</div>
                                <div class="text"><span class="text-danger"><fmt:formatDate value="${leaderTask.dueTime }" pattern="yyyy-MM-dd"></fmt:formatDate></span></div>
                            </li>
                            <li>
                                <div class="title">交办时间:</div>
                                <div class="text"><fmt:formatDate value="${leaderTask.applyTime }" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></div>
                            </li>    
                            </ul>                                                      
                        </div> 
                  </div>

                  <div class="col-md-7" id="workflow_widge">
                                          
                        <div class="head clearfix">
                            <div class="isw-chats"></div>
                            <h1>工作情况</h1>
                        </div>             
                        <div class="block-fluid " > 
                    		<div class="row-form clearfix">
                    			<div class="col-md-2">完成时间：</div>
                    			<div class="col-md-10"><fmt:formatDate value="${leaderTask.executionTime }" pattern="yyyy年MM月dd日 HH:mm"></fmt:formatDate></div>
                    		</div>
                    		<div class="row-form clearfix">
                    			<div class="col-md-2">工作结果：</div>
                    			<div class="col-md-10">${leaderTask.taskResult }</div>
                    		</div>
                        </div>
                        
                       <div class="head clearfix">
                            <div class="isw-mail"></div>                        
                            <h1>意见列表 <span class="label label-success" id="commentTitle"></span></h1>
                            <ul class="buttons">
                            	<c:if test="${empty leaderTask.executionTime }" >
                                <li>
                                    <a href="#newForm"  role="button" data-toggle="modal" class="isw-plus tipl" title="添加意见"></a>                            
                                </li> 
                                </c:if> 
                                <li class="toggle"><a href="#"></a></li>                                                  
                            </ul>
                        </div>
                        <div class="block messages scrollBox">                        
                            <div id="commentList" class="scroll" style="height: 250px;">
                            </div>
                        </div> 

                  </div>

               	</div>
            </div>
            <!--workplace end-->
        </div>
        <!-- comment -->
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
                        	<label class='checkbox checkbox-inline'><input type='checkbox' name='isnotify' checked='checked' value='true'/> 提醒任务办理人 </label>
                    		</div>
                    	</div>
                    </div>   
                    <div class="modal-footer">
                    	<input type="hidden" name="redirectAddress" value="/leadertask/view/${leaderTask.id}" />
                    	<input type="hidden" name="fp_taskId" value="${task.id }" />
                    	<input type="hidden" name="fp_processInstanceId" value="${leaderTask.processInstanceId }" />
                        <button class="btn btn-primary" aria-hidden="true">提交</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    </form>
                </div>
            </div>
        </div>
    	<!-- comment end -->
    </div>
</body>

</html>