package com.example.android.quizapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int userResult = 0;
    boolean submitPressed, afterRotationToast;
    Button submitButton;
    CheckBox answer1_1, answer1_2, answer1_3, answer1_4, answer6_1, answer6_2, answer6_3, answer6_4;
    RadioButton answer2_rb, answer2_rb_1, answer2_rb_2, answer2_rb_3, answer4_rb, answer4_rb_1,
            answer4_rb_2, answer4_rb_3, answer5_rb, answer5_rb_1, answer5_rb_2, answer5_rb_3;
    EditText answer3_edit;
    TextView summary, seal;

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
        submitButton = findViewById(R.id.submitButton);

        if (savedInstanceState != null)
            submitPressed = savedInstanceState.getBoolean("isSubmitted");

        answer3_edit.clearFocus();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("isSubmitted", submitPressed);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getBoolean("isSubmitted");

        //Restore the look of the app, if submitButton was clicked before the rotation
        //Avoid displaying toast after rotation
        if (submitPressed) {
            afterRotationToast = true;
            submitButton.performClick();
        }

        //Avoid popping-up keyboard after rotation
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        answer3_edit.clearFocus();
    }

    public void submit(View view) {
        //Set userResult to 0, to avoid problems when submit button is clicked more than once,
        //update submitPressed boolean
        userResult = 0;
        submitPressed = true;

        //Check answer, show correct answer for question 1
        if (answer1_1.isChecked() && answer1_2.isChecked() && answer1_3.isChecked() && !answer1_4.isChecked())
            userResult++;
        else if (answer1_4.isChecked())
            answer1_4.setTextColor(getResources().getColor(R.color.red));
        answer1_1.setTextColor(getResources().getColor(R.color.colorAccent));
        answer1_2.setTextColor(getResources().getColor(R.color.colorAccent));
        answer1_3.setTextColor(getResources().getColor(R.color.colorAccent));

        //Check answer, show correct answer for question 2
        if (answer2_rb.isChecked())
            userResult++;
        else if (answer2_rb_1.isChecked())
            answer2_rb_1.setTextColor(getResources().getColor(R.color.red));
        else if (answer2_rb_2.isChecked())
            answer2_rb_2.setTextColor(getResources().getColor(R.color.red));
        else if (answer2_rb_3.isChecked())
            answer2_rb_3.setTextColor(getResources().getColor(R.color.red));
        answer2_rb.setTextColor(getResources().getColor(R.color.colorAccent));

        //Check answer, show correct answer for question 3
        if (answer3_edit.getText().toString().equals("1952")) {
            answer3_edit.setTextColor(getResources().getColor(R.color.colorAccent));
            userResult++;
        } else {
            answer3_edit.setTextColor(getResources().getColor(R.color.red));
            seal.setText(R.string.seal_answer);
        }
        answer3_edit.clearFocus();

        //Check answer, show correct answer for question 4
        if (answer4_rb.isChecked())
            userResult++;
        else if (answer4_rb_1.isChecked())
            answer4_rb_1.setTextColor(getResources().getColor(R.color.red));
        else if (answer4_rb_2.isChecked())
            answer4_rb_2.setTextColor(getResources().getColor(R.color.red));
        else if (answer4_rb_3.isChecked())
            answer4_rb_3.setTextColor(getResources().getColor(R.color.red));
        answer4_rb.setTextColor(getResources().getColor(R.color.colorAccent));

        //Check answer, show correct answer for question 5
        if (answer5_rb.isChecked())
            userResult++;
        else if (answer5_rb_1.isChecked())
            answer5_rb_1.setTextColor(getResources().getColor(R.color.red));
        else if (answer5_rb_2.isChecked())
            answer5_rb_2.setTextColor(getResources().getColor(R.color.red));
        else if (answer5_rb_3.isChecked())
            answer5_rb_3.setTextColor(getResources().getColor(R.color.red));
        answer5_rb.setTextColor(getResources().getColor(R.color.colorAccent));

        //Check answer, show correct answer for question 6
        if (answer6_1.isChecked() && answer6_2.isChecked() && answer6_3.isChecked() && answer6_4.isChecked())
            userResult++;
        answer6_1.setTextColor(getResources().getColor(R.color.colorAccent));
        answer6_2.setTextColor(getResources().getColor(R.color.colorAccent));
        answer6_3.setTextColor(getResources().getColor(R.color.colorAccent));
        answer6_4.setTextColor(getResources().getColor(R.color.colorAccent));

        //Display the toast with user's result
        if (!afterRotationToast) {
            Resources res = getResources();
            String text = String.format(res.getString(R.string.result), userResult);
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }
        afterRotationToast = false;

        //Display suitable message, corresponding to user's result
        if (userResult == 6)
            summary.setText(R.string.result_6);
        else if (userResult == 5 || userResult == 4)
            summary.setText(R.string.result_45);
        else if (userResult == 3)
            summary.setText(R.string.result_3);
        else if (userResult == 2 || userResult == 1)
            summary.setText(R.string.result_21);
        else if (userResult == 0)
            summary.setText(R.string.result_0);
    }

    //Restore the default look of the app - reset the result, remove checks, clear texts and restore basic colors
    //update submitPressed boolean
    public void reset(View view) {
        userResult = 0;
        submitPressed = false;
        answer3_edit.setText("");
        answer3_edit.setTextColor(getResources().getColor(R.color.black));
        answer3_edit.clearFocus();
        seal.setText("");
        summary.setText("");
        RadioGroup radio_g_2 = findViewById(R.id.radio_g_2);
        radio_g_2.clearCheck();
        RadioGroup radio_g_4 = findViewById(R.id.radio_g_4);
        radio_g_4.clearCheck();
        RadioGroup radio_g_5 = findViewById(R.id.radio_g_5);
        radio_g_5.clearCheck();

        CompoundButton[] myButtons = new CompoundButton[]{answer1_1, answer1_2, answer1_3,
                answer1_4, answer6_1, answer6_2, answer6_3, answer6_4, answer2_rb, answer2_rb_1,
                answer2_rb_2, answer2_rb_3, answer4_rb, answer4_rb_1, answer4_rb_2, answer4_rb_3,
                answer5_rb, answer5_rb_1, answer5_rb_2, answer5_rb_3};
        for (CompoundButton aMyButtons : myButtons) {
            aMyButtons.setTextColor(getResources().getColor(R.color.black));
        }
        for (int i = 0; i < 8; i++) {
            if (myButtons[i].isChecked())
                myButtons[i].toggle();
        }
    }
}
