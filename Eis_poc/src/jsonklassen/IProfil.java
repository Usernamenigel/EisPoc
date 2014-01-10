
package jsonklassen;

import java.io.Serializable;

public interface IProfil extends Serializable {
	
	public String getNname();
	
	public void setNname(String name);
	
	public String getVname();
	
	public void setVname(String name);
	
	public int getId();
	
	public void setId(int id);
	
	public int getKreis();
	
	public void setKreis(int id);

}
