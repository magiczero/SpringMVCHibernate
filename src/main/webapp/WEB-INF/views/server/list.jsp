<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>服务器列表</title>
</head>
<body>
<c:url var="addAction" value="/Server/add" ></c:url>
<a href="${addAction }">添加</a>服务器
<table cellspacing="4">
	<tr>
		<td>资产编号</td>
		<td>涉密编号</td>
		<td>品牌</td>
		<td>型号</td>
		<td>SN</td>
		<td>类型</td>
		<td>密级</td>
		<td>用途</td>
		<td>生产日期</td>
		<td>购置时间</td>
		<td>CPU</td>
		<td>内存</td>
		<td>光驱</td>
		<td>raid卡</td>
		<td>raid模式</td>
		<td>电源数量</td>
	</tr>
	<c:forEach items="${listServers}" var="server">
	<tr>
		<td>${server.assetNum }</td>
		<td>${server.secretNum }</td>
		<td>${server.brand}</td>
		<td>${server.model }</td>
		<td>${server.snNum }</td>
		<td>${server.equipType.name }</td>
		<td>${server.secretLevel.level }</td>
		<td>${server.purpose }</td>
		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${server.productionDate }" /></td>
		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${server.purchaseTime }" /></td>
		<td>${server.cpu }</td>
		<td>${server.memory }</td>
		<td>${server.cdrom }</td>
		<td>${server.raidCard }</td>
		<td>${server.raidModel }</td>
		<td>${server.powerNum }</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>