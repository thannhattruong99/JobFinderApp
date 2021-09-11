<%-- 
    Document   : candidate-profile
    Created on : Sep 5, 2021, 2:06:54 PM
    Author     : truongtn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<h1 class="text text-center">${user.fullname}</h1>
<div class="row">
    <div class="col-md-4">
        <div class="form-group">
            <img id="output" src="${user.avatar}" style="height: 100%; width: 100%"/>
            <form:input type="file" id="imgInp" cssClass="form-control" path="file" onchange="loadFile(event)"/>
        </div>
    </div>
    <!-- end col-md-4 -->

    <div class="col-md-4">
        <c:set var="userId" value="${user.id}"/>
        <c:set var="username" value="${user.username}"/>
            
        <div class="form-group">
            <hr><span class="font-weight-bold">User name:&nbsp;&nbsp;&nbsp;</span> <span>${user.username}</span></hr>
        </div>
        <div class="form-group">
            <hr><span class="font-weight-bold">Full name:&nbsp;&nbsp;&nbsp;</span> <span>${user.fullname}</span></hr>
        </div>
        <div class="form-group">
            <hr><span class="font-weight-bold">Birth Date:&nbsp;&nbsp;&nbsp;</span> <span>${user.birthDate}</span></hr>
        </div>
        <div class="form-group">
            <hr><span class="font-weight-bold">Gender:&nbsp;&nbsp;&nbsp;</span> <span>${user.gender}</span></hr>
        </div>
        <div class="form-group">
            <hr><span class="font-weight-bold">Email:&nbsp;&nbsp;&nbsp;</span> <span>${user.email}</span></hr>
        </div>
        <div class="form-group">
            <hr><span class="font-weight-bold">Phone:&nbsp;&nbsp;&nbsp;</span> <span>${user.phone}</span></hr>
        </div>
        <div class="form-group">
            <hr><span class="font-weight-bold">Working at: &nbsp;&nbsp;&nbsp;</span> <span>${user.organization.name}</span></hr>
        </div>
        <div class="form-group">
            <hr><span class="font-weight-bold">Description: &nbsp;&nbsp;&nbsp;</span> <span>${user.organization.description}</span></hr>
        </div>
        <div class="form-group">
            <hr><span class="font-weight-bold">Address:&nbsp;&nbsp;&nbsp;</span> <span>${user.organization.address} ${user.organization.district.name}</span></hr>
        </div>
        <c:if test="${user.role == ROLE_CANDIDATE}">
            <div class="form-group">
                <hr><span class="font-weight-bold">Major:&nbsp;&nbsp;&nbsp;</span> <span>${user.major.name} - ${user.major.description}</span></hr>
            </div>
        </c:if>
        <hr></hr>
    </div>
    <!-- end col-md-8 -->
    <div class="col-md-4 img-fluid">
        POSTER QUẢNG CÁO
    </div>
</div>   