package kaze.sample.gs;

import kaze.App;
import kaze.server.Jetty;

// To check:
//  app -> http://localhost:8080/app/hello
//  doc -> http://localhost:8080/ (or /index.html)
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
