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
import client.ClientHandler;
import client.Swing;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static Gson gson = new Gson();
//	static Gson gson = new GsonBuilder().registerTypeAdapter(IProfil.class, new InterfaceAdapter<IProfil>())
//            .create();

	public static void main(String[] args) throws InterruptedException, IOException {
		Swing anwendung = new Swing();
		anwendung.an();
	}
}
