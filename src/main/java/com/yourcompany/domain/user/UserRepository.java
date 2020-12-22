package com.yourcompany.domain.user;

import com.yourcompany.api.factories.UserFactory;
import com.yourcompany.exceptions.user.UserValidationError;

public interface UserRepository {
    User authorizeUser(String email) throws UserValidationError;
    User createUser(UserFactory userFactory, byte[] salt, byte[] secret) throws UserValidationError;
    User findUserById(int id) throws UserValidationError;
    User updateUserSettings(int id, String email, String name) throws UserValidationError;
}
