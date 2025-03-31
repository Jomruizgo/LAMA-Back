package com.hackaton.msvc_user.domain.util;

public class Regex {
    private Regex(){throw new IllegalStateException("Utility class");}

    public static final String RH_REGEX = "^(A|B|AB|O)(\\+|-)$";
    public static final String MOBILE_NUMBER_REGEX = "^\\+?\\d{1,13}$";
    public static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
}
