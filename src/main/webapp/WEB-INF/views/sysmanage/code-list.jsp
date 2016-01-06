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
    
    <title>数据字典管理--运维管理系统</title>

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
    var selectCode = "INCIDENT_CATEGORY";
            $(document).ready(function () {
                $("#codeTable").dataTable({"bSort":false});
                $(".header").load("${contextPath}/header");
                $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons");
                $(".confirm").bind("click",function(){
                	if(!confirm("确定要执行该操作?"))
                		return false;
                });

            	$(".lnk_new").bind("click",newCode);
            	$(".lnk_type").bind("click",changeType);
            	initTable();
            });
    function changeType()
    {
    	selectCode = $(this).attr("title");
    	initTable();
    }
    function newCode(){
		$("#dialogTitle").html("新建编码");
		$("#fp_id").attr('value','0');
		$("#fp_code").attr('value','');
		$("#fp_codename").attr('value','');
		$("#fp_type").attr('value',selectCode);
		$("#codeFormDialog").modal('show');	
    }
    function modifyCode(){
    	var id = $(this).parents('tr').find('.codeid').text();
    	$.getJSON(ctx + '/system/syscode/get/' + id + '?t='+pm_random(),function(data){
    		$("#dialogTitle").html("修改编码");
    		$("#fp_id").attr('value',data.code.id);
    		$("#fp_code").attr('value',data.code.code);
    		$("#fp_codename").attr('value',data.code.codeName);
    		$("#fp_type").attr('value',data.code.type);
    		$("#codeFormDialog").modal('show');
    	});
    }
    function deleteCode(){
    	if(!confirm("确定要删除该代码?"))
    		return false;
    	
    	var id = $(this).parents('tr').find('.codeid').text();
    	$.getJSON(ctx + '/system/syscode/delete/' + id + '?t='+pm_random(),function(data){
    		initTable();
    	});
    }
    function initTable()
    {
    	$.getJSON(ctx + '/system/syscode/list/'+selectCode+'/?t=' + pm_random() , function(data) {
    		$("#codeTable tbody tr").remove();
    		if(data.list==null)
    			return;
    		var trs;
    		for(i=0;i<data.list.length;i++)
    		{
    			trs += "<tr>"
				+"<td class='codeid' style='display:none'>"+data.list[i]["id"]+"</td>"
				+"<td>"+data.list[i]["code"]+"</td>"
				+"<td>"+data.list[i]["codeName"]+"</td>"
				+"<td>"+data.list[i]["type"]+"</td>"
				+"<td>"
				+"<a href='#' class='lnk_modify'><span class='glyphicon glyphicon-edit'></span> 编辑</a> "
				+"&nbsp;&nbsp;<a href='#' class='lnk_delete'><span class='glyphicon glyphicon-remove'></span> 删除</a>"
				+"</td>"
				+"</tr>";
				
    		}
    		$("#codeTable tbody").append(trs);
            $(".lnk_modify").bind("click", modifyCode);
            $(".lnk_delete").bind("click",deleteCode);
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
                    <li><a href="#">系统管理</a> <span class="divider">></span></li>       
                    <li class="active">数据字典管理</li>
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
                		
                        <div class="block-fluid without-head accordion">
                            <h3>事件管理</h3>
                            <div>
                                <ul>
                            	<li class="active"><a href="#" class="lnk_type" title="INCIDENT_CATEGORY"> 事件分类</a><span class="arrow"></span></li>
                            	<li><a href="#" class="lnk_type" title="INCIDENT_SOURCE"> 事件来源</a><span class="arrow"></span></li>
                            	<li><a href="#" class="lnk_type" title="INCIDENT_TYPE"> 事件类型</a><span class="arrow"></span></li>
                                <li><a href="#" class="lnk_type" title="INCIDENT_STATUS"> 事件状态</a><span class="arrow"></span></li>
                                <li><a href="#" class="lnk_type" title="INCIDENT_SATISFACTION"> 事件满意度</a><span class="arrow"></span></li>
                               	<li><a href="#" class="lnk_type" title="INFLUENCE"> 影响度</a><span class="arrow"></span></li>
                                <li><a href="#" class="lnk_type" title="CRITICAL"> 紧急度</a><span class="arrow"></span></li>
                                <li><a href="#" class="lnk_type" title="PRIORITY"> 优先级</a><span class="arrow"></span></li>
                                </ul>                                               
                            </div>
                            <h3>变更管理</h3>
                            <div class="active">
                                <ul>
                                <li><a href="#" class="lnk_type" title="CHANGE_CATEGORY"> 变更分类</a><span class="arrow"></span></li>
                                <li><a href="#" class="lnk_type" title="CHANGE_RISK"> 变更风险等级</a><span class="arrow"></span></li>
                                <li><a href="#" class="lnk_type" title="CHANGE_STATUS"> 变更状态</a><span class="arrow"></span></li>
                                </ul>                                                
                            </div>  
                            <h3>知识管理</h3>
                            <div class="active">
                                <ul>
                               	<li><a href="#" class="lnk_type" title="KNOWLEDGE_STATUS"> 知识状态</a><span class="arrow"></span></li>
                               	</ul>                                                
                            </div>  
                            <h3>配置管理</h3>
                            <div class="active">
                                <ul>
                                <li><a href="#" class="lnk_type" title="CI_SYSTEM"> 配置项归属系统</a><span class="arrow"></span></li>
                               	<li><a href="#" class="lnk_type" title="CI_STATUS"> 配置项状态</a><span class="arrow"></span></li>
                                <li><a href="#" class="lnk_type" title="CI_REVIEW_STATUS"> 配置项审核状态</a><span class="arrow"></span></li>
                                <li><a href="#" class="lnk_type" title="CI_DELETE_STATUS"> 配置项删除状态</a><span class="arrow"></span></li>
                                <li><a href="#" class="lnk_type" title="CI_SECURITY_LEVEL"> 配置项密级</a><span class="arrow"></span></li>
                               	</ul>                                                
                            </div> 
                            <h3>运维工作管理</h3>
                            <div class="active">
                                <ul>
                                <li><a href="#" class="lnk_type" title="INSPECTION_STATUS"> 巡检结果</a><span class="arrow"></span></li>
                                <li><a href="#" class="lnk_type" title="UPDATE_TYPE"> 升级类型</a><span class="arrow"></span></li>
                                <li><a href="#" class="lnk_type" title="SECJOB_TYPE"> 三员工作类型</a><span class="arrow"></span></li>
                                </ul>                                                
                            </div>                   
                        </div>
                	</div>
                    <div class="col-md-9">                    
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>数据字典管理</h1>  

                            <ul class="buttons">
                            	<li>
                                    <a href="#" id="lnk_start_leadertask" class="isw-plus tipb lnk_new" title="创建"></a>
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
                            <table class="table" id="codeTable">
                                <thead>
								<tr>
									<th width="60px" style="display:none;">ID</th>
									<th width="150px">编码</th>
									<th>编码名称</th>
									<th width="120px">类别</th>
									<th width="130px">操作</th>
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
        <!-- 新建编码 modal form -->
        <div class="modal fade" id="codeFormDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4 id="dialogTitle">新建编码</h4>
                    </div>
                    <form id="userForm" action="${contextPath}/system/syscode/save" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">编码:</div>
                                    <div class="col-md-9"><input id="fp_code" name="fp_code" type="text" /></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">编码名称:</div>
                                    <div class="col-md-9"><input id="fp_codename" name="fp_codename" type="text"/></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">类别:</div>
                                    <div class="col-md-9"><input id="fp_type" name="fp_type" type="text"></input></div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" aria-hidden="true">保存</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    <input type="hidden" id="fp_id"  name="fp_id" value="0" /> 
                    </form>
                </div>
            </div>
        </div>
    	<!-- 新建编码 end from -->
    	
    </div>
</body>

</html>