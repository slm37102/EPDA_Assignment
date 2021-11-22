/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Users;
import model.UsersFacade;

/**
 *
 * @author SLM
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @EJB
    private UsersFacade userFacade;
    
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
        Users user = userFacade.findUser(username);
        
        //if first time admin login
        if (username.equals("admin") && user == null && password.equals("admin")) {
                user = new Users(0, "admin", "admin", "-", "-", "-", "-", "-", "-");
                userFacade.create(user);
        }
        
        try (PrintWriter out = response.getWriter()) {    
            if (user != null) {
                // check user password
                if (user.getPassword().equals(password)) {
                    HttpSession s = request.getSession();
                    s.setAttribute("login", user);
                    
                    switch (user.getUserType()) {
                        case 0: //if is Ministry staff
                            request.getRequestDispatcher("MinistryHome").include(request, response);
                            break;
                        case 1: //if is Clinic staff
                            request.getRequestDispatcher("ClinicHome").include(request, response);
                            break;
                        case 2: //if is Public User
                            request.getRequestDispatcher("PublicUserHome").include(request, response);
                            break;
                    }
                }
                // all password wrong
                else {
                    request.getRequestDispatcher("login.jsp").include(request, response);
                    out.println("wrong username or password");
                }
            } else {
                request.getRequestDispatcher("login.jsp").include(request, response); 
                out.println("wrong username or password");
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
