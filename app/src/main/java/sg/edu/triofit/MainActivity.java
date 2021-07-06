package sg.edu.triofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*---------------Variables---------------*/
        TextView welcomeusername, bmistatus, exerInfo, bmiInfo;
        double bmi = 25;
        /*---------------Variables---------------*/

        //Retrieve from layout
        welcomeusername = findViewById(R.id.wlcuser);
        bmistatus = findViewById(R.id.bmistatus);
        exerInfo = findViewById(R.id.exerInfo);
        bmiInfo = findViewById(R.id.bmiInfo);

        //set welcome text
        welcomeusername.setText("Welcome back, " + "elgin");
        bmistatus.setText(bmiCheck(bmi));
        exerInfo.setText("Ever want to do exercises but do not know how to start? Fear not, we will be providing information and tutorial videos for you to follow through to exercise.");
        bmiInfo.setText("Here are information that would show you the current bmi status and information and some information in how you can see on helping in keeping your bmi in ideal range.");
    }

    public String bmiCheck(Double bmi){
        String message = "";
        if(bmi < 18.5)
        {
            message = "BMI Status: You need to eat more stuff!";
        }
        else if(bmi < 24.9)
        {
            message = "BMI Status: Your BMI is looking good!";
        }
        else if(bmi < 29.9)
        {
            message = "BMI Status: You need to eat less";
        }
        else
        {
            message = "BMI Status: You need to exercise and eat healthy";
        }
        return message;
    }
}