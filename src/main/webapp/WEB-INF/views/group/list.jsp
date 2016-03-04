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
                
                $("#group").validationEngine({promptPosition : "topRight", scroll: true});
            	 
                $.getJSON(ctx + '/group/all-json?t=' + pm_random(), function(data1){
		    		obj= $.parseJSON(data1.json);
		    		
		    		$.each(obj, function (index, element) {
		    			var liStr = "<li><a href=\"javascript:void(0);\">"+element.groupName+"</a>";
		    			if(element.users || element.child) {
		    				liStr += "<ul>";
	                    	if(element.users) {
	                    		$.each(element.users, function (j, user) {
	                    			liStr += "<li><a href=\"javascript:void(0);\" onclick=\"inputAttr('applyUser','"+user.userName+"');\">"+user.userName+"</a></li>";
	                    		});
	                    	} 
	                    	if(element.child) {
	                    		$.each(element.child, function (j, child) {
	                    			liStr += "<li><a href=\"javascript:void(0);\">"+child.groupName+"</a>";
	                    			if(child.users || child.child) {
	                    				liStr += "<ul>";
	                    				if(child.users) {
		                    				$.each(child.users, function (j, user1) {
		    	                    			liStr += "<li><a href=\"javascript:void(0);\" onclick=\"inputAttr('applyUser','"+user1.userName+"');\">"+user1.userName+"</a></li>";
		    	                    		});
	                    				}
	                    				if(child.child) {
		                    				$.each(child.child, function (j, child1) {
				                    			liStr += "<li><a href=\"javascript:void(0);\">"+child1.groupName+"</a>";
				                    			if(child1.users) {
				                    				liStr += "<ul>";
				                    				$.each(child1.users, function (j, user2) {
				    	                    			liStr += "<li><a href=\"javascript:void(0);\" onclick=\"inputAttr('applyUser','"+user2.userName+"');\">"+user2.userName+"</a></li>";
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
            });
            
    function notify_e(title,text){
        $.pnotify({title: title, text: text, opacity: .8, type: 'error'});            
    }
    </script>
    <style type="text/css">
    	.tree, .tree ul {
    margin:0;
    padding:0;
    list-style:none
}
.tree ul {
    margin-left:1em;
    position:relative
}
.tree ul ul {
    margin-left:.5em
}
.tree ul:before {
    content:"";
    display:block;
    width:0;
    position:absolute;
    top:0;
    bottom:0;
    left:0;
    border-left:1px solid
}
.tree li {
    margin:0;
    padding:0 1em;
    line-height:2em;
    color:#369;
    font-weight:700;
    position:relative;
    cursor:pointer;
}
.tree ul li:before {
    content:"";
    display:block;
    width:10px;
    height:0;
    border-top:1px solid;
    margin-top:-1px;
    position:absolute;
    top:1em;
    left:0
}
.tree ul li:last-child:before {
    background:#fff;
    height:auto;
    top:1em;
    bottom:0
}
.indicator {
    margin-right:5px;
}
.tree li a {
    text-decoration: none;
    color:#369;
}
.tree li button, .tree li button:active, .tree li button:focus {
    text-decoration: none;
    color:#369;
    border:none;
    background:transparent;
    margin:0px 0px 0px 0px;
    padding:0px 0px 0px 0px;
    outline: 0;
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
                        </div>
                        <div class="block-fluid">
                        <ul id="treeview" class="tree">
                            </ul>
                        </div>
                    </div> 
                    <div class="col-md-9">                    
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>用户管理</h1>  

                            <ul class="buttons">                          
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                    	<li><a href="#groupModal" class="lnk_new" role="button" data-toggle="modal"><span class="isw-ok"></span> 创建</a></li>
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
									<th width="20%">用户名</th>
									<th >真实姓名</th>
									<th >所属部门</th>
									<th width="180px">操作</th>
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
</body>

</html>
