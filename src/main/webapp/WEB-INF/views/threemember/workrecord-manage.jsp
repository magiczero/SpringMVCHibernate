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
    
    <title>三员管理--运维管理系统</title>

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
    <link href="${contextPath }/resources/js/plugins/select2/select2.css" rel="stylesheet" type="text/css" />
    <link href='${contextPath }/resources/js/plugins/jstree/jquery.treeview.css' rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.edit.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.async.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
        $(document).ready(function () {
        	var table = $("#eventTable").dataTable({
        		"oLanguage": {"sUrl": "${contextPath}/resources/json/Chinese.json"},
        	"bProcessing": false, // 是否显示取数据时的那个等待提示
		    "bServerSide": true,//这个用来指明是通过服务端来取数据
            "sAjaxSource": "${contextPath}/three-member/workrecord-with-table",//这个是请求的地址
            "fnServerData": retrieveData, // 获取数据的处理函数
            "fnServerParams": function (aoData) {  //查询条件
                aoData.push(
                		{ "name": "userid", "value": $("#user_id_search").val() },
                		{ "name": "type_", "value": $("#type_").val() },
                		{ "name": "starttime", "value": $("input[name='startTime']").val() },
                		{ "name": "endtime", "value": $("input[name='endTime']").val() },
                		{ "name": "cmdbid", "value": $("#cmdb_search").val() }
                    );
            },
            "aoColumnDefs": [ {
                "aTargets": [ 8 ],
                "mData": "download_link",
                "mRender": function ( data, type, full ) {
                  return '<a href="#" data-href="workrecord/'+data+'" data-toggle="modal" data-target="#confirm-delete">删除</a>';
                }
              } ],
            "aoColumns" : [
                           { "mData" : 'cmdbname' }, 
                           { "mData" : 'rolename' }, 
                           { "mData" : 'username' }, 
                           { "mData" : 'itemname' }, 
                           { "mData" : 'actionname' }, 
                           { "mData" : 'intime' }, 
                           { "mData" : 'detail' }, 
                           { "mData" : 'basis'},
                           { "mData" : 'id'},
                             ]
            });
            
            $(".header").load("${contextPath}/header?t="+pm_random());
            $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active");  });
            $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
            $(".confirm").bind("click",function(){
               	if(!confirm("确定要执行该操作?"))
               		return false;
            });
            
            $(".dateISO").datepicker(); 
            
            $('#cmdbin').change(function(){ 
            	var cmdbid = $(this).children('option:selected').val();
            	
            	$.getJSON("get-mt-and-item-by-cmdb?t=" + pm_random()+"&cmdbId="+cmdbid, function(data){
            		//console.log(data);
            		$('#typeid').attr("value",data.id);
            		$('#type_').attr("value",data.desc);
            		
            		$.each(data.items, function(i,item){
            			$("#item_").append("<option value='"+item.itemid+"'>"+item.itemname+"</option>");
            		});
            	});
            });
            
            $('#confirm-delete').on('show.bs.modal', function(e) {
            	
            	$(this).find('.btn-ok').bind('click', function(){
            		//提交删除
            		 $.ajax({
                         type: "DELETE",
                         url: $(e.relatedTarget).data('href'),
                         //data: {username:$("#username").val(), content:$("#content").val()},
                         dataType: "text xml",
                         success: function(xml, textStatus, xhr) {
                             console.log(xml);
                             console.log(textStatus);
                             console.log(xhr.status);
                             if(xhr.status == '204'){
                            	var table = $("#eventTable").dataTable();
                             	table.fnDraw();
                             } else if(xhr.status == '203') {
                            	 notify_e('Error','删除失败，你没有权限删除这个记录');
                             }
                         },
                         error:function (xhr, textStatus, errorThrown) 
                         { 
                        	 if(xhr.status == '404') {
                            	 //右下角弹出提示
                            	 notify_e('Error','删除失败，没有这个记录');
                             } 
                         } 
                     });
            		$(this).unbind('click');
            		$('#confirm-delete').modal('hide');
            		
            	});
            });
            
            $('#item_').change(function(){ 
            	var itemid = $(this).children('option:selected').val();
            	var typeid = $('#typeid').val();
            	
            	$.getJSON("get-action-by-item-and-mt?t=" + pm_random()+"&itemId="+itemid+"&mt="+typeid, function(data){
            		//console.log(data);
	            	$.each(data, function(i,relation){
	        			$("#action_").append("<option value='"+relation.action.id+"'>"+relation.action.name+"</option>");
	        		});
            	});
            });
            
		    $("#b_popup_group").dialog({
		        autoOpen: false,
		        width: 400,
		        height:500,
		        buttons: { "确定": function () { $(this).dialog("close") } }
		    });
		    
		    $("input[name='user_name']").bind("click",function(){
		    	$("#grouptree").empty();
		    	$("#grouptree").treeview({
		    		url:ctx + '/group/all-json?haveuser=true&t=' + pm_random(),
            		collapsed: true,
            		unique: true
            	});
	    		$("#b_popup_group").dialog('open');
            });
		    
		  	//表单验证提交
            $("#form1").validationEngine('attach', {
                promptPosition: 'topRight',
                scroll: false,
                ajaxFormValidation: true,
                ajaxFormValidationMethod: 'post',  //设置 Ajax 提交时，发送数据的方式
                //ajaxFormValidationURL : "../api/person",
                onBeforeAjaxFormValidation: beforeCall,
                onAjaxFormComplete: ajaxValidationCallback,
            });
            
            $("#btn_find_threemember").bind("click", function () { //按钮 触发table重新请求服务器
                table.fnDraw();
                $("#myModal").modal('hide');
            });
            
        });

        function retrieveData( sSource111,aoData111, fnCallback111) {
			$.ajax({
				url : sSource111,//这个就是请求地址对应sAjaxSource
				data : {"aoData":JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				type : 'post',
				dataType : 'json',
				async : false,
				success : function(result) {
					fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
					
					//$("#eventTable").rowspan(0);
				},
				error : function(msg) {
				}
			});
		}
        
        function inputGroupinfo(groupid, groupname) {
        	if(window.console)
        	console.log(groupid + ' ' + groupname);
        }
        
        function inputUserinfo(username,realname,tel,room,userid) {
        	$("input[name='userid']").attr("value", userid);
        	$("input[name='user_name']").attr("value",realname);
        	//ajax获取部门的CMDB
        	$.getJSON("get-cmdb-list?t=" + pm_random()+"&username="+username, function(data){
        		$("#cmdb_search").empty();
        		$("#cmdb_search").append("<option value='0'>全部</option>");
        		$.each(data, function(i,item){
        			$("#cmdb_search").append("<option value='"+item.id+"'>"+item.name+"</option>");
        		  });
        		$("#cmdb_search").select2();
			});
        	$("#b_popup_group").dialog('close');
        }
        
        function beforeCall(form, options) {
            
            return true;
        }

        // Called once the server replies to the ajax form validation request

        function ajaxValidationCallback(status, form, json, options) {
            if (window.console) {
                console.log("return status :" + status);
            }
            if (status === true) {		
                if(json.OK) {	//保存成功，给出提示
                	var table = $("#eventTable").dataTable();
                	table.fnDraw();
                	 $("#addModal").modal('hide');
                }
            }
        }
        
        jQuery.fn.rowspan = function(colIdx) {
        	return this.each(function(){
            	var that;
            	$('tbody tr', this).each(function(row) {
	            	$('td:eq('+colIdx+')', this).filter(':visible').each(function(col) {
		            	if (that!=null && $(this).html() == $(that).html()) {
			            	rowspan = $(that).attr("rowSpan");
			            	if (rowspan == undefined) {
				            	$(that).attr("rowSpan",1);
				            	rowspan = $(that).attr("rowSpan");
				            } 
			            	rowspan = Number(rowspan)+1;
			            	$(that).attr("rowSpan",rowspan);
			            	$(this).hide();
		            	} else {
		            		that = this;
		            	}
	            	});
            	});
        	});
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
                    <li><a href="#">运维工作管理</a> <span class="divider">></span></li>       
                    <li class="active">三员工作</li>
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
                            <h1>三员工作记录</h1>  
							<ul class="buttons">
                                <li>
                                    <a href="#" class="isw-zoom tipb" data-toggle="modal" data-target="#myModal" title="查询"></a>
                                </li>                            
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                    	<li><a href="#" data-toggle="modal" data-target="#addModal"><span class="isw-plus"></span> 填写工作记录</a></li>
                                        <li><a href="#" data-toggle="modal" data-target="#myModal"><span class="isw-zoom"></span> 三员查询统计</a></li>
                                        <li><a href="#" onclick="pm_refresh()"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="eventTable">
                                <thead>
                                	<tr>
                                		<th>CMDB</th>
                                		<th>三员角色</th>
										<th>三员</th>
										<th>操作项</th>
										<th>操作动作</th>
										<th>时间</th>
										<th width="20%">详细信息</th>
										<th>依据</th>
										<th>操作</th>
									</tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>  
                </div>
                <div class="dr"><span></span></div>
   
            </div>
            <!--workplace end-->
        </div>  
        <!-- 添加 modal form -->
    	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>填写工作记录</h4>
                    </div>
                    <form action="${contextPath }/three-member/save-workrecord" id="form1">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                            <div class="row-form clearfix">
                                    <div class="col-md-2"><label for="cmdbin">CMDB:</label></div>
                                    <div class="col-md-4"><select id="cmdbin" name="cmdbin" class="validate[required]" style="width: 100%;"><option value=''>请选择……</option>
                                    <c:forEach items="${relationshipList }" var="relationship"><option value='${relationship.cmdb.id }'>${relationship.cmdb.name }</option></c:forEach>
                                    </select></div>
                                    <div class="col-md-2"><label for="type_">选择三员:</label></div>
                                    <div class="col-md-4">
                                    	<input type="hidden" id="typeid" name="typeid" class="validate[required]"/>
										<input type="text" value="" id="type_" name="type_" readonly="readonly" class="validate[required]"/>
									</div>
                                </div> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                            <div class="row-form clearfix">
                                    <div class="col-md-2"><label for="itemname">操作项:</label></div>
                                    <div class="col-md-4">
                                    	<select id="item_" name="item_" class="validate[required]" style="width: 100%;"><option value=''>请选择……</option>
                                    
                                    </select>
									</div>
									<div class="col-md-2"><label for="actionname">操作:</label></div>
                                    <div class="col-md-4">
                                    	<select id="action_" name="action_" class="validate[required]" style="width: 100%;"><option value=''>请选择……</option>
                                    
                                    </select>
									</div>
                                </div> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><label for="inTime">时间:</label></div>
                                    <div class="col-md-9"><input type="text" name="inTime" readonly="readonly" class="dateISO" class="validate[required]"/></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><label for="detail">详细信息:</label></div>
                                    <div class="col-md-9">
										<textarea name="detail" class="validate[required]"></textarea>
									</div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><label for="basis">依据:</label></div>
                                    <div class="col-md-9"><input type="text" name="basis" class="validate[required,max[100]]"/></div>
                                </div>                                                           
                            </div>                
                        </div>
                        
                    </div>   
                    <div class="modal-footer">
                    	<input id="search_" type="hidden" value="0" />
                        <button class="btn btn-primary"> 提交 </button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    </form>
                </div>
            </div>
        </div>
    	<!-- 添加end from -->
    	<!-- 查询 modal form -->
    	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>查询</h4>
                    </div>
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                             <div class="row-form clearfix">
                                    <div class="col-md-3"><label for="user_name">选择人员:</label></div>
                                    <div class="col-md-9"><input type="hidden" id="user_id_search" name="username" /><input type="text" id="user_name" readonly="readonly" name="user_name" /></div>
                                </div>
                                                                                          
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                            <div class="row-form clearfix">
                                    <div class="col-md-3"><label for="type_">选择三员:</label></div>
                                    <div class="col-md-9"><select id="type_" name="type_" style="width:100%">
                                    	<option value="0">全部</option>
	                                	<option value="1">系统管理员</option>
	                                	<option value="2">安全管理员</option>
	                                	<option value="3">安全审计员</option>
	                                	</select></div>
                                </div> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                            <div class="row-form clearfix">
                                    <div class="col-md-3"><label for="cmdb">选择cmdb:</label></div>
                                    <div class="col-md-9"><select id="cmdb_search" name="cmdb" style="width: 100%;"><option value='0' selected="selected">全部</option></select></div>
                                </div> 
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><label for="startTime">开始时间:</label></div>
                                    <div class="col-md-9"><input type="text" name="startTime" class="dateISO"/></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><label for="endTime">结束时间:</label></div>
                                    <div class="col-md-9"><input type="text" name="endTime" class="dateISO"/></div>
                                </div>                                                           
                            </div>                
                        </div>
                        
                        
                    </div>   
                    <div class="modal-footer">
                    	<input id="search_" type="hidden" value="0" />
                        <button class="btn btn-primary" id="btn_find_threemember"> 查询 </button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                </div>
            </div>
        </div>
    	<!-- 查询 end from -->
    </div>
    <div class='dialog' id='b_popup_group' style='display: none;' title='部门列表'>
    	<div class='block dialog_block messages '><div><ul id='grouptree'></ul></div></div>
    </div>
    
    <div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header"><h4>请确认</h4></div>
				<div class="modal-body">确认删除该记录吗？</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button><a class="btn btn-danger btn-ok">删除记录</a>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
