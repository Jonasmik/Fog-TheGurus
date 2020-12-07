package com.yourcompany.web.svg.tags;

import com.yourcompany.web.svg.Tag;

import java.util.Locale;

public class Text extends Tag {
    private final int x;
    private final int y;
    private final String fill;
    private final int fontSize;
    private final String transform;
    private final String text;
    /**
     * Set by each class constructor such as super("svg") or super("rect")
     */

    public Text(int x, int y, String fill, int fontSize, String transform, String text) {
        super("text");
        this.x = x;
        this.y = y;
        this.fill = fill;
        this.fontSize = fontSize;
        this.transform = transform;
        this.text = text;
    }

    @Override
    protected String renderAttributes() {
        return String.format(Locale.US, "x=\"%d\" y=\"%d\" fill=\"%s\" font-size=\"%d\" transform=\"%s\"",
                x, y, fill, fontSize, transform);
    }

    @Override
    protected String renderContent() {
        return text;
    }
}
