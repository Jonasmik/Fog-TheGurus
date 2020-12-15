package com.yourcompany.domain.bom;

import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.material.Material;
import com.yourcompany.domain.material.MaterialPrice;
import com.yourcompany.domain.material.MaterialPriceRepository;
import com.yourcompany.domain.material.MaterialRepository;
import com.yourcompany.domain.material.MaterialRepository.LumberType;
import com.yourcompany.domain.shed.Shed;
import com.yourcompany.exceptions.bom.NoSuchMaterialExist;

import com.yourcompany.infrastructure.database.DBMaterialPriceDB;
import com.yourcompany.infrastructure.dbsetup.Database;
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

    public static Bom createList(MaterialRepository materialRepository, Carport carport, Shed shed)
        throws NoSuchMaterialExist {
        List<BomItem> l = new ArrayList<>();

        // understernbrædder til for & bag ende
        // 25x200 mm. trykimp. BrædtV
        int rimCarport = carport.getLength();
        boolean skurDel = false;
        int minimumLengthThirdPost = (int) (1100 + (550 * 5.5) + 275) / 10;

        int carportLengthCalculation = calculateWood(carport.getLength());
        int carportWidthCalculation = calculateWood(carport.getWidth());

        int stolpeAntal = 4;

        if (carport.getLength() >= minimumLengthThirdPost) {
            stolpeAntal = stolpeAntal + 2;
        }

        if (shed.getLength() > 0) {
            skurDel = true;
        }

        l.add(new BomItem(materialRepository.findLumber(0, 25, 200, LumberType.TRYKIMP_BRÆDT), carportWidthCalculation + 30, 4,
            "Stk", "understernbrædder til for & bag ende", findPrice(LumberType.TRYKIMP_BRÆDT, carportWidthCalculation + 30, 4)));
        l.add(new BomItem(materialRepository.findLumber(0, 25, 200, LumberType.TRYKIMP_BRÆDT), carportLengthCalculation + 30, 4,
            "Stk", "understernbrædder til siderne", findPrice(LumberType.TRYKIMP_BRÆDT, carportLengthCalculation + 30, 4)));
        l.add(new BomItem(materialRepository.findLumber(0, 25, 125, LumberType.TRYKIMP_BRÆDT), carportLengthCalculation + 30, 2,
            "Stk", "oversternbrædder til forenden", findPrice(LumberType.TRYKIMP_BRÆDT, carportLengthCalculation + 30, 2)));
        l.add(new BomItem(materialRepository.findLumber(0, 25, 125, LumberType.TRYKIMP_BRÆDT), carportLengthCalculation + 30, 4,
            "Stk", "oversternbrædder til siderne", findPrice(LumberType.TRYKIMP_BRÆDT, carportLengthCalculation + 30, 4)));
        if (!skurDel) {
            l.add(new BomItem(materialRepository.findLumber(0, 45, 195, LumberType.SPÆRTRÆ_UBH), carport.getLength() + 30, 2,
                "Stk", "Remme i sider, sadles ned i stolper", findPrice(LumberType.SPÆRTRÆ_UBH, carport.getLength() + 30, 2)));
        }
        double calculateAmountOfRafters = carport.getLength() / (55 + 4.5);
        double calculateExtraRaftersSpace = ((carport.getLength() % (55 + 4.5))) / calculateAmountOfRafters;
        int amountOfRafters = (int) (carport.getLength() / (55 + 4.5) + calculateExtraRaftersSpace);

        l.add(new BomItem(materialRepository.findLumber(0, 45, 195, LumberType.SPÆRTRÆ_UBH), carport.getLength(), amountOfRafters,
            "Stk", "Spær, monteres på rem", findPrice(LumberType.SPÆRTRÆ_UBH, carport.getLength(), amountOfRafters)));

        if (skurDel) {
            int rimShed = shed.getLength() * 2;
            stolpeAntal = stolpeAntal + 4;

            int looseWoodLength = shed.getLength();
            int looseWoodWidth = shed.getWidth();

            if (looseWoodWidth >= minimumLengthThirdPost) {
                l.add(new BomItem(materialRepository.findLumber(0, 45, 95, LumberType.REGLAR_UB), looseWoodWidth / 2, 12, "Stk",
                    "løsholter til skurgavle", findPrice(LumberType.REGLAR_UB, looseWoodWidth / 2, 12)));
                stolpeAntal = stolpeAntal + 2;
            } else {
                l.add(new BomItem(materialRepository.findLumber(0, 45, 95, LumberType.REGLAR_UB), looseWoodWidth, 6, "Stk",
                    "løsholter til skurgavle", findPrice(LumberType.REGLAR_UB, looseWoodWidth / 2, 6)));
            }

            l.add(new BomItem(materialRepository.findLumber(0, 45, 95, LumberType.REGLAR_UB), looseWoodLength, 4, "Stk",
                "løsholter til sider", findPrice(LumberType.REGLAR_UB, looseWoodLength, 4)));

            if (shed.getWidth() >= carport.getWidth() - 70) {

                l.add(
                    new BomItem(materialRepository.findLumber(0, 45, 195, LumberType.SPÆRTRÆ_UBH),
                        carport.getLength() - shed.getLength() + 30,
                        2, "Stk",
                        "Remme i sider, sadles ned i stolper",
                        findPrice(LumberType.SPÆRTRÆ_UBH, carport.getLength() - shed.getLength() + 30, 2)));
                l.add(new BomItem(materialRepository.findLumber(0, 45, 195, LumberType.SPÆRTRÆ_UBH), rimShed + 30, 1, "Stk",
                    "Remme i sider, sadles ned i stolper (skur del, deles)", findPrice(LumberType.SPÆRTRÆ_UBH, rimShed + 30, 1)));
                stolpeAntal = stolpeAntal - 1;


            } else {
                rimCarport = calculateWood((rimCarport - (rimShed / 2)) * 2);
                rimShed = calculateWood(rimShed);

                //k Remme	i	sider,	sadles	ned	i	stolper
                l.add(new BomItem(materialRepository.findLumber(0, 45, 195, LumberType.SPÆRTRÆ_UBH), rimShed + 30, 1, "Stk",
                    "Remme i sider, sadles ned i stolper", findPrice(LumberType.SPÆRTRÆ_UBH, rimShed + 30, 1)));
                l.add(new BomItem(materialRepository.findLumber(0, 45, 195, LumberType.SPÆRTRÆ_UBH), rimCarport + 30, 1, "Stk",
                    "Remme i sider, sadles ned i stolper, skur del", findPrice(LumberType.SPÆRTRÆ_UBH, rimCarport + 30, 1)));

                l.add(new BomItem(materialRepository.findLumber(0, 45, 195, LumberType.SPÆRTRÆ_UBH), carport.getLength() + 30, 1,
                    "Stk",
                    "Remme i sider, sadles ned i stolper, uden skur", findPrice(LumberType.SPÆRTRÆ_UBH, carport.getLength() + 30, 1)));
            }
            l.add(new BomItem(materialRepository.findLumber(0, 38, 73, LumberType.LÆGTE_UBH), 420, 1, "Stk",
                "Til z på bagside af dør", findPrice(LumberType.LÆGTE_UBH, 420, 1)));

            int shedOuterBoard = ((shed.getLength() * 2) + (shed.getWidth() * 2)) / 7;
            l.add(new BomItem(materialRepository.findLumber(0, 19, 100, LumberType.TRYKIMP_BRÆDT), 210, shedOuterBoard, "Stk",
                "til beklædning af skur1 på 2", findPrice(LumberType.TRYKIMP_BRÆDT, 210, shedOuterBoard)));

        }

        l.add(new BomItem(materialRepository.findLumber(0, 19, 100, LumberType.TRYKIMP_BRÆDT), carportLengthCalculation + 30, 4,
            "Stk", "vandbrædt på stern til siderne", findPrice(LumberType.TRYKIMP_BRÆDT, carportLengthCalculation + 30, 4)));
        l.add(new BomItem(materialRepository.findLumber(0, 19, 100, LumberType.TRYKIMP_BRÆDT), carportLengthCalculation + 30, 2,
            "Stk", "vandbrædt på stern til forenden", findPrice(LumberType.TRYKIMP_BRÆDT, carportLengthCalculation + 30, 2)));
        l.add(new BomItem(materialRepository.findLumber(0, 97, 97, LumberType.TRYKIMP_STOLPE), 300, stolpeAntal, "Stk",
            "Stolper nedgraves 90cm. i jord", findPrice(LumberType.TRYKIMP_STOLPE, 300, stolpeAntal)));

        //Plastmo	Ecolite	blåtonet 600 6 Stk tagplader	monteres	på	spær
        //Plastmogtag bredde er 109, med 9 cm overlap
        int plastmogWidth = 100;
        int plastmogLength = carport.getLength();
        int plastmogCalculation = (carport.getWidth() / plastmogWidth) + 0;
        if (plastmogLength >= 600) {

            if (carport.getLength() % plastmogLength != 0) {
                plastmogCalculation = (carport.getWidth() / plastmogWidth) + 1;
            }

            l.add(
                new BomItem(materialRepository.findLumber(0, plastmogWidth, 16, LumberType.PLASTMO_ECOLITE_BLÅTONET), 240,
                    plastmogCalculation,
                    "Stk",
                    "tagplader monteres på spær", findPrice(LumberType.PLASTMO_ECOLITE_BLÅTONET, 240, plastmogCalculation)));
            l.add(
                new BomItem(materialRepository.findLumber(0, plastmogWidth, 16, LumberType.PLASTMO_ECOLITE_BLÅTONET), 600,
                    plastmogCalculation,
                    "Stk",
                    "tagplader monteres på spær", findPrice(LumberType.PLASTMO_ECOLITE_BLÅTONET, 600, plastmogCalculation)));


        } else {
            l.add(new BomItem(materialRepository.findLumber(0, plastmogWidth, 16, LumberType.PLASTMO_ECOLITE_BLÅTONET),
                carport.getLength(),
                plastmogCalculation,
                "Stk",
                "tagplader monteres på spær", findPrice(LumberType.PLASTMO_ECOLITE_BLÅTONET, carport.getLength(), plastmogCalculation)));

        }

        return new Bom(l);
    }

    private static double findPrice(LumberType trykimpBrædt, int lenght, int amount) {
        MaterialPriceRepository materialPriceRepository = new DBMaterialPriceDB(new Database());
        MaterialPrice material = null;
        try {
            material = materialPriceRepository.findByName(String.valueOf(trykimpBrædt));
        } catch (NoSuchMaterialExist noSuchMaterialExist) {
            noSuchMaterialExist.printStackTrace();
        }

        double price = ((lenght / 100) * material.getPrice()) * amount;
        return round(price, 1);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
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
        private final double price;

        public BomItem(Material material, int length, int amount, String unit, String description, double price) {
            this.material = material;
            this.length = length;
            this.amount = amount;
            this.unit = unit;
            this.description = description;
            this.price = price;
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

        public double getPrice() {
            return price;
        }
    }
}
