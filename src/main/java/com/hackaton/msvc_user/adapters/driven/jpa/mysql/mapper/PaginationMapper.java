package com.hackaton.msvc_user.adapters.driven.jpa.mysql.mapper;


import com.hackaton.msvc_user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.hackaton.msvc_user.domain.model.PaginationModel;
import com.hackaton.msvc_user.domain.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PaginationMapper {

    public static PaginationModel<User> toUserPaginationModel(Page<UserEntity> page, IUserEntityMapper mapper) {
        List<User> convertedContent = page.getContent().stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());

        return new PaginationModel<>(
                convertedContent,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast(),
                page.isFirst()
        );
    }


}
