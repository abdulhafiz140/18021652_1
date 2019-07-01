package sg.edu.rp.c346.mybmicalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
EditText weight;
EditText height;
Button cal;
Button reset;
TextView date;
TextView BMI;
TextView check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weight = findViewById(R.id.editTextWeight);
        height = findViewById(R.id.editTextHeight);
        cal = findViewById(R.id.btnCal);
        reset = findViewById(R.id.btnReset);
        date = findViewById(R.id.textViewDate);
        BMI = findViewById(R.id.textViewBMI);
        check = findViewById(R.id.Checker);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float strBMI = Float.parseFloat(weight.getText().toString()) / (Float.parseFloat(height.getText().toString()) * Float.parseFloat(height.getText().toString()));

                String bmi = Float.toString(strBMI);
                BMI.setText("Last Calculated BMI:" + bmi);

                Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
                String datetime = date.getText().toString() + now.get(Calendar.DAY_OF_MONTH) + "/" +
                        (now.get(Calendar.MONTH)+1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY) + ":" +
                        now.get(Calendar.MINUTE);

                date.setText(datetime);
                if (strBMI < 18.5) {
                    check.setText("You are underweight");
                }
                else if (strBMI < 24.9){
                    check.setText("Your BMI is normal");
                }
                else if (strBMI < 29.9){
                    check.setText("Your are overweight");
                }
                else{
                    check.setText("You are obese");
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight.setText("");
                height.setText("");
                date.setText("Last Calculated Date: ");
                BMI.setText("Last Calculated BMI:0.0 ");
                check.setText("");
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        float strBMI = Float.parseFloat(weight.getText().toString()) / (Float.parseFloat(height.getText().toString()) * Float.parseFloat(height.getText().toString()));
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putFloat("BMI", strBMI);





    }
}
