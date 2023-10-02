package com.squeekems.yat.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Constants {
  public static final String CORS_URL = "http://localhost:3000";
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

  public static final String F_SAVING = "Saving \"%s\"";
  public static final String F_DELETING = "Deleting \"%s\"";
  public static final String F_GETTING_WITH_ID = "Getting %s with id: %s";
  public static final String F_FINDING_ALL = "Finding all %s (%d)";
  public static final String SKIP_QUEUE = "Skip your next turn.";
  public static final String FLAG_SKIP = "skip";
  public static final String FLAG_ROLL = "roll";
}
