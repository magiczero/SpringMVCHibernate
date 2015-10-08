<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
    
    <title>软件管理--运维管理系统</title>

    <link href="${contextPath }/resources/css/stylesheets.css" rel="stylesheet" type="text/css" />
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
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    
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
                
                $("#software").validationEngine();
                
                $("#showhd").fancybox({
        	    	'href'  : '${contextPath }/harddisk/list/${server.id }',
        	    	'width' : 778,
        	        'height' : 530,
        	        'type' : 'iframe',
        	        'hideOnOverlayClick' : true,
        	        'showCloseButton' : true
        	    });
                
                $("#shownetcard").fancybox({
        	    	'href'  : '${contextPath }/networkcard/list/${server.id }',
        	    	'width' : 778,
        	        'height' : 530,
        	        'type' : 'iframe',
        	        'hideOnOverlayClick' : true,
        	        'showCloseButton' : true
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
                    <li><a href="${contextPath }/Asset/list">资产管理</a> <span class="divider">></span></li>         
                    <li class="active">软件</li>
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
                            <h1>软件详细信息</h1>
                            <ul class="buttons">        
                                <li>
                                    <a class="isw-users" href="#"></a>
                                </li>
                                <li>
                                    <a class="isw-settings" href="#"></a>
                                    <ul class="dd-list">
                                        <li><a id="showhd" href="javascript:void(0);"><span class="isw-text_document"></span>已安装服务器</a></li>
                                        <li><a id="shownetcard" href="javascript:void(0);"><span class="isw-mail"></span> 网卡</a></li>
                                        <li><a href="#"><span class="isw-refresh"></span> 软件</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <c:url var="addAction" value="/software/update" ></c:url>
						<form:form action="${addAction}" commandName="software">
						<form:hidden path="id"/>
						<form:hidden path="equipType.id" />
                        <div class="block-fluid">                        
                          <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="assetNum">资产编号</form:label></div>
                                <div class="col-md-4">
                                    <form:input path="assetNum" class="validate[required] text-input" /><form:errors path="assetNum" cssClass="error" />
                                </div>
                                <div class="col-md-2"><form:label path="secretNum">涉密编号</form:label></div>
                                <div class="col-md-4">
                                    <form:input path="secretNum" /><form:errors path="secretNum" cssClass="error" />
                                </div>
                            </div> 
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="brand">品牌</form:label></div>
                                <div class="col-md-4"><form:input path="brand" /></div>
                                <div class="col-md-2"><form:label path="model">设备型号</form:label></div>
                                <div class="col-md-4"><form:input path="model" /></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="snNum">SN</form:label></div>
                                <div class="col-md-4"><form:input path="snNum" /></div>
                                <div class="col-md-2"><form:label path="secretLevel">密级</form:label></div>
                                <div class="col-md-4"><form:select path="secretLevel" multiple="false">
				<c:forEach items="${levels }" var="level">
				<form:option value="${level.value }">${level.level }</form:option> 
				</c:forEach>
			</form:select></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="purpose">用途</form:label></div>
                                <div class="col-md-10"><form:textarea path="purpose"/>
                                    </div>
                            </div> 
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="productionDate">生产日期</form:label></div>
                                <div class="col-md-4"><form:input path="productionDate"  id="Datepicker" readonly="readonly"/></div>
                                <div class="col-md-2"><form:label path="purchaseTime">购置时间</form:label></div>
                                <div class="col-md-4"><form:input path="purchaseTime"  id="menuDatepicker" readonly="readonly"/></div>
                            </div> 
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="num">软件编号*</form:label></div>
                                <div class="col-md-4"><form:input path="num" class="validate[required]"/></div>
                                <div class="col-md-2"><form:label path="name">软件名称*</form:label></div>
                                <div class="col-md-4"><form:input path="name" class="validate[required]"/></div>
                            </div>                                                               
  							<div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="versions">版本*</form:label></div>
                                <div class="col-md-10"><form:input path="versions" class="validate[required]"/></div>
                            </div>     
							<div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="remark">备注</form:label></div>
                                <div class="col-md-10"><form:input path="remark" /></div>
                            </div>      

                            <div class="footer tar">
                                <button class="btn btn-primary center-block"> 修 改 </button>
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
</body>

</html>