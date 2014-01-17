package client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import jsonklassen.Kalender;
import alteKlassen.Profile;

/**
 * Der Handler dient dazu den Clients als Rest Schnitstelle
 * hier werden die HTTP Methoden für die einzelnen Ressourcen implementiert
 * 
 *
 */
public class ClientHandler {
	
	Client client = ClientBuilder.newClient();
    final String uri = "http://localhost:4434";
    Gson gson = new Gson();

    /**
     * Übergibt einen neuen Termin
     * @param termin
     * @param kreisId
     * @param id
     */
	public void sendeTermin(Kalender termin, int kreisId, int id) {
		String terminJson = gson.toJson(termin);  
		client.target(uri+"/kreis/"+kreisId+"/kalender/"+id).request(MediaType.APPLICATION_JSON).put(Entity.text(terminJson));
	}
	
	/**
	 * Gibt alle Kalender Einträge eines bestimmten Kreises zurück
	 * @param kreisId
	 * @return
	 */
	public JsonArray holeTermine(int kreisId) {
		TypeToken<JsonArray> token = new TypeToken<JsonArray>(){};
		Response res = client.target(uri+"/kreis/"+kreisId+"/kalender").request("Application/Json").get();
		JsonArray json = gson.fromJson(res.readEntity(String.class), token.getType());  
		return json;
	}
	
	/**
	 * Gibt einen bestimmten Kalender Eintrag zurück
	 * @param kreisId
	 * @param id
	 * @return
	 */
	protected JsonObject holeTermin(int kreisId, int id) {
		String js = client.target(uri+"/kreis/"+kreisId+"/kalender/"+id).request("Application/Json").get().readEntity(String.class);
		JsonObject jsonobject = gson.fromJson(js, JsonObject.class);
		return jsonobject;
	}
	
	protected void vergleicheTermine(List<Kalender> list1, List<Kalender> list2) {
		
	}
	
	
	

}
