package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto;

public class LoginDto {
    private String emailAddress;
    private String password;
    private String employeeCode;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }
}
