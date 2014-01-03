package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.SyncInvoker;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class WebClient {
	  
	
	Client client = ClientBuilder.newClient();
    String uri = "http://localhost:4434";
    Gson gson = new Gson();
    
	/**
	 * Hier eine Get vom Client und ruft den String unter /Benutzer auf.
	 */
	public void getTest() {
		
		System.out.println(uri+"/benutzer");
		Response res = client.target(uri+"/benutzer").request("text/plain").get();
	    String message = res.readEntity(String.class);
	    System.out.println(res.toString());
	    System.out.println(message);
	    
	    
	    /**
	     * Oder so
	     */
	    WebTarget target = client.target(uri);
	    Response response = null;

        try {
            response = target.path("/benutzer").request("text/plain").get();
            System.out.println(response.toString());
            System.out.println(response.readEntity(String.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
	}
	
	public void getJson() {
		Response res = client.target(uri+"/benutzer").request("Application/Json").get();
		System.out.println(res.toString());
		System.out.println(res.readEntity(String.class));
	}
	
	public void getJson2() {
		String js = client.target(uri+"/benutzer/get").request("Application/Json").get().readEntity(String.class);
		JsonObject o = gson.fromJson(js, JsonObject.class);
		System.out.println(o.toString());
	}	
	
	public void getJson3() {
//		Response res = client.target(uri+"/benutzer/liste").request("Application/Json").get();
////		List<JsonObject> jsonlist = (List<JsonObject>) res.getEntity();
//		JsonObject o = gson.fromJson(res.readEntity(String.class), JsonObject.class);
//		System.out.println(o.toString());
		List<String> list = client.target(uri+"/benutzer/liste").request().get(new GenericType<List<String>>() {});
	}
}




//client.property("MyProperty", "MyValue").register(MyProvider.class).enable(MyFeature.class);