package kaze.sample.feed;

import java.io.IOException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;

import kaze.App;
import kaze.Req;
import kaze.Res;
import kaze.server.Jetty;

public class Main {
  static int port = 8080;
  public static void main(String[] args) {
    String pp = System.getProperty("port");
    if (pp != null) port = Integer.parseInt(pp);
    //<- for heroku.
    App.get("/feed", Main::feed);
    Jetty jty = new Jetty();
    jty.listen(port);
  }

  //-> feed
  private static final  HttpRequestFactory http
    = (new NetHttpTransport()).createRequestFactory();
  private static void feed(Req req, Res res) throws IOException {
    res.srv.addHeader("Access-Control-Allow-Origin", "*");  // CORS
    HttpResponse re = null;
    try {
      re = http.buildGetRequest(
        new GenericUrl(req.param("url"))
      ).execute();
      res.write(
        re.getContentType(), re.parseAsString()
      );
    } catch (HttpResponseException e) {
      res.status(e.getStatusCode());
    } finally {
      if (re != null) re.disconnect();
    }
  }
}