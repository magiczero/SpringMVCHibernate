<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@page import="java.util.*" %>
<%@page import="com.cngc.pm.model.computer.InspectionData" %>
<%@page import="com.cngc.pm.model.SysCode" %>
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
    	var href="${contextPath }/analyse/view/${computerId}/${taskId }/${fields[0].type }";
        $(document).ready(function () {
            $("#eventTable").dataTable({
            	"oLanguage": {
         			"sUrl": "${contextPath}/resources/json/Chinese.json"
     			}});
            $(".header").load("${contextPath}/header?t="+pm_random());
            $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $(".navigation > li:eq(0)").addClass("active"); });
            $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
          
            $(".confirm").bind("click",function(){
            	if(!confirm("确定要执行该操作?"))
                	return false;
        	});
            $( ".accordion" ).accordion({active: ($(".accordion a[href='"+href+"']").parent().parent().parent().index()-1)/2});
           //alert($(".accordion a[href='"+href+"']").parent().parent().parent().index());
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
                	<div class="col-md-3">
                        <div class="head clearfix">
                            <div class="isw-list"></div>
                            <h1>数据集</h1>
                        </div>
                        <div class="block-fluid accordion">
                            <h3>终端基本信息</h3>
                            <div id="div1">
                                 <ul>
                                 	<li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/COMPUTERINFO">主机</a></li>
                                 	<li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/DISK">硬盘</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/DISKPARTITIONINFO">磁盘分区</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/OSINFO">操作系统</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/NETINFO">网络适配器</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/USERINFO">身份鉴别</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/DEVICEINFO">硬件信息</a></li>
                                </ul>                                               
                            </div>

                            <h3>USB</h3>
                            <div calss="active">
                                <ul>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/USBSTORAGE">存储设备</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/USBDEVICE">USB设备</a></li>
                                </ul>                                                
                            </div>   
                            <h3>联网记录</h3>
                            <div>
                                <ul>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/IEHISTORY">上网记录</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/BROWSERHISTORY">浏览器记录</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/IECOOKIE">Cookie记录</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/IECACHE">上网缓存地址</a></li>
                                    <li><a href="#">上网软件记录</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/WEBMAIL">WEB邮箱收发记录</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/INTERNET">终端外联记录</a></li>
                                </ul>                                                
                            </div>  
                            <h3>软件安装</h3>
                            <div>
                                <ul>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/INSTALLEDSOFTWARE">系统软件</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/MAILAPP">邮件客户端</a></li>
                                    <li><a href="#">虚拟机软件</a></li>
                                    <li><a href="#">杀毒软件</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/FILEVHD">镜像文件</a></li>
                                </ul>                                                
                            </div>   
                            <h3>其他辅助检查</h3>
                            <div>
                                <ul>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/EXPLORERRECENT">文件痕迹</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/POWERON">开关机记录</a></li>
                                </ul>                                                
                            </div>  
                            <h3>安全检查</h3>
                            <div>
                                <ul>
                                    <li><a href="#">三合一客户端</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/NETSHAREINFO">系统共享</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/WIRELESS">无线通信</a></li>
                                    <li><a href="#">音视频</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/SYSTEMPATCH">操作系统补丁</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/PROCESSINFO">系统进程信息</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/POLICYINFO">策略</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/ACCOUNT">账户安全配置</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/AUDIT">安全审计配置</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/PRIVILEGE">账户权限配置</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/SERVICEINFO">系统服务</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/PORTSINFO">系统端口</a></li>
                                </ul>                                                
                            </div>   
                            <!-- 
                            <h3>文档搜索</h3>
                            <div>
                                <ul>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/FILEKEY">中标文件</a></li>
                                </ul>                                                
                            </div>
                            <h3>图片文件</h3>
                            <div>
                                <ul>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/PICTUREOCR">中标文件</a></li>
                                </ul>                                                
                            </div>
                            <h3>磁盘深度检查</h3>
                            <div>
                                <ul>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/HISTORYWEB">上网痕迹</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/HISTORYFILE">文件使用痕迹</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/HISTORYUSBDEVICE">USB设备</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/HISTORYUSBSTORAGE">USB存储</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/HISTORYFILELOCAL">本地磁盘文件</a></li>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/HISTORYFILEOTHER">其他存储设备</a></li>
                                </ul>                                                
                            </div>    
                            <h3>已删除文件</h3>
                            <div>
                                <ul>
                                    <li><a href="${contextPath }/analyse/view/${computerId}/${taskId }/DELETEDFILE">中标文件</a></li>
                                </ul>                                                
                            </div>  
                             -->   
                        </div>
                    </div> 
                    <div class="col-md-9">                    
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>详细信息 ${fields[0].type }</h1>  

                            <ul class="buttons">    
                            	<li>
                                    <a href="${contextPath }/computer/task/detail/${taskId}" role="button" data-toggle="modal" class="isw-left_circle tipb" title="返回"></a>
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
                            <table class="table" id="eventTable" style="word-break:break-all">
                                <thead>
                                	<tr>
                                		<th width="60px" class="tac">序号</th>
                                		<c:forEach items="${fields}" var="field">
                                			<th>${field.codeName }</th>
                                		</c:forEach>
                                		<th width="60px">是否合规</th>
									</tr>
                                </thead>
                                <tbody>
                                	<%
                                		ObjectMapper mapper = new ObjectMapper();
                                		List<InspectionData> list = (List<InspectionData>)request.getAttribute("datas");
                                		List<SysCode> codes = (List<SysCode>)request.getAttribute("fields");
                                		for(int i=0;i<list.size();i++){
                                	%>
									<tr>
										<td class="tac"><%=i+1 %></td>
										<%
											Map<String,String> maps = mapper.readValue(list.get(i).getData(),Map.class);
											for (SysCode code : codes) {
										%>
											<td><%=maps.get(code.getCode()) %></td>
										<% 
											}
										%>
										<td><%=list.get(i).isCompliance()?"<span class='label label-success'>合规</span>":"<span class='label label-danger'>不合规</span>" %></td>
									</tr>
									<% } %>
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