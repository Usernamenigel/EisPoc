package client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientResponse;

import com.google.gson.Gson;

import jsonklassen.Kalender;
import alteKlassen.Profile;

public class ClientHandler {
	
	Client client = ClientBuilder.newClient();
    final String uri = "http://localhost:4434";
    Gson gson = new Gson();

	public void sendeTermin(Kalender termin, int kreisId, int id) {
		String terminJson = gson.toJson(termin);  
		client.target(uri+"/kreis/"+kreisId+"/kalender/"+id).request(MediaType.APPLICATION_JSON).put(Entity.text(terminJson));
	}

}
