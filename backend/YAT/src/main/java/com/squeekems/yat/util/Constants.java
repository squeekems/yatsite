package com.squeekems.yat.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

public class Constants {

  public static final String jDBCDriver = "spring.datasource.driverClassName";
  public static final String dbURL = "spring.datasource.url";
  public static final String username = "spring.datasource.username";
  public static final String password = "spring.datasource.password";

  public static final String fSaving = "Saving \"%s\"";
  public static final String fDeleting = "Deleting \"%s\"";
  public static final String fGettingWithId = "Getting %s with id: %s";
  public static final String fFindingAll = "Finding all %s";
  public static final String optionContinue = "Continue...";
  public static final String skipQueue = "Skip your next turn.";
}
