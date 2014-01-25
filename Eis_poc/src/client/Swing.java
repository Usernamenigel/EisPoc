package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;

import rest.WebServer;
import jsonklassen.Dementer;
import jsonklassen.Kalender;
import jsonklassen.Kommentar;
import jsonklassen.Kreis;
import jsonklassen.Pflegender;
import jsonklassen.Todo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import couch.Datenbankverwaltung;

public class Swing {

	private JFrame frameDementer;
	private JButton btnDb, btnServer, btnDementerStart, btnPflegender, btnDbFuellen;
	private JPanel panelStart, panelDementer;
	private GridBagLayout glo;
	private Container container;
	private JTextArea meineTextArea;
	private JTextField clientTextField, clienttextField2, clienttextField3;
	private JLabel clientDatum, dementerUhrzeit, dementerThema;
	static Datenbankverwaltung dv;
	static Gson gson = new Gson();
	WebServer server = new WebServer();
	static ClientHandler handler = new ClientHandler();
	static JTextArea consoleTextArea = new JTextArea();
	static DateFormat sdf;
	
	public void an() {
		JFrame frameStart = new JFrame("Beispiel JFrame");
		frameStart.setSize(400, 450);
		frameStart.setLocation(300, 100);
		frameStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Knöpfe
		btnDb = new JButton("Datenbank starten");
		btnServer = new JButton("Server starten");
		btnDementerStart = new JButton("Dementer Clien");
		btnPflegender = new JButton("Pflegenden Client");
		btnDbFuellen = new JButton("DB Fuellen");

		//Knopfe konfigurieren
		btnDb.setBounds(50, 50, 220, 50);
		btnDb.setEnabled(true);
		btnServer.setBounds(50, 150, 300, 50);
		btnDementerStart.setBounds(50, 250, 125, 50);
		btnPflegender.setBounds(225, 250, 125, 50);
		btnDbFuellen.setBounds(270, 50, 80, 50);
		btnDbFuellen.setEnabled(false);

		JPanel panelStart = new JPanel();
		panelStart.setLayout(null);

		panelStart.add(btnPflegender);
		panelStart.add(btnDb);
		panelStart.add(btnServer);
		panelStart.add(btnDementerStart);
		panelStart.add(btnDbFuellen);

		/**
		 * ACTION LISTENER
		 */
		// Button Datenbank
		btnDb.addActionListener(new ActionListener() {
			// Action wenn Button "Datenbank" gedrueckt wird
			public void actionPerformed(ActionEvent e) {
				dv = new Datenbankverwaltung();
				btnDb.setEnabled(false);
				btnDbFuellen.setEnabled(true);
			}
		});
		
		// Button Server
		btnServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					server.serverAn();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				btnServer.setEnabled(false);
			}
		});

		// Button Dementer
		btnDementerStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oeffneDementer();
			}
		});
		
		// Button Pflegender
				btnPflegender.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						oeffnePflegender();
					}
				});

		// Button Fuellen
		btnDbFuellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fuellen();
			}

		});

		frameStart.add(panelStart);
		frameStart.setTitle("Startmenue");
		frameStart.setVisible(true);
	}

	
	protected void oeffnePflegender() {
				
		  // Die Daten für das Table
	    String[] titel =  {
	      "Name", "Hat Zeit"
	    };
	    
		String[][] daten = {
			    { "Japan", "245" }, { "USA", "240" }, { "Italien", "220" },
			    { "Spanien", "217" }, {"Türkei", "215"} ,{ "England", "214" },
			    { "Frankreich", "190" }, {"Griechenland", "185" },
			    { "Deutschland", "180" }, {"Portugal", "170" }
			    };
		
       
        // Das JTable initialisieren
        JTable table = new JTable( daten, titel );
        
        JPanel panelDementer = new JPanel();
		panelDementer.setLayout(new BorderLayout());
		panelDementer.add(table);

		JFrame frameDementer = new JFrame("Client Pflegender");
		frameDementer.setSize(700, 700);
		frameDementer.setLocation(800, 100);
		panelDementer.setVisible(true);
		frameDementer.add(panelDementer);
        frameDementer.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frameDementer.setVisible( true );
	}


	private static void oeffneDementer() {
		JFrame frameDementer = new JFrame("Client Dementer");
		frameDementer.setSize(700, 700);
		frameDementer.setLocation(800, 100);
		frameDementer.setLayout(new BorderLayout());

		JPanel panelDementer = new JPanel();
		panelDementer.setLayout(null);
		
		
		//THEMA
		JLabel dementerThema = new JLabel("Thema");
		dementerThema.setBounds(50, 30, 50, 25);
		final JTextField txtDementerThema = new JTextField();
		txtDementerThema.setBounds(50, 50, 250, 25);
		txtDementerThema.setBorder(BorderFactory.createEtchedBorder());
		panelDementer.add(dementerThema);
		panelDementer.add(txtDementerThema);

		//BESCHREIBUNG
		JLabel dementerBeschreibung = new JLabel("Beschreibung");
		dementerBeschreibung.setBounds(50, 80, 80, 25);
		JTextField txtDementerBeschreibung = new JTextField();
		txtDementerBeschreibung.setBounds(50, 100, 250, 25);
		txtDementerBeschreibung.setBorder(BorderFactory.createEtchedBorder());
		panelDementer.add(dementerBeschreibung);
		panelDementer.add(txtDementerBeschreibung);
		
		//JAHR
		JLabel dementerJahr = new JLabel("Jahr");	
		dementerJahr.setBounds(50, 130, 50, 25);
		final JTextField clientTextFieldJahr = new JTextField();
		clientTextFieldJahr.setBounds(50, 150, 75, 25);
		clientTextFieldJahr.setBorder(BorderFactory.createEtchedBorder());
		panelDementer.add(dementerJahr);
		panelDementer.add(clientTextFieldJahr);
		
		//MONAT
		JLabel dementerMonat = new JLabel("Monat");
		dementerMonat.setBounds(137, 130, 50, 25);	
		final JTextField txtDementerMonat = new JTextField();
		txtDementerMonat.setBounds(137, 150, 75, 25);
		txtDementerMonat.setBorder(BorderFactory.createEtchedBorder());
		panelDementer.add(dementerMonat);
		panelDementer.add(txtDementerMonat);
		
		//TAG
		JLabel dementerTag = new JLabel("Tag");
		dementerTag.setBounds(225, 130, 50, 25);
		final JTextField txtDementerTag = new JTextField();
		txtDementerTag.setBounds(225, 150, 75, 25);
		txtDementerTag.setBorder(BorderFactory.createEtchedBorder());
		panelDementer.add(dementerTag);
		panelDementer.add(txtDementerTag);
		
		//UHRZEIT
		JLabel dementerUhrzeit = new JLabel("Beginn");
		dementerUhrzeit.setBounds(75, 180, 100, 25);
		final JTextField txtDementerUhr = new JTextField();
		txtDementerUhr.setBounds(75, 200, 75, 25);
		txtDementerUhr.setBorder(BorderFactory.createEtchedBorder());
		panelDementer.add(dementerUhrzeit);
		panelDementer.add(txtDementerUhr);

		//DAUER
		JLabel dementerDauer = new JLabel("Dauer");
		dementerDauer.setBounds(200, 180, 100, 25);
		final JTextField txtDementerDauer = new JTextField();
		txtDementerDauer.setBounds(200, 200, 75, 25);
		txtDementerDauer.setBorder(BorderFactory.createEtchedBorder());
		panelDementer.add(dementerDauer);
		panelDementer.add(txtDementerDauer);
		
		//KREIS ID
		JLabel dementerKreisId = new JLabel("Kreis-ID");
		dementerKreisId.setBounds(75, 230, 80, 25);
		final JTextField txtDementerKreis = new JTextField("0");
		txtDementerKreis.setBounds(75, 250, 75, 25);
		txtDementerKreis.setBorder(BorderFactory.createEtchedBorder());
		panelDementer.add(dementerKreisId);
		panelDementer.add(txtDementerKreis);
		
		//KALENDER ID
		JLabel dementerKalenderId = new JLabel("Kalender-ID");
		dementerKalenderId.setBounds(200, 230, 75, 25);
		final JTextField txtDementerKal = new JTextField("0");
		txtDementerKal.setBounds(200, 250, 75, 25);
		txtDementerKal.setBorder(BorderFactory.createEtchedBorder());
		panelDementer.add(dementerKalenderId);
		panelDementer.add(txtDementerKal);
		
		
		
		//BTN Termin abschicken
		JButton btnDemConf = new JButton("Termin eintragen");
		btnDemConf.setBounds(75, 300, 200, 50);
		
		//BTN TERMINE HOLEN
		JButton btnDementer = new JButton("Alle Termine holen");
		btnDementer.setBounds(0, 320, 200, 75);
		
		
		// Button Client
		btnDemConf.addActionListener(new ActionListener() {
			// Action wenn Button "Client" gedrueckt wird
			public void actionPerformed(ActionEvent e) {
				Kalender termin = new Kalender(txtDementerThema.getText(), "Toller Tag", new Dementer(), gibInt(txtDementerKreis.getText()),
						gibInt(txtDementerKal.getText()), gibInt(clientTextFieldJahr.getText()),
						gibInt(txtDementerMonat.getText()), gibInt(txtDementerTag.getText()), gibInt(txtDementerUhr.getText()), 00, 0);
				handler.sendeTermin(termin, termin.getKreisId(), termin.getId());
				consoleTextArea.append("Eintrag eingetragen \n");
				consoleTextArea.append(gson.toJson(termin)+"\n");
				consoleTextArea.setCaretPosition(consoleTextArea.getDocument().getLength());
			}

		});
		
		btnDementer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JsonArray jarray = new JsonArray();
				jarray = handler.holeTermine(gibInt(txtDementerKreis.getText()));
				consoleTextArea.append("Folgende Termine aus Kreis Nr: " + gibInt(txtDementerKreis.getText()) + " wurden geholt\n");
				for(int i=0; i<jarray.size(); i++) {
					Kalender k = gson.fromJson(jarray.get(i), Kalender.class);
					consoleTextArea.append("ID: " + k.getId() + " /Titel: " + k.getBezeichnung() + "/Beschreibung: " + k.getBeschreibung() + "\n");
					consoleTextArea.append("Datum und Uhrzeit: " + k.getJahr() + "-"+ k.getMonat() + "-" + 
					k.getTag() + " - "+ k.getStunde() + ":" + k.getMinute() + "\n");
					consoleTextArea.setCaretPosition(consoleTextArea.getDocument().getLength());
				}
			}
		});
		
		
		panelDementer.add(btnDemConf);
