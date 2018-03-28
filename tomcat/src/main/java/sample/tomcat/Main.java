package sample.tomcat;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;

public class Main {
  public static void main(String[] args) throws Exception {
    Tomcat cat = new Tomcat();
    cat.setPort(8080);
    cat.setHostname("0.0.0.0");
    File docDir = new File("src/main/resources/public");
    Context ctx = cat.addContext(
      "", docDir.getAbsolutePath()
    );
    Tomcat.addServlet(ctx, "default", new DefaultServlet());
    ctx.addServletMappingDecoded("/", "default");
    ctx.addWelcomeFile("index.html");
    Tomcat.addServlet(ctx, "app", new HelloServlet());
    ctx.addServletMappingDecoded("/app/*", "app");
    cat.start();
    cat.getServer().await();
  }
}
