package com.example.gguzzardi.it_accelerator_capacitacion.views;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gguzzardi.it_accelerator_capacitacion.R;
import com.example.gguzzardi.it_accelerator_capacitacion.model.Converter;

public class MainActivity extends AppCompatActivity {
    private EditText mMilesEditText;
    private TextView mKilometersTextView;
    private LinearLayout mResultLayout;

    private Converter mConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMilesEditText = findViewById(R.id.editText_miles);
        mKilometersTextView = findViewById(R.id.tv_numKm);
        mResultLayout = findViewById(R.id.layout_result);
        mResultLayout.setVisibility(View.INVISIBLE);

        mConverter = new Converter();

        setupMilesEditText();
        setupConvertButton();

    }

    private void setupConvertButton() {
        final Button convertButton = findViewById(R.id.bt_convertir);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertToKilometers();
                hideKeyboard(MainActivity.this);
            }
        });
    }

    private void setupMilesEditText() {
        mMilesEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    convertToKilometers();
                    return true;
                }
                return false;
            }
        });

        mMilesEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String milesText = mMilesEditText.getText().toString();
                if (milesText.isEmpty()) return;
                double milesValue = Double.parseDouble(milesText);
                mConverter.setMiles(milesValue);
                convertToKilometers();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void convertToKilometers() {
        mResultLayout.setVisibility(View.VISIBLE);
        mConverter.convert();
        mKilometersTextView.setText(String.format("%.2f", mConverter.getKilometers()));
        mConverter.getKilometers();
    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
