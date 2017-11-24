package kaze.sample.hw;

import kaze.App;
import kaze.server.Jetty;

public class Main {
  public static void main(String[] args) {
    App app = new App();
    app.get("/hello", (req, res) -> {
      res.html("<p>Hello, World</p>");
    });
    Jetty.app(app, "/app/*");
    Jetty.doc("/public", "/");
    Jetty.listen(8080);
  }
}
