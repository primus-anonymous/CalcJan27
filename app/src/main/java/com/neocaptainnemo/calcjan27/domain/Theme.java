package com.neocaptainnemo.calcjan27.domain;

import androidx.annotation.StyleRes;

import com.neocaptainnemo.calcjan27.R;

public enum Theme {
    ONE(R.style.Theme_CalcJan27, "one"),
    TWO(R.style.Theme_CalcJan27V2, "two"),
    THREE(R.style.Theme_CalcJan27V3, "three");

    @StyleRes
    private final int style;

    private final String key;

    Theme(int style, String key) {
        this.style = style;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public int getStyle() {
        return style;
    }
}
