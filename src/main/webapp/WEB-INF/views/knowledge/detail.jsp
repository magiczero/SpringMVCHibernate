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
    <script type='text/javascript' src='${contextPath }/resources/js/pm-knowledge.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
        $(document).ready(function () {
            $(".header").load("${contextPath}/header?t="+pm_random());
            $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
            $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
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
                    <li><a href="${contextPath }/knowledge/search">知识库管理</a> <span class="divider">></span></li>       
                    <li class="active">知识详细</li>
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
                                <div class="left"><h4>知识编号:${knowledge.id }</h4></div>
                            </div>                                  
                        </div>
                         <div class="info">                                                                
                            <ul class="rows">
                            <li>
                                <div class="title">知识分类:</div>
                                <div class="text">${knowledge.categoryName }</div>
                            </li>
                            <li>
                                <div class="title">关键字:</div>
                                <div class="text">${knowledge.keyword==""?"-":knowledge.keyword }</div>
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
                                <div class="title">提交时间:</div>
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
                                        <button class="btn btn-default btn-sm" ><span class="glyphicon glyphicon-plus glyphicon glyphicon-white"></span> 已阅读 ${knowledge.hits } 次</button>
                                        <!--
                                        <button class="btn btn-default btn-sm" ><span class="glyphicon glyphicon-envelope glyphicon glyphicon-white"></span> 引用*次</button>
                                        <button class="btn btn-default btn-sm" ><span class="glyphicon glyphicon-share-alt glyphicon glyphicon-white"></span> 附件</button>
                                        -->
                                    </div>
                            </div>
                            <div><h3><span class="label label-success">故障描述</span></h3></div>
                            <div style="padding:10px 10px 0px 20px;">${knowledge.desc }</div>
                            <div><h3><span class="label label-success">解决方案</span></h3></div>
                            <div style="padding:10px 10px 0px 20px;">${knowledge.solution }</div>
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