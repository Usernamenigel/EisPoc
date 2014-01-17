package jsonklassen;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Kalender {
	
	/**
	 * <IProfil> Liste durch konkrete Klassen ersetzt
	 * <Pflegender> teilnehmer (alle die Zeit haben [ifHatZeit()])
	 * 
	 */
	String bezeichnung;
	String beschreibung;
	Dementer ersteller;
	List<Pflegender> teilnehmer;
	List<Pflegender> verfuegbar;	
	List<Kommentar> kommentar;
	int dauer;
	int kreisId;
	int id;
	int jahr, monat, tag, stunde, minute;
	
	public Kalender() {
		
	}
		
	public Kalender(String bezeichnung, String beschreibung, Dementer ersteller, int kreisId, int id,
			int jahr, int monat, int tag, int stunde, int minute, int dauer) {
		this.bezeichnung = bezeichnung;
		this.beschreibung = beschreibung;
		this.ersteller = ersteller;
		this.kreisId = kreisId;
		this.id = id;
		this.jahr = jahr;
		this.monat = monat;
		this.tag = tag;
		this.stunde = stunde;
		this.minute = minute;
		this.dauer = dauer;

	}
	
	public String getBezeichnung() {
		return bezeichnung;
	}
	
	public String getBeschreibung() {
		return beschreibung;
	}
	
	public IProfil getErsteller() {
		return ersteller;
	}
	
	public int getKreisId() {
		return kreisId;
	}
	
	/**
	 * Auch hier musste <IProfil> durch konkrete Klasse ersetzt werden
	 * da GSON Probleme bereitet
	 * @return teilnehmenden Personen
	 */
	public List<Pflegender> getTeilnehmer() {
		return teilnehmer;
	}
	
	public List<Kommentar> getKommentar() {
		return kommentar;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public int getJahr() {
		return jahr;
	}
	
	public int getMonat() {
		return monat;
	}
		
	public int getTag() {
		return tag;
	}
	
	public int getDauer() {
		return dauer;
	}
	
	public int getStunde() {
		return stunde;
	}
	
	public int getMinute() {
		return minute;
	}
	
}

