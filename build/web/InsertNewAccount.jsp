<%-- 
    Document   : InsertNewAccount
    Created on : Jan 20, 2018, 11:42:52 AM
    Author     : student
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert new account</title>
    </head>
    <body>
        
        
        <h1>Insert new Account Page</h1>
        <c:set var="role" value="${requestScope.ROLE}"/>
        <c:set var="listRole" value="${requestScope.LISTROLE}"/>
        <form action="insert" method="POST">
            
            <input type="hidden" name="txtRoleLogin" value="${role}" />
            <c:set var="error" value="${requestScope.CREATEERROR}"/>
            <table>
                <tr>
                    <td>Username (required)</td>
                    <td><input type="text" name="txtUsername" value="" /></td>
                    <td>
                        
                        <c:if test="${not empty error.usernameLength}">                          
                            <font color="red">
                                ${error.usernameLength}
                            </font>                            
                        </c:if>
                        
                       
                        <c:if test="${not empty error.usernameIsExist}">
                            <font color="red">
                                ${error.usernameIsExist}
                            </font>
                        </c:if>
                         
                       
                    </td>
                </tr>
                <tr>
                    <td>Email (required)</td>
                    <td><input type="text" name="txtEmail" value="" /></td>
                    <td>
                      
                        <c:if test="${not empty error.emailLength}">
                            <font color="red">
                                ${error.emailLength}
                            </font>
                        </c:if>
                        <c:if test="${not empty error.isCorrectMail}">
                            <font color="red">
                                ${error.isCorrectMail}
                            </font>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="txtFirstName" value="" /></td>
                    <td>
                        
                        <c:if test="${not empty error.firstnameLength}">
                            <font color="red">
                                ${error.firstnameLength}
                            </font>
                        </c:if>
                        
                    </td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="txtLastName" value="" /></td>
                    <td>
                        
                        <c:if test="${not empty error.lastnameLength}">
                            <font color="red">
                                ${error.lastnameLength}
                            </font>
                        </c:if>
                        
                    </td>
                </tr>
                <tr>
                    <td>Website</td>
                    <td><input type="text" name="txtWebsite" value="" /></td>
                    <td>
                        
                        <c:if test="${not empty error.websiteLength}">
                            <font color="red">
                                ${error.websiteLength}
                            </font>
                        </c:if>
                        <c:if test="${not empty error.isCorrectWeb}">
                            <font color="red">
                                ${error.isCorrectWeb}
                            </font>
                        </c:if>
                        
                    </td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="txtPassword" value="" /></td>
                    <td>
                        
                        <c:if test="${not empty error.passwordLength}">
                            <font color="red">
                                ${error.passwordLength}
                            </font>
                        </c:if>
                        
                    </td>
                </tr>
                <tr>
                    <td>Confirm Password </td>
                    <td><input type="password" name="txtRePassword" value="" /></td>
                    <td>
                        
                        <c:if test="${not empty error.repasswordLength}">
                            <font color="red">
                                ${error.repasswordLength}
                            </font>
                        </c:if>
                        
                        <c:if test="${not empty error.rePassNotMatch}">
                            <font color="red">
                                ${error.rePassNotMatch}
                            </font>
                        </c:if>
                       
                    </td>
                </tr>
                <tr>
                    <td>Send User Notification</td>
                    <td>
                        <input type="checkbox" name="checkSend"/>Send the new user an email about their account
                    </td>
                </tr>
                <tr>
                    <td>Role</td>
                    <td>
                        <select name="txtRole">
                            <c:forEach var="roleList" items="${listRole}" varStatus="counter">
                                <option>${roleList.roleName}</option>
                            </c:forEach>
                            
                            
                        </select>
                    </td>
                </tr>
                <tr>
                   
                 
                </tr>
                
                
            </table>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
