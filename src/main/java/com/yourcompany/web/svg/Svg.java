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
            rect = rect.withStyle("fill: ;");
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


    public static Tag CarportTopView(int width, int length) {
        int widthmm = width*10;
        int lengthmm = length*10;
        Svg carportTopView = new Svg(lengthmm, widthmm, "0 0 9000 9000");

        int xspacing = 500;
        int yspacing = 500;

        // Tag

        Tag roof = new Rect(xspacing, yspacing, lengthmm, widthmm);
        carportTopView.add(roof);

        // rem
        Tag topRim = new Rect(xspacing, yspacing+350, lengthmm, 45);
        Tag bottomRim = new Rect(xspacing, yspacing+widthmm-350-45, lengthmm, 45);
        carportTopView.add(topRim);
        carportTopView.add(bottomRim);

        // Stolper
        Tag topLeftPost = new Rect(xspacing+1100-48.5, yspacing+350, 97, 97);
        Tag topRightPost = new Rect(xspacing+lengthmm-275-48.5, yspacing+350, 97, 97);
        carportTopView.add(topLeftPost);
        carportTopView.add(topRightPost);

        Tag bottomLeftPost = new Rect(xspacing+1100-48.5, yspacing+width-350-97, 97, 97);
        Tag bottomRightPost = new Rect(xspacing+lengthmm-275-48.5, yspacing+width-350-97, 97, 97);
        carportTopView.add(bottomLeftPost);
        carportTopView.add(bottomRightPost);

        // Den minimale længde før at vi er nød til at have 3 stolper per side i carporten
        // Udhæng før første stolpe + max afstanden mellem 2 stolper + udhæng bag til
        double minLengthThirdPost = 1100+(550*5.5)+275;

        if (lengthmm >= minLengthThirdPost){
            Tag topMiddlePost = new Rect((xspacing+lengthmm-275-48.5)-(xspacing+1100-48.5), yspacing+350, 97, 97);
            Tag bottomMiddlePost = new Rect(xspacing+1100-48.5, yspacing+width-350-97, 97, 97);
            carportTopView.add(topMiddlePost);
            carportTopView.add(bottomMiddlePost);
        }

        // Spær
        int x = 0;
        while (x < lengthmm){
            Tag rafter = new Rect( xspacing-22.5+x, yspacing, 45, widthmm);
            carportTopView.add(rafter);
            x=x+550;
        }
        // Stern MÅSKE IKKE NØDVENDIG?
        Tag frontUnderStern = new Rect(xspacing,yspacing,25,widthmm);
        Tag backUnderStern = new Rect(xspacing,yspacing+lengthmm-25,25,widthmm);

        // Her skal vi ændre længden så den har fået trukket bredden af sternbræderne som sidder på front og bagende
        Tag topUnderStern = new Rect(xspacing+25,yspacing,lengthmm-50,25);
        Tag bottomUnderStern = new Rect(xspacing+25,yspacing+widthmm-25,lengthmm-50,25);



        return carportTopView;
    }

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("output.svg")) {
            writer.write(chessboard().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
