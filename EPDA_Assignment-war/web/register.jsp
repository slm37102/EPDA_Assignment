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
        <script type="text/javascript">
        function changedValue() {
            var isPublicUser = document.getElementById("userType1").checked;
            var isClinic = document.getElementById("userType2").checked;
            if (isPublicUser === true) {
                document.getElementById("genderRow").hidden = false;
                document.getElementById("icRow").hidden = false;
            } else if (isClinic === true) {
                document.getElementById("genderRow").hidden = true;
                document.getElementById("icRow").hidden = true;
            }
        }
       </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <%
            HttpSession s = request.getSession(false);
            
            // if not login yet (register by user or clinic)
            if (s.getAttribute("login") == null) {
                request.setAttribute("backPage","login.jsp");
            } 
            // if login (register another ministry)
            else {
                request.setAttribute("backPage","MinistryManage");
            }
        %>
        <a href="${backPage}">Back</a>
        <br><br>
        <%--
            // TODO: change Clinic to not show gender and ic
            boolean isCheck = false;
        --%>
        <form action="Register?backPage=${backPage}" method="POST">
            <table>
                <%
                String publicUserCheck, ClinicCheck;
                if (request.getParameter("check") == null) {
                    publicUserCheck = "checked";
                    ClinicCheck = "";
                } else {
                    if (request.getParameter("check").equals("public")) {
                        publicUserCheck = "checked";
                        ClinicCheck = "";
                    } else {
                        ClinicCheck = "checked";
                        publicUserCheck = "";
                    }
                }
                request.setAttribute("publicUserCheck",publicUserCheck);
                request.setAttribute("ClinicCheck",ClinicCheck);
                    // if not login yet
                    if (s.getAttribute("login") == null) {
                %>
                <tr>
                    <td>Are you a Public User or a Clinic:</td>
                    <td>
                        <input type="radio" id="userType1" name="userType" value="Public User" onclick="javascript:changedValue()" checked="checked">
                        <label for="userType1">Public User</label>
                        <input type="radio" id="userType2" name="userType" value="Clinic" onclick="javascript:changedValue()">
                        <label for="userType2">Clinic</label>
                    </td>
                </tr>
                <%
                    }
                %>
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
                <tr id="genderRow">
                    <td>Gender:</td>
                    <td>
                        <input type="radio" id="gender1" name="gender" value="Male" checked="checked">
                        <label for="gender1">Male</label>
                        <input type="radio" id="gender2" name="gender" value="Female">
                        <label for="gender2">Female</label>
                    </td>
                </tr>
                <tr id="icRow">
                    <td>IC no.:</td>
                    <td><input type="text" name="ic" size="20"></td>
                </tr>
                <tr>
                    <td>Phone number:</td>
                    <td><input type="tel" name="phone" size="20" required></td>
                </tr>
                <tr>
                    <td>Email Address:</td>
                    <td><input type="email" name="email" size="20" required></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input type="text" name="address" size="20" required></td>
                </tr>
            </table>
            <p><input type="submit" value="Register"></p>
        </form>
    </body>
</html>
