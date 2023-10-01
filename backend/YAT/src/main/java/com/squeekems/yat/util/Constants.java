package com.squeekems.yat.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    public static String jDBCDriver;
    public static String dbURL;
    public static String username;
    public static String password;

    @Autowired
    private void GetPropertiesBean(@Value("${spring.datasource.driverClassName}") String jDBCDriver,
                                   @Value("${spring.datasource.url}") String dbURL,
                                   @Value("${spring.datasource.username}") String username,
                                   @Value("${spring.datasource.password}") String password) {
        Constants.jDBCDriver = jDBCDriver;
        Constants.dbURL = dbURL;
        Constants.username = username;
        Constants.password = password;
    }

    public static final String fSaving = "Saving \"%s\"";
    public static final String fDeleting = "Deleting \"%s\"";
    public static final String fGettingWithId = "Getting %s with id: %s";
    public static final String fFindingAll = "Finding all %s";
    public static final String optionContinue = "Continue...";
    public static final String skipQueue = "Skip your next turn.";
}
