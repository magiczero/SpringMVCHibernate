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
    
    <title>领导交办管理--运维管理系统</title>

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
        	
            
            $(".header").load("${contextPath}/header?t="+pm_random());
            $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active");  });
            $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
            $(".confirm").bind("click",function(){
               	if(!confirm("确定要执行该操作?"))
               		return false;
            });
            
            $(".dateISO").datepicker(); 
            
            $("#treeview").treeview({
            	unique: true,
        		url : ctx + '/three-member/get-system-tree?t=' + pm_random()
        	});
            
            var starttime,endtime;
            starttime = pm_getQueryString("startTime");
            endtime = pm_getQueryString("endTime");
            
            if(starttime!=null)
            	$("input[name='startTime']").attr("value",starttime);
            else
            	$("input[name='startTime']").attr("value",new Date().format("yyyy") +"-01-01");
            
            if(endtime!=null)
            	$("input[name='endTime']").attr("value",endtime);
            else
            	$("input[name='endTime']").attr("value",new Date().format("yyyy-MM-dd"));
            
            $("#lnk_start_threemember").bind("click",function(){
               	act_form_openStartDialog("发起三员管理工作","threeMember","/three-member/list/<c:choose><c:when test="${type==1}">system-manager</c:when><c:when test="${type==2}">security-manager</c:when><c:when test="${type==3}">security-comptroller</c:when><c:when test="${type==4}">bm-system-manager</c:when><c:when test="${type==5}">bm-security-manager</c:when><c:when test="${type==6}">bm-security-comptroller</c:when><c:when test="${type==7}">oa-system-manager</c:when><c:when test="${type==8}">oa-security-manager</c:when><c:when test="${type==9}">oa-security-comptroller</c:when></c:choose>");
            });
            
            $("#btn_find_threemember").bind("click", function () { //按钮 触发table重新请求服务器
            	$("#search_").val("1");
                table.fnDraw();
                $("#myModal").modal('hide');
            });
            
            pm_workflow_inittracedialog();
            act_commont_initdialog();
            act_history_initdialog();
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
                    <div class="col-md-3">
                	<div class="head clearfix">
                        <div class="isw-donw_circle"></div>
                        <h1>配置项类别</h1>
                           	<ul class="buttons">
                        		<li><a href="${contextPath }/cms/ci/list" class="isw-list tipl" title="查看全部 "></a>   </li>
                            </ul>
                    </div>
                		<div class="block-fluid">
                		<ul id="treeview" style="margin-left:10px;"></ul>
                		</div>
                	</div>
                    <div class="col-md-9"> 
                    	<ul class="nav nav-tabs">
                    		<li class="active"><a href="#tab1" data-toggle="tab">系统管理员</a></li>
                    		<li><a href="#tab2" data-toggle="tab">安全管理员</a></li>
                    		<li><a href="#tab3" data-toggle="tab">安全审计员</a></li>
                    	</ul>
                    	<div class="tab-content">
                    		<div class="tab-pane fade in active" id="tab1">
								<div class="head clearfix">
                            <div class="isw-grid"></div>
							<h1>系统管理员</h1>
                            <ul class="buttons">                          
                            	<li>
                                    <a href="#" role="button" data-toggle="modal" data-target="#saveModal" class="isw-plus tipb" title="创建系统管理员工作记录"></a>
                                </li>
                                
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="ciTable">
                                <thead>
                                	<tr>
                                		<th>序号</th>
										<th>项目</th>
										<th>变动（操作）</th>
										<th>详细情况</th>
										<th>依据</th>
										<th>填写人</th>
										<th>时间</th>
									</tr>
                                </thead>
                                <tbody>
  
                                </tbody>
                            </table>
                        </div>
							</div>
                    		<div class="tab-pane fade" id="tab2">系统管理员2</div>
                    		<div class="tab-pane fade" id="tab3">系统管理员3</div>
                    	</div>                   
                        
                    </div>  
                </div>
                <div class="dr"><span></span></div>
   
            </div>
            <!--workplace end-->
        </div>  
        <!-- 填写记录 form -->
    	<div class="modal fade" id="saveModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                                    <div class="col-md-3">项目:</div>
                                    <div class="col-md-9"><select id="item" name="item" style="width:100%">
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
                                    <div class="col-md-3">变动（操作）:</div>
                                    <div class="col-md-9"><select id="action_" name="action_" style="width:100%">
                                    	<option value="0">全部</option>
	                                	
	                                	</select></div>
                                </div> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                            <div class="row-form clearfix">
                                    <div class="col-md-3">时间:</div>
                                    <div class="col-md-9"><input type="text" name="startTime" class="dateISO"/></div>
                                </div> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">详细情况:</div>
                                    <div class="col-md-9"><textarea name="detail" rows="3" cols="20"></textarea></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">依据:</div>
                                    <div class="col-md-9"><input type="text" name="basis" /></div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                    	<input id="search_" type="hidden" value="0" />
                        <button class="btn btn-primary" id="btn_find_threemember"> 确定</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                </div>
            </div>
        </div>
    	<!-- 查询 end from -->
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
                                    <div class="col-md-3">选择三员:</div>
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
                                    <div class="col-md-3">选择三员类别:</div>
                                    <div class="col-md-9"><select id="type_" name="type_" style="width:100%">
                                    	<option value="0">全部</option>
	                                	<option value="1">系统管理员</option>
	                                	<option value="2">安全管理员</option>
	                                	<option value="3">安全审计员</option>
	                                	<option value="4">保密平台系统管理员</option>
	                                	<option value="5">保密平台安全管理员</option>
	                                	<option value="6">保密平台安全审计员</option>
	                                	<option value="7">oa系统管理员</option>
	                                	<option value="8">oa安全管理员</option>
	                                	<option value="9">oa安全审计员</option>
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
                    	<input id="search_" type="hidden" value="0" />
                        <button class="btn btn-primary" id="btn_find_threemember"> 查询 </button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                </div>
            </div>
        </div>
    	<!-- 查询 end from -->
    </div>
</body>

</html>
