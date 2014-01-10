package couch;

import java.util.ArrayList;
import java.util.List;

import jsonklassen.*;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.lightcouch.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Datenbankverwaltung {
	
	Gson gson = new Gson();
	CouchDbClient dbcKreis, dbcDementer, dbcPflegender, dbcTodo, dbcKalender, dbcKommentar;
	CouchDbProperties dbcKreisP, dbcDementerP, dbcPflegenderP, dbcTodoP, dbcKalenderP, dbcKommentarP;
	
	/**
	 * Datenbank Clients erstellen, jeder für eine eigene Datenbank
	 */
	public Datenbankverwaltung() {
		dbcKreisP = new CouchDbProperties().setDbName("dbkreis").setCreateDbIfNotExist(true).setProtocol("http")
				.setHost("127.0.0.1").setPort(5984).setMaxConnections(100);
		
		dbcDementerP = new CouchDbProperties().setDbName("dbdementer").setCreateDbIfNotExist(true).setProtocol("http")
				.setHost("127.0.0.1").setPort(5984);
		
		dbcPflegenderP = new CouchDbProperties().setDbName("dbpflegender").setCreateDbIfNotExist(true).setProtocol("http")
				.setHost("127.0.0.1").setPort(5984);
		
		dbcTodoP = new CouchDbProperties().setDbName("dbtodo").setCreateDbIfNotExist(true).setProtocol("http")
				.setHost("127.0.0.1").setPort(5984);
		
		dbcKalenderP = new CouchDbProperties().setDbName("dbkalender").setCreateDbIfNotExist(true).setProtocol("http")
				.setHost("127.0.0.1").setPort(5984);
		
		dbcKommentarP = new CouchDbProperties().setDbName("dbkommentar").setCreateDbIfNotExist(true).setProtocol("http")
				.setHost("127.0.0.1").setPort(5984);
		
		dbcKreis = new CouchDbClient(dbcKreisP);
		
		dbcDementer = new CouchDbClient(dbcDementerP);
		
		dbcPflegender = new CouchDbClient(dbcPflegenderP);
		
		dbcTodo = new CouchDbClient(dbcTodoP);
		
		dbcKalender = new CouchDbClient(dbcKalenderP);
		
		dbcKommentar = new CouchDbClient(dbcKommentarP);
	}

	public void addKreis(JsonObject kreis) {
		dbcKreis.save(kreis);
	}
	
	public List<JsonObject> getKreis() {
		List<JsonObject> jsonlist = new ArrayList<JsonObject>();
		jsonlist = dbcKreis.view("_all_docs").includeDocs(true).query(JsonObject.class);
		return jsonlist;
	}
	
	/**
	 * Einen Kreis-Eintrag modifizieren
	 * 
	 * Es werden alle JsonObjekte in die Liste "allDocs" gespeichert
	 * Diese wird iterriert und es wird die Id des neuen und des alten Objekts verglichen
	 * Wenn richtige Objekt gefunden, wird das Übergebenen JsonObjekt Kreis mit der "alten" ID und Rev gefüllt
	 * O wird zu "Kreis" überschrieben und in der DB geupdatet
	 * @param kreis
	 */
	public void setKreis(JsonObject kreis) {
		List<JsonObject> allDocs = dbcKreis.view("_all_docs").includeDocs(true).query(JsonObject.class);
		for(JsonObject o: allDocs) {
			if(kreis.get("id").equals(o.get("id"))) {
				kreis.add("_id", o.get("_id"));
				kreis.add("_rev", o.get("_rev"));
				o = kreis;
				dbcKreis.update(o);
			}
		}
	}
	
	
}
