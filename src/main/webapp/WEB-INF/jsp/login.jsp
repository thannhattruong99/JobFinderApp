<%-- 
    Document   : login
    Created on : Sep 1, 2021, 7:31:47 PM
    Author     : truongtn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center">LOGIN</h1>

<c:if test="${param.error != null}">
    <div class="alert alert-danger">
        Login fail. Please try again!!!
    </div>
</c:if>
<c:if test="${param.accesDenied != null}">
    <div class="alert alert-danger">
        You have no permission.
    </div>
</c:if>

<c:url value="/login" var="action"/>
<form method="post" action="${action}">
    <div class="form-group">
        <label>Username</label>
        <input type="text" id="username" name="username" class="form-control"/>
    </div>
    <div class="form-group">
        <label>Password</label>
        <input type="password" id="password" name="password" class="form-control"/>
    </div>
    <div class="form-group">
        <input type="submit" value="Login"/>
    </div>
</form>