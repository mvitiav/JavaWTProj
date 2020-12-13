<%--
  Created by IntelliJ IDEA.
  User: vitiav
  Date: 03.12.20
  Time: 13:29
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="stl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="prop"/>
<%--todo:make--%>


<stl:if test="${sessionScope.logged_USER != null}">

    <div><fmt:message key="youAreLogged"/>${logged_USER.getUsername()}</div>
    <stl:if test="${logged_USER.getRole().ordinal() == 0}">
        <form action="controller" method="post">
            Please enter your order info:
            <p><input name="order_size"></p>
            <p><input name="order_volume"></p>
            <p><input name="order_weight"></p>
            <p><input name="order_shipmentDate" type="date"></p>
            <p><input name="order_destinationDate" type="date"></p>
            <p><input name="order_shipmentPoint"></p>
            <p><input name="order_destinationPoint"></p>
            <input type="hidden" name="order_status" value="filled" >
            <button type="submit" name="button" value="order_cmd">order</button>
        </form>

        <p>some of your last orders:</p>

        <stl:forEach var="order" items="${order_last}" >
            <br><stl:out value="${order}">  </stl:out></br>
        </stl:forEach>
    </stl:if>

    <stl:if test="${logged_USER.getRole().ordinal() == 2}">
        <p>new orders of clients:</p>

        <stl:forEach var="order" items="${distribute_list}" >
            <br><stl:out value="${order}">  </stl:out></br>
        </stl:forEach>

    </stl:if>

</stl:if>


<form action="controller" method="post">
    <button type="submit" name="button" value="home_cmd">home</button>
</form>

</body>
</html>
