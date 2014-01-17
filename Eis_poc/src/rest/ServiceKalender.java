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

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import couch.Datenbankverwaltung;

@Path("/kreis/{kid}/kalender")
public class ServiceKalender {
	
	Gson gson = new Gson();
	Datenbankverwaltung dv = new Datenbankverwaltung();
	private final String db = "kalender";
	
	/**
	 * Holt alle Kalender Einträge eines bestimmten Kreises
	 *  {Kid} beachten
	 *  Iterriert Liste und wenn der Kalendereintrag der richtigen KreisID entspricht
	 *  wird dieser Kalendereintragg in die jsonString Liste gespeichert
	 * @return Kalender Liste in JsonObject Form
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getKalender(@PathParam("kid") int id) {
		List<JsonObject> jsonlist = new ArrayList<JsonObject>();
		List<JsonObject> jsonlist2 = new ArrayList<JsonObject>();
//		List<String> jsonString = new ArrayList<String>();
		jsonlist = dv.getAll(db);
		for(int i = 0; i<jsonlist.size(); i++) {
			if(jsonlist.get(i).get("kreisId").getAsInt() == id) {
				jsonlist2.add(jsonlist.get(i));
			}
		}
		return gson.toJson(jsonlist2);
	}
	/**
	 * Holt einen bestimmten Kalender aus einem bestimmten Kreis
	 * @param kid Kreis ID
	 * @param id die Kalender Id
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getEinKalender(@PathParam("kid") int kid, @PathParam("id") int id) {
		return gson.toJson(dv.get(id, kid, 0, db));
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("text/plain")
	public void addKalender(String kalender) {
		System.out.println("HALLO ");
		dv.add(gson.fromJson(kalender, JsonObject.class), db);
	}
	
	@POST
	@Path("/{id}")
	@Consumes("text/plain")
	public void setKalender(String kalender) {
		dv.set(gson.fromJson(kalender, JsonObject.class), db);
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes("text/plain")
	public void deleteKalender(@PathParam("kid") int kid, @PathParam("id") int id) {
		dv.delete(id, kid, 0, db);
	}
	
}
