package kaze.sample.war;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import kaze.App;

@WebServlet("/app/*")
@SuppressWarnings("serial")
public class SyncAppServlet extends App.Servlet {
  public void init() throws ServletException {
    app = new App();
    app.get("/hello", (req, res) -> {
      res.write("text/plain", "Hello.");
    });
    app.get("/err", (req, res) -> {
      throw new Exception("from Servlet.");
    });
  }
}
