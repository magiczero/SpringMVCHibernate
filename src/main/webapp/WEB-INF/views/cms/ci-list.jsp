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
    <link href="${contextPath }/resources/css/uploadify.css" rel="stylesheet" type="text/css" />
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
                $("#treeview").treeview({
                	unique: true,
            		url : ctx + '/cms/category/getjson?t=' + pm_random()
            	});
                
                initTable("0");
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
						
						trs += "</td>";
						trs += "<td>"+data.list[i]["num"]+"</td>"
						+"<td>"+data.list[i]["categoryName"]+"</td>"
						+"<td>"+data.list[i]["producer"]+"</td>"
						+"<td>"+data.list[i]["model"]+"</td>"
						+"<td>"+data.list[i]["departmentInUse"]+"</td>"
						+"<td>"+data.list[i]["userInMaintenanceName"]+"</td>"
						+"<td>"+data.list[i]["serial"]+"</td>"
						+"<td>"+transDate(data.list[i]["createdTime"])+"</td>"
						+"<td>"+transDate(data.list[i]["expirationTime"])+"</td>"
						+"<td>"+transDate(data.list[i]["serviceStartTime"])+"</td>"
						+"<td>"+data.list[i]["purpose"]+"</td>"
						+"<td>"+data.list[i]["statusName"]+"</td>";
						//if(data.list[i]["deleteStatus"]=='01')
						//	trs += "<td><span class='label label-success'>"+data.list[i]["deleteStatusName"]+"</span></td>";
						//else
						//	trs += "<td><span class='label label-danger'>"+data.list[i]["deleteStatusName"]+"</span></td>";
						//+"<td>"+new Date(data.list[i]["lastUpdateTime"]).format('yyyy-MM-dd HH:mm:ss')+"</td>"
						trs += "<td>"
						+"<a href='"+ctx+"/cms/ci/addproperty/"+data.list[i]["id"]+"' >属性</a> "
						+" <a href='"+ctx+"/cms/ci/delete/"+data.list[i]["id"]+"' class='confirm'>删除</a>"
						+"</td>"
						+"</tr>";
						
            		}
            		$("#ciTable tbody").append(trs);
            		
                    $(".confirm").bind("click",function(){
                    	if(!confirm("确定要执行该操作?"))
                    		return false;
                    }); 
            	});
            }
            
            function transDate(time_) {
            	if(time_==null) return "";
            	var dt = new Date(time_);
            	
            	return dt.toLocaleDateString();
            }
    </script>
    <style type="text/css">
    	.uploadify-button-text {color:#fff !important;}
    	
    </style>
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
                	<div class="col-md-2">
                	<div class="head clearfix">
                        <div class="isw-donw_circle"></div>
                        <h1>配置项类别</h1>
                    </div>
                		<div class="block-fluid">
                		<ul id="treeview" style="margin-left:10px;"></ul>
                		</div>
                	</div>
                    <div class="col-md-10">                    
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
                                    	<li><a href="#fModal" data-toggle="modal"><span class="isw-up"></span> 导入数据</a></li>
                                        <li><a href="#" onclick="pm_refresh()"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="ciTable">
                                <thead>
                                	<tr>
										<th>设备名称</th>
										<th width="100px">设备编号</th>
										<th width="80px">分类</th>
										<th width="60px">生产厂商/品牌</th>
										<th width="80px">型号/版本号</th>
										<th width="100px">所属部门</th>
										<th width="70px">责任人</th>
										<th width="80px">出厂编号/序列号</th>
										<th width="65px">购置时间</th>
										<th width="60px">维保截止时间</th>
										<th width="60px">启用时间</th>
										<th width="60px">设备用途</th>
										<th width="60px">使用情况</th>
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
        <div class="modal fade" id="fModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4>导入Excel文件</h4>
                    </div>
                    <form method="post" action="${contextPath}/cms/ci/importData">
                    <input id="fileids" name="fileids" type="hidden" />
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">文件:</div>
                                    <div class="col-md-9"><input type="file" name="file_upload" id="file_upload" /></div>
                                </div>
                                
                            </div>
                            
                        </div>
                    </div>
                    <div class="modal-footer">
                    <input type="submit" value="导入" class="btn btn-warning" />
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
                    </div>
                    </form>
                </div>
            </div>
        </div>  
    </div>
</body>

</html>
