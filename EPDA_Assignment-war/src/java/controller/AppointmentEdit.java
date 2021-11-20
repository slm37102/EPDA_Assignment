/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Appointment;
import model.AppointmentFacade;

/**
 *
 * @author SLM
 */
@WebServlet(name = "AppointmentEdit", urlPatterns = {"/AppointmentEdit"})
public class AppointmentEdit extends HttpServlet {

    @EJB
    private AppointmentFacade appointmentFacade;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            Long userId = Long.parseLong(request.getParameter("userId"));
            Long clinicId = Long.parseLong(request.getParameter("clinicId"));  
            String datetime = request.getParameter("date") + " " + request.getParameter("time");
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(datetime);
            int dose = Integer.parseInt(request.getParameter("dose"));
            
            int i = Integer.parseInt(request.getParameter("i"));
            Appointment appointment = appointmentFacade.findAll().get(i);
            
            appointment.setUserId(userId);
            appointment.setClinicId(clinicId);
            appointment.setAppointDate(date);
            appointment.setNumDose(dose);
            
            appointmentFacade.edit(appointment);
            
        } catch (NumberFormatException | ParseException e) {}
        
        try (PrintWriter out = response.getWriter()) {
            request.getRequestDispatcher("/MinistryHome").include(request, response);
            // not shown
            out.println("<br><br>Appointment has been updated.");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
