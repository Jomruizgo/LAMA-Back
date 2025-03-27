package com.hackaton.msvc_user.configuration;

import com.hackaton.msvc_user.adapters.driven.encoder.EncoderAdapter;
import com.hackaton.msvc_user.adapters.driven.jpa.mysql.adapter.RoleAdapter;
import com.hackaton.msvc_user.adapters.driven.jpa.mysql.adapter.UserAdapter;
import com.hackaton.msvc_user.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.hackaton.msvc_user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.hackaton.msvc_user.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.hackaton.msvc_user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.hackaton.msvc_user.adapters.driven.token.jwt.JavaJwtAdapter;
import com.hackaton.msvc_user.adapters.driven.token.jwt.JwtUserDetails;
import com.hackaton.msvc_user.domain.api.IAuthServicePort;
import com.hackaton.msvc_user.domain.api.IUserServicePort;
import com.hackaton.msvc_user.domain.api.usecase.AuthUseCase;
import com.hackaton.msvc_user.domain.api.usecase.UserUseCase;
import com.hackaton.msvc_user.domain.spi.IPasswordEncoderPort;
import com.hackaton.msvc_user.domain.spi.IRolePersistencePort;
import com.hackaton.msvc_user.domain.spi.ITokenServicePort;
import com.hackaton.msvc_user.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    @Bean
    public JwtUserDetails userDetail() {
        return new JwtUserDetails();
    }


    @Bean
    public IUserPersistencePort userPersistencePort(IUserRepository userRepository, IUserEntityMapper userEntityMapper) {
        return new UserAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IPasswordEncoderPort passwordEncoderPort() {
        return new EncoderAdapter();
    }

    @Bean
    public IRolePersistencePort rolePersistencePort(IRoleRepository roleRepository, IRoleEntityMapper roleEntityMapper) {
        return new RoleAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public ITokenServicePort tokenPort(JwtUserDetails jwtUserDetails) {
        return new JavaJwtAdapter(jwtUserDetails);
    }

    @Bean
    public IUserServicePort userServicePort(IUserPersistencePort userPersistencePort,
                                            IRolePersistencePort rolePersistencePort,
                                            IPasswordEncoderPort passwordEncoderPort) {
        return new UserUseCase(userPersistencePort, rolePersistencePort, passwordEncoderPort);
    }

    @Bean
    public IAuthServicePort authServicePort(IUserPersistencePort userPersistencePort,
                                            IPasswordEncoderPort passwordEncoderPort,
                                            ITokenServicePort tokenPort) {
        return new AuthUseCase(userPersistencePort, passwordEncoderPort, tokenPort);
    }
}
