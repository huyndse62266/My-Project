<%-- 
    Document   : error
    Created on : Jan 20, 2018, 11:50:31 AM
    Author     : student
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>Error Page</h1>
        <c:set var="login" value="${requestScope.ERRORLOGIN}"/>
        <c:if test="${not empty login}">
            <font color="red">
                ${login}
            </font>
            
            <br/><a href="login.html">Click to here try login again</a><br/>
            <%--<form action="forward" method="POST"> --%>
            <c:url var="linkInsert" value="forward">
                <c:param name="btAction" value="errorPage"/>
            </c:url>
            <a href="${linkInsert}" >Click to register new account</a>
          
            
        </c:if>
        
    </body>
</html>
