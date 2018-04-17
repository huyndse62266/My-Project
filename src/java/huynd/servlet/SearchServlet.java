/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynd.servlet;

import huynd.account.AccountDAO;
import huynd.account.AccountDTO;
import huynd.role.RoleDAO;
import huynd.role.RoleDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
    private final String listPage = "list.jsp";
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
        String url = listPage;
        try {
            String roleBt = "";
            String roleSelected = "";
            String searchValue = "";
            String source = request.getParameter("SEARCHSERVLET");
            if(source != null){
                roleBt = (String) request.getAttribute("ROLE");
                roleSelected = (String) request.getAttribute("SELECTED");
                searchValue = (String) request.getAttribute("SEARCHVALUE");
            }else{
                roleBt = request.getParameter("txtRole");
                roleSelected = request.getParameter("txtSelected");
                searchValue = request.getParameter("txtSearchValue");
            }
      
            AccountDAO dao = new AccountDAO();
            RoleDAO daoRo = new RoleDAO();
            if(!searchValue.isEmpty()){
                dao.searchLikeName(searchValue,roleSelected);
                List<AccountDTO> list = dao.getListAccount();
   
                daoRo.getRole();
                List<RoleDTO> listRole =daoRo.getListRole();
                request.setAttribute("ROLE", roleBt);
                request.setAttribute("LISTACCOUNT", list);
                request.setAttribute("LISTROLE", listRole);
                request.setAttribute("NUMBERALL", daoRo.countAll());
                request.setAttribute("SELECTED", roleSelected);
                request.setAttribute("SEARCHVALUE", searchValue);
                request.setAttribute("NUMBERRESULT", list.size());
            }else{
                daoRo.getRole();
                List<RoleDTO> listRole =daoRo.getListRole();
                request.setAttribute("ROLE", roleBt);
                request.setAttribute("LISTROLE", listRole);
                request.setAttribute("NUMBERALL", daoRo.countAll());    
                
            }
            
        } catch (Exception e) {
            log("Have error in Search Servlet:" +e.getMessage());
        }finally{
            RequestDispatcher rd =request.getRequestDispatcher(url);
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
