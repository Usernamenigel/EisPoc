package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import couch.Datenbankverwaltung;

@Path("/kreis/{Kid}/kalender")
public class ServiceKalender {
	
	Gson gson = new Gson();
	Datenbankverwaltung dv = new Datenbankverwaltung();
	private final String db = "kalender";
	
	/**
	 * Holt alle Kalender Einträge eines bestimmten Kreises
	 *  {Kid} beachten
	 *  Iterriert Liste und wenn der Kalendereintrag der richtigen KreisID entspricht
	 *  wird dieser Kalendereintragg in die jsonString Liste gespeichert
	 * @return Kalender Liste in JsonString
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getKalender(@PathParam("Kid") int id) {
		List<JsonObject> jsonlist = new ArrayList<JsonObject>();
		List<String> jsonString = new ArrayList<String>();
		jsonlist = dv.getAll(db);
		for(int i = 0; i<jsonlist.size(); i++) {
			if(jsonlist.get(i).get("kreisId").getAsInt() == id) {
				jsonString.add(i, gson.toJson(jsonlist.get(i)));
			}
		}
		return gson.toJson(jsonlist);
	}
	
	@GET
	@Path("/id")
	@Produces(MediaType.APPLICATION_JSON)
	public String getEinKalender() {
		return null;
	}
	
	public void putKalender() {
		
	}
	
	public void postKalender() {
		
	}
	
	public void deleteKalender() {
		
	}
	
}
