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

<h1 class="text text-center">Recruitment News</h1>

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
<c:url value="/" var="searchAction"/>
<form:form modelAttribute="searchValue" class="form-inline" action="${searchAction}" method="Post">
    <form:errors path="*" cssClass="alert alert-danger" element="div"/>
    <form:input type="text" name="title" path="title" class="form-control mb-2 mr-sm-2" placeholder="Title"/>
    <form:input type="text" path="salaryStr" class="form-control mb-2 mr-sm-2" placeholder="Expected salary"/>
    <form:input type="hidden" path="pageNum" readonly="true"/>
    <form:select path="majorId" class="form-control mb-2 mr-sm-2" style="width: 120px">
        <option value="0" <c:if test="${searchValue.majorId == 0}">selected</c:if>>All Majors</option>
        <c:forEach var="major" items="${majors}">
            <option value="${major.id}"<c:if test="${searchValue.majorId == major.id}">selected</c:if>>${major.name} - ${major.description}</option>
        </c:forEach>
    </form:select>
   
    <form:select path="districtId" class="form-control mb-2 mr-sm-2" style="width: 130px">
        <option value="0" selected >All Districts</option>
        <c:forEach var="city" items="${cities}">
            <c:forEach var="district" items="${city.districts}">
                <option value="${district.id}" <c:if test="${searchValue.districtId == district.id}">selected</c:if>>${district.name}</option>
            </c:forEach>
        </c:forEach>
    </form:select>
                
    <form:select path="jobType" class="form-control mb-2 mr-sm-2" style="width: 140px">
        <option value="" <c:if test="${empty searchValue.jobType}">selected</c:if>>All Job Type</option>
        <option value="FULL_TIME" <c:if test="${searchValue.jobType == 'FULL_TIME'}">selected</c:if>>Full time</option>
        <option value="PART_TIME" <c:if test="${searchValue.jobType == 'PART_TIME'}">selected</c:if>>Part time</option>
    </form:select>            
   
    <input type="submit" value="Search" class="btn btn-primary mb-2"/>    
</form:form>



<ul class="pagination">
    <c:forEach var="i" begin="1" end="${Math.ceil(totalRecord/20)}">
            <form:form modelAttribute="searchValue" class="form-inline" action="${searchAction}" method="Post">
                <form:input type="hidden" path="title" value="${searchValue.title}"/>
                <form:input type="hidden" path="salaryStr" value="${searchValue.salaryStr}"/>
                <form:input type="hidden" path="majorId" value="${searchValue.majorId}"/>
                <form:input type="hidden" path="districtId" value="${searchValue.districtId}"/>
                <form:input type="hidden" path="jobType" value="${searchValue.jobType}"/>
                <form:input class="page-item" type="submit" path="pageNum" value="${i}"/>
            </form:form>
    </c:forEach>
</ul>


<!--table view-->
<div class="row">
    <c:forEach var="rn" items="${recruitmentNewsLst}">
        <div class="card col-md-3">
            <div class="card-body">
                <c:if test="${rn.image != null && rn.image.startsWith('https') == true}">
                    <image class="img-fluid text-center"  style="width: 300px; height:200px" src="<c:url value="${rn.image}"/>"/>
                </c:if>
                <c:if test="${rn.image == null || rn.image.startsWith('https') == false}">
                    <image class="img-fluid"  style="width: 300px; height:200px" src="<c:url value="images/default.jpeg"/>"/>
                </c:if>
            </div>
            <div class="card-footer bg-light">
                <h4>${rn.title}</h4>
                <b>Salary:</b> <p> ${rn.minSalary}$ up to max ${rn.maxSalary}$</p>
                <b>Job type: </b> <p>${rn.jobType}</p>
                <b>Major: </b> <p>${rn.major.name}</p>
                <b>Address:</b> 
                <p>${rn.address} ${rn.district.name}</p>
                <b>Description:</b> <p>${rn.description}</p>
                <b>Update time: </b> <p>${rn.lastUpdated}</p>
                <c:url var="poster" value="/user?sender=${pageContext.request.userPrincipal.name}&receiver=${rn.user.username}"/>
                <b>Poster:</b> <a href="${poster}">${rn.user.fullname}</a>
            </div>
            
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <sec:authorize access="hasRole('ROLE_CANDIDATE')">
                    <div class="form-group text-center" style="margin-top: 2%">
                        <c:url var="apply" value="/cvs"/>
                        <form action="${apply}" method="post">
                            <input type="hidden" readonly="true" name="rnId" value="${rn.id}"/>
                            <input type="hidden" name="username" readonly="true" value="${pageContext.request.userPrincipal.name}"/>
                            <input class="btn btn-primary" type="submit" value="Apply Now"/>
                        </form>
                    </div>
                </sec:authorize>
            </c:if>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <div class="text-center">
                    <a href="<c:url value="/login"/>">Login to apply the job</a>
                </div>
            </c:if>
        </div>
    </c:forEach>  
</div>

<!-- paging nation -->
${totalRecord}
<ul class="pagination">
    <c:forEach var="i" begin="1" end="${Math.ceil(totalRecord/20)}">
            <form:form modelAttribute="searchValue" class="form-inline" action="${searchAction}" method="Post">
                <form:input type="hidden" path="title" value="${searchValue.title}"/>
                <form:input type="hidden" path="salaryStr" value="${searchValue.salaryStr}"/>
                <form:input type="hidden" path="majorId" value="${searchValue.majorId}"/>
                <form:input type="hidden" path="districtId" value="${searchValue.districtId}"/>
                <form:input type="hidden" path="jobType" value="${searchValue.jobType}"/>
                <form:input class="page-item" type="submit" path="pageNum" value="${i}"/>
            </form:form>
    </c:forEach>
</ul>

