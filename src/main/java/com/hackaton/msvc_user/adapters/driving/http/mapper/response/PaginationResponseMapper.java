package com.hackaton.msvc_user.adapters.driving.http.mapper.response;

import com.hackaton.msvc_user.adapters.driving.http.dto.PaginationResponseDto;
import com.hackaton.msvc_user.adapters.driving.http.dto.UserResponseDto;
import com.hackaton.msvc_user.domain.model.PaginationModel;
import com.hackaton.msvc_user.domain.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class PaginationResponseMapper {
    public static PaginationResponseDto<UserResponseDto> toPaginationResponseDto(PaginationModel<User> paginationModel, IUserResponseMapper userResponseMapper) {
        List<UserResponseDto> convertedContent = paginationModel.getContent().stream()
                .map(userResponseMapper::toUserResponseDto)
                .toList();

        PaginationResponseDto<UserResponseDto> paginationResponseDto = new PaginationResponseDto<>();
        paginationResponseDto.setContent(convertedContent);
        paginationResponseDto.setPageNumber(paginationModel.getPageNumber());
        paginationResponseDto.setPageSize(paginationModel.getPageSize());
        paginationResponseDto.setTotalElements(paginationModel.getTotalElements());
        paginationResponseDto.setTotalPages(paginationModel.getTotalPages());
        paginationResponseDto.setLast(paginationModel.isLast());
        paginationResponseDto.setFirst(paginationModel.isFirst());
        return paginationResponseDto;
    }
}
