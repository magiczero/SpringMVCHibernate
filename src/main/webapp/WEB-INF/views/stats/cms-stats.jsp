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
    
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
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
                $(".header").load("${contextPath}/header?t="+pm_random());
                $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
                $(".confirm").bind("click",function(){
                	if(!confirm("确定要执行该操作?"))
                		return false;
                });
                $(".dateISO").datepicker(); 
                var columnCategory,rowCategory;
                columnCategory = pm_getQueryString("columnCategory");
                rowCategory = pm_getQueryString("rowCategory");
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
                
                if(rowCategory!=null)
                	$("#rowCategory option[value='"+rowCategory+"']").attr("selected", "selected");
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
                    <li class="active">自定义二维统计</li>
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
                            <h1>资产配置统计</h1>
                        </div>
                        <div class="block-fluid accordion">
                            <h3>统计报表</h3>
                            <div>
                                 <ul>
                                 	<li><a href="${contextPath }/stats/cms/count">配置项统计</a></li>
                                 	<li><a href="${contextPath }/stats/cms/category">配置项分类统计</a></li>
                                    <li><a href="${contextPath }/stats/cms/performance">工程师维护统计</a></li>
                                </ul>                                               
                            </div>

                            <h3>自定义统计</h3>
                            <div class="active">
                                <ul>
                                    <li><a href="${contextPath }/stats/cms1">自定义一维统计</a></li>
                                    <li><a href="${contextPath }/stats/cms">自定义二维统计</a></li>
                                </ul>                                                
                            </div>                     
                        </div>
                    </div> 
                    <div class="col-md-9">
                        <div class="block-fluid without-head">
                			<div class="toolbar nopadding-toolbar clearfix">
                                <h4>统计项设置</h4>
                            </div>
                            	<form action="${contextPath }/stats/cms">
	                            <div class="row-form clearfix">
	                                <div class="col-md-2">列分类</div>
	                                <div class="col-md-4">
	                                	<select name="columnCategory" id="columnCategory">
                                            <option value="CODE_CI_STATUS">状态</option>
                                            <option value="CODE_REVIEW">审核状态</option>
                                            <option value="CODE_DELETE">删除状态</option>
                                    	</select>
                                    </div>
	                                <div class="col-md-2">行分类</div>
	                                <div class="col-md-4">
	                                	<select name="rowCategory" id="rowCategory">
                                            <option value="CODE_CI_STATUS">状态</option>
                                            <option value="CODE_REVIEW">审核状态</option>
                                            <option value="CODE_DELETE">删除状态</option>
                                            <option value="CODE_CI_CATEGORY">分类</option>
                                            <option value="USER_CI_ENGINEER">工程师</option> 
                                    	</select>
	                                </div>
	                            </div> 
	                            <div class="toolbar nopadding-toolbar clearfix">
                                	<h4>过滤条件设置</h4>
                           		</div>
	                            <div class="row-form clearfix">
	                                <div class="col-md-2">资产配置状态</div>
	                                <div class="col-md-4">
										<select name="status" id="status">
										<option value="00">全部</option>
										<c:forEach items="${status }" var="code">
											<option value="${code.code }">${code.codeName }</option>
										</c:forEach>
										</select>
									</div>
	                            </div>
	                            <div class="footer">
	                            	<button class="btn btn-primary center-block"> 统 计 </button>
	                            </div>
	                            </form>
                		</div>
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>统计结果</h1>                            
                        </div>
                        <div class="block-fluid">
                            <table class="table">
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
                                        		<span style="color:#a0a0a0;">0</span>
                                        	</c:if>
                                        	<c:if test="${ not empty stat[rowcode.key][columncode.key] }">
                                        		<strong><span class="text-info">${stat[rowcode.key][columncode.key] }</span></strong>
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