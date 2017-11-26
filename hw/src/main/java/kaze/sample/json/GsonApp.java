package kaze.sample.json;

import com.google.gson.Gson;

import kaze.App;
import kaze.server.Jetty;

// To check:
//  app -> http://localhost:8080/hello
public class GsonApp {
  public static final Gson gson = new Gson();
  public static void main(String[] args) {
    App app = new App();
    app.parser(gson::fromJson, gson::toJson);
    app.get("/hello", (req, res) -> {
      res.json("msg", "Hello Gson.");
    });
    Jetty.app(app, "/*");
    Jetty.listen(8080);
  }
}
