package kaze.sample.gs.html;

import kaze.App;
import kaze.opt.Jetty;

// app -> http://localhost:8080/app/hello
// doc -> http://localhost:8080/*
public class Main {
  public static void main(String[] args) {
    App app = new App();
    app.get("/hello", (req, res) -> {
      res.html("<p>Hello, World.</p>");
    });
    Jetty.app(app, "/app/*");
    Jetty.doc("/public", "/");
    Jetty.listen(8080);
  }
}
