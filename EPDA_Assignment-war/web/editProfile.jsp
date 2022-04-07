<%-- 
    Document   : editProfile
    Created on : Nov 17, 2021, 11:26:57 PM
    Author     : SLM
--%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile Page</title>
    </head>
    <body>
        <%
            HttpSession s = request.getSession(false);
            Users user;
            // only from Ministry manage has from variable
            String backPage = request.getParameter("from");
            boolean notClinic = true;
            
            // if is edited by Ministry
            if (backPage != null) {
                int i = Integer.parseInt(request.getParameter("i"));
                request.setAttribute("i", "&i="+i);
                user = ((List<Users>)s.getAttribute("userList")).get(i);
                if (user.getUserType() == 1) {
                    notClinic = false;
                }
            } 
            // if is edited by User himself
            else {
                user = (Users)s.getAttribute("login");
                switch (user.getUserType()) {
                    case 0:
                        backPage = "MinistryHome";
                        break;
                    case 1:
                        backPage = "ClinicHome";
                        notClinic = false;
                        break;
                    case 2:
                        backPage = "PublicUserHome";
                        break;
                }
            }
            
            request.setAttribute("id", user.getId());
            request.setAttribute("username", user.getUsername());
            request.setAttribute("password", user.getPassword());
            request.setAttribute("name", user.getName());
            request.setAttribute("phone", user.getPhone());
            request.setAttribute("email", user.getEmail());            
            request.setAttribute("address", user.getAddress());            
            request.setAttribute("backPage",backPage);
            request.setAttribute("notClinic",notClinic);
        %>
        <a href=${backPage}>Back</a><br>
        <h1 style="font-size:30px;">Edit Profile</h1>
        <form action="EditProfile?id=${id}&backPage=${backPage}${i}" method="POST">
            <table>                
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" size="20" value="${username}" required></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" size="20" value="${password}" required></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name" size="20" value="${name}" required></td>
                </tr>
                <c:if test="${notClinic}">
                <%
                    String male = "";
                    String female = "";
                    if (user.getGender().equals("Male")) {
                        male = "checked=\"checked\"";
                    } else {
                        female = "checked=\"checked\"";
                    }
                    request.setAttribute("male", male);
                    request.setAttribute("female", female);
                    request.setAttribute("ic", user.getIc());
                %>
                <tr>
                    <td>Gender:</td>
                    <td>
                        <input type="radio" id="gender1" name="gender" value="Male"${male}>
                        <label for="gender1">Male</label>
                    </td>
                    <td>
                        <input type="radio" id="gender2" name="gender" value="Female"${female}>
                        <label for="gender2">Female</label>
                    </td>
                </tr>
                <tr>
                    <td>IC no.:</td>
                    <td><input type="text" name="ic" size="20" pattern="[0-9]{12}" value="${ic}" required></td>
                </tr>
                </c:if>
                <tr>
                    <td>Phone number:</td>
                    <td><input type="tel" name="phone" size="20" pattern="[0-9]{10,11}" value="${phone}" required></td>
                </tr>
                <tr>
                    <td>Email Address:</td>
                    <td><input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" name="email" size="20" value="${email}" required></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input type="text" name="address" size="20" value="${address}" required></td>
                </tr>
            </table>
            <p><input type="submit" value="Edit"></p>
        </form>
    </body>
</html>
