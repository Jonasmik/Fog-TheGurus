package com.yourcompany.api.facades;

import com.yourcompany.domain.material.MaterialPrice;
import com.yourcompany.domain.material.MaterialPriceRepository;
import com.yourcompany.exceptions.bom.NoSuchMaterialExist;
import com.yourcompany.infrastructure.database.DBMaterialPriceDB;
import com.yourcompany.infrastructure.dbsetup.Database;
import java.util.List;

public class MaterialPriceFacade {

    private static MaterialPriceFacade instance;
    private final MaterialPriceRepository repo;

    public MaterialPriceFacade(MaterialPriceRepository repo) {
        this.repo = repo;
    }

    public static MaterialPriceFacade getInstance() {
        if (instance == null) {
            Database db = new Database();
            MaterialPriceRepository materialPriceRepository = new DBMaterialPriceDB(db);
            instance = new MaterialPriceFacade(materialPriceRepository);
        }
        return instance;
    }

    public MaterialPrice findById(int id) throws NoSuchMaterialExist {
        return repo.findById(id);
    }

    public MaterialPrice findByName(String name) throws NoSuchMaterialExist {
        return repo.findByName(name);
    }

    public void updateMaterial(int id, double price) throws NoSuchMaterialExist {
        repo.updateMaterial(id, price);
    }

    public List<MaterialPrice> findAll() throws NoSuchMaterialExist {
        return repo.findAll();
    }
}
