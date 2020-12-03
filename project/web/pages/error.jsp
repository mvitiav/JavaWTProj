<%--
  Created by IntelliJ IDEA.
  User: vitiav
  Date: 03.12.20
  Time: 21:00
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OOPS!</title>
</head>
<body>
<h1>Something went wrong! Sorry );</h1>
<h2>more info:</h2>
<div>${exception}</div>
<%--todo:make return button--%>
</body>
<form>
    <input type="submit" formaction="<%=request.getContextPath()%>" value="go home"/>
</form>
</html>
