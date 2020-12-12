<%--
  Created by IntelliJ IDEA.
  User: vitiav
  Date: 03.12.20
  Time: 12:22
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="stl"%>
<html>
  <head>
    <title>Welcome to Autobase</title>
    <style>
      <%@include file="styles/main.css" %>
    </style>
  </head>
  <body>
  <%--todo:clean fix style--%>

  <stl:if test="${logged_USER != null}">
      <div >Hello, ${logged_USER.getUsername()}</div>
  </stl:if>

  <form action="controller" method="post" >
  <div id = 'menu'>
    <ul>
      <li>
<%--        <button onclick="location.href='controller?command=login_cmd'">Add subject--%>
  <button  type="submit"  name="button" value="login_cmd">
        <div><i class="icon icon-login"></i></div>
        <span>Log in</span>
<%--        </button>--%>
      </button >
      </li>
        <li>
            <button  type="submit"  name="button" value="register_cmd">
                <div><i class="icon icon-login"></i></div>
                <span>register</span>
            </button >
        </li>
      <button  type="submit"  name="button" value="order_cmd">
      <li class='first'>
        <div>
          <i class="icon icon-order"></i>
        </div>
        <span>Place<br>order</span>
        </button >
      </li>
      <li>
        <button  type="submit"  name="button" value="about_cmd">
        <div>
          <i class="icon icon-question"></i></div>
        <span>About us</span>
        </button >
      </li>
    </ul>
  </div>
  </form>

  </body>
</html>
