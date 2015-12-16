<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.cngc.pm.model.Authority, com.cngc.pm.model.AuthReso, com.cngc.pm.model.Resources, java.util.List" %>
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
    
    <title>权限管理</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
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
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/animatedprogressbar/animated_progressbar.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cleditor/jquery.cleditor.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    
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
    <script type='text/javascript' src='${contextPath }/resources/js/settings.js'></script>    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
            $(document).ready(function () {
            	$(".header").load("${contextPath }/header");
            	$(".menu").load("${contextPath }/menu", function() {$("#node_${moduleId}").addClass("active");});
                $(".breadLine .buttons").load("${contextPath }/contentbuttons");
                
                $("#authority").validationEngine({promptPosition : "topLeft", scroll: true});
                
                if($("#resos").length > 0){
                    $("#resos").multiSelect({
                        selectableHeader: "<div class='multipleselect-header'>未选资源</div>",
                        selectedHeader: "<div class='multipleselect-header'>已选择</div>"
                    });
                    $('#multiselect-selectAll').click(function(){
                        $('#resos').multiSelect('select_all');
                        return false;
                    });
                    $('#multiselect-deselectAll').click(function(){
                        $('#resos').multiSelect('deselect_all');
                        return false;
                    });
                 }
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
                    <li><a href="#">系统管理</a> <span class="divider">></span></li>         
                    <li class="active">权限管理</li>
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
                            <h1>修改权限信息</h1>
                        </div><c:url var="actionUrl" value="/authority/save" ></c:url>
						<form:form action="${actionUrl}" commandName="authority">
						<form:hidden path="id"/>
						<form:hidden path="enable"/>
						<form:hidden path="sys" />
                        <div class="block-fluid">                        
                          <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="authorityName">权限名称*</form:label></div>
                                <div class="col-md-10"><form:input path="authorityName" class="validate[required,minSize[2],maxSize[30]] text-input" />
                                </div></div>
                          <div class="row clearfix"><br/>
                          <div class="col-md-1"></div>
                                <div class="col-md-10">
									<%-- <form:select multiple="true" path="setResources" class="validate[required] multiselect">
												<c:forEach items="${listResources }" var="res1"><option value="${res1.id }" <c:forEach items="${authority.setResources }" var="res2"><c:if test="${res2.id == res1.id }">selected="true"</c:if></c:forEach>>${res1.name }</option></c:forEach>
											</form:select>--%>
                            		<select multiple id="resos" name="resos" class="validate[required] multiselect">
										
										<%
											@SuppressWarnings("unchecked")
											List<Resources> list = (List<Resources>)request.getAttribute("listResources");
											Authority auth = (Authority)request.getAttribute("authority");
											for(Resources resources : list) {
												String option="<option value=\""+resources.getId()+"\" ";
												for(AuthReso ar : auth.getAuthResos()) {
													if(ar.getResources().getId() == resources.getId()) {
														option+="selected=\"selected\"";
													}
												}
												option+=" >"+resources.getName()+"</option>";
												out.write(option);
											}
										%>
										<%--<c:forEach items="${listResources }" var="resources"><option value='${resources.id }' 
										<c:forEach items="${authority.authResos }" var="ar">
										<c:choose>
											<c:if test="${ar.resources.id == resources.id }">selected='selected'</c:if>
										</c:choose>
										>${resources.name }</option>
										</c:forEach></c:forEach> --%>
										
									</select>
									
				                    <div class="btn-group">
				                    	<button class="btn btn-default btn-xs" id="multiselect-selectAll" type="button">全选</button>
				                        <button class="btn btn-default btn-xs" id="multiselect-deselectAll" type="button">全不选</button>
				                    </div>                          
				                        
								</div>  <div class="col-md-1"></div>  
                                </div>  
							<div class="row-form clearfix">		
                                <div class="col-md-2"><form:label path="authorityDesc">说明*</form:label></div>
                                <div class="col-md-10"><form:textarea path="authorityDesc"></form:textarea>
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