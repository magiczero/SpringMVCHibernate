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
        	"bPaginate" : false,// 分页按钮  
		    "bServerSide": true,//这个用来指明是通过服务端来取数据
            "sAjaxSource": "${contextPath}/three-member/relationship",//这个是请求的地址
            "fnServerData": retrieveData, // 获取数据的处理函数
            "fnServerParams": function (aoData) {  //查询条件
                aoData.push(
                		{ "name": "userid", "value": $("#user_id_search").val() },
                		{ "name": "type_", "value": $("#type_").val() },
                		{ "name": "cmdb", "value": $("#cmdb_search").val() }
                    );
            },
            "aoColumns" : [
                           { "mData" : 'cmdbname' }, 
                           { "mData" : 'rolename' }, 
                           { "mData" : 'userrealname' }, 
                           { "mData" : 'createtime' }, 
                           { "mData" : 'op' }
                             ]
            });
            
            $(".header").load("${contextPath}/header?t="+pm_random());
            $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active");  });
            $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
            $(".confirm").bind("click",function(){
               	if(!confirm("确定要执行该操作?"))
               		return false;
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
		    
		    $("#lnk_new_relationship").bind("click", function () { //按钮 触发table重新请求服务器
                window.location="init-create-threemember";
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
					
					$("#eventTable").rowspan(0);
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
                            <h1>三员列表</h1>  
							<ul class="buttons">
                                <li>
                                    <a href="#" class="isw-zoom tipb" data-toggle="modal" data-target="#myModal" title="查询"></a>
                                </li>                            
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                    	<li><a href="#" id="lnk_new_relationship"><span class="isw-plus"></span> 新建三员</a></li>
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
                                		<th >cmdb</th>
                                		<th>三员角色</th>
										<th >三员</th>
										<th >创建时间</th>
										<th >操作</th>
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
        
    	<!-- 动态表单 end --> 
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
                                    <div class="col-md-9"><input type="hidden" id="user_id_search" name="user_id" /><input type="text" id="user_name" readonly="readonly" name="user_name" /></div>
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
</body>

</html>
