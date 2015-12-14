<%@page import="com.cngc.utils.activiti.ProcessDefinitionCache,org.activiti.engine.RepositoryService,org.activiti.engine.RuntimeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    
    <title>运维记录管理--运维管理系统</title>

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
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-en.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/animatedprogressbar/animated_progressbar.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cleditor/jquery.cleditor.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
        
    <!-- <script type='text/javascript' src='../../../bp.yahooapis.com/2.4.21/browserplus-min.js'></script> -->

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
    
    <script type='text/javascript' src='${contextPath }/resources/js/cookies.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/myactions.js'></script>
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
    var ctx = "${contextPath}";
            $(document).ready(function () {
                $("#eventTable").dataTable();
                $(".header").load("${contextPath}/header");
                $(".menu").load("${contextPath}/menu", function () { $(".navigation > li:eq(7)").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons");
                $(".confirm").bind("click",function(){
                	if(!confirm("确定要执行该操作?"))
                		return false;
                });
                $(".dateISO").datepicker();
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
                    <li><a href="${contextPath }/Asset/list">运维记录管理</a> <span class="divider">></span></li>       
                    <li class="active">三员培训记录</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">             
				<div class="alert alert-danger hide">                
                    <h4>错误!</h4>请至少选择一项
                </div> 
                <div class="row">
                   <div class="col-md-3 clearfix" id="mails_navigation">                    
                        <div class="block-fluid sNavigation">
                            <ul>
                                <li><a href="${contextPath }/record/income"><span class="glyphicon glyphicon-arrow-right"></span> 机房人员出入</a><span class="arrow"></span></li>
                                <li class="active"><a href="${contextPath }/record/training"><span class="glyphicon glyphicon-user"></span> 三员培训</a><span class="arrow"></span></li>
                            </ul>
                        </div>                      
                    </div>
                    <div class="col-md-9">                    
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>记录列表</h1>  

                            <ul class="buttons"> 
                            	<li>
                                    <a href="#newForm" role="button" data-toggle="modal" class="isw-plus tipb" title="创建新记录"></a>
                                </li>                         
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                        <li><a href="javascript:void(0);" id="delBtn"><span class="isw-list"></span> 删除</a></li>
                                        <li><a href="#"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="eventTable">
                                <thead>
                                	<tr>
										<th>培训人</th>
										<th width="200px">培训单位</th>
										<th width="100px">证书</th>
										<th width="100px">证书编号</th>
										<th width="100px">有效期</th>
										<th width="60px">操作</th>
									</tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list}" var="training">
									<tr>
										<td>${training.userName }</td>
										<td>${training.company }</td>
										<td>${training.certification }</td>
										<td>${training.certificationNo }</td>
										<td><fmt:formatDate value="${training.expiryDate }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
										<td>
											<a href="${contextPath }/record/training/delete/${training.id}" class="confirm">删除</a>
										</td>
									</tr>
								</c:forEach>   
                                </tbody>
                            </table>
                        </div>
                    </div>  
                </div>
                <div class="dr"><span></span></div>
            </div>
            <!--workplace end-->
        </div>   
        <!-- modal form -->
        <div class="modal fade" id="newForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>三员培训记录</h4>
                    </div>
                    <form:form action="${contextPath}/record/training/save" commandName="training" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="userName">培训人：</form:label></div>
                                    <div class="col-md-9"><form:input path="userName"></form:input></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="company">培训单位：</form:label></div>
                                    <div class="col-md-9"><form:input path="company"></form:input></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="certification">证书：</form:label></div>
                                    <div class="col-md-9"><form:input path="certification"></form:input></div>
                                </div>                                                           
                            </div>                
                        </div>
                         <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="certificationNo">证书编号：</form:label></div>
                                    <div class="col-md-9"><form:input path="certificationNo"></form:input></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="expiryDate">有效期：</form:label></div>
                                    <div class="col-md-9"><form:input path="expiryDate" cssClass="dateISO"></form:input></div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" aria-hidden="true">提交</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</body>

</html>