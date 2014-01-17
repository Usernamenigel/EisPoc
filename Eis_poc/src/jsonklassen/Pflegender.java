package jsonklassen;

import java.util.ArrayList;
import java.util.List;

public class Pflegender implements IProfil {

	List<Meinkalender> meinkalender;
	String nName;
	String vName;
	int kreisId;
	int id;
	
	public Pflegender() {
	}
	
	public Pflegender (String nname, String vname, int kreis, int id) {
		meinkalender = new ArrayList<Meinkalender>();
		nName = nname;
		vName = vname;
		kreisId = kreis;
		this.id = id;
	}
	
	public void addKalenderEintrag(Meinkalender kal) {
		meinkalender.add(kal);
	}
	
	public List<Meinkalender> getKal() {
		return meinkalender;
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
