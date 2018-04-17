/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynd.account;

import huynd.role.RoleDAO;
import huynd.utils.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author student
 */
public class AccountDAO implements Serializable{
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    private List<AccountDTO> listAccount;
    private List<String> listUsername;
    
    public List<AccountDTO> getListAccount() {
        return listAccount;
    }

    public List<String> getListUsername() {
        return listUsername;
    }

    RoleDAO dao = new RoleDAO();
     
    
    
    
   
    
    public AccountDTO login(String username, String password){
        AccountDTO dto = null;
        try {
            conn = MyConnection.getMyConnection();
            if(conn != null){
                String sql = "Select Username, RoleID from tbl_Account where Username = ? and Password = ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, username);
                preStm.setString(2, password);
                rs =preStm.executeQuery();
                if(rs.next()){
                    username = rs.getString("Username");
                    String role = rs.getString("RoleID");
                    dto = new AccountDTO(username, role);
                }                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return dto;
    }
    
    
    public void printAllListAccount(){
        try {
            conn = MyConnection.getMyConnection();
            if(conn != null){
                String sql = "Select Username,Email,FirstName,Lastname, RoleID from tbl_Account";
                preStm = conn.prepareStatement(sql);
                rs = preStm.executeQuery();
                String username = "";
                String email = "";
                String firstname = "";
                String lastname = "";
                String roleID = "";
                String roleName = "";
       
                while(rs.next()){
                    username = rs.getString("Username");
                    email = rs.getString("Email");
                    firstname = rs.getString("FirstName");
                    lastname = rs.getString("Lastname");
                    roleID = rs.getString("RoleID");
                    roleName = dao.getRoleName(roleID);
                    AccountDTO dto = new AccountDTO(username, email, firstname, lastname, lastname, roleID, roleName) ;
                    if(listAccount == null){
                        listAccount = new ArrayList<AccountDTO>();
                    }
                    listAccount.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
    } 
    public AccountDTO getFullInfo(String username){
        AccountDTO dto = null;
        try {
            conn = MyConnection.getMyConnection();
            if(conn != null){
                String sql = "Select Username,Email,FirstName,Lastname, RoleID from tbl_Account where Username = ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, username);
                rs = preStm.executeQuery();

                if(rs.next()){
                    String email = rs.getString("Email");
                    String firstname = rs.getString("FirstName");
                    String lastname = rs.getString("Lastname");
                    String role = rs.getString("RoleID");
                    String roleName = dao.getRoleName(role);
                    dto = new AccountDTO(username, email, firstname, lastname, lastname, role,roleName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return dto;
    } 
    public void printAllListAccountbyRole(String roleID){
        try {
            conn = MyConnection.getMyConnection();
            if(conn != null){
      
                String sql = "Select Username,Email,FirstName,Lastname, RoleID from tbl_Account where RoleID = ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, roleID);
                rs = preStm.executeQuery();
                String username = "";
                String email = "";
                String firstname = "";
                String lastname = "";
                String roleName = "";
                while(rs.next()){
                    username = rs.getString("Username");
                    email = rs.getString("Email");
                    firstname = rs.getString("FirstName");
                    lastname = rs.getString("Lastname");
                    roleID = rs.getString("RoleID");
                    roleName = dao.getRoleName(roleID);

                    
                    AccountDTO dto = new AccountDTO(username, email, firstname, lastname, lastname, roleID, roleName) ;
                    if(listAccount == null){
                        listAccount = new ArrayList<AccountDTO>();
                    }
                    listAccount.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
    }
    
    public void searchLikeName(String searchValue, String roleSelected){
        try {
            conn = MyConnection.getMyConnection();
            if(conn != null){
                if(roleSelected.equals("All")){
                    String sql = "Select Username,Email,FirstName,Lastname, RoleID from tbl_Account where FirstName like ? OR LastName like  ?";
                     preStm = conn.prepareStatement(sql);
                     preStm.setString(1, "%" +searchValue + "%");
                     preStm.setString(2, "%" +searchValue + "%");
                }else{
                   String sql = "Select Username,Email,FirstName,Lastname, RoleID from tbl_Account where (FirstName like ?  OR LastName like  ? )and RoleID = ?";
                   preStm = conn.prepareStatement(sql);
                   preStm.setString(1, "%" +searchValue + "%");
                   preStm.setString(2, "%" +searchValue + "%");
                   preStm.setString(3, roleSelected);
                }
                
               
                
                rs = preStm.executeQuery();
                String username = "";
                String email = "";
                String firstname = "";
                String lastname = "";
                String roleID = "";
                String roleName = "";

                while(rs.next()){
                    username = rs.getString("Username");
                    email = rs.getString("Email");
                    firstname = rs.getString("FirstName");
                    lastname = rs.getString("Lastname");
                    roleID = rs.getString("RoleID");
                    roleName = dao.getRoleName(roleID);
                    AccountDTO dto = new AccountDTO(username, email, firstname, lastname, lastname, roleID, roleName) ;
                    if(listAccount == null){
                        listAccount = new ArrayList<AccountDTO>();
                    }
                    listAccount.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
    } 
    
    public boolean updateRole(String username,String newRole){
        try {
            conn = MyConnection.getMyConnection();
            if(conn != null){
                String sql = "Update tbl_Account set RoleID = ? where Username = ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, newRole);
                preStm.setString(2, username);
                if(preStm.executeUpdate() > 0){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return false;
    }
    public boolean insertAccount(AccountDTO dto){
        try {
            conn = MyConnection.getMyConnection();
            if(conn != null){
                String sql = "Insert into tbl_Account(Username,Email,FirstName,Lastname,Password,Website,SendNotification,RoleID) values(?,?,?,?,?,?,?,?)";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, dto.getUsername());
                preStm.setString(2, dto.getEmail());
                preStm.setString(3, dto.getFirstname());
                preStm.setString(4, dto.getLastname());
                preStm.setString(5, dto.getPassword());
                preStm.setString(6, dto.getWebsite());
                preStm.setBoolean(7, dto.isNoti());
                preStm.setString(8, dto.getRoleID());
                if(preStm.executeUpdate() > 0){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return false;
    }
   
    public void getExistAccount(){
        try {
            conn = MyConnection.getMyConnection();
            if(conn != null){
                String sql = "Select Username from tbl_Account";
                preStm = conn.prepareStatement(sql);
                rs = preStm.executeQuery();
                String username= "";
                while(rs.next()){
                    username = rs.getString("Username");
                    if(listUsername == null){
                        listUsername = new ArrayList<String>();
                    }
                    listUsername.add(username);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
    }
    
    public void closeConnection(){
        try {
            if(rs != null){
                rs.close();
            }       
            if(preStm != null){
                preStm.close();
            }       
            if(conn != null){
                conn.close();
            }       
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
