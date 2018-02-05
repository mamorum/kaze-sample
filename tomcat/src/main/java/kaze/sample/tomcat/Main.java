package kaze.sample.tomcat;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;

import kaze.App;

// To check:
//  app -> http://localhost:8080/app/hello
//  doc -> http://localhost:8080/ (or /index.html)
public class Main {
  public static void main(String[] args) throws Exception {
    App app = new App();
    app.get("/hello", (req, res) -> {
      res.html("<p>Hello, Tomcat.</p>");
    });
    listen(app);
  }
  private static void listen(App app) throws Exception {
    Tomcat cat = new Tomcat();  // embedded tomcat
    cat.setPort(8080);
    cat.setHostname("0.0.0.0");
    Context ctx = cat.addContext(
      "", new File("src/main/resources/public").getAbsolutePath()
    );
    Tomcat.addServlet(ctx, "default", new DefaultServlet());
    ctx.addServletMappingDecoded("/", "default");
    ctx.addWelcomeFile("index.html");
    Tomcat.addServlet(ctx, "app", app);
    ctx.addServletMappingDecoded("/app/*", "app");
    cat.start();
    cat.getServer().await();
  }
}
