package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import couch.Datenbankverwaltung;

@Path("/kreis")
public class ServiceKreis {

	Gson gson = new Gson();
	Datenbankverwaltung dv = new Datenbankverwaltung();
	
	
	@GET
	@Produces("text/plain")
    public String getKreise() {
//		dv.get
        return "Jo hier der String von /benutzer";
    }
	
	/**
	 * Gibt einen spezififischen Kreis zurück
	 * @return
	 */
	@GET
	@Path("/{Kid}")
	@Produces("text/plain")
    public String getKreis(@PathParam("Kid") int i) {
        return "Jo hier der String von /benutzer";
    }
	
}
