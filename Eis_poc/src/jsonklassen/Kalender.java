package jsonklassen;

import java.util.List;

public class Kalender {

	String bezeichnung;
	String beschreibung;
	IProfil ersteller;
	List<IProfil> teilnehmer;
	List<Kommentar> kommentar;
	
	public Kalender(String bezeichnung, String beschreibung, IProfil ersteller) {
		this.bezeichnung = bezeichnung;
		this.beschreibung = beschreibung;
		this.ersteller = ersteller;
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
}
