import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;






import rest.WebClient;
import rest.WebServer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jsonklassen.Profil;
import couch.Datenverwaltung;

public class Tester {

	static Datenverwaltung dv = new Datenverwaltung();
	static Scanner sc = new Scanner(System.in);
	static WebServer ws = new WebServer();
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("Hallo, Git testen");
//		Thread.sleep(10000);
		dv.dbinfo();
//		hinzufuegen();
//		suche();
//		löschen();
//		benutzer();
		restAn();
//		clientAn();
		Thread.sleep(10*60*1000);
		restAus();
	}

	
	private static void restAn() throws IllegalArgumentException, IOException, InterruptedException {
		ws.serverAn();
	}
	
	private static void restAus() {
		ws.serverAus();
	}
	
	private static void clientAn() {
		WebClient wc = new WebClient();
		wc.starte();
	}

	private static void löschen() {
		String s1;
		System.out.println("Welche Person löschen: ");
		s1 = sc.next();
		dv.löschen(s1);
	}

	private static void hinzufuegen() {
		String s1;
		String s2;
		System.out.println("Attribut eingeben: ");
		s1 = sc.next();
		System.out.println("Den Wert: ");
		s2 = sc.next();
		dv.eintrag(s1, s2);
	}
	
	public static void suche() {
		String s3;
		System.out.println("Nach welchem Attribut suchen?: ");
		s3 = sc.next();
		dv.ausgabe(s3);
	}
	
	public static void benutzer() {
		
		Profil profil = new Profil();
		profil.setAlter(20);
		profil.setKinder(false);
		profil.setNname("Hallo");
		profil.setVname("Peter");
		
		// Marshalling
		Gson gson = new Gson();
		String json = gson.toJson(profil);  
		
		JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
		JsonObject obj = gson.fromJson("{\"vname\":\"Peter\"}", JsonObject.class);
		
		jsonObj.addProperty("verheiratet", true);
		System.out.println("Hier der String: " + json);
		System.out.println("Hier das JsonObject" + jsonObj);
		System.out.println("Hier das JsonObject2: " + obj.toString());
		
		// Unmarshalling
		Profil profil2 = gson.fromJson(jsonObj, Profil.class); 
	}
	
	public static void daten() {
		
	}
	
	
}
