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
            $(document).ready(function () {
                $("#eventTable").dataTable({"bSort":false});
                
                $(".header").load("${contextPath}/header");
                $(".menu").load("${contextPath}/menu", function () { $("#node_${moduleId}").addClass("active"); });
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
                    <li><a href="#">报表与统计管理</a> <span class="divider">></span></li>       
                    <li class="active">台账管理</li>
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
                            <h1>台账管理</h1>
                        </div>
                        <div class="block-fluid accordion">
                            <h3>台账</h3>
                            <div class="active">
                                 <ul>
                                 	<li><a href="${contextPath }/stats/account/get/1">服务器台账</a></li>
                                 	<li><a href="${contextPath }/stats/account/get/1">网络设备台账</a></li>
                                    <li><a href="${contextPath }/stats/account/get/1">安全设备台账</a></li>
                                    <li><a href="${contextPath }/stats/account/get/1">终端设备台账</a></li>
                                </ul>                                               
                            </div>

                            <h3>自定义</h3>
                            <div>
                                <ul>
                                	<li><a href="${contextPath }/stats/account/list">台账信息列表</a></li>
                                    <li><a href="${contextPath }/stats/account/setting">自定义台账</a></li>
                                </ul>                                                
                            </div>                    
                        </div>
                    </div> 
                    <div class="col-md-9">                    
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>台账管理</h1>  

                            <ul class="buttons">  
								<li>
                                    <a href="${contextPath }/stats/account/add" role="button" data-toggle="modal" class="isw-plus tipb" title="创建新台账"></a>
                                </li>                       
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                        <li><a href="#" onclick="pm_refresh()"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="eventTable">
                                <thead>
                                	<tr>
                                		<th width="100px">名称</th>
										<th width="80px">分类代码</th>
										<th width="200px">字段信息</th>
										<th>属性信息</th>
										<th width="120px">创建时间</th>
										<th width="90px">创建人</th>
										<th width="70px">操作</th>
									</tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list}" var="account">
									<tr>
										<td>${account.name }</td>
										<td>${account.category }</td>
										<td>
											<c:set var="fieldlist" value="${account.fields.split(\",\") }"></c:set>
											<c:forEach items="${fieldlist }" var="field" varStatus="status"><span class="label label-info">${status.index+1 }</span>${field } </c:forEach>
										</td>
										<td>
											<c:set var="propertylist" value="${account.properties.split(\",\") }"></c:set>
											<c:forEach items="${propertylist }" var="property" varStatus="status">
												<c:if test="${not empty property }">
													<span class="label label-info">${status.index+1 }</span> ${property }
												</c:if> 
											</c:forEach>
										</td>
										<td><fmt:formatDate value="${account.createdTime }" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td>
										<td>${account.createdUserName }</td>
										<td>
											<a class="confirm" href="${contextPath}/stats/account/delete/${account.id}"><span class="glyphicon glyphicon-remove"></span> 删除</a>
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
     	
    </div>
</body>

</html>