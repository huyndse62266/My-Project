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
@WebServlet(name = "PrintListServlet", urlPatterns = {"/PrintListServlet"})
public class PrintListServlet extends HttpServlet {
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
       
            String role =(String) request.getAttribute("ROLE");
            String roleBt = request.getParameter("txtRole");
            String username = (String)request.getAttribute("USERNAME");
            String selectRole = request.getParameter("getListAction");
            String selected = (String) request.getAttribute("SELECTED");
            AccountDAO dao = new AccountDAO();
            RoleDAO daoRo = new RoleDAO();
  
          
            if(selectRole != null || selected != null){
               if(selected != null){
                   selectRole = selected;
                   if(selectRole.equals("All")){
                        dao.printAllListAccount();
                        List<AccountDTO> list = dao.getListAccount();

                        daoRo.getRole();
                        List<RoleDTO> listRole =daoRo.getListRole();

                        request.setAttribute("ROLE", roleBt);
                        request.setAttribute("LISTACCOUNT", list);
                        request.setAttribute("LISTROLE", listRole);
                        request.setAttribute("NUMBERRESULT", list.size());
                        request.setAttribute("NUMBERALL", daoRo.countAll());
                    }else {


                        dao.printAllListAccountbyRole(selectRole);
                        List<AccountDTO> list = dao.getListAccount();

                        daoRo.getRole();
                        List<RoleDTO> listRole =daoRo.getListRole();
                        if(list != null){
                            request.setAttribute("LISTACCOUNT", list);                   
                            request.setAttribute("NUMBERRESULT", list.size());
                            request.setAttribute("NUMBERALL", daoRo.countAll());
                        }else{
                            request.setAttribute("NUMBERALL", daoRo.countAll());
                            request.setAttribute("NUMBERRESULT", "0");
                        }


                        request.setAttribute("ROLE", roleBt);
                        request.setAttribute("LISTROLE", listRole);


                    }
                    request.setAttribute("SELECTED", selectRole);
               }else{
                    if(selectRole.equals("All")){
                        dao.printAllListAccount();
                        List<AccountDTO> list = dao.getListAccount();

                        daoRo.getRole();
                        List<RoleDTO> listRole =daoRo.getListRole();

                        request.setAttribute("ROLE", roleBt);
                        request.setAttribute("LISTACCOUNT", list);
                        request.setAttribute("LISTROLE", listRole);
                        request.setAttribute("NUMBERRESULT", list.size());
                        request.setAttribute("NUMBERALL", daoRo.countAll());
                    }else {


                        dao.printAllListAccountbyRole(selectRole);
                        List<AccountDTO> list = dao.getListAccount();

                        daoRo.getRole();
                        List<RoleDTO> listRole =daoRo.getListRole();
                        if(list != null){
                            request.setAttribute("LISTACCOUNT", list);                   
                            request.setAttribute("NUMBERRESULT", list.size());
                            request.setAttribute("NUMBERALL", daoRo.countAll());
                        }else{
                            request.setAttribute("NUMBERALL", daoRo.countAll());
                            request.setAttribute("NUMBERRESULT", "0");
                        }


                        request.setAttribute("ROLE", roleBt);
                        request.setAttribute("LISTROLE", listRole);


                    }
                    request.setAttribute("SELECTED", selectRole);
               }
                
            }else{
                if(role.equals("Ad")){
                    dao.printAllListAccount();
                    List<AccountDTO> list = dao.getListAccount();

                    daoRo.getRole();
                    List<RoleDTO> listRole =daoRo.getListRole();
                    
                    request.setAttribute("LISTACCOUNT", list);
                    request.setAttribute("LISTROLE", listRole);
                    request.setAttribute("NUMBERRESULT", list.size());
                    request.setAttribute("NUMBERALL", daoRo.countAll());
                }else{
               
                    AccountDTO info = dao.getFullInfo(username);
                    request.setAttribute("LISTACCOUNT", info);
                    request.setAttribute("NUMBERRESULT", "1");
               }
            }
            
        } catch (Exception e) {
            log("Have error in PrintList Servlet:" +e.getMessage());
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
