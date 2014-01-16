package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import couch.Datenbankverwaltung;

@Path("/kreis")
public class ServiceKreis {

	Gson gson = new Gson();
	Datenbankverwaltung dv = new Datenbankverwaltung();
	private final String db = "kreis";
	
	/**
	 * Hole alle Kreise aus der Datenbank und speichere in eine JsonObject Liste
	 * Iterriere Liste und wandle jedes JsonObject in ein Json konformen String um
	 * Speichere diesen String in eine String List
	 * wandle String-Liste in einen eigenen Json konformen String und gebe diesen zurück
	 * 
	 * @return String Liste in JsonObject Form
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
    public String getKreise() {
		List<JsonObject> jsonlist = new ArrayList<JsonObject>();
		List<String> jsonString = new ArrayList<String>();
		jsonlist = dv.getAll(db);
//		for(int i = 0; i<jsonlist.size(); i++) {
//			jsonString.add(i, gson.toJson(jsonlist.get(i)));
//		}
		return gson.toJson(jsonlist);
    }
	
	/**
	 * Gibt einen spezififischen Kreis zurück
	 * PathParam ID ist die ID des Kreises
	 * @return
	 */
	@GET
	@Path("/{kid}")
	@Produces(MediaType.APPLICATION_JSON)
    public String getKreis(@PathParam("kid") int i) {
        return gson.toJson(dv.get(i, 0, 0, db));
    }
	
	/**
	 * Schreibt ein neues KreisObjekt in die Datenbank
	 * @param id Die Kreis ID
	 * @param Das Kreisobjekt
	 */
	@PUT
	@Path("/{kid}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addKreis(String kreis) {
		dv.add(gson.fromJson(kreis, JsonObject.class), db);
	}
	
	@POST
	@Path("/{kid}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setKreis(String kreis) {
		dv.set(gson.fromJson(kreis, JsonObject.class), db);
	}
	
	@DELETE
	@Path("/{kid}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteKreis(@PathParam("kid") int id) {
		dv.delete(id, 0, 0, db);
	}
}
