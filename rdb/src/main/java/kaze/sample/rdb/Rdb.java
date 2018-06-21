package kaze.sample.rdb;

import org.flywaydb.core.Flyway;
import org.sql2o.Sql2o;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Rdb {
  public static HikariDataSource ds;
  public static Sql2o sql;
  public static Flyway fly;
  static void init() {
    HikariConfig conf = new HikariConfig();
    conf.setJdbcUrl("jdbc:h2:~/h2db/memo");
    conf.setUsername("sa");
    ds = new HikariDataSource(conf);
    sql = new Sql2o(ds);
    fly = new Flyway();
    fly.setDataSource(ds);
    fly.migrate();
  }
}
