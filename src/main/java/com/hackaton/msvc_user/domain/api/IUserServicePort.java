package com.hackaton.msvc_user.domain.api;

import com.hackaton.msvc_user.domain.model.User;

public interface IUserServicePort {

    void saveClient(User user);

}
