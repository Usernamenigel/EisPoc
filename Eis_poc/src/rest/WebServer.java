package rest;

import java.io.IOException;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyServerFactory;

public class WebServer
{
	
	static SelectorThread srv;
	static String url;
	
   public static void serverAn() throws IllegalArgumentException, IOException, InterruptedException {
      url = "http://localhost:4434";
      srv = GrizzlyServerFactory.create( url );
      System.out.println( "URL: " + url + "/benutzer");
   }
   
   
   public static void serverAus() {
	    srv.stopEndpoint();
	    System.out.println("Server aus");
   }
}