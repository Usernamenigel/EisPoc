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
	List<CouchDbClient> handlerlist;
	
	/**
	 * Datenbank Clients erstellen, jeder für eine eigene Datenbank
	 * Diese "Handler" erhalten ihre Properties durch die Property Klasse
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
		
		// HandlerList beinhaltet alle Handler um die Methodenanzahl kleiner zu halten
		handlerlist = new ArrayList<CouchDbClient>();
		handlerlist.add(dbcKreis);
		handlerlist.add(dbcDementer);
		handlerlist.add(dbcPflegender);
		handlerlist.add(dbcTodo);
		handlerlist.add(dbcKalender);
		handlerlist.add(dbcKommentar);
	}
	
	
	/**
	 * Hilfsmethode um den gewünschten Handler zu finden
	 * Methode dient dazu die Methodenanzahl in dieser Klasse klein zu halten
	 * @param Gesuchte Datenbank
	 * @return Den Datenbank Handler(Client)
	 */
	private CouchDbClient getHandler(String db) {
		for(CouchDbClient cdb : handlerlist) {
			if(cdb.getDBUri().toString().equals("http://127.0.0.1:5984/db" + db +"/")) 
				return cdb;
		}
		return null;
	}
	
	
	//________________________ADD METHODEN__________________________//
	/**
	 * Fügt ein neues Objekt in eine gewünschte Datenbank ein
	 * @param Neues JsonObject und die passende Datenbank
	 */
	public void add(JsonObject object, String db) {
		getHandler(db).save(object);
	}
	
	
	//________________________GET_all METHODE__________________________//
	/**
	 * Gibt alle Objekte der gewünschten Datenbank in einer JsonListe zurück
	 * @return JsonList
	 */
	public List<JsonObject> getAll(String db) {
		List<JsonObject> jsonlist = new ArrayList<JsonObject>();
		jsonlist = getHandler(db).view("_all_docs").includeDocs(true).query(JsonObject.class);
		return jsonlist;
	}
	
	//________________________GET_spezifisch METHODE__________________________//
	/** 
	 * By adding ?include_docs=true you can get the documents themselves, not just their id and rev 
	 * 
	 * kreisId bei einem Kreis nicht nötig
	 * KalenderId nur bei Kommentar Eintrag nötig
	 * Durchsucht die passende Datenbank (db) nach objekt-id und kreisId
	 * Falls if=true Dann wird nach einem Kreis gesucht
	 * Falls if else dann ist es ein Kommentar (hier auch KalenderId nötig)
	 * Falls if=false wird in einen der anderen Datenbanken gesucht.
	 * Die Fallunterscheidung ist nötig da alle anderen Objekte eine KreisID zu Identifikation benötigen.
	 * @param id Die ObjektID
	 * @param kreisId Die Kreis ID des Objekts
	 * @param kalenderId
	 *  Das gesuchte Attribut, Db die nötige Datenbank
	 */
	public JsonObject get(int id, int kreisId, int kalenderId, String db) {
		if(("dbc"+db).equals("dbckreis")) {
			List<JsonObject> allDocs = dbcKreis.view("_all_docs").includeDocs(true).query(JsonObject.class);
			for (JsonObject o : allDocs) {
				if (o.get("id").getAsInt() == id) {
					return o;
				}
			}
			return null;
		}
		else if(("dbc"+db).equals("dbckommentar")) {
			List<JsonObject> allDocs = dbcKommentar.view("_all_docs").includeDocs(true).query(JsonObject.class);
			for (JsonObject o : allDocs) {
				if (o.get("id").getAsInt() == id && o.get("kreisId").getAsInt() == kreisId &&
						o.get("kalenderId").getAsInt() == kalenderId) {
					return o;
				}
			}
			return null;
		}
		else {
			List<JsonObject> allDocs = getHandler(db).view("_all_docs").includeDocs(true).query(JsonObject.class);
			for (JsonObject o : allDocs) {
				if (o.get("id").getAsInt() == id && o.get("kreisId").getAsInt() == kreisId) {
					return o;
				}
			}
			return null;
		}
	}
	
	//________________________SET METHODEN__________________________//
	/**
	 * Einen Eintrag modifizieren
	 * In object sind alle Identifkation Informationen gespeichert
	 * Auch hier in der ersten if-klausel wird unterschieden ob "KreisDb", "KommentarDb" oder die anderen db
	 * Es werden alle JsonObjekte der Datenbank in die Liste "allDocs" gespeichert
	 * Die Liste wird iterriert und es wird die Id des neuen und des alten Objekts verglichen
	 * hier ggf. mit KreisId und der KalenderId (bei Kommentaren)
	 * Auch wird die kreisId benötigt.
	 * Wenn richtige Objekt gefunden, wird das Übergebenen JsonObjekt "object" mit der "alten" ID und Rev gefüllt
	 * O wird zu "Kreis" überschrieben und in der DB geupdatet
	 * @param kreis
	 */
	public void set(JsonObject object, String db) {
		if(("dbc"+db).equals("dbckreis")) {
			List<JsonObject> allDocs = dbcKreis.view("_all_docs").includeDocs(true).query(JsonObject.class);
			for(JsonObject o: allDocs) {
				if (o.get("id").getAsInt() == object.get("id").getAsInt()) {
					object.add("_id", o.get("_id"));
					object.add("_rev", o.get("_rev"));
					o = object;
					dbcKreis.update(o);
				}
			}
		}
		else if(("dbc"+db).equals("dbckommentar")) {
			List<JsonObject> allDocs = dbcKommentar.view("_all_docs").includeDocs(true).query(JsonObject.class);
			for (JsonObject o : allDocs) {
				if (o.get("id").getAsInt() == object.get("id").getAsInt() && o.get("kreisId").getAsInt()
						== object.get("kreisId").getAsInt() && o.get("kalenderId").getAsInt()
						== object.get("kalenderId").getAsInt()) {
					object.add("_id", o.get("_id"));
					object.add("_rev", o.get("_rev"));
					o = object;
					dbcKommentar.update(o);
				}
			}
		}
		else {
			List<JsonObject> allDocs = getHandler(db).view("_all_docs").includeDocs(true).query(JsonObject.class);
			for(JsonObject o: allDocs) {
				if (o.get("id").getAsInt() == object.get("id").getAsInt()  &&
						o.get("kreisId").getAsInt() == object.get("kreisId").getAsInt()) {
					object.add("_id", o.get("_id"));
					object.add("_rev", o.get("_rev"));
					o = object;
					getHandler(db).update(o);
				}
			}
		}
	}
	
	//________________________DELETE METHODEN__________________________//
	//________________________MOMENTAN UNBENUTZT__________________________//
	/**
	 * Löscht ein Objet aus der Datenbank.
	 * Wird momentan nicht benötigt und ist nicht up-to-date.
	 * @param Kreis-ID die gelöscht werden soll (nicht _id) u.s.w
	 */
	public void delete(int id, int kreisId, int kalenderId, String db) {
		if(("dbc"+db).equals("dbckreis")) {
			List<JsonObject> allDocs = dbcKreis.view("_all_docs").includeDocs(true).query(JsonObject.class);
			for(JsonObject o: allDocs) {
				if((o.get("id").getAsInt() == id)) {
					dbcKreis.remove(o);
				}
			}
		}
		else if(("dbc"+db).equals("dbckommentar")) {
			List<JsonObject> allDocs = dbcKommentar.view("_all_docs").includeDocs(true).query(JsonObject.class);
			for(JsonObject o: allDocs) {
				if((o.get("id").getAsInt() == id && o.get("kreisId").getAsInt() == kreisId &&
						o.get("kalenderId").getAsInt() == kalenderId)) {
					dbcKommentar.remove(o);
				}
			}
		}
		else {
			List<JsonObject> allDocs = getHandler(db).view("_all_docs").includeDocs(true).query(JsonObject.class);
			for(JsonObject o: allDocs) {
				if((o.get("id").getAsInt() == id && o.get("kreisId").getAsInt() == kreisId)) {
					getHandler(db).remove(o);
				}
			}
		}
	}
}
