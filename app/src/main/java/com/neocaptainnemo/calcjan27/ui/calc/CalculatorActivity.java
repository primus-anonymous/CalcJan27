package com.neocaptainnemo.calcjan27.ui.calc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.neocaptainnemo.calcjan27.R;
import com.neocaptainnemo.calcjan27.domain.CalculatorImpl;
import com.neocaptainnemo.calcjan27.domain.Operation;
import com.neocaptainnemo.calcjan27.domain.Theme;
import com.neocaptainnemo.calcjan27.storage.ThemeStorage;
import com.neocaptainnemo.calcjan27.ui.SelectThemeActivity;
import com.neocaptainnemo.calcjan27.ui.TextInputActivity;

import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private TextView result;

    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThemeStorage storage = new ThemeStorage(this);

        ActivityResultLauncher<Intent> settingsLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Theme theme = (Theme) result.getData().getSerializableExtra(SelectThemeActivity.THEME_RESULT);

                    storage.saveTheme(theme);

                    recreate();
                }

            }
        });

        Context activityContext = this;

        Context appContext = getApplication();

        Context appContext2 = getApplicationContext();

        Context appContext3 = activityContext.getApplicationContext();

        setTheme(storage.getTheme().getStyle());

        setContentView(R.layout.activity_calculator);

        presenter = new CalculatorPresenter(this, new CalculatorImpl());

        String appName = getString(R.string.app_name);
        int columnCount = getResources().getInteger(R.integer.column_count);

        int bgColor = getColor(R.color.bg_color);

        Toast.makeText(this, appName + " " + columnCount, Toast.LENGTH_SHORT).show();

        result = findViewById(R.id.result);

        if (getIntent().hasExtra("msg")) {
            result.setText(getIntent().getStringExtra("msg"));
        }

        findViewById(R.id.result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculatorActivity.this, TextInputActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsLauncher.launch(SelectThemeActivity.intent(CalculatorActivity.this, storage.getTheme()));
            }
        });

        findViewById(R.id.open_manual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri google = Uri.parse("https://yandex.ru");

                Intent googleIntent = new Intent(Intent.ACTION_VIEW, google);

                startActivity(Intent.createChooser(googleIntent, ""));
            }
        });

        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.key_1, 1);
        digits.put(R.id.key_2, 2);
        digits.put(R.id.key_3, 3);
        digits.put(R.id.key_4, 4);
        digits.put(R.id.key_5, 5);
        digits.put(R.id.key_6, 6);
        digits.put(R.id.key_7, 7);
        digits.put(R.id.key_8, 8);
        digits.put(R.id.key_9, 9);
        digits.put(R.id.key_0, 0);

        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitPressed(digits.get(view.getId()));
            }
        };

        findViewById(R.id.key_1).setOnClickListener(digitClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitClickListener);
        findViewById(R.id.key_0).setOnClickListener(digitClickListener);

        Map<Integer, Operation> operations = new HashMap<>();
        operations.put(R.id.key_add, Operation.ADD);
        operations.put(R.id.key_sub, Operation.SUB);
        operations.put(R.id.key_mult, Operation.MULT);
        operations.put(R.id.key_div, Operation.DIV);

        View.OnClickListener operationsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperationPressed(operations.get(view.getId()));
            }
        };

        findViewById(R.id.key_add).setOnClickListener(operationsClickListener);
        findViewById(R.id.key_sub).setOnClickListener(operationsClickListener);
        findViewById(R.id.key_mult).setOnClickListener(operationsClickListener);
        findViewById(R.id.key_div).setOnClickListener(operationsClickListener);

        findViewById(R.id.key_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDotPressed();
            }
        });

    }

    @Override
    public void showResult(String result) {
        this.result.setText(result);
    }
}