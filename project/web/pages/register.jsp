<%--
  Created by IntelliJ IDEA.
  User: vitiav
  Date: 12.12.20
  Time: 17:13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="stl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop"/>

<form action="controller" method="post">

    <stl:if test="${register_status == 'new'}">
        <fmt:message key="prop_register_enterinfo"/>
        <input id="name" name="register_name" required>
        <input id="pass" type="password" name="register_password" required>
        <button  type="submit"  name="button" value="register_cmd"><fmt:message key="btn_register"/></button >
    </stl:if>

    <stl:if test="${register_status == 'success'}">
        Congratulations! you have been registered!
    </stl:if>

    <stl:if test="${register_status == 'fail'}">
        <fmt:message key="prop_try_again"/>
        <input id="name" name="register_name" value="${login_name}" required>
        <input id="pass" type="password" name="register_password" required>
        <button  type="submit"  name="button" value="register_cmd"><fmt:message key="btn_register"/></button >
    </stl:if>
</form>

<form action="controller" method="post">
        <button  type="submit"  name="button" value="home_cmd"><fmt:message key="prop_home_cmd"/></button >
</form>

</body>
</html>
