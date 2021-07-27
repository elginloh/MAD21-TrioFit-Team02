package sg.edu.triofit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class EditProfile extends AppCompatActivity {
    public Uri imageUri;
    private ImageView profilePic;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad21-triofit-team02-ab582-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference mDatabase = firebaseDatabase.getReference();
    UserData user = LoginActivity.userInfo;

    //Nav
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_editeditprofile);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        /*---------------Variables---------------*/
        EditText editNumber, editEmail, editHeight, editWeight, editAge;
        Button updateBtn,changePW, changepic;
        TextView back;

        /*---------------Variables---------------*/

        //Retrieve
        editNumber = findViewById(R.id.editNumber);
        editEmail = findViewById(R.id.editEmail);
        editHeight = findViewById(R.id.editHeight);
        editWeight = findViewById(R.id.editWeight);
        editAge = findViewById(R.id.editAge);
        updateBtn = findViewById(R.id.updateBtn);
        changePW  = findViewById(R.id.btn_ChangePassword);
        changepic = findViewById(R.id.changeImg);
        profilePic = findViewById(R.id.editPfp);
        back = findViewById(R.id.profileBack7);

        //setDefaultText
        editNumber.setText(user.getNumber());
        editEmail.setText(user.getEmail());
        editHeight.setText(String.valueOf(user.getHeight()));
        editWeight.setText(String.valueOf(user.getWeight()));
        editAge.setText(user.getAge());
        if (user.getPfp().equals("null"))
        {
            profilePic.setImageResource(R.mipmap.ic_launcher_round);
        }
        else
        {
            Picasso.get().load(user.getPfp()).into(profilePic);
        }

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
                    Toast.makeText(EditProfile.this,"Home",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_profile)
                {
                    Toast.makeText(EditProfile.this,"Profile",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Profile.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_settings)
                {
                    Toast.makeText(EditProfile.this,"Settings",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),EditProfile.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_caloriestracker)
                {
                    Toast.makeText(EditProfile.this,"Calories Tracker",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),caloriesTracking.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_bmi)
                {
                    Toast.makeText(EditProfile.this,"BMI Information",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),bmiInformation.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_video)
                {
                    Toast.makeText(EditProfile.this,"Workout Videos",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),CatalogueActivity.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.nav_logout)
                {
                    Toast.makeText(EditProfile.this,"Logged out",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);

                }

                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        //set onclick
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

        changePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this,ChangePassword.class);
                startActivity(intent);
            }
        });

        changepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent , 2);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this, Profile.class);
                startActivity(intent);
            }
        });
    }

    private void saveData(String email,String age,Float height,Float weight,Float bmi,String number)
    {
        // create user object and store data in
//        UserData userInfo = new UserData(username,password,email,age,height,weight,bmi,number, username);
        //Save user object created to 'User' table in firebase
        if(imageUri != null){
            uploadToFirebase(imageUri);
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK && data!=null)
        {
            imageUri = data.getData();
            user.setPfp(imageUri.toString());
            profilePic.setImageURI(imageUri);
        }
    }

    private void uploadToFirebase(Uri uri){
        StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        mDatabase.child("User").child(user.getUsername()).child("imageUrl").setValue(uri.toString());
                        user.setPfp(uri.toString());
                        Toast.makeText(EditProfile.this, "Upload Successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProfile.this, "Uploading Failed!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
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