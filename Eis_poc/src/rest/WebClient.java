package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;


public class WebClient {

	Client client = ClientBuilder.newClient();
    String uri = "http://localhost:4434";
    
    
	/**
	 * Hier eine Get vom Client
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
            response = target.path("/benutzer").request().get(Response.class);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
	}
	
	public void getJson() {
		Response res = client.target(uri+"/benutzer/json").request("Application/Json").get();
		System.out.println(res.toString());
	}
	
	public void getJson2() {
		JsonObject o = client.target(uri+"/benutzer/json").request("Application/Json").get(JsonObject.class);
		System.out.println(o.toString());
	}
	
}




//client.property("MyProperty", "MyValue").register(MyProvider.class).enable(MyFeature.class);