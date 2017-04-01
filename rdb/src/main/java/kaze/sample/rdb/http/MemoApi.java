package kaze.sample.rdb.http;

import kaze.http.Req;
import kaze.http.Res;
import kaze.sample.rdb.model.Memo;
import kaze.sample.rdb.sql.MemoRepository;

public class MemoApi {

  private static final MemoRepository repo = new MemoRepository();

  public static void create(Req req, Res res) {
    Memo m = req.json(Memo.class).valid();
    res.json("memo", repo.create(m.txt));
  }

  public static void read(Req req, Res res) {
    res.json("memo", repo.readAll());
  }
  
  public static void update(Req req, Res res) {
    Memo m = repo.update(
      req.path(":id", Long.class), 
      req.json(Memo.class).valid().txt
    );
    res.json("memo", m);
  }

  public static void delete(Req req, Res res) {
    repo.delete(req.path(":id", Long.class));
  }
}
