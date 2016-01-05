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
    
    <title>报表与统计管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/stylesheets.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
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
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
            $(document).ready(function () {
                $(".header").load("${contextPath}/header?t="+pm_random());
                $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $(".navigation > li:eq(9)").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
                $(".confirm").bind("click",function(){
                	if(!confirm("确定要执行该操作?"))
                		return false;
                });
                $(".dateISO").datepicker(); 
                var columnCategory,rowCategory;
                columnCategory = pm_getQueryString("columnCategory");
                var starttime,endtime;
                starttime = pm_getQueryString("startTime");
                endtime = pm_getQueryString("endTime");
                var status;
                status = pm_getQueryString("status");
                
                if(starttime!=null)
                	$("input[name='startTime']").attr("value",starttime);
                else
                	$("input[name='startTime']").attr("value",new Date().format("yyyy") +"-01-01");
                
                if(endtime!=null)
                	$("input[name='endTime']").attr("value",endtime);
                else
                	$("input[name='endTime']").attr("value",new Date().format("yyyy-MM-dd"));
                
                if(columnCategory!=null)
                	$("#columnCategory option[value='"+columnCategory+"']").attr("selected", "selected");
                if(status!=null)
                	$("#status option[value='"+status+"']").attr("selected", "selected");
                
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
                    <li><a href="#">报表与统计管理</a> <span class="divider">></span></li>       
                    <li class="active">知识统计</li>
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
                        <div class="head clearfix">
                            <div class="isw-list"></div>
                            <h1>知识统计</h1>
                        </div>
                        <div class="block-fluid accordion">
                             <h3>统计报表</h3>
                            <div>
                                 <ul>
                                 	<li><a href="${contextPath }/stats/knowledge/count">知识总量统计</a></li>
                                 	<li><a href="${contextPath }/stats/knowledge/category">知识分类统计</a></li>
                                    <li><a href="${contextPath }/stats/knowledge/performance">工程师绩效统计</a></li>
                                </ul>                                               
                            </div>

                            <h3>自定义统计</h3>
                            <div class="active">
                                <ul>
                                    <li><a href="${contextPath }/stats/knowledge1">自定义一维统计</a></li>
                                    <li><a href="${contextPath }/stats/knowledge">自定义二维统计</a></li>
                                </ul>                                                
                            </div>                    

                        </div>
                    </div> 
                    <div class="col-md-9">
                        <div class="block-fluid without-head">
                			<div class="toolbar nopadding-toolbar clearfix">
                                <h4>统计项设置</h4>
                            </div>
                            	<form action="${contextPath }/stats/knowledge">
	                            <div class="row-form clearfix">
	                                <div class="col-md-2">分类</div>
	                                <div class="col-md-4">
	                                	<select name="columnCategory" id="columnCategory">
                                            <option value="CODE_KNOWLEDGE_STATUS">状态</option>
                                            <option value="CODE_KNOWLEDGE_CATEGORY">知识分类</option>
                                            <option value="USER_KNOWLEDGE_ENGINEER">工程师</option> 
                                            <option value="DATE_MONTH">月份</option>
                                            <option value="DATE_YEAR">年度</option>
                                    	</select>
	                                </div>
	                            </div> 
	                            <div class="toolbar nopadding-toolbar clearfix">
                                	<h4>过滤条件设置</h4>
                           		</div>
                           		<div class="row-form clearfix">
	                                <div class="col-md-2">开始时间</div>
	                                <div class="col-md-4"><input type="text" name="startTime" class="dateISO"/></div>
	                                <div class="col-md-2">截至时间</div>
	                                <div class="col-md-4"><input type="text" name="endTime" class="dateISO"/></div>
	                            </div>
	                            <div class="row-form clearfix">
	                                <div class="col-md-2">知识状态</div>
	                                <div class="col-md-4">
										<select name="status" id="status">
										<option value="00">全部</option>
										<c:forEach items="${status }" var="code">
											<option value="${code.code }">${code.codeName }</option>
										</c:forEach>
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
                                        <th>统计项</th>
                                        <th>总数</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${stat.column }" var="code">
                                      	<tr>
                                      	<td>${code.value }</td>
                                      	<td>
                                      		<c:if test="${ empty stat.counts[code.key] }">
                                        		<span style="color:#a0a0a0;">0</span>
                                        	</c:if>
                                        	<c:if test="${ not empty stat.counts[code.key] }">
                                        		<strong><span class="text-info">${stat.counts[code.key] }</span></strong>
                                        	</c:if>
                                      	</td>
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