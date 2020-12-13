<%--
  Created by IntelliJ IDEA.
  User: vitiav
  Date: 12.12.20
  Time: 19:19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="stl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop"/>

<form action="controller" method="post">

    <stl:if test="${login_status == 'new'}">
        <fmt:message key="prop_login_enterinfo"/>
        <input id="name" name="login_name" required>
        <input id="pass" type="password" name="login_password" required>
        <button  type="submit"  name="button" value="login_cmd"><fmt:message key="btn_login"/></button >
    </stl:if>

    <stl:if test="${login_status == 'fail'}">
        <fmt:message key="prop_try_again"/>
        <input id="name" name="login_name" value="${login_name}" required>
        <input id="pass" type="password" name="login_password" required>
        <button  type="submit"  name="button" value="login_cmd"><fmt:message key="btn_login"/></button >
    </stl:if>
</form>

<form action="controller" method="post">
    <button  type="submit"  name="button" value="home_cmd"><fmt:message key="prop_home_cmd"/></button >
</form>

</body>
</html>
