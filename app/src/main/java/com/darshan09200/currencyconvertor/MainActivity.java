package com.darshan09200.currencyconvertor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

enum CURRENCY {
    CAD, USD
}

public class MainActivity extends AppCompatActivity {

    private EditText priceInput;

    private TextView firstCurrency, secondCurrency;

    private Button swapBtn;

    CURRENCY inputCurrency = CURRENCY.CAD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priceInput = findViewById(R.id.priceInput);

        firstCurrency = findViewById(R.id.firstCurrency);
        secondCurrency = findViewById(R.id.secondCurrency);

        swapBtn = findViewById(R.id.swapBtn);

        convertPrice();

        priceInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                convertPrice();
            }
        });

        swapBtn.setOnClickListener(view -> {
            if (inputCurrency == CURRENCY.CAD)
                inputCurrency = CURRENCY.USD;
            else
                inputCurrency = CURRENCY.CAD;

            convertPrice();
        });

    }

    public void convertPrice() {
        try {
            String formattedPriceInput = priceInput.getText().toString().trim();
            if (formattedPriceInput.isEmpty()) formattedPriceInput = "0";

            double firstCurrencyPrice = Double.parseDouble(formattedPriceInput);
            double secondCurrencyPrice;

            CURRENCY currFirstCurrency = inputCurrency;
            CURRENCY currSecondCurrency;

            if (inputCurrency == CURRENCY.CAD) {
                secondCurrencyPrice = firstCurrencyPrice * 0.75;
                currSecondCurrency = CURRENCY.USD;
            } else {
                secondCurrencyPrice = firstCurrencyPrice * 1.33;
                currSecondCurrency = CURRENCY.CAD;
            }

            firstCurrency.setText(String.format("$%.2f %s", firstCurrencyPrice, currFirstCurrency.toString()));
            secondCurrency.setText(String.format("$%.2f %s", secondCurrencyPrice, currSecondCurrency.toString()));

        } catch (NumberFormatException err) {
            System.out.println(err.getMessage());
        }
    }

//    void convertPrice(){
//
//    }

}