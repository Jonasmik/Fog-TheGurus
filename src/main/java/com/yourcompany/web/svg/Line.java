package com.yourcompany.web.svg;

import java.util.Locale;

public class Line extends Tag {
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;


    /**
     * Set by each class constructor such as super("svg") or super("rect")
     */
    protected Line(int x1, int y1, int x2, int y2) {
        super("line");
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    protected String renderAttributes() {
        return String.format(Locale.US, "x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\"",
                x1, y1, x2, y2);
    }
}
