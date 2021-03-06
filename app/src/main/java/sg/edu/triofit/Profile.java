package sg.edu.triofit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class Profile extends AppCompatActivity {

    UserData user = LoginActivity.userInfo;

    //Nav
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_edit_profile);



        /*---------------Variables---------------*/
        TextView accountName, accountNumber, accountEmail, accountAge, accountBMI, accountHeight, accountWeight, profileName, profileEmail, backBtn, changePic;
        ImageView changePfp, editAccount, profilePic;

        /*---------------Variables---------------*/

        //Retrieve from layout
        accountName = findViewById(R.id.accountName);
        accountNumber = findViewById(R.id.accountNumber);
        accountEmail = findViewById(R.id.accountEmail);
        accountAge = findViewById(R.id.accountAge);
        accountBMI = findViewById(R.id.accountBMI);
        accountHeight = findViewById(R.id.accountHeight);
        accountWeight = findViewById(R.id.accountWeight);
        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profilePic = findViewById(R.id.changePfp);
        editAccount = findViewById(R.id.accountEdit);
        backBtn = findViewById(R.id.profileBack);

        //set account information
        profileName.setText(user.getUsername());
        profileEmail.setText(user.getEmail());
        accountName.setText(user.getUsername());
        accountNumber.setText(user.getNumber());
        accountEmail.setText(user.getEmail());
        accountAge.setText(user.getAge());
        accountBMI.setText(String.valueOf(user.getBmi()));
        accountHeight.setText(String.valueOf(user.getHeight()) + "M");
        accountWeight.setText(String.valueOf(user.getWeight()) + "KG");
        if (user.getPfp().equals("null"))
        {
            profilePic.setImageResource(R.mipmap.ic_launcher_round);
        }
        else
        {
            Picasso.get().load(user.getPfp()).into(profilePic);
        }



        //set onclicks
        editAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, EditProfile.class);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
            }
        });

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
                    Toast.makeText(Profile.this,"Home",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);

                }
                else if (item.getItemId() == R.id.nav_profile)
                {
                    Toast.makeText(Profile.this,"Profile",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Profile.class);
                    startActivity(intent);

                }
                else if (item.getItemId() == R.id.nav_settings)
                {
                    Toast.makeText(Profile.this,"Settings",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),EditProfile.class);
                    startActivity(intent);

                }
                else if (item.getItemId() == R.id.nav_caloriestracker)
                {
                    Toast.makeText(Profile.this,"Calories Tracker",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),caloriesTracking.class);
                    startActivity(intent);

                }
                else if (item.getItemId() == R.id.nav_bmi)
                {
                    Toast.makeText(Profile.this,"BMI Information",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),bmiInformation.class);
                    startActivity(intent);

                }
                else if (item.getItemId() == R.id.nav_video)
                {
                    Toast.makeText(Profile.this,"Workout Videos",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),CatalogueActivity.class);
                    startActivity(intent);

                }
                else if (item.getItemId() == R.id.nav_logout)
                {
                    Toast.makeText(Profile.this,"Logged out",Toast.LENGTH_SHORT).show();
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