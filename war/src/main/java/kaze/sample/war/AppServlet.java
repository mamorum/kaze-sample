package kaze.sample.war;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;

import kaze.App;

@WebServlet("/app/*")
@SuppressWarnings("serial")
public class AppServlet extends kaze.App.AppServlet {
  public void init() throws ServletException {
    Gson gson = new Gson();
    App.parser(gson::fromJson, gson::toJson);
    App.get("/app", (req, res) -> {
      res.write("text/plain", "Hello World");
    });
    App.get("/app/html/hello", (req, res) -> {
      res.html("<p>Hello</p>");
    });
    App.get("/app/json", (req, res) -> {
      res.json("msg", "Hello");
    });
    App.get("/app/err", (req, res) -> {
      throw new Exception();
    });
  }
}
