package com.hackaton.msvc_user.domain.spi;

import com.hackaton.msvc_user.domain.model.Role;

public interface IRolePersistencePort {
    void save(Role role);

    Role findRoleByName(String name);
}
