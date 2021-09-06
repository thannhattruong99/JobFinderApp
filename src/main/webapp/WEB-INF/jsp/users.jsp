<%-- 
    Document   : user
    Created on : Sep 5, 2021, 2:52:19 PM
    Author     : truongtn
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="<c:url value="/js/users.js"/>"></script>

<h1 class="text text-center">List of User</h1>

<div>
    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>Username</th>
                <th>Full name</th>
                <th>Gender</th>
                <th>Email</th>
                <th>Updated time</th>
                <th>Role</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>${user.username}</td>
                    <td>${user.fullname}</td>
                    <td>${user.gender}</td>
                    <td>${user.email}</td>
                    <td>${user.lastUpdated}</td>
                    <td>${user.role}</td>
                    <td>${user.active}</td>
                    <c:url value="/admin/user/status" var="action"/>
                    <form method="post" action="${action}" onsubmit="confirmMsg()">
                        <input type="text" hidden="true" readonly="true" name="id" value="${user.id}"/>
                        <input type="text" hidden="true" readonly="true" name="active" value="${user.active}"/>
                        <c:if test="${user.active}">
                            <td> <input type="submit" value="Inactive" class="btn btn-danger"/></td> 
                        </c:if>
                        <c:if test="${not user.active}">
                            <td> <input type="submit" value="Active" class="btn btn-success"/></td> 
                        </c:if>
                    </form>
                </tr>    
            </c:forEach>
        </tbody>
    </table>
</div>