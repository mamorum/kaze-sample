package kaze.sample.hw;

import com.google.gson.Gson;

import kaze.App;
import kaze.server.Jetty;

public class Main {
  public static void main(String[] args) {
    App app = new App("/app");
    app.get("/hello", (req, res) -> {
      res.json("msg", "Hello World.");
    });
    Gson gson = new Gson();
    app.parser(gson::fromJson, gson::toJson);
    Jetty.app(app);
    Jetty.doc("/public");
    Jetty.listen(8080);
  }
}
