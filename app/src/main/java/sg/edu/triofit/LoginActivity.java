package sg.edu.triofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DBHandler dbHandler = new DBHandler(this, null ,null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView register = findViewById(R.id.register);
        register.setTextColor(Color.BLUE);

        //if user click on register it will bring them to the register page
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button login = findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etUsername = findViewById(R.id.loginName);
                EditText etPassword = findViewById(R.id.loginPassword);
                if (isValidCredentials(etUsername.getText().toString(), etPassword.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Valid Credentials", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //this function is to check whether username and password both matches the database information
    public boolean isValidCredentials(String username, String password) {
        UserData dbData = dbHandler.findUser(username, "");
        if (dbData == null) {
            Toast.makeText(this, "User does not exist. Please Sign Up", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (dbData.getUsername().equals(username) && dbData.getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        }
    }

}