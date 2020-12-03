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

    private static void createRims(Svg carportTopView, int xspacing, int yspacing, int lengthmm, int widthmm) {
        // rem
        Tag topRim = new Rect(xspacing, yspacing + 350, lengthmm, 45);
        Tag bottomRim = new Rect(xspacing, yspacing + widthmm - 350 - 45, lengthmm, 45);
        carportTopView.add(topRim.withStyle("fill: white; stroke: black; stroke-width: 10;"));
        carportTopView.add(bottomRim.withStyle("fill: white; stroke: black; stroke-width: 10;"));
    }

    private static void createRafters(Svg carportTopView, int lengthmm, int xspacing, int yspacing, int widthmm) {
        // Spær
        double calculateAmountOfRafters = lengthmm / (550 + 45);
        double calculateExtraRaftersSpace = ((lengthmm % (550 + 45))) / calculateAmountOfRafters;

        System.out.println(calculateExtraRaftersSpace);
        System.out.println(calculateAmountOfRafters);
        double x = 0;
        while (x <= lengthmm) {
            Tag rafter = new Rect(xspacing - 22.5 + x, yspacing, 45, widthmm);
            carportTopView.add(rafter.withStyle("fill: white; stroke: black; stroke-width: 10;"));
            x = x + 550 + calculateExtraRaftersSpace;
        }
    }

    private static void createPosts(Svg carportTopView, int yspacing, int xspacing, int widthmm, int lengthmm) {

        int pillarWidth = 97;

        // Den minimale længde før at vi er nød til at have 3 stolper per side i carporten
        // Udhæng før første stolpe + max afstanden mellem 2 stolper + udhæng bag til
        double minLengthThirdPost = 1100 + (550 * 5.5) + 275;
        double upToFirstPillar = xspacing + 1100 - (pillarWidth / 2);
        double calculateMiddlePillar = ((xspacing + lengthmm - 275 - (pillarWidth / 2)) - (upToFirstPillar)) / 2 + upToFirstPillar;

        // Stolper 1100 = udhæng foran, 48.5 = halvdelen af stolpe, 275 = centeret af mellemrummet mellem spær
        Tag topLeftPost = new Rect(upToFirstPillar, yspacing + 350, pillarWidth, pillarWidth);
        Tag topRightPost = new Rect(xspacing + lengthmm - 275 - (pillarWidth / 2), yspacing + 350, pillarWidth, pillarWidth);
        carportTopView.add(topLeftPost.withStyle("fill: white; stroke: black; stroke-width: 10;"));
        carportTopView.add(topRightPost.withStyle("fill: white; stroke: black; stroke-width: 10;"));

        Tag bottomLeftPost = new Rect(upToFirstPillar, yspacing + widthmm - 350 - pillarWidth, pillarWidth, pillarWidth);
        Tag bottomRightPost = new Rect(xspacing + lengthmm - 275 - (pillarWidth / 2), yspacing + widthmm - 350 - pillarWidth, pillarWidth, pillarWidth);
        carportTopView.add(bottomLeftPost.withStyle("fill: white; stroke: black; stroke-width: 10;"));
        carportTopView.add(bottomRightPost.withStyle("fill: white; stroke: black; stroke-width: 10;"));

        if (lengthmm >= minLengthThirdPost) {
            Tag topMiddlePost = new Rect(calculateMiddlePillar, yspacing + 350, pillarWidth, pillarWidth);
            Tag bottomMiddlePost = new Rect(calculateMiddlePillar, yspacing + widthmm - 350 - pillarWidth, pillarWidth, pillarWidth);
            carportTopView.add(topMiddlePost.withStyle("fill: white; stroke: black; stroke-width: 10;"));
            carportTopView.add(bottomMiddlePost.withStyle("fill: white; stroke: black; stroke-width: 10;"));
        }

    }

    public static Tag carportTopView(int width, int length) {
        int widthmm = width * 10;
        int lengthmm = length * 10;
        Svg carportTopView = new Svg(lengthmm, widthmm, "0 0 9000 9000");


        int xspacing = 500;
        int yspacing = 500;


        // Generate roof
        Tag roof = new Rect(xspacing, yspacing, lengthmm, widthmm);
        carportTopView.add(roof.withStyle("fill: white; stroke: black; stroke-width: 10;"));

        //Generate rims
        createRims(carportTopView, xspacing, yspacing, lengthmm, widthmm);

        //Generate rafters
        createRafters(carportTopView, lengthmm, xspacing, yspacing, widthmm);

        //Generate Posts
        createPosts(carportTopView, yspacing, xspacing, widthmm, lengthmm);

        Tag markerTest = new Marker("beginArrow", 12, 12, 0, 6, "auto", new Path("M0,6 L12,0 L12,12 L0,6"));
        carportTopView.add(markerTest);

        Tag markerTest2 = new Marker("endArrow", 12, 12, 12, 6, "auto", new Path("M0,0 L12,6 L0,12 L0,0"));
        carportTopView.add(markerTest2);

        Tag bottomLengthMeasure = new Line(xspacing, yspacing+widthmm+400, xspacing+lengthmm, yspacing+widthmm+400);
        Tag lineTest2 = new Line(xspacing, yspacing+widthmm+300, xspacing, yspacing+widthmm+500);
        Tag lineTest3 = new Line(xspacing+lengthmm, yspacing+widthmm+300, xspacing+lengthmm, yspacing+widthmm+500);
        carportTopView.add(bottomLengthMeasure.withStyle("stroke: black; stroke-width: 10; marker-start: url(#beginArrow); marker-end: url(#endArrow)"));
        carportTopView.add(lineTest2.withStyle("stroke: black; stroke-width: 15;"));
        carportTopView.add(lineTest3.withStyle("stroke: black; stroke-width: 15;"));

        /* Stern MÅSKE IKKE NØDVENDIG?
        Tag frontUnderStern = new Rect(xspacing,yspacing,25,widthmm);
        Tag backUnderStern = new Rect(xspacing,yspacing+lengthmm-25,25,widthmm);

        // Her skal vi ændre længden så den har fået trukket bredden af sternbræderne som sidder på front og bagende
        Tag topUnderStern = new Rect(xspacing+25,yspacing,lengthmm-50,25);
        Tag bottomUnderStern = new Rect(xspacing+25,yspacing+widthmm-25,lengthmm-50,25);
        */


        return carportTopView;
    }


    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("output.svg")) {
            writer.write(carportTopView(800, 550).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
