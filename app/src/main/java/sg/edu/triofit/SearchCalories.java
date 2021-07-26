package sg.edu.triofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SearchCalories extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad21-triofit-team02-ab582-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference mDatabase = firebaseDatabase.getReference().child("User");
    UserData user = LoginActivity.userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_calories);

        /*---------------Variables---------------*/
        Button searchBtn;
        EditText searchDate;
        TextView breakfast, lunch, dinner, other, total;

        /*---------------Variables---------------*/

        //Retrieve from layout
        searchBtn = findViewById(R.id.searchBtn);
        searchDate = findViewById(R.id.searchDate);
        breakfast = findViewById(R.id.searchBreakfast);
        lunch = findViewById(R.id.searchLunch);
        dinner = findViewById(R.id.searchDinner);
        other = findViewById(R.id.searchOther);
        total = findViewById(R.id.searchTotal);



        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = searchDate.getText().toString();
                if (date.isEmpty())
                {
                    Toast.makeText(SearchCalories.this, "Please enter a date!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Query checkDate = mDatabase.child(user.getUsername()).child("calories").orderByChild("date").equalTo(date);
                    checkDate.addListenerForSingleValueEvent(new ValueEventListener() {
                        String searchBreakfast, searchLunch, searchDinner, searchOther;
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot)
                        {
                            Float searchTotal;
                            if (snapshot.exists()) {
                                searchBreakfast = snapshot.child(date).child("breakfast").getValue(String.class);
                                searchLunch = snapshot.child(date).child("lunch").getValue(String.class);
                                searchDinner = snapshot.child(date).child("dinner").getValue(String.class);
                                searchOther = snapshot.child(date).child("other").getValue(String.class);
                                searchTotal = Float.parseFloat(searchBreakfast) + Float.parseFloat(searchLunch) + Float.parseFloat(searchDinner) + Float.parseFloat(searchOther);
                                breakfast.setText("Breakfast Calories: " + searchBreakfast);
                                lunch.setText("Lunch Calories: " + searchLunch);
                                dinner.setText("Dinner Calories: " + searchDinner);
                                other.setText("Other/Snacks Calories: " + searchOther);
                                total.setText("Total Calories: " + searchTotal.toString());
                            }
                            else
                            {
                                Toast.makeText(SearchCalories.this, "Date does not exist!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });


    }
}