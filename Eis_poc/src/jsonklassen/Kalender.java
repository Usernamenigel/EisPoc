package jsonklassen;

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
	IProfil ersteller;
	List<Pflegender> teilnehmer;
	List<Pflegender> verfügbar;	
	List<Kommentar> kommentar;
	int kreisId;
	int id;
	Calendar cal;
	
	public Kalender() {
		Calendar cal = Calendar.getInstance();
	}
	
	public Kalender(String bezeichnung, String beschreibung, IProfil ersteller, int kreisId, int id,
			int jahr, int monat, int tag, int stunde, int minute) {
		this.bezeichnung = bezeichnung;
		this.beschreibung = beschreibung;
		this.ersteller = ersteller;
		this.kreisId = kreisId;
		this.id = id;
//		cal.set(jahr, monat, tag, stunde, minute);
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
	
	public Calendar getKalender() {
		return cal;
	}
	
	public void setKalendar(int jahr, int monat, int tag, int stunde, int minute) {
		cal.set(jahr, monat, tag, stunde, minute);
	}
}

