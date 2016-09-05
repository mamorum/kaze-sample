package kaze.sample.rdb;

import org.flywaydb.core.Flyway;
import org.sql2o.Sql2o;
import org.sql2o.quirks.PostgresQuirks;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import kaze.Conf;

public class Rdb {

  public static HikariDataSource ds;
  public static Sql2o sql;
  public static Flyway fly;
  
  static void init() {
    String path = Conf.get("_hikari.path");
    HikariConfig c = new HikariConfig(path);
    ds = new HikariDataSource(c);
    sql = new Sql2o(ds, new PostgresQuirks());
    fly = new Flyway();
    fly.setDataSource(ds);
    fly.migrate();
  }

}
