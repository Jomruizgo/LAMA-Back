package com.hackaton.msvc_user.domain.util;


import java.util.List;

public class Constants {
    public static final String USER_NOT_FOUND_MESSAGE = "User not found";
    public static final String INVALID_SORT_FIELD_MESSAGE = "Invalid sort field. Valid fields are: name, email, documentId";
    public static final String INVALID_ORDER_MESSAGE = "Invalid sorting order. Use 'asc' or 'desc'.";
    public static final String INVALID_PAGE_MESSAGE = "Page index must be zero or positive.";
    public static final String INVALID_SIZE_MESSAGE = "Size must be greater than zero.";

    public static final String DEFAULT_USER_SORT_BY = "name"; // Campo por defecto para ordenar
    public static final List<String> VALID_USER_SORT_FIELDS = List.of("name", "email", "createdAt"); // Campos permitidos para ordenar

    private Constants(){throw new IllegalStateException("Utility class");}

    public static final int MAX_MOBILE_NUMBER_LENGTH = 13;

    public static final String API_AUTH_PATH = "api/auth";
    public static final String LOGIN_SEMI_PATH = "/login";


    public static final String API_USER_PATH = "api/user";
    public static final String CLIENT_SEMI_PATH = "/member";

    //User Details Constants
    public static final boolean DEFAULT_ACCOUNT_NON_EXPIRED = true;
    public static final boolean DEFAULT_CREDENTIAL_IS_NON_EXPIRED = true;
    public static final boolean DEFAULT_ACCOUNT_NON_LOCKED = true;


    public static final String ROLE_NOT_FOUND_MESSAGE = "Role not found";

}
