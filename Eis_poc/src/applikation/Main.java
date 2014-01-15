package applikation;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import jsonklassen.Dementer;
import jsonklassen.IProfil;
import jsonklassen.InterfaceAdapter;
import jsonklassen.Kalender;
import jsonklassen.Kommentar;
import jsonklassen.Kreis;
import jsonklassen.Pflegender;
import jsonklassen.Todo;
import couch.Datenbankverwaltung;

public class Main {

	static Datenbankverwaltung dv;
	static Scanner sc = new Scanner(System.in);
	static Gson gson = new Gson();
//	static Gson gson = new GsonBuilder().registerTypeAdapter(IProfil.class, new InterfaceAdapter<IProfil>())
//            .create();

	public static void main(String[] args) throws InterruptedException, IOException {
		dv = new Datenbankverwaltung();
		fuellen();
	}
	
	private static void fuellen() {
		Dementer dementer = new Dementer("DementerV", "DementerV", 0, "hat hunger");
		Pflegender pflegender = new Pflegender("NachName", "Vorname", 0);
//		Kalender kalender = new Kalender("heute", "bitte putzen", pflegender, 0, 2002, 11, 11, 11, 11);
		Kalender kalender = new Kalender();
		Todo todo = new Todo("Bad", "bitte putzen", dementer, 0);
		Kommentar kommentar = new Kommentar("Das ist ein Kommentar", pflegender);
		Kreis kreis = new Kreis(0);
		
		String dementers = gson.toJson(dementer);
		String pflegenders = gson.toJson(pflegender);
		String kalenders = gson.toJson(kalender);
		String todos = gson.toJson(todo);
		String kommentars = gson.toJson(kommentar);
		String kreiss = gson.toJson(kreis);
		
		JsonObject dementerj = gson.fromJson(dementers, JsonObject.class);
		JsonObject pflegenderj = gson.fromJson(pflegenders, JsonObject.class);
		JsonObject kalenderj = gson.fromJson(kalenders, JsonObject.class);
		JsonObject todoj = gson.fromJson(todos, JsonObject.class);
		JsonObject kommentarj = gson.fromJson(kommentars, JsonObject.class);
		JsonObject kreisj = gson.fromJson(kreiss, JsonObject.class);
		
		dv.add(dementerj, "dementer");
		dv.add(pflegenderj, "pflegender");
		dv.add(kalenderj, "kalender");
		dv.add(todoj, "todo");
		dv.add(kommentarj, "kommentar");
		dv.add(kreisj, "kreis");
	}
}
