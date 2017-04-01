package kaze.sample.rdb;

import kaze.Http;
import kaze.sample.rdb.http.MemoApi;

public class Main {
  public static void main(String[] args) {
    Rdb.init();
    Http.server()
      .get("/memo", MemoApi::read)
      .post("/memo", MemoApi::create)
      .put("/memo/:id", MemoApi::update)
      .delete("/memo/:id", MemoApi::delete)
    .listen();
  }
}

