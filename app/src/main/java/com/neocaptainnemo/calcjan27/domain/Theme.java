package com.neocaptainnemo.calcjan27.domain;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.neocaptainnemo.calcjan27.R;

public enum Theme {
    ONE(R.style.Theme_CalcJan27, R.string.theme_one, "one"),
    TWO(R.style.Theme_CalcJan27V2, R.string.theme_two, "two"),
    THREE(R.style.Theme_CalcJan27V3, R.string.theme_three, "three");

    @StyleRes
    private final int style;

    @StringRes
    private final int title;

    private final String key;

    Theme(int style, int title, String key) {
        this.style = style;
        this.title = title;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public int getStyle() {
        return style;
    }

    public int getTitle() {
        return title;
    }
}
