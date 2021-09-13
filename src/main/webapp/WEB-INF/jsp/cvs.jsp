<%-- 
    Document   : cvs
    Created on : Sep 10, 2021, 3:34:46 PM
    Author     : truongtn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<c:url value="/js/cvs.js"/>"></script>

<h1 class="text-center">Your CV list </h1>
<table border="1">
    <thead>
        <tr>
            <th>No.</th>
            <th>Name</th>
            <th>Description</th>
            <th>Link</th>
            <th>Last Update</th>
            <th>Action</th>

        </tr>
    </thead>
    <tbody>
        
        <c:forEach var="cv" items="${cvs}" varStatus="counter">
            <c:url value="/send-cv" var="sendCV">
                <c:param name="rnId" value="${rnId}"/>
                <c:param name="cvId" value="${cv.id}"/>
            </c:url>
            <tr>
                <form method="Post" action="${sendCV}">
                    <td>${counter.count}</td>
                    <td><input type="text" readonly="true" cssClass="form-control" value="${cv.name}"/></td>
                    <td><input type="text" readonly="true" cssClass="form-control" value="${cv.description}"/></td>
                    <td><input type="hidden" readonly="true" cssClass="form-control" value="${cv.url}"/><c:if test="${not empty cv.url}"><a href="${cv.url}" target="_blank">See detail</a></c:if></td>
                    <td>${cv.lastUpdated}</td>
                    <td><input type="submit" value="Submit"/></td>
                </form>
            </tr>
        </c:forEach>
    </tbody>
</table>
