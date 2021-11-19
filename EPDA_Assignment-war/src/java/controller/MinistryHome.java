/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "MinistryHome", urlPatterns = {"/MinistryHome"})
public class MinistryHome extends HttpServlet {
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
        
        request.getRequestDispatcher("ministryBanner.jsp").include(request, response);
        
        List<Appointment> appointmentList = appointmentFacade.findAll();
        
        try (PrintWriter out = response.getWriter()) {
            // if has appointment
            if (appointmentList.size() > 0) {
                out.println("<br><br><table>\n" +
                "  <tr>\n" +
                "    <th>Public User</th>\n" +
                "    <th>Clinic</th>\n" +
                "    <th>Date</th>\n" +
                "    <th>Dose</th>\n" +
                "    <th>Accept appointment</th>\n" +
                "    <th>Finished Vaccine</th>\n" +
                "    <th>Edit</th>\n" +
                "    <th>Delete</th>\n" +
                "  </tr>");

                for (int i = 0; i < appointmentList.size(); i++) {
                    //print tables row
                    out.print("  <tr>\n" +
                        "    <td>"+appointmentList.get(i).getUserId()+"</td>\n" +
                        "    <td>"+appointmentList.get(i).getClinicId()+"</td>\n" +
                        "    <td>"+appointmentList.get(i).getAppointDate()+"</td>\n" +
                        "    <td>"+appointmentList.get(i).getNumDose()+"</td>\n" +
                        "    <td>"+appointmentList.get(i).isAccepted()+"</td>\n" +
                        "    <td>"+appointmentList.get(i).isFinishVac()+"</td>\n" +
                        "    <td><a href=\"\">Edit</a> |</td>\n" +
                        "    <td><a href=\"\">Delete</a> |</td>\n" +
                        "  </tr>");
                }
                out.print("</table>");
            } 
            // No appointment
            else {
                out.print("<br><br>No Appointment");
            }
            out.print("<br><br><form action=\"appointmentRegister.jsp\">\n" +
                        "    <input type=\"submit\" value=\"Create New Appointment\" />\n" +
                        "</form>");
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
