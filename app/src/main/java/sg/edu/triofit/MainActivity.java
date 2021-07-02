package sg.edu.triofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView welcomeusername, bmistatus;
        welcomeusername = findViewById(R.id.wlcuser);
        bmistatus = findViewById(R.id.bmistatus);
        welcomeusername.setText("Welcome back, " + "elgin");
    }

    public String bmiCheck(Double bmi){
        String message = "";
        if(bmi < 18.5)
        {
            message = "You need to eat more stuff!";
        }
        else if(bmi < 24.9)
        {
            message = "Your BMI is looking good!";
        }
        else if(bmi < 29.9)
        {
            message = "You need to eat less";
        }
        else
        {
            message = "You need to exercise and eat healthy";
        }
        return message;
    }
}