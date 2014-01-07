package rest;

import java.util.ArrayList;
import java.util.List;





import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.SyncInvoker;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import jsonklassen.Profil;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientResponse;

import com.fasterxml.jackson.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class WebClient {
	  
	
	Client client = ClientBuilder.newClient();
    String uri = "http://localhost:4434";
    Gson gson = new Gson();
	static Scanner sc = new Scanner(System.in);
    
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
	
	/**
	 * Das JsonArray Token wird benötigt damit GSON aus dem String von der Ressource, ein JsonArray erstellen kann
	 */
	public void getJsonArray() {
		TypeToken<JsonArray> token = new TypeToken<JsonArray>(){};
		Response res = client.target(uri+"/benutzer/array").request("Application/Json").get();
		JsonArray json = gson.fromJson(res.readEntity(String.class), token.getType());   
		for(int i=0; i<json.size(); i++) 
			System.out.println(json.get(i).toString() + "---CLIENT AUS LISTE---");
	}
	
	public void putJson() {
		Profil profil = new Profil();
		profil.setAlter(100);
		profil.setKinder(true);
		profil.setNname("Verbale");
		profil.setVname("Beleidigung");
		String profilJson = gson.toJson(profil);  
		
		Response response = client.target(uri+"/benutzer/post/0").request().post(Entity.text(profilJson));
//		Invocation inv2 = client.target(uri+"/benutzer/post/0").request().build(profilJson);
	}
	
	public void löscheJson() {
		System.out.println("Welche Person löschen: ");
		String attribut = sc.next();
		Response response = client.target(uri+"/benutzer/delete/"+ attribut).request().delete();
		System.out.println(response);
	}
}



//res.readEntity(JsonArray.class);
//JsonArray aa = gson.fromJson(res.getEntity(), JsonElement.class);
//List<JsonObject> jsonlist = (List<JsonObject>) res.getEntity();
//JsonObject o = gson.fromJson(res.readEntity(String.class), JsonObject.class);
//System.out.println(res.readEntity(String.class));
//List<String> list = client.target(uri+"/benutzer/liste").request().get(new GenericType<List<String>>() {});

//client.property("MyProperty", "MyValue").register(MyProvider.class).enable(MyFeature.class);