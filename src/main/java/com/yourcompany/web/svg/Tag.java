package com.yourcompany.web.svg;

import java.util.ArrayList;
import java.util.List;

public abstract class Tag {

    protected final List<Tag> subTags = new ArrayList<>();
    private final String name;
    private String style;
    private String clazz;

    /**
     * Set by each class constructor such as super("svg") or super("rect")
     */
    protected Tag(String name) {
        this.name = name;
    }

    /**
     * Called to add a tag to the list
     */
    public void add(Tag tag) {
        subTags.add(tag);
    }

    /**
     * Used if you want to add a class to your svg
     */
    public Tag withClass(String name) {
        clazz = name;
        return this;
    }

    /**
     * Used if you want to style your svg
     */
    public Tag withStyle(String style) {
        this.style = style;
        return this;
    }

    /**
     * This is where the whole thing is written down.
     * It checks a class or style has been added
     * then it renders each tag in the subTags List
     */
    public void render(StringBuilder builder) {
        builder.append(String.format("<%s", name));
        if (this.clazz != null) {
            builder.append(String.format(" class=\"%s\"", this.clazz));
        }
        if (this.style != null) {
            builder.append(String.format(" style=\"%s\"", this.style));
        }
        builder.append(" ");
        builder.append(renderAttributes());
        builder.append(">");
        renderSubTags(builder);
        builder.append(String.format("</%s>", name));

    }

    protected abstract String renderAttributes();

    /**
     * renders the amount of subtags that has been added to the the subTags List
     */
    public void renderSubTags(StringBuilder builder) {
        for (Tag t : subTags) {
            t.render(builder);
        }
    }

    /**
     * Called on a method such as chessboard().toString()
     * Calls the render()
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        render(builder);
        return builder.toString();
    }
}
