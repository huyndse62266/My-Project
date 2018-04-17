/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynd.servlet;

import huynd.account.AccountDAO;
import huynd.account.AccountDTO;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private final String invalidPage = "error.jsp";
    private final String printListServlet = "PrintListServlet";
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
        String url = invalidPage;
        try {
            String username =  request.getParameter("txtUsername");
            String password =  request.getParameter("txtPassword");
            if(username.trim().length() > 0 && password.trim().length() > 0 ){
                AccountDAO dao = new AccountDAO();
                AccountDTO dto = dao.login(username, password);
          
                if(dto != null){
                    
                    url = printListServlet;
                    HttpSession session = request.getSession();
         
                    session.setAttribute("USERNAME", dto.getUsername());
                    request.setAttribute("USERNAME", dto.getUsername());
                    request.setAttribute("ROLE", dto.getRoleID());
                    
                }else{
                     request.setAttribute("ERRORLOGIN", "Username or password is invalid ");
                }
            }else{
                request.setAttribute("ERRORLOGIN", "Username or password is invalid ");
            }
        } catch (Exception e) {
            log("Have error in Login Servlet:" +e.getMessage());
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
