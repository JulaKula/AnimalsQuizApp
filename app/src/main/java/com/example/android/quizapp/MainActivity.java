package com.example.android.quizapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int userResult = 0;
    CheckBox answer1_1;
    CheckBox answer1_2;
    CheckBox answer1_3;
    CheckBox answer1_4;
    CheckBox answer6_1;
    CheckBox answer6_2;
    CheckBox answer6_3;
    CheckBox answer6_4;
    RadioButton answer2_rb;
    RadioButton answer4_rb;
    RadioButton answer5_rb;
    RadioButton answer2_rb_1;
    RadioButton answer2_rb_2;
    RadioButton answer2_rb_3;
    RadioButton answer4_rb_1;
    RadioButton answer4_rb_2;
    RadioButton answer4_rb_3;
    RadioButton answer5_rb_1;
    RadioButton answer5_rb_2;
    RadioButton answer5_rb_3;
    EditText answer3_edit;
    TextView summary;
    TextView seal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answer1_1 = findViewById(R.id.ans1_1t);
        answer1_2 = findViewById(R.id.ans1_2t);
        answer1_3 = findViewById(R.id.ans1_3t);
        answer1_4 = findViewById(R.id.ans1_4f);
        answer2_rb = findViewById(R.id.ans2_rb);
        answer2_rb_1 = findViewById(R.id.ans2_rb_1);
        answer2_rb_2 = findViewById(R.id.ans2_rb_2);
        answer2_rb_3 = findViewById(R.id.ans2_rb_3);
        answer3_edit = findViewById(R.id.ans3_edit);
        answer4_rb = findViewById(R.id.ans4_rb);
        answer4_rb_1 = findViewById(R.id.ans4_rb_1);
        answer4_rb_2 = findViewById(R.id.ans4_rb_2);
        answer4_rb_3 = findViewById(R.id.ans4_rb_3);
        answer5_rb = findViewById(R.id.ans5_rb);
        answer5_rb_1 = findViewById(R.id.ans5_rb_1);
        answer5_rb_2 = findViewById(R.id.ans5_rb_2);
        answer5_rb_3 = findViewById(R.id.ans5_rb_3);
        answer6_1 = findViewById(R.id.ans6_1t);
        answer6_2 = findViewById(R.id.ans6_2t);
        answer6_3 = findViewById(R.id.ans6_3t);
        answer6_4 = findViewById(R.id.ans6_4t);
        summary = findViewById(R.id.summary);
        seal = findViewById(R.id.seal_correct_answer);

    }


    public void submit(View view) {
        //Checks answer, shows correct answer for question 1
        if (answer1_1.isChecked() && answer1_2.isChecked() && answer1_3.isChecked() && !answer1_4.isChecked())
            userResult++;
        else if (answer1_4.isChecked()) answer1_4.setTextColor(getResources().getColor(R.color.red));

        answer1_1.setTextColor(getResources().getColor(R.color.colorAccent));
        answer1_2.setTextColor(getResources().getColor(R.color.colorAccent));
        answer1_3.setTextColor(getResources().getColor(R.color.colorAccent));


        //Checks answer, shows correct answer for question 2
        if (answer2_rb.isChecked()) userResult++;
        else if (answer2_rb_1.isChecked()) {
            answer2_rb_1.setTextColor(getResources().getColor(R.color.red));
        }else if (answer2_rb_2.isChecked()) {
            answer2_rb_2.setTextColor(getResources().getColor(R.color.red));
        }else if (answer2_rb_3.isChecked()) {
            answer2_rb_3.setTextColor(getResources().getColor(R.color.red));
        }
        answer2_rb.setTextColor(getResources().getColor(R.color.colorAccent));


        //Checks answer, shows correct answer for question 3
        if (answer3_edit.getText().toString().equals("1952")) {
            answer3_edit.setTextColor(getResources().getColor(R.color.colorAccent));
            userResult++;
        } else {
            answer3_edit.getText();
            answer3_edit.setTextColor(getResources().getColor(R.color.red));
            seal.setText(R.string.seal_answer);
        }

        //Checks answer, shows correct answer for question 4
        if (answer4_rb.isChecked()) userResult++;
        else if (answer4_rb_1.isChecked()) {
            answer4_rb_1.setTextColor(getResources().getColor(R.color.red));
        }else if (answer4_rb_2.isChecked()) {
            answer4_rb_2.setTextColor(getResources().getColor(R.color.red));
        }else if (answer4_rb_3.isChecked()) {
            answer4_rb_3.setTextColor(getResources().getColor(R.color.red));
        }
        answer4_rb.setTextColor(getResources().getColor(R.color.colorAccent));


        //Checks answer, shows correct answer for question 5
        if (answer5_rb.isChecked()) userResult++;
        else if (answer5_rb_1.isChecked()) {
            answer5_rb_1.setTextColor(getResources().getColor(R.color.red));
        }else if (answer5_rb_2.isChecked()) {
            answer5_rb_2.setTextColor(getResources().getColor(R.color.red));
        }else if (answer5_rb_3.isChecked()) {
            answer5_rb_3.setTextColor(getResources().getColor(R.color.red));
        }
        answer5_rb.setTextColor(getResources().getColor(R.color.colorAccent));


        //Checks answer, shows correct answer for question 6
        if (answer6_1.isChecked() && answer6_2.isChecked() && answer6_3.isChecked() && answer6_4.isChecked())
            userResult++;

        answer6_1.setTextColor(getResources().getColor(R.color.colorAccent));
        answer6_2.setTextColor(getResources().getColor(R.color.colorAccent));
        answer6_3.setTextColor(getResources().getColor(R.color.colorAccent));
        answer6_4.setTextColor(getResources().getColor(R.color.colorAccent));

        //Displays the toast with user's result
        Resources res = getResources();
        String text = String.format(res.getString(R.string.result), userResult);
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();

        //Displays suitable message, corresponding to user's result
        if (userResult == 6) summary.setText(R.string.result_6);
        else if (userResult == 5 || userResult == 4) summary.setText(R.string.result_45);
        else if (userResult == 3) summary.setText(R.string.result_3);
        else if (userResult == 2 || userResult == 1) summary.setText(R.string.result_21);
        else if (userResult == 0) summary.setText(R.string.result_0);

        userResult = 0;


    }

    //Restores the default look of the app - removes checks, clears some texts and restores colors
    public void reset(View view) {

        userResult = 0;

        RadioGroup radio_g_2 = findViewById(R.id.radio_g_2);
        RadioGroup radio_g_4 = findViewById(R.id.radio_g_4);
        RadioGroup radio_g_5 = findViewById(R.id.radio_g_5);

        radio_g_2.clearCheck();
        radio_g_4.clearCheck();
        radio_g_5.clearCheck();

        answer3_edit.setText("");
        answer3_edit.clearFocus();
        summary.setText("");
        seal.setText("");

        CheckBox[] myBoxArray = new CheckBox[]{answer1_1, answer1_2, answer1_3, answer1_4,
                answer6_1, answer6_2, answer6_3, answer6_4};

        for (CheckBox aMyStringArray : myBoxArray) {
            aMyStringArray.setTextColor(getResources().getColor(R.color.black));
            if (aMyStringArray.isChecked()) aMyStringArray.toggle();
        }

        RadioButton[] myRadioArray = new RadioButton[]{answer2_rb, answer4_rb, answer5_rb, answer2_rb_1,
                answer2_rb_2, answer2_rb_3, answer4_rb_1, answer4_rb_2, answer4_rb_3, answer5_rb_1,
                answer5_rb_2, answer5_rb_3};

        for (RadioButton aMyRadioArray : myRadioArray)
            aMyRadioArray.setTextColor(getResources().getColor(R.color.black));

    }

}
