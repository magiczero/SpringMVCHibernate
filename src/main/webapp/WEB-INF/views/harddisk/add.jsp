<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>填写硬盘信息</title>
</head>
<body>
<c:url var="addAction" value="/harddisk/save" ></c:url>
<form:form action="${addAction}" commandName="harddisk">
<form:hidden path="server.id" value="${serverid }"/>
<table>
	<tr>
		<td>
			<form:label path="serialNum">序列号	</form:label>
		</td>
		<td>
			<form:input path="serialNum" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="interf">接口</form:label>
		</td>
		<td>
			<form:input path="interf" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="type">类型</form:label>
		</td>
		<td>
			<form:input path="type" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="capacity">容量</form:label>
		</td>
		<td>
			<form:input path="capacity" />
		</td> 
	</tr>
	<tr>
		<td colspan="2">
				<input type="submit"
					value="添加" />
		</td>
	</tr>
</table>	
</form:form>
</body>
</html>