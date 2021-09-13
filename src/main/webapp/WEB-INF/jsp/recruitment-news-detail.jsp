<%-- 
    Document   : index
    Created on : Sep 3, 2021, 2:47:51 PM
    Author     : truongtn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<script src="<c:url value="/js/index.js"/>"></script>

<h1 class="text text-center">Your Recruitment News</h1>

<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        ${errMsg}
    </div>
</c:if>
<c:if test="${sucessMsg != null}">
    <div class="alert alert-primary">
        ${sucessMsg}
    </div>
</c:if>


<!-- search form -->







<!--table view-->
<c:url var="create" value="/create-recruitment"/>
<form action="${create}" method="get">
    <input type="submit" value="Create recruitment news" />
</form>
<div class="row">
    <div class="card col-md-4">
        <div class="card-body">
            <c:if test="${rn.image != null && rn.image.startsWith('https') == true}">
                <image class="img-fluid" src="<c:url value="${rn.image}"/>"/>
            </c:if>
            <c:if test="${rn.image == null || rn.image.startsWith('https') == false}">
                <image class="img-fluid" src="<c:url value="images/default.jpeg"/>"/>
            </c:if>
        </div>
        <div class="card-footer bg-light">
            <h3>${rn.title}</h3>
            <b>Salary:</b> <p> ${rn.minSalary}$ up to max ${rn.maxSalary}$</p>
            <b>Job type: </b> <p>${rn.jobType}</p>
            <b>Address:</b> <p>${rn.address} ${rn.district.name}</p>
            <b>Description:</b> <p>${rn.description}</p>
            <b>Poster:</b> <p>${rn.user.fullname}</p>
        </div>
    </div>
</div>