package sg.edu.triofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class bmiInformation extends AppCompatActivity {
    List<String> bmiInfo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_information);

        bmiInfo = bmiCheck(26.3);

        /*---------------Variables---------------*/
        TextView bmiType, typeInfo, risk, solution;
        /*---------------Variables---------------*/

        //Retrieve from layout
        bmiType = findViewById(R.id.bmiType);
        typeInfo = findViewById(R.id.typeInfo);
        risk = findViewById(R.id.risk);
        solution = findViewById(R.id.solution);

        //set info
        bmiType.setText(bmiInfo.get(0)); //get from first data in list
        typeInfo.setText(bmiInfo.get(1)); //get second first data in list
        risk.setText(bmiInfo.get(2)); //get from third data in list
        solution.setText(bmiInfo.get(3)); //get from fourth data in list
    }

    public List<String> bmiCheck(Double bmi){
        String message = "";
        String type = "";
        String risk = "";
        String solution = "";
        if(bmi < 18.5)
        {
            type = "You are Underweight";
            message = "How do you know if you are malnourished?\n\n If you've been ill and not eating properly, or if you're underweight, ask your doctor about screening you with a simple questionnaire called the MUST tool.";
            risk = "Risks\n Getting more infections.\n\n Taking longer to recover from illness.\n\n Irregular heart rhythms.\n\n Thinning of the bones.\n\n Higher risk of heart attack.";
            solution = "Solutions\n Ask your doctor for a referral to a dietician to assess what you need.\n\n Eat small quantities frequently, especially if you get full quickly.\n\n Avoid filling up on fluids before a meal.\n\n Don't fill up on 'empty' calories like sugary sweets - if you're not eating much, getting enough vitamins and minerals is crucial.";
            bmiInfo.add(type);
            bmiInfo.add(message);
            bmiInfo.add(risk);
            bmiInfo.add(solution);
        }
        else if(bmi < 24.9)
        {
            type = "Your are Moderate Weight";
            message = "How to achieve healthy weight?\n\n In order to achieve a healthy weight, it is important to first understand the dangers of obesity. Obesity is a condition in which excess body fat accumulates and puts you at risk of a variety of health problems. This excess fat may reduce life expectancy and increase the risk of other health problems, so it is vital to maintain a healthy weight.";
            risk = "Losing Weight the Right Way\n\n Weight management is all about energy balance. To maintain your weight, you must balance the amount of energy you get from food and drinks with the amount of energy your body uses for daily physical activity. Consuming more energy than you need will lead to weight gain, while burning more energy than you consume will lead to weight loss.";
            solution = "Make It A Lifestyle\n\n Successful weight management is a lifestyle. Any healthy weight management plan should incorporate the principles of healthy eating and active living. These habits should become something you grow to enjoy and can include in your daily life. Changing your diet alone or becoming more physically active without eating healthier foods will not be as effective as doing both at the same time.";
            bmiInfo.add(type);
            bmiInfo.add(message);
            bmiInfo.add(risk);
            bmiInfo.add(solution);
        }
        else
        {
            type = "You are Overweight/Obese";
            message = "What is obesity?\n\nObesity is a complex disease involving an excessive amount of body fat. Obesity isn't just a cosmetic concern. It is a medical problem that increases your risk of other diseases and health problems, such as heart disease, diabetes, high blood pressure and certain cancers";
            risk = "Risks\n Heart disease and stroke.\n\n High blood pressure.\n\n Some cancers. \n\n Diabetes. \n\n Gallbladder disease and gallstones. \n\n  Breathing problems, such as sleep apnea (when a person stops breathing for short episodes during sleep) and asthma";
            solution = "Solutions\n Consume less “bad” fat and more “good” fat\n\n Eat plenty of dietary fiber\n\n Engage in regular aerobic activity\n\nIncorporate a weight training regimen\n\nConsume less processed and sugary foods\n\nFocus on eating low–glycemic index foods";
            bmiInfo.add(type);
            bmiInfo.add(message);
            bmiInfo.add(risk);
            bmiInfo.add(solution);
        }
        return bmiInfo;
    }
}