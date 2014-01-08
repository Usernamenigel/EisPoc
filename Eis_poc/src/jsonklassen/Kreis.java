package jsonklassen;

import java.util.List;

public class Kreis {
	
	int id;
	protected List<Todo> todo;
	protected List<Kalender> kalender;
	protected List<IProfil> mitglieder;
	
	public Kreis(int id) {
		this.id = id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void addTodo(Todo eintrag) {
		todo.add(eintrag);
	}
	
	public List<Todo> getTodo() {
		return todo;
	}
	
	public void addKalender(Kalender eintrag) {
		kalender.add(eintrag);
	}
	
	public List<Kalender> getKalender() {
		return kalender;
	}
	
	public void addMitglied(IProfil glied) {
		mitglieder.add(glied);
	}
	
	public List<IProfil> getMitglieder() {
		return mitglieder;
	}
}
