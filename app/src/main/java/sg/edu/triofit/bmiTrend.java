package sg.edu.triofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class bmiTrend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_trend);

        /*---------------Variables---------------*/
        CardView bmiCard, foodCard, exerCard;
        /*---------------Variables---------------*/

        //Retrieve from layout
        bmiCard = findViewById(R.id.bmiCard);
        foodCard = findViewById(R.id.foodCard);
        exerCard = findViewById(R.id.exerCard);

        //Set onclick
        bmiCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bmiTrend.this, bmiInformation.class);
                startActivity(intent);
            }
        });

        foodCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bmiTrend.this, foodRecco.class);
                startActivity(intent);
            }
        });
    }
}