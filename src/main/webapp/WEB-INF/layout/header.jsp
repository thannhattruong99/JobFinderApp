<%-- 
    Document   : header
    Created on : Aug 31, 2021, 8:16:29 PM
    Author     : truongtn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <li class="nav-item">
            <a class="nav-link text-danger" href="<c:url value="/"/>">Home</a>
        </li>
     <c:if test="${pageContext.request.userPrincipal.name == null}">
        <li class="nav-item">
            <a class="nav-link text-danger" href="<c:url value="/login"/>">Login</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-danger" href="<c:url value="/register"/>">Registry</a>
        </li>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li class="nav-item">
                <a class="nav-link text-danger" href="<c:url value="/admin/users"/>" >Manage User</a>
            </li>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_EMPLOYER')">
            <li class="nav-item">
                <a class="nav-link text-danger" href="<c:url value="/recruitments?username=${pageContext.request.userPrincipal.name}"/>" >My Recruitment News</a>
            </li>
        </sec:authorize>
        <li class="nav-item">
            <a class="nav-link text-danger" href="<c:url value="/profile"/>?username=${pageContext.request.userPrincipal.name}">${pageContext.request.userPrincipal.name}</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-danger" href="<c:url value="/logout"/>">Logout</a>
        </li>
    </c:if>
</nav>