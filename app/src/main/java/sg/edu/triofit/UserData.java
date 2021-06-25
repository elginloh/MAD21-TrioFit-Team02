package sg.edu.triofit;

import java.util.Date;

public class UserData {
    private String Username;
    private String Password;
    private String Email;
    private String DOB;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public UserData() {
    }

    public UserData(String username, String password, String email, String DOB) {
        Username = username;
        Password = password;
        Email = email;
        this.DOB = DOB;
    }


}
