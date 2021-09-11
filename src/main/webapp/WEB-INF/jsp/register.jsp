<%-- 
    Document   : register
    Created on : Sep 4, 2021, 8:54:25 AM
    Author     : truongtn
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<script src="<c:url value="/js/register.js"/>"></script>

<h1 class="text-center text-danger">Registry</h1>
<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        ${errMsg}
    </div>
</c:if>

<c:url value="/register" var="action"/>
<form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data" onsubmit="return confirmMsg();">
    <form:errors path="*" cssClass="alert alert-danger" element="div"/>
    <div class="row">
        <div class="col-md-4">
            <div class="form-group">
                <label for="file">Avatar</label>
                <form:input type="file" id="imgInp" cssClass="form-control" path="file" onchange="loadFile(event)"/>
                <img id="output"/>
            </div>
        </div>
        <div class="col-md-8">        
            <div class="form-group">
                <label>Username</label>
                <form:input type="text" id="username" path="username" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Password</label>
                <form:input type="password" id="password" path="password" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Re-type password</label>
                <form:input type="password" id="confirm-password" path="confirmPassword" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Full name</label>
                <form:input type="text" id="fullname" path="fullname" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Gender</label>
                <form:select id="gender" path="gender" class="form-control">
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                </form:select>
            </div>
            <div class="form-group">
                <label>Email</label>
                <form:input type="email" id="email" path="email" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Phone</label>
                <form:input type="tel" id="phone" path="phone" class="form-control" pattern="[0-9]{10,14}"/>
            </div>
            <div class="form-group">
                <label>Address</label>
                <form:input type="text" id="address" path="address" class="form-control" />
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
                <label>Role</label>
                <form:select id="role" cssClass="form-control" path="role" onchange="showInputsByRole()">
                    <option value="ROLE_CANDIDATE" selected="selected">Candidate</option>
                    <option value="ROLE_EMPLOYER">Employer</option>
                </form:select>
            </div>
                <div class="form-group">
                <label>Current Organization</label>
                <form:select id="organizationId" cssClass="form-control" path="organizationId">
                    <option value="0">None</option>
                    <c:forEach var="organization" items="${organizations}" varStatus="counter">
                        <option value="${organization.id}">${organization.name}</option>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-group">
                <label>Your Major</label>
                <form:select id="major" cssClass="form-control" path="majorId">
                    <option value="0">None</option>
                    <c:forEach var="major" items="${majors}" varStatus="counter">
                        <option value="${major.id}">${major.name} - ${major.description}</option>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-group">
                <label>Birth Date</label>
                <form:input type="date" id="birthDate" path="birthDate" min="1970-12-31" class="form-control"/>
            </div>
            <div class="form-group">
                <input type="submit" value="Registry"/>
            </div>
        </div>    
    </div>            
</form:form>
