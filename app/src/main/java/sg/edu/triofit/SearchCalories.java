package sg.edu.triofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SearchCalories extends AppCompatActivity {
    //Navi
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    TextView breakfast, lunch, dinner, other, total;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad21-triofit-team02-ab582-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference mDatabase = firebaseDatabase.getReference().child("User");
    UserData user = LoginActivity.userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_search_calories);

        /*---------------Variables---------------*/
        Button searchBtn;
        EditText searchDate;
        TextView back;

        /*---------------Variables---------------*/

        //Retrieve from layout
        searchBtn = findViewById(R.id.searchBtn);
        searchDate = findViewById(R.id.searchDate);
        breakfast = findViewById(R.id.searchBreakfast);
        lunch = findViewById(R.id.searchLunch);
        dinner = findViewById(R.id.searchDinner);
        other = findViewById(R.id.searchOther);
        total = findViewById(R.id.searchTotal);
        back = findViewById(R.id.profileBack4);

        //Navi
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Navi
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()== R.id.nav_home)
                {
                    Toast.makeText(SearchCalories.this,"Home",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_profile)
                {
                    Toast.makeText(SearchCalories.this,"Profile",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Profile.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_settings)
                {
                    Toast.makeText(SearchCalories.this,"Settings",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),EditProfile.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_caloriestracker)
                {
                    Toast.makeText(SearchCalories.this,"Calories Tracker",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),caloriesTracking.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_bmi)
                {
                    Toast.makeText(SearchCalories.this,"BMI Information",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),bmiInformation.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_video)
                {
                    Toast.makeText(SearchCalories.this,"Workout Videos",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),CatalogueActivity.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_logout)
                {
                    Toast.makeText(SearchCalories.this,"Logged out",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);

                }

                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        //set onclicks
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
                                textViewVisible();
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchCalories.this, caloriesTracking.class);
                startActivity(intent);
            }
        });

    }
    //Nav Bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void textViewVisible()
    {
        breakfast.setVisibility(View.VISIBLE);
        lunch.setVisibility(View.VISIBLE);
        dinner.setVisibility(View.VISIBLE);
        other.setVisibility(View.VISIBLE);
        total.setVisibility(View.VISIBLE);
    }
}