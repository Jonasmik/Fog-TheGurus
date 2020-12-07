package com.yourcompany.web.svg.tags;

import com.yourcompany.web.svg.Tag;

import java.util.Locale;

public class Marker extends Tag {
    private final String id;
    private final int markerWidth;
    private final int markerHeight;
    private final int refX;
    private final int refY;
    private final String orient;

    /**
     * Set by each class constructor such as super("svg") or super("rect")
     */
    public Marker(String id, int markerWidth, int markerHeight, int refX, int refY, String orient) {
        super("marker");
        this.id = id;
        this.markerWidth = markerWidth;
        this.markerHeight = markerHeight;
        this.refX = refX;
        this.refY = refY;
        this.orient = orient;
    }

    @Override
    protected String renderAttributes() {
        return String.format(Locale.US, "id=\"%s\" markerWidth=\"%d\" markerHeight=\"%d\" refX=\"%d\" refY=\"%d\" orient=\"%s\"",
                id, markerWidth, markerHeight, refX, refY, orient);
    }

    // @Override
    // protected String renderContent() {
    //     return path.toString();
    // }
}
