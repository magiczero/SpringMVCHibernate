<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>服务器软件列表</title>

    <link href="${contextPath }/resources/css/stylesheets.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
        <link href="css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/fnReloadAjax.js'></script>    
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/cookies.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/actions.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/charts.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/mail.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/settings.js'></script>  
    <script type="text/javascript">
	    $(document).ready(function () {
	    	$("#createBtn").click(function() {
	    		window.location.href="${contextPath }/software/addbyserver/${serverid}";
	    	});
	    	
	    	$("#deleteBtn").click(function() {
	    		if(confirm("确定执行删除操作？")) {
		    		var arr = [];
		    		$("input[name=ids]").each(function() {
		    			if($(this).attr("checked")=='checked') {
		    				arr.push($(this).val());
		    			}
		    		});
		    		if(arr=='') {
		    			$("div.alert").removeClass('hide');
		    			return false;
		    		} else {
		    			$("div.alert").addClass('hide');
		    			$.ajax({
		    				type : "post",
		    				dataType : "json",
		    				url : "${contextPath }/networkcard/delete",
		    				data : {ids : arr.join(",")},
		    				success : function(data) {
		    					if (data.flag == "true") {
		    						
		    						window.location.href="${contextPath }/server/listsoftware/"+data.serverid;
		    						
		    					} else {
		    						alert('删除失败');
		    					}
		    				}
		    			});
		    		}
	    		}
	    	});
	    	
	    	$("#datalist").dataTable({
	    		"bPaginate": false, //翻页功能
	    		"bLengthChange": false, //改变每页显示数据数量
	    		"bFilter": false, //过滤功能
	    		"bSort": false, //排序功能
	    		"bInfo": false,//页脚信息
	    		"bAutoWidth": false//自动宽度
	    	});
	    });
	    
    </script>
</head>
<body>
 <div class="wrapper">
  	<div class="breadLine">
            </div>
            <div class="workplace">          
                
                <div class="page-header">
                    <h1>服务器 <small>软件列表</small></h1>
                </div>  
                <div class="alert alert-danger hide">                
                    <h4>错误!</h4>请至少选择一项
                </div> 
                <div class="row">

                    <div class="col-md-12" id="mails">
                        <div class="block-fluid" id="inbox">
                            <div class="toolbar clearfix">
                                <div class="left">
                                    <div class="btn-group">
                                        <button id="createBtn" class="btn btn-sm btn-success tip" title="新建"><span class="glyphicon glyphicon-share-alt glyphicon glyphicon-white"></span></button>
                                        <button class="btn btn-sm btn-warning tip" title="Spam"><span class="glyphicon glyphicon-warning-sign glyphicon glyphicon-white"></span></button>
                                        <button id="deleteBtn" class="btn btn-sm btn-danger tip" title="删除"><span class="glyphicon glyphicon-trash glyphicon glyphicon-white"></span></button>
                                    </div>
                                </div>
                            </div>
                            <table cellpadding="0" cellspacing="0" width="100%" class="table" id="datalist">
                                <thead>
                                    <tr>
                                        <th><input type="checkbox" name="ids"/></th>
                                        <th width="20%">软件编号</th>
                                        <th width="20%">软件名称</th>
                                        <th width="15%">版本</th>
                                        <th width="25%">备注</th>                                                                        
                                    </tr>
                                </thead>
                               <tbody> <c:forEach items="${softwares}" var="serversoft">
                               	<tr>
                               		<td><input type="checkbox" name="ids" value="${serversoft.id }"/></td>
                               		<td>${serversoft.software.num }</td>
                               		<td>${serversoft.software.name }</td>
                               		<td>${serversoft.software.versions }</td>
                               		<td>${serversoft.remark }</td>
                               	</tr></c:forEach>
                               </tbody>
                            </table>                       
                                                                                                
                        </div>


                    </div>                

                </div>            

            </div>
 </div>
</body>
</html>