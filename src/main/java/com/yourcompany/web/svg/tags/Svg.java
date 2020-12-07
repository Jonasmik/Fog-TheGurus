package com.yourcompany.web.svg.tags;

import com.yourcompany.web.svg.Tag;

public class Svg extends Tag {
    private final int width;
    private final int height;
    private final String viewBox;

    /**
     * the super is used as "name" in Tag, so in this case it will be <svg
     */
    public Svg(int width, int height, String viewBox) {
        super("svg");
        this.width = width;
        this.height = height;
        this.viewBox = viewBox;
    }

    /**
     * Implemente because of the Svg class extending Tag. It is used as the start of an svg doc.
     */
    @Override
    public String renderAttributes() {
        return String.format(
                "xmlns=\"http://www.w3.org/2000/svg\""
                        + " height=\"%d\" width=\"%d\" viewBox=\"%s\""
                        + " preserveAspectRatio=\"xMidYMid meet\"",
                height,
                width,
                viewBox
        );
    }
}
