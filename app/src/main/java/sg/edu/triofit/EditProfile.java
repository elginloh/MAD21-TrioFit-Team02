package sg.edu.triofit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        /*---------------Variables---------------*/
        EditText editNumber, editEmail, editHeight, editWeight, editAge;
        Button updateBtn,changePW, changepic;

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
}