package kaze.sample.gs.json;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import kaze.App;
import kaze.server.Jetty;

// To check:
//  app ->
//   - http://localhost:8080/json/gson/msg
//   - http://localhost:8080/json/jackson/msg
//  doc ->
//   - http://localhost:8080/json/gson.html
//   - http://localhost:8080/json/jackson.html
public class Main {
  public static void main(String[] args) {
    //-> gson
    App ga = new App();
    ga.conv(gson::fromJson, gson::toJson);
    ga.get("/msg", (req, res) -> {
      res.json("msg", "Gson App.");
    });
    Jetty.app(ga, "/json/gson/*");
    //-> jackson
    App ja = new App();
    ja.conv(Main::jsonToObj, Main::objToJson);
    ja.get("/msg", (req, res) -> {
      res.json("msg", "Jackson App.");
    });
    Jetty.app(ja, "/json/jackson/*");
    //-> static contentes
    Jetty.doc("/public", "/");
    Jetty.listen(8080);
  }
  //-> gson
  static final Gson gson = new Gson();
  //-> jackson
  static final ObjectMapper jackson = new ObjectMapper();
  static <T> T jsonToObj(String json, Class<T> to) {
    try { return jackson.readValue(json, to);}
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  static String objToJson(Object obj) {
    try { return jackson.writeValueAsString(obj);}
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
