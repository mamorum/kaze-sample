package kaze.sample.rdb.http;

import kaze.Http;
import kaze.http.Req;
import kaze.http.Res;
import kaze.sample.rdb.model.Memo;
import kaze.sample.rdb.sql.MemoRepository;

public class MemoApi {
  
  static final String uri = "/memo";
  static MemoRepository repo = new MemoRepository();
  
  @Http({"POST", uri})
  public void create(Req req, Res res) {
    Memo m = req.json(Memo.class).valid();
    res.json("memo", repo.create(m.txt));
  }
  
  @Http({"GET", uri})
  public void read(Req req, Res res) {
    res.json("memo", repo.readAll());
  }
  
  @Http({"PUT", uri + "/:id"})
  public void update(Req req, Res res) {
    Memo m = repo.update(
        req.uri(":id", Long.class), 
        req.json(Memo.class).valid().txt
    );
    res.json("memo", m);
  }
  
  @Http({"DELETE", uri + "/:id"})
  public void delete(Req req, Res res) {
    repo.delete(
        req.uri(":id", Long.class)
    );
  }
}
