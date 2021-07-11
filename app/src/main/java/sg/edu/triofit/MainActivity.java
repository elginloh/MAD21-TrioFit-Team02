package sg.edu.triofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*---------------Variables---------------*/
        TextView welcomeusername, bmistatus, exerInfo, bmiInfo, idkyet;
        Button bmiBtn, exerBtn, idkBtn;
        ImageView profileBtn;
        UserData user = LoginActivity.userInfo;
        /*---------------Variables---------------*/

        //Retrieve from layout
        welcomeusername = findViewById(R.id.wlcuser);
        bmistatus = findViewById(R.id.bmistatus);
        exerInfo = findViewById(R.id.exerInfo);
        idkyet = findViewById(R.id.idkyet);
        bmiInfo = findViewById(R.id.bmiInfo);
        bmiBtn = findViewById(R.id.bmiBtn);
        exerBtn = findViewById(R.id.exerBtn);
        idkBtn = findViewById(R.id.idkBtn);
        profileBtn = findViewById(R.id.profileBtn);

        //Retrieve from intent
//        Intent intent = getIntent();
//        String name = intent.getStringExtra("name");
//        Float bmi = intent.getFloatExtra("bmi",0);
//        String pass = intent.getStringExtra("password");



        //set welcome text

        welcomeusername.setText("Welcome back, " + user.getUsername());
        bmistatus.setText(bmiCheck(user.getBmi()));
        exerInfo.setText("Ever want to do exercises but do not know how to start? Fear not, we will be providing information and tutorial videos for you to follow through to exercise.");
        bmiInfo.setText("Here are information that would show you the current bmi status and information and some information in how you can see on helping in keeping your bmi in ideal range.");
        idkyet.setText("If have enough time this slot will be added for the usage of API");

        //onClicks
        bmiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, bmiTrend.class);
                startActivity(intent);
            }
        });

        exerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CatalogueActivity.class);
                startActivity(intent);

            }
        });

        idkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Still waiting", Toast.LENGTH_LONG).show();
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            }
        });

    }

    //function for changing of message based on their calculated bmi
    public String bmiCheck(Float bmi){
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