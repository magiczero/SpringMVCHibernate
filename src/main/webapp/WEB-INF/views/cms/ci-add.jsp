<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>配置管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/stylesheets.css" rel="stylesheet" type="text/css" />
    <!-- <link type="text/css" rel="stylesheet" href="${contextPath }/resources/plupload/css/jquery-ui.min.css" /> -->
	<!-- <link type="text/css" rel="stylesheet" href="${contextPath }/resources/js/plugins/plupload/jquery.plupload.queue/css/jquery.plupload.queue.css" /> -->
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
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
    
    <!-- 
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.gears.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.silverlight.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.flash.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.browserplus.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.html4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.html5.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/jquery.plupload.queue/jquery.plupload.queue.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/i18n/cn.js'></script>
     -->
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
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
            $(document).ready(function () {
            	$(".header").load("${contextPath }/header");
                $(".menu").load("${contextPath }/menu", function () { $(".navigation > li:eq(3)").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath }/contentbuttons");
                //$("#myeditor").cleditor({width:"100%", height:"300px"});
                $("#btn_sel_category").bind("click",selCategory);
                $(".dateISO").datepicker();
            });
            
            function selCategory(){
            	$("#categoryDialog").modal('show');
            	/* var userid = $(this).parents('tr').find('.userid').text();
            	$.getJSON(ctx + '/user/get/' + userid,function(data){
            		$("#dialogTitle").html("修改用户信息");
            		$("#name").attr('value',data.user.name);
            		$("#userform_id").attr('value',data.user.id);
            		$("#username").attr('value',data.user.username);
            		$("#username").attr('disabled',true);
            		$("#userFormDialog").modal('show');
            	}); */
            }
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
                    <li><a href="#">配置管理</a> <span class="divider">></span></li>         
                    <li class="active">配置项维护</li>
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
                        <c:url var="addAction" value="/cms/ci/save" ></c:url>
						<form:form action="${addAction}" commandName="ci" method="post">
                        <div class="block-fluid">                        
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="name">名称:</form:label></div>
                                <div class="col-md-10"><form:input path="name"></form:input></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="categoryCode">分类:</form:label></div>
                                <div class="col-md-4"><form:input path="categoryCode"></form:input></div>
                                 <div class="col-md-2"><form:label path="securityNo">保密编号:</form:label></div>
                                <div class="col-md-4"><form:input path="securityNo"></form:input></div>
                            </div> 
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="incidence">影响范围:</form:label></div>
                                <div class="col-md-4"><form:input path="incidence"></form:input></div>
                                <div class="col-md-2"><form:label path="location">物理位置:</form:label></div>
                                <div class="col-md-4"><form:input path="location"></form:input></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="departmentInUse">使用部门:</form:label></div>
                                <div class="col-md-4"><form:input path="departmentInUse"></form:input></div>
                                <div class="col-md-2"><form:label path="userInMaintenance">维护人:</form:label></div>
                                <div class="col-md-4"><form:input path="userInMaintenance"></form:input></div>
                            </div> 
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="serviceStartTime">服务开始时间:</form:label></div>
                                <div class="col-md-4"><form:input path="serviceStartTime" cssClass="dateISO"></form:input></div>
                                <div class="col-md-2"><form:label path="serviceEndTime">服务结束时间:</form:label></div>
                                <div class="col-md-4"><form:input path="serviceEndTime" cssClass="dateISO"></form:input></div>
                            </div>                              
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="serviceProvider">服务提供商:</form:label></div>
                                <div class="col-md-4"><form:input path="serviceProvider"></form:input></div>
                                <div class="col-md-2"><form:label path="serviceProviderContact">联系方式:</form:label></div>
                                <div class="col-md-4"><form:input path="serviceProviderContact"></form:input></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="producer">厂商:</form:label></div>
                                <div class="col-md-4"><form:input path="producer"></form:input></div>
                                <div class="col-md-2"><form:label path="expirationTime">截至日期:</form:label></div>
                                <div class="col-md-4"><form:input path="expirationTime" cssClass="dateISO"></form:input></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="purpose">用途:</form:label></div>
                                <div class="col-md-4"><form:input path="purpose"></form:input></div>
                                <div class="col-md-2"><form:label path="remark">备注:</form:label></div>
                                <div class="col-md-4"><form:input path="remark" ></form:input></div>
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
        
         <!-- 新建用户 modal form -->
        <div class="modal fade" id="categoryDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4 id="dialogTitle">选择分类</h4>
                    </div>

                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">用户名:</div>
                                    <div class="col-md-9"><input id="username" name="username" type="text" /></div>
                                </div>                                                           
                            </div>                
                        </div>

                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" aria-hidden="true">保存</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    <input type="hidden" id="userform_id"  name="userform_id" value="0" /> 
                </div>
            </div>
        </div>
    	<!-- 新建用户 end from -->
    	
    </div>
    
</body>

</html>