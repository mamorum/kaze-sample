package kaze.sample.hw;

import com.google.gson.Gson;

import kaze.App;
import kaze.server.Jetty;

public class Main {
  public static void main(String[] args) {
    App app = new App();
    app.get("/hello", (req, res) -> {
      res.json("msg", "Hello World.");
    });
    Gson gson = new Gson();
    app.parser(gson::fromJson, gson::toJson);
    Jetty jetty = new Jetty();
    jetty.app(app, "/app/*");
    jetty.doc("/public", "/");
    jetty.listen(8080);
  }
}
