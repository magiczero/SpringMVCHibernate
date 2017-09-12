<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>运维记录管理--运维管理系统</title>

    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/js/plugins/select2/select2.css" rel="stylesheet" type="text/css" />
    
    <link href="${contextPath }/resources/js/plugins/datetimepicker/datetimepicker.min.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/datetimepicker/datetimepicker.min.js' charset="UTF-8"></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/datetimepicker/datetimepicker.zh-CN.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
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
                "sAjaxSource": "${contextPath}/maintain-record/maintainrecord-list-with-table",//这个是请求的地址
                "fnServerData": retrieveData, // 获取数据的处理函数
                "fnServerParams": function (aoData) {  //查询条件
                    aoData.push(
                    		{ "name": "equipname", "value": $("#equipName").val() },
                    		{ "name": "type_", "value": $("#type").val() },
                        { "name": "starttime", "value": $("input[name='startTime']").val() },
                        { "name": "endtime", "value": $("input[name='endTime']").val() }
                        );
                },
                "aoColumnDefs": [
                	{
                        "aTargets": [ 2 ],
                        "mData": "download_link",
                        "mRender": function ( data, type, full ) {
                        	if(data==1) {return '变更';}
                        	else if(data==2) {return '运维';}
                        	else if(data==3) {return '维修';}
                        	else if(data==4) {return '报废';}
                        	else return '未知';
                         	 
                        }
                      },
                      {
                          "aTargets": [ 3 ],
                          "mData": "download_link",
                          "mRender": function ( data, type, full ) {
                          	return data.substr(0,16);
                           	 
                          }
                        },
                	 {
                         "aTargets": [ 8 ],
                         "mData": "download_link",
                         "mRender": function ( data, type, full ) {
                        	var str = "";
                    		for(var i=0;i<data.length; i++) {
                        		str += "<a href='"+ctx+"/attachment/download/"+data[i].id+"'>"+data[i].name+"</a>";
                        	 }
                           	 return str;
                         }
                       }
                	 <sec:authorize access="hasRole('WK_LEADER')"> ,{
                    "aTargets": [ 10 ],
                    "mData": "download_link",
                    "mRender": function ( data, type, full ) {
                      return '<a href="${contextPath }/maintain-record/delete?id='+data+'" onclick="javascript:return del();">删除</a>';
                    }
                  } </sec:authorize>],
                "aoColumns" : [
                               { "mData" : 'equipName' }, 
                               { "mData" : 'equipNum' }, 
                               { "mData" : 'type' },
                               { "mData" : 'maintainTime' },
                               { "mData" : 'addIn' },
                               { "mData" : 'circs' },
                               { "mData" : 'executorName' }, 
                               { "mData" : 'escort' }, 
                               { "mData" : 'attachs' }, 
                               { "mData" : 'remark' }
                               <sec:authorize access="hasRole('WK_LEADER')">,{ "mData" : 'id' }</sec:authorize>
                                 ]
            });
            
            $(".header").load("${contextPath}/header?t="+pm_random());
            $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
            $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
            
          	//表单验证
            $("#validation").validationEngine({promptPosition : "topRight", scroll: true});
            
            $(".dateISO").datetimepicker({autoclose: true,language: 'zh-CN',minuteStep: 5,todayBtn: true});
            
            $("#btn_find").bind("click", function () { //按钮 触发table重新请求服务器
                table.fnDraw();
                $("#myModal").modal('hide');
            });
        });
        
        function del() {
        	if(!confirm("确定要执行该操作?"))
           		return false;
        }
        
        function retrieveData(sSource111,aoData111, fnCallback111) {
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
                    <li><a href="#">运维工作管理</a> <span class="divider">></span></li>       
                    <li class="active">维护工作记录</li>
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
                            <h1>记录列表</h1>  
							<ul class="buttons">
                                <li>
                                    <a href="${contextPath }/maintain-record/add" class="isw-plus tipb" title="创建新记录"></a>
                                </li>                            
                                <li><a href="#myModal" data-toggle="modal" class="isw-zoom tipb" title="创建新记录"></a></li>
                            </ul>
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="eventTable">
                                <thead>
                                	<tr>
										<th width="100px">设备名称</th>
										<th width="100px">设备编号</th>
										<th width="100px">维护类型</th>
										<th width="130px">维护时间</th>
										<th width="130px">外接设备情况</th>
										<th>执行情况</th>
										<th width="80px">执行人</th>
										<th width="100px">陪同人</th>
										<th width="100px">文件</th>
										<th width="70px">备注</th>
										<sec:authorize access="hasRole('WK_LEADER')"><th>删除</th></sec:authorize>
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
        <!-- modal form -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>查找</h4>
                    </div>
                    <!--<form id="validation" action="${contextPath}/maintain-record/search" method="post">-->
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><label for="equipName">设备名称：</label></div>
                                    <div class="col-md-9"><input type="text" id="equipName" name="equipName" class="validate[maxSize[50]]" /></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><label for="startTime">维护开始时间：</label></div>
                                    <div class="col-md-9"><input type="text" id="startTime" name="startTime" class="dateISO" /></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><label for="endTime">维护结束时间：</label></div>
                                    <div class="col-md-9"><input type="text" id="endTime" name="endTime" class="dateISO" /></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><label for="type">维护类型：</label></div>
                                    <div class="col-md-9">
										<select id="type" name="type">
											<option value="0">全部</option>
											<option value="1">变更</option>
											<option value="2">运维</option>
											<option value="3">维修</option>
											<option value="4">报废</option>
										</select>
									</div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" aria-hidden="true" id="btn-find">提交</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    <!--</form>-->
                </div>
            </div>
        </div>
    	<!-- modal form end -->
    </div>
</body>

</html>