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

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop"/>
<%--todo:make--%>


<stl:if test="${sessionScope.logged_USER != null}">

    <div><fmt:message key="youAreLogged"/>${logged_USER.getUsername()}</div>
    <stl:if test="${logged_USER.getRole().ordinal() == 0}">
        <form action="controller" method="post">
            <fmt:message key="enterOrderInfo"/>
            <p><fmt:message key="prop_order_size"/><input name="order_size"></p>
            <p><fmt:message key="prop_order_volume"/><input name="order_volume"></p>
            <p><fmt:message key="prop_order_weight"/><input name="order_weight"></p>
            <p><fmt:message key="prop_order_shipmentDate"/><input name="order_shipmentDate" type="date"></p>
            <p><fmt:message key="prop_order_destinationDate"/><input name="order_destinationDate" type="date"></p>
            <p><fmt:message key="prop_order_shipmentPoint"/><input name="order_shipmentPoint"></p>
            <p><fmt:message key="prop_order_destinationPoint"/><input name="order_destinationPoint"></p>
            <input type="hidden" name="order_status" value="filled" >
            <button type="submit" name="button" value="order_cmd"><fmt:message key="prop_order_cmd"/></button>
        </form>

        <p> <fmt:message key="prop_last_orders"/></p>

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
    <button type="submit" name="button" value="home_cmd"><fmt:message key="prop_home_cmd"/></button>
</form>

</body>
</html>
