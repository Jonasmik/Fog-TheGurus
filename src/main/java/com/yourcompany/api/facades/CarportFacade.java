package com.yourcompany.api.facades;

import com.yourcompany.api.factories.CarportFactory;
import com.yourcompany.api.settings.CarportSettings;
import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.carport.CarportRepository;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import com.yourcompany.infrastructure.database.DBCarportRepository;
import com.yourcompany.infrastructure.dbsetup.Database;

import java.util.List;

public class CarportFacade {

   private static CarportFacade instance;
   private final CarportRepository repo;

   public CarportFacade(CarportRepository repo) {
      this.repo = repo;
   }

   public static CarportFacade getInstance() {
      if (instance == null) {
         Database db = new Database();
         CarportRepository carportRepository = new DBCarportRepository(db);
         instance = new CarportFacade(carportRepository);
      }
      return instance;
   }

   public List<Carport> findAll() throws NoSuchCarportExists {
      return repo.findAll();
   }

   public Carport findById(int id) throws NoSuchCarportExists {
      return repo.findById(id);
   }

   public Carport createCarport(CarportFactory carportFactory) throws NoSuchCarportExists {
      return repo.createCarport(carportFactory);
   }

   public void updateCarport(CarportFactory carportFactory, int id) throws NoSuchCarportExists {
      repo.updateCarport(carportFactory, id);
   }

   public CarportSettings getSettings() {
      return new CarportSettings();
   }

}
