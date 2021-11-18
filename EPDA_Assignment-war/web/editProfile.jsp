<%-- 
    Document   : editProfile
    Created on : Nov 17, 2021, 11:26:57 PM
    Author     : SLM
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile Page</title>
    </head>
    <body>
        <form action="EditProfile" method="POST">
            <%
                HttpSession s = request.getSession(false);
                User user = (User)s.getAttribute("login");
            %>
            <table>                
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" size="20" value="${user.getUsername()}" required></td>
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
                    <td>Phone number:</td>
                    <td><input type="tel" name="phone" size="20" required></td>
                </tr>
                <tr>
                    <td>IC:</td>
                    <td><input type="text" name="ic" size="20" required></td>
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
