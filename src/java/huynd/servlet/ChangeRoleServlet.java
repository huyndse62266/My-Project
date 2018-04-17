/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynd.servlet;

import huynd.account.AccountDAO;
import huynd.role.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author student
 */
@WebServlet(name = "ChangeRoleServlet", urlPatterns = {"/ChangeRoleServlet"})
public class ChangeRoleServlet extends HttpServlet {
    private final String printListServlet = "PrintListServlet";
    private final String searchServlet = "SearchServlet";
    private final String errorPage = "error.jsp";
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
        PrintWriter out = response.getWriter();
        String url = printListServlet;
        try {
            AccountDAO dao = new AccountDAO();
            RoleDAO daoRo = new RoleDAO();
            boolean result = false;
            boolean flag = false;
            String roleLogin = request.getParameter("txtRole");
    
            String roleChange = request.getParameter("txtChangeRole");
            String selected = request.getParameter("txtSelected");
            String role[] = roleChange.split("Change Role to ");
            String searchValue = request.getParameter("txtSearchValue");
            roleChange = daoRo.getRoleID(role[1]);
       
            if(selected.isEmpty()){
                selected = "All";
            }
            HttpSession session = request.getSession(false);
            String usernameLogin = (String)session.getAttribute("USERNAME");
      
            String username[] = request.getParameterValues("chkItem");
            
            if(username != null){
              

               
                for (String string : username) {
                 
                    result = dao.updateRole(string, roleChange);
                 
                    if(string.equalsIgnoreCase(usernameLogin)){
                        flag = true;
                      
                    }
       
                }
              
                if(result){
                    if(flag && !roleChange.equals("Ad")){
                        if(session != null){
                            session.invalidate();
                        }
                        url = "login.html";
                    }else{
                        if(!searchValue.isEmpty() ){
                            url = searchServlet;
                            request.setAttribute("ROLE", roleLogin);
                            request.setAttribute("SELECTED", selected);
                            request.setAttribute("SEARCHVALUE", searchValue);
                            request.setAttribute("SEARCHSERVLET", "Search");
                         
                        }else{
                       
                            url = printListServlet;
                            request.setAttribute("ROLE", roleLogin);
                            request.setAttribute("SELECTED", selected);
                        }
                        
                        
                    }
                        
                    
                }else{
                 
                    url = errorPage;
                    request.setAttribute("ERROR", "Update Role is Failed");
                }
            }else{
           
            }
       
            request.setAttribute("ROLE", roleLogin);
        } catch (Exception e) {
            
            log("ChangeRoleServlet_SQL :"+ e.getMessage());
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
