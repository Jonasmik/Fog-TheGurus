package com.yourcompany.api.settings;

import java.util.ArrayList;
import java.util.List;

public class CarportSettings {
    private final int minWidth = 240;
    private final int maxWidth = 750;
    private final int intervalWidth = 30;
    private final int minLength = 240;
    private final int maxLength = 780;
    private final int intervalLength = 30;

    public List<Integer> getCarportWidths() {
        List list = new ArrayList();
        for (int i = minWidth; i <= maxWidth; i+=intervalWidth) {
            list.add(i);
        }
        return list;
    }

    public List<Integer> getCarportLengths() {
        List lenghtList = new ArrayList();
        for(int i = minLength; i <= maxLength; i+=intervalLength) {
            lenghtList.add(i);
        }
        return lenghtList;
    }

}
