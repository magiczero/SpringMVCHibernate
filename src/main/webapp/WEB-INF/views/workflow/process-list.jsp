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
    
    <title>流程管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/uniform.default.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
            $(document).ready(function () {
                $("#eventTable").dataTable({"aaSorting":[[3,'desc']]});
                $(".header").load("${contextPath}/header?t="+pm_random());
                $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
                $(".confirm").bind("click",function(){
                	if(!confirm("确定要执行该操作?"))
                		return false;
                });
                // 资源
                $("#b_popup_resource").dialog({
                    autoOpen: false,
                    width: 800,
                    height: 400,
                    buttons: { "关闭": function () { $(this).dialog("close") } }
                });
                $(".lnk_image").click(function () {
                	$("#resource_content").html("<img src='"+$(this).attr("src")+"'/>");
                	$("#b_popup_resource").dialog('open');
                });
                $(".lnk_xml").click(function () {
                	$("#resource_content").html("<textarea id='description' name='description' style='height:270px;'></textarea>")
                	$("#description").load($(this).attr("src"), function () {
                        $("#b_popup_resource").dialog('open');
                    });
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
                    <li><a href="${contextPath }/workflow/process/list">工作流管理</a> <span class="divider">></span></li>       
                    <li class="active">流程列表</li>
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
                    <div class="col-md-12">                    
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>流程列表</h1>  

                            <ul class="buttons">  
                            	<li>
                                    <a href="#deployForm" class="isw-plus tipb" role="button" data-toggle="modal" title="部署流程"></a>
                                </li>                         
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                    	<li><a href="#deployForm" role="button" data-toggle="modal"><span class="isw-ok"></span> 部署流程</a></li>
                                        <li><a href="#" onclick="pm_refresh()"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="eventTable">
                                <thead>
                                	<tr>
										<th  width="100px">流程部署ID</th>
										<th>流程名称</th>
										<th width="70px">版本号</th>
										<th  width="150px">部署时间</th>
										<th  width="130px">流程资源</th>
										<th  width="100px">是否挂起</th>
										<th  width="150px">操作</th>
									</tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list}" var="obj">
									<c:set var="process" value="${obj[0]}" />
									<c:set var="deployment" value="${obj[1]}" />
									<tr>
										<td>${process.deploymentId }</td>
										<td>${process.name }</td>
										<td>${process.version }</td>
										<td><fmt:formatDate value="${deployment.deploymentTime }" pattern="yyyy/MM/dd HH:mm:ss" /></td>
										<td><a class="lnk_xml" href='#' src='${contextPath }/workflow/resource/read?processDefinitionId=${process.id}&resourceType=xml'>配置文件</a> |
											<a class="lnk_image" href='#' src='${contextPath }/workflow/resource/read?processDefinitionId=${process.id}&resourceType=image'>流程图</a></td>
										<td>${process.suspended} |
											<c:if test="${process.suspended }">
												<a class="confirm" href="${contextPath }/workflow/processdefinition/update/active/${process.id}">激活</a>
											</c:if>
											<c:if test="${!process.suspended }">
												<a class="confirm" href="${contextPath }/workflow/processdefinition/update/suspend/${process.id}">挂起</a>
											</c:if>
										</td>
										<td>
					                        <a class="confirm" href='${contextPath }/workflow/process/delete?deploymentId=${process.deploymentId}'><span class="glyphicon glyphicon-remove"></span> 删除</a>
					                        <a class="confirm" href='${contextPath }/workflow/process/convert-to-model/${process.id}'>转换为Model</a>
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
        <!-- 流程部署 modal form -->
        <div class="modal fade" id="deployForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>部署流程</h4>
                    </div>
                    <form action="${contextPath }/workflow/deploy" method="post" enctype="multipart/form-data">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">上传流程文件:</div>
                                    <div class="col-md-9"><input type="file" name="file" /></div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" aria-hidden="true">提交</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    </form>
                </div>
            </div>
        </div>
    	<!-- 资源读取 -->
    	<div class="dialog" id="b_popup_resource" style="display: none;" title="流程资源">
	    	<div class="block dialog_block  uploads" id="resource_content">
			                                
			</div>
    	</div>
    </div>
</body>

</html>