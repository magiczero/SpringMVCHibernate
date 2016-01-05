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
    <link href="${contextPath }/resources/css/select2.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/multi-select.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/treeview/bootstrap-treeview.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/jquery.form.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/activiti-form.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-knowledge.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/activiti-comment.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-cms.js'></script>
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
   		var status = "${change.status}";
   		
   		//使用模板必须定义模板数据的获取地址
   		var get_templatedata_address = ctx + '/change/' + changeid + '/template/get?t=' + pm_random();
   		
            $(document).ready(function () {
                $("#eventTable").dataTable();
                $(".header").load("${contextPath}/header?t="+pm_random());
                $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
				$("#btn_set_property").bind("click",getProperty);
                
				act_form_task(taskid,'/change/list');   
				pm_knowledge_initdialog("incident",changeid);
                act_comment_getlist(processInstanceId,taskid);
                if(status=="05")
                {
                	$("#div_change_setting").show();
                	pm_cms_initselectdialog('change');
                	pm_change_getci();
                }
                if(status=="06")
                {
                	$("#div_items").show();
                	$("#form_items").load("${contextPath}/change/items/${change.id}?t="+pm_random());
                }
                if(status=="07")
                {
                	$("#div_items").show();
                	$("#form_items").load("${contextPath}/change/items/${change.id}?t="+pm_random(),function(){
                		$("#div_save_button").hide();
                	});
                }
            });
            function getProperty(){
            	var properties="",fields="",propertiesName = "",fieldsName="";
            	properties = $("#sel_properties").val();
            	if(properties!=null)
            		properties == "";
            	fields = $("#sel_field").val();
            	if(fields!=null)
            	{
            		if(properties!="")
            			properties = "," + properties;
            		properties = fields + properties;
            	}
            	propertiesName = $("#sel_properties").find('option:selected').map(function(){ return $(this).text() }).get();
            	fieldsName = $("#sel_field").find('option:selected').map(function(){ return $(this).text() }).get();
            	if(fieldsName!="")
            	{
            		if(propertiesName!="")
            			propertiesName = "," + propertiesName;
            		propertiesName = fieldsName + propertiesName;
            	}
            	
            	$("#fm_properties").attr('value',properties);
            	$("#fm_propertiesName").attr('value',propertiesName);
            	$("#selPropertyForm").ajaxSubmit(function(){
            		alert("保存成功!");
            		pm_change_getci();
            	});
            	return false;
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

        <div class="content">
            <!--breadline-->
            <div class="breadLine">

                <ul class="breadcrumb">
                    <li><a href="#">运维管理系统</a> <span class="divider">></span></li>
                    <li><a href="${contextPath }/Asset/list">变更管理</a> <span class="divider">></span></li>       
                    <li class="active">变更处理</li>
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
               	
               	  <div class="col-md-5">
                     	<div style="text-align:right;">
                            <div class="btn-group">
                                <button class="btn btn-default" type="button" id="lnk_knowledge">查看知识库</button>
                            </div>
                        </div>
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
                                <div class="text">${change.applyTime }</div>
                            </li>   
                            </ul>                                                      
                        </div>                     
               
                    </div>

                  <div class="col-md-7">
                        <div class="head clearfix">
                            <div class="isw-chats"></div>
                            <h1>${task.name }</h1>
                        </div>             
                        <div class="block-fluid clearfix" > 
                        	<div id="div_template" class="row-form" style="display:none">                                                     
                            	<form id="form_template" action="${contextPath }/change/${change.id}/template/save/" method="post"></form>
                    		</div>
                    		<div id="div_items" class="row-form" style="display:none">                                                     
                            	<form id="form_items" action="${contextPath }/change/${change.id}/saveitems/" method="post"></form>
                    		</div>
                    		<div id="div_change_setting" class="without-head" style="display:none">                        
	                            <div class="toolbar nopadding-toolbar clearfix">
	                                <h4>变更排程</h4>
	                            </div>                         
	                            <table cellpadding="0" cellspacing="0" width="100%" class="table images" id="itemTable">
	                                <thead>
	                                    <tr>
	                                        <th width="50px">序号</th>
	                                        <th width="200px">配置项</th>
	                                        <th>待修改参数</th>
	                                        <th width="60px">操作</th>                                
	                                    </tr>
	                                </thead>
	                                <tbody>                                            
	                                </tbody>
	                            </table>                    
	                            <div class="toolbar bottom-toolbar clearfix">
	                                <div class="left">
	                                	<button type="button" class="btn btn-default tip" title="添加配置项" onclick="pm_cms_addRelations('a')">添加</button>
	                                </div>                                                  
	                            </div>                    
                        	</div><!-- change -->
                    		<div id="workflow_task_form"></div>
                        </div>
                  
                  		<div class="head clearfix">
                            <div class="isw-mail"></div>                        
                            <h1>意见列表 <span id="commentTitle" class="label label-success"></span></h1>
                            <ul class="buttons">
                                <li>
                                    <a href="#newForm"  role="button" data-toggle="modal" class="isw-plus tipl" title="添加意见"></a>                            
                                </li>
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
        <!-- comment start -->  
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
                    	<input type="hidden" name="redirectAddress" value="/change/deal/${change.id}/${task.id}" />
                    	<input type="hidden" name="fp_taskId" value="${task.id }" />
                    	<input type="hidden" name="fp_processInstanceId" value="${change.processInstanceId }" />
                        <button class="btn btn-primary" aria-hidden="true">提交</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- comment end -->
        <!-- property -->
        <div class="modal fade" id="selPropertyDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>选择需变更的信息</h4>
                    </div>
                    <form id="selPropertyForm" action="${contextPath}/change/updateitem" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                            	<div class="row-form clearfix">
                            		<div class="col-md-2">配置项字段</div>
                                    <div class="col-md-10">
                                    <select multiple class="multiselect" id="sel_field" name="sel_field">                              
                                    </select>
                                    </div>
                                </div> 
                                <div class="row-form clearfix">
                                	<div class="col-md-2">可选属性</div>
                                    <div class="col-md-10">
                                    <select multiple class="multiselect" id="sel_properties" name="sel_properties">                              
                                    </select>
                                    </div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-default" id="btn_set_property">保存</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    <input type="hidden" id="fm_properties"  name="fm_properties" value="0" />
                    <input type="hidden" id="fm_propertiesName"  name="fm_propertiesName" value="0" />
                    <input type="hidden" id="fm_id"  name="fm_id" value="0" /> 
                    </form>
                </div>
            </div>
        </div><!-- property -->
    </div>
</body>

</html>