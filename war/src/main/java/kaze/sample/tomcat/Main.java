package kaze.sample.tomcat;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;

import kaze.sample.war.AppServlet;

// run embedded tomcat
public class Main {
  public static void main(String[] args) throws Exception {
    Tomcat cat = new Tomcat();
    cat.setPort(8080);
    cat.setHostname("0.0.0.0");
    Context ctx = cat.addContext(
      "", new File("src/main/webapp").getAbsolutePath()
    );
    Tomcat.addServlet(ctx, "default", new DefaultServlet());
    ctx.addServletMappingDecoded("/", "default");
    ctx.addWelcomeFile("index.html");
    Tomcat.addServlet(ctx, "app", new AppServlet());
    ctx.addServletMappingDecoded("/app/*", "app");
    cat.start();
    cat.getServer().await();
  }
}
