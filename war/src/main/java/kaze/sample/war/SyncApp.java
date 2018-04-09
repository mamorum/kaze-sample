package kaze.sample.war;

import javax.servlet.ServletException;

import kaze.App;

@SuppressWarnings("serial")
public class SyncApp extends App {
  public void init() throws ServletException {
    get("/hello", (req, res) -> {
      res.write("text/plain", "Hello.");
    });
  }
}
