/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynd.role;

/**
 *
 * @author student
 */
public class RoleDTO {
    private String roleID;  
    private String roleName;
    private String roleCount;

    public RoleDTO(String roleID, String roleName, String roleCount) {
        this.roleID = roleID;
        this.roleName = roleName;
        this.roleCount = roleCount;
    }

    public RoleDTO(String roleName, String roleCount) {
        this.roleName = roleName;
        this.roleCount = roleCount;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCount() {
        return roleCount;
    }

    public void setRoleCount(String roleCount) {
        this.roleCount = roleCount;
    }

}
