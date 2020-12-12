<%--
  Created by IntelliJ IDEA.
  User: vitiav
  Date: 12.12.20
  Time: 17:13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="stl"%>
<html>
<head>
    <title>Registration</title>
</head>
<body>


<form action="controller" method="post">

    <stl:if test="${register_status == 'new'}">
        Please enter your registration info:
        <input id="name" name="register_name" required>
        <input id="pass" type="password" name="register_password" required>
        <button  type="submit"  name="button" value="register_cmd">register</button >
    </stl:if>

    <stl:if test="${register_status == 'success'}">
        Congratulations! you have been registered!
    </stl:if>

    <stl:if test="${register_status == 'fail'}">
       Something went wrong! Try again:
        <input id="name" name="register_name" value="${login_name}" required>
        <input id="pass" type="password" name="register_password" required>
        <button  type="submit"  name="button" value="register_cmd">register</button >
    </stl:if>
    back to main:
        <button  type="submit"  name="button" value="home_cmd">home</button >
</form>

</body>
</html>
