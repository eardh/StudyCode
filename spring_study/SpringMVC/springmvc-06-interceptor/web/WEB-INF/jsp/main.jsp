<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/10
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>首页</h1>
<span>${userLoginInfo}</span>
<p>
    <a href="${pageContext.request.contextPath}/user/loginout">注销</a>
</p>
</body>
</html>
