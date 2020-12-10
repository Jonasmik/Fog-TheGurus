package com.yourcompany.api.facades;

import com.yourcompany.api.factories.ShedFactory;
import com.yourcompany.domain.shed.Shed;
import com.yourcompany.domain.shed.ShedRepository;
import com.yourcompany.exceptions.shed.NoSuchShedExists;
import com.yourcompany.infrastructure.database.DBShedRepository;
import com.yourcompany.infrastructure.dbsetup.Database;

public class ShedFacade {

    private static ShedFacade instance;
    private final ShedRepository repo;

    public ShedFacade(ShedRepository repo) {
        this.repo = repo;
    }

    public static ShedFacade getInstance() {
        if (instance == null) {
            Database db = new Database();
            ShedRepository ShedRepository = new DBShedRepository(db);
            instance = new ShedFacade(ShedRepository);
        }
        return instance;
    }

    public Shed findById(int id) throws NoSuchShedExists {
        return repo.findById(id);
    }

    public Shed createShed(ShedFactory shedFactory) throws NoSuchShedExists {
        return repo.createShed(shedFactory);
    }

    public Shed findByCarportId(int id) throws NoSuchShedExists {
        return repo.findByCarportId(id);
    }

}
