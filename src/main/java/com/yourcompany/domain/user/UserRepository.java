package com.yourcompany.domain.user;

import com.yourcompany.api.factories.UserFactory;
import com.yourcompany.exceptions.NoSuchUserExists;
import com.yourcompany.exceptions.UserValidationError;

public interface UserRepository {
    User authorizeUser(String email) throws UserValidationError;
    User createUser(UserFactory userFactory, byte[] salt, byte[] secret) throws NoSuchUserExists;
}
