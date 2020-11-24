package com.yourcompany.web.svg;

import java.io.FileWriter;
import java.io.IOException;

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
                        + " height=\"%d\" width=\"%d\" viewBox=\"%s\"",
                height,
                width,
                viewBox
        );
    }

    /**
     * Called in the loop of chessboard() just to make it easier to read.
     */
    public static Tag chessfield(int r, int c) {
        Tag rect = new Rect(0.05 + r, c + 0.05, 0.9, 0.9);
        if ((r % 2 + c) % 2 == 0) {
            rect = rect.withStyle("fill: black;");
        } else {
            rect = rect.withStyle("fill: white;");
        }
        return rect;
    }


    /**
     * Called by a command svg.chessboard().toString()
     * Adds stuff to the List<Tag> sublist
     */
    public static Tag chessboard() {
        Svg checkboard = new Svg(1000, 1000, "1 1 8 8");

        for (int r = 1; r <= 8; r++) {
            for (int c = 1; c <= 8; c++) {
                checkboard.add(chessfield(r, c));
            }
        }
        return checkboard;
    }

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("output.svg")) {
            writer.write(chessboard().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
