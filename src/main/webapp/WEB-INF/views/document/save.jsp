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
    
    <title>附件上传</title>

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
    <link href="${contextPath }/resources/css/dataTables.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
	<link href="${contextPath }/resources/css/stylesheets2.css" rel="stylesheet" type="text/css" />
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
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.stack.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.pie.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.resize.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/sparklines/jquery.sparkline.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fullcalendar/fullcalendar.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/animatedprogressbar/animated_progressbar.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cleditor/jquery.cleditor.js'></script>
      
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/plupload/js/plupload.full.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/plupload/js/jquery.plupload.queue/jquery.plupload.queue.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/plupload/js/i18n/zh_CN.js'></script>   
        
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/tagsinput/jquery.tagsinput.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/cookies.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/actions.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/charts.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/settings.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/faq.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    var ctx="${contextPath}";
            $(document).ready(function () {
            	$(".header").load("${contextPath }/header");
            	// $(".menu").load("${contextPath }/menu", function () { $(".navigation > li:eq(2)").addClass("active"); });
                $(".menu").load("${contextPath }/menu?t="+pm_random(), function() {$("#node_${moduleId}").addClass("active");});
                $(".breadLine .buttons").load("${contextPath }/contentbuttons");
                
                $("#document").validationEngine({promptPosition : "topLeft", scroll: true});
                
                $(function() {
            		$("#uploader").pluploadQueue({
            			// General settings
            			runtimes : 'gears,flash,silverlight,browserplus,html5,html4',
            			url : '${contextPath }/attachment/plupload',
            			max_file_size : '10mb',
            			unique_names : true,
            			chunk_size: '2mb',
            			filters: {
            				  mime_types : [ //只允许上传图片和zip文件
            				    { title : "Image files", extensions : "jpg,jpeg,gif,png" },
            				    { title : "Doc files", extensions : "xls,xlsx,doc,docx,ppt,pdf" },
            				    { title : "Zip files", extensions : "zip" }
            				  ],
            				  prevent_duplicates : true //不允许选取重复文件
            				},
            	
            			// Flash settings
            			flash_swf_url : '${contextPath }/resources/js/plugins/plupload/plupload.flash.swf',
            			// Silverlight settings
            			silverlight_xap_url : '${contextPath }/resources/js/plugins/plupload/plupload.silverlight.xap'
            		});
            		$('form[name=document]').submit(function(e) {
            			if($('form[name=document]').validationEngine('validate')) {
            	        var uploader = $('#uploader').pluploadQueue();
            	        if (uploader.files.length > 0) {
            	            // When all files are uploaded submit form
            	            uploader.bind('StateChanged', function() {
            	                if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
            	                    $('form')[1].submit();
            	                    return false;
            	                }
            	            });
            	            uploader.start();
            	        } else {
            				alert('请先上传数据文件.');
            			}}
            	        return false;
                	});
            	});
                //$(".plupload_start")[0].hide();
                //document.querySelector('.plupload_start').style.display = 'none';
            });
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
                    <li><a href="#">文档管理</a> <span class="divider">></span></li>    
                    <li><a href="#">${syscode.codeName }</a> <span class="divider">></span></li>       
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
                        </div><c:url var="addAction" value="/document/save" ></c:url>
						<form:form action="${addAction}" name="document" commandName="document">
						<input type="hidden" name="versions" value="1" />
                        <div class="block-fluid">                        
                          <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="name">文档名称*</form:label></div>
                                <div class="col-md-4"><form:input path="name" class="validate[required,minSize[2],maxSize[30]] text-input" /><form:errors path="name" cssClass="error" />
                                </div>
                                <div class="col-md-2"><form:label path="keywords">关键字*</form:label></div>
                                <div class="col-md-4"><form:input path="keywords" class="validate[required,minSize[2],maxSize[30]] text-input" />
                                </div>
                            </div> 
                            <div class="row-form clearfix">
                             <div class="col-md-2"><form:label path="auth">密级*</form:label></div>
                                <div class="col-md-4">
									<form:select path="secretLevel" multiple="false">
										<c:forEach items="${levels }" var="level">
										<form:option value="${level.value }">${level.level }</form:option> 
										</c:forEach>
									</form:select>
									</div>
                                <div class="col-md-2"><form:label path="docNum">编号*</form:label></div>
                                <div class="col-md-4"><form:input path="docNum" class="validate[required] text-input"></form:input>
                                </div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2">
                                <label for="style.id">所属类别</label>
                                </div>
                                <div class="col-md-7">
									<form:select path="style.id" class="validate[required]">
									<c:forEach items="${styleList}" var="styleOne">
										<form:option value="${styleOne.id }">${styleOne.name }</form:option>
										<c:forEach items="${styleOne.child}" var="styleTwo">
											<form:option value="${styleTwo.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| - ${styleTwo.name }</form:option>
											<c:forEach items="${styleTwo.child}" var="styleThree">
												<form:option value="${styleThree.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| - ${styleThree.name }</form:option>
											</c:forEach>
										</c:forEach>
									</c:forEach>
									</form:select>
                                </div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2">
                                <label for="deposit">存放位置</label>
                                </div>
                                <div class="col-md-7">
                                <form:input path="deposit" ></form:input>
                                </div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-12">
                                <div id="uploader">
									<p>您的浏览器未安装 Flash, Silverlight, Gears, BrowserPlus 或者支持 HTML5 .</p>
								</div>
								</div>
                            </div>
							<div class="row-form clearfix">
                                <div class="col-md-2">
                                <label for="link">文档链接</label>
                                </div>
                                <div class="col-md-7">
                                <form:input path="link" ></form:input> 上传文档与文档链接只能选择一项
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