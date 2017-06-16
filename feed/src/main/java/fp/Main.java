package fp;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.IOUtils;

import kaze.Http;
import kaze.http.Req;
import kaze.http.Res;

public class Main {
  public static void main(String[] args) {
    Http.server(
      ).get("/feed", Main::feed
    ).listen();
  }

  //-> feed
  private static final  HttpRequestFactory fac
    = (new NetHttpTransport()).createRequestFactory();

  private static void feed(Req req, Res res) throws Exception {
    HttpResponse gres = null;
    try {
      gres = fac.buildGetRequest(
        new GenericUrl(req.param("url"))
      ).execute();
      render(gres, res);
    } catch (HttpResponseException e) {
      res.status(e.getStatusCode());
    } finally {
      if (gres != null) gres.disconnect();
    }
  }
  private static void render(HttpResponse gres, Res res) throws Exception {
    String ctype = gres.getContentType();
    res.type(ctype);
    if (ctype.contains("charset=") || ctype.contains("Charset=")) {
      // response header has charset and use it.
      res.write(gres.parseAsString());
      return;
    }
    // response header has no charset.
    // use 'utf-8' instead of google http client default 'iso-8859-1'.
    InputStream content = gres.getContent();
    if (content == null) res.status(404);
    else {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      IOUtils.copy(content, out);
      res.write(out.toString("UTF-8"));
    }
  }
}
