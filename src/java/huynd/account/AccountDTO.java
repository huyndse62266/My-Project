/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynd.account;

/**
 *
 * @author student
 */
public class AccountDTO {
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String website;
    private boolean noti;
    private String roleID;
    private String roleName;
    
    public AccountDTO(String username, String email, String firstname, String lastname, String password, String roleID, String roleName) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public AccountDTO(String username, String email, String firstname, String lastname, String password, String roleID) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.roleID = roleID;
    }

    public AccountDTO(String username, String email, String firstname, String lastname, String password, String website, boolean noti, String roleID) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.website = website;
        this.noti = noti;
        this.roleID = roleID;
    }

    public AccountDTO(String username, String roleID) {
        this.username = username;
        this.roleID = roleID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isNoti() {
        return noti;
    }

    public void setNoti(boolean noti) {
        this.noti = noti;
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
    

    
}
