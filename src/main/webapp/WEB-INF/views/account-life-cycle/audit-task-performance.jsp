<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>审核任务执行情况--运维管理系统</title>

    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/fullcalendar.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/uniform.default.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/cleditor.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/fancybox/jquery.fancybox.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/elfinder.min.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/elfinder.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/multi-select.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ibutton.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stepy.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/tagsinput.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/dataTables.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
	<link href="${contextPath }/resources/css/stylesheets2.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap-submenu.min.css" rel="stylesheet" type="text/css" />
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
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/submenu/bootstrap-submenu.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/sparklines/jquery.sparkline.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fullcalendar/fullcalendar.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/animatedprogressbar/animated_progressbar.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cleditor/jquery.cleditor.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
        
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/jquery.form.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/cookies.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/settings.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    var ctx = "${contextPath}";
    
    $(document).ready(function () {
		$(".header").load("${contextPath }/header?t="+pm_random());
        $(".menu").load("${contextPath }/menu?t="+pm_random(), function() {$("#node_${moduleId}").addClass("active");});
        $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
           
        $('#end').click(function(){
        	if(confirm('是否结束此次审核？')){
            	
        		$.post("${contextPath }/account-life-cycle/end-audit-task/${at.id}?nonforce=false", function(data) {

    				if(data.flag){
    					//table.fnDraw();
    					window.location.href='${contextPath }/account-life-cycle/audit-task/index';
    				} else
    					if(data.force){
    						if(confirm(data.msg+'，是否强制结束此次审核？')){
    		                	
    		            		$.post("${contextPath }/account-life-cycle/end-audit-task/${at.id}?nonforce=true", function(data) {
    		
    								if(data.flag){
    									//table.fnDraw();
    									alert('审核已结束');	
    								}else
    									alert("操作失败，原因："+data.msg);
    	            			},"json");
    		            		return true;
    	            		} else
    	            			return false;
    					} else
    						alert("操作失败，原因："+data.msg);
    			},"json");
        		return true;
    		} else
    			return false;
        });
    });
    
    function viewGroup(id) {
    	$("#view0").load("${contextPath }/account-life-cycle/audit-task/details?at=${at.id}&t="+pm_random());
    	$("#detailsModal").modal('show');
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
                    <li><a href="#">台账管理</a> <span class="divider">></span></li>
                    <li><a href="index">台账审核</a> <span class="divider">></span></li><li><a href="#">台账审核任务执行情况</a></li>    
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">             

                <div class="row">
<input type="hidden" id="group0" value="">
                    <div class="col-md-12" >
                        	<input type="hidden" id="categorycode" value="0">
						<div class="head clearfix">
						<div class="isw-grid"></div>
                            <h1>审核任务执行情况</h1>
                            <ul class="buttons">
                                <li>
                                    <a class="isw-settings" href="#"></a>
                                    <ul class="dd-list">
                                        <c:if test="${isAccountMaster }"> <li><a data-toggle="modal" href="#eModal"><span class="isw-edit"></span> 按部门查看</a></li></c:if>
                                        <li><a href="javascript:void(0);" id="end"><span class="isw-edit"></span> 结束审核</a></li>
                                    </ul>
                                </li>
                            </ul>                        
                        </div>
                        <div class="block-fluid">
                            <table  class="table">
                                <thead>
                                    <tr>
                                       	<th width="6%"></th>
                                        <th>部门</th>
                                        <th width="10%">是否提交</th>
										<th width="15%">台账类别</th>
										<th width="8%">已确认</th>
										<th width="8%">未确认</th>
										<th width="8%">审核中</th>
										<th width="8%">审核通过</th>
										<th width="8%">审核未通过</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="item0">
                                	<tr>
                                		<td></td>
                                		<td><a title="查看详细信息" href="javascript:void(0);" onclick="viewGroup(${item0.groupId});">${item0.groupName }</a></td>
                                		<td>${item0.commit0 }</td>
                                		<td>${item0.categoryName }</td>
                                		<td>${item0.verity }</td>
                                		<td>${item0.unVerity }</td>
                                		<td>${item0.auditing }</td>
                                		<td>${item0.auditPass }</td>
                                		<td>${item0.auditUnPass }</td>
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
        
         <!-- Bootrstrap modal form -->
        <div class="modal fade" id="detailsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width:1000px">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>详细信息</h4>
                    </div>
                    <div class="modal-body modal-body-np">
                        <div class="row">
                        <div class="block-fluid">
                        <div class="row-form clearfix">
                        <div id="view0" class="col-md-12" >
						
                            </div>
                            </div>             
                            </div>
                        </div>
                    </div>   
                </div>
            </div>
        </div>
    </div>
</body>

</html>