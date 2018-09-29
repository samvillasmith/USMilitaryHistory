package com.example.android.usmilitaryhistory;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Objects;

//This quiz tests the player regarding US Military facts.  The Questions are below//

public class MainActivity extends AppCompatActivity {

    //establishing baseScore as a public asset//
    public int baseScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etName;
        Button btMake;

//This portion of code establishes the EditText field etName, btMake Button, and establishes toast//

        etName = findViewById(R.id.name_field);
        btMake = findViewById(R.id.btPress);
        final Context context = getApplicationContext();
        final int duration = Toast.LENGTH_SHORT;


//Question regarding the first US General with EditText and if/else statements//

        btMake.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onClick(View v) {
                if (Objects.equals(etName.getText().toString(), "George Washington")) {

                    String Washington = etName.getText().toString();
                    Toast toast = Toast.makeText(context, "Hail to the Chief! It is " + Washington, duration);
                    toast.show();
                    baseScore = baseScore + 1;
                } else {
                    Toast toast = Toast.makeText(context, "Wrong", duration);
                    toast.show();
                    baseScore = baseScore - 1;
                }

            }
        });
    }

//boolean CheckBox variables with toasts and incremental score calculation for Cold War history//

    public void checkBoxes (View view) {

        boolean isChecked_1 = ((CheckBox) findViewById( R.id.soviet_checkbox )).isChecked();
        boolean isChecked_2 = ((CheckBox) findViewById( R.id.gbr_checkbox )).isChecked();
        boolean isChecked_3 = ((CheckBox) findViewById( R.id.wgm_checkbox )).isChecked();
        boolean isChecked_4 = ((CheckBox) findViewById( R.id.prona_checkbox )).isChecked();

        if (isChecked_2 == true && isChecked_3 == true) {
            if (isChecked_1 == false && isChecked_4 == false)
                Toast.makeText(this, "Outstanding!", Toast.LENGTH_SHORT).show();
            {
                baseScore += 1;
            }
        }
        else {
            baseScore -= 1;
            Toast.makeText(this, "No, we never embraced Communism", Toast.LENGTH_SHORT).show();
        }
    }

    //RadioButton fields with toasts and score calculation for the history of the War of 1812//
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.yes_radio_button:
                if (checked)
                    Toast.makeText(this, "You got it!", Toast.LENGTH_SHORT).show();
                baseScore = baseScore + 1;

                break;
            case R.id.no_radio_button:
                if (checked)
                    baseScore = baseScore - 1;
                    Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
                break;
        }
    }
//US Marine history during WWII with RadioButtons, toasts, and score calculation//
    public void onRadioButtonClicked1(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.yes_radio_button1:
                if (checked)
                    Toast.makeText(this, "Shores of Iwo Jima", Toast.LENGTH_SHORT).show();
                baseScore = baseScore + 1;
                break;
            case R.id.no_radio_button2:
                if (checked)
                    baseScore = baseScore - 1;
                    Toast.makeText(this, "Have you ever talked to a Marine?", Toast.LENGTH_SHORT).show();
                break;
        }
    }
//Trick question involving the Russian Armata Tank for Armor enthusiasts. Radio boolean group with//
    //toasts, score calculation, and adjustments//
    public void onRadioButtonClicked2(View view) {
        // Determining if the button is currently checked//
        boolean checked = ((RadioButton) view).isChecked();
        //Check on which radio button was clicked//
        switch (view.getId()) {
            case R.id.yes_radio_button3:
                if (checked)
                    Toast.makeText( this, "Wrong. This isn't Russia.", Toast.LENGTH_SHORT ).show();
                baseScore = baseScore - 1;
                break;
            case R.id.no_radio_button4:
                if (checked) baseScore = baseScore + 1;
                Toast.makeText( this, "That's right. It's the M1A1 Abrams.", Toast.LENGTH_SHORT ).show();
                break;
        }

    }

//Calculating overall score and displaying it in a Toast, with the number attempted vs. total//
    public void submitOrder(View view) {
        String finalMessage1 = "Your score is: " + baseScore + " out of 5! Fall out!";
        Toast.makeText (this, finalMessage1, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData( Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "I scored " +baseScore + " can you beat that?");
                intent.putExtra(Intent.EXTRA_SUBJECT, "I challenge you to beat my score!");
                        intent.putExtra(Intent.EXTRA_TEXT, "What's going on hero? I just" +
                                " tested my knowledge of US Military History and scored "
                        + baseScore +" out of 5. Think you can do better? Take the quiz now!");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    }




