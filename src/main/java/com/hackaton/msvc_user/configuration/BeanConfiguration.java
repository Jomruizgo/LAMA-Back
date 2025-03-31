package com.hackaton.msvc_user.configuration;

import com.hackaton.msvc_user.adapters.driven.encoder.EncoderAdapter;
import com.hackaton.msvc_user.adapters.driven.jpa.mysql.adapter.UserAdapter;
import com.hackaton.msvc_user.adapters.driven.jpa.mysql.mapper.IUserEmergencyContactEntityMapper;
import com.hackaton.msvc_user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.hackaton.msvc_user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.hackaton.msvc_user.adapters.driven.token.jwt.JavaJwtAdapter;
import com.hackaton.msvc_user.adapters.driven.token.jwt.JwtUserDetails;
import com.hackaton.msvc_user.domain.api.IAuthServicePort;
import com.hackaton.msvc_user.domain.api.IUserServicePort;
import com.hackaton.msvc_user.domain.api.usecase.AuthUseCase;
import com.hackaton.msvc_user.domain.api.usecase.UserUseCase;
import com.hackaton.msvc_user.domain.spi.IPasswordEncoderPort;
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
    public IUserPersistencePort userPersistencePort(IUserRepository userRepository, IUserEntityMapper userEntityMapper, IUserEmergencyContactEntityMapper userEmergencyContactEntityMapper) {
        return new UserAdapter(userRepository, userEntityMapper, userEmergencyContactEntityMapper);
    }

    @Bean
    public IPasswordEncoderPort passwordEncoderPort() {
        return new EncoderAdapter();
    }


    @Bean
    public ITokenServicePort tokenPort(JwtUserDetails jwtUserDetails) {
        return new JavaJwtAdapter(jwtUserDetails);
    }

    @Bean
    public IUserServicePort userServicePort(IUserPersistencePort userPersistencePort,
                                            IPasswordEncoderPort passwordEncoderPort) {
        return new UserUseCase(userPersistencePort, passwordEncoderPort);
    }

    @Bean
    public IAuthServicePort authServicePort(IUserPersistencePort userPersistencePort,
                                            IPasswordEncoderPort passwordEncoderPort,
                                            ITokenServicePort tokenPort) {
        return new AuthUseCase(userPersistencePort, passwordEncoderPort, tokenPort);
    }
}
