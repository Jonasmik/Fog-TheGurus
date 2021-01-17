package com.yourcompany.api.settings;

import java.util.ArrayList;
import java.util.List;

public class CarportSettings {

    private final int maxWidth = 750;
    private final int intervalWidth = 30;
    private final int minLength = 240;
    private final int maxLength = 780;
    private final int intervalLength = 30;
    private final int angleMin = 15;
    private final int angleMax = 45;
    private final int intervalAngle = 5;


    public List<Integer> getCarportWidths() {
        List widthList = new ArrayList<>();
        int minWidth = 240;
        for (int i = minWidth; i <= maxWidth; i+=intervalWidth) {
            widthList.add(i);
        }
        return widthList;
    }

    public List<Integer> getCarportLengths() {
        List lenghtList = new ArrayList();
        for(int i = minLength; i <= maxLength; i+=intervalLength) {
            lenghtList.add(i);
        }
        return lenghtList;
    }

    public List<Integer> getCarportRoofAngle() {
        List angleList = new ArrayList();
        for (int i = angleMin; i <= angleMax; i+=intervalAngle) {
            angleList.add(i);
        }
        return angleList;
    }

}
