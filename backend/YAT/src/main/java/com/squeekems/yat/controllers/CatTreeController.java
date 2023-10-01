package com.squeekems.yat.controllers;

import com.squeekems.yat.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

@RestController
@RequestMapping("/editor")
public class CatTreeController {
  Logger log = LoggerFactory.getLogger(CatTreeController.class);

  @RequestMapping("/exportDb")
  public void exportDb() {
    ourBest("SCRIPT TO 'data-new.sql'");
  }

  @RequestMapping("/dropColumn")
  public void dropColumn(@RequestParam("tableName") String tableName,
                         @RequestParam("columnName") String columnName) {
    ourBest("ALTER TABLE " + tableName + " DROP COLUMN IF EXISTS " + columnName + ";");
  }

  @RequestMapping("/createColumn")
  public void createColumn(@RequestParam("tableName") String tableName,
                           @RequestParam("columnName") String columnName,
                           @RequestParam("dataType") String dataType) {
    ourBest("ALTER TABLE " + tableName + " ADD " + columnName + " " + dataType + ";");
  }

  @RequestMapping("/addRow")
  public void addRow(@RequestBody Map<String, Map<String, String>> rowData) {
    Map<String, String> data = null;
    String tableName = null;
    StringBuilder sql = new StringBuilder("INSERT INTO ");
    int counter = 1;

    for (Map.Entry<String, Map<String, String>> entry : rowData.entrySet()) {
      tableName = entry.getKey();
      data = entry.getValue();
    }

    String tableNameSingular = tableName.substring(0, tableName.length() - 1);

    ourBest("ALTER TABLE " + tableName + " ALTER COLUMN " + tableNameSingular + "_id RESTART WITH (SELECT MAX(" + tableNameSingular + "_id) FROM " + tableName + ") + 1");

    sql.append(tableName).append('(');

    for (Map.Entry<String, String> entry : data.entrySet()) {
      counter++;
        sql.append(entry.getKey());
        if (counter <= data.size()) {
          sql.append(", ");
        }
    }
    counter = 1;

    sql.append(") VALUES (");

    for (Map.Entry<String, String> entry : data.entrySet()) {
      counter++;
      sql.append(entry.getValue());
      if (counter <= data.size()) {
        sql.append(", ");
      }
    }

    sql.append(");");

    ourBest(sql.toString());
  }

  public void ourBest(String sql) {
    String jDBCDriver = "org.h2.Driver";
    String dbURL = "jdbc:h2:mem:yatpoc";
    String username = "sa";
    String password = "password";
    Connection con = null;
    Statement statement = null;
    try {
      Class.forName(jDBCDriver);
      con = DriverManager.getConnection(dbURL, username, password);
      statement = con.createStatement();
      statement.execute(sql);
      log.info("Our Best was '" + sql + "'");
      statement.close();
      con.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
