package sg.edu.triofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*---------------Variables---------------*/
        TextView accountName, accountNumber, accountEmail, accountAge, accountBMI, accountHeight, accountWeight, profileName, profileEmail, backBtn;
        ImageView changePfp, editAccount;
        UserData user = LoginActivity.userInfo;
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
        changePfp = findViewById(R.id.changePfp);
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
    }
}