<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>请假管理--运维管理系统</title>

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
    <link href="${contextPath }/resources/js/plugins/dataTables/extras/css/TableTools.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js"></script>
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/extras/js/ZeroClipboard.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/extras/js/TableTools.min.js'></script>
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
            var table = $("#eventTable").dataTable({
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
            	"oLanguage": {"sUrl": "${contextPath}/resources/json/Chinese.json"},
            	"bProcessing": false, // 是否显示取数据时的那个等待提示
			    "bServerSide": true,//这个用来指明是通过服务端来取数据
	            "sAjaxSource": "${contextPath}/overtime/ajax-list",//这个是请求的地址
	            "fnServerData": retrieveData, // 获取数据的处理函数
	            "fnServerParams": function (aoData) {  //查询条件
                    aoData.push(
                    		{ "name": "username", "value": $("#engineer").val() },
                    		{ "name": "searchBtn", "value": $("input[name='search']").val() },
                        { "name": "starttime", "value": $("input[name='startTime']").val() },
                        { "name": "endtime", "value": $("input[name='endTime']").val() }
                        );
                },
	            "aoColumns" : [
	                           { "mData" : 'applicant' }, 
	                           { "mData" : 'overtimeStart' }, 
	                           { "mData" : 'overtimeEnd' },
	                                 { "mData" : 'hour' },
	                                 { "mData" : 'company' },
	                                 { "mData" : 'reason' },
	                                 { "mData" : 'status_' },
	                                   //mData 表示发请求时候本列的列名，返回的数据中相同下标名字的数据会填充到这一列
	                                  { "mData" : 'op' }
	                             ]
 			});
            $(".header").load("${contextPath}/header?t="+pm_random());
            $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active");  });
            $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
            
            $(".dateISO").datetimepicker({autoclose: true,language: 'zh-CN',minuteStep: 5,todayBtn: true}); 
            
            $("input[name='startTime']").attr("value",new Date().format("yyyy") +"-01-01 00:00");
            
           	$("input[name='endTime']").attr("value",new Date().format("yyyy-MM-dd HH:mm"));
            
            $("button[name='btnSearch']").bind("click", function () { //按钮 触发table重新请求服务器
            	$("input[name='search']").val("1");
                table.fnDraw();
                $("#myModal").modal('hide');
                var countTime = $("input[name='countTime']").val();
                if(countTime !=0) {
	                
	                $("#eventTable_info").html("共加班："+countTime);
	            }
            });
            $("#lnk_start_overtime").bind("click",function(){
               	act_form_openStartDialog("发起请假申请","OVERTIME","/overtime");
            });
            pm_workflow_inittracedialog();
            act_commont_initdialog();
            act_history_initdialog();
        });
        
        function initUpdateModal(taskId) {
        	$.ajax({
				type : "post",
				dataType : "json",
				url : "${contextPath }/overtime/task/"+taskId,
				success : function(data) {
					$("input[name='start']").val(data.start);
					$("input[name='end']").val(data.end);
					$("input[name='pid']").val(data.pid);
					$("#reason").val(data.reason);
					$("#updateModal").modal();
				}
			});
        }

        function retrieveData( sSource111,aoData111, fnCallback111) {
			$.ajax({
				url : sSource111,//这个就是请求地址对应sAjaxSource
				data : {"aoData":JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				type : 'post',
				dataType : 'json',
				async : false,
				success : function(result) {
					fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
					if(result.countTime) {
						$("input[name='countTime']").val(result.countTime);
					}
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
                </div> <input type="hidden" name="countTime" value="0">
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
                                        <c:if test="${isLeader }"><li><a href="#" data-toggle="modal" data-target="#myModal"><span class="isw-zoom"></span> 加班查询统计</a></li></c:if>
                                        <li><a href="#" onclick="pm_refresh()"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="eventTable">
                                <thead>
                                	<tr>
                                		<th width="90px">加班者</th>
                                		<th width="140px">开始时间</th>
                                		<th width="140px">结束时间</th>
                                		<th width="120px">加班时长</th>
                                		<th width="100px">服务对象</th>
										<th >加班原因</th>
										<th width="130px">状态</th>
										<th width="150px">操作</th>
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
        <!-- 动态表单 -->
        <div class="modal fade" id="dynamicForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
        <!-- 修改 modal form -->
    	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>修改</h4>
                    </div>
                    <form method="post" action="${contextPath }/overtime/update">
                    <input type="hidden" name="pid" id="pid" />
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">开始时间:</div>
                                    <div class="col-md-9"><input type="text" name="start" class="dateISO"/></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">结束时间:</div>
                                    <div class="col-md-9"><input type="text" name="end" class="dateISO"/></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">请假原因:</div>
                                    <div class="col-md-9"><textarea id="reason" name="reason" ></textarea></div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                    	<input type="hidden" name="search" value="0">
                        <button class="btn btn-primary" > 修改 </button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div></form>
                </div>
            </div>
        </div>
    	<!-- 查询 end from -->
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
                                    <div class="col-md-3">请假者:</div>
                                    <div class="col-md-9"><select id="engineer" name="username" style="width:100%">
	                                	<option value="00">全部</option>
	                                	<c:forEach items="${engineers }" var="user">
	                                		<option value="${user.username }">${user.name }</option>
	                                	</c:forEach>
	                                	</select></div>
                                </div>                                                           
                            </div>                
                        </div>
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
                    	<input type="hidden" name="search" value="0">
                        <button class="btn btn-primary" name="btnSearch"> 查询 </button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                </div>
            </div>
        </div>
    	<!-- 查询 end from -->
    </div>
</body>

</html>
