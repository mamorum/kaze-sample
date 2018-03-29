package kaze.sample.gs.json;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import kaze.App;
import kaze.server.Jetty;

// app -> http://localhost:8080/app/msg
// doc -> http://localhost:8080/*
public class JacksonMain {
  public static void main(String[] args) {
    App app = new App();
    app.conv(Jackson::jsonToObj, Jackson::objToJson);
    app.get("/msg", (req, res) -> {
      res.json("msg", "Jackson App.");
    });
    Jetty.app(app, "/app/*");
    Jetty.doc("/public", "/");
    Jetty.listen(8080);
  }
  static class Jackson {
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
}
