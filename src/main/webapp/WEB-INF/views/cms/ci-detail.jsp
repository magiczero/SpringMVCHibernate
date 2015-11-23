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
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.stack.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.pie.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.resize.js'></script>
    
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

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.gears.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.silverlight.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.flash.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.browserplus.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.html4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.html5.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/jquery.plupload.queue/jquery.plupload.queue.js'></script>    
    
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/cookies.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/myactions.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/charts.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/settings.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/faq.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/jquery.form.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
   		var ctx = "${contextPath}"; 
   		var propertiesdata = ${ci.propertiesData };
   		var ciId = "${ci.id}";
   		
            $(document).ready(function () {
                $("#eventTable").dataTable();
                $(".header").load("${contextPath}/header");
                $(".menu").load("${contextPath}/menu", function () { $(".navigation > li:eq(3)").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons");

                $.each(propertiesdata, function(k,v){
        			$("div[name='"+k+"']").html(v);
        		});
                
            });
            function deleteConfirm()
            {
            	if(!confirm("确定要执行该操作?"))
            		return false;
            }
            function initTable(relationId)
            {
            	$.getJSON(ctx + '/cms/ci/getrelation/'+ciId+'/'+relationId+'?t=' + Math.random() , function(data) {
            		if(data.cis==null)
            			return;
            		var trs;
            		for(i=0;i<data.cis.length;i++)
            		{
            			$("#table_"+relationId+" tbody tr").remove();
            			trs += "<tr>"
            				+ "<td>"+data.cis[i]["name"]+"</td>"
            				+ "<td>"+data.cis[i]["userInMaintenance"]+"</td>"
            				+ "<td>"+data.cis[i]["status"]+"</td>"
            				+ "<td><a class='confirm' href='"+ctx+"/cms/ci/deleterelation?primary_id="+ciId+"&secondary_id="+data.cis[i]["id"]+"&relation_id="+relationId+"'><span class='glyphicon glyphicon-remove'></span></a></td>"
            				+ "</tr>";           			
            		}
            		$("#table_"+relationId+" tbody").append(trs);
                    $(".confirm").bind("click",function(){
                    	if(!confirm("确定要执行该操作?"))
                    		return false;
                    });
            	});
            }
            function addRelation(relationId)
            {
            	$("#secondary_id").attr("value","");
            	$("#relation_id").attr("value",relationId);
            	//$("#relation_id").attr("disabled","disabled");
            	$("#relationFormDialog").modal('show');
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
                                <div class="left"><h4>配置项信息</h4></div>
                            </div>                                  
                        </div>
                         <div class="info">                                                                
                            <ul class="rows">
                            <li>
                                <div class="title">名称:</div>
                                <div class="text">${ci.name}</div>
                            </li>
                             <li>
                                <div class="title">物理位置:</div>
                                <div class="text">${ci.location} </div>
                            </li>
                            <li>
                                <div class="title">使用部门:</div>
                                <div class="text">${ci.departmentInUse }</div>
                            </li> 
                            <li>
                                <div class="title">维护人:</div>
                                <div class="text">${ci.userInMaintenance }</div>
                            </li>
                            <li>
                                <div class="title">服务提供商:</div>
                                <div class="text">${ci.serviceProvider }</div>
                            </li>
                            <li>
                                <div class="title">状态:</div>
                                <div class="text">${ci.status }</div>
                            </li>
                            <li>
                                <div class="title">用途:</div>
                                <div class="text">${ci.purpose }</div>
                            </li>
                            <li>
                                <div class="title">最近更新时间:</div>
                                <div class="text">${ci.lastUpdateTime }</div>
                            </li>   
                            </ul>                                                      
                        </div>                     
               
                    </div>
				<c:forEach items="${properties}" var="map">
                   <div class="col-md-3">
                        <div class="head clearfix">
                            <div class="isw-cloud"></div>
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
	                                <div class="text" id="${property.propertyId}" name="${property.propertyId}">&nbsp;</div>
	                            </li>
                            </c:forEach>
                            </ul>                                                      
                        </div>    

                    </div>
                </c:forEach>  
                
               	</div>
            	
            	<div class="dr"><span></span></div>
            	
            	<div class="row">
            	
            		<div class="col-md-3 clearfix" id="mails_navigation">
            			<div class="block-fluid sNavigation">
                            <ul>
                            	<c:forEach items="${relations }" var="categoryRelation">
                                	<li><a href="#"><span class="glyphicon glyphicon-inbox"></span>${categoryRelation.relation.relationName }</a><span class="arrow"></span></li>
                                </c:forEach>
                            </ul>
                        </div>
            		</div>
            		
            		<div class="col-md-9">
            		<c:forEach items="${relations }" var="categoryRelation">
            			<script>
            			$(document).ready(function(){
            				initTable('${categoryRelation.relation.relationId }');
            			});         				
            			
            			</script>
                        <div class="head clearfix">
                            <div class="isw-cloud"></div>
                            <h1>${categoryRelation.relation.relationName }</h1>
                            <ul class="buttons">        
                                <li class="toggle"><a href="#"></a></li>
                            </ul>                         
                        </div>
                        
  						<div class="block-fluid" >                      
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

                            <div class="toolbar bottom-toolbar clearfix">
                                <div class="left">
                                    <div class="btn-group">
                                        <button onclick="addRelation('${categoryRelation.relation.relationId}')" type="button" class="btn btn-sm btn-warning tip" title="新增"><span class="glyphicon glyphicon-plus glyphicon glyphicon-white"></span></button>
                                        <button type="button" class="btn btn-sm btn-danger tip" title="删除"><span class="glyphicon glyphicon-remove glyphicon glyphicon-white"></span></button>
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
            </div>
            <!--workplace end-->
        </div>   
        <!-- relation modal form -->
        <div class="modal fade" id="relationFormDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4 id="dialogTitle">添加关联</h4>
                    </div>
                    <form id="userForm" action="${contextPath}/cms/ci/saverelation" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">配置项ID:</div>
                                    <div class="col-md-9"><input id="secondary_id" name="secondary_id" type="text" /></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">关系名:</div>
                                    <div class="col-md-9"><input id="relation_id" name="relation_id" type="text" /></div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" aria-hidden="true">保存</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    <input type="hidden" id="primary_id"  name="primary_id" value="${ci.id }" /> 
                    </form>
                </div>
            </div>
        </div>
    	<!-- relation end from -->
    </div>
</body>

</html>