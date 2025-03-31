package com.hackaton.msvc_user.domain.api;

import com.hackaton.msvc_user.domain.model.PaginationModel;
import com.hackaton.msvc_user.domain.model.User;
import java.util.List;

public interface IUserServicePort {

    void saveClient(User user);

    PaginationModel<User> listUsers(String sortBy, String order, int page, int size);

    User findUserById(Long userId);

    User findUserByEmail(String email);

    void updateUser(Long userId, User user);

    void deleteUser(Long userId);

}
