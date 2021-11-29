/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
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
import model.UsersFacade;

/**
 *
 * @author SLM
 */
@WebServlet(name = "Report", urlPatterns = {"/Report"})
public class Report extends HttpServlet {

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
        
        List<Users> ministryList = usersFacade.findAllMinistry();
        List<Users> clinicList = usersFacade.findAllClinic();
        List<Users> publicUserList = usersFacade.findAllPublicUser();
        List<Appointment> appointmentList = appointmentFacade.findAll();
        
        Map<String, Integer> account = new Hashtable<String, Integer>();
        Map<String, Integer> gender = new Hashtable<String, Integer>();
        Map<Integer, Integer> dose = new Hashtable<Integer, Integer>();
        Map<String, Integer> vacPerDay = new Hashtable<String, Integer>();
        Map<String, Integer> vacStatus = new Hashtable<String, Integer>();
        
        account.put("Number of Ministry Account", ministryList.size());
        account.put("Number of Clinic Account", clinicList.size());
        account.put("Number of Public User Account", publicUserList.size());
        
        gender.put("Male", 0);
        gender.put("Female", 0);
        
        vacStatus.put("Pending", 0);
        vacStatus.put("Accepted", 0);
        vacStatus.put("Rejected", 0);
        vacStatus.put("Vaccine Taken", 0);
        
        int male = 0, female = 0, total;
        for (int i = 0; i < publicUserList.size(); i++) {
            Users user = publicUserList.get(i);
            if (user.getGender().equals("Male")) {
                gender.put("Male", gender.get("Male")+1);
            } else {
                gender.put("Female", gender.get("Female")+1);
            }
        }
        
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment appointment = appointmentList.get(i);
            
            String stringDate = new SimpleDateFormat("yyyy-MM-dd").format(appointment.getAppointDate());
            if (vacPerDay.get(stringDate) == null) {
                vacPerDay.put(stringDate, 1);
            } else {
                vacPerDay.put(stringDate, vacPerDay.get(stringDate) + 1);
            }
            
            if (appointment.isFinishVac()) {
                int numDoze = appointment.getNumDose();
                if (dose.get(numDoze) == null) {
                    dose.put(numDoze, 1);
                } else {
                    dose.put(numDoze, dose.get(numDoze) + 1);
                }
            }
            // pending
            switch (appointment.isAccepted()) {
                case 0:
                    vacStatus.put("Pending", vacStatus.get("Pending") + 1);
                    // rejected
                    break;
                case -1:
                    vacStatus.put("Rejected", vacStatus.get("Rejected") + 1);
                    // accepted
                    break;
                default:
                    if (appointment.isFinishVac()) {
                        vacStatus.put("Vaccine Taken", vacStatus.get("Vaccine Taken") + 1);
                    } else {
                        vacStatus.put("Accepted", vacStatus.get("Accepted") + 1);
                    }
                    break;
            }
        }
        
        HttpSession s = request.getSession();
        s.setAttribute("account", account);
        s.setAttribute("gender", gender);
        s.setAttribute("dose", dose);
        s.setAttribute("vacPerDay", vacPerDay);
        s.setAttribute("vacStatus", vacStatus);
        
        try (PrintWriter out = response.getWriter()) {  
            out.print("<h1 style=\"font-size:30px;\">Report</h1>");
            request.getRequestDispatcher("ministryBanner.jsp").include(request, response);
            out.print("<br>");
            request.getRequestDispatcher("report.jsp").include(request, response);
//            out.print(account.keySet());
//            out.print(account.values());
//            out.print(gender.keySet());
//            out.print(gender.values());
//            out.print(dose.keySet());
//            out.print(dose.values());
//            out.print(vacPerDay.keySet());
//            out.print(vacPerDay.values());
//            out.print(vacStatus.keySet());
//            out.print(vacStatus.values());
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
