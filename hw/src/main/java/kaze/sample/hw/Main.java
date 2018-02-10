package kaze.sample.hw;

import com.google.gson.Gson;

import kaze.App;
import kaze.server.Jetty;

// To check:
//  app -> http://localhost:8080/app/hello
//  doc -> http://localhost:8080/ (or /index.html)
public class Main {
  public static void main(String[] args) {
    App app = new App();
    Gson gson = new Gson();
    app.conv(gson::fromJson, gson::toJson);
    app.get("/hello", (req, res) -> {
      res.json("msg", "Hello, World.");
    });
    Jetty.app(app, "/app/*");
    Jetty.doc("/public", "/");
    Jetty.listen(8080);
  }
}
