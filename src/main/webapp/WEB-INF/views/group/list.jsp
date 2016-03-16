<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
    <!-- <link href="${contextPath }/resources/css/elfinder.css" rel="stylesheet" type="text/css" /> -->
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
    var grpid = 0;
            $(document).ready(function () {
                
            	$(".header").load("${contextPath}/header?t="+pm_random());
                $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
                
                $("#group").validationEngine({promptPosition : "topRight", scroll: true});
                
                $("#userForm").validationEngine({promptPosition : "topRight", scroll: true});
                $("#userEditForm").validationEngine({promptPosition : "topRight", scroll: true});
            	 
                department_list();
            });
            
    function department_list() {
    	$("#treeview li").remove();
    	$.getJSON(ctx + '/group/all-json?t=' + pm_random(), function(data1){
    		obj= $.parseJSON(data1.json);
    		
    		$.each(obj, function (index, element) {
    			var liStr = "<li><a href=\"javascript:void(0);\" onclick=\"viewUsers('"+element.groupId+"');\">"+element.groupName+"</a>";
    			if(element.users || element.child) {
    				liStr += "<ul>";
                	if(element.users) {
                		$.each(element.users, function (j, user) {
                			liStr += "<li><a href=\"javascript:void(0);\" >"+user.userName+"</a></li>";
                		});
                	} 
                	if(element.child) {
                		$.each(element.child, function (j, child) {
                			liStr += "<li><a href=\"javascript:void(0);\" onclick=\"viewUsers('"+child.groupId+"');\">"+child.groupName+"</a>";
                			if(child.users || child.child) {
                				liStr += "<ul>";
                				if(child.users) {
                    				$.each(child.users, function (j, user1) {
    	                    			liStr += "<li><a href=\"javascript:void(0);\" >"+user1.userName+"</a></li>";
    	                    		});
                				}
                				if(child.child) {
                    				$.each(child.child, function (j, child1) {
		                    			liStr += "<li><a href=\"javascript:void(0);\" onclick=\"viewUsers('"+child1.groupId+"');\">"+child1.groupName+"</a>";
		                    			if(child1.users) {
		                    				liStr += "<ul>";
		                    				$.each(child1.users, function (j, user2) {
		    	                    			liStr += "<li><a href=\"javascript:void(0);\" >"+user2.userName+"</a></li>";
		    	                    		});
		                    				liStr += "</ul>";
		                    			}
		                    			liStr += "</li>";
		                    		});
                				}
                				liStr += "</ul>";
                			}
                			liStr += "</li>";
                		});
                	}
                	liStr += "<ul>";
    			}
            	liStr += "</ul></li>";
            	$("#treeview").append(liStr);
    		});
    		$("#treeview").treed();
    		
    	});
    }
            
    function notify_e(title,text){
        $.pnotify({title: title, text: text, opacity: .8, type: 'error'});            
    }
    
    function viewUsers(groupid) {
    	$.getJSON(ctx + '/user/get-users/'+groupid+'?t=' + pm_random(), function(data){
    		if(data.users == "") {
    			$("#userTable tbody tr").remove();
    		} else {
	    		user= $.parseJSON(data.users);
	    		var trs = "";
	    		$.each(user, function (index, element) {
	    			index = index+1;
	    			trs += "<tr><td>"+index+"</td><td>"+element.userName+"</td><td>"+element.name+"</td><td>"+element.groupName+"</td><td>"+element.priorityCH+"</td><td>"+element.tel+"</td><td><a href=\"javascript:void(0);\" onclick=\"initEditUserForm("+element.userid+");\">编辑</a>&nbsp;<a href=\"javascript:void(0);\" onclick=\"delUser("+element.userid+","+groupid+");\">删除</a></td></tr>";
	    		});
	    		$("#userTable tbody tr").remove();
	    		$("#userTable tbody").append(trs);
	    	}
    		
    		//$("#group").val('"+groupid+"');
    		grpid = groupid;
    		$("#groupid").attr("value",groupid);
    	});
    }
    
    function delUser(userid, groupid) {
    	if(confirm('确定删除？')) {
        	$.ajax({
				type : "put",
				dataType : "json",
				url : "${contextPath }/user/del/"+userid,
				success : function(data) {
					//console.log(data.flag);
					if (data.flag) {
		            	//notify_e('Success','启用成功');
		            	//window.location.reload();
		            	viewUsers(groupid);
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
    
    function initSaveGroupForm() {
    	//清空
		$("#id").attr("value",'');
		$("#groupName").attr("value",'');
		$("#order").attr("value",'');
		$("#explain").attr("value",'');
		$("#groupModal").modal('show');
    }
    
    function initEditUserForm(userid) {
    	$.getJSON(ctx + '/user/get-user/'+userid+'?t=' + pm_random(), function(data){
    		user= $.parseJSON(data.user);
    		//清空
    		$("#euserid").attr("value",user.userid);
    		$("#egroupid").attr("value",user.groupId);
    		$("#eusername").attr("value",user.userName);
    		$("#ename").attr("value",user.name);
    		$("#epriority").attr("value",user.priority);
    		$("#etel").attr("value",user.tel);
    		$("#esort").attr("value",user.sort);
    		$("#userEditFormDialog").modal('show');
    	});
    }
    
    function editUser() {
    	if($('#euserid').val() == '') {
    		notify_e('Error','未查询到用户id，无法编辑，请联系管理员');
    		return false;
    	}
    	if($('#egroupid').val() == '') {
    		notify_e('Error','未找到用户部门id，无法编辑，请联系管理员');
    		return false;
    	}
    	if($('form[name=userEditForm]').validationEngine('validate')) {
    		$.ajax({
	            cache: true,
	            type: "POST",
	            url:"${contextPath }/user/edit-user",
	            data:$('#userEditForm').serialize(),
	            async: false,
	            error: function(request) {
	                alert("无法保存，请联系管理员");
	            },
	            success: function(data) {
	            	if (data.flag) {
	            		viewUsers(grpid);
	            		$("#userEditFormDialog").modal('hide');
					} else {
						notify_e('Error','无法编辑用户，请联系管理员');
					}
	            }
	        });
    	}
    }
    
    function editGroup() {
    	if(grpid ==0) {
    		notify_e('Error','请先选中部门，在进行编辑');
    		return false;
    	}
    	
    	$.getJSON(ctx + '/group/group/'+grpid+'?t=' + pm_random(), function(data){
    		group= $.parseJSON(data.group);
    		//清空
    		$("#id").attr("value",group.groupid);
    		$("#groupName").attr("value",group.groupName);
    		$("#parentGroup.id").attr("value",group.parentId);
    		$("#order").attr("value",group.sort);
    		$("#explain").attr("value",group.explain);
    		$("#groupModal").modal('show');
    	});
    }
    
    function initUserSaveForm() {
    	$("#username").attr("value",'');
    	$("#name").attr("value",'');
    	$("#tel").attr("value",'');
    	$("#sort").attr("value",'');
    	$("#userFormDialog").modal('show');
    }
    
    function saveUser() {
    	if($('#groupid').val() == '') {
    		notify_e('Error','请点击部门');
    		return false;
    	}
    	if($('form[name=userSaveForm]').validationEngine('validate')) {
	    	$.ajax({
	            cache: true,
	            type: "POST",
	            url:"${contextPath }/user/save-user",
	            data:$('#userForm').serialize(),
	            async: false,
	            error: function(request) {
	                alert("无法保存，请联系管理员");
	            },
	            success: function(data) {
	            	if (data.flag) {
	            		viewUsers(grpid);
	            		$("#userFormDialog").modal('hide');
					} else {
						notify_e('Error','请选择部门');
					}
	            }
	        });
    	}
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
                    <li class="active">部门管理</li>
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
                           <h1>部门列表</h1>
                        <ul class="buttons">
                        <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                    	<li><a href="javascript:void(0);" class="lnk_new" onclick="initSaveGroupForm();"><span class="isw-ok"></span> 创建</a></li>
                                    	<li><a href="javascript:void(0);" id="delBtn"><span class="isw-cancel"></span> 删除</a></li>
                                        <li><a href="javascript:void(0);" onclick="editGroup();"><span class="isw-list"></span> 编辑</a></li>
                                        <li><a href="javascript:void(0);" onclick="department_list();"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                        </ul>
                        </div>
                        <div class="block-fluid">
                        <ul id="treeview" class="tree">
                            </ul>
                        </div>
                    </div> 
                    <div class="col-md-9">                    
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>用户列表</h1>  

                            <ul class="buttons">   
	                            <li><a title="新建用户" class="isw-plus" onclick="initUserSaveForm();"></a>
	                        </li>                       
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="userTable">
                                <thead>
								<tr>
									<th width="50px">序号</th>
									<th width="15%">用户名</th>
									<th>真实姓名</th>
									<th>所属部门</th>
									<th>优先级</th>
									<th >电话</th>
									<th width="15%">操作</th>
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
        </div>
        <div class="modal fade" id="groupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                <c:url var="addAction" value="/group/save" ></c:url>
	                <form:form action="${addAction}" commandName="group">
	                <form:hidden path="id"/>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4>新建部门</h4>
                    </div>
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="groupName">部门名称:</form:label></div>
                                    <div class="col-md-9"><form:input path="groupName" class="validate[required,minSize[2]]"/></div>
                                </div>
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="parentGroup.id">上级部门：</form:label></div>
                                    <div class="col-md-9">
                                    	<form:select path="parentGroup.id" class="validate[required]">
                                    	<form:option value="">#</form:option>
                                    	<c:forEach items="${groupList }" var="group">
                                    	
	                                    <form:option value="${group.id }">&nbsp;${group.groupName }</form:option>
	                                    <c:forEach items="${group.child }" var="child">
	                                    <form:option value="${child.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| - ${child.groupName }</form:option>
	                                    <c:forEach items="${child.child }" var="child1">
	                                    <form:option value="${child1.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| - ${child1.groupName }</form:option>
	                                    </c:forEach>
	                                    </c:forEach>
	                                    </c:forEach>
	                                    	</form:select>
	                                </div>
                                </div>
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="order">排序：</form:label></div>
                                    <div class="col-md-9"><form:input path="order" class="validate[required]"/></div>
                                </div>
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="explain">说明：</form:label></div>
                                    <div class="col-md-9"><form:textarea path="explain" class="validate[maxSize[100]]"/></div>
                                </div>
                            </div>
                            <div class="dr"><span></span></div>
                            <div class="block">
                                <p>如果选择#，则是顶级部门</p>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                    <input class="btn btn-warning" type="submit" value="确定" />
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">Close</button>
                    </div>
                    </form:form>
                </div>
            </div>
        </div>
        <!-- 新建用户 modal form -->
        <div class="modal fade" id="userFormDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4 id="dialogTitle">新建用户</h4>
                    </div>
                    <form id="userForm" name="userSaveForm">
                    <input id="groupid" type="hidden" name="group" value="" class="validate[required]"/>
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">用户名:</div>
                                    <div class="col-md-9"><input id="username" class="validate[required,minSize[2],maxSize[20]]" name="username" type="text" /></div>
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
                                    <div class="col-md-3">优先级:</div>
                                    <div class="col-md-9">
										<select name="priority">
										<option value="0">高</option>
										<option value="10">中</option>
										<option value="20">低</option>
										</select>
									</div>
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
                        <div class="dr"><span></span></div>
                    </div>  
                    </form> 
                    <div class="modal-footer">
                        <button class="btn btn-primary" onclick="saveUser();">保存</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    
                </div>
            </div>
        </div>
    	<!-- 新建用户 end from -->
    	<!-- 编辑用户 modal form -->
        <div class="modal fade" id="userEditFormDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4 id="dialogTitle">编辑用户信息</h4>
                    </div>
                    <form id="userEditForm" name="userEditForm">
                    <input id="euserid" type="hidden" name="euserid" value="" class="validate[required]"/>
                    <input id="egroupid" type="hidden" name="egroup" value="" class="validate[required]"/>
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">用户名:</div>
                                    <div class="col-md-9"><input id="eusername" class="validate[required,minSize[2],maxSize[20]]" name="eusername" type="text" /></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">真实姓名:</div>
                                    <div class="col-md-9"><input id="ename" name="ename" type="text" class="validate[required,minSize[2],maxSize[6]]"></input></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">优先级:</div>
                                    <div class="col-md-9">
										<select name="epriority">
										<option value="0">高</option>
										<option value="10">中</option>
										<option value="20">低</option>
										</select>
									</div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">联系电话:</div>
                                    <div class="col-md-9"><input id="etel" name="etel" type="text" class="validate[required,custom[phone]]"></input></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">排序:</div>
                                    <div class="col-md-9"><input id="esort" name="esort" type="text" class="validate[required,custom[integer]]"></input></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="dr"><span></span></div>
                    </div>  
                    </form> 
                    <div class="modal-footer">
                        <button class="btn btn-primary" onclick="editUser();">保存</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    
                </div>
            </div>
        </div>
    	<!-- 编辑用户 end from -->
</body>

</html>
