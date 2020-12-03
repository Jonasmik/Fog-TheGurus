package com.yourcompany.web.svg;

import java.util.Locale;

public class Line extends Tag {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;


    /**
     * Set by each class constructor such as super("svg") or super("rect")
     */
    protected Line(double x1, double y1, double x2, double y2) {
        super("line");
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    protected String renderAttributes() {
        return String.format(Locale.US, "x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\"",
                x1, y1, x2, y2);
    }
}
