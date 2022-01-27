package com.neocaptainnemo.calcjan27;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.neocaptainnemo.calcjan27.domain.Theme;
import com.neocaptainnemo.calcjan27.storage.ThemeStorage;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThemeStorage storage = new ThemeStorage(this);

        setTheme(storage.getTheme().getStyle());

        setContentView(R.layout.activity_calculator);

        String appName = getString(R.string.app_name);
        int columnCount = getResources().getInteger(R.integer.column_count);

        int bgColor = getColor(R.color.bg_color);

        Toast.makeText(this, appName + " " + columnCount, Toast.LENGTH_SHORT).show();

        Button keyOne = findViewById(R.id.key_1);

        if (keyOne != null) {
            keyOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(CalculatorActivity.this, "One pressed", Toast.LENGTH_SHORT).show();
                }
            });
        }

        findViewById(R.id.result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculatorActivity.this, TextInputActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.theme_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                storage.saveTheme(Theme.ONE);

                recreate();

            }
        });

        findViewById(R.id.theme_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                storage.saveTheme(Theme.TWO);

                recreate();

            }
        });

        findViewById(R.id.theme_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                storage.saveTheme(Theme.THREE);

                recreate();
            }
        });
    }
}