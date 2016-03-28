<%@page import="com.cngc.utils.activiti.ProcessDefinitionCache,org.activiti.engine.RepositoryService,org.activiti.engine.RuntimeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>配置管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
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
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
            $(document).ready(function () {
                
                $(".header").load("${contextPath}/header?t="+pm_random());
                $(".menu").load("${contextPath }/menu?t="+pm_random(), function() {$("#node_${moduleId}").addClass("active");});
                $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
                $(".confirm").bind("click",function(){
                	if(!confirm("确定要执行该操作?"))
                		return false;
                });
                <%--$('#treeview').treeview({
                    levels: 1, 
                    data: jsonData,
                    color: "#666666",
                    nodeIcon: 'glyphicon glyphicon-list',
                    onNodeSelected: function(event, node) {
                      initTable(node.text.substring(0,node.text.indexOf(" ")));
                    }
                  });
                  $.each(jsonData, function (i, field) {
                  	var code = field.text.substring(0,field.text.indexOf(" "));
                  	var liStr = "<li><a href=\"#\" onclick=\"initTable("+code+");\">"+field.text+"</a>";
                  	if(field.nodes) {
                  		liStr += "<ul>";
                  		$.each(field.nodes, function(j, node){
                  			var code1 = node.text.substring(0,node.text.indexOf(" "));
                  			liStr+="<li><a href=\"#\" onclick=\"initTable("+code1+");\">"+node.text+"</a>";
                  			if(node.nodes) {
                  				liStr += "<ul>";
                  				$.each(node.nodes, function(k, last){
                  					var code2 = last.text.substring(0,last.text.indexOf(" "));
                  					liStr+="<li><a href=\"#\" onclick=\"initTable("+code2+");\">"+last.text+"</a></li>";
                  				});
                  				liStr += "</ul>";
                  			}
                  			liStr+="</li>"
                  		});
                  		liStr += "</ul>";
                  	}
                  	liStr += "</li>";
                    $("#treeview").append(liStr);
                  }); --%>
                  $("#treeview").treeview({
                	unique: true,
            		url : ctx + '/cms/category/getjson?t=' + pm_random()
            	});
                
                initTable("0");
                /*
                $("#ciTable").dataTable({
                	paging:true,
                	"oLanguage": {
             			"sUrl": "${contextPath}/resources/json/Chinese.json"
         			},
               	"bSort":false});*/
            });
            function initTable(code)
            {
            	
            	$.getJSON(ctx + '/cms/ci/list/'+code+'/?t=' + pm_random() , function(data) {
            		$("#ciTable tbody tr").remove();
            		if(data.list==null)
            			return;
            		var trs = "";
            		for(i=0;i<data.list.length;i++)
            		{
            			trs += "<tr>"
						+"<td><a href='"+ctx+"/cms/ci/detail/"+data.list[i]["id"]+"'>"+data.list[i]["name"]+"</a>"
						if(data.list[i]["model"]!="")
							trs += " -"+data.list[i]["model"];
						trs += "</td>";
						trs += "<td>"+data.list[i]["systemName"]+"</td>"
						+"<td>"+data.list[i]["categoryName"]+"</td>"
						+"<td>"+data.list[i]["securityLevelName"]+"</td>"
						+"<td>"+data.list[i]["securityNo"]+"</td>"
						+"<td>"+data.list[i]["departmentInUse"]+"</td>"
						+"<td>"+data.list[i]["userInMaintenanceName"]+"</td>"
						+"<td>"+data.list[i]["statusName"]+"</td>"
						+"<td>"+data.list[i]["reviewStatusName"]+"</td>";
						if(data.list[i]["deleteStatus"]=='01')
							trs += "<td><span class='label label-success'>"+data.list[i]["deleteStatusName"]+"</span></td>";
						else
							trs += "<td><span class='label label-danger'>"+data.list[i]["deleteStatusName"]+"</span></td>";
						//+"<td>"+new Date(data.list[i]["lastUpdateTime"]).format('yyyy-MM-dd HH:mm:ss')+"</td>"
						trs += "<td>"
						+"<a href='"+ctx+"/cms/ci/addproperty/"+data.list[i]["id"]+"' >属性</a> "
						+" <a href='"+ctx+"/cms/ci/delete/"+data.list[i]["id"]+"' class='confirm'>删除</a>"
						+"</td>"
						+"</tr>";
						
            		}
            		$("#ciTable tbody").append(trs);
            		/*
            		if ( $.fn.dataTable.isDataTable( '#ciTable' ) ) {
            		    table = $('#ciTable').DataTable({
            		    	paging:true,
                        	"oLanguage": {
                     			"sUrl": "${contextPath}/resources/json/Chinese.json"
                 			},
                       	"bSort":false});
            		}
            		else {
            		    table = $('#ciTable').DataTable( {
            		    	paging:true,
                        	"oLanguage": {
                     			"sUrl": "${contextPath}/resources/json/Chinese.json"
                 			},
                       	"bSort":false} );
            		}
            		*/
            		
                    $(".confirm").bind("click",function(){
                    	if(!confirm("确定要执行该操作?"))
                    		return false;
                    }); 
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
                    <li><a href="${contextPath }/cms/ci/list">配置管理</a> <span class="divider">></span></li>       
                    <li class="active">配置项管理</li>
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
                        <div class="isw-donw_circle"></div>
                        <h1>配置项类别</h1>
                           	<ul class="buttons">
                        		<li><a href="${contextPath }/cms/ci/list" class="isw-list tipl" title="查看全部 "></a>   </li>
                            </ul>
                    </div>
                		<div class="block-fluid">
                		<ul id="treeview" style="margin-left:10px;"></ul>
                		</div>
                	</div>
                    <div class="col-md-9">                    
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>配置项管理</h1>  

                            <ul class="buttons">                          
                            	<li>
                                    <a href="${contextPath }/cms/ci/add" role="button" data-toggle="modal" class="isw-plus tipb" title="创建配置项"></a>
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
                            <table class="table" id="ciTable">
                                <thead>
                                	<tr>
										<th>名称</th>
										<th width="100px">所属系统</th>
										<th width="80px">分类</th>
										<th width="60px">密级</th>
										<th width="80px">保密编号</th>
										<th width="100px">使用部门</th>
										<th width="70px">维护人</th>
										<th width="80px">状态</th>
										<th width="65px">审核</th>
										<th width="60px">删除</th>
										<th width="80px">操作</th>
									</tr>
                                </thead>
                                <tbody>
  
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