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
                    <div class="col-md-12" style="padding-top:15px;">                    
                        <div class="block table-sorting clearfix without-head">
                            <table class="table" id="eventTable" style="word-break:break-all">
                                <thead>
                                	<tr>
                                		<th width="60px">序号</th>
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
										<td><%=i+1 %></td>
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
     	
</body>

</html>