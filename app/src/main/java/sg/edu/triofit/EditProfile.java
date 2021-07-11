package sg.edu.triofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad21-triofit-team02-ab582-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference mDatabase = firebaseDatabase.getReference();
    UserData user = LoginActivity.userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        /*---------------Variables---------------*/
        EditText editNumber, editEmail, editHeight, editWeight, editAge;
        Button updateBtn;

        /*---------------Variables---------------*/

        //Retrieve
        editNumber = findViewById(R.id.editNumber);
        editEmail = findViewById(R.id.editEmail);
        editHeight = findViewById(R.id.editHeight);
        editWeight = findViewById(R.id.editWeight);
        editAge = findViewById(R.id.editAge);
        updateBtn = findViewById(R.id.updateBtn);

        //setDefaultText
        editNumber.setText(user.getNumber());
        editEmail.setText(user.getEmail());
        editHeight.setText(String.valueOf(user.getHeight()));
        editWeight.setText(String.valueOf(user.getWeight()));
        editAge.setText(user.getAge());

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,number,email,age;
                number = editNumber.getText().toString();
                email = editEmail.getText().toString();
                age = editAge.getText().toString();
                float height = Float.parseFloat(editHeight.getText().toString());
                float weight = Float.parseFloat(editWeight.getText().toString());
                float bmi = weight / (height*height);
                saveData(email,age,height,weight,bmi,number);

                startActivity(new Intent(EditProfile.this, Profile.class));
            }
        });


    }

    private void saveData(String email,String age,Float height,Float weight,Float bmi,String number)
    {
        // create user object and store data in
//        UserData userInfo = new UserData(username,password,email,age,height,weight,bmi,number, username);
        //Save user object created to 'User' table in firebase
        mDatabase.child("User").child(user.getUsername()).child("number").setValue(number);
        mDatabase.child("User").child(user.getUsername()).child("email").setValue(email);
        mDatabase.child("User").child(user.getUsername()).child("age").setValue(age);
        mDatabase.child("User").child(user.getUsername()).child("height").setValue(height);
        mDatabase.child("User").child(user.getUsername()).child("weight").setValue(weight);
        mDatabase.child("User").child(user.getUsername()).child("bmi").setValue(bmi);
        user.setNumber(number);
        user.setEmail(email);
        user.setAge(age);
        user.setHeight(height);
        user.setWeight(weight);
        user.setBmi(bmi);
    }
}