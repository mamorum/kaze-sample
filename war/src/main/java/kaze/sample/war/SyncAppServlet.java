package kaze.sample.war;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;

import kaze.App.AppServlet;

@WebServlet("/app/*")
@SuppressWarnings("serial")
public class SyncAppServlet extends AppServlet {
  public void init() throws ServletException {
    Gson gson = new Gson();
    app.parser(gson::fromJson, gson::toJson);
    app.get("/", (req, res) -> {
      res.write("text/plain", "Hello World");
    });
    app.get("/html/hello", (req, res) -> {
      res.html("<p>Hello</p>");
    });
    app.get("/json", (req, res) -> {
      res.json("msg", "Hello");
    });
    app.get("/err", (req, res) -> {
      throw new Exception();
    });
  }
}
