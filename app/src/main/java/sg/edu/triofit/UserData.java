package sg.edu.triofit;

import java.util.Date;

public class UserData {
    private String Username;
    private String Password;
    private String Email;
    private String Age;
    private float height;
    private float weight;

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

    public UserData() {
    }

    public UserData(String username, String password, String email, String age, float height, float weight) {
        Username = username;
        Password = password;
        Email = email;
        Age = age;
        this.height = height;
        this.weight = weight;
    }
}
