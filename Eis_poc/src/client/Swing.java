package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
	static DefaultTableModel modelDementer2 = new DefaultTableModel();
	static JTable tableDementer2 = new JTable(modelDementer2);
	static JScrollPane tableScrollPane2 = new JScrollPane(tableDementer2);
	
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

		final ArrayList<Meinkalender> termine = new ArrayList<Meinkalender>();
		
		JFrame framePflegender = new JFrame("Client Pflegender");
		framePflegender.setSize(750, 500);
		framePflegender.setLocation(500, 450);
		framePflegender.setLayout(new GridLayout(1,2));
		
		JPanel panel2Pflegender = new JPanel(new BorderLayout());
		JPanel panelPflegender = new JPanel(null);
		
		framePflegender.add(panelPflegender);
		framePflegender.add(panel2Pflegender);
		framePflegender.setVisible(true);
		
		//BESCHRIFTUNG
		JLabel pflegenderBeschr = new JLabel("Persoenlicher Kalender");
		pflegenderBeschr.setBounds(125, 5, 150, 25);
		panelPflegender.add(pflegenderBeschr, BorderLayout.NORTH);
		
		//THEMA
		JLabel pflegenderThema = new JLabel("Thema");
		pflegenderThema.setBounds(50, 30, 50, 25);
		final JTextField txtPflegenderThema = new JTextField();
		txtPflegenderThema.setBounds(50, 50, 250, 25);
		txtPflegenderThema.setBorder(BorderFactory.createEtchedBorder());
		panelPflegender.add(pflegenderThema);
		panelPflegender.add(txtPflegenderThema);
		
		//BESCHREIBUNG
		JLabel PflegenderBeschreibung = new JLabel("Beschreibung");
		PflegenderBeschreibung.setBounds(50, 80, 80, 25);
		final JTextField txtPflegenderBeschreibung = new JTextField();
		txtPflegenderBeschreibung.setBounds(50, 100, 250, 25);
		txtPflegenderBeschreibung.setBorder(BorderFactory.createEtchedBorder());
		panelPflegender.add(PflegenderBeschreibung);
		panelPflegender.add(txtPflegenderBeschreibung);
		
		
		//JAHR
		JLabel pflegenderJahr = new JLabel("Jahr");	
		pflegenderJahr.setBounds(50, 130, 50, 25);
		final JTextField clientTextFieldJahr = new JTextField();
		clientTextFieldJahr.setBounds(50, 150, 75, 25);
		clientTextFieldJahr.setBorder(BorderFactory.createEtchedBorder());
		panelPflegender.add(pflegenderJahr);
		panelPflegender.add(clientTextFieldJahr);
		
		//MONAT
		JLabel pflegenderMonat = new JLabel("Monat");
		pflegenderMonat.setBounds(137, 130, 50, 25);	
		final JTextField txtPflegenderMonat = new JTextField();
		txtPflegenderMonat.setBounds(137, 150, 75, 25);
		txtPflegenderMonat.setBorder(BorderFactory.createEtchedBorder());
		panelPflegender.add(pflegenderMonat);
		panelPflegender.add(txtPflegenderMonat);
		
		//TAG
		JLabel pflegenderTag = new JLabel("Tag");
		pflegenderTag.setBounds(225, 130, 50, 25);
		final JTextField txtPflegenderTag = new JTextField();
		txtPflegenderTag.setBounds(225, 150, 75, 25);
		txtPflegenderTag.setBorder(BorderFactory.createEtchedBorder());
		panelPflegender.add(pflegenderTag);
		panelPflegender.add(txtPflegenderTag);
		
		//UHRZEIT
		JLabel pflegenderUhrzeit = new JLabel("Beginn");
		pflegenderUhrzeit.setBounds(75, 180, 100, 25);
		final JTextField txtPflegenderUhr = new JTextField();
		txtPflegenderUhr.setBounds(75, 200, 75, 25);
		txtPflegenderUhr.setBorder(BorderFactory.createEtchedBorder());
		panelPflegender.add(pflegenderUhrzeit);
		panelPflegender.add(txtPflegenderUhr);

		//DAUER
		JLabel pflegenderDauer = new JLabel("Dauer");
		pflegenderDauer.setBounds(200, 180, 100, 25);
		final JTextField txtPflegenderDauer = new JTextField();
		txtPflegenderDauer.setBounds(200, 200, 75, 25);
		txtPflegenderDauer.setBorder(BorderFactory.createEtchedBorder());
		panelPflegender.add(pflegenderDauer);
		panelPflegender.add(txtPflegenderDauer);
		
		//KREIS ID
		JLabel pflegenderKreisId = new JLabel("Kreis-ID");
		pflegenderKreisId.setBounds(140, 325, 75, 25);
		final JTextField txtpflegenderKreis = new JTextField("0");
		txtpflegenderKreis.setBounds(140, 345, 75, 25);
		txtpflegenderKreis.setBorder(BorderFactory.createEtchedBorder());
		panelPflegender.add(pflegenderKreisId);
		panelPflegender.add(txtpflegenderKreis);


		
		final DefaultTableModel model = new DefaultTableModel(); 
		JTable table = new JTable(model); 
		
		model.addColumn("Titel"); 
		model.addColumn("Datum");
		model.addColumn("Uhrzeit");
		model.addColumn("Dauer");
		
		 // Das JTable initialisieren
		 JScrollPane tableScrollPane = new JScrollPane(table);
		 panel2Pflegender.add(tableScrollPane); 
		
		
		//BTN Termin abschicken
		JButton btnPflegenderEintrag = new JButton("Termin eintragen");
		btnPflegenderEintrag.setBounds(75, 250, 200, 50);
		panelPflegender.add(btnPflegenderEintrag);
		// Button Client
		btnPflegenderEintrag.addActionListener(new ActionListener() {
					// Action wenn Button "Client" gedrueckt wird
					public void actionPerformed(ActionEvent e) {
						Meinkalender termin = new Meinkalender(txtPflegenderThema.getText(), txtPflegenderBeschreibung.getText(),
								gibInt(clientTextFieldJahr.getText()),
								gibInt(txtPflegenderMonat.getText()),
								gibInt(txtPflegenderTag.getText()),
								gibInt(txtPflegenderUhr.getText()),
								00,
								gibInt(txtPflegenderDauer.getText()));
						termine.add(termin);
						String zeit = txtPflegenderTag.getText() + "." + txtPflegenderMonat.getText() + "." + clientTextFieldJahr.getText();
						String uhr = txtPflegenderUhr.getText() + ":00";
						String dauer = txtPflegenderDauer.getText() + " Stunden";
						model.addRow(new Object[]{txtPflegenderThema.getText(), zeit, uhr, dauer});
					}
				});
		
		
		//BTN HOLEN UND VERGLEICHEN
		JButton btnPflegenderVergleiche = new JButton("Holen und vergleichen");
		btnPflegenderVergleiche.setBounds(75, 375, 200, 50);
		panelPflegender.add(btnPflegenderVergleiche);

		btnPflegenderVergleiche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JsonArray jarray = new JsonArray();
				jarray = handler.holeTermine(gibInt(txtpflegenderKreis
						.getText()));
				for(int i=0; i<jarray.size(); i++) {
					for(int j=0; j<termine.size(); j++) {
						if(handler.vergleicheTermin(gson.fromJson(jarray.get(i), Kalender.class), termine.get(j))) {
							modelDementer2.addRow(new Object[]{gson.fromJson(jarray.get(i), Kalender.class).getBezeichnung(), "Ja"});
							System.out.println("ASDASDASDSAD");
						}
						else {
							modelDementer2.addRow(new Object[]{gson.fromJson(jarray.get(i), Kalender.class).getBezeichnung(), "Nein"});
							System.out.println("ASDASDASDSAD");
						}
					}
				}

			}
		});
		
	  
		
	}


	private static void oeffneDementer() {
		JFrame frameDementer = new JFrame("Client Dementer");
		frameDementer.setSize(700, 700);
		frameDementer.setLocation(800, 100);
		frameDementer.setLayout(new GridLayout(1,2));

		JPanel panelDementer = new JPanel();
		JPanel panelDementer2 = new JPanel(new BorderLayout());
		panelDementer.setLayout(null);
		
		//TABELLE 1
		final DefaultTableModel modelDementer = new DefaultTableModel(); 
		JTable tableDementer = new JTable(modelDementer); 
		modelDementer.addColumn("Titel"); 
		modelDementer.addColumn("Datum");
		modelDementer.addColumn("Uhrzeit");
		modelDementer.addColumn("Dauer");
		JScrollPane tableScrollPane = new JScrollPane(tableDementer);
		panelDementer2.add(tableScrollPane, BorderLayout.NORTH); 
		
		//TABELLE 2
		modelDementer2 = new DefaultTableModel(); 
		tableDementer2 = new JTable(modelDementer2); 
		modelDementer2.addColumn("Bezeichnung");
		modelDementer2.addColumn("Hat jemand Zeit"); 
		tableScrollPane2 = new JScrollPane(tableDementer2);
		panelDementer2.add(tableScrollPane2, BorderLayout.CENTER); 
		
		
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
		final JTextField txtDementerBeschreibung = new JTextField();
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
		panelDementer.add(btnDemConf);
		
		//BTN TERMINE HOLEN
		JButton btnDementer = new JButton("Alle Termine holen");
		btnDementer.setBounds(0, 320, 200, 75);
		
		
		// Button Client
		btnDemConf.addActionListener(new ActionListener() {
			// Action wenn Button "Client" gedrueckt wird
			public void actionPerformed(ActionEvent e) {
				Kalender termin = new Kalender(txtDementerThema.getText(),
						txtDementerBeschreibung.getText(), new Dementer(),
						gibInt(txtDementerKreis.getText()),
						gibInt(txtDementerKal.getText()),
						gibInt(clientTextFieldJahr.getText()),
						gibInt(txtDementerMonat.getText()),
						gibInt(txtDementerTag.getText()), gibInt(txtDementerUhr
								.getText()), 00, gibInt(txtDementerDauer
								.getText()));
				handler.sendeTermin(termin, termin.getKreisId(), termin.getId());
				String zeit = txtDementerTag.getText() + "." + txtDementerMonat.getText() + "." + clientTextFieldJahr.getText();
				String uhr = txtDementerUhr.getText() + ":00";
				String dauer = txtDementerDauer.getText() + " Stunden";
				modelDementer.addRow(new Object[]{txtDementerThema.getText(), zeit, uhr, dauer});
			}

		});
		
//		btnDementer.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				JsonArray jarray = new JsonArray();
//				jarray = handler.holeTermine(gibInt(txtDementerKreis.getText()));
//				consoleTextArea.append("Folgende Termine aus Kreis Nr: " + gibInt(txtDementerKreis.getText()) + " wurden geholt\n");
//				for(int i=0; i<jarray.size(); i++) {
//					Kalender k = gson.fromJson(jarray.get(i), Kalender.class);
//					consoleTextArea.append("ID: " + k.getId() + " /Titel: " + k.getBezeichnung() + "/Beschreibung: " + k.getBeschreibung() + "\n");
//					consoleTextArea.append("Datum und Uhrzeit: " + k.getJahr() + "-"+ k.getMonat() + "-" + 
//					k.getTag() + " - "+ k.getStunde() + ":" + k.getMinute() + "\n");
//					consoleTextArea.setCaretPosition(consoleTextArea.getDocument().getLength());
//				}
//			}
//		});
		
		frameDementer.add(panelDementer);
		frameDementer.add(panelDementer2);
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

//	
//	  // Die Daten für das Table
//  String[] titel =  {
//    "Name", "Hat Zeit"
//  };
//  
//	String[][] daten = {
//		    { "Japan", "245" }, { "USA", "240" }, { "Italien", "220" },
//		    { "Spanien", "217" }, {"Türkei", "215"} ,{ "England", "214" },
//		    { "Frankreich", "190" }, {"Griechenland", "185" },
//		    { "Deutschland", "180" }, {"Portugal", "170" }
//		    };
//	
// 
//  // Das JTable initialisieren
//  JTable table = new JTable( daten, titel );
//  
//  JPanel panelDementer = new JPanel();
//	panelDementer.setLayout(new BorderLayout());
//	panelDementer.add(table);
//
//	JFrame frameDementer = new JFrame("Client Pflegender");
//	frameDementer.setSize(700, 700);
//	frameDementer.setLocation(800, 100);
//	panelDementer.setVisible(true);
//	frameDementer.add(panelDementer);
//  frameDementer.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//  frameDementer.setVisible( true );
}