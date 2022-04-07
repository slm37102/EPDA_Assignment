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
import model.Users;
import model.UsersFacade;

/**
 *
 * @author SLM
 */
@WebServlet(name = "SelectUser", urlPatterns = {"/SelectUser"})
public class SelectUser extends HttpServlet {

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
        
        String i_value = request.getParameter("i");
        boolean isClinic = request.getParameter("option").equals("clinic");
        String userId = request.getParameter("userId");
        String clinicId = request.getParameter("clinicId");
        String from = request.getParameter("from");
        String url = from + "?userId=" + userId + "&clinicId=" + clinicId;
        List<Users> userList = isClinic?usersFacade.findAllClinic():usersFacade.findAllPublicUser();
        
        try (PrintWriter out = response.getWriter()) {
            //back button
            out.println("<a href=\""+url+"\">Back</a>");
            
            out.println("<br><br><table class=\"blueTable\">\n" +
                "  <tr>\n" +
                "    <th>Username</th>\n" +
                "    <th>Name</th>\n" +
                "    <th>Phone Number</th>\n" +
                "    <th>Email</th>\n" +
                "    <th>Address</th>\n" +
                "    <th>Select</th>\n" +
                "  </tr>");
            
            for (int i = 0; i < userList.size(); i++) {
                String id = userList.get(i).getId().toString();
                if (isClinic) {
                    url = from + "?i="+i_value+"&userId=" + userId + "&clinicId=" + id;
                } else {
                    url = from + "?i="+i_value+"&userId=" + id + "&clinicId=" + clinicId;
                }
                out.print("  <tr>\n" +
                    "    <td>"+userList.get(i).getUsername()+"</td>\n" +
                    "    <td>"+userList.get(i).getName()+"</td>\n" +
                    "    <td>"+userList.get(i).getPhone()+"</td>\n" +
                    "    <td>"+userList.get(i).getEmail()+"</td>\n" +
                    "    <td>"+userList.get(i).getAddress()+"</td>\n" +
                    "    <td><a href=\""+url+"\">Select</a></td></tr>\n");
            }
            
            out.print("</table><br>");
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
