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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Path("/benutzer")
public class WebService {

	Gson gson = new Gson();
	
	
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
	
	
	@Path("/json")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String json() {
    	JsonParser parser = new JsonParser();
    	JsonObject o = (JsonObject)parser.parse("{\"a\": \"A\"}");
    	return gson.toJson(o);
    }
	
}



