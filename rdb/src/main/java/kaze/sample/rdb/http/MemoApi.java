package kaze.sample.rdb.http;

import kaze.App;
import kaze.Req;
import kaze.Res;
import kaze.sample.rdb.model.Memo;
import kaze.sample.rdb.sql.MemoRepository;

public class MemoApi {
  private static final MemoRepository repo = new MemoRepository();
  public static void register(App app) {
    app.get.add("/memo", MemoApi::read);
    app.post.add("/memo", MemoApi::create);
    app.put.add("/memo", MemoApi::update);
    app.delete.add("/memo/:id", MemoApi::delete);
  }
  public static void read(Req req, Res res) {
    res.json("memo", repo.readAll());
  }
  public static void create(Req req, Res res) {
    String txt = req.param("txt");
    res.json("memo", repo.create(txt));
  }
  public static void update(Req req, Res res) {
    Memo input = req.json(Memo.class);
    Memo after = repo.update(
      input.id, input.txt
    );
    res.json("memo", after);
  }
  public static void delete(Req req, Res res) {
    repo.delete(Long.valueOf(
      req.path(":id")
    ));
  }
}
