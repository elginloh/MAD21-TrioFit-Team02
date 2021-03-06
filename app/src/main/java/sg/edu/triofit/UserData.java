package sg.edu.triofit;

import android.net.Uri;

import java.util.Date;

public class UserData {
    public String Username;
    public String Password;
    public String Email;
    public String Age;
    public String Number;
    public float height;
    public float weight;
    public float bmi;
    public String Pfp;

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

//    public String getDefaultUsername() { return DefaultUsername; }
//
//    public void setDefaultUsername(String defaultUsername) { DefaultUsername = defaultUsername; }

    public String getNumber() { return Number; }

    public void setNumber(String number) { Number = number; }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public String getPfp() {
        return Pfp;
    }

    public void setPfp(String pfp) {
        this.Pfp = pfp;
    }

    public UserData() {
    }

    public UserData(String username, String password, String email, String age, float height, float weight,float bmi, String number, String pfp) {
        Username = username;
        Password = password;
        Email = email;
        Age = age;
        Number = number;
        Pfp = pfp;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
    }
}
