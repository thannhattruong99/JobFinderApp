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

<!-- search form -->
<c:url value="/admin/users" var="searchAction"/>
<form action="${searchAction}" method="get">
    <div class="form-group">
        <label>Full name</label>
        <input type="text" name="fullname" value="${searchValue.fullname}" class="form-control" />
        <label>Status</label>
        <select type="text" name="statusMode" value="${searchValue.statusMode}" class="form-control" >
            <option value="0" <c:if test="${searchValue.statusMode == 1}">selected</c:if>>All status</option>
            <option value="1" <c:if test="${searchValue.statusMode == 1}">selected</c:if>>Inactive</option>
            <option value="2" <c:if test="${searchValue.statusMode == 2}">selected</c:if>>Active</option>
        </select>
        <input type="submit" value="Search"/>
    </div> 
</form>
        
    
<!-- paging nation -->
<ul class="pagination">
    <c:forEach var="i" begin="1" end="${Math.ceil(totalPage/2)}">
        <c:url var="pageNumberUrl" value="/admin/users">
            <c:param name="fullname" value="${searchValue.fullname}"/>
            <c:param name="statusMode" value="${searchValue.statusMode}"/>
            <c:param name="pageNum" value="${i}"/>
        </c:url> 
        <li class="page-item"><a class="page-link" href="${pageNumberUrl}">${i}</a></li>
    </c:forEach>
</ul>


<!--table view-->
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
                    <td>
                        <c:if test="${user.active}"><p class="text-primary">Active</p></c:if> 
                        <c:if test="${not user.active}"><p class="text-danger">Inactive</p></c:if>
                    </td>
                    <c:url value="/admin/user/status" var="action"/>
                    <form method="post" action="${action}" onsubmit="return confirmMsg();">
                        <input type="text" hidden="true" readonly="true" name="id" value="${user.id}"/>
                        <input type="text" hidden="true" readonly="true" name="active" value="${user.active}"/>
                        <c:if test="${user.role != 'ROLE_ADMIN'}">
                            <c:if test="${user.active}">
                                <td> <input type="submit" value="Inactive" class="btn btn-danger"/></td> 
                            </c:if>
                            <c:if test="${not user.active}">
                                <td> <input type="submit" value="Active" class="btn btn-success"/></td> 
                            </c:if>
                        </c:if>
                    </form>
                </tr>    
            </c:forEach>
        </tbody>
    </table>
</div>