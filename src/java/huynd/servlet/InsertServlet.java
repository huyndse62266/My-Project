/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynd.servlet;

import huynd.account.AccountDAO;
import huynd.account.AccountDTO;
import huynd.account.CreateAccountError;
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
@WebServlet(name = "InsertServlet", urlPatterns = {"/InsertServlet"})
public class InsertServlet extends HttpServlet {
    private final String printListServlet = "PrintListServlet";
    private final String loginPage = "login.html";
    private final String insertPage ="InsertNewAccount.jsp";
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
        String url = insertPage;
     
        try {
            String username = request.getParameter("txtUsername");
            String email = request.getParameter("txtEmail");
            String firstname = request.getParameter("txtFirstName");
            String lastname = request.getParameter("txtLastName");
            String website = request.getParameter("txtWebsite");
            String password = request.getParameter("txtPassword");
            String repassword = request.getParameter("txtRePassword");
            String roleLogin = request.getParameter("txtRoleLogin");
            String checkSend = request.getParameter("checkSend");

            String roleName = request.getParameter("txtRole");
            RoleDAO daoRo = new RoleDAO();
            String role = daoRo.getRoleID(roleName);
            boolean ErrorFound = false;
            boolean noti = false;
  
            if(checkSend == null){
                noti = false;
            }else{
                noti = true;
            }
            AccountDAO dao = new AccountDAO();
            CreateAccountError error = new CreateAccountError();
                if(username.trim().length() < 1 ){
                    ErrorFound = true;
                    error.setUsernameLength("Username can't be blank");
                }
                if(email.trim().length() < 1 ){
                    ErrorFound = true;
                    error.setEmailLength("Email can't be blank");
                }else if(!email.matches("\\w+@\\w+[.]\\w+([.]\\w+)?")){
                    ErrorFound = true;
                    error.setIsCorrectMail("Email is incorret. Format (xxx@xxx.xxx)");
                }
                if(firstname.trim().length() < 1 ){
                    ErrorFound = true;
                    error.setFirstnameLength("First Name can't be blank");
                }
                if(lastname.trim().length() < 1 ){
                    ErrorFound = true;
                    error.setLastnameLength("Last Name can't be blank");
                }
              
                if(website.trim().length() < 1 ){
                    ErrorFound = true;
                    error.setWebsiteLength("Website can't be blank");
                }else if(!website.matches("\\w+.\\w+[.]\\w+([.]\\w+)?")){
                    ErrorFound = true; 
                    error.setIsCorrectWeb("Web is incorrect format");
                }
                if(password.trim().length() < 1 ){
                    ErrorFound = true;
                    error.setPasswordLength("Password can't be blank");
                }
             
                if(repassword.trim().length() < 1 ){
                    ErrorFound = true;
                    error.setRepasswordLength("Confirm Password can't be blank");
                }
                else if(!repassword.matches(password)){
                    ErrorFound = true;
                    error.setRePassNotMatch("Password and Confirm Password aren't match");                 
                }
     
                dao.getExistAccount();
                boolean flag = false;
                List<String> listExist = dao.getListUsername();
                for (String string : listExist) {
             
                    flag = username.equalsIgnoreCase(string);
                    if(flag){
                        ErrorFound = true;
                        error.setUsernameIsExist("Username is Exists. Try to add different username");
                        break;
                    }
                }
     
                if(!ErrorFound){

                    AccountDTO dto = new AccountDTO(username, email, firstname, lastname, password, website, noti, role);
                    boolean result = dao.insertAccount(dto);
                    if(result){
                        if(roleLogin.equals("Ad")){
                            url = printListServlet;
                        }else{
                            url = loginPage;
                        }
                        
               
                        request.setAttribute("ROLE", roleLogin);
                    }else{
                        ErrorFound = true;                       
                        url = "error.jsp";
                        request.setAttribute("ERROR", "Insert new account failed");
                    }
                }else{
                 
                    if(roleLogin.equals("Ad")){
                  
                    
                        daoRo.getRole();
                        List<RoleDTO> listRole =daoRo.getListRole();
                        request.setAttribute("LISTROLE", listRole);
                        request.setAttribute("CREATEERROR", error);
                        request.setAttribute("ROLE", roleLogin);
                    }else{
             
                 
                        daoRo.getRoleWithoutAdmin();
                        List<RoleDTO> listRole =daoRo.getListRole();
                        request.setAttribute("LISTROLE", listRole);
                        request.setAttribute("CREATEERROR", error);
                        request.setAttribute("ROLE", roleLogin);
                    }
                    
                }
            
           
                
          
                
            

        } catch (Exception e) {
            
            log("CreateNewCustomerServlet_SQL :"+ e.getMessage());
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
