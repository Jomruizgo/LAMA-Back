package com.hackaton.msvc_user.domain.api.usecase;

import com.hackaton.msvc_user.domain.api.IUserServicePort;
import com.hackaton.msvc_user.domain.exceptions.ObjectNotFoundException;
import com.hackaton.msvc_user.domain.model.PaginationModel;
import com.hackaton.msvc_user.domain.model.User;
import com.hackaton.msvc_user.domain.spi.IPasswordEncoderPort;
import com.hackaton.msvc_user.domain.spi.IUserPersistencePort;
import com.hackaton.msvc_user.domain.util.Constants;
import com.hackaton.msvc_user.domain.util.UserRole;


public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoderPort passwordEncoder;

    public UserUseCase(IUserPersistencePort userPersistencePort, IPasswordEncoderPort passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void saveClient(User user) {
        this.saveUser(user, UserRole.CLIENT.getRoleName());

    }

    @Override
    public PaginationModel<User> listUsers(String sortBy, String order, int page, int size) {
        validatePaginationParameters(page, size);

        String validSortBy = getValidSortByField(sortBy);
        boolean ascending = isAscendingOrder(order);

        PaginationModel<User> users = userPersistencePort.listUsers(validSortBy, ascending, page, size);

        if (users == null || users.getContent().isEmpty()) {
            throw new ObjectNotFoundException(Constants.USER_NOT_FOUND_MESSAGE);
        }

        return users;
    }

    private void validatePaginationParameters(int page, int size) {
        if (page < 0) {
            throw new IllegalArgumentException(Constants.INVALID_PAGE_MESSAGE);
        }
        if (size <= 0) {
            throw new IllegalArgumentException(Constants.INVALID_SIZE_MESSAGE);
        }
    }

    private String getValidSortByField(String sortBy) {
        if (sortBy == null || sortBy.isBlank()) {
            return Constants.DEFAULT_USER_SORT_BY;
        }

        String normalizedSortBy = sortBy.trim().toLowerCase();

        if (!Constants.VALID_USER_SORT_FIELDS.contains(normalizedSortBy)) {
            throw new IllegalArgumentException(Constants.INVALID_SORT_FIELD_MESSAGE);
        }

        return normalizedSortBy;
    }

    private boolean isAscendingOrder(String order) {
        if (order == null || order.isBlank()) {
            return true; // Por defecto, orden ascendente
        }

        String normalizedOrder = order.trim().toLowerCase();
        if (normalizedOrder.equals("asc")) {
            return true;
        } else if (normalizedOrder.equals("desc")) {
            return false;
        } else {
            throw new IllegalArgumentException(Constants.INVALID_ORDER_MESSAGE);
        }
    }


    @Override
    public User findUserById(Long userId) {
        User user = userPersistencePort.findUserById(userId);
        if (user == null) {
            throw new ObjectNotFoundException(Constants.USER_NOT_FOUND_MESSAGE);
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userPersistencePort.findUserByEmail(email);
        if (user == null) {
            throw new ObjectNotFoundException(Constants.USER_NOT_FOUND_MESSAGE);
        }
        return user;
    }

    @Override
    public void updateUser(Long userId, User user) {
        User existingUser = userPersistencePort.findUserById(userId);
        if (existingUser == null) {
            throw new ObjectNotFoundException(Constants.USER_NOT_FOUND_MESSAGE);
        }

        user.setId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userPersistencePort.updateUser(userId, user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userPersistencePort.findUserById(userId);
        if(user==null){
            throw new ObjectNotFoundException(Constants.USER_NOT_FOUND_MESSAGE);
        }
        userPersistencePort.deleteUser(userId);
    }

    protected void saveUser(User user, String role) {
        ValidateUser validateUser= new ValidateUser(userPersistencePort);

        validateUser.validateUserData(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userPersistencePort.save(user);

    }

}
