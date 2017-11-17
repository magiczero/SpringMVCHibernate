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
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
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
    	var ctx="${contextPath}";
        $(document).ready(function () {
            $("#eventTable").dataTable({
            	"oLanguage": {
         			"sUrl": "${contextPath}/resources/json/Chinese.json"
     			},"aoColumns": [ { "bSortable": false }, null, null,null, {"bSortable":false}]});
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
            	var src= ctx+ '/analyse/compliance/'+ $(this).attr('tid');
            	$("#inspection_data").html("<iframe frameborder='0' src='"+ src +"' width='100%' height='100%' style='padding:0px;'/>");
            	$("#b_pop_data").dialog('open');
            	return false;
            });
          	//终端记录
            $("#b_pop_computer").dialog({
                autoOpen: false,
                width: 800,
                height: 400,
                buttons: { "关闭": function () { $(this).dialog("close") } }
            });
            $(".lnk_computer").click(function () {
            	var src= ctx+ '/analyse/compliance-computer/'+ $(this).attr('tid');
            	$("#inspection_computer").html("<iframe frameborder='0' src='"+ src +"' width='100%' height='100%' style='padding:0px;'/>");
            	$("#b_pop_computer").dialog('open');
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
                    <li><a href="#">终端合规性管理</a> <span class="divider">></span></li>       
                    <li class="active">合规性检查</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">             
                <div class="row">
                	<div class="col-md-3">
                	   <a href="${contextPath }/computer/task/list" role="button" class="btn btn-default btn-block">返回</a>
                       <div class="headInfo">
                            <div class="toolbar nopadding-toolbar clear clearfix">
                                <div class="left text-info"><h4>合规性检查任务</h4> </div>
                            </div>                                  
                        </div>
                         <div class="block-fluid ucard">                                                                
                            <ul class="rows">
                            <li>
                                <div class="title">任务名称:</div>
                                <div class="text">${task.taskName } </div>
                            </li>
                            <li>
                                <div class="title">任务提示信息:</div>
                                <div class="text">${task.taskInfo } </div>
                            </li>
                            <li>
                                <div class="title">任务创建时间:</div>
                                <div class="text"><fmt:formatDate value="${task.createDate }" pattern="yyyy/MM/dd HH:mm:ss" /> </div>
                            </li>
                            <li>
                                <div class="title">检查终端总数:</div>
                                <div class="text">${ComputerCount } </div>
                            </li>
                            <li>
                                <div class="title">已完成终端数:</div>
                                <div class="text">${EndedComputerCount} </div>
                            </li> 
                            </ul>                                                      
                        </div>                     
               
                    </div>
                    <div class="col-md-9">
                    	
                    	<div class="row">
                    		<div class="col-md-6">
                    			<div class="head clearfix">
		                            <div class="isw-grid"></div>
		                            <h1>合规客户端</h1>
		                        </div>
		                        <div class="block tac"><h1>${s_compliance }</h1></div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="head clearfix">
		                            <div class="isw-grid"></div>
		                            <h1>不合规客户端</h1>
		                        </div>
		                        <div class="block tac"><h1 class="text-danger">${s_discompliance }</h1></div>
                    		</div>
                    	</div>
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>硬件设备</h1>
                        </div>             
                        <div class="block " > 
                        
                        	<div class="row-form clearfix">
                        		<div class="col-md-2"><span class="glyphicon glyphicon-signal"></span> <span class="text">无线通信</span></div>
                        		<div class="col-md-10">
                        			<div class="row"><span class="text">涉密信息系统、涉密信息设备和涉密存储设备，不得具有无线通信功能，不得连接具有无线通信功能的设备。</span></div>
                        			<div class="row">
                        				<div class="col-md-6"><span class="text">合规客户端:</span> 
                        				<span class="label label-success"><a href="#" tid="${task.id}/1/WIRELESS" class="lnk_computer">${ComputerCount-ls_wireless.size()}</a></span>
                        				</div>
                        				<div class="col-md-6"><span class="text">不合规客户端:</span> 
                        					<span class="label label-danger"><a href="#" tid="${task.id }/0/WIRELESS" class="lnk_computer">${ls_wireless.size() }</a></span>
                        					<a href="#" tid="${task.id }/0/WIRELESS" class="lnk_data">查看记录</a>
                        				</div>
                        			</div>
                        		</div>
                        	</div>
                        	<div class="row-form clearfix">
                        		<div class="col-md-2"><span class="glyphicon glyphicon-facetime-video"></span> <span class="text">音视频设备</span></div>
                        		<div class="col-md-10">
                        			<div class="row"><span class="text">不得安装音视频设备</span></div>
                        			<div class="row">
                        			<div class="col-md-6"><span class="text">合规客户端数:</span> 
                        				<span class="label label-success"><a href="#" tid="${task.id}/1/VIDEO" class="lnk_computer">${ComputerCount-ls_video.size() }</a></span></div>
                        			<div class="col-md-6"><span class="text">不合规客户端数:</span> 
                        				<span class="label label-danger"><a href="#" tid="${task.id }/0/VIDEO" class="lnk_computer">${ls_video.size() }</a></span>
                        				<a href="#" tid="${task.id }/0/VIDEO" class="lnk_data">查看记录</a>
                        			</div>
                        			</div>
                        		</div>
                        	</div>
                        </div>                  
                      
                      <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>违规外联</h1>
                        </div>             
                        <div class="block " > 
                        	<div class="row-form clearfix">
                        		<div class="col-md-2"><span class="glyphicon glyphicon-globe"></span> <span class="text">违规连接互联网</span></div>
                        		<div class="col-md-10">
                        			<div class="row"><span class="text">涉密信息系统、涉密信息设备和涉密存储设备接入互联网及其他
                        			公共信息网络、涉密信息处于不可控状态，直接危害国家秘密安全。涉密信息系统、涉密信息设备和涉密存储设备
                        			的管理和使用过程中应防止接入互联网及其他公共信息网络。</span></div>
	                        		<div class="row">
		                        		<div class="col-md-6"><span class="text">未连接互联网客户端数:</span> 
		                        			<span class="label label-success"><a href="#" tid="${task.id }/1/INTERNET" class="lnk_computer">${ComputerCount-ls_internet.size()}</a></span></div>
		                        		<div class="col-md-6"><span class="text">连接互联网客户端数:</span> 
		                        		<span class="label label-danger"><a href="#" tid="${task.id }/0/INTERNET" class="lnk_computer">${ls_internet.size() }</a></span>
		                        		<a href="#" tid="${task.id }/0/INTERNET" class="lnk_data">查看记录</a></div>
	                        		</div>
                        		</div>
                        	</div>
                        	<div class="row-form clearfix">
                        		<div class="col-md-2"><span class="glyphicon glyphicon-chevron-right"></span> <span class="text">USB设备</span></div>
                        		<div class="col-md-10">
                        			<div class="row"><span class="text">未经审批，不得私自连接和拆卸设备。</span></div>
                        			<div class="row">
                        				<div class="col-md-6"><span class="text">合规客户端数:</span> <span class="label label-success"><a href="#" tid="${task.id }/1/USBDEVICE" class="lnk_computer">${ComputerCount-ls_usbdevice.size()}</a></span></div>
                        				<div class="col-md-6"><span class="text">疑似违规客户端数:</span> <span class="label label-danger"><a href="#" tid="${task.id }/0/USBDEVICE" class="lnk_computer">${ls_usbdevice.size() }</a></span>
                        				<a href="#" tid="${task.id }/0/USBDEVICE" class="lnk_data">查看记录</a></div>
                        			</div>
                        		</div>
                        	</div>
                        	<div class="row-form clearfix">
                        		<div class="col-md-2"><span class="glyphicon glyphicon-hdd"></span> <span class="text">USB存储设备</span></div>
                        		<div class="col-md-10">
                        			<div class="row"><span class="text">数据导入导出应当采用国家保密行政管理部门批准的单向导入设备或者使用中间转换机。禁止普通移动存储设备交叉使用。</span></div>
                        			<div class="row">
                        				<div class="col-md-6"><span class="text">合规客户端数:</span> <span class="label label-success"><a href="#" tid="${task.id }/1/USBSTORAGE" class="lnk_computer">${ComputerCount-ls_usbstorage.size()}</a></span></div>
                        				<div class="col-md-6"><span class="text">疑似违规客户端数:</span> <span class="label label-danger"><a href="#" tid="${task.id }/0/USBSTORAGE" class="lnk_computer">${ls_usbstorage.size() }</a></span>
                        				<a href="#" tid="${task.id }/0/USBSTORAGE" class="lnk_data">查看记录</a></div>
                        			</div>
                        		</div>
                        	</div>
                        	<div class="row-form clearfix">
                        		<div class="col-md-2"><span class="glyphicon glyphicon-bell"></span> <span class="text">三合一</span></div>
                        		<div class="col-md-10">
                        			<div class="row"><span class="text">采用国家保密行政管理部门批准的违规外联监控等技术措施，
                        			防止涉密信息系统、涉密信息设备和涉密存储设备接入互联网及其他公共信息网络。。</span></div>
                        			<div class="row">
                        				<div class="col-md-6"><span class="text">已安装三合一客户端数:</span> 
                        				<span class="label label-success"><a href="#" tid="${task.id }/1/SHY" class="lnk_data">${ComputerCount-ls_shy.size()}</a></span>
                        				<a href="#" tid="${task.id }/1/SHY" class="lnk_data">查看记录</a></div>
                        				<div class="col-md-6"><span class="text">未安装三合一客户端数:</span> 
                        				<span class="label label-danger"><a href="#" tid="${task.id }/0/SHY" class="lnk_data">${ls_shy.size()}</a></span></div>
                        			</div>
                        		</div>
                        	</div>
                        </div> 
                        
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>软件安装</h1>
                        </div>             
                        <div class="block " > 
                       	 	<div class="row-form clearfix">
                        		<div class="col-md-2"><span class="glyphicon glyphicon-chevron-right"></span> <span class="text">防病毒软件</span></div>
                        		<div class="col-md-10">
                        			<div class="row"></div>
                        			<div class="row">
                        				<div class="col-md-6"><span class="text">已安装防病毒客户端数:</span> 
                        					<span class="label label-success"><a href="#" tid="${task.id }/1/ANTIVIRUS" class="lnk_computer">${ComputerCount-ls_antivirus.size()}</a></span>
                        					<a href="#" tid="${task.id }/1/ANTIVIRUS" class="lnk_data">查看记录</a></div>
                        				<div class="col-md-6"><span class="text">未安装防病毒客户端数:</span> 
                        				<span class="label label-danger"><a href="#" tid="${task.id }/0/ANTIVIRUS" class="lnk_computer">${ls_antivirus.size() }</a></span></div>
                        			</div>
                        		</div>
                        	</div>
                        	<div class="row-form clearfix">
                        		<div class="col-md-2"><span class="glyphicon glyphicon-chevron-right"></span> <span class="text">软件安装</span></div>
                        		<div class="col-md-10">
                        			<div class="row"><span class="text">未履行审批程序，禁止任何软硬件的安装、扩展、缩减、拆卸。</span></div>
                        			<div class="row">
                        				<div class="col-md-6"><span class="text">合规客户端数:</span> <span class="label label-success"><a href="#" tid="${task.id }/1/INSTALLEDSOFTWARE" class="lnk_computer">${ComputerCount-ls_software.size()}</a></span></div>
                        				<div class="col-md-6"><span class="text">安装软件白名单之外的客户端数:</span> <span class="label label-danger"><a href="#" tid="${task.id }/0/INSTALLEDSOFTWARE" class="lnk_computer">${ls_software.size() }</a></span>
                        				<a href="#" tid="${task.id }/0/INSTALLEDSOFTWARE" class="lnk_data">查看记录</a></div>
                        			</div>
                        		</div>
                        	</div>
                        </div>
                       
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>系统运行安全</h1>
                        </div>             
                        <div class="block " > 
                        	<div class="row-form clearfix">
                        		<div class="col-md-2"><span class="glyphicon glyphicon-chevron-right"></span> <span class="text">隐藏分区</span></div>
                        		<div class="col-md-5">合规客户端数: 
                        			<span class="label label-success"><a href="#" tid="${task.id}/1/DISKPARTITIONINFO" class="lnk_computer">${ComputerCount-ls_hiddendisk.size()}</a></span></div>
                        		<div class="col-md-5">不合规客户端数: 
                        			<span class="label label-danger"><a href="#" tid="${task.id }/0/DISKPARTITIONINFO" class="lnk_computer">${ls_hiddendisk.size()}</a></span>
                        			<a href="#" tid="${task.id }/0/DISKPARTITIONINFO" class="lnk_data">查看记录</a></div>
                        	</div>
                        	<div class="row-form clearfix">
                        		<div class="col-md-2"><span class="glyphicon glyphicon-chevron-right"></span> <span class="text">系统共享</span></div>
                        		<div class="col-md-5">合规客户端数: 
                        		<span class="label label-success"><a href="#" tid="${task.id}/1/NETSHAREINFO" class="lnk_computer">${ComputerCount-ls_netshare.size()}</a></span></div>
                        		<div class="col-md-5">不合规客户端数: 
                        		<span class="label label-danger"><a href="#" tid="${task.id }/0/NETSHAREINFO" class="lnk_computer">${ls_netshare.size()}</a></span>
                        		<a href="#" tid="${task.id }/0/NETSHAREINFO" class="lnk_data">查看记录</a></div>
                        	</div>
                        	<div class="row-form clearfix">
                        		<div class="col-md-2"><span class="glyphicon glyphicon-chevron-right"></span> <span class="text">端口开放</span></div>
                        		<div class="col-md-5">合规客户端数: 
                        		<span class="label label-success"><a href="#" tid="${task.id}/1/PORTSINFO" class="lnk_computer">${ComputerCount-ls_ports.size()}</a></span></div>
                        		<div class="col-md-5">不合规客户端数: 
                        		<span class="label label-danger"><a href="#" tid="${task.id }/0/PORTSINFO" class="lnk_computer">${ls_ports.size() }</a></span>
                        		<a href="#" tid="${task.id }/0/PORTSINFO" class="lnk_data">查看记录</a></div>
                        	</div>
                        	<div class="row-form clearfix">
                        		<div class="col-md-2"><span class="glyphicon glyphicon-chevron-right"></span> <span class="text">屏保设置</span></div>
                        		<div class="col-md-5">合规客户端数: 
                        			<span class="label label-success"><a href="#" tid="${task.id}/1/SCREENSAVE" class="lnk_computer">${ComputerCount-ls_screensave.size()}</a></span></div>
                        		<div class="col-md-5">不合规客户端数: 
                        			<span class="label label-danger"><a href="#" tid="${task.id }/0/SCREENSAVE" class="lnk_computer">${ls_screensave.size()}</a></span>
                        			<a href="#" tid="${task.id }/0/SCREENSAVE" class="lnk_data">查看记录</a></div>
                        	</div>
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
   	<!-- 终端记录 -->
   	<div class="" id="b_pop_computer" style="display: none;padding:0px 0px 0px 0px;" title="终端记录">
    	<div id="inspection_computer" height="100%" style="height:100%;padding:0px 0px 0px 0px;">
		</div>
   	</div>
</body>

</html>