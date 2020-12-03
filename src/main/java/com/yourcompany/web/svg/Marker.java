package com.yourcompany.web.svg;

import java.util.Locale;

public class Marker extends Tag {
    private final String id;
    private final int markerWidth;
    private final int markerHeight;
    private final int refX;
    private final int refY;
    private final String orient;
    private final Path path;

    /**
     * Set by each class constructor such as super("svg") or super("rect")
     */
    protected Marker(String id, int markerWidth, int markerHeight, int refX, int refY, String orient, Path path) {
        super("marker");
        this.id = id;
        this.markerWidth = markerWidth;
        this.markerHeight = markerHeight;
        this.refX = refX;
        this.refY = refY;
        this.orient = orient;
        this.path = path;
    }

    @Override
    protected String renderAttributes() {
        return String.format(Locale.US, "id=\"%s\" markerWidth=\"%d\" markerHeight=\"%d\" refX=\"%d\" refY=\"%d\" orient=\"%s\">" + path,
                id, markerWidth, markerHeight, refX, refY, orient);
    }
}
