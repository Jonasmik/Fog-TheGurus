package com.yourcompany.domain.material;

import com.yourcompany.exceptions.bom.NoSuchMaterialExist;
import java.util.List;

public interface MaterialPriceRepository {

    MaterialPrice findById(int id) throws NoSuchMaterialExist;

    MaterialPrice findByName(String name) throws NoSuchMaterialExist;

    void updateMaterial(int id, double price) throws NoSuchMaterialExist;

    List<MaterialPrice> findAll() throws NoSuchMaterialExist;

}
