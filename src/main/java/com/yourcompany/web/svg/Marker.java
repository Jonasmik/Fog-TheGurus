package com.yourcompany.web.svg;

import java.util.Locale;

public class Marker extends Tag {
    private final String id;
    private final double markerWidth;
    private final double markerHeight;
    private final double refX;
    private final double refY;
    private final String orient;
    private final Path path;

    /**
     * Set by each class constructor such as super("svg") or super("rect")
     */
    protected Marker(String id, double markerWidth, double markerHeight, double refX, double refY, String orient, Path path) {
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
        return String.format(Locale.US, "id=\"%s\" markerWidth=\"%f\" markerHeight=\"%f\" refX=\"%f\" refY=\"%f\" orient=\"%s\">" + path,
                id, markerWidth, markerHeight, refX, refY, orient);
    }
}
