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
        TextView accountName, accountNumber, accountEmail, accountAge, accountBMI, accountHeight, accountWeight, profileName, profileEmail;
        ImageView changePfp;
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

        //set account information
        profileName.setText("Elgin Loh");
        profileEmail.setText("elginloh@gmail.com");
        accountName.setText("Elgin Loh");
        accountNumber.setText("91691626");
        accountEmail.setText("elginloh@gmail.com");
        accountAge.setText("20");
        accountBMI.setText("24");
        accountHeight.setText("173CM");
        accountWeight.setText("73KG");

        //set onclicks

    }
}