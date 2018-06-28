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
    
    <title>台账详细信息--运维管理系统</title>

    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href='${contextPath }/resources/js/plugins/jstree/jquery.treeview.css' rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.edit.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.async.js'></script>
    
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
                $("#eventTable").dataTable({
                	"oLanguage": {
             			"sUrl": "${contextPath}/resources/json/Chinese.json"
         			}
                });
                $(".header").load("${contextPath}/header?t="+pm_random());
                $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());

                if(propertiesdata!="")
                {
                	propertiesdata = $.parseJSON(propertiesdata);
	                $.each(propertiesdata, function(k,v){
	                	if(v!="")
	        				$("div[name='"+k+"']").html(v);
	        		});
                }
                pm_cms_initselectdialog('ci');
                
                $(".tabs").find("li").bind("click",function(){
                	switch($(this).children("a").attr("href"))
                	{
                	case "#tabs-2":
                		pm_cms_getOS();					//操作系统重装
                		break;
                	case "#tabs-3":						//变更
                		pm_cms_getChanges();
                		break;
                	case "#tabs-4":						//停用
                		pm_cms_getStops();
                		break;
                	case "#tabs-5":						//停用
                		pm_cms_getScraps();
                		break;
                	case "#tabs-6":
                		pm_cms_getMaintains();
                		break;
                	case "#tabs-7":						//停用
                		pm_cms_getDestroys();
                		break;
                	}
                });
            });
            
            function initTable(code) {
            	pm_cms_initdialogtable(code);
            }
            function deleteConfirm()
            {
            	if(!confirm("确定要执行该操作?"))
            		return false;
            }
            
            function pm_cms_getStops() {
            	$("#stopTable tbody tr").remove();
        		$.getJSON(ctx+'/account-life-cycle/get-stops-by-ci?ciid='+ciId+'&t='+pm_random(),function(data){
        			var trs = "";
        			$.each(data,function(i,v){
        				trs += "<tr><td>"+v.solution+"</td><td>"+v.delegateUser+"</td>"
        					+"<td>"+new Date(v.startTime).format("yyyy-MM-dd HH:mm")+"</td>"
        					+"<td>"+new Date(v.endTime).format("yyyy-MM-dd HH:mm")+"</td>"
        					+"<td>"+v.resultView+"</td></tr>";
        			});
        			$("#stopTable tbody").append(trs);
        		});
            }
            
            function pm_cms_getOS() {
            	$("#osTable tbody tr").remove();
        		$.getJSON(ctx+'/account-life-cycle/get-os-by-ci?ciid='+ciId+'&t='+pm_random(),function(data){
        			var trs = "";
        			$.each(data,function(i,v){
        				trs += "<tr><td>"+v.solution+"</td><td>"+v.delegateUser+"</td>"
        					+"<td>"+new Date(v.startTime).format("yyyy-MM-dd HH:mm")+"</td>"
        					+"<td>"+new Date(v.endTime).format("yyyy-MM-dd HH:mm")+"</td>"
        					+"<td>"+v.OLD_OSVERSION+"</td>"
        					+"<td>"+v.OLD_OSINSTALLTIME+"</td>"
        					+"<td>"+v.NEW_OSVERSION+"</td>"
        					+"<td>"+v.NEW_OSINSTALLTIME+"</td>"
        					+"<td>"+v.resultView+"</td></tr>";
        			});
        			$("#osTable tbody").append(trs);
        		});
            }
		
            function pm_cms_getChanges() {
            		$("#changeTable tbody tr").remove();
            		$.getJSON(ctx+'/account-life-cycle/get-changes-by-ci?ciid='+ciId+'&t='+pm_random(),function(data){
            			var trs = "";
            			$.each(data,function(i,v){
            				trs += "<tr><td>"+v.solution+"</td><td>"+v.delegateUser+"</td>"
            					+"<td>"+new Date(v.startTime).format("yyyy-MM-dd HH:mm")+"</td>"
            					+"<td>"+new Date(v.endTime).format("yyyy-MM-dd HH:mm")+"</td>"
            					+"<td>"+v.resultView+"</td></tr>";
            			});
            			$("#changeTable tbody").append(trs);
            		});
            }
            
            function pm_cms_getScraps() {
        		$("#scrapTable tbody tr").remove();
        		$.getJSON(ctx+'/account-life-cycle/get-scraps-by-ci?ciid='+ciId+'&t='+pm_random(),function(data){
        			var trs = "";
        			$.each(data,function(i,v){
        				trs += "<tr><td>"+v.solution+"</td><td>"+v.delegateUser+"</td>"
        					+"<td>"+new Date(v.startTime).format("yyyy-MM-dd HH:mm")+"</td>"
        					+"<td>"+new Date(v.endTime).format("yyyy-MM-dd HH:mm")+"</td>"
        					+"<td>"+v.resultView+"</td></tr>";
        			});
        			$("#scrapTable tbody").append(trs);
        		});
        	}
            
            function pm_cms_getDestroys() {
        		$("#destroyTable tbody tr").remove();
        		$.getJSON(ctx+'/account-life-cycle/get-destroys-by-ci?ciid='+ciId+'&t='+pm_random(),function(data){
        			var trs = "";
        			$.each(data,function(i,v){
        				trs += "<tr><td>"+v.solution+"</td><td>"+v.delegateUser+"</td>"
        					+"<td>"+new Date(v.startTime).format("yyyy-MM-dd HH:mm")+"</td>"
        					+"<td>"+new Date(v.endTime).format("yyyy-MM-dd HH:mm")+"</td>"
        					+"<td>"+v.resultView+"</td></tr>";
        			});
        			$("#destroyTable tbody").append(trs);
        		});
        	}
            
            function pm_cms_getMaintains() {
        		$("#maintainTable tbody tr").remove();
        		$.getJSON(ctx+'/account-life-cycle/get-maintains-by-ci?ciid='+ciId+'&t='+pm_random(),function(data){
        			var trs = "";
        			$.each(data,function(i,v){
        				trs += "<tr><td>"+v.solution+"</td><td>"+v.delegateUser+"</td>"
        					+"<td>"+new Date(v.startTime).format("yyyy-MM-dd HH:mm")+"</td>"
        					+"<td>"+new Date(v.endTime).format("yyyy-MM-dd HH:mm")+"</td>"
        					+"<td>"+v.resultView+"</td></tr>";
        			});
        			$("#maintainTable tbody").append(trs);
        		});
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
                    <li><a href="${contextPath}/account-life-cycle/index">台账管理</a> <span class="divider">></span></li>       
                    <li class="active">台账信息</li>
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
                                <div class="left text-info"><h4>${ci.name} <c:if test="${not empty ci.model }">-</c:if>${ci.model}</h4> </div>
                            </div>                                  
                        </div>
                         <div class="block-fluid ucard">                                                                
                            <ul class="rows">
                            <!-- <li>
                                <div class="title">所属系统:</div>
                                <div class="text"><c:if test="${empty ci.system }">-</c:if>${ci.systemName} </div>
                            </li> -->
                            <li>
                                <div class="title">密级:</div>
                                <div class="text"><c:if test="${empty ci.securityLevel }">-</c:if>${ci.securityLevelName} </div>
                            </li>
                            <li>
                                <div class="title">保密编号:</div>
                                <div class="text"><c:if test="${empty ci.securityNo }">-</c:if>${ci.securityNo} </div>
                            </li>
                             <li>
                                <div class="title">物理位置:</div>
                                <div class="text"><c:if test="${empty ci.location }">-</c:if>${ci.location} </div>
                            </li>
                            <li>
                                <div class="title">使用部门:</div>
                                <div class="text"><c:if test="${empty ci.departmentName }">-</c:if>${ci.departmentName }</div>
                            </li> 
                            <li>
                                <div class="title">维护人:</div>
                                <div class="text"><c:if test="${empty ci.userInMaintenance }">-</c:if>${ci.userInMaintenanceName }</div>
                            </li>
                            <!-- <li>
                                <div class="title">服务提供商:</div>
                                <div class="text"><c:if test="${empty ci.serviceProvider }">-</c:if>${ci.serviceProvider }</div>
                            </li> -->
                            <li>
                                <div class="title">状态:</div>
                                <div class="text"><c:if test="${empty ci.status }">-</c:if>${ci.statusName }</div>
                            </li>
                            <!-- <li>
                                <div class="title">审核状态:</div>
                                <div class="text"><c:if test="${empty ci.reviewStatus }">-</c:if>${ci.reviewStatusName }</div>
                            </li>
                            <li>
                                <div class="title">删除状态:</div>
                                <div class="text"><c:if test="${empty ci.deleteStatus }">-</c:if>${ci.deleteStatusName }</div>
                            </li> -->
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
                            <c:if test="${map.value.size()==0 }">
                            	<li><div class="title">未定义属性</div><div class="text">&nbsp;</div></li>
                            </c:if>
                            <c:if test="${map.value.size()>0 }">
                            <c:forEach items="${map.value }" var="property"> 
	                            <li>
	                                <div class="title">${property.propertyName}:</div>
	                                <div class="text" id="${property.propertyId}" name="${property.propertyId}">-</div>
	                            </li>
                            </c:forEach>
                            </c:if>
                            </ul>                                                      
                        </div>    

                    </div>
                </c:forEach>  
                
               	</div>
            	
            	<div class="dr"><span></span></div>
            	
            	<div class="row">
	            	<div class="col-md-12">
	            		<div class="head clearfix">
	                        <div class="isw-attachment"></div>
	                    	<h1>台账操作列表</h1>
	                    </div>
	                    <div class="block-fluid tabs">
	                        <ul>
                                <li><a href="#tabs-1">设备申请信息</a></li>
                                <li><a href="#tabs-2">重装操作系统</a></li>
                                <li><a href="#tabs-3">变更</a></li>
                                <li><a href="#tabs-4">停用</a></li>
                                <li><a href="#tabs-5">报废</a></li>
                                <li><a href="#tabs-6">维修</a></li>
                                <li><a href="#tabs-7">销毁</a></li>
                            </ul> 
                            
                            <div id="tabs-1">
                            	<div style="height:300px;">
                                <table style="cellpadding:0;cellspacing:0;width:100%;" class="table">
	                                <thead>
	                                    <tr>
	                                        <th>名称</th>
	                                        <th>密级</th>
	                                        <th>涉密编号</th>
	                                        <th>时间</th>
	                                        <th>设备类型</th>
	                                        <th>品牌型号</th>
	                                        <th>责任人</th>
	                                        <th>责任部门</th>
	                                        <th>物理位置</th>
	                                    </tr>
	                                </thead>
	                                <tbody> 
	                                <tr>
	                                <td>${createDetail.CMS_FIELD_NAME }</td>
	                                <td>${createDetail.CMS_FIELD_SECURITYLEVELNAME }</td>
	                                <td>${createDetail.CMS_FIELD_SECURITYNO }</td>
	                                <td>${createDetail.CMS_FIELD_CREATEDTIME }</td>
	                                <td>${createDetail.CMS_FIELD_CATEGORYNAME }</td>
	                                <td>${createDetail.CMS_FIELD_PRODUCER }&nbsp;--&nbsp;${createDetail.CMS_FIELD_MODEL }</td>
	                                <td>${createDetail.CMS_FIELD_USERINMAINTENANCE }</td>
	                                <td>${createDetail.CMS_FIELD_DEPARTMENTINUSE }</td>
	                                <td>${createDetail.CMS_FIELD_LOCATION }</td>
	                                </tr>                                           
	                                </tbody>
	                            </table>                    
                            	</div>
                            </div>  
                            <div id="tabs-2">
								<table class="table" id="osTable">
                                <thead>
                                    <tr>                                    
                                        <th>摘要</th>
                                        <th width="60px">实施人</th>
                                        <th width="120px">开始时间</th>
                                        <th width="120px">结束时间</th>
                                        <th width="120px">原操作系统</th>
                                        <th width="120px">原安装时间</th>
                                        <th width="120px">操作系统</th>
                                        <th width="120px">安装时间</th>
                                        <th>实施结果</th>
                                    </tr>
                                </thead>
                                <tbody>
										
                                </tbody>
                            	</table></div>
                            <div id="tabs-3">
                                <table class="table" id="changeTable">
                                <thead>
                                    <tr>                                    
                                        <th>摘要</th>
                                        <th width="120px">实施人</th>
                                        <th width="120px">开始时间</th>
                                        <th width="120px">结束时间</th>
                                        <th>实施结果</th>
                                    </tr>
                                </thead>
                                <tbody>
										
                                </tbody>
                            	</table>
							</div>
                            <div id="tabs-4">
								<table class="table" id="stopTable">
                                <thead>
                                    <tr>                                    
                                        <th>摘要</th>
                                        <th width="120px">实施人</th>
                                        <th width="120px">开始时间</th>
                                        <th width="120px">结束时间</th>
                                        <th>实施结果</th>
                                    </tr>
                                </thead>
                                <tbody>
										
                                </tbody>
                            	</table>
								
							</div>
                            <div id="tabs-5">
							<table class="table" id="scrapTable">
                                <thead>
                                    <tr>                                    
                                        <th>摘要</th>
                                        <th width="120px">实施人</th>
                                        <th width="120px">开始时间</th>
                                        <th width="120px">结束时间</th>
                                        <th>实施结果</th>
                                    </tr>
                                </thead>
                                <tbody>
										
                                </tbody>
                            	</table>
							</div>
                            <div id="tabs-6"><table class="table" id="maintainTable">
                                <thead>
                                    <tr>                                    
                                        <th>摘要</th>
                                        <th width="120px">实施人</th>
                                        <th width="120px">开始时间</th>
                                        <th width="120px">结束时间</th>
                                        <th>实施结果</th>
                                    </tr>
                                </thead>
                                <tbody>
										
                                </tbody>
                            	</table></div>
                            <div id="tabs-7">
                            <table class="table" id="scrapTable">
                                <thead>
                                    <tr>                                    
                                        <th>摘要</th>
                                        <th width="120px">实施人</th>
                                        <th width="120px">开始时间</th>
                                        <th width="120px">结束时间</th>
                                        <th>实施结果</th>
                                    </tr>
                                </thead>
                                <tbody>
										
                                </tbody>
                            	</table></div>
	                    </div>
	            	</div>
            	</div><!-- row end -->
            </div>
            <!--workplace end-->
        </div>   

    </div>
</body>

</html>