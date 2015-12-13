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
    
    <title>配置管理--运维管理系统</title>

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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/treeview/bootstrap-treeview.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/cookies.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/myactions.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/charts.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/settings.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/faq.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-cms.js'></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
   		var ctx = "${contextPath}"; 
   		var propertiesdata = '${ci.propertiesData }';
   		var ciId = "${ci.id}";
   		
            $(document).ready(function () {
                $("#eventTable").dataTable();
                $(".header").load("${contextPath}/header");
                $(".menu").load("${contextPath}/menu", function () { $(".navigation > li:eq(3)").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons");

                if(propertiesdata!="")
                {
                	propertiesdata = $.parseJSON(propertiesdata);
	                $.each(propertiesdata, function(k,v){
	        			$("div[name='"+k+"']").html(v);
	        		});
                }
                pm_cms_initselectdialog('ci');
                pm_cms_getIncident();
                pm_cms_getChange();
            });
            function deleteConfirm()
            {
            	if(!confirm("确定要执行该操作?"))
            		return false;
            }

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
                    <li><a href="${contextPath }/Asset/list">配置管理</a> <span class="divider">></span></li>       
                    <li class="active">配置项信息</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">             

                <div class="row">
               	
               	  <div class="col-md-3">
                       <div class="headInfo">
                            <div class="toolbar nopadding-toolbar clear clearfix">
                                <div class="left text-info"><h4>配置项信息</h4></div>
                            </div>                                  
                        </div>
                         <div class="block-fluid ucard">                                                                
                            <ul class="rows">
                            <li>
                                <div class="title">名称:</div>
                                <div class="text"><c:if test="${empty ci.name }">-</c:if>${ci.name}</div>
                            </li>
                             <li>
                                <div class="title">物理位置:</div>
                                <div class="text"><c:if test="${empty ci.location }">-</c:if>${ci.location} </div>
                            </li>
                            <li>
                                <div class="title">使用部门:</div>
                                <div class="text"><c:if test="${empty ci.departmentInUse }">-</c:if>${ci.departmentInUse }</div>
                            </li> 
                            <li>
                                <div class="title">维护人:</div>
                                <div class="text"><c:if test="${empty ci.userInMaintenance }">-</c:if>${ci.userInMaintenance }</div>
                            </li>
                            <li>
                                <div class="title">服务提供商:</div>
                                <div class="text"><c:if test="${empty ci.serviceProvider }">-</c:if>${ci.serviceProvider }</div>
                            </li>
                            <li>
                                <div class="title">状态:</div>
                                <div class="text"><c:if test="${empty ci.status }">-</c:if>${ci.statusName }</div>
                            </li>
                            <li>
                                <div class="title">审核状态:</div>
                                <div class="text"><c:if test="${empty ci.reviewStatus }">-</c:if>${ci.reviewStatusName }</div>
                            </li>
                            <li>
                                <div class="title">删除状态:</div>
                                <div class="text"><c:if test="${empty ci.deleteStatus }">-</c:if>${ci.deleteStatusName }</div>
                            </li>
                            <li>
                                <div class="title">用途:</div>
                                <div class="text"><c:if test="${empty ci.purpose }">-</c:if>${ci.purpose }</div>
                            </li>
                            <li>
                                <div class="title">最近更新时间:</div>
                                <div class="text">
                                	<c:if test="${empty ci.lastUpdateTime }">-</c:if>
                                	<fmt:formatDate value="${ci.lastUpdateTime }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                                </div>
                            </li>   
                            </ul>                                                      
                        </div>                     
               
                    </div>
				<c:forEach items="${properties}" var="map">
                   <div class="col-md-3">
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>${map.key }</h1>
                            <ul class="buttons">        
                                <li class="toggle"><a href="#"></a></li>
                            </ul>                         
                        </div>
                                              
                        <div class="info">                                                                
                            <ul class="rows">
                            <c:forEach items="${map.value }" var="property"> 
	                            <li>
	                                <div class="title">${property.propertyName}:</div>
	                                <div class="text" id="${property.propertyId}" name="${property.propertyId}">-</div>
	                            </li>
                            </c:forEach>
                            </ul>                                                      
                        </div>    

                    </div>
                </c:forEach>  
                
               	</div>
            	
            	<div class="dr"><span></span></div>
            	
            	<div class="row">
	            	<div class="col-md-6">
	            		<div class="head clearfix">
	                        <div class="isw-attachment"></div>
	                    	<h1>关联关系</h1>
	                    </div>
	                    <div class="block-fluid tabs">
	                        <ul>
	                        	<c:forEach items="${relations }" var="categoryRelation">
                                <li><a href="#tabs-${categoryRelation.relation.relationId }">${categoryRelation.relation.relationName }</a></li>
                             	</c:forEach>
                            </ul> 
                            <c:forEach items="${relations }" var="categoryRelation">
                            <script>
		            			$(document).ready(function(){
		            				pm_cms_inittable('${categoryRelation.relation.relationId }');
		            			});         				
	            			</script>
                            <div id="tabs-${categoryRelation.relation.relationId }">
                            	<div style="height:300px;">
                                <table cellpadding="0" cellspacing="0" width="100%" class="table" id="table_${categoryRelation.relation.relationId}">
	                                <thead>
	                                    <tr>
	                                        <th>配置项名称</th>
	                                        <th  width="100">维护人</th>
	                                        <th width="60">状态</th>
	                                        <th width="40">操作</th>                                
	                                    </tr>
	                                </thead>
	                                <tbody>                                            
	                                </tbody>
	                            </table>                    
                            	</div>
	                            <div class="toolbar bottom-toolbar clearfix">
	                                <div class="left">
	                                    <div class="btn-group">
	                                        <button onclick="pm_cms_addRelations('${categoryRelation.relation.relationId}')" type="button" class="btn btn-sm btn-warning tip" title="新增"><span class="glyphicon glyphicon-plus glyphicon glyphicon-white"></span></button>
	                                    </div>                                
	                                </div>                            
	                                <div class="right">                                       
	                                    <ul class="pagination pagination-sm">
	                                        <li class="disabled"><a href="#">Prev</a></li>
	                                        <li class="disabled"><a href="#">1</a></li>
	                                        <li><a href="#">2</a></li>
	                                        <li><a href="#">Next</a></li>
	                                    </ul>                                        
	                                </div>                        
	                            </div> 
                            </div>  
                            </c:forEach>
	                    </div>
	            	</div>
   					<div class="col-md-6">
   						<div class="head clearfix">
                            <div class="isw-list"></div>
                            <h1>关联信息</h1>
                        </div>
                        <div class="block-fluid tabs">
	                        <ul>
	                        	<li><a href="#tabs-incident"> 事 件 </a></li>
	                       		<li><a href="#tabs-change"> 变 更 </a></li>
	                        </ul> 
	                        <div id="tabs-incident">
                            	<div style="height:300px;">
                                <table cellpadding="0" cellspacing="0" width="100%" class="table" id="incidentTable">
                                <thead>
                                    <tr>                                    
                                        <th>摘要</th>
                                        <th width="120px">申报时间</th>
                                        <th width="60px">状态</th>
                                    </tr>
                                </thead>
                                <tbody>
										
                                </tbody>
                            	</table>
                            	</div>
                            	<div class="toolbar bottom-toolbar clearfix">                         
	                                <div class="right">                                       
	                                    <ul class="pagination pagination-sm">
	                                        <li class="disabled"><a href="#">Prev</a></li>
	                                        <li class="disabled"><a href="#">1</a></li>
	                                        <li><a href="#">2</a></li>
	                                        <li><a href="#">Next</a></li>
	                                    </ul>                                        
	                                </div>                        
                           		 </div>
                            </div><!-- incident end -->
                            <div id="tabs-change">
                            	<div style="height:300px;">
                                <table cellpadding="0" cellspacing="0" width="100%" class="table" id="changeTable">
                                <thead>
                                    <tr>                                    
                                        <th>摘要</th>
                                        <th width="120px">申报时间</th>
                                        <th width="60px">状态</th>
                                    </tr>
                                </thead>
                                <tbody>
										
                                </tbody>
                            	</table>
                            	</div>
                            	<div class="toolbar bottom-toolbar clearfix">                         
	                                <div class="right">                                       
	                                    <ul class="pagination pagination-sm">
	                                        <li class="disabled"><a href="#">Prev</a></li>
	                                        <li class="disabled"><a href="#">1</a></li>
	                                        <li><a href="#">2</a></li>
	                                        <li><a href="#">Next</a></li>
	                                    </ul>                                        
	                                </div>                        
                           		 </div>
                            </div><!-- change end -->
                        </div>
   					</div>
            	</div><!-- row end -->
            </div>
            <!--workplace end-->
        </div>   

    </div>
</body>

</html>