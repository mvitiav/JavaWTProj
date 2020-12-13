<%--
  Created by IntelliJ IDEA.
  User: vitiav
  Date: 03.12.20
  Time: 12:22
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="stl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
  <head>
    <title>Welcome to Autobase</title>
    <style>
      <%@include file="styles/main.css" %>
    </style>
  </head>
  <body>
  <fmt:setLocale value="${sessionScope.lang}"/>
  <fmt:setBundle basename="prop"/>
  <%--todo:clean fix style--%>

  <stl:if test="${sessionScope.logged_USER != null}">
      <div >  <fmt:message key="youAreLogged"/>${sessionScope.logged_USER.getUsername()}</div>
  </stl:if>

  <form action="controller" method="post" >
  <div id = 'menu'>
    <ul>

        <stl:if test="${sessionScope.logged_USER == null}">
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
        </stl:if>
        <stl:if test="${logged_USER != null}">
<%--            ${logged_USER.getRole()}--%>
<%--            ${User.Role.client}--%>
<%--            ${logged_USER.getRole().ordinal()}--%>

            <stl:if test="${sessionScope.logged_USER.getRole().ordinal() == 0}">
            <li class='first'>
                <button  type="submit"  name="button" value="order_cmd">
                    <div>
                        <i class="icon icon-order"></i>
                    </div>
                    <span>Place order</span>
                </button >
            </li>
            </stl:if>

            <li>
                <button  type="submit"  name="button" value="logout_cmd">
                    <div><i class="icon icon-login"></i></div>
                    <span>Log out</span>
                </button >
            </li>

        </stl:if>
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
