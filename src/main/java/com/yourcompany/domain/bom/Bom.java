package com.yourcompany.domain.bom;

import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.material.Material;
import com.yourcompany.domain.material.MaterialPrice;
import com.yourcompany.domain.material.MaterialRepository;
import com.yourcompany.domain.material.MaterialRepository.LumberType;
import com.yourcompany.domain.shed.Shed;
import com.yourcompany.exceptions.bom.NoSuchMaterialExist;

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
