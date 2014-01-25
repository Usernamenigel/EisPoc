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
import jsonklassen.Meinkalender;
import jsonklassen.Pflegender;
import jsonklassen.Todo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import couch.Datenbankverwaltung;


/**
 * Aus zeitlichen Gründen ist diese Klasse leider sehr unaufgeräumt.
 * Schlecht kommentiert / dokumentiert
 * Scroolbar der Statuskonsole funktioniert nicht 
 * */
public class SwingAlt {

	private JFrame meinFrame2;
	private JButton meinButton1, meinButton2, meinButton3;
	private JButton clientButton1, clientButton2;
	private JPanel meinPanel, clientPanel;
	private GridBagLayout glo;
	private Container container;
	private JTextArea meineTextArea;
	private JTextField clientTextField, clienttextField2, clienttextField3;
	private JLabel clientDatum, clientUhrzeit, clientThema;
	Frame meinFrame3 = new JFrame();
	static Datenbankverwaltung dv;
	static Gson gson = new Gson();
	WebServer server = new WebServer();
	static ClientHandler handler = new ClientHandler();
	static JTextArea consoleTextArea = new JTextArea();
	static DateFormat sdf;
	Dementer dementer;
	Pflegender pflegender;
	static boolean hatZeit;
	Meinkalender mk1;
	
	public void an() {
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		JFrame meinFrame = new JFrame("Beispiel JFrame");
		meinFrame.setSize(400, 450);
		meinFrame.setLocation(300, 100);
		meinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// JTextArea meineTextArea = new JTextArea();
		// meineTextArea.setBounds(50, 300, 300, 50);
		// meineTextArea.setBorder(BorderFactory.createEtchedBorder());

		final JButton meinButton1 = new JButton("Datenbank starten");
		final JButton meinButton2 = new JButton("Server starten");
		JButton meinButton3 = new JButton("Client hinzufuegen");
		final JButton meinButton4 = new JButton("DB Fuellen");

		meinButton1.setBounds(50, 50, 220, 50);
		meinButton1.setEnabled(true);
		meinButton2.setBounds(50, 150, 300, 50);
		meinButton3.setBounds(50, 250, 300, 50);
		meinButton4.setBounds(270, 50, 80, 50);
		meinButton4.setEnabled(false);

		JPanel meinPanel = new JPanel();
		meinPanel.setLayout(null);

		meinPanel.add(meinButton1);
		meinPanel.add(meinButton2);
		meinPanel.add(meinButton3);
		meinPanel.add(meinButton4);

		// Button Datenbank
		meinButton1.addActionListener(new ActionListener() {
			// Action wenn Button "Datenbank" gedrueckt wird
			public void actionPerformed(ActionEvent e) {
				dv = new Datenbankverwaltung();
				meinButton1.setEnabled(false);
				meinButton4.setEnabled(true);
			}
		});
		// Button Server
		meinButton2.addActionListener(new ActionListener() {

			// Action wenn Button "Client" gedrueckt wird
			public void actionPerformed(ActionEvent e) {
				try {
					server.serverAn();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				meinButton2.setEnabled(false);
			}
		});

		// Button Client
		meinButton3.addActionListener(new ActionListener() {
			// Action wenn Button "Client" gedrŸckt wird
			public void actionPerformed(ActionEvent e) {
				System.out.println("ClientButton");
				oeffneClient();
				if(!meinFrame3.isVisible()) {
					oeffneConsole();
				}
			}
		});

		// Button Fuellen
		meinButton4.addActionListener(new ActionListener() {
			// Action wenn Button "Datenbank" gedrueckt wird
			public void actionPerformed(ActionEvent e) {
				fuellen();
			}

		});

		meinFrame.add(meinPanel);
		meinFrame.setTitle("Startmenue");
		meinFrame.setVisible(true);
	}

	private static void oeffneClient() {
		
		JFrame meinFrame2 = new JFrame("This Client !");
		meinFrame2.setSize(400, 450);
		meinFrame2.setLocation(800, 100);
		JLabel clientThema = new JLabel("Thema");
		JLabel clientTag = new JLabel("Tag");
		JLabel clientMonat = new JLabel("Monat");
		JLabel clientJahr = new JLabel("Jahr");
		JLabel clientUhrzeit = new JLabel("Uhrzeit");
		JLabel clientBis = new JLabel("Dauer");
		JLabel clientKreisId = new JLabel("Kreis-ID");
		JLabel clientKalenderId = new JLabel("Kalender-ID");
		
		
		clientThema.setBounds(50, 30, 50, 25);
		clientTag.setBounds(50, 80, 50, 25);
		clientMonat.setBounds(150, 80, 50, 25);		
		clientJahr.setBounds(250, 80, 50, 25);
		clientUhrzeit.setBounds(50, 130, 50, 25);
		clientKreisId.setBounds(50, 200, 80, 25);
		clientKalenderId.setBounds(50, 230, 80, 25);
		clientBis.setBounds(150, 130, 50, 25);
		
		final JTextField clientTextFieldKreis = new JTextField();
		clientTextFieldKreis.setBounds(130, 200, 80, 25);
		clientTextFieldKreis.setBorder(BorderFactory.createEtchedBorder());
		
		final JTextField clientTextFieldKalender = new JTextField();
		clientTextFieldKalender.setBounds(130, 230, 80, 25);
		clientTextFieldKalender.setBorder(BorderFactory.createEtchedBorder());
		
		final JTextField clientTextField2 = new JTextField();
		clientTextField2.setBounds(50, 50, 300, 25);
		clientTextField2.setBorder(BorderFactory.createEtchedBorder());

		final JTextField clientTextFieldTag = new JTextField();
		clientTextFieldTag.setBounds(50, 100, 50, 25);
		clientTextFieldTag.setBorder(BorderFactory.createEtchedBorder());
		
		final JTextField clientTextFieldBis = new JTextField();
		clientTextFieldBis.setBounds(150, 150, 50, 25);
		clientTextFieldBis.setBorder(BorderFactory.createEtchedBorder());
		
		final JTextField clientTextFieldMonat = new JTextField();
		clientTextFieldMonat.setBounds(150, 100, 50, 25);
		clientTextFieldMonat.setBorder(BorderFactory.createEtchedBorder());
		
		final JTextField clientTextFieldJahr = new JTextField();
		clientTextFieldJahr.setBounds(250, 100, 50, 25);
		clientTextFieldJahr.setBorder(BorderFactory.createEtchedBorder());

		final JTextField clientTextField3 = new JTextField();
		clientTextField3.setBounds(50, 150, 50, 25);
		clientTextField3.setBorder(BorderFactory.createEtchedBorder());

		JButton clientButton = new JButton("Fertig");
		clientButton.setBounds(300, 320, 75, 75);
		JButton clientButtonHole = new JButton("Alles holen");
		clientButtonHole.setBounds(0, 320, 200, 75);

		JPanel clientPanel = new JPanel();
		clientPanel.setLayout(null);

		// Button Client
		clientButton.addActionListener(new ActionListener() {
			// Action wenn Button "Client" gedrueckt wird
			public void actionPerformed(ActionEvent e) {
				Kalender termin = new Kalender(clientTextField2.getText(), "Toller Tag", new Dementer(), gibInt(clientTextFieldKreis.getText()),
						gibInt(clientTextFieldKalender.getText()), gibInt(clientTextFieldJahr.getText()),
						gibInt(clientTextFieldMonat.getText()), gibInt(clientTextFieldTag.getText()), gibInt(clientTextField3.getText()), 00, gibInt(clientTextFieldBis.getText()));
				handler.sendeTermin(termin, termin.getKreisId(), termin.getId());
				consoleTextArea.append("Eintrag eingetragen \n");
				consoleTextArea.append(gson.toJson(termin)+"\n");
				consoleTextArea.setCaretPosition(consoleTextArea.getDocument().getLength());
			}

		});
		
		Pflegender hans = new Pflegender("Hans", "Peter", 0, 0);
		
		final Meinkalender mk1 = new Meinkalender(2014, 01, 01, 10, 10, 10);
		
//		hans.addKalenderEintrag(kal);
		
		clientButtonHole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JsonArray jarray = new JsonArray();
				jarray = handler.holeTermine(gibInt(clientTextFieldKreis.getText()));
				for(int i=0; i<jarray.size(); i++) {
					Kalender k = gson.fromJson(jarray.get(i), Kalender.class);
					if(handler.vergleicheTermin(k, mk1)) {
						hatZeit = false;
					}
					if(hatZeit == false) {
						consoleTextArea.append("Hat leider keine Zeit");
					}
				}
				consoleTextArea.append("Wunderbar hat Zeit");
				
			}
		});
		
