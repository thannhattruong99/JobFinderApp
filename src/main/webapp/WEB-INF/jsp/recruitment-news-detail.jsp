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

<h1 class="text text-center">Recruitment News Detail</h1>

<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        ${errMsg}
    </div>
</c:if>
<c:if test="${successMsg != null}">
    <div class="alert alert-primary">
        ${successMsg}
    </div>
</c:if>


<!-- search form -->







<!--table view-->
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
            <b>Major:</b><p>${rn.major.name}</p>
            <b>Address:</b> <p>${rn.address} ${rn.district.name}</p>
            <b>Description:</b> <p>${rn.description}</p>
            <b>Update time: </b> <p>${rn.lastUpdated}</p>
            <b>Poster:</b> <p>${rn.user.fullname}</p>
        </div>
    </div>
    <div class="card col-md-8">
        <h2 class="text-center">Applying CV List</h2> 
        <table border="1" class="text-center">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>CV link</th>
                    <th>Last Update</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cv" items="${rn.curriculumVitaes}" varStatus="counter">
                    <c:url value="/send-cv" var="sendCV">
                        <c:param name="rnId" value="${rnId}"/>
                        <c:param name="cvId" value="${cv.id}"/>
                    </c:url>
                    <tr>
                        <c:url var="candidate" value="/user?username=${cv.user.username}"/>
                        <td>${counter.count}</td>
                        <td><a href="${candidate}">${cv.user.fullname}</a></td>
                        <td><input type="hidden" readonly="true" cssClass="form-control" value="${cv.url}"/><c:if test="${not empty cv.url}"><a href="${cv.url}" target="_blank">See detail</a></c:if></td>
                        <td>${cv.lastUpdated}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>