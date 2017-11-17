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
    
    <title>终端合规性管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
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
    <link rel='stylesheet' type='text/css' href='${contextPath }/resources/css/fullcalendar.print.css' media='print' />
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
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
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx="${contextPath}";
        $(document).ready(function () {
            $("#eventTable").dataTable({
            	"oLanguage": {
         			"sUrl": "${contextPath}/resources/json/Chinese.json"
     			}});
            $(".header").load("${contextPath}/header?t="+pm_random());
            $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $(".navigation > li:eq(1)").addClass("active"); });
            $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
          
            $(".confirm").bind("click",function(){
            	if(!confirm("确定要执行该操作?"))
                	return false;
        	});
         	//检查记录
            $("#b_pop_data").dialog({
                autoOpen: false,
                width: 800,
                height: 400,
                buttons: { "关闭": function () { $(this).dialog("close") } }
            });
            $(".lnk_data").click(function () {
            	var src= ctx+ '/analyse/compliancebyid/'+ $(this).attr('tid');
            	$("#inspection_data").html("<iframe frameborder='0' src='"+ src +"' width='100%' height='100%' style='padding:0px;'/>");
            	$("#b_pop_data").dialog('open');
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
                    <li><a href="#">终端合规性管理</a> <span class="divider">></span></li>       
                    <li class="active">合规性检查</li>
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
                            <h1>合规性检查汇总</h1>  

                            <ul class="buttons">    
                            	<li>
                                    <a href="${contextPath }/computer/task/list" role="button" data-toggle="modal" class="isw-left_circle tipb" title="返回"></a>
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
                                		<th width="60px" class="tac">序号</th>
										<th width="100px">终端名称</th>
										<th width="60px">无线</th>
										<th width="70px">音视频</th>
										<th width="70px">互联网</th>
										<th width="70px">USB</th>
										<th width="70px">USB存储</th>
										<th width="70px">三合一</th>
										<th width="70px">防病毒</th>
										<th width="70px">软件安装</th>
										<th width="70px">隐藏分区</th>
										<th width="70px">系统共享</th>
										<th width="60px">端口</th>
										<th width="70px">屏保</th>
										<th width="70px">合规性</th>
									</tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list}" var="computerTask" varStatus="st">
									<tr>
										<td class="tac">${st.index+1 }</td>
										<td><a href="${contextPath }/analyse/view/${computerTask.computer.id }/${computerTask.task.id}/COMPUTERINFO">
											${computerTask.computer.userName }</a></td>
										<td>
											<c:if test="${not empty reportmap.get(computerTask.id).get('WIRELESS')}">
												<c:if test="${reportmap.get(computerTask.id).get('WIRELESS')}">
												<span class='label label-success'>合规</span>
												</c:if>
												<c:if test="${!reportmap.get(computerTask.id).get('WIRELESS')}">
												<a href="#" tid="${computerTask.id }/0/WIRELESS" class="lnk_data"><span class='label label-danger'>不合规</span></a>
												</c:if>
											</c:if>
										</td>
										<td>
											<c:if test="${not empty reportmap.get(computerTask.id).get('VIDEO')}">
												<c:if test="${reportmap.get(computerTask.id).get('VIDEO')}">
												<span class='label label-success'>合规</span>
												</c:if>
												<c:if test="${!reportmap.get(computerTask.id).get('VIDEO')}">
												<a href="#" tid="${computerTask.id }/0/VIDEO" class="lnk_data"><span class='label label-danger'>不合规</span></a>
												</c:if>
											</c:if>
										</td>
										<td>
											<c:if test="${not empty reportmap.get(computerTask.id).get('INTERNET')}">
												<c:if test="${reportmap.get(computerTask.id).get('INTERNET')}">
												<span class='label label-success'>合规</span>
												</c:if>
												<c:if test="${!reportmap.get(computerTask.id).get('INTERNET')}">
												<a href="#" tid="${computerTask.id }/0/INTERNET" class="lnk_data"><span class='label label-danger'>不合规</span></a>
												</c:if>
											</c:if>
										</td>
										<td>
											<c:if test="${not empty reportmap.get(computerTask.id).get('USBDEVICE')}">
												<c:if test="${reportmap.get(computerTask.id).get('USBDEVICE')}">
												<span class='label label-success'>合规</span>
												</c:if>
												<c:if test="${!reportmap.get(computerTask.id).get('USBDEVICE')}">
												<a href="#" tid="${computerTask.id }/0/USBDEVICE" class="lnk_data"><span class='label label-danger'>不合规</span></a>
												</c:if>
											</c:if>
										</td>
										<td>
											<c:if test="${not empty reportmap.get(computerTask.id).get('USBSTORAGE')}">
												<c:if test="${reportmap.get(computerTask.id).get('USBSTORAGE')}">
												<span class='label label-success'>合规</span>
												</c:if>
												<c:if test="${!reportmap.get(computerTask.id).get('USBSTORAGE')}">
												<a href="#" tid="${computerTask.id }/0/USBSTORAGE" class="lnk_data"><span class='label label-danger'>不合规</span></a>
												</c:if>
											</c:if>
										</td>
										<td>
											<c:if test="${not empty reportmap.get(computerTask.id).get('SHY')}">
												<c:if test="${reportmap.get(computerTask.id).get('SHY')}">
												<a href="#" tid="${computerTask.id }/1/SHY" class="lnk_data"><span class='label label-success'>合规</span></a>
												</c:if>
												<c:if test="${!reportmap.get(computerTask.id).get('SHY')}">
												<a href="#" tid="${computerTask.id }/0/SHY" class="lnk_data"><span class='label label-danger'>不合规</span></a>
												</c:if>
											</c:if>
										</td>
										<td>
											<c:if test="${not empty reportmap.get(computerTask.id).get('ANTIVIRUS')}">
												<c:if test="${reportmap.get(computerTask.id).get('ANTIVIRUS')}">
												<a href="#" tid="${computerTask.id }/1/ANTIVIRUS" class="lnk_data"><span class='label label-success'>合规</span></a>
												</c:if>
												<c:if test="${!reportmap.get(computerTask.id).get('ANTIVIRUS')}">
												<a href="#" tid="${computerTask.id }/0/ANTIVIRUS" class="lnk_data"><span class='label label-danger'>不合规</span></a>
												</c:if>
											</c:if>
										</td>
										
										<td>
											<c:if test="${not empty reportmap.get(computerTask.id).get('INSTALLEDSOFTWARE')}">
												<c:if test="${reportmap.get(computerTask.id).get('INSTALLEDSOFTWARE')}">
												<span class='label label-success'>合规</span>
												</c:if>
												<c:if test="${!reportmap.get(computerTask.id).get('INSTALLEDSOFTWARE')}">
												<a href="#" tid="${computerTask.id }/0/INSTALLEDSOFTWARE" class="lnk_data"><span class='label label-danger'>不合规</span></a>
												</c:if>
											</c:if>
										</td>
										<td>
											<c:if test="${not empty reportmap.get(computerTask.id).get('DISKPARTITIONINFO')}">
												<c:if test="${reportmap.get(computerTask.id).get('DISKPARTITIONINFO')}">
												<span class='label label-success'>合规</span>
												</c:if>
												<c:if test="${!reportmap.get(computerTask.id).get('DISKPARTITIONINFO')}">
												<a href="#" tid="${computerTask.id }/0/DISKPARTITIONINFO" class="lnk_data"><span class='label label-danger'>不合规</span></a>
												</c:if>
											</c:if>
										</td>
										<td>
											<c:if test="${not empty reportmap.get(computerTask.id).get('NETSHAREINFO')}">
												<c:if test="${reportmap.get(computerTask.id).get('NETSHAREINFO')}">
												<span class='label label-success'>合规</span>
												</c:if>
												<c:if test="${!reportmap.get(computerTask.id).get('NETSHAREINFO')}">
												<a href="#" tid="${computerTask.id }/0/NETSHAREINFO" class="lnk_data"><span class='label label-danger'>不合规</span></a>
												</c:if>
											</c:if>
										</td>
										<td>
											<c:if test="${not empty reportmap.get(computerTask.id).get('PORTSINFO')}">
												<c:if test="${reportmap.get(computerTask.id).get('PORTSINFO')}">
												<span class='label label-success'>合规</span>
												</c:if>
												<c:if test="${!reportmap.get(computerTask.id).get('PORTSINFO')}">
												<a href="#" tid="${computerTask.id }/0/PORTSINFO" class="lnk_data"><span class='label label-danger'>不合规</span></a>
												</c:if>
											</c:if>
										</td>
										
										<td>
											<c:if test="${not empty reportmap.get(computerTask.id).get('SCREENSAVE')}">
												<c:if test="${reportmap.get(computerTask.id).get('SCREENSAVE')}">
												<span class='label label-success'>合规</span>
												</c:if>
												<c:if test="${!reportmap.get(computerTask.id).get('SCREENSAVE')}">
												<a href="#" tid="${computerTask.id }/0/SCREENSAVE" class="lnk_data"><span class='label label-danger'>不合规</span></a>
												</c:if>
											</c:if>
										</td>
										<td>
											<c:if test="${computerTask.status.equals('04') }">
												<c:if test="${computerTask.compliance }">
													<span class='label label-success'>合规</span>
												</c:if>
												<c:if test="${!computerTask.compliance }">
													<span class='label label-danger'>不合规</span>
												</c:if>
											</c:if>
											<c:if test="${!computerTask.status.equals('04') }">
												<span class='label label-warning'>未知</span>
											</c:if>
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
    <!-- 详细记录 -->
   	<div class="" id="b_pop_data" style="display: none;padding:0px 0px 0px 0px;" title="详细记录">
    	<div id="inspection_data" height="100%" style="height:100%;padding:0px 0px 0px 0px;">
		</div>
   	</div>
</body>

</html>