package applikation;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import rest.WebServer;

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
import client.Swing;

public class Main {

	//static Datenbankverwaltung dv;
	static Scanner sc = new Scanner(System.in);
	static Gson gson = new Gson();
//	static Gson gson = new GsonBuilder().registerTypeAdapter(IProfil.class, new InterfaceAdapter<IProfil>())
//            .create();

	public static void main(String[] args) throws InterruptedException, IOException {
		//dv = new Datenbankverwaltung();
//		fuellen();
		//WebServer server = new WebServer();
		Swing anwendung = new Swing();
		anwendung.an();
		//server.serverAn();
	}
	
//	private static void fuellen() {
//		int durchlaeufe = 10;
//		
//		for(int i=0; i<durchlaeufe; i++) {
//			
//			Dementer dementer = new Dementer("DementerN"+i, "DementerV"+i, i, i, "hat hunger");
//			String dementers = gson.toJson(dementer);
//			JsonObject dementerj = gson.fromJson(dementers, JsonObject.class);
//			dv.add(dementerj, "dementer");
//			
//			Pflegender pflegender = new Pflegender("NachName"+i, i+"Vorname", i, i);
//			String pflegenders = gson.toJson(pflegender);
//			JsonObject pflegenderj = gson.fromJson(pflegenders, JsonObject.class);
//			dv.add(pflegenderj, "pflegender");
//			
//			Kalender kalender = new Kalender("heute", "bitte putzen", pflegender, i, i, 2002, 11, 11, 11, 11);
//			String kalenders = gson.toJson(kalender);
//			JsonObject kalenderj = gson.fromJson(kalenders, JsonObject.class);
//			dv.add(kalenderj, "kalender");
//			
//			Todo todo = new Todo("Bad"+i, i+"bitte putzen", dementer, i);
//			String todos = gson.toJson(todo);
//			JsonObject todoj = gson.fromJson(todos, JsonObject.class);
//			dv.add(todoj, "todo");
//			
//
//			Kommentar kommentar = new Kommentar("Das ist Kommentar Nr: "+i, pflegender,i, i, i);
//			String kommentars = gson.toJson(kommentar);
//			JsonObject kommentarj = gson.fromJson(kommentars, JsonObject.class);
//			dv.add(kommentarj, "kommentar");
//
//			Kreis kreis = new Kreis(i);
//			kreis.addPflegender(pflegender);
//			kreis.addKalender(kalender);
//			kreis.addTodo(todo);
//			String kreiss = gson.toJson(kreis);
//			JsonObject kreisj = gson.fromJson(kreiss, JsonObject.class);
//			dv.add(kreisj, "kreis");
//			
//		}
//	}
}
