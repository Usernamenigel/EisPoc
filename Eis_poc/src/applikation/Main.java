package applikation;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import jsonklassen.IProfil;
import jsonklassen.InterfaceAdapter;
import jsonklassen.Kreis;
import jsonklassen.Pflegender;
import couch.Datenbankverwaltung;

public class Main {

	static Datenbankverwaltung dv;
	static Scanner sc = new Scanner(System.in);
	static Gson gson = new Gson();
//	static Gson gson = new GsonBuilder().registerTypeAdapter(IProfil.class, new InterfaceAdapter<IProfil>())
//            .create();

	public static void main(String[] args) throws InterruptedException, IOException {
		dv = new Datenbankverwaltung();

		Kreis kreis = new Kreis(0);
		kreis.addPflegender(new Pflegender("Hans", "Peter", 0));
		String json = gson.toJson(kreis);
		JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
		dv.addKreis(jsonObj);
		
		List<JsonObject> kralt = dv.getKreis();
		JsonObject js = kralt.get(0);
		
		Kreis kr = gson.fromJson(js, Kreis.class);
		Pflegender mensch = new Pflegender("Nigel", "Schulte", 1);
		kr.addPflegender(mensch);
		
		String jas = gson.toJson(kr);
		JsonObject jsonObj2 = gson.fromJson(jas, JsonObject.class);
		
		dv.setKreis(jsonObj2);
		
	}
}
