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


  <fmt:setLocale value="${sessionScope.locale}"/>
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
        <span> <fmt:message key="btn_login"/></span>
<%--        </button>--%>
      </button >
      </li>
            <li>
                <button  type="submit"  name="button" value="register_cmd">
                    <div><i class="icon icon-login"></i></div>
                    <span> <fmt:message key="btn_register"/></span>
                </button >
            </li>
        </stl:if>
        <stl:if test="${logged_USER != null}">
            <stl:if test="${sessionScope.logged_USER.getRole().ordinal() == 0}">
            <li class='first'>
                <button  type="submit"  name="button" value="order_cmd">
                    <div>
                        <i class="icon icon-order"></i>
                    </div>
                    <span> <fmt:message key="btn_placeOrder"/></span>
                </button >
            </li>
            </stl:if>
            <stl:if test="${sessionScope.logged_USER.getRole().ordinal() == 2}">
                <li class='first'>
                    <button  type="submit"  name="button" value="distribute_cmd">
                        <div>
                            <i class="icon icon-order"></i>
                        </div>
                        <span>Distribute orders</span>
                    </button >
                </li>
            </stl:if>
            <li>
                <button  type="submit"  name="button" value="logout_cmd">
                    <div><i class="icon icon-login"></i></div>
                    <span> <fmt:message key="btn_logout"/></span>
                </button >
            </li>

        </stl:if>
      <li>
        <button  type="submit"  name="button" value="about_cmd">
        <div>
          <i class="icon icon-question"></i></div>
        <span> <fmt:message key="btn_aboutUs"/></span>
        </button >
      </li>

        <li>
            <button  type="submit"  name="button" value="lang_cmd">
                <div>
                    <i class="icon icon-question"></i></div>
                <span>Change language</span>
            </button >
        </li>

    </ul>
  </div>
  </form>

  </body>
</html>
