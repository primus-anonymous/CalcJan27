package com.neocaptainnemo.calcjan27.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.neocaptainnemo.calcjan27.domain.Theme;

public class ThemeStorage {


    private final SharedPreferences preferences;

    public ThemeStorage(Context context) {

        preferences = context.getSharedPreferences("themes", Context.MODE_PRIVATE);
    }

    public Theme getTheme() {
        String savedTheme = preferences.getString("theme", Theme.ONE.getKey());

        Theme selectedTheme = Theme.ONE;

        for (Theme theme : Theme.values()) {
            if (theme.getKey().equals(savedTheme)) {
                selectedTheme = theme;
                break;
            }
        }

        return selectedTheme;
    }

    public void saveTheme(Theme theme) {
        preferences.edit().putString("theme", theme.getKey()).apply();
    }
}
