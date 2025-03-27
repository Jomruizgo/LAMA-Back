package com.hackaton.msvc_user.domain.api;

import com.hackaton.msvc_user.domain.model.AuthUser;

public interface IAuthServicePort {
    AuthUser login(AuthUser authUser);
}
