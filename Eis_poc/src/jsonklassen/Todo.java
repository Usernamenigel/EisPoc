package jsonklassen;

public class Todo {
	
	String bezeichnung;
	String beschreibung;
	IProfil ersteller;
	Boolean istErledigt;
	IProfil zustaendig;
	int id;
	
	public Todo(String bezeichnung, String beschreibung, IProfil ersteller) {
		istErledigt = false;
		this.bezeichnung = bezeichnung;
		this.beschreibung = beschreibung;
		this.ersteller = ersteller;
	}
	
	public IProfil getErsteller() {
		return ersteller;
	}
	
	public String getBezeichnung() {
		return bezeichnung;
	}
	
	public String getBeschreibung() {
		return beschreibung;
	}
	
	public void erledigt(IProfil freiwilliger) {
		istErledigt = true;
		zustaendig = freiwilliger;
	}
	
	public Boolean istErledigt() {
		return istErledigt;
	}
	
	public IProfil getZustaendiger() {
		return zustaendig;
	}

}
