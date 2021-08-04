package sg.edu.triofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    DBHandler dbHandler = new DBHandler(this,null,null,1);

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    //link to database
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad21-triofit-team02-ab582-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference mDatabase = firebaseDatabase.getReference();


    EditText etUsername,etEmail,etHeight,etWeight,etAge,etPassword,etCfmPassword,etNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //finding all the edit text
        TextView login = findViewById(R.id.login);
        login.setTextColor(Color.BLUE);
        etUsername = findViewById(R.id.username);
        etEmail = findViewById(R.id.email);
        etHeight = findViewById(R.id.et_Height);
        etWeight = findViewById(R.id.Weight);
        etAge = findViewById(R.id.age);
        etPassword = findViewById(R.id.password);
        etCfmPassword = findViewById(R.id.confirmpassword);
        etNumber = findViewById(R.id.number);

        Button registerBtn = findViewById(R.id.reigsterBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get user input
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();
                String number = etNumber.getText().toString();
                String age = etAge.getText().toString();
                String confirmPassword = etCfmPassword.getText().toString();

                //If Edit Text are empty
                if (TextUtils.isEmpty(etUsername.getText().toString()) || TextUtils.isEmpty(etEmail.getText().toString()) || TextUtils.isEmpty(etAge.getText().toString())
                        || TextUtils.isEmpty(etHeight.getText().toString()) || TextUtils.isEmpty(etWeight.getText().toString()) || TextUtils.isEmpty(etNumber.getText().toString())
                || TextUtils.isEmpty(etPassword.getText().toString()) ||  TextUtils.isEmpty(etCfmPassword.getText().toString())) {
                    //Show Toast
                    Toast.makeText(RegisterActivity.this, "Please fill in all boxes", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (email.trim().matches(emailPattern)) //if email is in correct format
                    {
                        if (etPassword.getText().toString().equals(etCfmPassword.getText().toString())) // if password matches cfm password
                        {
                            float height = Float.parseFloat(etHeight.getText().toString());
                            float weight = Float.parseFloat(etWeight.getText().toString());
                            float bmi = weight / (height * height);

                            //call method
                            saveData(username, password, email, age, height, weight, bmi, number);

                            Toast.makeText(RegisterActivity.this, "Register!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                }
            }





            private void saveData(String username,String password,String email,String age,Float height,Float weight,Float bmi,String number)
            {
                //Save user object created to 'User' table in firebase
                mDatabase.child("User").child(username).child("username").setValue(username);
                mDatabase.child("User").child(username).child("password").setValue(password);
                mDatabase.child("User").child(username).child("email").setValue(email);
                mDatabase.child("User").child(username).child("age").setValue(age);
                mDatabase.child("User").child(username).child("height").setValue(height);
                mDatabase.child("User").child(username).child("weight").setValue(weight);
                mDatabase.child("User").child(username).child("bmi").setValue(bmi);
                mDatabase.child("User").child(username).child("number").setValue(number);
                mDatabase.child("User").child(username).child("imageUrl").setValue("null");

            }


        });

        //if user click on login it will bring them to the login page
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }


}