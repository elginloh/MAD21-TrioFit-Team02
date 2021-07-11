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

    ProgressBar progressBar;
    FirebaseAuth fAuth;

    EditText etUsername,etEmail,etHeight,etWeight,etAge,etPassword,etCfmPassword,etNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();
                String number = etNumber.getText().toString();

                String age = etAge.getText().toString();


                String confirmPassword = etCfmPassword.getText().toString();

                // if password is same as confirm password
                if (password.equals(confirmPassword))
                {
                    // if edit text is empty | All must be filled in
                    if(TextUtils.isEmpty(etUsername.getText().toString()) || TextUtils.isEmpty(etEmail.getText().toString()) ||TextUtils.isEmpty(etAge.getText().toString())
                            || TextUtils.isEmpty(etHeight.getText().toString()) || TextUtils.isEmpty(etWeight.getText().toString()) || TextUtils.isEmpty(etNumber.getText().toString()))
                    {
                        Toast.makeText(RegisterActivity.this,"Please fill in all boxes", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        // if email is in correct format
                        if (email.trim().matches(emailPattern)) //if email is in correct format
                        {
                            if(etPassword.getText().toString().equals(etCfmPassword.getText().toString())) // if password matches cfm password
                            {
                                float height = Float.parseFloat(etHeight.getText().toString());
                                float weight = Float.parseFloat(etWeight.getText().toString());
                                float bmi = weight / (height*height);

                                //call method
                                saveData(username,password,email,age,height,weight,bmi,number);

                                Toast.makeText(RegisterActivity.this,"Register!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                }

//                if(etPassword.getText().toString().equals((etCfmPassword.getText().toString())))
//                {
//                UserData userInfo = dbHandler.findUser(etUsername.getText().toString(), etEmail.getText().toString()); // retrieve user information/null
//                if(TextUtils.isEmpty(etUsername.getText()) || TextUtils.isEmpty(etEmail.getText()) || TextUtils.isEmpty(etDOB.getText()) || TextUtils.isEmpty(etPassword.getText()) || TextUtils.isEmpty(etCfmPassword.getText())){
//                    Toast.makeText(RegisterActivity.this, "Please fill in all boxes", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    if (userInfo == null) // if username and email doesnt exist
//                    {
//                        if (etEmail.getText().toString().trim().matches(emailPattern))  // if email is in correct format
//                        {
//                            if(etPassword.getText().toString().equals(etCfmPassword.getText().toString())) // if password matches confirm password
//                            {
//                                String dbUsername = etUsername.getText().toString();
//                                String dbEmail = etEmail.getText().toString();
//                                String dbDOB = etDOB.getText().toString();
//                                String dbPassword = etPassword.getText().toString();
//                                UserData dbUserData = new UserData();
//                                dbUserData.setUsername(dbUsername);
//                                dbUserData.setEmail(dbEmail);
//                                dbUserData.setDOB(dbDOB);
//                                dbUserData.setPassword(dbPassword);
//                                dbHandler.addUser(dbUserData);
//                                Toast.makeText(RegisterActivity.this, "New User Created", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                                startActivity(intent);
//                            }
//                            else
//                            {
//                                Toast.makeText(RegisterActivity.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        else
//                        {
//                            Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                    else if (userInfo.getEmail().equals(etEmail.getText().toString()))
//                    {
//                        Toast.makeText(RegisterActivity.this, "Email is taken", Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        Toast.makeText(RegisterActivity.this, "Username is taken", Toast.LENGTH_SHORT).show();
//                    }
//                }
            }

            private void saveData(String username,String password,String email,String age,Float height,Float weight,Float bmi,String number)
            {
                // create user object and store data in
                UserData user = new UserData(username,password,email,age,height,weight,bmi,number);
                //Save user object created to 'User' table in firebase
                mDatabase.child("User").child(username).setValue(user);
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