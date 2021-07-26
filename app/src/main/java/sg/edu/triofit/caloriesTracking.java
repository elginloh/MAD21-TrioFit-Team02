package sg.edu.triofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class caloriesTracking extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad21-triofit-team02-ab582-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference mDatabase = firebaseDatabase.getReference().child("User");
    UserData user = LoginActivity.userInfo;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_tracking);

        /*---------------Variables---------------*/
        Button caloriesBtn, searchBtn;
        EditText breakfast, lunch, dinner, other;
        /*---------------Variables---------------*/

        //Retrieve from layout
        caloriesBtn = findViewById(R.id.submitdaily);
        breakfast = findViewById(R.id.breakfast);
        lunch = findViewById(R.id.lunch);
        dinner = findViewById(R.id.dinner);
        other = findViewById(R.id.other);
        searchBtn = findViewById(R.id.search);


        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = dateFormat.format(calendar.getTime());


        caloriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child(user.getUsername()).child("calories").child(date).child("breakfast").setValue(breakfast.getText().toString());
                mDatabase.child(user.getUsername()).child("calories").child(date).child("lunch").setValue(lunch.getText().toString());
                mDatabase.child(user.getUsername()).child("calories").child(date).child("dinner").setValue(dinner.getText().toString());
                mDatabase.child(user.getUsername()).child("calories").child(date).child("other").setValue(other.getText().toString());
                mDatabase.child(user.getUsername()).child("calories").child(date).child("date").setValue(date);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(caloriesTracking.this, SearchCalories.class);
                startActivity(intent);
            }
        });

        //get Calories Intake
        Query checkUser = mDatabase.orderByChild("username").equalTo(user.getUsername());
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            String fbBreakfast, fbLunch, fbDinner, fbOther;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {

                fbBreakfast = snapshot.child(user.getUsername()).child("calories").child(date).child("breakfast").getValue(String.class);
                fbLunch = snapshot.child(user.getUsername()).child("calories").child(date).child("lunch").getValue(String.class);
                fbDinner = snapshot.child(user.getUsername()).child("calories").child(date).child("dinner").getValue(String.class);
                fbOther = snapshot.child(user.getUsername()).child("calories").child(date).child("other").getValue(String.class);
                breakfast.setText(fbBreakfast);
                lunch.setText(fbLunch);
                dinner.setText(fbDinner);
                other.setText(fbOther);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}