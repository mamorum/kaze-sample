package kaze.sample.rdb.http;

import kaze.Http;
import kaze.http.Req;
import kaze.http.Res;
import kaze.sample.rdb.model.Tsubuyaki;
import kaze.sample.rdb.sql.TsubuyakiRepository;

public class TsubuyakiApi {
  
  static final String uri = "/tsubuyaki";
  static TsubuyakiRepository repo = new TsubuyakiRepository();
  
  @Http({"POST", uri})
  public void create(Req req, Res res) {
    Tsubuyaki t = req.json(Tsubuyaki.class).get();
    res.json("tsubuyaki", repo.create(t.txt));
  }
  
  @Http({"GET", uri})
  public void read(Req req, Res res) {
    res.json("tsubuyaki", repo.readAll());
  }
  
  @Http({"PUT", uri + "/:id"})
  public void update(Req req, Res res) {
    repo.update(
        req.uri(":id", Long.class),
        req.param("txt")
    );
  }
  
  @Http({"DELETE", uri + "/:id"})
  public void delete(Req req, Res res) {
    repo.delete(
        req.uri(":id", Long.class)
    );
  }
}
