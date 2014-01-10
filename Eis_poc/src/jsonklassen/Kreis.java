package jsonklassen;

import java.util.ArrayList;
import java.util.List;

public class Kreis {
	
	int id;
	protected List<Todo> todo;
	protected List<Kalender> kalender;
	protected List<Pflegender> pflegende;
	protected Dementer dementer;
	
	public Kreis() {
	}
	
	public Kreis(int id) {
		this.id = id;
		pflegende = new ArrayList<Pflegender>();
		kalender = new ArrayList<Kalender>();
		todo = new ArrayList<Todo>();
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
	
	public void addPflegender(Pflegender glied) {
		pflegende.add(glied);
	}
	
	public List<Pflegender> getMitglieder() {
		return pflegende;
	}
}
