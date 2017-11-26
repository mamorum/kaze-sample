package kaze.sample.html;

import kaze.App;
import kaze.server.Jetty;

// To check:
//  app -> http://localhost:8080/app/hello
//  doc -> http://localhost:8080/ (or /index.html)
public class JettyApp {
  public static void main(String[] args) {
    App app = new App();
    app.get("/hello", (req, res) -> {
      res.html("<p>Hello World from Jetty.</p>");
    });
    Jetty.app(app, "/app/*");
    Jetty.doc("/public", "/");
    Jetty.listen(8080);
  }
}
