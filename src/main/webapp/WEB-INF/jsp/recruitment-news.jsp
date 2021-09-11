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
<div class="row">
    <c:forEach var="rn" items="${recruitmentNewsLst}">
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
                <c:url var="poster" value="/user?username=${rn.user.username}"/>
                <b>Poster:</b> <a href="${poster}">${rn.user.fullname}</a>
            </div>
            
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <div class="form-group text-center" style="margin-top: 2%">
                    <c:url var="cv-list" value="/apply-cvs"/>
                    <form action="${cv-list}" method="get">
                        <input type="hidden" readonly="true" name="rnId" value="${rnId}"/>
                        <input class="btn btn-primary" type="submit" value="Candidate list"/>
                    </form>
                </div>
            </c:if>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <div class="text-center">
                    <a href="<c:url value="/login"/>">Login to apply the job</a>
                </div>
            </c:if>
        </div>
    </c:forEach>  
</div>