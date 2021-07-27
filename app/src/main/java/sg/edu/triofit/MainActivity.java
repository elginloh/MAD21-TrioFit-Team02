package sg.edu.triofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.net.CookieHandler;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    //Navi
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);

        /*---------------Variables---------------*/
        TextView welcomeusername, bmistatus, exerInfo, bmiInfo;
        ImageView pfp;
        CardView calories, profile, exercise, information;
        UserData user = LoginActivity.userInfo;
        /*---------------Variables---------------*/

        //Retrieve from layout
        welcomeusername = findViewById(R.id.wlcuser);
        bmistatus = findViewById(R.id.bmistatus);
        exerInfo = findViewById(R.id.exerInfo);
        calories = findViewById(R.id.calories);
        bmiInfo = findViewById(R.id.bmiInfo);
        profile = findViewById(R.id.profile);
        exercise = findViewById(R.id.exercise);
        information = findViewById(R.id.information);
        pfp = findViewById(R.id.pfp);

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
                    Toast.makeText(MainActivity.this,"Home",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);

                }
                else if (item.getItemId() == R.id.nav_profile)
                {
                    Toast.makeText(MainActivity.this,"Profile",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Profile.class);
                    startActivity(intent);

                }
                else if (item.getItemId() == R.id.nav_settings)
                {
                    Toast.makeText(MainActivity.this,"Settings",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),EditProfile.class);
                    startActivity(intent);

                }
                else if (item.getItemId() == R.id.nav_caloriestracker)
                {
                    Toast.makeText(MainActivity.this,"Calories Tracker",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),caloriesTracking.class);
                    startActivity(intent);

                }
                else if (item.getItemId() == R.id.nav_bmi)
                {
                    Toast.makeText(MainActivity.this,"BMI Information",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),bmiInformation.class);
                    startActivity(intent);

                }
                else if (item.getItemId() == R.id.nav_video)
                {
                    Toast.makeText(MainActivity.this,"Workout Videos",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),CatalogueActivity.class);
                    startActivity(intent);

                }
                else if (item.getItemId() == R.id.nav_logout)
                {
                    Toast.makeText(MainActivity.this,"Logged out",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);

                }

                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        //set welcome text

        welcomeusername.setText("Welcome back, " + user.getUsername());
        bmistatus.setText(bmiCheck(user.getBmi()));
        if (user.getPfp().equals("null"))
        {
            pfp.setImageResource(R.mipmap.ic_launcher_round);
        }
        else
        {
            Picasso.get().load(user.getPfp()).into(pfp);
        }

        calories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, caloriesTracking.class);
                startActivity(intent);
            }
        });

        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CatalogueActivity.class);
                startActivity(intent);
            }
        });

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, bmiInformation.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            }
        });

    }

    //function for changing of message based on their calculated bmi
    public String bmiCheck(Float bmi){
        String message = "";
        if(bmi < 18.5)
        {
            message = "BMI Status: You need to eat more stuff!";
        }
        else if(bmi < 24.9)
        {
            message = "BMI Status: Your BMI is looking good!";
        }
        else if(bmi < 29.9)
        {
            message = "BMI Status: You need to eat less";
        }
        else
        {
            message = "BMI Status: You need to exercise and eat healthy";
        }
        return message;
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
}