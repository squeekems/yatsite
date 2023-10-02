package com.squeekems.yat.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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
  public static final String FLAG_INVENTORY = "inventory";
  public static final String FLAG_SAVING_THROW = "saving-throw";

  public static int rollDice(int number, int sides) {
    int result = 0;

    for (int i = 0; i < number; i++) {
      result += new Random().nextInt(sides) + 1;
    }

    return result;
  }

  public static List<Integer> rollDiceExplicit(int number, int sides) {
    List<Integer> results = new ArrayList<>();
    int total = 0;
    for (int i = 0; i < number; i++) {
      int result = new Random().nextInt(sides) + 1;
      results.add(result);
      total += result;
    }
    results.add(total);
    return results;
  }
}
