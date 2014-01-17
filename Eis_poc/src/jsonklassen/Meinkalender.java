package jsonklassen;

public class Meinkalender {

	int jahr, monat, tag, stunde, minute;
	int dauer;
	
	public Meinkalender() {
		
	}
	
	public Meinkalender(int jahr, int monat, int tag, int stunde, int minute, int dauer) {
		this.jahr = jahr;
		this.monat = monat;
		this.tag = tag;
		this.stunde = stunde;
		this.minute = minute;
		this.dauer = dauer;
	}
	
	public int getDauer() {
		return dauer;
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
