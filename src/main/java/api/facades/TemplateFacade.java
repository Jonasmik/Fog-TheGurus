package api.facades;

import infrastructure.dbsetup.Database;
import infrastructure.database.DBTemplate;

public class TemplateFacade {

    private static TemplateFacade instance;
    private final DBTemplate dbTemplate;

    public TemplateFacade(DBTemplate dbTemplate) {
        this.dbTemplate = dbTemplate;
    }

    public static TemplateFacade getInstance(){
        Database db = new Database();
        if(instance == null){
            instance = new TemplateFacade(new DBTemplate(db));
        }
        return instance;
    }
}
