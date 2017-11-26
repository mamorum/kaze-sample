package kaze.sample.rdb;

import com.google.gson.Gson;

import kaze.App;
import kaze.sample.rdb.http.MemoApi;
import kaze.server.Jetty;

public class Main {
  public static final Gson gson = new Gson();
  public static void main(String[] args) {
    Rdb.init();
    App app = new App();
    app.parser(gson::fromJson, gson::toJson);
    MemoApi.register(app);
    Jetty.app(app, "/app/*");
    Jetty.doc("/public", "/");
    Jetty.listen(8080);
  }
}