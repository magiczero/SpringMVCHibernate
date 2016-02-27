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
    
    <title>配置管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/select2.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/uploadify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <%--<link rel='stylesheet' type='text/css' href='${contextPath }/resources/css/bootstrap-treeview.css' media='print' /> --%>
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    <%--<script type='text/javascript' src='${contextPath }/resources/js/plugins/treeview/bootstrap-treeview.min.js'></script> --%>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jtree/jtree.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uploadify/jquery.uploadify.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-select.js'></script>
    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
            $(document).ready(function () {
            	$(".header").load("${contextPath }/header?t="+pm_random());
                $(".menu").load("${contextPath }/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath }/contentbuttons?t="+pm_random());
                
              	//表单验证
                $("#validation").validationEngine({promptPosition : "topLeft", scroll: true});
              
                $(".dateISO").datepicker();
                
                //act_dialog_ci_init();			//IE8+
                $(".wrapper").append("<div class='dialog' id='b_popup_select' style='display: none;' title='分类'></div>");
				$("#b_popup_select").html("<div class='block dialog_block messages '>"
						//+"<div>"
						//+"<div id='treeview'></div>"		//IE8+
						+"<ul id='treeview'></ul>"
						+"</div>");
				
			    $("#b_popup_select").dialog({
			        autoOpen: false,
			        width: 400,
			        height:500,
			        buttons: { "确定": function () { $(this).dialog("close") } }
			    });
			    
                $("input[name='categoryCode']").bind("click",function(){
                	
                	//act_dialog_ci_select("categoryCode",true);//IE8+
                	$("#treeview").html('');
                	$.getJSON(ctx + '/cms/category/getjson?t=' + pm_random(), function(data){
                		//tree_json = data.json;
                		obj= $.parseJSON(data.json);
                		//console.log(obj);
                		$.each(obj, function (i, field) {
                			//console.log(field.text);
                        	var liStr = "<li><a href=\"#\">"+field.text+"</a>";
                        	if(!field.nodes) {
                        		var code = field.text.substring(0,field.text.indexOf(" "));
                        		liStr = "<li><a href=\"#\" onclick=\"inputAttr('categoryCode','"+code+"');\">"+field.text+"</a>";
                        	} 
                        	if(field.nodes) {
                        		liStr += "<ul>";
                        		$.each(field.nodes, function(j, node){
                        			if(node.nodes) {
                        				liStr+="<li><a href=\"#\">"+node.text+"</a>";                        				
                        			} else {
                        				var code1 = node.text.substring(0,node.text.indexOf(" "));
                        				liStr+="<li><a href=\"#\" onclick=\"inputAttr('categoryCode','"+code1+"');\">"+node.text+"</a>";
                        			}
                        			if(node.nodes) {
                        				liStr += "<ul>";
                        				$.each(node.nodes, function(k, last){
                        					var code2 = last.text.substring(0,last.text.indexOf(" "));
                        					liStr+="<li><a href=\"#\" onclick=\"inputAttr('categoryCode','"+code2+"');\">"+last.text+"</a></li>";
                        				});
                        				liStr += "</ul>";
                        			}
                        			liStr+="</li>"
                        		});
                        		liStr += "</ul>";
                        	}
                        	liStr += "</li>";
                            $("#treeview").append(liStr);
                		});
                		
                		$("#treeview").treed();
                		
                		$("#b_popup_select").dialog('open');
                	});
                	
                });
                $("#userInMaintenance").select2();
                init();
                
                $('#file_upload').uploadify({
    				'formData' : { 'type' : 3 },
    		        'swf'      : '${contextPath }/resources/flash/uploadify.swf',
    		      //按钮显示的文字
                    'buttonText': '选择文件……',
    		        'uploader' : '${contextPath}/attachment/upload',
    		        'removeCompleted' : false,
    		        // Put your options here
    		        'onUploadSuccess': function (file, data, response) {
    		        	//console.log(data);
                        $('#' + file.id).find('.data').html(' 上传完毕');
                        var fileids = document.getElementById("fileids").value + '';
                        document.getElementById("fileids").value = fileids + data;
    		        }
    		    });
            });
            function init(){
            	$("select[name='status'] option[value='03']").attr("selected","selected");
            }
            
            function inputAttr(name,value) {
            	//console.log(value);
            	$("input[name='"+name+"']").attr("value",value);
            }	
    </script>
    <style type="text/css">
    	.uploadify-button-text {color:#fff !important;}

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
                    <li><a href="${contextPath }/cms/ci/list">配置管理</a> <span class="divider">></span></li>         
                    <li class="active">新建配置项</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">

                <div class="row">
                    <div class="col-md-12">
                        <div class="head clearfix">
                            <div class="isw-documents"></div>
                            <h1>新建配置项</h1>
                        </div>
                        <c:url var="addAction" value="/cms/ci/save" ></c:url>
						<form:form id="validation"  action="${addAction}" commandName="ci" method="post">
						<input id="fileids" name="fileids" type="hidden" />
                        <div class="block-fluid">                        
                            <div class="row-form clearfix">
                                <div class="col-md-1"><form:label path="name">名称:</form:label></div>
                                <div class="col-md-3"><form:input path="name" class="validate[required,maxSize[50]]"></form:input></div>
                                <div class="col-md-1"><form:label path="model">型号:</form:label></div>
                                <div class="col-md-3"><form:input path="model"></form:input></div>
                                <div class="col-md-1"><form:label path="categoryCode">分类:</form:label></div>
                                <div class="col-md-3"><form:input path="categoryCode" class="validate[required,maxSize[50]]"></form:input></div>
                            </div>
                            <div class="row-form clearfix">
                            	<div class="col-md-1"><form:label path="securityLevel">密级:</form:label></div>
	                            <div class="col-md-3"><form:select path="securityLevel" items="${securityLevel }" itemLabel="codeName" itemValue="code"></form:select></div>
                            	<div class="col-md-1"><form:label path="system">归属系统:</form:label></div>
	                            <div class="col-md-3"><form:select path="system" items="${system }" itemLabel="codeName" itemValue="code"></form:select></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-1"><form:label path="securityNo">保密编号:</form:label></div>
                                <div class="col-md-3"><form:input path="securityNo"></form:input></div>
                                <div class="col-md-1"><form:label path="incidence">影响范围:</form:label></div>
                                <div class="col-md-3"><form:input path="incidence"></form:input></div>
                                <div class="col-md-1"><form:label path="location">物理位置:</form:label></div>
                                <div class="col-md-3"><form:input path="location"></form:input></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-1"><form:label path="departmentInUse">使用部门:</form:label></div>
                                <div class="col-md-3"><form:input path="departmentInUse"></form:input></div>
                                <div class="col-md-1"><form:label path="userInMaintenance">维护人:</form:label></div>
                                <div class="col-md-3"><form:select path="userInMaintenance" items="${users }" itemLabel="name" itemValue="username" cssStyle="width:100%"></form:select></div>
                                <div class="col-md-1"><form:label path="purpose">用途:</form:label></div>
                                <div class="col-md-3"><form:input path="purpose"></form:input></div>
                            </div> 
                            <div class="row-form clearfix">
                                <div class="col-md-1"><form:label path="serviceStartTime">服务开始时间:</form:label></div>
                                <div class="col-md-3"><form:input path="serviceStartTime" cssClass="dateISO"></form:input></div>
                                <div class="col-md-1"><form:label path="serviceEndTime">服务结束时间:</form:label></div>
                                <div class="col-md-3"><form:input path="serviceEndTime" cssClass="dateISO"></form:input></div>
                                <div class="col-md-1"><form:label path="status">状态:</form:label></div>
	                            <div class="col-md-3"><form:select path="status" items="${status }" itemLabel="codeName" itemValue="code"></form:select></div>
                            </div>                              
                            <div class="row-form clearfix">
                                <div class="col-md-1"><form:label path="serviceProvider">服务提供商:</form:label></div>
                                <div class="col-md-3"><form:input path="serviceProvider"></form:input></div>
                                <div class="col-md-1"><form:label path="serviceProviderContact">联系方式:</form:label></div>
                                <div class="col-md-3"><form:input path="serviceProviderContact"></form:input></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-1"><form:label path="producer">厂商:</form:label></div>
                                <div class="col-md-3"><form:input path="producer"></form:input></div>
                                <div class="col-md-1"><form:label path="expirationTime">截至日期:</form:label></div>
                                <div class="col-md-3"><form:input path="expirationTime" cssClass="dateISO"></form:input></div>
                                <div class="col-md-1"><form:label path="remark">备注:</form:label></div>
                                <div class="col-md-3"><form:input path="remark" ></form:input></div>
                            </div>  
                            <div class="row-form clearfix">
	                            	<div class="col-md-1"><label >文件:</label></div>
	                            	<div class="col-md-11">
										<input type="file" name="file_upload" id="file_upload" />
									</div>
	                            </div> 
                            <div class="footer tar">                        	
                                <button class="btn btn-primary center-block"> 保 存 </button>
                            </div>                            
                        </div>
                        </form:form>
                    </div>
                </div>

            </div>
            <!--workplace end-->
        </div>   
    </div>
    
</body>

</html>
