package kaze.sample.rdb;

import com.google.gson.Gson;

import kaze.App;
import kaze.opt.Jetty;
import kaze.sample.rdb.http.MemoApi;

public class Main {
  public static void main(String[] args) {
    Rdb.init();
    App app = new App();
    Gson gson = new Gson();
    app.conv(gson::fromJson, gson::toJson);
    MemoApi.register(app);
    Jetty.app(app, "/app/*");
    Jetty.doc("/public", "/");
    Jetty.listen(8080);
  }
}