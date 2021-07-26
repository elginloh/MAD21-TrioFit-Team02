package sg.edu.triofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class CatalogueActivity extends AppCompatActivity {

    //Navi
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_catalogue);

        //calling functions from database to retrieve data wanted
        DBHandler dbHandler = new DBHandler(this,null,null,1);
        List<Category> categoryList = dbHandler.getAll();
        List<Activity> imageList = dbHandler.getImages();
        List<Activity> videoList = dbHandler.getVideos();
        List<Activity> descList = dbHandler.getDescript();
        CategoryAdapter adapter = new CategoryAdapter(categoryList, imageList, videoList, descList,getApplicationContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        RecyclerView rvCategory = findViewById(R.id.recyclerView);
        rvCategory.setLayoutManager(layoutManager);
        rvCategory.setAdapter(adapter);


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
                    Toast.makeText(CatalogueActivity.this,"Home",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_profile)
                {
                    Toast.makeText(CatalogueActivity.this,"Profile",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Profile.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_settings)
                {
                    Toast.makeText(CatalogueActivity.this,"Settings",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),EditProfile.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_caloriestracker)
                {
                    Toast.makeText(CatalogueActivity.this,"Calories Tracker",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),caloriesTracking.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_bmi)
                {
                    Toast.makeText(CatalogueActivity.this,"BMI Information",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),bmiInformation.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_video)
                {
                    Toast.makeText(CatalogueActivity.this,"Workout Videos",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),CatalogueActivity.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_logout)
                {
                    Toast.makeText(CatalogueActivity.this,"Logged out",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);

                }

                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
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

}