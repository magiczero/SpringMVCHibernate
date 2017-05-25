<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>事件管理--运维管理系统</title>

    <!-- <link rel="icon" type="image/ico" href="favicon.ico"/> -->
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/js/plugins/select2/select2.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
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
          	$(".header").load("${contextPath }/header?t=" + pm_random());
            $(".menu").load("${contextPath }/menu?t=" + pm_random());
            $(".breadLine .buttons").load("${contextPath }/contentbuttons?t=" + pm_random());
          	
            //表单验证
            $("#form1").validationEngine('attach', {
                promptPosition: 'topRight',
                scroll: false,
                ajaxFormValidation: true,
                ajaxFormValidationMethod: 'post',  //设置 Ajax 提交时，发送数据的方式
                //ajaxFormValidationURL : "../api/person",
                onBeforeAjaxFormValidation: beforeCall,
                onAjaxFormComplete: ajaxValidationCallback,
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
            
		    $(".alert").hide();
		    
		    $(".alert").click(function(){
		        $(this).fadeOut(300, function(){            
		                $(this).hide();            
		        });
		    });
        });
       
        function inputUserinfo(username,realname,tel,room,userid) {
        	$("input[name='userid']").attr("value", userid);
        	$("input[name='user_name']").attr("value",realname);
        	//ajax获取部门的CMDB
        	$.getJSON("get-cmdb-list?t=" + pm_random()+"&username="+username, function(data){
        		$("#s2_2").empty();
        		$.each(data, function(i,item){
        			$("#s2_2").append("<option value='"+item.id+"'>"+item.name+"</option>");
        		  });
        		
        		$("#s2_2").select2();
			});
        	$("#b_popup_group").dialog('close');
        }
        
        function inputGroupinfo(groupid, groupname) {
        	console.log(groupid + ' ' + groupname);
        }

        function beforeCall(form, options) {
           
            return true;
        }

        // Called once the server replies to the ajax form validation request

        function ajaxValidationCallback(status, form, json, options) {
            if (window.console) {
                console.log(status);
            }
            if (status === true) {		
                if(json.OK) {	//保存成功，给出提示
                	window.location='relationship-index';
                } else {
                	$(".alert-danger").append(json.message);
                	$(".alert-danger").show();
                }
                // uncomment these lines to submit the form to form.action
                // form.validationEngine('detach');
                // form.submit();
                // or you may use AJAX again to submit the data
            } 
        }

    </script>
    <style type="text/css">
    	.uploadify-button-text {color:#fff !important;}
    	
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
                    <li><a href="#">三员管理</a> <span class="divider">></span></li>  
                    <li class="active">新建三员信息</li>  
                </ul>
                <ul class="buttons"></ul>
            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">
				<div class="alert alert-danger">                
                    <h4>现在错误，无法保存!</h4>
                    
                </div>            

                <div class="alert alert-success">                
                    <h4>添加三员成功!</h4>
                    	三员添加成功
                </div> 
                <div class="row">
                	<div class="col-md-1"></div>
	                <div class="col-md-10">
	                        <div class="head clearfix">
	                            <div class="isw-empty_document"></div>
	                            <h1>新建三员信息</h1>
	                        </div>
	                       	<form action="${contextPath }/three-member/save-relationship" id="form1">
	                        <input name="userid" type="hidden" />
	                        <div class="block-fluid">                        
	                            <div class="row-form clearfix">
	                                <div class="col-md-2"><label for="user_name">选择人员:</label></div>
	                                <div class="col-md-4"><input type="text" id="user_name" readonly="readonly" name="user_name" class="validate[required]"></div>
	                                <div class="col-md-2"><label for="role_">选择角色:</label></div>
	                                <div class="col-md-4">
										<select name="role_" class="validate[required]">
											<option value="1">系统管理员</option>
											<option value="2">安全管理员</option>
											<option value="3">安全审计员</option>
										</select>
	                                </div>
	                            </div>
	                            <div class="row-form clearfix">
	                               	<div class="col-md-2"><label for="cmdb">选择CMDB:</label></div>
                                	<div class="col-md-10">                                
                                    <select name="cmdb" id="s2_2" style="width: 100%;" multiple="multiple">
                                                                       
                                    </select>
                                </div>
	                            </div> 
	                            <div class="footer">
	                                <button class="btn btn-primary center-block"> 保 存 </button>
	                            </div>                            
	                        </div>
	                   		</form>
	                    </div>
	                <div class="col-md-1"></div>
                </div>

            </div>
            <!--workplace end-->
        </div>  
    </div>
    
    <div class='dialog' id='b_popup_group' style='display: none;' title='部门列表'>
    	<div class='block dialog_block messages '><div><ul id='grouptree'></ul></div></div>
    </div>
</body>
</html>