//		panelDementer.add(btnDementer);
		
//		frameDementer.add(dementerThema);
		frameDementer.add(panelDementer);
		frameDementer.setVisible(true);
	}

	private void oeffneConsole() {
		Frame meinFrame3 = new JFrame("This Console !");
		meinFrame3.setSize(800, 200);
		meinFrame3.setLocation(300, 580);
		consoleTextArea.setBounds(10, 10, 780, 160);
		consoleTextArea.setBorder(BorderFactory.createEtchedBorder());
		JButton testButton = new JButton("testButton");
		JPanel consolePanel = new JPanel();
		consolePanel.setLayout(null);
		consolePanel.add(consoleTextArea);
		consolePanel.add(testButton);
		meinFrame3.add(consolePanel);
		meinFrame3.setVisible(true);
	}
	
	public static Integer gibInt(String text) {
		Integer dieDaten = Integer.valueOf(text);
		return dieDaten;
		//}
	}
	public static void gibString(String text) {
		
		System.out.println("Text aus Feld: " + text);
	}

	private static void fuellen() {
		int durchlaeufe = 10;
		for (int i = 0; i < durchlaeufe; i++) {
			Dementer dementer = new Dementer("DementerN" + i, "DementerV" + i, i, i, "hat hunger");
			String dementers = gson.toJson(dementer);
			JsonObject dementerj = gson.fromJson(dementers, JsonObject.class);
			dv.add(dementerj, "dementer");

			Pflegender pflegender = new Pflegender("NachName" + i, i+ "Vorname", i, i);
			String pflegenders = gson.toJson(pflegender);
			JsonObject pflegenderj = gson.fromJson(pflegenders,JsonObject.class);
			dv.add(pflegenderj, "pflegender");

			Kalender kalender = new Kalender("Sauberkeit", "bitte putzen", dementer, i, i, 2000+i, 1+i, 1+i, 1+i, 1+i, 3%(i+1));
			String kalenders = gson.toJson(kalender);
			JsonObject kalenderj = gson.fromJson(kalenders, JsonObject.class);
			dv.add(kalenderj, "kalender");

			Todo todo = new Todo("Bad" + i, i + "bitte putzen", dementer, i);
			String todos = gson.toJson(todo);
			JsonObject todoj = gson.fromJson(todos, JsonObject.class);
			dv.add(todoj, "todo");

			Kommentar kommentar = new Kommentar("Das ist Kommentar Nr: " + i,pflegender, i, i, i);
			String kommentars = gson.toJson(kommentar);
			JsonObject kommentarj = gson.fromJson(kommentars, JsonObject.class);
			dv.add(kommentarj, "kommentar");

			Kreis kreis = new Kreis(i);
			kreis.addPflegender(pflegender);
			kreis.addKalender(kalender);
			kreis.addTodo(todo);
			String kreiss = gson.toJson(kreis);
			JsonObject kreisj = gson.fromJson(kreiss, JsonObject.class);
			dv.add(kreisj, "kreis");
		}
	}

}