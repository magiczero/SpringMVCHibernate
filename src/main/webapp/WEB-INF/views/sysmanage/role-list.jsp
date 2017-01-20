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
    
    <title>角色管理--运维管理系统</title>

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
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/tree-ie8.css" rel="stylesheet" type="text/css" />
	<link href="${contextPath }/resources/css/stylesheets2.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    
    
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
        
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jtree/jtree.js'></script>
    
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
                $(".lnk_modify").bind("click", modifyRole);
            	$(".lnk_new").bind("click",newRole);
            	$(".set_auth").bind("click", setAuth);
            	$(".set_menu").bind("click", setMenu);
            	$("#eventTable").dataTable();
            	if($("#selAuth").length > 0){
                    $("#selAuth").multiSelect({
                        selectableHeader: "<div class=''>Selectable item</div>",
                        selectedHeader: "<div class=''>Selected items</div>"
                    });
            	}
            	
            	if($("#selMenu").length > 0){$("#selMenu").multiSelect();}
            	
            	$(".tree").treed();
            });
    function newRole(){
		$("#dialogTitle").html("新建角色");
		$("#id").attr('value','0');
		$("#rolename").attr('value','');
		$("#roledesc").attr('value','');

		$("#roleForm").modal('show');	
    }
    function modifyRole(){
    	var roleid = $(this).parents('tr').find('.roleid').text();
    	
    	$.getJSON(ctx + '/role/get/' + roleid,function(data){
    		$("#dialogTitle").html("修改角色信息");
    		$("#id").attr('value',data.role.id);
    		$("#rolename").attr('value',data.role.roleName);
    		$("#roledesc").attr('value',data.role.roleDesc);
    		
    		$("#roleForm").modal('show');
    	});
    }
    function setAuth() {
    	var roleid = $(this).parents('tr').find('.roleid').text();
    	$("#selAuth").multiSelect("deselect_all");
    	$.getJSON(ctx + '/role/getauth/' + roleid +'?t=' + Math.random(),function(data){
    		for(var i=0;i<data.auth.length;i++)
    		{
    			$("#selAuth").multiSelect("select",data.auth[i].id);
    		}
    		$("#authform_id").attr('value',roleid);
    		$("#setAuthFormDialog").modal();
    	});	
    }
    function setMenu() {
    	var roleid = $(this).parents('tr').find('.roleid').text();
    	$("#selMenu").multiSelect("deselect_all");
    	$.getJSON(ctx + '/role/getmenu/' + roleid +'?t=' + Math.random(),function(data){
    		for(var i=0;i<data.menu.length;i++)
    		{
    			$("#selMenu").multiSelect("select",data.menu[i].id);
    		}
    		$("#menuform_id").attr('value',roleid);
    		$("#setMenuFormDialog").modal();
    	});	
    }
    </script>
    <style type="text/css">
