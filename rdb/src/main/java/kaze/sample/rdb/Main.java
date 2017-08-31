package kaze.sample.rdb;

import com.google.gson.Gson;

import kaze.App;
import kaze.sample.rdb.http.MemoApi;
import kaze.server.Jetty;

public class Main {
  public static void main(String[] args) {
    Rdb.init();
    Gson gson = new Gson();
    App.parser(gson::fromJson, gson::toJson);
    MemoApi.register();
    Jetty jty = new Jetty();
    jty.location("/public");
    jty.listen(8080);
  }
}