//		consoleTextArea.append("Folgende Termine aus Kreis Nr: " + gibInt(clientTextFieldKreis.getText()) + " wurden geholt\n");
//		for(int i=0; i<jarray.size(); i++) {
//			Kalender k = gson.fromJson(jarray.get(i), Kalender.class);
//			consoleTextArea.append("ID: " + k.getId() + " /Titel: " + k.getBezeichnung() + "/Beschreibung: " + k.getBeschreibung() + "\n");
//			consoleTextArea.append("Datum und Uhrzeit: " + k.getJahr() + "-"+ k.getMonat() + "-" + 
//			k.getTag() + " - "+ k.getStunde() + ":" + k.getMinute() + "\n");
//			consoleTextArea.setCaretPosition(consoleTextArea.getDocument().getLength());
//		}
		

		clientPanel.add(clientThema);
		clientPanel.add(clientTag);
		clientPanel.add(clientMonat);
		clientPanel.add(clientJahr);
		clientPanel.add(clientUhrzeit);
		clientPanel.add(clientButton);
		clientPanel.add(clientButtonHole);
		clientPanel.add(clientTextFieldTag);
		clientPanel.add(clientTextFieldMonat);
		clientPanel.add(clientTextFieldJahr);
		clientPanel.add(clientTextField2);
		clientPanel.add(clientTextField3);
		clientPanel.add(clientKreisId);
		clientPanel.add(clientKalenderId);
		clientPanel.add(clientTextFieldKalender);
		clientPanel.add(clientTextFieldBis);
		clientPanel.add(clientBis);
		clientPanel.add(clientTextFieldKreis);
		

		// meinFrame2.add(clientThema);
		meinFrame2.add(clientPanel);
		meinFrame2.setVisible(true);
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
		int durchlaeufe = 11;
		for (int i = 1; i < durchlaeufe; i++) {
			Dementer dementer = new Dementer("DementerN" + i, "DementerV" + i, i, i, "hat hunger");
			String dementers = gson.toJson(dementer);
			JsonObject dementerj = gson.fromJson(dementers, JsonObject.class);
			dv.add(dementerj, "dementer");
			
			Pflegender pflegender = new Pflegender("NachName" + i, i+ "Vorname", i, i);
			String pflegenders = gson.toJson(pflegender);
			JsonObject pflegenderj = gson.fromJson(pflegenders,JsonObject.class);
			dv.add(pflegenderj, "pflegender");

			Kalender kalender = new Kalender("Sauberkeit", "bitte putzen", dementer, i, i, 2000+i, 1+i, 1+i, 1+i, 1+i, 3%i);
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