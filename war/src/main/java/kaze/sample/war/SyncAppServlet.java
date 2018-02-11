package kaze.sample.war;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import kaze.App;

@WebServlet("/app/*")
@SuppressWarnings("serial")
public class SyncAppServlet extends App {
  public void init() throws ServletException {
    get("/hello", (req, res) -> {
      res.write("text/plain", "Hello.");
    });
  }
}
