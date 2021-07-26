package sg.edu.triofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
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
        TextView welcomeusername, bmistatus, exerInfo, bmiInfo, idkyet;
        Button bmiBtn, exerBtn, caloriesBtn;
        ImageView profileBtn, pfp;
        UserData user = LoginActivity.userInfo;
        /*---------------Variables---------------*/

        //Retrieve from layout
        welcomeusername = findViewById(R.id.wlcuser);
        bmistatus = findViewById(R.id.bmistatus);
        exerInfo = findViewById(R.id.exerInfo);
        idkyet = findViewById(R.id.idkyet);
        bmiInfo = findViewById(R.id.bmiInfo);
        bmiBtn = findViewById(R.id.bmiBtn);
        exerBtn = findViewById(R.id.exerBtn);
        caloriesBtn = findViewById(R.id.caloriesBtn);
        profileBtn = findViewById(R.id.profileBtn);
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
        exerInfo.setText("Ever want to do exercises but do not know how to start? Fear not, we will be providing information and tutorial videos for you to follow through to exercise.");
        bmiInfo.setText("Here are information that would show you the current bmi status and information and some information in how you can see on helping in keeping your bmi in ideal range.");
        idkyet.setText("If have enough time this slot will be added for the usage of API");
        if (user.getPfp().equals("null"))
        {
            pfp.setImageResource(R.mipmap.ic_launcher_round);
        }
        else
        {
            Picasso.get().load(user.getPfp()).into(pfp);
        }

        //onClicks
        bmiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, bmiTrend.class);
                startActivity(intent);
            }
        });

        exerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CatalogueActivity.class);
                startActivity(intent);

            }
        });

        caloriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, caloriesTracking.class);
                startActivity(intent);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
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