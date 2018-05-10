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
    <link href='${contextPath }/resources/js/plugins/jstree/jquery.treeview.css' rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/js/plugins/datetimepicker/datetimepicker.min.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uploadify/jquery.uploadify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/datetimepicker/datetimepicker.min.js' charset="UTF-8"></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/datetimepicker/datetimepicker.zh-CN.js'></script>
    <!-- <script type='text/javascript' src='${contextPath }/resources/js/plugins/jtree/jtree.js'></script>-->
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.edit.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.async.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- <script type='text/javascript' src='${contextPath }/resources/js/pm-select.js'></script> -->
    
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
                $("#validation").validationEngine({promptPosition : "topRight", scroll: true});
              
                $(".dateISO").datetimepicker({autoclose: true,language: 'zh-CN',minuteStep: 5,todayBtn: true});
                
                $("#b_popup_select").dialog({
			        autoOpen: false,
			        width: 400,
			        height:500,
			        buttons: { "确定": function () { $(this).dialog("close") } }
			    });
			    
                $("input[name='equipName']").bind("click",function(){
                	
                	//act_dialog_ci_select("categoryCode",true);//IE8+
                	$("#treeview").empty('');
                	
                	$("#treeview").treeview({
                		collapsed: true,
                		unique: true,
                		url : ctx + '/cms/category/getjson-all-department?t=' + pm_random()
	            	});
            		
            		$("#b_popup_select").dialog('open');
                	
                });
                
                $('#file_upload').uploadify({
    				'formData' : { 'type' : 9 },
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
            
            function insert(id,name,num) {
            	$("input[name='equipName']").val(name);
            	$("input[name='equipNum']").val(num);
            	
            	$("input[name='equipId']").val(id);
            	
            	$("#b_popup_select").dialog('close');
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
                    <li><a href="${contextPath }/maintain-record">工作记录</a> <span class="divider">></span></li>         
                    <li class="active">新建维护工作记录</li>
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
                            <div class="isw-documents"></div>
                            <h1>新建配置项</h1>
                        </div>
                        <c:url var="addAction" value="/maintain-record/save" ></c:url>
						<form:form id="validation"  action="${addAction}" commandName="maintainRecord" method="post">
						<input id="fileids" name="fileids" type="hidden" />
						<form:hidden path="equipId"/>
                        <div class="block-fluid">                        
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="equipName">设备名称:</form:label></div>
                                <div class="col-md-4"><form:input path="equipName" cssClass="validate[required,maxSize[50]]"></form:input></div>
                                <div class="col-md-2"><form:label path="equipNum">设备编号:</form:label></div>
                                <div class="col-md-4"><form:input path="equipNum" cssClass="validate[required,maxSize[50]]"></form:input></div>
                            </div>
                            <div class="row-form clearfix">
                            	<div class="col-md-2"><form:label path="maintainTime">维护时间：</form:label></div>
                                <div class="col-md-4"><form:input path="maintainTime" cssClass="validate[required] dateISO"></form:input></div>
                                <div class="col-md-2"><form:label path="type">维护类型:</form:label></div>
	                            <div class="col-md-4"><form:select path="type">
											<form:option value="1">变更</form:option>
											<form:option value="2">运维</form:option>
											<form:option value="3">维修</form:option>
											<form:option value="4">报废</form:option>
											<form:option value="5">授权</form:option>
											<form:option value="6">其他</form:option>
										</form:select></div>
                            </div>
                            <div class="row-form clearfix">
                            	
                            	<div class="col-md-2"><form:label path="escort">陪同人员:</form:label></div>
	                            <div class="col-md-10"><form:input path="escort" cssClass="validate[maxSize[50]]"></form:input></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="circs">执行情况:</form:label></div>
                                <div class="col-md-10"><form:textarea path="circs" cssClass="validate[maxSize[500]]"/></div>
                            </div> 
                            <div class="row-form clearfix">
                            	<div class="col-md-2"><form:label path="addIn">外接设备情况:</form:label></div>
	                            <div class="col-md-10"><form:textarea path="addIn" cssClass="validate[maxSize[500]]"/></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="remark">备注:</form:label></div>
                                <div class="col-md-10"><form:textarea path="remark" cssClass="validate[maxSize[500]]"/></div>
                            </div>  
                            <div class="row-form clearfix">
	                            	<div class="col-md-2"><label >文件:</label></div>
	                            	<div class="col-md-10">
										<input type="file" name="file_upload" id="file_upload" />
									</div>
	                            </div> 
                            <div class="footer tar">                        	
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
    <div class='dialog' id='b_popup_select' style='display: none;' title='设备列表'>
    	<div class='block dialog_block messages '><ul id='treeview'></ul></div>
    </div>   
</body>

</html>