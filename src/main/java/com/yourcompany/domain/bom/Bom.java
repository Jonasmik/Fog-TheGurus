package com.yourcompany.domain.bom;

import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.material.Material;
import com.yourcompany.domain.material.MaterialRepository;
import com.yourcompany.domain.material.MaterialRepository.LumberType;
import com.yourcompany.domain.shed.Shed;
import com.yourcompany.exceptions.bom.NoSuchMaterialExist;
import com.yourcompany.exceptions.bom.UnsatisfiableCarport;

import java.util.ArrayList;
import java.util.List;

public class Bom {

    private final List<BomItem> bomItemList;

    public Bom(List<BomItem> bomItemList) {
        this.bomItemList = bomItemList;
    }

    public List<BomItem> getBomItemList() {
        return bomItemList;
    }

    @Override
    public String toString() {
        return "Bom{" + "bomItemList=" + bomItemList + '}';
    }

    public static Bom createList(MaterialRepository repo, Carport carport, Shed shed)
        throws UnsatisfiableCarport, NoSuchMaterialExist {
        List<BomItem> l = new ArrayList<>();

        // understernbrædder til for & bag ende
        // 25x200 mm. trykimp. BrædtV
        int rimCarport = carport.getLength();
        boolean skurDel = false;

        int carportLengthCalculation = calculateWood(carport.getLength());
        int carportWidthCalculation = calculateWood(carport.getWidth());

        if (shed != null) {
            skurDel = true;
        }

        l.add(new BomItem(repo.findLumber(0, 25, 200, LumberType.TRYKIMP_BRÆDT), carportWidthCalculation + 30, 4,
            "Stk", "understernbrædder til for & bag ende"));
        l.add(new BomItem(repo.findLumber(0, 25, 200, LumberType.TRYKIMP_BRÆDT), carportLengthCalculation + 30, 4,
            "Stk", "understernbrædder til siderne"));
        l.add(new BomItem(repo.findLumber(0, 25, 125, LumberType.TRYKIMP_BRÆDT), carportLengthCalculation + 30, 2,
            "Stk", "oversternbrædder til forenden"));
        l.add(new BomItem(repo.findLumber(0, 25, 125, LumberType.TRYKIMP_BRÆDT), carportLengthCalculation + 30, 4,
            "Stk", "oversternbrædder til siderne"));
        if (!skurDel) {
            l.add(new BomItem(repo.findLumber(0, 45, 195, LumberType.SPÆRTRÆ_UBH), carport.getLength() + 30, 2,
                "Stk", "Remme i sider, sadles ned i stolper"));
        }

        if (shed != null) {
            int rimShed = shed.getLength() * 2;

            int minimumLengthThirdPost = (int) (1100 + (550 * 5.5) + 275) / 10;

            int looseWoodLength = shed.getLength();
            int looseWoodWidth = shed.getWidth();

            if (looseWoodWidth >= minimumLengthThirdPost) {
                l.add(new BomItem(repo.findLumber(0, 45, 95, LumberType.REGLAR_UB), looseWoodWidth / 2, 12, "Stk",
                    "løsholter til skurgavle"));
            } else {
                l.add(new BomItem(repo.findLumber(0, 45, 95, LumberType.REGLAR_UB), looseWoodWidth, 6, "Stk",
                    "løsholter til skurgavle"));
            }

            l.add(new BomItem(repo.findLumber(0, 45, 95, LumberType.REGLAR_UB), looseWoodLength, 4, "Stk",
                "løsholter til sider"));

            if (shed.getWidth() >= carport.getWidth() - 70) {
                rimShed = calculateWood(rimShed * 2);

                l.add(new BomItem(repo.findLumber(0, 45, 195, LumberType.SPÆRTRÆ_UBH), rimShed + 30, 2, "Stk",
                    "Remme i sider, sadles ned i stolper (skur del, deles)"));

            } else {
                rimCarport = calculateWood((rimCarport - (rimShed / 2)) * 2);
                rimShed = calculateWood(rimShed);

                //k Remme	i	sider,	sadles	ned	i	stolper
                l.add(new BomItem(repo.findLumber(0, 45, 195, LumberType.SPÆRTRÆ_UBH), rimShed + 30, 1, "Stk",
                    "Remme i sider, sadles ned i stolper"));
                l.add(new BomItem(repo.findLumber(0, 45, 195, LumberType.SPÆRTRÆ_UBH), rimCarport + 30, 1, "Stk",
                    "Remme i sider, sadles ned i stolper, skur del"));

                l.add(new BomItem(repo.findLumber(0, 45, 195, LumberType.SPÆRTRÆ_UBH), carport.getLength() + 30, 1, "Stk",
                    "Remme i sider, sadles ned i stolper, uden skur"));
            }
            l.add(new BomItem(repo.findLumber(0, 38, 73, LumberType.LÆGTE_UBH), 420, 1, "Stk",
                "Til z på bagside af dør"));

            int shedOuterBoard = ((shed.getLength() * 2) + (shed.getWidth() * 2)) / 7;
            l.add(new BomItem(repo.findLumber(0, 19, 100, LumberType.TRYKIMP_BRÆDT), 210, shedOuterBoard, "Stk",
                "til beklædning af skur1 på 2"));

        }
        //Plastmo	Ecolite	blåtonet 600 6 Stk tagplader	monteres	på	spær
        //Plastmogtag bredde er 109, med 9 cm overlap
        int plastmogWidth = 100;
        int plastmogLength = carport.getLength();
        int plastmogCalculation = (carport.getWidth() / plastmogWidth) + 0;
        if (plastmogLength > 600) {

            if (carport.getLength() % plastmogLength != 0) {
                plastmogCalculation = (carport.getWidth() / plastmogWidth) + 1;
            }

            l.add(new BomItem(repo.findLumber(0, 19, 100, LumberType.TRYKIMP_BRÆDT), 240, plastmogCalculation,  "Stk",
                "til beklædning af skur1 på 2"));
            l.add(new BomItem(repo.findLumber(0, 19, 100, LumberType.TRYKIMP_BRÆDT), 600, plastmogCalculation,  "Stk",
                "til beklædning af skur1 på 2"));


        } else {
            l.add(new BomItem(repo.findLumber(0, 19, 100, LumberType.TRYKIMP_BRÆDT), carport.getLength(), plastmogCalculation,  "Stk",
                "til beklædning af skur1 på 2"));

        }
        return new Bom(l);
    }

    private static int calculateWood(int number) {
        int carportCalculation = (number / 2);

        if (carportCalculation % 30 != 0) {
            carportCalculation = carportCalculation + 15;
        }
        return carportCalculation;
    }

    public static class BomItem {

        private final Material material;
        private final int length;
        private final int amount;
        private final String unit;
        private final String description;

        private BomItem(Material material, int length, int amount, String unit, String description) {
            this.material = material;
            this.length = length;
            this.amount = amount;
            this.unit = unit;
            this.description = description;
        }

        @Override
        public String toString() {
            return "BomItem{" + "material=" + material + ", length=" + length + ", amount=" + amount + ", unit='" + unit
                + '\'' + ", description='" + description + '\'' + '}';
        }

        public Material getMaterial() {
            return material;
        }

        public int getLength() {
            return length;
        }

        public int getAmount() {
            return amount;
        }

        public String getUnit() {
            return unit;
        }

        public String getDescription() {
            return description;
        }
    }
}
