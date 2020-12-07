package com.yourcompany.web.svg.tags;

import com.yourcompany.web.svg.Tag;

import java.util.Locale;

public class Path extends Tag {
    private final String d;

    /**
     * Set by each class constructor such as super("svg") or super("rect")
     */
    public Path(String d) {
        super("path");
        this.d = d;
    }

    @Override
    protected String renderAttributes() {
        return String.format(Locale.US, "d=\"%s\"",
                d);
    }
}
