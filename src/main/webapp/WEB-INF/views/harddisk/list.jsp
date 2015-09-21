<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>所有硬盘列表</title>
<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<c:url var="addAction" value="/harddisk/add" ></c:url>
<a href="${addAction }">添加</a>一块硬盘
<h3>硬盘列表</h3>
<c:if test="${!empty diskList}">
	<table class="tg">
	<tr>
		<th width="180">序列号</th>
		<th width="120">硬盘接口</th>
		<th width="120">硬盘类型</th>
		<th width="60">容量</th>
		<th width="60">服务器</th>
	</tr>
	<c:forEach items="${diskList}" var="disk">
		<tr>
			<td>${disk.serialNum }</td>
			<td>${disk.interf }</td>
			<td>${disk.type}</td>
			<td>${disk.capacity }</td>
			<td>${disk.severName }</td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>