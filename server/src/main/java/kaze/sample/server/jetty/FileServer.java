package kaze.sample.server.jetty;

import kaze.server.Jetty;

public class FileServer {
  public static void main(String[] args) {
    Jetty.location("/public");
    Jetty.listen(8080);
  }
}
