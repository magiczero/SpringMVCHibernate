<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
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
    <link href="${contextPath }/resources/js/plugins/select2/select2.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href='${contextPath }/resources/js/plugins/jstree/jquery.treeview.css' rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/js/plugins/dataTables/extras/css/TableTools.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/extras/js/ZeroClipboard.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/extras/js/TableTools.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>  
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
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
            	var table = $("#myTable").dataTable({
            		"bFilter" : false,
            		//关闭过滤功能替换为文件导出按钮
                    "sDom" : 'T<"clear">lfrtip' ,
                	"oTableTools": {
                        "sSwfPath": "${contextPath }/resources/js/plugins/dataTables/extras/swf/copy_csv_xls_pdf.swf",
                        "aButtons" : [
                                      {
                                         "sExtends" : "xls" ,
                                         "sButtonText" : "导出Excel文件" ,
                                         "sFileName" : "*.xls"
                                        },
                                      ]
                    },
                    "bSort": false, //排序功能
                    "aLengthMenu":[[10,25,50,-1],[10,25,50,"All"]],
                	"oLanguage": {"sUrl": "${contextPath}/resources/json/Chinese.json"},
                	"bProcessing": false, // 是否显示取数据时的那个等待提示
    			    "bServerSide": true,//这个用来指明是通过服务端来取数据
    	            "sAjaxSource": "${contextPath}/incident/table-page-ajax-list",//这个是请求的地址
    	            "fnServerData": retrieveData, // 获取数据的处理函数
    	            "fnServerParams": function (aoData) {  //查询条件
                        aoData.push(
                        		{ "name": "unit", "value": $("#unit").val() },
                        		{ "name": "applyUser", "value": $("input[name='applyUser']").val() },
                        		{ "name": "engineer", "value": $("#engineer").val() },
                        		{ "name": "satisfaction", "value": $("#satisfaction").val() },
                        		{ "name": "abs", "value": $("#abstract").val() },
                            { "name": "starttime", "value": $("#startTime").val() },
                            { "name": "endtime", "value": $("#endTime").val() }
                            );
                    },
    	            "aoColumns" : [
    	                           { "mData" : 'abs' }, 
    	                           { "mData" : 'priorityName' }, 
    	                           { "mData" : 'applyUserName' }, 
    	                           { "mData" : 'currentDelegateUserName' },
    	                                 { "mData" : 'satisfactionName' },
    	                                 { "mData" : 'applyTime' },
    	                                 { "mData" : 'recoverTime' }
    	                             ]	
                });
                $(".header").load("${contextPath}/header?t="+pm_random());
                $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
                
                $("button[name='btnSearch']").bind("click", function () { //按钮 触发table重新请求服务器
                    table.fnDraw();
                    $("#myModal").modal('hide');
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
    		    		url:ctx + '/group/all-json?t=' + pm_random(),
                		collapsed: true,
                		unique: true
                	});
    	    		$("#b_popup_group").dialog('open');
                });
               
                $(".dateISO").datepicker();
                
                $("#unit").select2();
                $("#satisfaction").select2();
                $("#engineer").select2();
            });
            
            function inputUserinfo(userid,username,tel,room) {
            	$("input[name='applyUser']").attr("value", userid);
            	$("input[name='user_name']").attr("value",username);
            }
         // 3个参数的名字可以随便命名,但必须是3个参数,少一个都不行
    		function retrieveData( sSource111,aoData111, fnCallback111) {
    			$.ajax({
    				url : sSource111,//这个就是请求地址对应sAjaxSource
    				data : {"aoData":JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
    				type : 'post',
    				dataType : 'json',
    				async : false,
    				success : function(result) {
    					fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
    				},
    				error : function(msg) {
    				}
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
                    <li><a href="${contextPath }/incident/list">事件管理</a> <span class="divider">></span></li>       
                    <li class="active">历史事件查询</li>
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
                            <h1>已关闭事件</h1>
                            <ul class="buttons">
                                <li>
                                    <a href="#" class="isw-zoom tipb" data-toggle="modal" data-target="#myModal" title="查询"></a>
                                </li>                            
                            </ul>                         
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="myTable">
                                <thead>
                                    <tr>
                                        <th>摘要</th>
                                        <th width="80px">紧急度</th>
                                        <th width="100px">申请人</th>
                                        <th width="100px">受派者</th>
                                        <th width="80px">满意度</th>
                                        <th width="120px">申请时间</th>
                                        <th width="120px">关闭时间</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>  
                </div>
            </div>
            <!--workplace end-->
        </div> 
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>查询条件</h4>
                    </div>
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-2">单位</div>
                           			<div class="col-md-10"><select id="unit" name="unit" style="width:100%">
	                                	<option value="00">全部</option>
	                                	<c:forEach items="${units }" var="group">
	                                		<option value="${group.id }">${group.groupName }</option>
	                                	</c:forEach>
	                                	</select></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
	                                <div class="col-md-2">开始时间</div>
	                                <div class="col-md-4"><input type="text" id="startTime" class="dateISO"/></div>
	                                <div class="col-md-2">截至时间</div>
	                                <div class="col-md-4"><input type="text" id="endTime" class="dateISO"/></div>
	                            </div>
	                        </div>
	                    </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-2">摘要</div>
	                                <div class="col-md-4"><input type="text" id="abstract" /></div>
	                                <div class="col-md-2">申请人</div>
	                                <div class="col-md-4">
	                                	<input type="hidden" name="applyUser" value=""/>
	                                	<input type="text" id="user_name" readonly="readonly" name="user_name"/>
	                                </div>
	                            </div>
	                        </div>
	                    </div>    
	                    <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
	                                <div class="col-md-2">受派人</div>
	                                <div class="col-md-4">
	                                	<select id="engineer" name="engineer" style="width:100%">
	                                	<option value="00">全部</option>
	                                	<c:forEach items="${engineers }" var="user">
	                                		<option value="${user.username }">${user.name }</option>
	                                	</c:forEach>
	                                	</select>
	                                </div>
                                    <div class="col-md-2">满意度</div>
	                                <div class="col-md-4">
	                                	<select name="satisfaction" id="satisfaction">
										<option value="00">全部</option>
										<c:forEach items="${satisfaction }" var="code">
											<option value="${code.code }">${code.codeName }</option>
										</c:forEach>
										</select>
	                                </div>
	                            </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" name="btnSearch"> 查询 </button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class='dialog' id='b_popup_group' style='display: none;' title='部门列表'>
    	<div class='block dialog_block messages '><div><ul id='grouptree'></ul></div></div>
    </div>
</body>

</html>