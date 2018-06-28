<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
    <c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>台账管理--运维管理系统</title>

    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/fullcalendar.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/uniform.default.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/cleditor.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/fancybox/jquery.fancybox.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/elfinder.min.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/elfinder.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/multi-select.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ibutton.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stepy.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/tagsinput.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/dataTables.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
	<link href="${contextPath }/resources/css/stylesheets2.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap-submenu.min.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    <link rel='stylesheet' type='text/css' href='${contextPath }/resources/css/fullcalendar.print.css' media='print' />
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/submenu/bootstrap-submenu.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/sparklines/jquery.sparkline.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fullcalendar/fullcalendar.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/animatedprogressbar/animated_progressbar.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cleditor/jquery.cleditor.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
        
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/jquery.form.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/cookies.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/settings.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    var ctx = "${contextPath}";
    var table;
    var isAccountMaster = ${isAccountMaster};
    var isAccountSub = ${isAccountSub};
            $(document).ready(function () {

            	$(".header").load("${contextPath }/header?t="+pm_random());
            	$(".menu").load("${contextPath }/menu?t="+pm_random(), function() {$("#node_${moduleId}").addClass("active");});
            	$(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
            	
            	$("#validation").validationEngine({promptPosition : "topRight", scroll: true });
            	$('#submitBtn').click(function(){
                	if($("#validation").validationEngine("validate")){
	                    $("#validation").ajaxSubmit(function(data){
	                         if(data) {
	                        	 //shuan新Table
	                        	 table.fnDraw();
	                        	 $('#fModal').modal('hide');
	                        	 
	                        	 return true;
	                         } else {
	                        	 alert('请选择部门或分类');
	                        	 return false;
	                         }
	                          });
	                }
              	});
                
                table = $('#eventTable').dataTable({
                	"oLanguage": {"sUrl": "${contextPath}/resources/json/Chinese.json"},
    			    "bAutoWidth": true,
    			    "sDom": '<"top"i>rt<"bottom"flp><"clear">',     //改变页面元素位置      
    			    "bPaginate": true,   
    			    "sPaginationType": "full_numbers", 
    			    "iDisplayLength": 10, 
    			    "bSort": false, 
    			    "bFilter": false, 
    			    "aaSorting": [], 
    			    "bInfo": false, 
    			    "bStateSave": false, 
    			    "iCookieDuration": 0, 
    			    "bScrollCollapse": true, 
    			    "bProcessing": true, 
    			    "bJQueryUI": false,
    		        "bServerSide": true,//这个用来指明是通过服务端来取数据
    	            "sAjaxSource": ctx + '/account-life-cycle/get-aduit-task-list?t=' + pm_random(),//这个是请求的地址
    	            "fnServerData": retrieveData, // 获取数据的处理函数
    	            "fnServerParams": function (aoData) {  //查询条件
    	                aoData.push(
    	                		{ "name": "categoryCode", "value": $("#categorycode").val() }
    	                    );
    	            },
    	            "aoColumnDefs": [ {
    	                "aTargets": [ 5 ],
    	                "mRender": function ( data, type, full ) {
    	                	var group = "";
    	                	for(var i=0;i<data.length;i++){
    	                		group += data[i].groupName + " ";
    	                		}
    	                	return group;
    	                }
    	              },{
    	                "aTargets": [ 6 ],
    	                "mRender": function ( data, type, full ) {
    	                	var codeNames = "";
    	                	for(var i=0;i<data.length;i++){
    	                		codeNames+=data[i].codeName + " ";
    	                		}
    	                	return codeNames;
    	                }
    	              },{
    	                "aTargets": [ 7 ],
    	                "mRender": function ( data, type, full ) {
    	                	if(full.endTime == null) {
        	                	return "<a href='"+data+"'>进入任务</a>";
    	                	} else {
    	                		return "<a href='history?at="+data+"'>查看历史任务信息</a>";
    	                	}
    	                }
    	              }],
    	            "aoColumns" : [
    	                           { "mData" : 'name' }, 
    	                           { "mData" : 'reason' }, 
    	                           { "mData" : 'startTime' }, 
    	                           { "mData" : 'endTime'}, 
    	                           { "mData" : 'assessor.realname'}, 
    	                           { "mData" : 'groups'}, 
    	                           { "mData" : 'categorys'},
    	                           { "mData" : 'id' }
    	                             ]
    			});
                
            });
            
            
            function retrieveData( sSource111,aoData111, fnCallback111) {
    			$.ajax({
    				url : sSource111,//这个就是请求地址对应sAjaxSource
    				data : {"aoData":JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
    				type : 'get',
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
                    <li><a href="#">台账管理</a></li>       
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">             

                <div class="row">

                    <div class="col-md-12" >
                            
                                    <div class="dr"></div>
                        	<div class="toolbar clearfix">
                        		
                        	</div>
						<div class="head clearfix">
                            <ul class="buttons">
                                <li>
                                    <a class="isw-settings" href="#"></a>
                                    <ul class="dd-list">
                                        <c:if test="${nonTask && isAccountMaster }"><li><a href="#fModal" data-toggle="modal"><span class="isw-plus"></span> 新建审核任务</a></li></c:if>
                                    </ul>
                                </li>
                            </ul>                        
                        </div>
                        <div class="block-fluid  table-sorting clearfix" id="inbox">
                            <table  class="table" id="eventTable">
                                <thead>
                                    <tr>
                                        <th width="10%">审核名称</th>
                                        <th width="15%">原因</th>
										<th width="10%">开始时间</th>
										<th width="10%">结束时间</th>
										<th width="12%">发起人</th>
										<th >部门</th>
										<th >类别</th>
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
        
         <!-- Bootrstrap modal form -->
         <c:if test="${nonTask && isAccountMaster }">
         <div class="modal fade" id="fModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>新建审核任务</h4>
                    </div>
                    <form id="validation" action="${contextPath}/account-life-cycle/start-aduit-task" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">审核名称:</div>
                                    <div class="col-md-9"><input type="text" name="auditName" class="validate[required,maxSize[50]]" value=""/></div>
                                </div>            
                                <div class="row-form clearfix">
                                    <div class="col-md-3">原因:</div>
                                    <div class="col-md-9"><textarea name="reason" class="validate[required,maxSize[50]]"></textarea></div>                    
                                </div>                                    
                                <div class="row-form clearfix">
                                    <div class="col-md-3">部门:</div>
                                    <div class="col-md-9"><c:forEach items="${topGroup.child }" var="group"><label class="checkbox checkbox-inline">
                                        <input type="checkbox" name="groups" value="${group.id }" /> ${group.groupName} </label></c:forEach></div>
                                </div>         
                                <div class="row-form clearfix">
                                    <div class="col-md-3">台账分类:</div>
                                    <div class="col-md-9"><c:forEach items="${accountTypes }" var="type"><label class="checkbox checkbox-inline">
                                        <input type="checkbox" name="codes" value="${type.category }" /> ${type.name} </label></c:forEach></div>
                                </div>                                          
                            </div>                
                            <div class="dr"><span></span></div>
                        </div>
                    </div>   
                    </form>
                    <div class="modal-footer">
                        <button class="btn btn-warning" id="submitBtn" aria-hidden="true"> 提 交 </button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">Close</button>            
                    </div>
                    
                </div>
            </div>
        </div>
        </c:if>
    </div>
</body>

</html>