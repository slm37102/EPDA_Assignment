/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Appointment;
import model.AppointmentFacade;
import model.Users;

/**
 *
 * @author SLM
 */
@WebServlet(name = "ClinicHome", urlPatterns = {"/ClinicHome"})
public class ClinicHome extends HttpServlet {

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
        
        HttpSession s = request.getSession(false);
        Users user = (Users)s.getAttribute("login");
        
        List<Appointment> appointmentList = appointmentFacade.findAllFromClinic(user.getId());
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<h1 style=\"font-size:30px;\">Clinic Home Page</h1>");
            request.getRequestDispatcher("clinicBanner.jsp").include(request, response);
            // if has appointment
            if (appointmentList.size() > 0) {
                
                out.println("<br><br><table class=\"blueTable\">\n" +
                "  <tr>\n" +
                "    <th>Appointment ID</th>\n" +
                "    <th>Public User</th>\n" +
                "    <th>Clinic</th>\n" +
                "    <th>Appointment Date</th>\n" +
                "    <th>Appointment Time</th>\n" +
                "    <th>Dose</th>\n" +
                "    <th>Accept appointment</th>\n" +
                "    <th>Finished Vaccine</th>\n" +
                "    <th>Completed Vaccined</th>\n" +
                "  </tr>");

                for (int i = 0; i < appointmentList.size(); i++) {
                    // convert date format to date and time
                    String stringDate = new SimpleDateFormat("yyyy-MM-dd").format(appointmentList.get(i).getAppointDate());
                    String stringTime = new SimpleDateFormat("HH:mm").format(appointmentList.get(i).getAppointDate());
                    
                    String status;
                    switch (appointmentList.get(i).isAccepted()) {
                        case 1:
                            status = "Accepted";
                            break;
                        case -1:
                            status = "Rejected";
                            break;
                        default:
                            status = "Pending";
                            break;
                    }
                    
                    //print tables row
                    out.print("  <tr>\n" +
                        "    <td>"+appointmentList.get(i).getId()+"</td>\n" +
                        "    <td>"+appointmentList.get(i).getUserId()+"</td>\n" +
                        "    <td>"+appointmentList.get(i).getClinicId()+"</td>\n" +
                        "    <td>"+stringDate+"</td>\n" +
                        "    <td>"+stringTime+"</td>\n" +
                        "    <td>"+appointmentList.get(i).getNumDose()+"</td>\n" +
                        "    <td>"+status+"</td>\n" +
                        "    <td>"+(appointmentList.get(i).isFinishVac()?"Yes":"No")+"</td>\n");
                    
                    // if user accept then make it able to complete 
                    if (appointmentList.get(i).isAccepted() == 1 && !appointmentList.get(i).isFinishVac()) {
                        out.print("    <td><a href=\"AppointmentFinished?id="+appointmentList.get(i).getId()+"\">Completed Vaccined</a> |</td>\n");
                    } else {
                        out.print("    <td></td>\n");
                    }
                    
                    out.print("  </tr>");
                }
                out.print("</table>");
            } 
            // No appointment
            else {
                out.print("<br><br>No Appointment");
            }
            
            if (request.getParameter("finishedId") != null) {
                out.print("<br>Appointment "+request.getParameter("finishedId")+" has been completed.");
            }
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
