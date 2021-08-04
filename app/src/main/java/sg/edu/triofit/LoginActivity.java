package sg.edu.triofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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

    // UserData class to store user data after login
    static UserData userInfo;

    EditText etUsername,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                //Get user input
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                //call method
                isUser(username,password);


            }
        });

    }
    //Check whether user is in firebase.
    private void isUser(String username,String password) {

        //Get data from firebase realtime database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad21-triofit-team02-ab582-default-rtdb.asia-southeast1.firebasedatabase.app/");
        //Reference to user table
        DatabaseReference mDatabase = firebaseDatabase.getReference().child("User");

        //Checks user entered username to database
        Query checkUser = mDatabase.orderByChild("username").equalTo(username);

        //checking
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // if user entered data exist
                String passwordFromDB, nameFromDB, ageFromDB, numberFromDB, emailFromDB, imageUrl;
                Float heightFromDB, weightFromDB, BMIFromDB;

                //if user exist
                if (snapshot.exists()) {
                    //set error to null
                    etPassword.setError(null);

                    //retrieve everything from firebase realtime database
                    //created variable to store data retrieved from firebase
                    //based on username they will create a data called username to store to data

                    passwordFromDB = snapshot.child(username).child("password").getValue(String.class);
                    nameFromDB =  snapshot.child(username).child("username").getValue(String.class);
                    ageFromDB =  snapshot.child(username).child("age").getValue(String.class);
                    numberFromDB = snapshot.child(username).child("number").getValue(String.class);
                    emailFromDB =  snapshot.child(username).child("email").getValue(String.class);
                    heightFromDB =  snapshot.child(username).child("height").getValue(Float.class);
                    weightFromDB =  snapshot.child(username).child("weight").getValue(Float.class);
                    BMIFromDB =  snapshot.child(username).child("bmi").getValue(Float.class);
                    imageUrl = snapshot.child(username).child("imageUrl").getValue(String.class);



                    //Then checks user entered password is the same as password from the database
                    if (password.equals(passwordFromDB)) {

                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//
//
                        //Store data using variable that i created to UserData class.
                        //After user login, can use data from this class.
                        userInfo = new UserData(nameFromDB, passwordFromDB, emailFromDB, ageFromDB, heightFromDB, weightFromDB, BMIFromDB, numberFromDB, imageUrl);
                        startActivity(intent);

                    }
                    // if user entered password is not the same
                    else {
                        etPassword.setError("Wrong Password!");
                        etPassword.requestFocus();
                    }
                    // if user does not exist in database
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
    public void onBackPressed() {
        //do nothing
    }

}