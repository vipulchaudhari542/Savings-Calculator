package com.example.vipul.savingscalculator;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final int WEEKS_IN_ONE_YEAR = 52;
    TextView txtTitle;
    EditText edtYearlyIncome;
    TextView txtWeeklySavings;
    SeekBar seekBar;
    Button btnReset;
    TextView txtTotalSavings;
    TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialise elements

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        edtYearlyIncome = (EditText) findViewById(R.id.edtYearlyIncome);
        txtWeeklySavings = (TextView) findViewById(R.id.txtWeeklySavings);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        btnReset = (Button) findViewById(R.id.btnReset);
        txtTotalSavings = (TextView)findViewById(R.id.txtTotalSavings);
        txtName = (TextView) findViewById(R.id.txtName);

        //setup fonts
        Typeface monofettFont = Typeface.createFromAsset(getAssets(),"fonts/Monofett.ttf");
        Typeface TekoMediumFont = Typeface.createFromAsset(getAssets(),"fonts/Teko-Medium.ttf");
        Typeface TekoSemiBoldFont = Typeface.createFromAsset(getAssets(),"fonts/Teko-SemiBold.ttf");

        //apply fonts for texts
        txtTitle.setTypeface(TekoSemiBoldFont);
        txtWeeklySavings.setTypeface(TekoMediumFont);
        txtTotalSavings.setTypeface(monofettFont);
        btnReset.setTypeface(TekoMediumFont);
        txtName.setTypeface(TekoSemiBoldFont);

        //setup an seek bar

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //displaing the changes on seekbar
                txtWeeklySavings.setText("Weekly savings:\n$"+ progress);

                //calculate the yearly savings
                int totalSavingsPerYear = progress * WEEKS_IN_ONE_YEAR;
                txtTotalSavings.setText("$"+totalSavingsPerYear );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        edtYearlyIncome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //calculate max amount allowed on seekbar

                String yearlyIncomeAsText = edtYearlyIncome.getText().toString();
                double yearlyIncomeAsNumber = 0;

                if(!yearlyIncomeAsText.isEmpty()){
                    yearlyIncomeAsNumber = Double.parseDouble(yearlyIncomeAsText);

                }

                double weeklyIncome = yearlyIncomeAsNumber / WEEKS_IN_ONE_YEAR;
                int maxSavingsAllowed = (int) (weeklyIncome / 2);

                //setup max amount on seekbar
                seekBar.setMax(maxSavingsAllowed);


            }
        });

        //setup reset button

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reset edit text
                edtYearlyIncome.setText(null);
                edtYearlyIncome.dispatchDisplayHint(View.VISIBLE);


                //reset seekbar
                seekBar.setProgress(0);
                seekBar.setMax(100);

                //reset total yearly savings
                txtTotalSavings.setText("TOTAL");
            }
        });
    }
}
