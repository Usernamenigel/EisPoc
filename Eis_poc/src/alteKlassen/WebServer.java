package alteKlassen;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


public class WebServer {

	static HttpServer server;
	
	public static void serverAn() throws IOException, InterruptedException {
		
		URI baseUri = UriBuilder.fromUri("http://localhost/").port(4434).build();
		
		ResourceConfig config = new ResourceConfig().register(WebService.class);
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
		
		try {
			server.start();
			NetworkListener listener = new NetworkListener("grizzly2", "localhost", 4434);
			server.addListener(listener);
		} catch (Exception e) {
			System.err.println(e);
		}
   }
}