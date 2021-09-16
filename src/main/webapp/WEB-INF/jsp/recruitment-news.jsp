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
    <c:forEach var="rn" items="${recruitmentNewsLst}">
        <div class="card col-md-4">
            <div class="card-body">
                <c:if test="${rn.image != null && rn.image.startsWith('https') == true}">
                    <image class="img-fluid"  style="width: 300px; height:200px" src="<c:url value="${rn.image}"/>"/>
                </c:if>
                <c:if test="${rn.image == null || rn.image.startsWith('https') == false}">
                    <image class="img-fluid"  style="width: 300px; height:200px" src="<c:url value="images/default.jpeg"/>"/>
                </c:if>
            </div>
            <div class="card-footer bg-light">
                <h4>${rn.title}</h4>
                <b>Salary:</b> <p> ${rn.minSalary}$ up to max ${rn.maxSalary}$</p>
                <b>Job type: </b> <p>${rn.jobType}</p>
                <b>Major:</b><p>${rn.major.name}</p>
                <b>Address:</b> <p>${rn.address} ${rn.district.name}</p>
                <b>Description:</b> <p>${rn.description}</p>
                <c:url var="poster" value="/user?username=${rn.user.username}"/>
                <b>Poster:</b> <a href="${poster}">${rn.user.fullname}</a>
            </div>
            
            <div class="form-group text-center" style="margin-top: 2%">
                <c:url var="viewDetail" value="/view-detail"/>
                <form action="${viewDetail}" method="get">
                    <input type="hidden" readonly="true" name="rnId" value="${rn.id}"/>
                    <input class="btn btn-primary" type="submit" value="View Detail"/>
                </form>
            </div>

        </div>
    </c:forEach>  
</div>