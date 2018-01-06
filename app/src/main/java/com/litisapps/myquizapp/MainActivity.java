package com.litisapps.myquizapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    boolean isQ2Correct = false;
    boolean isQ4Correct = false;
    boolean isQ2Answered = false;
    boolean isQ4Answered = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void OnsubmitAnswers(View view) {
        // question 1. "What is the number of squares on a chessboard?" = 64
        int chessNumber;
        EditText chessNumField = (EditText) findViewById(R.id.chess_number_field);
        String text = chessNumField.getText().toString();
        if (text.matches("")) {
            Toast.makeText(this, "You did not answer question 1.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            chessNumber = Integer.parseInt(chessNumField.getText().toString());
        }


        // question 3. "Which is St. Petersburg previous name?"
        CheckBox q3CB1CheckBox = (CheckBox) findViewById(R.id.q3_cb1); //petrograd true
        boolean isq3CB1CheckBox = q3CB1CheckBox.isChecked();

        CheckBox q3CB2CheckBox = (CheckBox) findViewById(R.id.q3_cb2);
        boolean isq3CB2CheckBox = q3CB2CheckBox.isChecked();


        CheckBox q3CB3CheckBox = (CheckBox) findViewById(R.id.q3_cb3); //leningrad true
        boolean isq3CB3CheckBox = q3CB3CheckBox.isChecked();

        CheckBox q3CB4CheckBox = (CheckBox) findViewById(R.id.q3_cb4);
        boolean isq3CB4CheckBox = q3CB4CheckBox.isChecked();

        if (isQ2Answered==false) {
            Toast.makeText(this, "You did not answer question 2.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isq3CB1CheckBox == false && isq3CB2CheckBox == false && isq3CB3CheckBox == false && isq3CB4CheckBox == false) {
            Toast.makeText(this, "You did not answer question 3.", Toast.LENGTH_SHORT).show();
            return;
        }


        if (isQ4Answered==false) {
            Toast.makeText(this, "You did not answer question 4.", Toast.LENGTH_SHORT).show();
            return;
        }

        createQuizSummary(chessNumber, isQ2Correct, isq3CB1CheckBox, isq3CB2CheckBox, isq3CB3CheckBox, isq3CB4CheckBox, isQ4Correct);


    }


    /**
     * question 2. "Who was told “Beware the Ides of March."?"
     *
     * @param view
     */
    public void onQ2RadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        isQ2Answered= true;
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.q2_rb1:
                if (checked)
                    isQ2Correct = true;// julius caesar
                break;
            case R.id.q2_rb2:
                if (checked)
                    isQ2Correct = false;// augustus caesar
            case R.id.q2_rb3:
                if (checked)
                    isQ2Correct = false;// mark antony
                break;
            case R.id.q2_rb4:
                if (checked)
                    isQ2Correct = false;// cleopatra
                break;
            default:
                isQ2Answered = false;

        }
    }


    /**
     * question 4. "Do you wish to be in this place?"
     *
     * @param view
     */
    public void onQ4RadioButtonClicked(View view) {
        // Is the button now checked?
        isQ4Answered= true;
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.q4_rb1:
                if (checked)
                    isQ4Correct = false;// Thailand - false
                break;
            case R.id.q4_rb2:
                if (checked)
                    isQ4Correct = true;// Costa Rika - true
                break;

        }
    }

    
    private void createQuizSummary(int chessNumber, boolean isQ2Correct, boolean isq3CB1CheckBox, boolean isq3CB2CheckBox, boolean isq3CB3CheckBox,
                                   boolean isq3CB4CheckBox, boolean isQ4Correct) {

        int grade = 0;
        String summaryMessage = "";

        if (chessNumber == 64) {
            grade++;
        } else summaryMessage += "\n1. Chess number of squares is 64";


        if (isQ2Correct == true) {
            grade++;
        } else summaryMessage += "\n2. Jolease Caesar is the answer";


        if (isq3CB1CheckBox == true)  //Petrograd true
            grade++;
        else summaryMessage += "\n3. Petrograd is a correct answer";

        if (isq3CB2CheckBox == true) { //Tashkent false
            summaryMessage += "\n3. Tashkent is an incorrect answer";
            grade--;
        }

        if (isq3CB3CheckBox == true) //Leningrad true
            grade++;
        else summaryMessage += "\n3. Leningrad is a correct answer";

        if (isq3CB4CheckBox == true) { //Belgrade false
            summaryMessage += "\n3. Belgrade is an incorrect answer";
            grade--;
        }


        if (isQ4Correct == true) {
            grade++;
        } else summaryMessage += "\n4. The Flag is of Costa Rika.";


        if (grade < 4) {
            if (grade < 0) { grade = 0; }
            summaryMessage += "\n\nNice Try! your grade is " + grade + " out of 5 points";
        } else
            summaryMessage += "\n\nYou are a Awesome! you scored " + grade + " out of 5 points";

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, summaryMessage, duration);
        toast.show();

    }


}
