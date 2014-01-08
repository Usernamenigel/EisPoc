package rest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;





import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.entity.StringEntity;

import jsonklassen.Profile;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import couch.Datenverwaltung;

@Path("/benutzer")
public class WebService {

	Gson gson = new Gson();
	Datenverwaltung dv = new Datenverwaltung();
	
	
	@GET
	@Produces("text/plain")
    public String getNachricht() {
        return "Jo hier der String von /benutzer";
    }

	@GET
    @Produces("text/html")
    public String html() {
    	return "<strong>info</strong>";
    }
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
    public String holeBenutzer() {
		Gson gson = new Gson();
		Datenverwaltung dv = new Datenverwaltung();
		JsonObject js = new JsonObject();
		js = dv.getEinBenutzer();
		System.out.println(js.toString()+" Hier in WebService");
		return gson.toJson(js);
	}
	
	@GET
	@Path("/get")
	@Produces("application/json")
	public String getTrackInJSON() {
		Profile profil = new Profile();
		profil.setAlter(34);
		profil.setKinder(true);
		profil.setNname("Mangelhaft");
		profil.setVname("Ausserordentlich");
		return gson.toJson(profil);  
	}
	
	@Path("/json")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String json() {
    	JsonParser parser = new JsonParser();
    	JsonObject o = (JsonObject)parser.parse("{\"a\": \"A\"}");
    	return gson.toJson(o);
    }
	
	/**
	 * JsonObject-Liste in eine String-Liste kopieren
	 * Diese wird testhalber doppelt ausgegeben um das kopieren zu überprüfen
	 * 
	 * NACHTRAG: jsonlist als String geht ebenso
	 * @return String Liste in JsonFormat
	 */
	@Path("/liste")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String holeBenutzerListe() {
		List<JsonObject> jsonlist = new ArrayList<JsonObject>();
		List<String> jsonString = new ArrayList<String>();
		jsonlist = dv.getBenutzer();
		for(int i = 0; i<jsonlist.size(); i++) {
			System.out.println(jsonlist.get(i).toString());
			jsonString.add(i, gson.toJson(jsonlist.get(i)));
			System.out.println(jsonString.get(i).toString());
		}
//		final GenericEntity<List<String>> entity = new GenericEntity<List<String>>(jsonString) { };
		// Da die Übergabe von Listen nicht funktioniert wird hier ein einzelner String mit der Menge an Daten zurücjgegeben
		return gson.toJson(jsonlist);
	}
	
	/** 
	 * Ähnliche Methode nur mit einem JsonArray welches mit Objekten gefühlt wird
	 * Gefüllte JsonArray wird in einen String gemarshellt und dieser wird zurückgegeben
	 * Der Client kann umständlicher und über Umwege diesen String wieder in ein JsonArray umwandeln.
	 * @return
	 */
	@Path("/array")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String holeBenutzerArray() {
		List<JsonObject> jsonlist = dv.getBenutzer();
		JsonArray jsonarray = new JsonArray();
		for(int i = 0; i<jsonlist.size(); i++) {
			System.out.println(jsonlist.get(i).toString());
			jsonarray.add(jsonlist.get(i));
			System.out.println(jsonarray.get(i).toString());
		}
		return gson.toJson(jsonarray);
	}
	 
	/**
	 * Die ID ist noch nicht in Gebrauch
	 * Später wird ID zu Name
	 * @param profil
	 * @return
	 */
	@POST
	@Path("/post/{id}")	
	@Consumes("text/plain")
	public Response erstelleEintrag(@PathParam("id") int id, String profil) {
		JsonObject obj = gson.fromJson(profil, JsonObject.class);
		dv.eintragJson(obj);
		return Response.noContent().build();
//		return Response.status(201).entity(result).build();
	}
	
	/**
	 * 
	 * Es wird hier die Methode dv.löschen() aufgerufen die nach Namen sucht und
	 * gewünschten Namen dann löscht.
	 * @param id
	 * @param attribut
	 * @return
	 */
	@DELETE
	@Path("/delete/{name}")	
	@Produces("text/plain")
	public Response löscheEintrag(@PathParam("name") String name) {
		dv.löschen(name);
		return Response.noContent().build();
	}
	
	
	
}



