package jsonklassen;

public class Meinkalender {

	int jahr, monat, tag, stunde, minute;
	int dauer;
	String titel, beschreibung;
	
	public Meinkalender() {
		
	}
	
	public Meinkalender(String Titel, String Beschreibung, int jahr, int monat, int tag, int stunde, int minute, int dauer) {
		this.jahr = jahr;
		this.monat = monat;
		this.tag = tag;
		this.stunde = stunde;
		this.minute = minute;
		this.dauer = dauer;
		this.titel = titel;
		this.beschreibung = beschreibung;
	}
	
	public int getDauer() {
		return dauer;
	}
	
	public String getTitel() {
		return titel;
	}
	
	public String getBeschreibung() {
		return beschreibung;
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
	
	public int getStunde() {
		return stunde;
	}
	
	public int getMinute() {
		return minute;
	}
}
