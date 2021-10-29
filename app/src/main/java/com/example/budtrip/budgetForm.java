package com.example.budtrip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class budgetForm extends AppCompatActivity {

    EditText formDist, formNoPerson, formDays, formAvg;
    TextView rowcol_name, rowcol_petrol, rowcol_food, rowcol_total, rowcol_stop;
    TableLayout tab1;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_budget_form);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        //EditText
        formDist = findViewById(R.id.form_dist);
        formNoPerson = findViewById(R.id.person_no);
        formDays = findViewById(R.id.noDays);
        formAvg = findViewById(R.id.form_avg);

        //TextView
        rowcol_name = findViewById(R.id.table_name);
        rowcol_petrol = findViewById(R.id.table_petrol);
        rowcol_stop = findViewById(R.id.table_stop);
        rowcol_food = findViewById(R.id.table_food);
        rowcol_total = findViewById(R.id.table_total);

        //Table Layout
        tab1 = findViewById(R.id.table1);

        //RadioGroup and RadioButton
        radioGroup = findViewById(R.id.radioGroup);

        //Button
        btnSubmit = findViewById(R.id.bt_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Checking Fields
                final String distForm = formDist.getText().toString().trim();
                final String noPerson = formNoPerson.getText().toString().trim();
                final String noDays = formDays.getText().toString().trim();
                final String avgForm = formAvg.getText().toString().trim();

                if (TextUtils.isEmpty(distForm)) {
                    formDist.setError("Distance is Required.");
                    return;
                }

                if (TextUtils.isEmpty(noPerson)) {
                    formNoPerson.setError("No of Person is Required.");
                    return;
                }

                if (TextUtils.isEmpty(noDays)) {
                    formDays.setError("No of Days is Required.");
                    return;
                }

                if (TextUtils.isEmpty(avgForm)) {
                    formDays.setError("No of Days is Required.");
                    return;
                }

                if (distForm.equals("0")) {
                    formDist.setError("Distance cannot be zero.");
                    return;
                }

                if (noPerson.equals("0")) {
                    formNoPerson.setError("No of Person cannot be zero.");
                    return;
                }

                if (noDays.equals("0")) {
                    formDays.setError("No of Days cannot be zero.");
                    return;
                }

                if (avgForm.equals("0")) {
                    formDays.setError("No of Days cannot be zero.");
                    return;
                }

                //String To Double
                double dist = Double.valueOf(distForm).doubleValue();
                double avg = Double.valueOf(avgForm).doubleValue();
                //String To Int
                int personNo = Integer.valueOf(noPerson).intValue();
                int days = Integer.valueOf(noDays).intValue();

                int radioId = radioGroup.getCheckedRadioButtonId();
                //Budget Calculation
                radiofun(radioId, dist, personNo, days, avg);
                if (radioId == -1) {
                    // No radio buttons are checked
                    Message.message(getApplicationContext(), "Please select a Mode.");

                } else {
                    // One of the radio buttons is checked
                    radioButton = findViewById(radioId);
                    rowcol_name.setText(radioButton.getText());

                    //Button
                    btnSubmit.setVisibility(view.GONE);

                    //EditText
                    formDist.setVisibility(view.GONE);
                    formNoPerson.setVisibility(view.GONE);
                    formDays.setVisibility(view.GONE);
                    formAvg.setVisibility(view.GONE);

                    //RadioGroup
                    radioGroup.setVisibility(view.GONE);

                    //Table Layout
                    tab1.setVisibility(view.VISIBLE);
                }
            }
        });
    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected Mode: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    private void radiofun(int checkedId, double dist, int personNo, int days, double avg) {

        double total, petrol, food;
        int stop;
        String str_petrol,str_food,str_total,str_stop;

        switch (checkedId) {
            case R.id.radio_bike:
                //Calculation
                petrol = (dist/avg) * 110;
                stop = (int)dist / 300;
                food = personNo * days * 500;
                total = petrol + food;

                //Double to string
                str_petrol = Double.toString(petrol);
                str_stop = Double.toString(stop);
                str_food = Double.toString(food);
                str_total = Double.toString(total);

                //SetText
                rowcol_petrol.setText(str_petrol);
                rowcol_stop.setText(str_stop);
                rowcol_food.setText(str_food);
                rowcol_total.setText(str_total);

                break;

            case R.id.radio_car:
                //Calculation
                petrol = (dist/avg) * 120;
                stop = (int)dist / 360;
                food = personNo * days * 500;
                total = petrol + food;

                //Double to string
                str_petrol = Double.toString(petrol);
                str_stop = Double.toString(stop);
                str_food = Double.toString(food);
                str_total = Double.toString(total);

                //SetText
                rowcol_petrol.setText(str_petrol);
                rowcol_stop.setText(str_stop);
                rowcol_food.setText(str_food);
                rowcol_total.setText(str_total);

                break;

            case R.id.radio_other:

                //Calculation
                petrol = (dist/avg) * 90;
                stop = (int)dist / 300;
                food = personNo * days * 500;
                total = petrol + food;

                //Double to string
                str_petrol = Double.toString(petrol);
                str_stop = Double.toString(stop);
                str_food = Double.toString(food);
                str_total = Double.toString(total);

                //SetText
                rowcol_petrol.setText(str_petrol);
                rowcol_stop.setText(str_stop);
                rowcol_food.setText(str_food);
                rowcol_total.setText(str_total);

                break;
        }
    }
}