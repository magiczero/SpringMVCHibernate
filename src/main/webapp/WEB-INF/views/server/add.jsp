<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加服务器</title>
<link rel="stylesheet" type="text/css"  href="${contextPath }/resources/style/jquery-ui.min.css">
<script type="text/javascript" src="${contextPath }/resources/js/jquery.js"></script>
<script type="text/javascript" src="${contextPath }/resources/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${contextPath }/resources/js/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript">
$(function() {
    $("#proDate").datepicker({
    	changeMonth: true,
        changeYear: true
    })
    
    $("#purTime").datepicker({
    	changeMonth: true,
        changeYear: true
    })
});
</script>
<style type="text/css">
   .error { color: red;}
</style>
</head>
<body>
<c:url var="addAction" value="/Server/save" ></c:url>
<form:form action="${addAction}" commandName="server">
<table>
	<tr>
		<td>
			<form:label path="assetNum">资产编号</form:label>
		</td>
		<td>
			<form:input path="assetNum" />
			<form:errors path="assetNum" cssClass="error" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="secretNum">涉密编号</form:label>
		</td>
		<td>
			<form:input path="secretNum" />
			<form:errors path="secretNum" cssClass="error" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="brand">品牌</form:label>
		</td>
		<td>
			<form:input path="brand" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="model">设备型号</form:label>
		</td>
		<td>
			<form:input path="model" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="snNum">SN</form:label>
		</td>
		<td>
			<form:input path="snNum" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="equipType.id">设备类型</form:label>
		</td>
		<td>
			<form:select path="equipType.id" multiple="false" items="${styles }" >
			</form:select>
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="secretLevel">密级</form:label>
		</td>
		<td>
			<form:select path="secretLevel" multiple="false">
				<c:forEach items="${levels }" var="level">
				<form:option value="${level.value }">${level.level }</form:option> 
				</c:forEach>
			</form:select>
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="purpose">用途</form:label>
		</td>
		<td>
			<form:input path="purpose" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="productionDate">生产日期</form:label>
		</td>
		<td>
			<form:input path="productionDate"  id="proDate"/>
		</td> 
	</tr>
	
	<tr>
		<td>
			<form:label path="purchaseTime">购置时间</form:label>
		</td>
		<td>
			<form:input path="purchaseTime"  id="purTime"/>
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="cpu">CPU</form:label>
		</td>
		<td>
			<form:input path="cpu" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="memory">内存</form:label>
		</td>
		<td>
			<form:input path="memory" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="cdrom">光驱</form:label>
		</td>
		<td>
			<form:input path="cdrom" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="raidCard">raid卡</form:label>
		</td>
		<td>
			<form:input path="raidCard" />
		</td> 
	</tr><tr>
		<td>
			<form:label path="raidModel">raid模式</form:label>
		</td>
		<td>
			<form:input path="raidModel" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="powerNum">电源数量</form:label>
		</td>
		<td>
			<form:input path="powerNum"  value="1"/>
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