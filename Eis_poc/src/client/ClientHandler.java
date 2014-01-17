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
import jsonklassen.Meinkalender;
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
	
	protected boolean vergleicheTermin(Kalender k, Meinkalender mk) {
		if(k.getTag() == mk.getTag() && k.getMonat() == mk.getMonat() && k.getJahr() == mk.getJahr()) {
			return hilfe(mk, k);
		}
		else {
			return true;
		}
	}
	
//	protected boolean vergleicheTermine(Kalender kalender, List<Meinkalender> list) {
//		for(Meinkalender mk: list) {
//			if(kalender.getJahr() == mk.getJahr() && kalender.getMonat() == mk.getMonat() && kalender.getTag() == mk.getTag()) {
//				return hilfe(mk, kalender);
//			}
//			else if(kalender.getMonat() == mk.getMonat() && kalender.getTag() == mk.getTag()) {
//				return hilfe(mk, kalender);
//			}
//			else if(kalender.getTag() == mk.getTag()) {
//				return hilfe(mk, kalender);
//			}
//			else {
//				return true;
//			}
//		}
//		return true;
//	}
//	
	private boolean hilfe(Meinkalender mk, Kalender kalender) {
			if(mk.getStunde() < kalender.getStunde() && (mk.getStunde() + mk.getDauer() < kalender.getStunde()) ) {
				return true;
			}
			else if(mk.getStunde() > (kalender.getStunde()+kalender.getDauer())) {
				return true;
			}
			return false;
		}
		
}
