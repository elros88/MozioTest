package com.example.antonio.moziotest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.RadialGradient;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.antonio.pizzasdk.PizzaMainActivity;
import com.example.antonio.moziotest.model.Answer;
import com.example.antonio.moziotest.utils.Constants;
import com.example.antonio.moziotest.utils.SharedPreferenceAccesor;

public class MainActivity extends AppCompatActivity
{

    private RadioGroup migrainesGroup;
    private RadioGroup genderGroup;
    private RadioGroup substancesGroup;
    private Button selectAgeButton;
    private Button sendAnswersButton;
    TextView selectedAge;
    private Dialog ageDialog;
    private Answer answers = new Answer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        migrainesGroup = findViewById(R.id.migraine_selector);
        genderGroup = findViewById(R.id.gender_selector);
        substancesGroup = findViewById(R.id.substance_selector);
        selectAgeButton = findViewById(R.id.button_select_age);
        selectedAge = findViewById(R.id.selected_age);
        sendAnswersButton = findViewById(R.id.button_send);


    }

    @Override
    public void onResume()
    {
        super.onResume();

        answers = new Answer();

        uncheckButtons(migrainesGroup);
        uncheckButtons(genderGroup);
        uncheckButtons(substancesGroup);

        configureRadioGroups();

        selectAgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAgeDialog();
            }
        });

        selectedAge.setText(answers.getAge()>=0?
                answers.getAge().toString() : getResources().getText(R.string.age_not_setted));

        sendAnswersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAnswer();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_history) {

            SharedPreferenceAccesor accesor =
                    new SharedPreferenceAccesor(getApplicationContext());

            if(!accesor.getPreference(Constants.ANSWERS_TAG).equals(""))
            {
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra(Constants.ANSWERS_TAG, accesor.getPreference(Constants.ANSWERS_TAG));
                intent.putExtra(Constants.HISTORY_TAG, true);
                startActivity(intent);
            }
            else
            {
                showAlert(getResources().getString(R.string.no_history_message).toString(),
                        getResources().getString(R.string.no_history_title).toString());
            }

            return true;
        }

        if (id == R.id.action_pizza) {

            Intent intent = new Intent(getApplicationContext(), PizzaMainActivity.class);

            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private Integer checkRadioAnswer(RadioGroup group)
    {
        int selectedId = group.getCheckedRadioButtonId();
        RadioButton button = findViewById(selectedId);
        return button.getId();
    }

    private void showAgeDialog()
    {
        ageDialog = new Dialog(this);
        ageDialog.setContentView(R.layout.dialog_age_selection);
        ageDialog.setTitle(getResources().getString(R.string.label_age).toString());
        final NumberPicker agePicker = ageDialog.findViewById(R.id.age_selector);
        Button ageButton = ageDialog.findViewById(R.id.age_button);

        configureAgePicker(agePicker);

        ageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answers.setAge(agePicker.getValue());
                selectedAge.setText(answers.getAge().toString());
                ageDialog.dismiss();
            }
        });

        ageDialog.show();
    }

    private void configureAgePicker(NumberPicker picker)
    {
        String[] ages = new String[150];
        for(int i=0; i<ages.length; i++)
            ages[i] = Integer.toString(i+1);

        picker.setMinValue(1);
        picker.setMaxValue(150);
        picker.setWrapSelectorWheel(false);
        picker.setDisplayedValues(ages);
        picker.setValue(25);
    }

    private void showAlert(String message, String title)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message)
                .setTitle(title);

        builder.setNegativeButton(R.string.alert_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });


        AlertDialog dialog = builder.create();

        dialog.show();
    }

    private void sendAnswer()
    {
        if(answers.checkAnswers())
        {
            SharedPreferenceAccesor accesor =
                    new SharedPreferenceAccesor(getApplicationContext());

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra(Constants.ANSWERS_TAG,accesor.saveObject(answers, Constants.ANSWERS_TAG));
            startActivity(intent);
        }
        else
        {
            showAlert(getResources().getString(R.string.uncomplete_answer_message).toString(),
                    getResources().getString(R.string.uncomplete_answer_title));
        }
    }

    private void configureRadioGroups()
    {
        migrainesGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(checkRadioAnswer(migrainesGroup) == R.id.migraineYes)
                {
                    answers.setMigrainesAnswers(Constants.affirmativeAnswer);
                }
                if(checkRadioAnswer(migrainesGroup) == R.id.migraineNo)
                {
                    answers.setMigrainesAnswers(Constants.negativeAnswer);
                }
            }
        });

        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(checkRadioAnswer(genderGroup) == R.id.radio_male)
                {
                    answers.setGender(Constants.maleAnswer);
                }
                if(checkRadioAnswer(genderGroup) == R.id.radio_female)
                {
                    answers.setGender(Constants.femaleAnswer);
                }
            }
        });

        substancesGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(checkRadioAnswer(substancesGroup) == R.id.substanceYes)
                {
                    answers.setSubstanceAnswer(Constants.affirmativeAnswer);
                }
                if(checkRadioAnswer(substancesGroup) == R.id.substanceNo)
                {
                    answers.setSubstanceAnswer(Constants.negativeAnswer);
                }
            }
        });
    }

    private void uncheckButtons(RadioGroup group)
    {

        group.setOnCheckedChangeListener(null);
        group.clearCheck();
    }
}
