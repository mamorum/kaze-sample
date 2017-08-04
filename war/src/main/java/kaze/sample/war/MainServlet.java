package kaze.sample.war;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kaze.App;

@WebServlet("/app/*")
@SuppressWarnings("serial")
public class MainServlet extends HttpServlet {
  public void init() throws ServletException {
    Gson gson = new Gson();
    App.parser(gson::fromJson, gson::toJson);
    App.get("/app/hello", (req, res) -> {
      res.html("<p>Hello</p>");
    });
    App.get("/app/json", (req, res) -> {
      res.json("msg", "Hello");
    });
    App.get("/app/err", (req, res) -> {
      throw new Exception();
    });
  }
  public void service(
    HttpServletRequest req, HttpServletResponse res)
  throws ServletException, IOException
  {
    try {
      boolean run = App.run(req, res);
      if (!run) res.sendError(404);
    } catch (Exception e) {
      res.sendError(500);
      throw new ServletException(e);
    }
  }
}
