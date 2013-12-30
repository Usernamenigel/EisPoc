package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.sun.jersey.api.client.*;

public class WebClient {

	String jsonStr;
	List<JsonObject> liste = new ArrayList<JsonObject>();
	static String url;
	static WebResource wrs;
	
	public static void starte() {
		url = "http://localhost:4434/benutzer";
		System.out.println("URL: " + url);

		wrs = Client.create().resource(url);

		System.out.println("\nTextausgabe:");
		System.out.println(wrs.accept("text/plain").get(String.class));
	}

	public void holeBenutzer() {
		wrs = Client.create().resource(url);
		liste = wrs.accept("Application/Json").get( new GenericType<List<JsonObject>>() {} );
		System.out.println("Hallo das sind alle Benutzer: ");
		for(JsonObject jo : liste) {
			System.out.println(jo.get("Name").toString());
		}
   }
	
	public void einBenutzer() {
		wrs = Client.create().resource(url);
		System.out.println("Hier ist der WebClient JSON");
		JsonObject jo = wrs.accept("application/json").get(JsonObject.class);
		System.out.println("Hier das eine JsonObjet: " + jo.toString());
	}
	
	public void testText() {
		wrs = Client.create().resource("http://localhost:4434/benutzer/0");
		System.out.println(wrs.accept("text/plain").get(String.class));
	}
}