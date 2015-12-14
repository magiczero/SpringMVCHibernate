<%@page import="com.cngc.utils.activiti.ProcessDefinitionCache,org.activiti.engine.RepositoryService,org.activiti.engine.RuntimeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>知识库管理--运维管理系统</title>

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
    
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/cookies.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/myactions.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/settings.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-knowledge.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
            $(document).ready(function () {
                $(".header").load("${contextPath}/header");
                $(".menu").load("${contextPath}/menu", function () { $(".navigation > li:eq(8)").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons");
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
                    <li><a href="${contextPath }/knowledge/list">知识库管理</a> <span class="divider">></span></li>       
                    <li class="active">知识库</li>
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
                	<div class="col-md-3">
                		 <div class="headInfo">
                            <div class="toolbar nopadding-toolbar clear clearfix">
                                <div class="left"><h4>知识</h4></div>

                                <div class="right btn-group">
                                    <button title="" class="btn btn-sm btn-primary tipb" type="button" data-original-title="操作历史" id="view_deal"><span class="glyphicon glyphicon-eye-close glyphicon glyphicon-white"></span></button>
                                    <button title="" class="btn btn-sm btn-primary tipb" type="button" data-original-title="关联对象" id="lnk_assets"><span class="glyphicon glyphicon-info-sign glyphicon glyphicon-white"></span></button>
                                    <button title="" class="btn btn-sm btn-primary tipb" type="button" data-original-title="附件" id="lnk_uploads"><span class="glyphicon glyphicon-download-alt glyphicon glyphicon-white"></span></button>
                                </div>
                            </div>                                  
                        </div>
                         <div class="info">                                                                
                            <ul class="rows">
                            <li>
                                <div class="title">知识编号:</div>
                                <div class="text">${knowledge.id }</div>
                            </li>
                            <li>
                                <div class="title">知识分类:</div>
                                <div class="text">${knowledge.categoryName }</div>
                            </li>
                            <li>
                                <div class="title">知识提交者:</div>
                                <div class="text">${knowledge.applyUserName }</div>
                            </li>
                            <li>
                                <div class="title">状态:</div>
                                <div class="text">${knowledge.statusName }</div>
                            </li>
                            <li>
                                <div class="title">提交日期:</div>
                                <div class="text"><fmt:formatDate value="${knowledge.applyTime }" pattern="yyyy-MM-dd HH:mm:ss" /></div>
                            </li>   
                            </ul>                                                      
                        </div> 
                	</div>
                	<div class="col-md-9">
                		
                    <div class="headInfo">
                             <h4 class="text-info">${knowledge.title }</h4>
                            <div class="arrow_down"></div>
                    </div>
                    <div class="block news">
                        <div id="scroll_knowledge" style="height:400px;">
                            <div class="actions text-right" style="margin-right:10px;">
                                    <div class="btn-group">
                                        <button class="btn btn-default btn-sm" ><span class="glyphicon glyphicon-plus glyphicon glyphicon-white"></span> 阅读${knowledge.hits }次</button>
                                        <button class="btn btn-default btn-sm" ><span class="glyphicon glyphicon-envelope glyphicon glyphicon-white"></span> 引用*次</button>
                                        <button class="btn btn-default btn-sm" ><span class="glyphicon glyphicon-share-alt glyphicon glyphicon-white"></span> 附件</button>
                                    </div>
                            </div>
                            <div><h4><span class="label label-info">故障描述</span></h4></div>
                            ${knowledge.desc }
                             <div><h4><span class="label label-success">解决方案</span></h4></div>
                            ${knowledge.solution }
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