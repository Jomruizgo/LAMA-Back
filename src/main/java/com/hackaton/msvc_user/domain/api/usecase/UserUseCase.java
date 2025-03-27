package com.hackaton.msvc_user.domain.api.usecase;

import com.hackaton.msvc_user.domain.api.IUserServicePort;
import com.hackaton.msvc_user.domain.exceptions.ObjectNotFoundException;
import com.hackaton.msvc_user.domain.model.Role;
import com.hackaton.msvc_user.domain.model.User;
import com.hackaton.msvc_user.domain.spi.IPasswordEncoderPort;
import com.hackaton.msvc_user.domain.spi.IRolePersistencePort;
import com.hackaton.msvc_user.domain.spi.IUserPersistencePort;
import com.hackaton.msvc_user.domain.util.Constants;
import com.hackaton.msvc_user.domain.util.UserRole;


public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final IPasswordEncoderPort passwordEncoder;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort, IPasswordEncoderPort passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void saveClient(User user) {
        this.saveUser(user, UserRole.CLIENT.getRoleName());

    }

    protected void saveUser(User user, String role) {
        ValidateUser validateUser= new ValidateUser(userPersistencePort);

        Role defaultRole = rolePersistencePort.findRoleByName(role);

        if(defaultRole==null){
            throw new ObjectNotFoundException(Constants.ROLE_NOT_FOUND_MESSAGE);
        }

        user.setRole(defaultRole);

        validateUser.validateUserData(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userPersistencePort.save(user);

    }

}
