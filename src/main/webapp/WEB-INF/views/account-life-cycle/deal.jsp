<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>台账管理--运维管理系统</title>

    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/multi-select.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href='${contextPath }/resources/js/plugins/jstree/jquery.treeview.css' rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/js/plugins/select2/select2.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/js/plugins/datetimepicker/datetimepicker.min.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
	<script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.edit.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.async.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/datetimepicker/datetimepicker.min.js' charset="UTF-8"></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/datetimepicker/datetimepicker.zh-CN.js'></script>
      
    <script type='text/javascript' src='${contextPath }/resources/js/jquery.form.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/activiti-form.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-cms.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
    	var taskDefinitionKey = '${task.taskDefinitionKey}';
    	var processInstanceId = ${task.processInstanceId};
    	
    	var type = '${type}';
        $(document).ready(function () {
          	$(".header").load("${contextPath }/header?t=" + pm_random());
            $(".menu").load("${contextPath }/menu?t=" + pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
            $(".breadLine .buttons").load("${contextPath }/contentbuttons?t=" + pm_random());
          	
            //表单验证
            $("#validation").validationEngine({promptPosition : "topRight", scroll: true});
            
         	//创建关联对象
            $("#b_popup_ci").dialog({
                autoOpen: false,
                width: 1000,
                height:500,
                buttons: { "选择": function () { 
                	pm_cms_ciselected('life-cycle');
                	$(this).dialog("close") 
                }, "关闭": function () { $(this).dialog("close") } }
            });
         	
            $('#treeview').treeview({
            	url : ctx + '/cms/category/getjson?t=' + pm_random()
            });
            
            $("#btn_set_property").bind("click",getProperty);
            
            act_form_task(${task.id},'/workflow/task/mytask');   
            
            if(taskDefinitionKey=="usertask1") {
            	$("#div_change_setting").show();
            	pm_life_cycle_getci();
            } else if(taskDefinitionKey=="approve") {
            	$("#div_items_view").show();
            	$("#div_items_view").load("${contextPath}/change/items0/"+processInstanceId+"?t="+pm_random());
            } else if(taskDefinitionKey=="insertResult") {
            	$("#div_items").show();
            	$("#form_items").load("${contextPath}/change/items/"+processInstanceId +"?t="+pm_random());
            } else if(taskDefinitionKey=="confirmResult") {
            	$("#div_items").show();
            	$("#form_items").load("${contextPath}/change/items/"+processInstanceId +"?t="+pm_random(),function(){
            		$("#div_save_button").hide();
            	});
            }
        });
        
        function initTable(code) {
        	pm_cms_initdialogtable(code);
        }
        
        function pm_life_cycle_getci()
        {
        	$.getJSON(ctx+'/account-life-cycle/getitems?processInstanceId='+processInstanceId+'&t='+pm_random(),function(data){
        		$("#itemTable tbody tr").remove();
        		var trs = "";
        		var items = data.items;
        		var ids="";
        		$.each(items,function(i,v){
        			if(ids!="")
        				ids += ",";
        			ids += v.ciId; 
        		});
        		if(ids.length>0) {
        			$.getJSON(ctx+'/cms/ci/getjson/'+ids+'/?t='+pm_random(),function(data){
        				var map = new Object();
        				$.each(data.cis,function(i,v){
        					map[v.id] = v;
        				});
        				$.each(items,function(i,v){
        					
        					trs += "<tr>"
        						+"<td>"+(i+1)+"</td>"
        						+"<td><a href='"+ctx+"/cms/ci/detail/"+v.ciId+"' target=_blank>"+map[v.ciId].name+"</a></td>";
        					if( v.propertiesName==null )
        						trs += "<td></td>";
        					else
        						trs += "<td>"+v.propertiesName+"</td>";
        					trs += "<td>";
        					if(type=='change')
        						trs += "<a href='#' onclick='setProperty("+v.id+","+v.ciId+",\""+v.properties+"\")'><span class='glyphicon glyphicon-pencil'></span></a>";
        					trs +="</td></tr>";
        				});
        				$("#itemTable tbody").append(trs);
        			});
        		}
        	});
        	
        }
        
        function removeci(id,obj)
        {
        	//obj.parentNode.parentNode.remove();
        	$.getJSON(ctx+'/account-life-cycle/deleteitem?itemid='+id+'&t='+pm_random(),function(data){
        		if(data.result)
        			itemids0 = itemids0.replace(id,'');
        			//$(obj).parent().parent().remove();
        			pm_life_cycle_getci(itemids0);
        			
        			$("input[name='itemid']").val(itemids0);
        	});
        	return false;
        }
        function setProperty(id,ciid,properties){
        	
        	$.getJSON(ctx + '/cms/ci/getproperty/' + ciid +'?t=' + pm_random(),function(data){
        		$("#sel_properties option").remove();
        		$("#sel_field option").remove();
        		// 参数
        		$.each(data.properties,function(key,value){
        			$.each(value,function(i,v){
        				$("#sel_properties").append("<option value='"+v.propertyId+"'>"+v.propertyName+"</option");
        			});
        		});
        		$("#sel_properties").multiSelect("refresh");
        		// 字段
        		$.each(data.fields,function(i,v){
        			$("#sel_field").append("<option value='"+v.propertyId+"'>"+v.propertyName+"</option");
        		});
        		$("#sel_field").multiSelect("refresh");
        		if(properties!="null")
        		{
        			ids= properties.split(",");
        			for(i=0;i<ids.length;i++)
        			{
        				if(ids[i].indexOf("CMS_FIELD_")==0)
        					$("#sel_field").multiSelect("select",ids[i]);
        				else
        					$("#sel_properties").multiSelect("select",ids[i]);
        			}
        		}
        			
        		$("#fm_id").attr('value',id);
        		$("#selPropertyDialog").modal('show');
        	});	
        }
        
        function getProperty(){
        	var properties="",fields="",propertiesName = "",fieldsName="";
        	properties = $("#sel_properties").val();
        	if(properties!=null)
        		properties == "";
        	fields = $("#sel_field").val();
        	if(fields!=null)
        	{
        		if(properties !=null && properties!="")
        			properties = "," + properties;
        		if(properties ==null)
        			properties = fields; 
        		else
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
        		pm_life_cycle_getci();
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
                    <li><a href="${contextPath }/incident/list">事件管理</a> <span class="divider">></span></li>   
                    <c:if test="${not empty incident.id }" >     
                    	<li class="active">修改事件信息</li>
                    </c:if>
                    <c:if test="${empty incident.id }" >     
                    	<li class="active">创建新事件</li>
                    </c:if>
                </ul>
                <ul class="buttons"></ul>
            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">

                <div class="row">
                	<div class="col-md-2"></div>
	                <div class="col-md-8">
	                        <div class="head clearfix">
	                            <div class="isw-empty_document"></div>
	                            <h1>${task.name }</h1>
	                        </div>
	                        <div class="block-fluid">
	                        <div id="div_items" class="row-form" style="display:none">                                                     
                            	<form id="form_items" action="${contextPath }/change/${task.processInstanceId}/saveitems/" method="post"></form>
                    		</div>
	                        <div id="div_items_view" class="row-form" style="display:none">                                                     
                            	
                    		</div>
	                        	<div id="div_change_setting" class="without-head" style="display:none">                        
	                            <div class="toolbar nopadding-toolbar clearfix">
	                                <h4>选取要修改的项</h4>
	                            </div>                         
	                            <table style="cellpadding:0;cellspacing:0;width:100%;" class="table images" id="itemTable">
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
                        	</div>                        
	                            <div id="workflow_task_form"></div>                           
	                        </div>
	                    </div>
	                <div class="col-md-2"></div>
                </div>

            </div>
            <!--workplace end-->
        </div>  
    </div>
    <div class='dialog' id='b_popup_ci' style='display: none;' title='关联对象'>
    		<div class='block-fluid dialog_block table-sorting clearfix'>
    			<div class='col-md-3'>
    		 		<div id='treeview'></div>
    		  </div>
    		  <div class='col-md-9'>
    		  	<div class='block-fluid'>                  
    		      	<table class='table' id='ciTable'>
    		          <thead>
    		          <tr>
    		             	<th width='60px'><input type='checkbox' name='checkall'/></th>
    						<th>名称</th>
    						<th width='120px'>使用部门</th>
    						<th width='80px'>状态</th>
    						<th width='80px'>审核状态</th>
    						<th width='80px'>删除状态</th>
    					</tr>
    		          </thead>
    		          <tbody>
    		          </tbody>
    		          </table>
    		    </div>
    		  </div> 
    		</div>
    </div>
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
</body>
</html>
