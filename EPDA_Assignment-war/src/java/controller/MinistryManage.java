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
@WebServlet(name = "MinistryManage", urlPatterns = {"/MinistryManage"})
public class MinistryManage extends HttpServlet {

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
        
        request.getRequestDispatcher("ministryBanner.jsp").include(request, response);
        
        List<Users> userList = usersFacade.findAllMinistry();
        // search value
//        String search = request.getParameter("search");
//        if (search != null) {
//            userList = usersFacade.findAllMinistry();
//        } else {
//            userList = usersFacade.findAllMinistry();
//        }
        
        HttpSession s = request.getSession(false);
        Users user = (Users)s.getAttribute("login");
        s.setAttribute("userList", userList);
        
        try (PrintWriter out = response.getWriter()) {
            // title
            out.println("<br><br>Ministry Staff Infomation");
            
//            // search
//            out.print("<br><br><form action=\"MinistryManage\">\n"+ 
//            "    Username:<input type=\"text\" name=\"search\" size=\"20\">" +
//            "    <br><input type=\"submit\" value=\"Search\" />\n" +
//            "</form>");

            //print tables header
            out.println("<br><br><table>\n" +
                "  <tr>\n" +
                "    <th>Name</th>\n" +
                "    <th>Gender</th>\n" +
                "    <th>Phone Number</th>\n" +
                "    <th>Email</th>\n" +
                "    <th>Edit</th>\n" +
                "    <th>Delete</th>\n" +
                "  </tr>");
            
            //print tables row
            for (int i = 0; i < userList.size(); i++) {
                out.print("  <tr>\n" +
                    "    <td>"+userList.get(i).getName()+"</td>\n" +
                    "    <td>"+userList.get(i).getGender()+"</td>\n" +
                    "    <td>"+userList.get(i).getPhone()+"</td>\n" +
                    "    <td>"+userList.get(i).getEmail()+"</td>\n");
                
                // if delete current user in table
                if (user != userList.get(i)) {
                    out.print("    <td><a href=\"editProfile.jsp?i="+i+"&from=MinistryManage\">Edit</a> |</td>\n" +
                        "    <td><a href=\"UserDelete?id="+userList.get(i).getId()+"&from=MinistryManage\">Delete</a> |</td>\n" +
                        "  </tr>");
                } else {
                    out.print("<td></td>\n" +
                        "<td></td>\n" +
                        "</tr>");
                }
            }
            out.print("</table><br>" +
                    "<form action=\"register.jsp\">\n" +
                    "    <input type=\"submit\" value=\"Create New Ministry Staff\" />\n" +
                    "</form>");
            
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
