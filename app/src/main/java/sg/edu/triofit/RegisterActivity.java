package sg.edu.triofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    DBHandler dbHandler = new DBHandler(this,null,null,1);
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView login = findViewById(R.id.login);
        login.setTextColor(Color.BLUE);
        EditText etUsername = findViewById(R.id.username);
        EditText etEmail = findViewById(R.id.email);
        EditText etHeight = findViewById(R.id.Height);
        EditText etWeight = findViewById(R.id.Weight);
        EditText etDOB = findViewById(R.id.age);
        EditText etPassword = findViewById(R.id.password);
        EditText etCfmPassword = findViewById(R.id.confirmpassword);

        Button registerBtn = findViewById(R.id.reigsterBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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