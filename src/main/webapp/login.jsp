<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎使用</title>
</head>
<body>
<h2>用户登录：</h2><br/>
${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message} <!-- 输出异常信息 -->
<form name="f" action="${pageContext.request.contextPath}/j_spring_security_check" method="POST">
	user : <input type="text" name="j_username" /><br/>
	password : <input type="password" name="j_password" /><br />
	<input name="submit"  type="submit"/>
</form>
</body>
</html>