.sys {
	color:red;
}
    </style>
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
                    <li class="active">角色管理</li>
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
                            <h1>角色管理</h1>  

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
								<th width="60px">序号</th>
									<th >角色名</th>
									<th width="15%">角色的权限</th>
									<th width="25%">角色的菜单</th>
									<th width="15%">角色描述</th>
									<th width="50px">ID</th>
									<th width="150px">操作</th>
								</tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list }" var="role" varStatus="st">
									<tr>
										<td>${st.index+1 }</td>
										<td><span <c:if test="${role.sys }">class="sys"</c:if>>${role.roleName}</span></td>
										<td>
											<c:forEach items="${role.roleAuths }" var="ra">
											${ra.auth.authorityName }<br/>
											</c:forEach>
										</td>
										<td>
										<ul class="tree">
										<c:forEach items="${role.modules }" var="menu">
											<c:if test="${empty menu.parent}">
											<li>${menu.name }
												<ul>
												<c:forEach items="${role.modules }" var="menuson">
												<c:if test="${menuson.parent.id == menu.id }">
												<li>${menuson.name }
													<c:if test="${menuson.child.size()>0 }">
													<ul>
													<c:forEach items="${menuson.child }" var="menu1">
													<c:if test="${menu1.parent.id == menuson.id }">
													<li>${menu1.name }
														<c:if test="${menu1.child.size()>0 }">
														<ul>
														<c:forEach items="${menu1.child }" var="menu2">
														<c:if test="${menu2.parent.id == menu1.id }">
														<li>${menu2.name }
															<c:if test="${menu2.child.size()>0 }">
															<ul>
															<c:forEach items="${menu2.child }" var="menu3">
															<c:if test="${menu3.parent.id == menu2.id }">
															<li>${menu3.name }</li>
															</c:if>
															</c:forEach>
															</ul>
															</c:if>
														</li>
														</c:if>
														</c:forEach>
														</ul>
														</c:if>
													</li>
													</c:if>
													</c:forEach>
													</ul>
													</c:if>
												</li>
												</c:if>
												</c:forEach>
												</ul>
											</li>	
											</c:if>
										</c:forEach>
										</ul>
										</td>
										<td>${role.roleDesc}</td>
										<td class="roleid">${role.id }</td>
										<td>
											<sec:authorize access="hasAnyRole('security_secrecy_admin','ROLE_ADMIN')"><a class="set_auth" href="javascript:void(0);">设置权限</a><br/></sec:authorize>
											<sec:authorize access="hasAnyRole('sys_admin','ROLE_ADMIN')"><a class="set_menu" href="javascript:void(0);">设置菜单</a><br/></sec:authorize>
											<sec:authorize access="hasAnyRole('sys_admin','ROLE_ADMIN')"><a class="lnk_modify" href="#">编辑</a><br/></sec:authorize>
					                        <c:if test="${!role.sys }"><sec:authorize access="hasAnyRole('sys_admin','ROLE_ADMIN')"><a class="confirm" href="${contextPath}/role/delete/${role.id}">删除</a></sec:authorize></c:if>
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
        <div class="modal fade" id="roleForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4 id="dialogTitle">新建角色</h4>
                    </div>
                    <form id="modelForm" action="${contextPath}/role/save" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">角色名:</div>
                                    <div class="col-md-9"><input id="rolename" name="rolename" type="text" /></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">角色描述:</div>
                                    <div class="col-md-9"><input id="roledesc" name="roledesc" type="text"></input></div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" aria-hidden="true">保存</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    <input type="hidden" id="id"  name="id" value="0" /> 
                    </form>
                </div>
            </div>
        </div>
    	<!-- 新建用户 end -->
    	<!-- 设置权限 -->
    	<div class="modal fade" id="setAuthFormDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>设置角色权限</h4>
                    </div>
                    <form id="setAuthForm" action="${contextPath}/role/role-update-auth" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-12">
                                     <select multiple class="multiselect" id="selAuth" name="auths">   
                                    <c:forEach items="${authList }" var="auth">                             
                                        <option value="${auth.id}">${auth.authorityName}</option>
                                    </c:forEach>
                                    </select>
                                    </div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" id="btn_set_role">保存</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    <input type="hidden" id="authform_id" name="role_id" value="0" /> 
                    </form>
                </div>
            </div>
        </div>
        <!-- 设置菜单 -->
    	<div class="modal fade" id="setMenuFormDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>设置角色菜单</h4>
                    </div>
                    <form id="setAuthForm" action="${contextPath}/role/role-set-menu" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-12">
                                     <select multiple class="multiselect" id="selMenu" name="menus">   
                                    <c:forEach items="${menuList }" var="menu">   
	                                    <option value="${menu.id}">${menu.name}</option>
	                                    <c:forEach items="${menu.child }" var="child">
	                                    <option value="${child.id}">${menu.name}--${child.name}</option>
	                                    <c:forEach items="${child.child }" var="child1">
	                                    <option value="${child1.id}">${menu.name}--${child.name}--${child1.name}</option>
	                                    <c:forEach items="${child1.child }" var="child2">
	                                    <option value="${child2.id}">${menu.name}--${child.name}--${child1.name}--${child2.name}</option>
	                                    <c:forEach items="${child2.child }" var="child3">
	                                    <option value="${child3.id}">${menu.name}--${child.name}--${child1.name}--${child2.name}--${child3.name}</option>
	                                    <c:forEach items="${child3.child }" var="child4">
	                                    <option value="${child4.id}">${menu.name}--${child.name}--${child1.name}--${child2.name}--${child3.name}--${child3.name}</option>
	                                    </c:forEach>
	                                    </c:forEach>
	                                    </c:forEach>
	                                    </c:forEach>
	                                    </c:forEach>
                                    </c:forEach>
                                    </select>
                                    </div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" id="btn_set_role">保存</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    <input type="hidden" id="menuform_id" name="role_id" value="0" /> 
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
