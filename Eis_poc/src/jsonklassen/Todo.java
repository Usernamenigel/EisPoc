package jsonklassen;

public class Todo {
	
	String bezeichnung;
	String beschreibung;
	IProfil ersteller;
	Boolean istErledigt;
	IProfil zustaendig;
	int id;
	int kreisId;
	
	public Todo() {
	}
	
	public Todo(String bezeichnung, String beschreibung, IProfil ersteller, int kreisId) {
		istErledigt = false;
		this.bezeichnung = bezeichnung;
		this.beschreibung = beschreibung;
		this.ersteller = ersteller;
		this.kreisId = kreisId;
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
	
	public void setid(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public int getKreisid() {
		return kreisId;
	}
	
	public void setKreisId(int id) {
		kreisId = id;
	}

}
