package com.hackaton.msvc_user.adapters.driving.http.controller;

import com.hackaton.msvc_user.adapters.driving.http.dto.AddUserRequestDto;
import com.hackaton.msvc_user.adapters.driving.http.dto.PaginationResponseDto;
import com.hackaton.msvc_user.adapters.driving.http.dto.UpdateUserRequestDto;
import com.hackaton.msvc_user.adapters.driving.http.dto.UserResponseDto;
import com.hackaton.msvc_user.adapters.driving.http.mapper.request.IUserRequestMapper;
import com.hackaton.msvc_user.adapters.driving.http.mapper.response.IUserResponseMapper;
import com.hackaton.msvc_user.adapters.driving.http.mapper.response.PaginationResponseMapper;
import com.hackaton.msvc_user.domain.api.IUserServicePort;
import com.hackaton.msvc_user.domain.model.PaginationModel;
import com.hackaton.msvc_user.domain.model.User;
import com.hackaton.msvc_user.domain.util.Constants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constants.API_USER_PATH)
public class UserController {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    public UserController(IUserServicePort userServicePort, IUserRequestMapper userRequestMapper, IUserResponseMapper userResponseMapper) {
        this.userServicePort = userServicePort;
        this.userRequestMapper = userRequestMapper;
        this.userResponseMapper = userResponseMapper;
    }


    @PostMapping(Constants.CLIENT_SEMI_PATH)
    public ResponseEntity<Void> addClient(@Valid  @RequestBody AddUserRequestDto request){
        userServicePort.saveClient(userRequestMapper.addRequestDtotoModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(Constants.CLIENT_SEMI_PATH)
    public ResponseEntity<PaginationResponseDto<UserResponseDto>> listUsers(@RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
                                                                            @RequestParam(value = "order", defaultValue = "asc") String order,
                                                                            @RequestParam(value = "page", defaultValue = "0") int page,
                                                                            @RequestParam(value = "size", defaultValue = "10") int size) {
        PaginationModel<User> userPagination = userServicePort.listUsers(sortBy, order, page, size);
        PaginationResponseDto<UserResponseDto> response = PaginationResponseMapper.toPaginationResponseDto(userPagination, userResponseMapper);

        return ResponseEntity.ok(response);
    }

    @GetMapping(Constants.CLIENT_SEMI_PATH + "/{userId}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long userId) {
        UserResponseDto response = userResponseMapper.toUserResponseDto(userServicePort.findUserById(userId));
        return ResponseEntity.ok(response);
    }

    @GetMapping(Constants.CLIENT_SEMI_PATH + "/email/{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userServicePort.findUserByEmail(email));
    }

    @PutMapping(Constants.CLIENT_SEMI_PATH + "/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @Valid @RequestBody UpdateUserRequestDto request) {
        userServicePort.updateUser(userId, userRequestMapper.updateRequestDtotoModel(request));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(Constants.CLIENT_SEMI_PATH + "/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userServicePort.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
