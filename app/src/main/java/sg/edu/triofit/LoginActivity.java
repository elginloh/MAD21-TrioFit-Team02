package sg.edu.triofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    DBHandler dbHandler = new DBHandler(this, null ,null, 1);

//    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser user;

    EditText etUsername,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();



        etUsername = findViewById(R.id.loginName);
        etPassword = findViewById(R.id.loginPassword);

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
//                EditText etUsername = findViewById(R.id.loginName);
//                EditText etPassword = findViewById(R.id.loginPassword);
//                if (isValidCredentials(etUsername.getText().toString(), etPassword.getText().toString())) {
//                    Toast.makeText(LoginActivity.this, "Valid Credentials", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
//                }
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                isUser(username,password);

            }
        });

    }
    private void isUser(String username,String password) {

//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad21-triofit-team02-ab582-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference mDatabase = firebaseDatabase.getReference().child("User");

        Query checkUser = mDatabase.orderByChild("username").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    etPassword.setError(null);

                    String passwordFromDB = snapshot.child(username).child("password").getValue(String.class);

                    String nameFromDB =  snapshot.child(username).child("name").getValue(String.class);
                    String ageFromDB =  snapshot.child(username).child("age").getValue(String.class);
                    String emailFromDB =  snapshot.child(username).child("email").getValue(String.class);
                    Float heightFromDB =  snapshot.child(username).child("height").getValue(Float.class);
                    Float weightFromDB =  snapshot.child(username).child("weight").getValue(Float.class);
                    Float BMIFromDB =  snapshot.child(username).child("bmi").getValue(Float.class);

                    if (password.equals(passwordFromDB)) {

                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                    } else {
                        etPassword.setError("Wrong Password!");
                        etPassword.requestFocus();
                    }
                } else {
                    etUsername.setError("No such User exist.");
                    etUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
//


    //this function is to check whether username and password both matches the database information
//    public boolean isValidCredentials(String username, String password) {
//        UserData dbData = dbHandler.findUser(username, "");
//        if (dbData == null) {
//            Toast.makeText(this, "User does not exist. Please Sign Up", Toast.LENGTH_SHORT).show();
//            return false;
//        } else {
//            if (dbData.getUsername().equals(username) && dbData.getPassword().equals(password)) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//    }

}