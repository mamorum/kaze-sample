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
  public Memo create(String txt) {
    try (Connection c = Rdb.sql.open()) {
      return c.createQuery(
          "insert into memo ( txt ) values ( :txt ) returning id, txt, updated, created"
      ).addParameter("txt", txt).executeAndFetchFirst(Memo.class);
    }
  }
  public Memo update(Long id, String txt) {
    try (Connection c = Rdb.sql.open()) {
      return c.createQuery(
          "update memo set txt = :txt, updated = current_timestamp where id = :id returning id, txt, updated, created"
      ).addParameter("id", id).addParameter("txt", txt).executeAndFetchFirst(Memo.class);
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
