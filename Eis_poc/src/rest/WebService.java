package rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import couch.Datenverwaltung;

@Path("/benutzer")
public class WebService {

	
	// @GET
	// @Produces("text/plain")
	// public String getBenutzer() {
	// return "asdasdsadsadasdsad";
	// }
	//
	
	@GET
	@Produces("text/html")
	public String html() {
		return "<html><title>HelloWorld</title><body><h2>Html: Hallo " 
				+ "</h2></body></html>";
	}

	@GET
	@Produces("text/plain")
	public String halloText(@QueryParam("name") String name) {
		return "Hallo " + name;
	}

//	@GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<JsonObject> holeBenutzer() {
//		Datenverwaltung dv = new Datenverwaltung();
//		List<JsonObject> jsonlist = new ArrayList<JsonObject>();
//		jsonlist = dv.getBenutzer();
//		return jsonlist;
//    }
	
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
	
	
}