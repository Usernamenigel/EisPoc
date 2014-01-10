package jsonklassen;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Kalender {

	String bezeichnung;
	String beschreibung;
	IProfil ersteller;
	List<IProfil> teilnehmer;
	List<Kommentar> kommentar;
	int kreisId;
	int id;
	Calendar cal;
	
	public Kalender() {
	}
	
	public Kalender(String bezeichnung, String beschreibung, IProfil ersteller, int kreisId,
			int jahr, int monat, int tag, int stunde, int minute) {
		this.bezeichnung = bezeichnung;
		this.beschreibung = beschreibung;
		this.ersteller = ersteller;
		this.kreisId = kreisId;
		cal.set(jahr, monat, tag, stunde, minute);
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
	
	public List<IProfil> getTeilnehmer() {
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

