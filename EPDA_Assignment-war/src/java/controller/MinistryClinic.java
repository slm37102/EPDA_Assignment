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
import javax.servlet.http.HttpSession;
import model.Users;
import model.UsersFacade;

/**
 *
 * @author SLM
 */
@WebServlet(name = "MinistryClinic", urlPatterns = {"/MinistryClinic"})
public class MinistryClinic extends HttpServlet {
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
        
        List<Users> userList = usersFacade.findAllClinic();
        
        HttpSession s = request.getSession(false);
        s.setAttribute("userList", userList);
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<h1 style=\"font-size:30px;\">Clinic Staff Infomation</h1>");
        
            request.getRequestDispatcher("ministryBanner.jsp").include(request, response);
            //print tables header
            out.println("<br><br><table class=\"blueTable\">\n" +
                "  <tr>\n" +
                "    <th>Username</th>\n" +
                "    <th>Name</th>\n" +
                "    <th>Phone Number</th>\n" +
                "    <th>Email</th>\n" +
                "    <th>Address</th>\n" +
                "    <th>Edit</th>\n" +
                "    <th>Delete</th>\n" +
                "  </tr>");
            
            for (int i = 0; i < userList.size(); i++) {
                //print tables row
                out.print("  <tr>\n" +
                    "    <td>"+userList.get(i).getUsername()+"</td>\n" +
                    "    <td>"+userList.get(i).getName()+"</td>\n" +
                    "    <td>"+userList.get(i).getPhone()+"</td>\n" +
                    "    <td>"+userList.get(i).getEmail()+"</td>\n" +
                    "    <td>"+userList.get(i).getAddress()+"</td>\n" +
                    "    <td><a href=\"editProfile.jsp?i="+i+"&from=MinistryClinic\">Edit</a> |</td>\n" +
                    "    <td><a href=\"UserDelete?id="+userList.get(i).getId()+"&from=MinistryClinic\">Delete</a> |</td>\n" +
                    "  </tr>");
            }
            out.print("</table><br>");
            
            if (request.getParameter("deletedName") != null) {
                out.print("<br>User "+ request.getParameter("deletedName") +" has been deleted.");
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
