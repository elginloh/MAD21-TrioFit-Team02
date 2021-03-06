package sg.edu.triofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ChangePassword extends AppCompatActivity {

    //Reference to firebase based on link
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad21-triofit-team02-ab582-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference mDatabase = firebaseDatabase.getReference();
    UserData user = LoginActivity.userInfo;

    EditText oldPW,newPW,cfmNewPW;
    Button changePW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);


        //variable
        TextView back;

        //retrieve from layout
        back = findViewById(R.id.profileBack5);
        oldPW = findViewById(R.id.et_OldPassword);
        newPW = findViewById(R.id.et_NewPassword);
        cfmNewPW = findViewById(R.id.et_CfmNewPassword);

        changePW = findViewById(R.id.btn_Change);

//        oldPW.setText(user.getPassword());

        //Get old password from user data class
        String oldPWFromDB = user.getPassword();


        //set onclick
        changePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if password from user data class is the same as user input
                if(oldPW.getText().toString().equals(oldPWFromDB))
                {
                    // if new password matches cfm new password
                    if(newPW.getText().toString().equals(cfmNewPW.getText().toString()))
                    {
                        //call saveData method
                        saveData(newPW.getText().toString());

                        Toast.makeText(getApplicationContext(),"Successfully changed password",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChangePassword.this,Profile.class);
                        startActivity(intent);
                    }
                    else
                    {
                        newPW.setError("Password does not match");
                        cfmNewPW.setError("Password does not match");
                    }

                }
                else
                {
                    oldPW.setError("Old password is incorrect");
                    oldPW.requestFocus();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePassword.this, EditProfile.class);
                startActivity(intent);
            }
        });


    }

    private void saveData(String newPassword)
    {
        //Go back to firebase to find user table, username and then set password to new password
        mDatabase.child("User").child(user.getUsername()).child("password").setValue(newPassword);
        
        //Change the password in user data class
        user.setPassword(newPassword);
    }
}