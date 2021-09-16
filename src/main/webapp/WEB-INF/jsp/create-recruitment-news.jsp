<%-- 
    Document   : create-recruitment-news
    Created on : Sep 11, 2021, 2:55:06 PM
    Author     : truongtn
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<script src="<c:url value="/js/register.js"/>"></script>

<h1 class="text-center text-danger">Create recruitment news</h1>
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

<c:url value="/create-recruitment" var="action"/>
<form:form method="post" action="${action}" modelAttribute="rn" enctype="multipart/form-data" onsubmit="return confirmMsg();">
    <form:errors path="*" cssClass="alert alert-danger" element="div"/>
    <div class="row">
        <div class="col-md-4">
            <div class="form-group">
                <label for="file">Avatar</label>
                <form:input type="file" id="imgInp" cssClass="form-control" path="file" onchange="loadFile(event)"/>
                <img id="output" style="width: 100%"/>
            </div>
        </div>
        <div class="col-md-8">
            <div class="form-group">
                <label>Title</label>
                <form:input type="text" id="title" path="title" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Description</label>
                <form:textarea type="text" id="description" path="description" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Min salary</label>
                <form:input type="text" id="minSalary" path="minSalary" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Max salary</label>
                <form:input type="text" id="maxSalary" path="maxSalary" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Min Experience Year</label>
                <form:input type="text" id="maxSalary" path="minExperienceYear" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Address</label>
                <form:input type="text" id="address" path="address" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Job type</label>
                <form:select id="jobType" path="jobType" class="form-control">
                    <option value="FULL_TIME">Full time</option>
                    <option value="PART_TIME">Part time</option>
                </form:select>
            </div>
            <div class="form-group">
                <label>District</label>
                <form:select id="districtId" cssClass="form-control" path="districtId">
                    <c:forEach var="city" items="${cities}" varStatus="counter">
                        <c:forEach var="district" items="${city.districts}">
                            <option value="${district.id}">${district.name}</option>
                        </c:forEach>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-group">
                <label>Major</label>
                <form:select id="major" cssClass="form-control" path="majorId">
                    <c:forEach var="major" items="${majors}" varStatus="counter">
                        <option value="${major.id}">${major.name} - ${major.description}</option>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-group">
                <input type="hidden" readonly="true" name="username" value="${pageContext.request.userPrincipal.name}"/>
                <input type="submit" value="Post"/>
            </div>
        </div>
    </div>               
</form:form>