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
import model.Users;
import model.UsersFacade;

/**
 *
 * @author SLM
 */
@WebServlet(name = "EditProfile", urlPatterns = {"/EditProfile"})
public class EditProfile extends HttpServlet {

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
        Users user = usersFacade.find(id);
        
        String backPage = request.getParameter("backPage");
        String i = request.getParameter("i");
        
        String username = request.getParameter("username");
        
        // if username not exist and if username not change 
        
        
        if (
                user.getUsername().equals(username) || (
                    !user.getUsername().equals("admin") || (    
                        !username.equals("admin") || !(usersFacade.findUser(username) == null)
                    )
                )
        ) {
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            if (user.getUserType() != 1) {
                // for ministry and public user
                String gender = request.getParameter("gender");
                String ic = request.getParameter("ic");
                user.setGender(gender);
                user.setIc(ic);
            }

            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            user.setPhone(phone);
            user.setEmail(email);
            user.setAddress(address);

            usersFacade.edit(user);
            
            request.getRequestDispatcher(backPage).include(request, response);
            
        } else {
            
            try (PrintWriter out = response.getWriter()) {
                String output;
                String page = "editProfile.jsp";
                if (user.getUsername().equals("admin")) {
                    output = "<br><br>admin cannot change username.";
                }                 
                else if (username.equals("admin")) {
                    output = "<br><br>cannot change username to admin.";
                } else {
                    output = "<br><br>Username '"+ username +"' has been used.";
                }
                
                if (i != null) {
                    page = "editProfile.jsp?i="+i+"&from="+backPage;
                }
                
                request.getRequestDispatcher(page).include(request, response);   
                out.println(output);
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
