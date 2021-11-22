/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Appointment;
import model.AppointmentFacade;
import model.Users;
import model.UsersFacade;

/**
 *
 * @author SLM
 */
@WebServlet(name = "UserDelete", urlPatterns = {"/UserDelete"})
public class UserDelete extends HttpServlet {

    @EJB
    private AppointmentFacade appointmentFacade;
    
    @EJB
    private UsersFacade usersFacade;

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
        
        Long id = Long.parseLong(request.getParameter("id"));
        String from = request.getParameter("from");
        Users user = usersFacade.find(id);
        
        if (user.getUsername().equals("admin")) {
            // admin user cannot be deleted
            request.getRequestDispatcher("/"+from+"?admin=true").include(request, response);
            
        } else {            
            // delete appointment for the user
            if (user.getUserType() == 1) {
                List<Appointment> appointmentList = appointmentFacade.findAllFromClinic(id);
                for (int i = 0; i < appointmentList.size(); i++) {
                    appointmentFacade.remove(appointmentList.get(i));
                }
            } else if (user.getUserType() == 2) {
                List<Appointment> appointmentList = appointmentFacade.findAllFromPublicUser(id);
                for (int i = 0; i < appointmentList.size(); i++) {
                    appointmentFacade.remove(appointmentList.get(i));
                }
            }
            
            // delete the user
            usersFacade.remove(user);
            request.getRequestDispatcher("/"+from+"?deletedName="+user.getName()).include(request, response);
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
