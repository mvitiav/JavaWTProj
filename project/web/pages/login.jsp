<%--
  Created by IntelliJ IDEA.
  User: vitiav
  Date: 12.12.20
  Time: 19:19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="stl"%>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form action="controller" method="post">

    <stl:if test="${login_status == 'new'}">
        Please enter your login info:
        <input id="name" name="login_name" required>
        <input id="pass" type="password" name="login_password" required>
        <button  type="submit"  name="button" value="login_cmd">log in</button >
    </stl:if>

    <stl:if test="${login_status == 'fail'}">
        Something went wrong! Try again:
        <input id="name" name="login_name" value="${login_name}" required>
        <input id="pass" type="password" name="login_password" required>
        <button  type="submit"  name="button" value="login_cmd">log in</button >
    </stl:if>
    back to main:
    <button  type="submit"  name="button" value="home_cmd">home</button >
</form>

</body>
</html>
