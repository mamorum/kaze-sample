package kaze.sample.gs.html;

import kaze.App;
import kaze.server.Jetty;

// app -> http://localhost:8080/app/html/hello
// doc -> http://localhost:8080/*
public class Main {
  public static void main(String[] args) {
    App app = new App();
    app.get("/html/hello", (req, res) -> {
      res.html("<p>Hello, World.</p>");
    });
    Jetty.app(app, "/app/*");
    Jetty.doc("/public", "/");
    Jetty.listen(8080);
  }
}
