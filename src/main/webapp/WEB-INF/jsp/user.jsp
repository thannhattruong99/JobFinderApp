<%-- 
    Document   : candidate-profile
    Created on : Sep 5, 2021, 2:06:54 PM
    Author     : truongtn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1 class="text text-center">${user.fullname}</h1>
<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        ${errMsg}
    </div>
</c:if>
<div class="row">
    <div class="col-md-4">
        <div class="form-group">
            <img id="output" src="${user.avatar}" style="height: 100%; width: 100%"/>
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
        
<!--comment-->
<div class="row">
    <div class="container p-2">
        <h2 class="text-center">Average Score: ${user.averageScore} stars (${user.numberOfVote} votes)</h2>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <sec:authorize access="hasRole('ROLE_CANDIDATE')">
                <form:form action="rating" method="post" modelAttribute="rating">
                    <b class="mr-2">Your rating:</b>
                    <form:input class="mr-2" type="number" path="score" min="1" max="5" value="${user.senderScore}"/>
                    <p>Rating Score is from 1 to 5 star</p>
                    <form:input type="hidden" readonly="true" path="sender" value="${pageContext.request.userPrincipal.name}"/>
                    <form:input type="hidden" readonly="true" path="receiver" value="${user.username}"/>
                    <input class="mr-2" type="submit" value="Submit"/>
                </form:form>
            </sec:authorize>
        </c:if>
    </div>
    
    
    <div class="container p-3 border">
        <h2 class="text-center">Comments</h2>
        <c:forEach var="cmt" items="${user.comments}">
            <div class="container p-1 border">
                <b class="mr-4">${cmt.candidate.fullname}:</b>${cmt.content}
            </div>
            
        </c:forEach>
        
        
    </div>

    <div class="container border" >
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <sec:authorize access="hasRole('ROLE_CANDIDATE')">
                <div class="container p-3 border">
                    <form:form action="comment" method="post" modelAttribute="comment">
                        <form:errors path="*" cssClass="alert alert-danger" element="div"/>
                        <label class="align-text-top">Your comment:</label> <form:textarea path="content"></form:textarea>
                        <form:input type="hidden" readonly="true" path="sender" value="${pageContext.request.userPrincipal.name}"/>
                        <form:input type="hidden" readonly="true" path="receiver" value="${user.username}"/>
                        <input type="submit" value="submit"/>
                    </form:form>
                </div>
            </sec:authorize>
        </c:if>
    </div>
</div>