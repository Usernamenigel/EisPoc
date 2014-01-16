package jsonklassen;

public class Dementer implements IProfil {

	String nName;
	String vName;
	int id;
	int kreisId;
	String beschreibung;
	
	public Dementer() {
	}
	
	public Dementer(String Vname, String Nname,int id , int kreis, String beschreibung) {
		nName = Nname;
		vName = Vname;
		kreisId = kreis;
		this.id = id;
		this.beschreibung = beschreibung;
	}
	
	public String getNname() {
		return nName;
	}

	public void setNname(String name) {
		nName = name;
	}

	public String getVname() {
		return vName;
	}

	public void setVname(String name) {
		vName = name;
	}

	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	
	public int getKreis() {
		return kreisId;
	}

	public void setKreis(int id) {
		kreisId = id;
	}

}
