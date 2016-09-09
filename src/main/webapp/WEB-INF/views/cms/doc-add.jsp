<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE HTML>
<html>
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>录入文档信息</title>

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
    <link href="${contextPath }/resources/js/plugins/select2/select2.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/tagsinput.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/dataTables.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/uploadify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
	<link href="${contextPath }/resources/css/stylesheets2.css" rel="stylesheet" type="text/css" />
	<link href='${contextPath }/resources/js/plugins/jstree/jquery.treeview.css' rel="stylesheet" type="text/css" />
	<link type="text/css" rel="stylesheet" href="${contextPath }/resources/plupload/js/jquery.plupload.queue/css/jquery.plupload.queue.css" />
	
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
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/animatedprogressbar/animated_progressbar.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cleditor/jquery.cleditor.js'></script>
      
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uploadify/jquery.uploadify.min.js'></script>
        
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.edit.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.async.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/tagsinput/jquery.tagsinput.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    var ctx="${contextPath}";
            $(document).ready(function () {
            	$(".header").load("${contextPath }/header?t="+pm_random());
            	$(".menu").load("${contextPath }/menu?t="+pm_random(), function() {$("#node_${moduleId}").addClass("active");});
            	$(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
                
                $("#document").validationEngine({promptPosition : "topRight", scroll: true});
                $("#securityNo").select2();
                $(function() {
                	$('#file_upload').uploadify({
        				'formData' : { 'type' : 3 },
        		        'swf'      : '${contextPath }/resources/flash/uploadify.swf',
        		        'fileTypeExts': '*.jpg;*.doc;*.docx;*.xls;*.xlsx;*.pdf;*.ppt;*.pptx',//允许上传的文件类型，限制弹出文件选择框里能选择的文件
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
            		$('form[name=document]').submit(function(e) {
            			if($('form[name=document]').validationEngine('validate')) {
            				if(document.getElementById("fileids").value == '') {
            					notify_e('Error','请上传文件');
            					return false;
            				} else {
            					//$('form')[2].submit();
            					$this.submit();
            				}
            			}
            	        return false;
                	});
            	});

                $(".wrapper").append("<div class='dialog' id='b_popup_select' style='display: none;' title='分类'></div>");
				$("#b_popup_select").html("<div class='block dialog_block messages '>"
						+"<ul id='treeview'></ul>"
						+"</div>");
				
			    $("#b_popup_select").dialog({
			        autoOpen: false,
			        width: 400,
			        height:500,
			        buttons: { "确定": function () { $(this).dialog("close") } }
			    });
            	
 				$("input[name='categoryCode']").bind("click",function(){
                	
                	$("#treeview").treeview({
                		unique: true,
                		url : ctx + '/cms/ci/document/document-category-tree?t=' + pm_random()
	            	});
            		
            		$("#b_popup_select").dialog('open');
                	
                });
            });
            function notify_e(title,text){
                $.pnotify({title: title, text: text, opacity: .8, type: 'error'});            
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
                    <li><a href="${contextPath}/document">文档管理</a> <span class="divider">></span></li>    
                    <li class="active">文档信息</li>
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
                            <h1>文档信息录入</h1>
                        </div><c:url var="addAction" value="/cms/ci/document/save" ></c:url>
						<form:form action="${addAction}" name="document" commandName="document">
						<input id="fileids" name="fileids" type="hidden" />
                        <div class="block-fluid">                        
                          <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="name">文档名称*</form:label></div>
                                <div class="col-md-4"><form:input path="name" class="validate[required,minSize[2],maxSize[200]] text-input" /><form:errors path="name" cssClass="error" />
                                </div>
                                <div class="col-md-2"><form:label path="remark">关键字*</form:label></div>
                                <div class="col-md-4"><form:input path="remark" class="validate[required,minSize[2],maxSize[30]] text-input" />
                                </div>
                            </div> 
                            <div class="row-form clearfix">
                             <div class="col-md-2"><form:label path="securityNo">密级*</form:label></div>
                                <div class="col-md-4">
									<form:select path="securityNo" multiple="false" style="width:100%;">
										<c:forEach items="${levels }" var="level">
										<form:option value="${level.value }">${level.level }</form:option> 
										</c:forEach>
									</form:select>
									</div>
                                <div class="col-md-2"><form:label path="model">编号*</form:label></div>
                                <div class="col-md-4"><form:input path="model" class="validate[required] text-input"></form:input>
                                </div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2">
                                <label >所属类别*</label>
                                </div>
                                <div class="col-md-7">
									<form:input path="categoryCode" class="validate[required] text-input" readonly="true"/>
                                </div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2">
                                <label for="location">存放位置</label>
                                </div>
                                <div class="col-md-7">
                                <form:input path="location" ></form:input>
                                </div>
                            </div>
                            <div class="row-form clearfix">
                            	<div class="col-md-2">
                                	<label >文件：</label>
                                </div>
                                <div class="col-md-7">
                                	<input type="file" name="file_upload" id="file_upload" />
                                </div>
                            </div>
							<div class="row-form clearfix">
                                <div class="col-md-2">
                                <label for="incidence">文档链接</label>
                                </div>
                                <div class="col-md-7">
                                <form:input path="incidence" ></form:input> 上传文档与文档链接只能选择一项
                                </div>
                            </div>
                            <div class="footer tar">
                               <input type="submit" class="btn btn-primary center-block" value="提 交" />
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