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
    
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/cookies.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/myactions.js'></script>
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
    	var data = [];
    	var data_index=0;
            $(document).ready(function () {
                $(".header").load("${contextPath}/header");
                $(".menu").load("${contextPath}/menu", function () { $(".navigation > li:eq(1)").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons");
                $(".confirm").bind("click",function(){
                	if(!confirm("确定要执行该操作?"))
                		return false;
                });
                $(".dateISO").datepicker(); 
                var columnCategory,rowCategory;
                columnCategory = pm_getQueryString("columnCategory");
                rowCategory = pm_getQueryString("rowCategory");
                
                
                if(columnCategory!=null)
                	$("#columnCategory option[value='"+columnCategory+"']").attr("selected", "selected");
                
                if(rowCategory!=null)
                	$("#rowCategory option[value='"+rowCategory+"']").attr("selected", "selected");
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
                    <li><a href="${contextPath }/incident/list">事件管理</a> <span class="divider">></span></li>       
                    <li class="active">事件统计</li>
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
                	                	<div class="block-fluid without-head">
                            <ul class="sList" id="selectable_1">
                                <li>本月按事件分类统计</li>
                                <li>本季度按事件分类统计</li>
                                <li>本年度按事件分类统计</li>
                                <li>本月故障率统计</li>
                                <li>本月工作绩效统计</li>
                                <li>高级统计设置</li>
                            </ul>                                                
                        </div>
                	</div>
                    <div class="col-md-9">
                        <div class="block-fluid without-head">
                			<div class="toolbar nopadding-toolbar clearfix">
                                <h4>统计项设置</h4>
                            </div>
                            	<form action="${contextPath }/incident/mystats">
	                            <div class="row-form clearfix">
	                                <div class="col-md-2">列分类</div>
	                                <div class="col-md-4">
	                                	<select name="columnCategory" id="columnCategory">
                                            <option value="CODE_INFLUENCE">影响度</option>
                                            <option value="CODE_CRITICAL">紧急度</option>
                                            <option value="CODE_PRIORITY">优先级</option>
                                            <option value="CODE_STATUS">状态</option> 
                                            <option value="USER_ID">用户</option>
                                    	</select>
                                    </div>
	                                <div class="col-md-2">行分类</div>
	                                <div class="col-md-4">
	                                	<select name="rowCategory" id="rowCategory">
                                            <option value="CODE_INFLUENCE">影响度</option>
                                            <option value="CODE_CRITICAL">紧急度</option>
                                            <option value="CODE_PRIORITY">优先级</option>
                                            <option value="CODE_STATUS">状态</option>
                                            <option value="USER_ID">用户</option> 
                                    	</select>
	                                </div>
	                            </div> 
	                            <div class="footer tac">
	                            	<button class="btn btn-primary"> 统 计 </button>
	                            </div>
	                            </form>
                		</div>
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>统计结果</h1>                            
                        </div>
                        <div class="block-fluid">
                            <table cellpadding="0" cellspacing="0" width="100%" class="table">
                                <thead>
                                    <tr>                                    
                                        <th></th>
                                        <c:forEach items="${stat.column }" var="code">
                                        	<th>${code.value }</th>
                                        </c:forEach>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${stat.row }" var="rowcode">
                                	<tr>
                                    	<td>${rowcode.value }</td>
                                    	
                                    	<c:forEach items="${stat.column }" var="columncode">
                                        	<td>
                                        	<c:if test="${ empty stat[rowcode.key][columncode.key] }">
                                        		0
                                        	</c:if>
                                        	<c:if test="${ not empty stat[rowcode.key][columncode.key] }">
                                        		${stat[rowcode.key][columncode.key] }
                                        	</c:if>
                                        	</td>
                                        </c:forEach>
                                	</tr>
                                	</c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!--workplace end-->
        </div> 
    </div>
</body>

</html>