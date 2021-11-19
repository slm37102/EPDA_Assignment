<%-- 
    Document   : register
    Created on : Nov 17, 2021, 8:40:00 PM
    Author     : SLM
--%>

<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <%
            HttpSession s = request.getSession(false);
            
            if (s.getAttribute("login") == null) {
                request.setAttribute("backPage","login.jsp");
            } else {
                request.setAttribute("backPage","MinistryManage");
            }  
        %>
        <a href="${backPage}">Back</a>
        <br><br>
        <%--
            // TODO: change Clinic to not show gender and ic
            boolean isCheck = false;
        --%>
        <form action="Register" method="POST">
            <table>
                <%
                    if (s.getAttribute("login") == null) {
                %>
                <tr>
                    <td>Are you a Public User or a Clinic:</td>
                    <td>
                        <input type="radio" id="userType1" name="userType" value="Public User" checked="checked">
                        <label for="userType1">Public User</label>
                    </td>
                    <td>
                        <input type="radio" id="userType2" name="userType" value="Clinic">
                        <label for="userType2">Clinic</label>
                    </td>
                </tr>
                <%}%>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" size="20" required></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" size="20" required></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name" size="20" required></td>
                </tr>
                <tr>
                    <td>Gender:</td>
                    <td>
                        <input type="radio" id="gender1" name="gender" value="Male" checked="checked">
                        <label for="gender1">Male</label>
                    </td>
                    <td>
                        <input type="radio" id="gender2" name="gender" value="Female">
                        <label for="gender2">Female</label>
                    </td>
                </tr>
                <tr>
                    <td>IC no.:</td>
                    <td><input type="text" name="ic" size="20" required></td>
                </tr>
                <tr>
                    <td>Phone number:</td>
                    <td><input type="tel" name="phone" size="20" required></td>
                </tr>
                <tr>
                    <td>Email Address:</td>
                    <td><input type="email" name="email" size="20" required></td>
                </tr>
            </table>
            <p><input type="submit" value="Register"></p>
        </form>
    </body>
</html>
