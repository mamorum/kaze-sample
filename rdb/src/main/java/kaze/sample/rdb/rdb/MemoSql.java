package kaze.sample.rdb.rdb;

import java.util.List;

import org.sql2o.Connection;

import kaze.sample.rdb.Rdb;
import kaze.sample.rdb.model.Memo;

public class MemoSql {
  public List<Memo> readAll() {
    try (Connection c = Rdb.sql.open()) {
      return c.createQuery(
          "select id, txt, updated, created from memo order by updated desc"
      ).executeAndFetch(Memo.class);
    }
  }
  private Memo read(Long id) {
    try (Connection c = Rdb.sql.open()) {
      return c.createQuery(
          "select id, txt, updated, created from memo where id = :id"
      ).addParameter("id", id).executeAndFetchFirst(Memo.class);
    }
  }
  public Memo create(String txt) {
    try (Connection c = Rdb.sql.open()) {
      long id = c.createQuery(
          "insert into memo ( txt ) values ( :txt )"
      ).addParameter("txt", txt).executeUpdate().getKey(Long.class);
      return read(id);
    }
  }
  public Memo update(Long id, String txt) {
    try (Connection c = Rdb.sql.open()) {
      c.createQuery(
          "update memo set txt = :txt, updated = current_timestamp where id = :id"
      ).addParameter("id", id).addParameter("txt", txt).executeUpdate();
      return read(id);
    }
  }
  public void delete(Long id) {
    try (Connection c = Rdb.sql.open()) {
      c.createQuery(
          "delete from memo where id = :id"
      ).addParameter("id", id).executeUpdate();
    }
  }
}
