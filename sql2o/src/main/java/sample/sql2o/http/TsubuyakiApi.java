package sample.sql2o.http;

import kaze.Http;
import kaze.http.Req;
import kaze.http.Res;
import sample.sql2o.model.Tsubuyaki;
import sample.sql2o.sql.TsubuyakiRepository;

public class TsubuyakiApi {
  
  TsubuyakiRepository repo = new TsubuyakiRepository();
  
  static final String uri = "/tsubuyaki";
  
  @Http({"POST", uri})
  public void create(Req req, Res res) {
    Tsubuyaki t = req.json(Tsubuyaki.class).get();
    res.json("tsubuyaki", repo.create(t.txt));
  }
  
  @Http({"GET", uri})
  public void read(Req req, Res res) {
    res.json("tsubuyaki", repo.all());
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
