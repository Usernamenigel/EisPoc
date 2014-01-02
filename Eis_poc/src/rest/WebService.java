package rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;













import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jsonklassen.Profil;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import couch.Datenverwaltung;

@Path("/benutzer")
public class WebService {

	@GET
	@Produces("text/plain")
    public String getNachricht() {
        return "Jo hier die Nachricht";
    }

	@GET
    @Produces("text/html")
    public String html() {
    	return "<strong>info</strong>";
    }

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Profil getTrackInJSON() {
 
		Profil profil = new Profil();
		profil.setAlter(34);
		profil.setKinder(true);
		profil.setNname("Nicht Hitler");
		profil.setVname("Ist es Hitler");
 
		return profil;
 
	}
	
	@Path("/json")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject json() {
    	JsonParser parser = new JsonParser();
    	JsonObject o = (JsonObject)parser.parse("{\"a\": \"A\"}");
    	return o;
    }
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
    public JsonObject holeBenutzer() {
//		Gson gson = new Gson();
		Datenverwaltung dv = new Datenverwaltung();
		JsonObject js = new JsonObject();
		js = dv.getEinBenutzer();
		System.out.println("Hier ist der Webservice JSON");
		System.out.println(js.toString()+" Hier in WebService");
		return js;
//	    return Response.ok().entity(js).build();
//		return gson.fromJson("{\"success\":\"true\",\"controls\":[{\"controlID\":\"1\",\"type\":\"TEXTBOX\"},{\"controlID\":\"4\",\"type\":\"CHECKBOX\"}]}", JsonObject.class);
//		return "{\"success\":\"true\",\"controls\":[{\"controlID\":\"1\",\"type\":\"TEXTBOX\"},{\"controlID\":\"4\",\"type\":\"CHECKBOX\"}]}";
	}
	
	@Path("/0")
	@GET
	@Produces("text/plain")
	public String stringBenutzer() {
		Datenverwaltung dv = new Datenverwaltung();
		JsonObject js = new JsonObject();
		js = dv.getEinBenutzer();
		System.out.println("Hier ist der Webservice TEXT");
		System.out.println(js);
		return js.toString();
	}
	
	
	@Path("/hallo")
	@GET
	@Produces("text/plain")
	public String benutzerHallo() {
		return "Das hier ist benutzer/hallo";
	}
	
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Profil profil) {
		String result = "Track saved : " + profil;
		return Response.status(201).entity(result).build();
	}
}

//@GET
//@Produces(MediaType.APPLICATION_JSON)
//public List<JsonObject> holeBenutzer() {
//	Datenverwaltung dv = new Datenverwaltung();
//	List<JsonObject> jsonlist = new ArrayList<JsonObject>();
//	jsonlist = dv.getBenutzer();
//	return jsonlist;
//}
