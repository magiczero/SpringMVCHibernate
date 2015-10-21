<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>附件上传</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/stylesheets.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    <link rel='stylesheet' type='text/css' href='${contextPath }/resources/css/fullcalendar.print.css' media='print' />
    <!-- blueimp Gallery styles -->
    <link rel="stylesheet" href="${contextPath }/resources/css/blueimp-gallery.min.css">
    <!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
    <link rel="stylesheet" href="${contextPath }/resources/css/jquery.fileupload.css">
    <link rel="stylesheet" href="${contextPath }/resources/css/jquery.fileupload-ui.css">
    
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
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
        
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.gears.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.silverlight.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.flash.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.browserplus.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.html4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.html5.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/jquery.plupload.queue/jquery.plupload.queue.js'></script>    
    
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
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
            $(document).ready(function () {
            	$(".header").load("${contextPath }/header");
                $(".menu").load("${contextPath }/menu", function () { $(".navigation > li:eq(4)").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath }/contentbuttons");
                
                $("#btnUpload").click(function() {
                	$('#fileupload').fileupload();
                });
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
                    <li><a href="#">附件管理</a> <span class="divider">></span></li>         
                    <li class="active">上传附件</li>
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
                            <h1>附件录入</h1>
                        </div><c:url var="uploadAction" value="/attachment/upload" ></c:url>
						<form action='' method="POST" enctype="multipart/form-data">
                        <div class="block-fluid">                        
                          <div class="row-form clearfix">
                                <div class="col-md-2"><label>文档名称*</label></div>
                                <div class="col-md-4">
                                    技术手册.doc
                                </div>
                                <div class="col-md-2">关键字</div>
                                <div class="col-md-4">
                                    技术，手册
                                </div>
                            </div> 
                            <div class="row-form clearfix">
                                <div class="col-md-2">类型</div>
                                <div class="col-md-10">
                                    手册
                                </div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2">版本</div>
                                <div class="col-md-10">
                                    v1.0
                                </div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-12">
									<input id="fileupload" type="file" name="files[]" data-url="${uploadAction }" multiple>
					                <!-- The global file processing state -->
               						<span class="fileupload-process"></span>
								</div>
                               	<!-- The global progress state -->
					            <div class="col-lg-5 fileupload-progress fade">
					                <!-- The global progress bar -->
					                <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
					                    <div class="progress-bar progress-bar-success" style="width:0%;"></div>
					                </div>
					                <!-- The extended global progress state -->
					                <div class="progress-extended">&nbsp;</div>
					            </div>
                            </div>

                            <div class="footer tar">
                               <input id="btnUpload" type="button" value="上传">
                            </div>                            
                        </div>
                        </form>
                    </div>
                    
                </div>

            </div>
            <!--workplace end-->
        </div>   
    </div>
    
   
<script src="${contextPath }/resources/js/vendor/jquery.ui.widget.js"></script>
<script src="${contextPath }/resources/js/upload/jquery.iframe-transport.js"></script>
<script src="${contextPath }/resources/js/upload/jquery.fileupload.js"></script>
<script src="${contextPath }/resources/js/upload/cors/jquery.xdr-transport.js"></script>
<!-- <script src="${contextPath }/resources/js/vendor/jquery.ui.widget.js"></script>
<script src="${contextPath }/resources/js/upload/tmpl.min.js"></script>
<script src="${contextPath }/resources/js/upload/load-image.all.min.js"></script>
<script src="${contextPath }/resources/js/upload/canvas-to-blob.min.js"></script>
<script src="${contextPath }/resources/js/upload/jquery.blueimp-gallery.min.js"></script>
<script src="${contextPath }/resources/js/upload/jquery.iframe-transport.js"></script>
<script src="${contextPath }/resources/js/upload/jquery.fileupload.js"></script>
<script src="${contextPath }/resources/js/upload/jquery.fileupload-process.js"></script>
<script src="${contextPath }/resources/js/upload/jquery.fileupload-image.js"></script>
<script src="${contextPath }/resources/js/upload/jquery.fileupload-audio.js"></script>
<script src="${contextPath }/resources/js/upload/jquery.fileupload-video.js"></script>
<script src="${contextPath }/resources/js/upload/jquery.fileupload-validate.js"></script>
<script src="${contextPath }/resources/js/upload/jquery.fileupload-ui.js"></script>
<script src="${contextPath }/resources/js/upload/main.js"></script>
<!--[if (gte IE 8)&(lt IE 10)]>
<script src="${contextPath }/resources/js/upload/cors/jquery.xdr-transport.js"></script>
<![endif]-->
</body>

</html>