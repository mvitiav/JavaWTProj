<%--
  Created by IntelliJ IDEA.
  User: vitiav
  Date: 12.12.20
  Time: 05:03
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="stl"%>
<html>
<head>
    <title>About</title>
</head>
<body>
<%--${carPurpose1}--%>
<h2>These types of cars are available:</h2>
<stl:forEach var="data" items="${carPurposes}" >
    <br><stl:out value="${data.getName()}">  </stl:out></br>
</stl:forEach>

</body>
</html>
