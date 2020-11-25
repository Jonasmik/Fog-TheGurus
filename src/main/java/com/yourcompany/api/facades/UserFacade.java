package com.yourcompany.api.facades;

import com.yourcompany.api.factories.TemplateFactory;
import com.yourcompany.api.factories.UserFactory;
import com.yourcompany.domain.Template;
import com.yourcompany.domain.TemplateRepository;
import com.yourcompany.domain.user.User;
import com.yourcompany.domain.user.UserRepository;
import com.yourcompany.exceptions.NoSuchTemplateExists;
import com.yourcompany.exceptions.UserValidationError;
import com.yourcompany.infrastructure.database.DBTemplateRepository;
import com.yourcompany.infrastructure.database.DBUserRepository;
import com.yourcompany.infrastructure.dbsetup.Database;

import javax.validation.ValidationException;
import java.util.List;

public class UserFacade {
    /**
     * We use a facade pattern inside our com.yourcompany.api because it splits up the code so we get a better overview of the methods.
     */

    private static UserFacade instance;
    private final UserRepository repo;

    public UserFacade(UserRepository repo) {
        this.repo = repo;
    }

    public static UserFacade getInstance() {
        if (instance == null) {
            Database db = new Database();
            UserRepository UserRepository = new DBUserRepository(db);
            instance = new UserFacade(UserRepository);
        }
        return instance;
    }

    public User createUser(UserFactory factory) {
        return null;
    }

    public User authorizeUser(String email, String password) throws UserValidationError {
        User user = repo.authorizeUser(email);
        if(user == null){
            return null;
        }

        try {
            if (user.isPasswordCorrect(password)) {
                return user;
            } else {
                user = null;
                return user;
            }
        } catch (UserValidationError validationError) {
            validationError.printStackTrace();
        }

        return null;
    }

    public List<User> findAll() {
        return null;
    }

}

