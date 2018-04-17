<%-- 
    Document   : list
    Created on : Jan 20, 2018, 11:50:50 AM
    Author     : student
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Page</title>
        <style>
            #searchLayout{
                margin-left: 150px;
            }
        </style>
    </head>

    <body>
        <c:if test="${empty sessionScope}">
            <jsp:forward page="login.html"/>
        </c:if>
        
        <c:if test="${not empty sessionScope}">
            
        
            <form action="Logout" method="POST">
                <font color="red">

                    Welcome, ${sessionScope.USERNAME} <input type="submit" value="Logout" name="btAction" />
                </font>  
            </form>

    
            <c:set var="role" value="${requestScope.ROLE}"/>    
            <c:set var="result" value="${requestScope.LISTACCOUNT}"/>        
            <c:set var="listRole" value="${requestScope.LISTROLE}"/>
            <c:set var="number" value="${requestScope.NUMBERRESULT}"/>
            <c:set var="selected" value="${requestScope.SELECTED}"/>
            <c:set var="search" value="${requestScope.SEARCHVALUE}"/>
            
            <c:if test="${role == 'Ad'}">
                <div id="Title-Add">
                    <h1>List Account</h1>
                    <form action="forward" method="POST">
                        <input type="hidden" name="txtRole" value="${role}" />
                        <input type="submit" value="Add New" name="btAction" />
                    </form>  
                </div>
                         <br/>
                <form action="Search" method="POST" id="searchLayout">
                    Search Value: <input type="text" name="txtSearchValue" value="" />
                    <input type="hidden" name="txtRole" value="${role}" />
                    <input type="hidden" name="txtSelected" value="${selected}" />
                    <input type="submit" value="Search" name="btAction" />
                </form> 
                    <br/>


                
                        <form action="printList" method="POST">
                            
                            <c:url var="urlRewriting" value="printList">
                                <c:param name="getListAction" value="All"/>
                                <c:param name="txtRole" value="${role}"/>
                            </c:url>
                            <a href="${urlRewriting}"> All (${requestScope.NUMBERALL}) |</a>
                            <table >
                                    <c:forEach var="roleList" items="${listRole}" varStatus="counter">
                                        <tr>
                                             
                                            <c:url var="urlRewriting" value="printList">
                                                <c:param name="getListAction" value="${roleList.roleID}"/>
                                                <c:param name="txtRole" value="${role}"/>
                                            </c:url>
                                             <a href="${urlRewriting}"> ${roleList.roleName} (${roleList.roleCount}) |</a>
                                            <%--
                                            <input type="submit" value="${roleList.roleName} (${roleList.roleCount})" name="getListAction" />
                                            --%>
                                        </tr>
                                    </c:forEach>
                            </table>
                          
                        </form>
                            
                             <br/>

                  
                
                    <form action="changeRole" method="POST">
                        <input type="hidden" name="txtRole" value="${role}" />
                        <input type="hidden" name="txtSearchValue" value="${search}" />
                        <input type="hidden" name="txtSelected" value="${selected}" />
                        <select name="txtChangeRole">
                            <c:forEach var="roleList" items="${listRole}" varStatus="counter">
                                <option>Change Role to ${roleList.roleName}</option>
                                
                            </c:forEach>
                            <input type="submit" value="Change" name="btAction" />
                            
                        </select>
                    
                    <c:if test="${number gt 0}">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ${number} items
                    </c:if>
                    
                    
                    <c:if test="${not empty result}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>
                                    <input type="checkbox" id="chkAll" onclick="checkBox(this)" />
                                </th>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Role</th>

                            </tr>
                        </thead>
                        <tbody>
                            
                                <c:if test="${role == 'Ad'}">
                                    <c:forEach var="dto" items="${result}" varStatus="counter">
                                    <tr>
                                        <td><input type="checkbox" name="chkItem" value="${dto.username}" onclick="uncheckBox(this)"/></td>
                                        <td>${counter.count}</td>
                                        <td>${dto.username}
                                            <input type="hidden" name="txtUsername" value="${dto.username}" />
                                        </td>
                                        <td>${dto.firstname}&nbsp${dto.lastname}</td>
                                        <td>${dto.email}</td>
                                        <td>${dto.roleName}
                                            <input type="hidden" name="txtRoleName" value="${dto.roleName}"/>
                                        </td>

                                    </tr>    
                                </c:forEach>

                                </c:if>
                               
      
                        </tbody>
                    </table>
                    </c:if> 
                   <c:if test="${empty result}">
                        <tr>
                            <font color="red">
                                <br/>No result
                            </font>   
                         </tr>
                                
                    </c:if>
                    </form>
                

            </c:if>


            <c:if test="${role != 'Ad'}">
                <h1>List Account</h1>
                <c:if test="${not empty result}">
                    <table border="1">
                        <thead>
                            <tr>                        
                                <th>Username</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Role</th>

                            </tr>
                        </thead>
                        <tbody>
                            <tr>                        
                                <td>${result.username}</td>
                                <td>${result.firstname}&nbsp${result.lastname}</td>
                                <td>${result.email}</td>
                                <td>${result.roleName}</td>
                            </tr>



                        </tbody>
                    </table>
                </c:if>


            </c:if>
        </c:if>
    </body>
    <script>
        function checkBox(rs){
            var checkboxes = document.getElementsByName("chkItem");

            for (var i = 0; i < checkboxes.length; i++) {
                checkboxes[i].checked = rs.checked;
            }
    }
        function uncheckBox(rs){
            if(rs.checked == false){
                document.getElementById("chkAll").checked = false;
            }
        }
        
    </script>
</html>
