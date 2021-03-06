package alteKlassen;


import java.util.ArrayList;
import java.util.List;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.lightcouch.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Datenverwaltung {

	CouchDbClient dbClient2;
	CouchDbClient dbClient3;
	CouchDbClient dbClient = new CouchDbClient("couchdb.properties");

	CouchDbProperties properties = new CouchDbProperties().setDbName("db-name")
			.setCreateDbIfNotExist(true).setProtocol("https")
			.setHost("example.com").setPort(443).setUsername("username")
			.setPassword("secret").setMaxConnections(100)
			.setConnectionTimeout(0);

	public void an() {
		// CouchDbClient dbClient = new CouchDbClient("couchdb.properties");
		// CouchDbClient dbClient2 = new CouchDbClient("db-name", true, "http",
		// "127.0.0.1", 5984, "username", "secret");
		// CouchDbClient dbClient3 = new CouchDbClient(properties);
	}

	public void aus() {
		// dbClient1.shutdown();
		// dbClient2.shutdown();
		// dbClient3.shutdown();
	}
	
	/**
	 * Eintragen halt
	 * 
	 * @param s1 Attribut
	 * @param s2 Wert
	 */
	public void eintrag(String atr, String wert) {
		JsonObject object = new JsonObject();
		object.addProperty(atr, wert);
		dbClient.save(object);
		System.out.println("Objekt eingetragen mit Attribut: " + atr + ": " + wert);
		
//		String jsonstr = "{" + s1 + ":" + s2 + "}";
//		JsonObject jsonobj = dbClient.getGson().fromJson(jsonstr,JsonObject.class);
//		dbClient.save(jsonobj);
	}

	/**
	 * Aus dem String wird ein Json Objekt erstellt
	 * Danach in die Datenbank gelegt
	 * 
	 * @param s1 Das Attirbut
	 * @param s2 Der Wert
	 */
	public void eintragJson(JsonObject jo) {
		dbClient.save(jo);
		System.out.println("Objekt eingetragen: " + jo);
	}
	
	

	/** 
	 * Durchsucht die Datenbank nach dem Attribut und gibt es aus falls gefunden
	 * @param s Das gesuchte Attribut
	 */
	public void ausgabe(String attribut) {
		// Holle alle Json Dokumente aus der Datenbank (view) und speichere diese in Liste
		// Iteriere und suche nach JSON Dokumenten in der Liste
		// By adding ?include_docs=true you can get the documents themselves, not just their id and rev 
		List<JsonObject> allDocs = dbClient.view("_all_docs").includeDocs(true).query(JsonObject.class);
		for(JsonObject o: allDocs) {
			//Wenn im Dokument das Attribut "s" (z.B. Alter) gefunden wird
			if(o.has(attribut)) {
				//Hole den Wert aus dem Attribut "s" und gebe aus
				String str = o.get(attribut).toString();
				String id = o.get("_id").toString();
				System.out.println("JSON mit Attribut " + attribut + " gefunden, Wert: " + str + " Und ID: " + id);
			}
//			System.out.println(o.toString());
		}
	}
	/**
	 * L�scht das JSON Objekt aus der Datenbank mit dem Namen "wert"
	 * @param wert Der Name des Objektes das gel�scht werden soll
	 */
	public void l�schen(String wert) {
		List<JsonObject> allDocs = dbClient.view("_all_docs").includeDocs(true).query(JsonObject.class);
		for(JsonObject o: allDocs) {
			if(o.has("Name") && (o.get("Name").toString().equals(("\"" + wert + "\"")))) {
//				String id = o.get("_id").toString();
//				String rev = o.get("_rev").toString();
//				dbClient.remove(id, rev);
				dbClient.remove(o);
				System.out.println("Wurde gel�scht");
			}
		}
	}
	
	public List<JsonObject> getBenutzer() {
		List<JsonObject> jsonlist = new ArrayList<JsonObject>();
		jsonlist = dbClient.view("_all_docs").includeDocs(true).query(JsonObject.class);
		return jsonlist;
	}
	
	/**
	 * Der erste Eintrag wird zur�ckgegeben
	 * @return Erster Bentzer in Liste
	 */
	public JsonObject getEinBenutzer() {
		List<JsonObject> jsonlist = new ArrayList<JsonObject>();
		jsonlist = dbClient.view("_all_docs").includeDocs(true).query(JsonObject.class);
		return jsonlist.get(0);
	}

	

	/**
	 * URI der Datenbank und der einzelen
	 */
	public void dbinfo() {
		System.out.println("Die Datenbank-URI: " + dbClient.getDBUri());
		System.out.println("Die Base-URI: " + dbClient.getBaseUri());
	}

}
