package com.yourcompany.api.facades;

import com.yourcompany.api.factories.TemplateFactory;
import com.yourcompany.domain.Template;
import com.yourcompany.domain.TemplateRepository;
import com.yourcompany.exceptions.NoSuchTemplateExists;
import com.yourcompany.infrastructure.dbsetup.Database;
import com.yourcompany.infrastructure.database.DBTemplateRepository;

import java.util.List;

public class TemplateFacade {

    /**
     * We use a facade pattern inside our com.yourcompany.api because it splits up the code so we get a better overview of the methods.
     */

    private static TemplateFacade instance;
    private final TemplateRepository repo;

    public TemplateFacade(TemplateRepository repo) {
        this.repo = repo;
    }

    public static TemplateFacade getInstance() {
        if (instance == null) {
            Database db = new Database();
            TemplateRepository templateRepository = new DBTemplateRepository(db);
            instance = new TemplateFacade(templateRepository);
        }
        return instance;
    }

    public Template create(TemplateFactory factory) {
        return null;
    }

    public Template find(int id) throws NoSuchTemplateExists {
        return null;
    }

    public List<Template> findAll() {
        return null;
    }

}
