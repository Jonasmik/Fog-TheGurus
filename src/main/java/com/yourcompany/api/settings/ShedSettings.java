package com.yourcompany.api.settings;

import java.util.ArrayList;
import java.util.List;

public class ShedSettings {
    private final int minWidth = 210;
    private final int maxWidth = 720;
    private final int intervalWidth = 30;
    private final int minLength = 150;
    private final int maxLength = 690;
    private final int intervalLength = 30;

    public List<Integer> getShedWidths() {
        List list = new ArrayList();
        for (int i = minWidth; i <= maxWidth; i+=intervalWidth) {
            list.add(i);
        }
        return list;
    }

    public List<Integer> getShedLengths() {
        List lenghtList = new ArrayList();
        for(int i = minLength; i <= maxLength; i+=intervalLength) {
            lenghtList.add(i);
        }
        return lenghtList;
    }

}
