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
    
    <title>事件管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>   
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
	<script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script> 
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
      
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/activiti-form.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/activiti-comment.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/activiti-history.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-knowledge.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-cms.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-incident.js'></script>
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
   		var incidentId = '${incident.id}';
   		var processInstanceId = "${incident.processInstanceId}";
   		var p_processInstanceId = "${incident.processInstanceId}";
   		
            $(document).ready(function () {
                $("#eventTable").dataTable({
                	"oLanguage": {
             			"sUrl": "${contextPath}/resources/json/Chinese.json"
         			}
                });
                $(".header").load("${contextPath}/header?t="+pm_random());
  
                act_comment_getlist(processInstanceId,taskId);
                act_history_init();
                $(".tabs").find("li").bind("click",function(){
                	switch($(this).children("a").attr("href"))
                	{
                	case "#tabs-knowledge":
                		pm_incident_getKnowledge();
                		break;
                	case "#tabs-object":
                		pm_incident_getci();
                		break;
                	}
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
                    <li><a href="${contextPath }/incident/list">事件管理</a> <span class="divider">></span></li>       
                    <li class="active">查看事件信息</li>
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
                                <div class="left"><h4>事件信息</h4></div>
                            </div>                                  
                        </div>
                         <div class="block-fluid ucard">                                                                
                            <ul class="rows">
                            <li>
                                <div class="title">流程号:</div>
                                <div class="text">${incident.processInstanceId} </div>
                            </li>
                             <li>
                                <div class="title">事件来源:</div>
                                <div class="text">${incident.sourceName} </div>
                            </li>
                            <li>
                                <div class="title">事件摘要:</div>
                                <div class="text">${incident.abs }</div>
                            </li>
                            <li>
                                <div class="title">详细描述:</div>
                                <div class="text">&nbsp;${incident.detail }</div>
                            </li>
                            <li>
                                <div class="title">联系人:</div>
                                <div class="text">${incident.applyUserName }（电话:${incident.phoneNumber }）</div>
                            </li>
                            <li>
                                <div class="title">房间号:</div>
                                <div class="text">${incident.applyUserRoom==null?"-":incident.applyUserRoom }</div>
                            </li>
                            <li>
                                <div class="title">影响度:</div>
                                <div class="text">${incident.influenceName }</div>
                            </li> 
                            <li>
                                <div class="title">紧急度:</div>
                                <div class="text">${incident.criticalName }</div>
                            </li>
                            <li>
                                <div class="title">优先级:</div>
                                <div class="text">${incident.priority==null?"-":incident.priorityName }</div>
                            </li>
                            <li>
                                <div class="title">事件分类:</div>
                                <div class="text">${incident.category==null?"-":incident.categoryName }</div>
                            </li>
                            <li>
                                <div class="title">事件来源:</div>
                                <div class="text">${incident.source==null?"-":incident.sourceName }</div>
                            </li>
                            <li>
                                <div class="title">事件类型:</div>
                                <div class="text">${incident.type==null?"-":incident.typeName }</div>
                            </li>
                            <li>
                                <div class="title">申报日期:</div>
                                <div class="text"><fmt:formatDate value="${incident.applyTime }" pattern="yyyy-MM-dd HH:mm:ss" /></div>
                            </li>
                            <li>
                                <div class="title">附件:</div>
                                <div class="text">
                                	<c:if test="${empty incident.attachs}">
                                	-
                                	</c:if>
                                	<c:if test="${not empty incident.attachs}">
                                	<c:forEach items="${incident.attachs}" var="attach">
										<a href="${contextPath }/attachment/download/${attach.id}">${attach.name }</a><br/>
									</c:forEach>
									</c:if>
								</div>
                            </li>   
                            </ul>                                                      
                        </div>                     
                    </div>

                  <div class="col-md-8">
                  		<div class="head clearfix">
                            <div class="isw-chats"></div>
                            <h1>事件处理信息</h1>
                        </div>             
                        <div class="block-fluid clearfix" > 
							<table class="table">
								<tr>
									<td width="100px">状态：</td>
									<td>${incident.statusName }</td>
									<td width="100px">恢复时间：</td>
									<td>
										<c:if test="${not empty incident.recoverTime }">
											<fmt:formatDate value="${incident.recoverTime }" pattern="yyyy年MM月dd日 HH:mm:ss"></fmt:formatDate>
										</c:if>
										<c:if test="${empty incident.recoverTime }">
										-
										</c:if>
									</td>
								</tr>
								<tr>
									<td width="100px">受派组：</td>
									<td>${incident.currentDelegateGroup==null?"-":incident.currentDelegateGroup }</td>
									<td width="100px">受派人：</td>
									<td>${incident.currentDelegateUser==null?"-":incident.currentDelegateUserName }</td>
								</tr>
								<tr>
									<td width="100px">解决方案：</td>
									<td colspan="3">${incident.solution==null?"-":incident.solution }</td>
								</tr>
								<tr>
									<td width="100px">满意度：</td>
									<td>${incident.satisfaction==null?"-":incident.satisfactionName }</td>
									<td width="100px">反馈意见：</td>
									<td>${incident.feedback==null?"-":incident.feedback }</td>
								</tr>
							</table>
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
                            <div class="isw-list"></div>
                            <h1>关联信息</h1>
                        </div>
                        <div class="block-fluid tabs">

                            <ul>
                            	<li><a href="#tabs-comment"> 意见 <span class="label label-success" id="commentTitle"></span> </a></li>
                                <li><a href="#tabs-knowledge"> 引用知识 </a></li>
                                <li><a href="#tabs-object"> 关联其它资产 </a></li>
                            </ul>                        
							<div id="tabs-comment">
                            	<div style="height:300px;">
                            		<div id="loader_comment" class="tac"><img src="${contextPath }/resources/img/loaders/loader.gif" title="loader.gif"/></div>
	                            	<div class="block-withoutborder messages scrollBox">                        
			                        	<div id="commentList" class="scroll" style="height: 260px;"></div>
			                        </div>
                            	</div>
                            	<div class="toolbar bottom-toolbar clearfix">                         
	                                <div class="left">  
	                                	<c:if test="${incident.status!='06' }">                                  
	                                    	<button href="#newForm" role="button" data-toggle="modal" class="btn btn-default">添加意见</button>
	                                    </c:if>   
	                                    <c:if test="${incident.status=='06' }">                                  
	                                    	<button href="#newForm" role="button" data-toggle="modal" class="btn btn-default" disabled>添加意见</button>
	                                    </c:if>                                     
	                                </div>                        
                           		 </div>
                            </div>
                            <div id="tabs-knowledge">
                            	<div style="height:300px;">
                                <table class="table" id="knowledgeTable">
	                                <thead>
	                                    <tr>                                    
	                                        <th>标题</th>
	                                        <th width="60px">阅读数</th>
	                                    </tr>
	                                </thead>
	                                <tbody></tbody>
                            	</table>
                            	</div>
                            	<div class="toolbar bottom-toolbar clearfix">                         
	                                <div class="right">                                       
	                                    <ul class="pagination pagination-sm">
	                                        <li class="disabled"><a href="#">Prev</a></li>
	                                        <li class="disabled"><a href="#">1</a></li>
	                                        <li><a href="#">2</a></li>
	                                        <li><a href="#">Next</a></li>
	                                    </ul>                                        
	                                </div>                        
                           		 </div>
                            </div>
                            <div id="tabs-object">
								<div style="height:300px;">
                                <table class="table" id="objectTable">
	                                <thead>
	                                    <tr>                                    
	                                        <th>名称</th>
	                                        <th width="120px">类别</th>
	                                        <th width="120px">位置</th>
	                                        <th width="60px">状态</th>
	                                        <th width="60px">操作</th>
	                                    </tr>
	                                </thead>
	                                <tbody></tbody>
                            	</table>
                            	</div>
                            	<div class="toolbar bottom-toolbar clearfix">                         
	                                <div class="right">                                       
	                                    <ul class="pagination pagination-sm">
	                                        <li class="disabled"><a href="#">Prev</a></li>
	                                        <li class="disabled"><a href="#">1</a></li>
	                                        <li><a href="#">2</a></li>
	                                        <li><a href="#">Next</a></li>
	                                    </ul>                                        
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
                                    <div class="col-md-2">意见：</div>
                                    <div class="col-md-10"><textarea name="fp_message" id="fp_message"></textarea></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                            	&nbsp;&nbsp;&nbsp;&nbsp;<label class='checkbox checkbox-inline'><input type='checkbox' name='isnotify' value='true'/> 提醒任务办理人 </label>
                    		</div>
                    	</div>
                    </div>   
                    <div class="modal-footer">
                    	<input type="hidden" name="redirectAddress" value="/incident/view/${incident.id}" />
                    	<input type="hidden" name="fp_taskId" value="${task.id }" />
                    	<input type="hidden" name="fp_processInstanceId" value="${incident.processInstanceId }" />
                        <button class="btn btn-primary" aria-hidden="true">提交</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="dialog" id="b_popup_knowledge" style="display: none" title="知识库"></div>
    </div>
</body>

</html>