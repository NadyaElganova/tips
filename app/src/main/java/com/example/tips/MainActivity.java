package com.example.tips;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText billAmountEditText;
    private SeekBar tipPercentageSeekBar;
    private TextView tipResultTextView;
    private TextView totalResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        billAmountEditText = findViewById(R.id.billAmount);
        tipPercentageSeekBar = findViewById(R.id.tipPercentage);
        tipResultTextView = findViewById(R.id.tipResult);
        totalResultTextView = findViewById(R.id.totalResult);

        billAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateTip();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        tipPercentageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                calculateTip();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void calculateTip() {
        double billAmount = Double.parseDouble(billAmountEditText.getText().toString());
        int tipPercentage = tipPercentageSeekBar.getProgress();

        double tipAmount = billAmount * (tipPercentage / 100.0);
        double totalAmount = billAmount + tipAmount;

        tipResultTextView.setText(String.format("Чаевые: %.2f", tipAmount));
        totalResultTextView.setText(String.format("Итого: %.2f", totalAmount));
    }
}