<%-- 
    Document   : login
    Created on : Nov 17, 2021, 8:33:07 PM
    Author     : SLM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1 style="font-size:30px;">Login Page</h1>
        <form action="Login" method="POST">
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" size="20" required></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" size="20" required></td>
                </tr>
            </table>
            <a href="register.jsp">Register</a>
            <p><input type="submit" value="Login"></p>
        </form>
    </body>
</html>
