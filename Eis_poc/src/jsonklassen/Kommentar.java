package jsonklassen;

public class Kommentar {
	
	int id;
	String kommentar;
	IProfil ersteller;
	
	public Kommentar(String kommentar, IProfil ersteller) {
		this.kommentar = kommentar;
		this.ersteller = ersteller;
	}
	
	public String getKommentar() {
		return kommentar;
	}
	
	public IProfil getErsteller() {
		return ersteller;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
