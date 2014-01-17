package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import rest.WebServer;
import jsonklassen.Dementer;
import jsonklassen.Kalender;
import jsonklassen.Kommentar;
import jsonklassen.Kreis;
import jsonklassen.Pflegender;
import jsonklassen.Todo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import couch.Datenbankverwaltung;

public class Swing {

	private JFrame meinFrame2;
	private JButton meinButton1, meinButton2, meinButton3;
	private JButton clientButton1, clientButton2;
	private JPanel meinPanel, clientPanel;
	private GridBagLayout glo;
	private Container container;
	private JTextArea meineTextArea;
	private JTextField clientTextField, clienttextField2, clienttextField3;
	private JLabel clientDatum, clientUhrzeit, clientThema;
	static Datenbankverwaltung dv;
	static Gson gson = new Gson();
	WebServer server = new WebServer();

	public void an() {
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
			// Action wenn Button "Client" gedrückt wird
			public void actionPerformed(ActionEvent e) {
				System.out.println("ClientButton");
				oeffneClient();
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

		clientThema.setBounds(50, 30, 50, 25);
		clientTag.setBounds(50, 80, 50, 25);
		clientMonat.setBounds(150, 80, 50, 25);		
		clientJahr.setBounds(250, 80, 50, 25);
		clientUhrzeit.setBounds(50, 130, 50, 25);
		
		final JTextField clientTextField2 = new JTextField();
		clientTextField2.setBounds(50, 50, 300, 25);
		clientTextField2.setBorder(BorderFactory.createEtchedBorder());

		final JTextField clientTextFieldTag = new JTextField();
		clientTextFieldTag.setBounds(50, 100, 50, 25);
		clientTextFieldTag.setBorder(BorderFactory.createEtchedBorder());
		
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
		clientButton.setBounds(340, 370, 50, 50);

		JPanel clientPanel = new JPanel();
		clientPanel.setLayout(null);

		// Button Client
		clientButton.addActionListener(new ActionListener() {
			// Action wenn Button "Client" gedrueckt wird
			public void actionPerformed(ActionEvent e) {
				System.out.println("clientButton wurde gedrieckt");
				// String meinWert = clientTextField.getText();
				// System.out.println("Text aus Feld:"+meinWert);
				gibString(clientTextField2.getText());
				gibInt(clientTextFieldTag.getText());
				gibInt(clientTextFieldMonat.getText());
				gibInt(clientTextFieldJahr.getText());
				gibInt(clientTextField3.getText());

			}

		});

		clientPanel.add(clientThema);
		clientPanel.add(clientTag);
		clientPanel.add(clientMonat);
		clientPanel.add(clientJahr);
		clientPanel.add(clientUhrzeit);
		clientPanel.add(clientButton);
		clientPanel.add(clientTextFieldTag);
		clientPanel.add(clientTextFieldMonat);
		clientPanel.add(clientTextFieldJahr);
		clientPanel.add(clientTextField2);
		clientPanel.add(clientTextField3);

		// meinFrame2.add(clientThema);
		meinFrame2.add(clientPanel);
		meinFrame2.setVisible(true);
	}

	public static Integer gibInt(String text) {
		Integer dieDaten = Integer.valueOf(text);
		
//		if(dieDaten == null){
//			System.out.println("Sir, Sie haben nichts eingetragen");
//			return 0;
//			}
//		else{
//		//String derInteger = text;
////		Integer dieDaten = Integer.valueOf(text);
		System.out.println("Der Integer "+dieDaten);
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

			Kalender kalender = new Kalender("heute", "bitte putzen", pflegender, i, i, 2002, 11, 11, 11, 11);
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