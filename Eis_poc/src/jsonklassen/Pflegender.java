package jsonklassen;

public class Pflegender implements IProfil {

	String nName;
	String vName;
	int kreisId;
	int id;
	
	public Pflegender() {
	}
	
	public Pflegender (String nname, String vname, int kreis) {
		nName = nname;
		vName = vname;
		kreisId = kreis;
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
