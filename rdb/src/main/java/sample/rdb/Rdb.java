package sample.rdb;

import org.flywaydb.core.Flyway;
import org.sql2o.Sql2o;
import org.sql2o.quirks.PostgresQuirks;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Rdb {

  public static final HikariDataSource ds;
  public static final Sql2o sql;
  public static Flyway fly;
  
  static {
    HikariConfig c = new HikariConfig("/db/hikari.properties");
    ds = new HikariDataSource(c);
    sql = new Sql2o(ds, new PostgresQuirks());
    fly = new Flyway();
    fly.setDataSource(ds);
    fly.migrate();
  }

}
