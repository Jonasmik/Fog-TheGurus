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
                        + " height=\"%d\" width=\"%d\" viewBox=\"%s\""
                        + " preserveAspectRatio=\"xMidYMid meet\"",
                height,
                width,
                viewBox
        );
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

    private static void createStandardPosts(Svg carportTopView, int yspacing, int xspacing, int widthmm, int lengthmm) {

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

    private static void createArrowLines(Svg carportTopView, int xspacing, int yspacing, int widthmm, int lengthmm, int length, int width) {
        Tag markerTest = new Marker("beginArrow", 12, 12, 0, 6, "auto");
        markerTest.add(new Path("M0,6 L12,0 L12,12 L0,6"));
        carportTopView.add(markerTest);

        Tag markerTest2 = new Marker("endArrow", 12, 12, 12, 6, "auto");
        markerTest2.add(new Path("M0,0 L12,6 L0,12 L0,0"));
        carportTopView.add(markerTest2);

        //Bottom arrow
        Tag bottomLengthMeasure = new Line(xspacing, yspacing+widthmm+400, xspacing+lengthmm, yspacing+widthmm+400);
        Tag lineStopLeftBottom = new Line(xspacing, yspacing+widthmm+100, xspacing, yspacing+widthmm+500);
        Tag lineStopRightBottom = new Line(xspacing+lengthmm, yspacing+widthmm+100, xspacing+lengthmm, yspacing+widthmm+500);
        Tag bottomLengthText = new Text(xspacing+(lengthmm/2),yspacing+widthmm+300, "black", 200, "rotate(0 0,0)",+length + " cm");
        carportTopView.add(bottomLengthMeasure.withStyle("stroke: black; stroke-width: 10; marker-start: url(#beginArrow); marker-end: url(#endArrow)"));
        carportTopView.add(lineStopLeftBottom.withStyle("stroke: black; stroke-width: 15;"));
        carportTopView.add(lineStopRightBottom.withStyle("stroke: black; stroke-width: 15;"));
        carportTopView.add(bottomLengthText.withStyle("stroke-width: 15"));

        //Left outer arrow
        int halfWidth = yspacing+(widthmm/2);
        Tag leftOuterLengthMeasure = new Line(200, yspacing, 200, yspacing+widthmm);
        Tag lineStopTopOuterLeft = new Line(100, yspacing, 900, yspacing);
        Tag lineStopBottomOuterLeft = new Line(100, yspacing+widthmm, 900, yspacing+widthmm);
        Tag leftLengthOuterText = new Text(450,yspacing+(widthmm/2), "black", 200, "rotate(-90 450, " + halfWidth + ")",+width + " cm");
        carportTopView.add(leftOuterLengthMeasure.withStyle("stroke: black; stroke-width: 10; marker-start: url(#beginArrow); marker-end: url(#endArrow)"));
        carportTopView.add(lineStopTopOuterLeft.withStyle("stroke: black; stroke-width: 15"));
        carportTopView.add(lineStopBottomOuterLeft.withStyle("stroke: black; stroke-width: 15"));
        carportTopView.add(leftLengthOuterText.withStyle("stroke-width: 15"));

        //Left Inner arrow
        Tag leftInnerLengthMeasure = new Line(600, yspacing+350, 600, yspacing+widthmm-350);
        Tag lineStopTopInnerLeft = new Line(500, yspacing+350, 900, yspacing+350);
        Tag lineStopBottomInnerLeft = new Line(500, yspacing+widthmm-350, 900, yspacing+widthmm-350);
        Tag leftLengthInnerText = new Text(850,yspacing+(widthmm/2), "black", 200, "rotate(-90 850, " + halfWidth + ")",+width-70.0 + " cm");
        carportTopView.add(leftInnerLengthMeasure.withStyle("stroke: black; stroke-width: 10; marker-start: url(#beginArrow); marker-end: url(#endArrow)"));
        carportTopView.add(lineStopTopInnerLeft.withStyle("stroke: black; stroke-width: 15"));
        carportTopView.add(lineStopBottomInnerLeft.withStyle("stroke: black; stroke-width: 15"));
        carportTopView.add(leftLengthInnerText.withStyle("stroke-width: 15"));
    }

    private static void createShed(Svg carportTopView, int xspacing, int yspacing, int lengthmm, int widthmm, int shedwidth, int shedlength) {
        int shedWidthMM = shedwidth * 10;
        int shedLengthMM = shedlength * 10;
        int pillarWidth = 97;

        Tag topShedShedding = new Rect(xspacing + lengthmm - 275 - shedLengthMM + (pillarWidth/2), yspacing + 350, shedLengthMM, 45);
        Tag bottomShedShedding = new Rect(xspacing + lengthmm - 275 - shedLengthMM + (pillarWidth/2), yspacing + shedWidthMM + 350 -45, shedLengthMM, 45);
        Tag leftShedShedding = new Rect(xspacing + lengthmm - 275 - shedLengthMM + (pillarWidth/2), yspacing + 350, 45, shedWidthMM);
        Tag rightShedShedding = new Rect(xspacing + lengthmm - 275, yspacing + 350, 45, shedWidthMM);
        carportTopView.add(topShedShedding.withStyle("fill: white; stroke: black; stroke-width: 20;"));
        carportTopView.add(bottomShedShedding.withStyle("fill: white; stroke: black; stroke-width: 20;"));
        carportTopView.add(leftShedShedding.withStyle("fill: white; stroke: black; stroke-width: 20;"));
        carportTopView.add(rightShedShedding.withStyle("fill: white; stroke: black; stroke-width: 20;"));

        createShedPosts(carportTopView, xspacing, yspacing, lengthmm, widthmm, shedLengthMM, shedWidthMM, pillarWidth);
    }

    private static void createShedPosts(Svg carportTopView, int xspacing, int yspacing, int lengthmm, int widthmm, int shedLengthMM, int shedWidthMM, int pillarWidth) {

        double upToFirstShedPillar = xspacing + lengthmm - 275 - shedLengthMM + (pillarWidth/2);

        Tag leftTopPillar = new Rect(upToFirstShedPillar, yspacing + 350, pillarWidth, pillarWidth);
        Tag leftBottomPillar = new Rect(upToFirstShedPillar, yspacing + shedWidthMM + 350 - 90, pillarWidth, pillarWidth);
        carportTopView.add(leftBottomPillar.withStyle("fill: white; stroke: black; stroke-width: 10;"));
        carportTopView.add(leftTopPillar.withStyle("fill: white; stroke: black; stroke-width: 10;"));

        double minLengthThirdPost = 1100 + (550 * 5.5) + 275;
        double calculateMiddlePillar = (yspacing + 350) + (shedWidthMM/2);

        boolean doesNotNeedBottomRightPost = shedWidthMM != widthmm-70;
        if(doesNotNeedBottomRightPost) {
            Tag rightBottomPillar = new Rect(xspacing + lengthmm - 275 - (pillarWidth/2), yspacing + shedWidthMM + 350 - 90, pillarWidth, pillarWidth);
            carportTopView.add(rightBottomPillar.withStyle("fill: white; stroke: black; stroke-width: 10"));
        }

        boolean needsAThirdPost = shedWidthMM >= minLengthThirdPost;
        if (needsAThirdPost) {
            Tag leftMiddlePost = new Rect(upToFirstShedPillar, calculateMiddlePillar, pillarWidth, pillarWidth);
            Tag rightMiddlePost = new Rect(upToFirstShedPillar + shedLengthMM - pillarWidth, calculateMiddlePillar, pillarWidth, pillarWidth);
            carportTopView.add(leftMiddlePost.withStyle("fill: white; stroke: black; stroke-width: 10;"));
            carportTopView.add(rightMiddlePost.withStyle("fill: white; stroke: black; stroke-width: 10;"));
        }

        //Line arround the shed
//        Tag lineAround = new Rect()

    }

    //int shedwidth, int shedlength
    //if shed == null - shedwidth and shedlength == 0
    public static Tag carportTopView(int width, int length, int shedwidth, int shedlength) {
        int widthmm = width * 10;
        int lengthmm = length * 10;
        int sizeLength = lengthmm + 1200;
        int sizeWidth = widthmm + 1200;
        Svg carportTopView = new Svg(500, 500, "0 0 " + sizeLength + " " + sizeWidth);


        int xspacing = 1000;
        int yspacing = 500;


        // Generate roof
        Tag roof = new Rect(xspacing, yspacing, lengthmm, widthmm);
        carportTopView.add(roof.withStyle("fill: white; stroke: black; stroke-width: 10;"));

        //Generate shed if shed > 0
        if (shedlength > 0) {
            createShed(carportTopView, xspacing, yspacing, lengthmm, widthmm, shedwidth, shedlength);
        }

        //Generate rims
        createRims(carportTopView, xspacing, yspacing, lengthmm, widthmm);

        //Generate rafters
        createRafters(carportTopView, lengthmm, xspacing, yspacing, widthmm);


        //Generate Arrow lines
        createArrowLines(carportTopView, xspacing, yspacing, widthmm, lengthmm, length, width);


        //Generate Posts
        createStandardPosts(carportTopView, yspacing, xspacing, widthmm, lengthmm);

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
            writer.write(carportTopView(750, 780, 680, 270).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
