package sample.rdb.sql;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import sample.rdb.Rdb;
import sample.rdb.model.Tsubuyaki;

public class TsubuyakiRepository {
  
  Sql2o sql = Rdb.sql; 
  
  public List<Tsubuyaki> all() {
    try (Connection c = sql.open()) {
      return c.createQuery(
          "select id, txt, updated_time updatedTime, created_time createdTime from tsubuyaki order by updated_time desc"
      ).executeAndFetch(Tsubuyaki.class);
    }
  }
  
  public Tsubuyaki create(String txt) {
    try (Connection c = sql.open()) {
      return c.createQuery(
          "insert into tsubuyaki ( txt ) values ( :txt ) returning id, txt, updated_time updatedtime, created_time createdtime"
      ).addParameter("txt", txt).executeAndFetchFirst(Tsubuyaki.class);
    }
  }

  public void update(Long id, String txt) {
    try (Connection c = sql.open()) {
      c.createQuery(
          "update tsubuyaki set txt = :txt, updated_time = current_timestamp where id = :id"
      ).addParameter("id", id).addParameter("txt", txt).executeUpdate();
    }
  }

  public void delete(Long id) {
    try (Connection c = sql.open()) {
      c.createQuery(
          "delete from tsubuyaki where id = :id"
      ).addParameter("id", id).executeUpdate();
    }
  }
}
