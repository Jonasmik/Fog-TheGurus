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


    public static Bom createList(MaterialRepository repo, Carport carport, Shed shed) throws UnsatisfiableCarport {
        List<BomItem> l = new ArrayList<>();

        //understernbrædder	til	for	&	bag	ende
        //25x200	mm.	trykimp.	BrædtV

        if (shed != null) {
            try {
                l.add(new BomItem(repo.findLumber(0, 38, 73, LumberType.LÆGTE_UBH), 420, 1, "Stk", "Til z på bagside af dør"));
            } catch (NoSuchMaterialExist e) {
                throw new UnsatisfiableCarport(e);
            }
        }

        int carportLengthCalculation = (carport.getLength() / 2);

        if (carportLengthCalculation % 30 != 0) {
            carportLengthCalculation = carportLengthCalculation + 15;
        }
        int carportWidthCalculation = (carport.getWidth() / 2);

        if (carportWidthCalculation % 30 != 0) {
            carportWidthCalculation = carportWidthCalculation + 15;
        }

        try {
            l.add(new BomItem(repo.findLumber(0, 25, 200, LumberType.TRYKIMP_BRÆDT), carportWidthCalculation + 30,
                4, "Stk", "understernbrædder til for & bag ende"));
            l.add(new BomItem(repo.findLumber(0, 25, 200, LumberType.TRYKIMP_BRÆDT), carportLengthCalculation + 30,
                4, "Stk", "understernbrædder til siderne"));

        } catch (NoSuchMaterialExist e) {
            throw new UnsatisfiableCarport(e);
        }

        return new Bom(l);
    }

    @Override
    public String toString() {
        return "Bom{" +
            "bomItemList=" + bomItemList +
            '}';
    }

    private static class BomItem {

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
            return "BomItem{" +
                "material=" + material +
                ", length=" + length +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                ", description='" + description + '\'' +
                '}';
        }
    }
}
