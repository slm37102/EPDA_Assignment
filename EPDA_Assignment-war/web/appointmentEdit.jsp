<%-- 
    Document   : appointmentEdit
    Created on : Nov 21, 2021, 1:56:39 AM
    Author     : SLM
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="model.Appointment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Appointment Edit Page</title>
    </head>
    <body>
        <a href="MinistryHome">Back</a>
        <br>
        <h1 style="font-size:30px;">Appointment Edit Page</h1>
        <br>
        <%
            HttpSession s = request.getSession(false);                       
            int i = Integer.parseInt(request.getParameter("i"));
            Appointment appointment = ((List<Appointment>)s.getAttribute("appointmentList")).get(i);

            String stringDate = new SimpleDateFormat("yyyy-MM-dd").format(appointment.getAppointDate());
            String stringTime = new SimpleDateFormat("HH:mm").format(appointment.getAppointDate());

            request.setAttribute("i", i);
            request.setAttribute("userId", appointment.getUserId());
            request.setAttribute("clinicId", appointment.getClinicId());
            request.setAttribute("date", stringDate);
            request.setAttribute("time", stringTime);
            request.setAttribute("dose", appointment.getNumDose());
        %>
        <form action="AppointmentEdit?i=${i}" method="POST">
            <table>
                <tr>
                    <td>UserID:</td>
                    <td><input type="text" name="userId" size="20" value="${userId}" required></td>
                </tr>
                <tr>
                    <td>ClinicID:</td>
                    <td><input type="text" name="clinicId" size="20" value="${clinicId}" required></td>
                </tr>
                <tr>
                    <td>Appointment Date:</td>
                    <td><input type="date" name="date" size="20" value="${date}" required></td>
                </tr>
                <tr>
                    <td>Appointment Time</td>
                    <td><input type="time" name="time" size="20" value="${time}" required></td>
                </tr>
                <tr>
                    <td>Dosage to take:</td>
                    <td><input type="text" name="dose" size="20" value="${dose}" required></td>
                </tr>
            </table>
            <p><input type="submit" value="Edit Appointment"></p>
        </form>
    </body>
</html>
