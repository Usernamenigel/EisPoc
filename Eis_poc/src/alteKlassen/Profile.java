package alteKlassen;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {
	
	public String vname;
	public String nname;
	public int alter;
	public boolean kinder;
	
	public Profile() {
	}
	
	public String getVname() {
		return vname;
	}
	
	public String getNname() {
		return nname;
	}
	
	public int getAlter() {
		return alter;
	}
	
	public boolean hatKinder() {
		return kinder;
	}
	
	public void setVname(String name) {
		vname = name;
	}
	
	public void setNname(String name) {
		nname = name;
	}
	
	public void setAlter(int alter) {
		this.alter = alter;
	}
	
	public void setKinder(boolean x) {
		kinder = x;
	}

}
