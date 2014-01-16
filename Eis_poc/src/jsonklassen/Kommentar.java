package jsonklassen;

public class Kommentar {
	
	int id;
	int kreisId;
	int kalenderId;
	String kommentar;
	IProfil ersteller;
	
	public Kommentar() {
	}
	
	public Kommentar(String kommentar, IProfil ersteller, int kalenderId, int id) {
		this.kommentar = kommentar;
		this.ersteller = ersteller;
		this.kalenderId = kalenderId;
		this.id = id;
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
	
	public void setKalenderId(int id) {
		kalenderId = id;
	}
	
	public void setKreisId(int id) {
		kreisId = id;
	}
	
	public int getKreisId() {
		return kreisId;
	}
	
	public int getKalenderId() {
		return kalenderId;
	}

}
