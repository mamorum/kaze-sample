package kaze.sample.gs.json;

import com.google.gson.Gson;

import kaze.App;
import kaze.server.Jetty;

// app -> http://localhost:8080/app/msg
// doc -> http://localhost:8080/*
public class GsonMain {
  static final Gson gson = new Gson();
  public static void main(String[] args) {
    App app = new App();
    app.conv(gson::fromJson, gson::toJson);
    app.get("/msg", (req, res) -> {
      res.json("msg", "Hello World.");
    });
    Jetty.app(app, "/app/*");
    Jetty.doc("/public", "/");
    Jetty.listen(8080);
  }
}