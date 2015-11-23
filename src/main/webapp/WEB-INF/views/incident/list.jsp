<%@page import="com.cngc.utils.activiti.ProcessDefinitionCache,org.activiti.engine.RepositoryService,org.activiti.engine.RuntimeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>事件管理--运维管理系统</title>

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
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
            $(document).ready(function () {
                $("#eventTable").dataTable();
                $(".header").load("../header");
                $(".menu").load("../menu", function () { $(".navigation > li:eq(1)").addClass("active"); });
                $(".breadLine .buttons").load("../contentbuttons");
                $(".confirm").bind("click",function(){
                	if(!confirm("确定要执行该操作?"))
                		return false;
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
                    <li><a href="${contextPath }/Asset/list">事件管理</a> <span class="divider">></span></li>       
                    <li class="active">事件控制台</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">             
			<%
				RuntimeService runtimeService  = (RuntimeService)request.getAttribute("runtime");
				RepositoryService repositoryService = (RepositoryService)request.getAttribute("res");
				ProcessDefinitionCache.setRuntimeService(runtimeService);
				ProcessDefinitionCache.setRepositoryService(repositoryService);
			%>
				<div class="alert alert-danger hide">                
                    <h4>错误!</h4>请至少选择一项
                </div> 
                <div class="row">
                    <div class="col-md-9">                    
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>待处理事件</h1>  

                            <ul class="buttons">
                                <li>
                                    <a href="${contextPath }/incident/add" class="isw-text_document tipb" title="新事件"></a>
                                </li>                            
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                        <li><a href="#"><span class="isw-list"></span> 查看全部</a></li>
                                        <li><a href="#"><span class="isw-ok"></span> 查看未指派</a></li>
                                        <li><a href="#"><span class="isw-minus"></span> 查看已超期事件</a></li>
                                        <li><a href="#"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table cellpadding="0" cellspacing="0" width="100%" class="table" id="eventTable">
                                <thead>
                                    <tr>
                                        <th width="40px"><input type="checkbox" name="checkall"/></th>
                                        <th width="80px">事件ID</th>
                                        <th width="80px">父事件ID</th>
                                        <th>摘要</th>
                                        <th width="100px">申请人</th>
                                        <th width="80px">优先级</th>
                                        <th width="80px">状态</th>
                                        <th width="100px">受派者</th>
                                        <th width="120px">目标日期</th>                                    
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list }" var="incident">
                                    <tr>
                                        <td><input type="checkbox" name="checkbox"/></td>
                                        <td><a href="${contextPath }/incident/deal/${incident.id}">${incident.id }</a></td>
                                        <td></td>
                                        <td>${incident.abs }</td>
                                        <td>${incident.applyUser }</td>
                                        <td>${incident.critical }</td>
                                        <td>${incident.status }</td>
                                        <td>${incident.currentDelegateUser }</td>
                                        <td><fmt:formatDate value="${incident.applyTime }" pattern="MM/dd HH:mm:ss" /></td>
                                    </tr>
                                   </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>  

                    <div class="col-md-3">
                        <div class="head clearfix">
                            <div class="isw-list"></div>
                            <h1>操作</h1>
                        </div>
                        <div class="block-fluid accordion">
                            <h3>计数</h3>
                            <div>
                                <ul>
                                    <li><a href="#">打开</a><span style="float:right;margin-right:20px;">9</span></li>
                                    <li><a href="#">未指派</a><span style="float:right;margin-right:20px;">0</span></li>
                                    <li><a href="#">未确认</a><span style="float:right;margin-right:20px;">0</span></li>
                                    <li><a href="#">已超期</a><span style="float:right;margin-right:20px;">1</span></li>
                                </ul>                                              
                            </div>

                            <h3>功能</h3>
                            <div>
                                <ul>
                                    <li><a href="#">创建新事件</a></li>
                                    <li><a href="#" id="lnk_knowledge">知识库</a></li>
                                </ul>                                                
                            </div>                     

                        </div>

                        <div class="head clearfix">
                            <div class="isw-edit"></div>
                            <h1>广播消息</h1>
                            <ul class="buttons">                            
                                <li>
                                    <a href="#" class="isw-text_document tipb" title="新消息"></a>
                                </li>                            
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作"></a>
                                    <ul class="dd-list">
                                        <li><a href="#"><span class="isw-list"></span> 查看全部</a></li>
                                        <li><a href="#"><span class="isw-edit"></span> 新消息</a></li>
                                        <li><a href="#"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>                        
                        </div>
                        <div class="block news scrollBox">

                            <div class="scroll" style="height: 240px;">

                                <div class="item">
                                    <a href="#" id="lnk_message">打印服务升级.</a>
                                    <p>[事件E4059560] 6月12日 9:00-12:00 对打印服务器进行升级，在此过程中可能影响到终端打印.</p>
                                    <span class="date">2015-6-12 08:23</span>
                                    <div class="controls">                                    
                                        <a href="#" class="glyphicon glyphicon-trash tip" title="已阅"></a>
                                    </div>
                                </div>

                                <div class="item">
                                    <a href="#">打印服务升级.</a>
                                    <p>[变更C495859] 6月12日 9:00-12:00 对打印服务器进行升级，在此过程中可能影响到打印.</p>
                                    <span class="date">2012.6.12 08:23</span>
                                    <div class="controls">                                    
                                        <a href="#" class="glyphicon glyphicon-trash tip" title="已阅"></a>
                                    </div>
                                </div>

                                <div class="item">
                                    <a href="#">打印服务升级.</a>
                                    <p>6月12日 9:00-12:00 对打印服务器进行升级，在此过程中可能影响到打印.</p>
                                    <span class="date">2012.6.12 08:23</span>
                                    <div class="controls">                                    
                                        <a href="#" class="glyphicon glyphicon-trash tip" title="已阅"></a>
                                    </div>
                                </div>                           

                                <div class="item">
                                    <a href="#">打印服务升级.</a>
                                    <p>6月12日 9:00-12:00 对打印服务器进行升级，在此过程中可能影响到打印.</p>
                                    <span class="date">2012.6.12 08:23</span>
                                    <div class="controls">                                    
                                        <a href="#" class="glyphicon glyphicon-trash tip" title="已阅"></a>
                                    </div>
                                </div>                         

                            </div>

                        </div>
                    </div> 
                </div>
                
                
            </div>
            <!--workplace end-->
        </div> 
    </div>
</body>

</html>