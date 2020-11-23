package com.yourcompany.infrastructure.database;

import com.yourcompany.api.factories.TemplateFactory;
import com.yourcompany.domain.Template;
import com.yourcompany.domain.TemplateRepository;
import com.yourcompany.exceptions.NoSuchTemplateExists;
import com.yourcompany.infrastructure.dbsetup.Database;

import java.util.List;

public class DBTemplateRepository implements TemplateRepository {

    /**
     * Implements an interface it choosing.
     * In here you can call the database.
     */

    private final Database db;

    public DBTemplateRepository(Database db) {
        this.db = db;
    }

    @Override
    public List<Template> findAll() {
        return null;
    }

    @Override
    public Template findById(int id) throws NoSuchTemplateExists {
        return null;
    }

    @Override
    public Template create(TemplateFactory factory) {
        return null;
    }
}
