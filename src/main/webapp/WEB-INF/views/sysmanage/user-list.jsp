<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>用户管理--运维管理系统</title>

    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/fullcalendar.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/select2.css" rel="stylesheet" type="text/css" />
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
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
	<link href="${contextPath }/resources/css/stylesheets2.css" rel="stylesheet" type="text/css" />
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
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/sparklines/jquery.sparkline.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fullcalendar/fullcalendar.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    
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
            
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
    
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
            $(document).ready(function () {
                
            	$(".header").load("${contextPath}/header?t="+pm_random());
                $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
                $(".confirm").bind("click",function(){
                	if(!confirm("确定要执行该操作?"))
                		return false;
                });
                $(".lnk_modify").bind("click", modifyUser);
            	$(".lnk_new").bind("click",newUser);
            	$(".lnk_setrole").bind("click",setRole);
            	$("#btn_set_role").bind("click",getRole);
            	 if ($("#selRole").length > 0) {
                     $("#selRole").multiSelect();
                 }
            	 $("#eventTable").dataTable({"oLanguage": {
             			"sUrl": "${contextPath}/resources/json/Chinese.json"
         			}});
            	 
            	 $("#userForm").validationEngine({promptPosition : "topRight", scroll: true});
            });
            
            
    function newUser(){
		$("#dialogTitle").html("新建用户");
		$("#userform_id").attr('value','0');
		$("#name").attr('value','');
		$("#username").attr('value','');
		$("#username").attr('disabled',false);
		$("#password").attr('value','');
		$("#userFormDialog").modal('show');	
    }
    function modifyUser(){
    	var userid = $(this).parents('tr').find('.userid').text();
    	$.getJSON(ctx + '/user/get/' + userid,function(data){
    		$("#dialogTitle").html("修改用户信息");
    		$("#name").attr('value',data.user.name);
    		$("#userform_id").attr('value',data.user.id);
    		$("#username").attr('value',data.user.username);
    		$("#username").attr('disabled',true);
    		$("#userFormDialog").modal('show');
    	});
    }
    function setRole(){
    	var userid = $(this).parents('tr').find('.userid').text();
    	$("#selRole").multiSelect("deselect_all");
    	$.getJSON(ctx + '/user/getrole/' + userid +'?t=' + Math.random(),function(data){
    		for(var i=0;i<data.role.length;i++)
    		{
    			$("#selRole").multiSelect("select",data.role[i].id);
    		}
    		$("#roleform_id").attr('value',userid);
        	$("#setRoleFormDialog").modal('show');
    	});	
    }
    function getRole(){
    	//var values = $("#selRole").find('option:selected').map(function(){ return $(this).val() }).get();
    	$("#rolefrom_userrole").attr('value',$("#selRole").val());
    	return true;
    }
    function enableUser(id) {
    	if(confirm('确定启用？')) {
        	$.ajax({
				type : "put",
				dataType : "json",
				url : "${contextPath }/user/enable/"+id,
				success : function(data) {
					//console.log(data.flag);
					if (data.flag) {
		            	//notify_e('Success','启用成功');
		            	window.location.reload();
					} else {
						notify_e('Error','启用失败，请联系系统管理员');
					}
				}
			});
        	return true;
    	} else {
    		return false;
    	}
    }
    function notify_e(title,text){
        $.pnotify({title: title, text: text, opacity: .8, type: 'error'});            
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
                    <li><a href="${contextPath }/Asset/list">系统管理</a> <span class="divider">></span></li>       
                    <li class="active">用户管理</li>
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
                            <h1>用户管理</h1>  

                            <ul class="buttons">                          
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                    	<li><a href="#" class="lnk_new" role="button" data-toggle="modal"><span class="isw-ok"></span> 创建</a></li>
                                        <li><a href="javascript:void(0);" id="delBtn"><span class="isw-list"></span> 删除</a></li>
                                        <li><a href="#"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="eventTable">
                                <thead>
								<tr>
									<th width="50px">序号</th>
									<th width="10%">用户名</th>
									<th width="10%">真实姓名</th>
									<th width="10%">所属部门</th>
									<th >用户角色</th>
									<th width="10%">创建时间</th>
									<th width="5%">是否启用</th>
									<th width="10%">最后访问时间</th>
									<th width="50px">ID</th>
									<th width="180px">操作</th>
								</tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list }" var="user" varStatus="st">
									<tr>
										<td>${st.index+1 }</td>
										<td>${user.username}</td>
										<td>${user.name}</td>
										<td>${user.group.groupName }</td>
										<td>
										<c:forEach items="${user.userRoles }" var="role">
										 ${role.role.roleDesc }<br> 										
										</c:forEach>
										</td>
										<td>${user.createWhile }</td>
										<td><c:if test="${!user.enabled }">否</c:if></td>
										<td>${user.lastWhile }</td>
										<td class="userid">${user.id }</td>
										<td>
											<sec:authorize access="hasAnyRole('security_secrecy_admin','ROLE_ADMIN')"><a class="lnk_setrole" href="#" >设置角色</a></sec:authorize>
											<sec:authorize access="hasAnyRole('sys_admin','ROLE_ADMIN')"><a class="lnk_modify" href="#">编辑</a></sec:authorize>
					                        <%--<sec:authorize access="hasAnyRole('sys_admin','ROLE_ADMIN')"><a class="confirm" href="${contextPath}/user/delete/${user.id}">删除</a></sec:authorize> --%>
					                        <sec:authorize url="/user/enable/*"><c:if test="${!user.enabled }"><a href="javascript:void(0);" onclick="enableUser(${user.id});">启用</a></c:if></sec:authorize>
					                        <%--<sec:authorize access="hasRole('security_secrecy_admin')"><c:if test="${!user.enabled }"><a href="javascript:void(0);" onclick="enableUser(${user.id});">启用</a></c:if></sec:authorize> --%>
										</td>
									</tr>
								</c:forEach>   
                                </tbody>
                            </table>
                        </div>
                    </div>  
                  
                </div>
                <div class="dr"><span></span></div>
            </div>
            <!--workplace end-->
        </div>   
        <!-- 新建用户 modal form -->
        <div class="modal fade" id="userFormDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4 id="dialogTitle">新建用户</h4>
                    </div>
                    <form id="userForm" action="${contextPath}/user/save" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">用户名:</div>
                                    <div class="col-md-9"><input id="username" class="validate[required,ajax[existUserName]]" name="username" type="text" /></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">用户密码:</div>
                                    <div class="col-md-9"><input id="password" name="password" type="password" /></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">真实姓名:</div>
                                    <div class="col-md-9"><input id="name" name="name" type="text" class="validate[required,minSize[2],maxSize[6]]"></input></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">联系电话:</div>
                                    <div class="col-md-9"><input id="tel" name="tel" type="text" class="validate[required,custom[phone]]"></input></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">排序:</div>
                                    <div class="col-md-9"><input id="sort" name="sort" type="text" class="validate[required,custom[integer]]"></input></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">所属部门:</div>
                                    <div class="col-md-9">
                                    <select name="group">
                                    <c:forEach items="${groupList }" var="group">
                                    <option value="${group.id }">&nbsp;${group.groupName }</option>
                                    <c:forEach items="${group.child }" var="child">
                                    <option value="${child.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| - ${child.groupName }</option>
                                    <c:forEach items="${child.child }" var="child1">
                                    <option value="${child1.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| - ${child1.groupName }</option>
                                    </c:forEach>
                                    </c:forEach>
                                    </c:forEach>
                                    </select>
                                    </div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="dr"><span></span></div>
                            <div class="block">
                                <p>如果密码不填写，默认“123456”</p>
                            </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" aria-hidden="true">保存</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    <input type="hidden" id="userform_id"  name="userform_id" value="0" /> 
                    </form>
                </div>
            </div>
        </div>
    	<!-- 新建用户 end from -->
    	<!-- 设置角色 modal form -->
    	<div class="modal fade" id="setRoleFormDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>设置用户角色</h4>
                    </div>
                    <form id="setRoleForm" action="${contextPath}/user/updaterole" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-12">
                                    <select multiple class="multiselect" id="selRole" name="test[]">   
                                    <c:forEach items="${rolelist }" var="role">                             
                                        <option value="${role.id}">${role.roleDesc}</option>
                                    </c:forEach>
                                    </select></div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" id="btn_set_role">保存</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    <input type="hidden" id="roleform_id"  name="roleform_id" value="0" /> 
                    <input type="hidden" id="rolefrom_userrole" name="rolefrom_userrole" value="0" />
                    </form>
                </div>
            </div>
        </div>
    	<!-- 设置角色 end from -->
    </div>
</body>

</html>
