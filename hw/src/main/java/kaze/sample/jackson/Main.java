package kaze.sample.jackson;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import kaze.App;
import kaze.server.Jetty;

// To check:
//  app -> http://localhost:8080/
public class Main {
  public static final ObjectMapper jackson = new ObjectMapper();
  public static void main(String[] args) {
    App app = new App();
    app.json.parser(Main::toObj, Main::toJson);
    app.get.add("/", (req, res) -> {
      res.json("msg", "Hello, Jackson.");
    });
    Jetty.app(app, "/*");
    Jetty.listen(8080);
  }
  private static <T> T toObj(String json, Class<T> to) {
    try { return jackson.readValue(json, to);}
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  private static String toJson(Object from) {
    try { return jackson.writeValueAsString(from);}
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
