package com.yourcompany.web.svg.tags;

import com.yourcompany.web.svg.Tag;

import java.util.Locale;

public class Rect extends Tag {

    private final double x;
    private final double y;
    private final double width;
    private final double height;

    public Rect(double x, double y, double width, double height) {
        super("rect");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public String renderAttributes() {
        return String.format(Locale.US, "x=\"%f\" y=\"%f\" width=\"%f\" height=\"%f\"",
            x, y, width, height);
    }
}
