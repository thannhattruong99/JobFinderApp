<%-- 
    Document   : base
    Created on : Aug 31, 2021, 8:13:52 PM
    Author     : truongtn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <tiles:insertAttribute name="title"/>
        </title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"  rel="stylesheet"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>
        <link href="<c:url value="/css/style.css"/>" rel="stylesheet"/>
    </head>
    <body>
        <div class="container">
            <!-- HEADER  -->
            <tiles:insertAttribute name="header"/>

            <!-- CONTENT  -->
            <tiles:insertAttribute name="content"/>

            <!-- FOOTER  -->
            <tiles:insertAttribute name="footer"/>
        </div>
        
    </body>
</html>
