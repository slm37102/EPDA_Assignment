<%-- 
    Document   : appointmentRegister
    Created on : Nov 20, 2021, 12:21:35 AM
    Author     : SLM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Appointment Register Page</title>
    </head>
    <body>
        <a href="MinistryHome">Back</a>
        <br><br>
        <form action="AppointmentRegister" method="POST">
            <table>
                <tr>
                    <td>UserID:</td>
                    <td><input type="text" name="userId" size="20" required></td>
                </tr>
                <tr>
                    <td>ClinicID:</td>
                    <td><input type="text" name="clinicId" size="20" required></td>
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
            <p><input type="submit" value="Create Appointment"></p>
        </form>
    </body>
</html>
