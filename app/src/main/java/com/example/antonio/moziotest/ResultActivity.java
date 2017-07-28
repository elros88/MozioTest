package com.example.antonio.moziotest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.antonio.moziotest.model.Answer;
import com.example.antonio.moziotest.utils.Constants;
import com.google.gson.Gson;

/**
 * Created by antonio on 7/27/17.
 */

public class ResultActivity extends AppCompatActivity
{
    private TextView result;
    private TextView migraineAnswer;
    private TextView ageAnswer;
    private TextView genderAnswer;
    private TextView substanceAnswer;
    private TextView historyLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Gson gson = new Gson();
        Answer answer = gson.fromJson(intent.getStringExtra(Constants.ANSWERS_TAG),
                Answer.class);

        setContentView(R.layout.activity_result);

        result = findViewById(R.id.result);
        migraineAnswer = findViewById(R.id.migraine_answer);
        ageAnswer = findViewById(R.id.age_answer);
        genderAnswer = findViewById(R.id.gender_answer);
        substanceAnswer = findViewById(R.id.substance_answer);
        historyLabel = findViewById(R.id.history_label);

        result.setText(answer.calculateProbability());

        populateFileds(answer);

        if(intent.getBooleanExtra(Constants.HISTORY_TAG, false))
        {
            historyLabel.setVisibility(View.VISIBLE);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void populateFileds(Answer answer)
    {
        if(answer.getMigrainesAnswers().equals(Constants.affirmativeAnswer))
        {
            migraineAnswer.setText(getResources().getString(R.string.migraines_yes));
        }
        else
        {
            migraineAnswer.setText(getResources().getString(R.string.migraines_no));
        }

        if(answer.getGender().equals(Constants.maleAnswer))
        {
            genderAnswer.setText(getResources().getString(R.string.gender_male));
        }
        else
        {
            genderAnswer.setText(getResources().getString(R.string.gender_female));
        }

        if(answer.getSubstanceAnswer().equals(Constants.affirmativeAnswer))
        {
            substanceAnswer.setText(getResources().getString(R.string.substance_yes));
        }
        else
        {
            substanceAnswer.setText(getResources().getString(R.string.substance_no));
        }

        ageAnswer.setText(answer.getAge().toString());

    }

}
