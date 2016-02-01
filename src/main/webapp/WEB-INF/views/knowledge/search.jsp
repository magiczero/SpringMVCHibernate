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
            $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_0").addClass("active"); });
            $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
            $(".confirm").bind("click",function(){
               	if(!confirm("确定要执行该操作?"))
               		return false;
            });
            var keyword = pm_getQueryString("keyword");
            if(keyword!=null)
            {
               	$("input[name='keyword']").attr("value",keyword);
              	pm_knowledge_highlight(keyword);
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
                    <li><a href="${contextPath }/knowledge/search">知识库管理</a> <span class="divider">></span></li>       
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
                
                
                    <div class="col-md-8">   
                    <form action="${contextPath }/knowledge/search">  
                        <div class="headInfo">
                            <div class="input-group">
                            	
                                <input type="text" name="keyword" placeholder="关键字.." class="form-control"/>
                                <div class="input-group-btn">
                                    <!--  button class="btn btn-success" type="button">搜索知识</button-->
                                    <button class="btn btn-primary">搜索知识 </button>
                                </div>
                                
                            </div>                                           
                            <div class="arrow_down"></div>
                        </div>
                        </form>
                        <div class="block-fluid">
                            <div class="toolbar clearfix">
                                <div class="left">
                                    <div id="faqSearchResult" class="note"></div>
                                </div>
                                <div class="right">
                                    <div class="btn-group">
                                        <button class="btn btn-default btn-sm tip" id="faqOpenAll" title="显示摘要"><span class="glyphicon glyphicon-chevron-down glyphicon glyphicon-white"></span></button>
                                        <button class="btn btn-default btn-sm tip" id="faqCloseAll" title="关闭摘要"><span class="glyphicon glyphicon-chevron-up glyphicon glyphicon-white"></span></button>
                                        <button class="btn btn-default btn-sm tip" id="faqRemoveHighlights" title="去除高亮显示"><span class="glyphicon glyphicon-remove glyphicon glyphicon-white"></span></button>
                                    </div>
                                </div>
                            </div>                                                        
                            <div class="faq">
                            	<c:if test="${count ==0}">
                            	未查询到相应知识项。
                            	</c:if>
                            	<c:forEach items="${list}" var="knowledge">
                                <div class="item" id="faq-1">
                                    <div class="title"><a href="${contextPath }/knowledge/detail/${knowledge.id}">${knowledge.title }</a></div>
                                    <div class="text"><p>${knowledge.desc }</p></div>
                                </div>
                                </c:forEach>
                            </div>
                            <div class="toolbar bottom-toolbar clearfix">
                                <tag:paginate max="5" offset="${offset}" count="${count}" uri="${url }" />
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4"> 
                        <div class="block-fluid without-head">
                            <div class="toolbar nopadding-toolbar clear clearfix">
                                <h4>近期被阅读的知识</h4>
                            </div>                            
                            
                            <ul class="list nol" id="faqListController">
                            	<c:forEach items="${lastread}" var="knowledge">
                                	<li><a href="${contextPath }/knowledge/detail/${knowledge.id}">${knowledge.title }</a> 
                                	<span style="color:#999999;font-style:italic;">已阅读 ${knowledge.hits} 次</span></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>                    
                
                </div>
            </div>
            <!--workplace end-->
        </div> 
    </div>
</body>

</html>