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
public class CreateAccountError {
    private String usernameLength;
    private String usernameIsExist;
    private String emailLength;
    private String isCorrectMail;
    private String firstnameLength;
    private String lastnameLength;
    private String websiteLength;
    private String isCorrectWeb;
    private String passwordLength;
    private String repasswordLength;
    private String rePassNotMatch;
    private String InsertFail;

    public String getUsernameLength() {
        return usernameLength;
    }

    public void setUsernameLength(String usernameLength) {

        this.usernameLength = usernameLength;
    }

    public String getUsernameIsExist() {
        return usernameIsExist;
    }

    public void setUsernameIsExist(String usernameIsExist) {

        this.usernameIsExist = usernameIsExist;
    }

    public String getEmailLength() {
        return emailLength;
    }

    public void setEmailLength(String emailLength) {
 
        this.emailLength = emailLength;
    }

    public String getIsCorrectMail() {
        return isCorrectMail;
    }

    public void setIsCorrectMail(String isCorrectMail) {
  
        this.isCorrectMail = isCorrectMail;
    }

    public String getFirstnameLength() {
        return firstnameLength;
    }

    public void setFirstnameLength(String firstnameLength) {
   
        this.firstnameLength = firstnameLength;
    }

    public String getLastnameLength() {
        return lastnameLength;
    }

    public void setLastnameLength(String lastnameLength) {
      
        this.lastnameLength = lastnameLength;
    }

    public String getWebsiteLength() {
        return websiteLength;
    }

    public void setWebsiteLength(String websiteLength) {

        this.websiteLength = websiteLength;
    }

    public String getIsCorrectWeb() {
        return isCorrectWeb;
    }

    public void setIsCorrectWeb(String isCorrectWeb) {
   
        this.isCorrectWeb = isCorrectWeb;
    }

    public String getPasswordLength() {
        return passwordLength;
    }

    public void setPasswordLength(String passwordLength) {
    
        this.passwordLength = passwordLength;
    }

    public String getRepasswordLength() {
        return repasswordLength;
    }

    public void setRepasswordLength(String repasswordLength) {
    
        this.repasswordLength = repasswordLength;
    }

    public String getRePassNotMatch() {
        return rePassNotMatch;
    }

    public void setRePassNotMatch(String rePassNotMatch) {
 
        this.rePassNotMatch = rePassNotMatch;
    }

    

    public String getInsertFail() {
        return InsertFail;
    }

    public void setInsertFail(String InsertFail) {
 
        this.InsertFail = InsertFail;
    }
}
