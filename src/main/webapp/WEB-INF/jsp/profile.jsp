<%-- 
    Document   : profile
    Created on : Sep 5, 2021, 2:40:56 PM
    Author     : truongtn
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="<c:url value="/js/profile.js"/>"></script>

<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        ${errMsg}
    </div>
</c:if>

<h1 class="text text-center">Basic information</h1>

<c:url value="/update-profile" var="action"/>
<form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data">
    <c:set var="userId" value="${user.id}"/>
    <c:set var="username" value="${user.username}"/>
    <form:errors path="*" cssClass="alert alert-danger" element="div"/>
    <div class="form-group">
        <label>Username</label>
        <form:input type="text" id="username" path="username" class="form-control"/>
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
                    <option value="${district.id}" <c:if test="${district.id == user.districtId}"> selected</c:if> >${district.name}</option>
                </c:forEach>
            </c:forEach>
        </form:select>
    </div>
    <div class="form-group">
        <label>Role</label>
        <form:select id="role" cssClass="form-control" path="role" onchange="showInputsByRole()">
            <option value="ROLE_CANDIDATE" <c:if test="${role == 'ROLE_CANDIDATE'}"> selected</c:if>>Candidate</option>
            <option value="ROLE_EMPLOYER" <c:if test="${role == 'ROLE_EMPLOYER'}"> selected</c:if>>Employer</option>
        </form:select>
    </div>
        <div class="form-group">
        <label>Current Organization</label>
        <form:select id="organizationId" cssClass="form-control" path="organizationId">
            <option value="">None</option>
            <c:forEach var="organization" items="${organizations}" varStatus="counter">
                <option value="${organization.id}" <c:if test="${organization.id == user.organization.id}"> selected</c:if>>${organization.name}</option>
            </c:forEach>
        </form:select>
    </div>
    <sec:authorize access="hasRole('ROLE_CANDIDATE')">
        <div class="form-group">
            <label>Your Major</label>
            <form:select id="major" cssClass="form-control" path="majorId">
                <option value="">None</option>
                <c:forEach var="major" items="${majors}" varStatus="counter">
                    <option value="${major.id}" <c:if test="${major.id == user.major.id}"> selected</c:if> >${major.name} - ${major.description}</option>
                </c:forEach>
            </form:select>
        </div>
    </sec:authorize>
    <div class="form-group">
        <label for="file">Avatar</label>
        <form:input type="file" id="imgInp" cssClass="form-control" path="file" onchange="loadFile(event)"/>
        <img id="output" src="${user.avatar}"/>
    </div>
    <div class="form-group">
        <label>Birth Date</label>
        <form:input type="date" id="birthDate" path="birthDate" min="1970-12-31" class="form-control"/>
    </div>
    <div class="form-group">
        <input type="submit" value="Update Profile"/>
    </div>
</form:form>

<sec:authorize access="hasRole('ROLE_CANDIDATE')">
    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>Name</th>
                <th>Description</th>
                <th>Link</th>
                <th>Last Update</th>
                <th>Update</th>
                <th>Remove</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="cv" items="${user.curriculumVitaes}" varStatus="counter">
                <tr>
                    <c:url value="/update-cv" var="updateCV"/>
                    <form:form method="post" action="${updateCV}" modelAttribute="curriculum" onsubmit="return confirmMsg();" >
                        <td>${counter.count}</td>
                        <td><input type="text" name="name" cssClass="form-control" value="${cv.name}"/></td>
                        <td><input type="text" name="description" cssClass="form-control" value="${cv.description}"/></td>
                        <td><input type="text" name="url" cssClass="form-control" value="${cv.url}"/><c:if test="${not empty cv.url}"><a href="${cv.url}">See detail</a></c:if></td>
                        <td>${cv.lastUpdated}</td>
                        <input type="hidden" readonly="true" name="username" value="${pageContext.request.userPrincipal.name}"/>
                        <input type="hidden" readonly="true" name="id" value="${cv.id}"/>
                        <td><input type="submit" value="Update" class="btn btn-danger"/></td>
                        <c:url var="deleteUrlRewritting" value="remove-cv">
                            <c:param name="id" value="${cv.id}"/>
                            <c:param name="username" value="${pageContext.request.userPrincipal.name}"/>
                        </c:url>
                        <td><a href="${deleteUrlRewritting}" onclick=" return confirmMsg();">Remove</a></td>
                    </form:form>
                </tr>
            </c:forEach>
                <tr>
                    <c:url value="/add-cv" var="addCV"/>
                    <form:form method="post" action="${addCV}" modelAttribute="curriculum" onsubmit="return confirmMsg();" >
                        <td></td>
                        <td><form:input type="text" path="name" cssClass="form-control"/></td>
                        <td><form:input type="text" path="description" cssClass="form-control"/></td>
                        <td><form:input type="text" path="url" cssClass="form-control"/></td>
                        <td></td>    
                        <form:input type="hidden" readonly="true" path="candidateId" value="${userId}"/>
                        <input type="hidden" readonly="true" name="username" value="${username}"/>
                        <td class="text-center" colspan="2"><input type="submit" value="Add CV" class="btn btn-danger"/></td>
                        <form:errors path="*" cssClass="alert alert-danger" element="div"/>
                    </form:form>
                </tr>
        </tbody>
    </table>
    
</sec:authorize>

