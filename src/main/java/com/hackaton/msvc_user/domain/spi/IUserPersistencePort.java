package com.hackaton.msvc_user.domain.spi;

import com.hackaton.msvc_user.domain.model.PaginationModel;
import com.hackaton.msvc_user.domain.model.User;


public interface IUserPersistencePort {
    void save(User user);

    User findUserByEmail(String email);

    PaginationModel listUsers(String sortBy, boolean ascending, int page, int size);

    User findUserByDocumentId(Long documentId);

    User findUserById(Long userId);

    void updateUser(Long userId, User user);

    void deleteUser(Long userId);

}
