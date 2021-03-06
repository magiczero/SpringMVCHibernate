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
    <link href="${contextPath }/resources/css/uploadify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/js/plugins/datetimepicker/datetimepicker.min.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uploadify/jquery.uploadify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/datetimepicker/datetimepicker.min.js' charset="UTF-8"></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/datetimepicker/datetimepicker.zh-CN.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.edit.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.async.js'></script>
      
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <%--<script type='text/javascript' src='${contextPath }/resources/js/pm-select.js'></script> //IE8+    --%>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
        $(document).ready(function () {
          	$(".header").load("${contextPath }/header?t=" + pm_random());
            $(".menu").load("${contextPath }/menu?t=" + pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
            $(".breadLine .buttons").load("${contextPath }/contentbuttons?t=" + pm_random());
          	
            //表单验证
            $("#validation").validationEngine({promptPosition : "topRight", scroll: true});
          	<%-- IE8+
            //选择事件分类
            act_dialog_category_init();
            $("input[name='category']").bind("click",function(){
            	act_dialog_category_select('INCIDENT_CATEGORY','category');
            });
            --%>
            $(".dateISO").datetimepicker({autoclose: true,language: 'zh-CN',minuteStep: 5,todayBtn: true});
            $("#module.id").select2();
            //------------------IE8---------------------------
            $(".wrapper").append("<div class='dialog' id='b_popup_select' style='display: none;' title='事件分类'></div>");
			$("#b_popup_select").html("<div class='block dialog_block messages '>"
					+"<div>"
					+"<ul id='treeview'></ul>"
					+"</div></div>");
			
		    $("#b_popup_select").dialog({
		        autoOpen: false,
		        width: 400,
		        height:500,
		        buttons: { "确定": function () { $(this).dialog("close") } }
		    });
		    
		    if($("#id").val()=="")
		    $("#finishTime").val(gettodayfinishedtime());
            
		    $("input[name='category']").bind("click",function(){
		    	$("#treeview").empty('');
		    	
		    	$.getJSON(ctx + '/system/syscode/getjson/INCIDENT_CATEGORY?t=' + pm_random(), function(data){
		    		obj= $.parseJSON(data.json);
		    		$.each(obj, function (index, element) {
		    			var liStr = "";
                    	if(element.nodes) {
                    		liStr = "<li><a href=\"javascript:void(0);\">"+element.text+"</a><ul>";
                    		$.each(element.nodes, function (j, element1) {
                    			if(element1.nodes) {
                    				liStr += "<li><a href=\"javascript:void(0);\">"+element1.text+"</a><ul>";
                    				$.each(element1.nodes, function (k, element2) {
                    					var code2 = element2.text.substring(0,element2.text.indexOf(" "));
                        				liStr += "<li><a href=\"javascript:void(0);\" onclick=\"inputAttr('category','"+code2+"');\">"+element2.text+"</a></li>";
                    				});
                    				liStr += "</ul>";
                    			} else {
                    				var code1 = element1.text.substring(0,element1.text.indexOf(" "));
                    				liStr += "<li><a href=\"javascript:void(0);\" onclick=\"inputAttr('category','"+code1+"');\">"+element1.text+"</a></li>";
                    			}
                    		})
                    		liStr += "</ul>";
                    	} else {
                    		var code = element.text.substring(0,element.text.indexOf(" "));
                    		liStr = "<li><a href=\"javascript:void(0);\" onclick=\"inputAttr('category','"+code+"');\">"+element.text+"</a>";
                    	}
                    	
                    	liStr += "</li>";
                    	$("#treeview").append(liStr);
		    		});
		    		$("#treeview").treeview({
	            		collapsed: true,
	            		unique: true
	            	});
		    		$("#b_popup_select").dialog('open');
		    	});
		    	
            });
		    //----------------------IE8 end--------------------
		    
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
            
            //输入摘要是自动补全描述字段
            $("#abs").bind("blur",function(){
            	if( $("#detail").val()=="" )
            		$("#detail").text($("#abs").val());
            });
            //初始化表单信息
			initForm();
            //申请人下拉框效果
			//$("#applyUser").select2();
            
			$('#file_upload').uploadify({
				'formData' : { 'type' : 2 },
		        'swf'      : '${contextPath }/resources/flash/uploadify.swf',
		      //按钮显示的文字
                'buttonText': '选择文件……',
		        'uploader' : '${contextPath}/attachment/upload',
		        'removeCompleted' : false,
		        'multi': true,
		        // Put your options here
		        'onUploadSuccess': function (file, data, response) {
		        	console.log(data);
	                $('#' + file.id).find('.data').html(' 上传完毕');
	                var fileids = document.getElementById("fileids").value + '';
	                document.getElementById("fileids").value = fileids + data;
		        }
		    });

        });
        function initForm()
        {
        	$("select[name='influence'] option[value='04']").attr("selected","selected");
        	$("select[name='critical'] option[value='04']").attr("selected","selected");
        	$("select[name='priority'] option[value='04']").attr("selected","selected");
        }
        function inputAttr(name,value) {
        	$("input[name='"+name+"']").attr("value",value);
        }
        
        function inputUserinfo(username,realname,tel,room,userid) {
        	$("#applyUser").attr("value", username);
        	$("input[name='user_name']").attr("value",realname);
        	$("input[name='phoneNumber']").attr("value",tel);
        	$("#room").attr("value", room);
        	
        	$("#b_popup_group").dialog('close');
        }
        
        function inputGroupinfo(groupid, groupname) {
        	console.log(groupid + ' ' + groupname);
        }

      //获取当前时间,格式 2015-09-05 10:00:00.000
        function gettodayfinishedtime() {
                  var nowtime = new Date();
                  var year = nowtime.getFullYear();
                  var month = padleft0(nowtime.getMonth() + 1);
                  var day = padleft0(nowtime.getDate());
                  return year + "-" + month + "-" + day + " 17:00";
        }
              //补齐两位数
        function padleft0(obj) {return obj.toString().replace(/^[0-9]{1}$/, "0" + obj);}

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
                    <li><a href="${contextPath }/incident/list">事件管理</a> <span class="divider">></span></li>   
                    <c:if test="${not empty incident.id }" >     
                    	<li class="active">修改事件信息</li>
                    </c:if>
                    <c:if test="${empty incident.id }" >     
                    	<li class="active">创建新事件</li>
                    </c:if>
                </ul>
                <ul class="buttons"></ul>
            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">

                <div class="row">
                	<div class="col-md-1"></div>
	                <div class="col-md-10">
	                        <div class="head clearfix">
	                            <div class="isw-empty_document"></div>
	                            <h1>事件信息</h1>
	                        </div>
	                        <c:url var="addAction" value="/incident/save" ></c:url>
	                        <form:form action="${addAction}" commandName="incident" method="post" id="validation">
	                        <form:hidden path="id" />
	                        <form:hidden path="applyUser"/>
	                        <input id="fileids" name="fileids" type="hidden" />
	                        <div class="block-fluid">                        
	                            <div class="row-form clearfix">
	                                <div class="col-md-1"><form:label path="abs">摘要:</form:label></div>
	                                <div class="col-md-11"><form:input path="abs" class="validate[required,maxSize[50]]"></form:input></div>
	                             </div>
	                            <div class="row-form clearfix">
	                                <div class="col-md-1"><form:label path="detail">描述:</form:label></div>
	                                <div class="col-md-11"><form:textarea path="detail" class="validate[required,maxSize[255]]"></form:textarea>
	                                </div>
	                            </div>
	                            <div class="row-form clearfix">
	                                <div class="col-md-1"><label for="user_name">申请人:</label></div>
	                                <div class="col-md-3">
	                                <input type="text" id="user_name" value="${incident.applyUserName }" readonly="readonly" name="user_name" class="validate[required]">
	                                <!--<form:select path="applyUser" items="${users }" itemLabel="name" itemValue="username" cssStyle="width:100%"></form:select>--></div>
	                                <div class="col-md-1"><form:label path="phoneNumber">电话:</form:label></div>
	                                <div class="col-md-3"><form:input path="phoneNumber"></form:input></div>
	                                <div class="col-md-1"><label>房间号:</label></div>
	                                <div class="col-md-3"><input type="text" value="${incident.applyUserRoom }" readonly="readonly" id="room" /></div>
	                            </div> 

	                            <div class="row-form clearfix">
	                           	 	<div class="col-md-1"><form:label path="category">分类:</form:label></div>
	                                <div class="col-md-3"><form:input path="category" readonly="true" class="validate[required,maxSize[50]]"></form:input></div>
	                            	<div class="col-md-1"><form:label path="type">类型:</form:label></div>
	                            	<div class="col-md-3"><form:select path="type" items="${type }" itemLabel="codeName" itemValue="code"></form:select></div>
	                            	<div class="col-md-1"><form:label path="source">来源:</form:label></div>
	                                <div class="col-md-3"><form:select path="source" items="${source }" itemLabel="codeName" itemValue="code"></form:select></div>
	                            </div>      
	                            <div class="row-form clearfix">
	                                <div class="col-md-2"><form:label path="supportType">支持类型:</form:label></div>
	                                <div class="col-md-3"><form:select path="supportType" items="${supporttype }" itemLabel="codeName" itemValue="code"></form:select></div>
	                                <div class="col-md-2"><form:label path="finishTime">要求完成时间:</form:label></div>
	                                <div class="col-md-3"><form:input path="finishTime" cssClass="dateISO" readonly="true"/></div>
	                            </div>   
	                            <div class="row-form clearfix">
	                                <div class="col-md-1"><form:label path="influence">影响度:</form:label></div>
	                                <div class="col-md-3"><form:select path="influence" items="${influence }" itemLabel="codeName" itemValue="code"></form:select></div>
	                                <div class="col-md-1"><form:label path="critical">紧急度:</form:label></div>
	                                <div class="col-md-3"><form:select path="critical" items="${critical }" itemLabel="codeName" itemValue="code"></form:select></div>
	                                <div class="col-md-1"><form:label path="priority">优先级:</form:label></div>
	                                <div class="col-md-3"><form:select path="priority" items="${priority }" itemLabel="codeName" itemValue="code"></form:select></div>
	                            </div>    
	                            <div class="row-form clearfix">
	                            	<div class="col-md-1"><label >文件:</label></div>
	                            	<div class="col-md-11">
										<input type="file" name="file_upload" id="file_upload" />
									</div>
	                            </div>                                                   
	                            <div class="footer">
	                                <button class="btn btn-primary center-block"> 保 存 </button>
	                            </div>                            
	                        </div>
	                   		</form:form>
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
