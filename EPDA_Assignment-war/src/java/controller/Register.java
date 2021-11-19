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
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

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
        
        String userType = request.getParameter("userType");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        
        Users newUser = null;
        
        if (usersFacade.findUser(username) == null) {
            HttpSession s = request.getSession(false);
            // for ministry staff (only login able to access)
            if (s.getAttribute("login") != null) {
                String gender = request.getParameter("gender");
                String ic = request.getParameter("ic");
                newUser = new Users(0, username, password, name, gender, ic, phone, email);
            } 
            // for clinic staff
            else if (userType.equals("Clinic")) {       
                newUser = new Users(1, username, password, name, phone, email);
            }
            // for public user
            else if (userType.equals("Public User")) {
                String gender = request.getParameter("gender");
                String ic = request.getParameter("ic");
                newUser = new Users(2, username, password, name, gender, ic, phone, email);
            }

            usersFacade.create(newUser);
        }
        
        try (PrintWriter out = response.getWriter()) {
            if (newUser == null) {
                request.getRequestDispatcher("register.jsp").include(request, response);
                out.println("<br><br>Username "+ username +" has been used");
            } else {
                request.getRequestDispatcher("login.jsp").include(request, response);
                out.println("<br><br>Thank you "+name+", registration is done!");
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
