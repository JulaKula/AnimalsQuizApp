package com.example.android.quizapp;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.quizapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    static final String isSubmitted = "submitButtonWasClicked";
    ActivityMainBinding binding;
    private int userResult;
    private boolean submitPressed;
    private boolean afterRotationToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (savedInstanceState != null) {
            submitPressed = savedInstanceState.getBoolean(isSubmitted);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(isSubmitted, submitPressed);
    }

    //Call the submit method if submitButton was clicked before the rotation
    //Avoid displaying toast, popping-up keyboard and focusing on EditText after rotation
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (submitPressed) {
            afterRotationToast = true;
            binding.submitButton.performClick();
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding.ans3Edit.clearFocus();
        binding.welcome.requestFocus();
    }

    public void submit(View view) {
        //Set userResult to 0, to avoid problems when submit button is clicked more than once,
        //update submitPressed boolean
        userResult = 0;
        submitPressed = true;

        //Check answers, show correct answers for all questions
        check1();
        checkRadioGroup(binding.ans2Rb, binding.ans2Rb1, binding.ans2Rb2, binding.ans2Rb3);
        check3();
        checkRadioGroup(binding.ans4Rb, binding.ans4Rb1, binding.ans4Rb2, binding.ans4Rb3);
        checkRadioGroup(binding.ans5Rb, binding.ans5Rb1, binding.ans5Rb2, binding.ans5Rb3);
        check6();

        //Display the toast with user's result
        if (!afterRotationToast) {
            Resources res = getResources();
            String text = String.format(res.getString(R.string.result), userResult);
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }
        afterRotationToast = false;

        //Display suitable message, corresponding to user's result
        if (userResult == 6) {
            binding.summary.setText(R.string.result_6);
        } else if (userResult == 5 || userResult == 4) {
            binding.summary.setText(R.string.result_45);
        } else if (userResult == 3) {
            binding.summary.setText(R.string.result_3);
        } else if (userResult == 2 || userResult == 1) {
            binding.summary.setText(R.string.result_21);
        } else if (userResult == 0) {
            binding.summary.setText(R.string.result_0);
        }
    }

    //Next 4 methods check answers given in the quiz
    private void check1() {
        if (binding.ans11t.isChecked() && binding.ans12t.isChecked() && binding.ans13t.isChecked() && !binding.ans14f.isChecked()) {
            userResult++;
        } else if (binding.ans14f.isChecked()) {
            binding.ans14f.setTextColor(getResources().getColor(R.color.red));
        }
        binding.ans11t.setTextColor(getResources().getColor(R.color.colorAccent));
        binding.ans12t.setTextColor(getResources().getColor(R.color.colorAccent));
        binding.ans13t.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    private void checkRadioGroup(RadioButton correctAnswer, RadioButton wrongAnswer1,
                                 RadioButton wrongAnswer2, RadioButton wrongAnswer3) {
        if (correctAnswer.isChecked()) {
            userResult++;
        } else if (wrongAnswer1.isChecked()) {
            wrongAnswer1.setTextColor(getResources().getColor(R.color.red));
        } else if (wrongAnswer2.isChecked()) {
            wrongAnswer2.setTextColor(getResources().getColor(R.color.red));
        } else if (wrongAnswer3.isChecked()) {
            wrongAnswer3.setTextColor(getResources().getColor(R.color.red));
        }
        correctAnswer.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    private void check3() {
        if (binding.ans3Edit.getText().toString().equals("1952")) {
            binding.ans3Edit.setTextColor(getResources().getColor(R.color.colorAccent));
            userResult++;
        } else {
            binding.ans3Edit.setTextColor(getResources().getColor(R.color.red));
            binding.sealCorrectAnswer.setText(R.string.seal_answer);
        }
        binding.ans3Edit.clearFocus();
    }

    private void check6() {
        if (binding.ans61t.isChecked() && binding.ans62t.isChecked() && binding.ans63t.isChecked() && binding.ans64t.isChecked()) {
            userResult++;
        }
        binding.ans61t.setTextColor(getResources().getColor(R.color.colorAccent));
        binding.ans62t.setTextColor(getResources().getColor(R.color.colorAccent));
        binding.ans63t.setTextColor(getResources().getColor(R.color.colorAccent));
        binding.ans64t.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    //Restore the default look of the app - reset the result, clear texts, remove checks and restore basic colors
    //update submitPressed boolean
    public void reset(View view) {
        userResult = 0;
        submitPressed = false;
        binding.ans3Edit.setText("");
        binding.ans3Edit.clearFocus();
        binding.sealCorrectAnswer.setText("");
        binding.summary.setText("");

        TextView[] allViews = new TextView[]{binding.ans11t, binding.ans12t, binding.ans13t,
                binding.ans14f, binding.ans61t, binding.ans62t, binding.ans63t, binding.ans64t,
                binding.ans2Rb, binding.ans2Rb1, binding.ans2Rb2, binding.ans2Rb3, binding.ans4Rb,
                binding.ans4Rb1, binding.ans4Rb2, binding.ans4Rb3, binding.ans5Rb, binding.ans5Rb1,
                binding.ans5Rb2, binding.ans5Rb3, binding.ans3Edit};
        for (TextView allMyViews : allViews) {
            allMyViews.setTextColor(getResources().getColor(R.color.black));
        }

        CompoundButton[] myButtons = new CompoundButton[]{binding.ans11t, binding.ans12t, binding.ans13t,
                binding.ans14f, binding.ans61t, binding.ans62t, binding.ans63t, binding.ans64t};
        for (CompoundButton myCompoundButtons : myButtons) {
            myCompoundButtons.setChecked(false);
        }

        binding.radioG2.clearCheck();
        binding.radioG4.clearCheck();
        binding.radioG5.clearCheck();
    }
}