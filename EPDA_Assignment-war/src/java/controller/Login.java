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
import model.Clinic;
import model.ClinicFacade;
import model.Ministry;
import model.MinistryFacade;
import model.PublicUser;
import model.PublicUserFacade;

/**
 *
 * @author SLM
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @EJB
    private MinistryFacade ministryFacade;

    @EJB
    private ClinicFacade clinicFacade;

    @EJB
    private PublicUserFacade publicUserFacade;
    
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
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // search for user 
        // can change to other popega method 
        
        List<PublicUser> userList = publicUserFacade.findAll();
        PublicUser user = null;        
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username)) {
                user = userList.get(i);
                break;
            }
        }
        
        List<Clinic> clinicList = clinicFacade.findAll();
        Clinic clinic = null;
        for (int i = 0; i < clinicList.size(); i++) {
            if (clinicList.get(i).getUsername().equals(username)) {
                clinic = clinicList.get(i);
                break;
            }
        }
        
        List<Ministry> ministryList = ministryFacade.findAll();
        Ministry ministry = null;
        for (int i = 0; i < ministryList.size(); i++) {
            if (ministryList.get(i).getUsername().equals(username)) {
                ministry = ministryList.get(i);
                break;
            }
        }        
        
        try (PrintWriter out = response.getWriter()) {    
            if (user != null || clinic != null || ministry != null ) {
                // check user password
                if (user.getPassword().equals(password)) {
                    request.getRequestDispatcher("publicUserHome.jsp").include(request, response);
                } 
                // check clinic password
                else if (clinic.getPassword().equals(password)) {
                    request.getRequestDispatcher("clinicHome.jsp").include(request, response);
                    out.println("clinic la");
                }
                // check ministry password
                else if (ministry.getPassword().equals(password)) {
                    request.getRequestDispatcher("ministryHome.jsp").include(request, response);
                    out.println("ministry la");
                } 
                // all password wrong
                else {
                    request.getRequestDispatcher("login.jsp").include(request, response);
                    out.println("wrong password");
                }
            } else {
                request.getRequestDispatcher("login.jsp").include(request, response); 
                out.println("wrong username");
            }
//            if (username.equals("admin") && password.equals("admin")){
//                request.getRequestDispatcher("login.jsp").include(request , response);
//                out.println("<br><br>nice");
//            }
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
