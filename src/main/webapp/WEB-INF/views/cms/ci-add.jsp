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
              
                //$(".dateISO").datepicker();
                $('.form_date').datetimepicker({
                    language:  'zh-CN',
                    weekStart: 1,
                    todayBtn:  1,
            		autoclose: 1,
            		todayHighlight: 1,
            		startView: 2,
            		minView: 2,
            		forceParse: 0
                });
                
			    $("#b_popup_select").dialog({
			        autoOpen: false,
			        width: 400,
			        height:500,
			        buttons: { "确定": function () { $(this).dialog("close") } }
			    });
			    
                $("input[name='categoryCode']").bind("click",function(){
                	
                	//act_dialog_ci_select("categoryCode",true);//IE8+
                	$("#treeview").empty('');
                	
                	$("#treeview").treeview({
                		collapsed: true,
                		unique: true,
                		url : ctx + '/cms/category/getjson?t=' + pm_random()
	            	});
            		
            		$("#b_popup_select").dialog('open');
                	
                });
                
                $("#b_popup_group").dialog({
    		        autoOpen: false,
    		        width: 400,
    		        height:500,
    		        buttons: { "确定": function () { $(this).dialog("close") } }
    		    });
                
                $("input[name='department1']").bind("click",function(){
    		    	$("#grouptree").empty();
    		    	$("#grouptree").treeview({
    		    		url:ctx + '/group/all-json?haveuser=false&t=' + pm_random(),
                		collapsed: true,
                		unique: true
                	});
    	    		$("#b_popup_group").dialog('open');
                });
                
                $("#userInMaintenance").select2();
                $("select[name='status'] option[value='07']").attr("selected","selected");
                $("#status").select2();
                
                
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
            
            function inputGroupinfo(groupid, groupname) {
            	$("#departmentInUse").val(groupid);
            	$("input[name='department1']").val(groupname);
            	
            	$("#b_popup_group").dialog('close');
            }
            
            function initTable(value) {
            	//console.log(value);
            	$("#categoryCode").attr("value",value);
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
						<form:hidden path="use" value="启用"/>
						<form:hidden path="departmentInUse" value=""/>
						<form:hidden path="importance" value="低"/>
						<input id="fileids" name="fileids" type="hidden" />
                        <div class="block-fluid">                        
                            <div class="row-form clearfix">
                                <div class="col-md-1"><form:label path="name">设备名称:</form:label></div>
                                <div class="col-md-3"><form:input path="name" class="validate[required,maxSize[50]]"></form:input></div>
                                <div class="col-md-1"><form:label path="num">设备编号:</form:label></div>
                                <div class="col-md-3"><form:input path="num" class="validate[maxSize[5]]"></form:input></div>
                                <div class="col-md-1"><form:label path="categoryCode">分类:</form:label></div>
                                <div class="col-md-3"><form:input path="categoryCode" readonly="true" class="validate[required,maxSize[50]]"></form:input></div>
                            </div>
                            <div class="row-form clearfix">
                            	<div class="col-md-2"><form:label path="serial">出厂编号/序列号:</form:label></div>
	                            <div class="col-md-2"><form:input path="serial" class="validate[required,maxSize[50]]"></form:input></div>
                            	<div class="col-md-2"><form:label path="model">型号/版本号:</form:label></div>
	                            <div class="col-md-2"><form:input path="model" class="validate[required,maxSize[50]]"></form:input></div>
	                            <div class="col-md-2"><form:label path="producer">生产厂商/品牌:</form:label></div>
	                            <div class="col-md-2"><form:input path="producer" class="validate[required,maxSize[50]]"></form:input></div>
                            </div>
                            <div class="row-form clearfix">
                            	<div class="col-md-1"><form:label path="securityLevel">密级:</form:label></div>
	                            <div class="col-md-3"><form:select path="securityLevel" items="${securityLevel }" itemLabel="codeName" itemValue="code" cssStyle="width:100%"></form:select></div>
                        
                                <div class="col-md-1"><form:label path="securityNo">保密编号:</form:label></div>
                                <div class="col-md-3"><form:input path="securityNo"></form:input></div>
                                
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-1"><label for="department1">所属部门:</label></div>
                                <div class="col-md-3"><input name="department1" type="text" readonly="readonly"/></div>
                                <div class="col-md-1"><form:label path="userInMaintenance">责任人:</form:label></div>
                                <div class="col-md-3"><form:select path="userInMaintenance" items="${users }" itemLabel="name" itemValue="username" cssStyle="width:100%"></form:select></div>
                                <div class="col-md-1"><form:label path="purpose">设备用途:</form:label></div>
                                <div class="col-md-3"><form:input path="purpose" class="validate[required,maxSize[50]]"></form:input></div>
                            </div> 
                            <div class="row-form clearfix">
                          		<div class="col-md-1">
				                	<label for="serviceStartTime">启用时间</label>
				                </div>
				                <div class="col-md-3">
				                <div class="input-group date form_date " data-date="" data-date-format="yyyy-mm-dd" data-link-field="serviceStartTime" data-link-format="yyyy-mm-dd">
				                    <input class="form-control" type="text" value="" readonly>
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				                </div>
								<form:hidden path="serviceStartTime"></form:hidden>
								</div>
				           		<div class="col-md-1">
				                <label for="expirationTime">维保截止时间</label>
				                </div>
				                <div class="col-md-3">
				                <div class="input-group date form_date " data-date="" data-date-format="yyyy-mm-dd" data-link-field="expirationTime" data-link-format="yyyy-mm-dd">
				                    <input class="form-control" type="text" value="" readonly>
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				                </div>
								<form:hidden path="expirationTime"></form:hidden>
				            	</div>
                                <div class="col-md-1"><form:label path="status">使用情况:</form:label></div>
	                            <div class="col-md-3"><form:select path="status" items="${status }" itemLabel="codeName" itemValue="code" cssStyle="width:100%"></form:select></div>
                            </div>                              
                            <div class="row-form clearfix">
                                <div class="col-md-1"><form:label path="location">安放位置:</form:label></div>
                                <div class="col-md-3"><form:input path="location"></form:input></div>
                                <div class="col-md-1"><form:label path="createdTime">购置日期:</form:label></div>
                                <div class="col-md-3">
                                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="createdTime" data-link-format="yyyy-mm-dd">
				                    <input class="form-control" type="text" value="" readonly>
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				                </div>
                                <form:hidden path="createdTime"></form:hidden></div>
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
    <div class='dialog' id='b_popup_group' style='display: none;' title='部门列表'>
    	<div class='block dialog_block messages '><ul id='grouptree'></ul></div>
    </div>
    <div class='dialog' id='b_popup_select' style='display: none;' title='分类'>
    <div class='block dialog_block messages '><ul id='treeview'></ul></div>
    </div>
</body>

</html>
