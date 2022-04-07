<%-- 
    Document   : appointmentRegister
    Created on : Nov 20, 2021, 12:21:35 AM
    Author     : SLM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Appointment Register Page</title>
    </head>
    <body>
        <%
            String clinicId = request.getParameter("clinicId");
            String userId = request.getParameter("userId");
            
            if (clinicId == null) {
                clinicId = "";
            }
            if (userId == null) {
                userId = "";
            }
            request.setAttribute("userId", userId);
            request.setAttribute("clinicId", clinicId);
        %>
        <a href="MinistryHome">Back</a>
        <br><br>
        <form action="AppointmentRegister?userId=${userId}&clinicId=${clinicId}" method="POST">
            <table>
                <tr>
                    <td>UserID:</td>
                    <td> <input type="text" name="userId" size="20" value="${userId}" required disabled></td>
                    <td><a href="SelectUser?option=user&userId=${userId}&clinicId=${clinicId}&from=appointmentRegister.jsp">Select User</a></td>
                </tr>
                <tr>
                    <td>ClinicID:</td>
                    <td> <input type="text" name="clinicId" size="20" value="${clinicId}" required disabled></td>
                    <td><a href="SelectUser?option=clinic&userId=${userId}&clinicId=${clinicId}&from=appointmentRegister.jsp">Select Clinic</a></td>
                </tr>
                <tr>
                    <td>Appointment Date:</td>
                    <td><input type="date" name="date" size="20" required></td>
                </tr>
                <tr>
                    <td>Appointment Time</td>
                    <td><input type="time" name="time" size="20" required></td>
                </tr>
                <tr>
                    <td>Dosage to take:</td>
                    <td><input type="text" name="dose" size="20" required></td>
                </tr>
            </table>
            <%if (!userId.equals("") & !clinicId.equals("")){%>
            <p><input type="submit" value="Create Appointment"></p>
        <%}%>
        </form>
    </body>
</html>
