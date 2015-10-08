<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>服务器硬盘列表</title>

    <link href="${contextPath }/resources/css/stylesheets.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
        <link href="css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    
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
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-en.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/animatedprogressbar/animated_progressbar.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cleditor/jquery.cleditor.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/cookies.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/actions.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/charts.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/mail.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/settings.js'></script>  
    <script type="text/javascript">
    $(document).ready(function () {
        $("#netcard").validationEngine();
       
    });
    </script> 
</head>
<body>
 <div class="wrapper">
  	<div class="breadLine">

            </div>

            <div class="workplace">          
                
                <div class="page-header">
                    <h1>网卡 <small>新建</small></h1>
                </div>  
                
                <div class="row">
					<c:url var="addAction" value="/networkcard/save" ></c:url>
                    <form:form action="${addAction}" commandName="netcard">
					<form:hidden path="server.id" value="${server.id }"/>
<div class="col-sm-1"></div>
                    <div class="col-sm-10" id="mails">
                        <div class="block-fluid without-head">
                            <div class="toolbar nopadding-toolbar clearfix">
                                <h4>填写网卡信息</h4>
                            </div>                                                 
                            <div class="row-form clearfix">
                                <div class="col-sm-4"><form:label path="cardType.id">网卡类型</form:label></div>
                                <div class="col-sm-8"><form:select path="cardType.id" multiple="false" items="${types }" ></form:select></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-sm-4"><form:label path="macAdd">mac地址*</form:label></div>
                                <div class="col-sm-8"><form:input path="macAdd" class="validate[required]" /></div>
                            </div>   
                            <div class="row-form clearfix">
                                <div class="col-sm-4"><form:label path="internetProtocol">IP*</form:label></div>
                                <div class="col-sm-8"><form:input path="internetProtocol" class="validate[required]"/></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-sm-4"><form:label path="subnetMask">子网掩码*</form:label></div>
                                <div class="col-sm-8"><form:input path="subnetMask" class="validate[required]"/></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-sm-4"><form:label path="remark">备注</form:label></div>
                                <div class="col-sm-8"><form:input path="remark" /></div>
                            </div> 
                            <div class="toolbar clear clearfix">
                                <div class="right">                                
                                    <button type="submit" class="btn btn-sm btn-warning"><span class="glyphicon glyphicon-ok glyphicon glyphicon-white"></span></button>                                
                                </div>
                            </div>                         
                        </div>    

                    </div> <div class="col-sm-1"></div>               
					</form:form>
                </div>            

            </div>
 </div>
</body>
</html>