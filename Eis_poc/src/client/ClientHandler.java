package client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import jsonklassen.Kalender;
import alteKlassen.Profile;

/**
 * Der Handler dient dazu den Clients als Rest Schnitstelle
 * hier werden die HTTP Methoden f�r die einzelnen Ressourcen implementiert
 * 
 *
 */
public class ClientHandler {
	
	Client client = ClientBuilder.newClient();
    final String uri = "http://localhost:4434";
    Gson gson = new Gson();

	public void sendeTermin(Kalender termin, int kreisId, int id) {
		String terminJson = gson.toJson(termin);  
		client.target(uri+"/kreis/"+kreisId+"/kalender/"+id).request(MediaType.APPLICATION_JSON).put(Entity.text(terminJson));
	}
	
	public JsonArray holeTermine(int kreisId) {
		TypeToken<JsonArray> token = new TypeToken<JsonArray>(){};
		Response res = client.target(uri+"/kreis/"+kreisId+"/kalender").request("Application/Json").get();
		JsonArray json = gson.fromJson(res.readEntity(String.class), token.getType());  
		return json;
	}
	
	public void holeTermin(int kreisId, int id) {
	
	}

}
