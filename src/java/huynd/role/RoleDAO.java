/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynd.role;

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
public class RoleDAO implements Serializable {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    private List<RoleDTO> listRole;

    public List<RoleDTO> getListRole() {
        return listRole;
    }
    
    public void getRole(){
        try {
            conn = MyConnection.getMyConnection();
            if(conn != null){
                String sql = "Select RoleID from tbl_Role";
                preStm = conn.prepareStatement(sql);
                rs = preStm.executeQuery();
                String role = "";
                String roleName = "";
                int count = 0;
                RoleDAO daoR = new RoleDAO();
                while(rs.next()){
                 
                   role = rs.getString("RoleID");
                   
                   if(listRole == null){
                       listRole = new ArrayList();
                   }
                   roleName = daoR.getRoleName(role);
                   count = daoR.getRoleCount(role);
                   String countString = String.valueOf(count).toString();
                   RoleDTO dto = new RoleDTO(role, roleName, countString);
                   listRole.add(dto);
                   
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
    }
        public int countAll(){
        int count = 0;
        try {
            conn = MyConnection.getMyConnection();
            if(conn != null){
                String sql = "Select count(username) AS count from tbl_Account ";
                preStm = conn.prepareStatement(sql); 
                rs = preStm.executeQuery();
                if(rs.next()){
                    count = rs.getInt("count");
      
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return count;
    }
    public void getRoleWithoutAdmin(){
        try {
            conn = MyConnection.getMyConnection();
            if(conn != null){
                String sql = "Select RoleID from tbl_Role";
                preStm = conn.prepareStatement(sql);
                rs = preStm.executeQuery();
                String role = "";
                String roleName = "";
                int count = 0;
                RoleDAO daoR = new RoleDAO();
                while(rs.next()){
                 
                   role = rs.getString("RoleID");
                   
                   if(listRole == null){
                       listRole = new ArrayList();
                   }
                   if(!role.equals("Ad")){
                      roleName = daoR.getRoleName(role);
                    count = daoR.getRoleCount(role);
                    String countString = String.valueOf(count).toString();
                    RoleDTO dto = new RoleDTO(role, roleName, countString);
                    listRole.add(dto); 
                   }
                   
                   
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
    }
    public String getRoleName(String roleID){
        String roleName = "";     
        try {
            conn = MyConnection.getMyConnection();
            if(conn != null){
                String sql = "Select RoleName from tbl_Role where RoleID = ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, roleID);
                rs = preStm.executeQuery();
                         
                if(rs.next()){
                 
                   roleName = rs.getString("RoleName");                 
                   
                   
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return roleName;
    }
    public String getRoleID(String roleName){
        String roleID = "";     
        try {
            conn = MyConnection.getMyConnection();
            if(conn != null){
                String sql = "Select RoleID from tbl_Role where RoleName = ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, roleName);
                rs = preStm.executeQuery();
                         
                if(rs.next()){
                 
                   roleID = rs.getString("RoleID");                 
                   
                   
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return roleID;
    }
    public int getRoleCount(String roleID){
        int count = 0;
        try {
            conn = MyConnection.getMyConnection();
            if(conn != null){
                String sql = "Select count(RoleID) AS count from tbl_Account where RoleID = ?";
                preStm = conn.prepareStatement(sql);
   
                preStm.setString(1, roleID);
                rs = preStm.executeQuery();
                if(rs.next()){
                    count = rs.getInt("count");
      
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return count;
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
