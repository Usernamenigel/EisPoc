import java.util.Scanner;

import couch.Datenverwaltung;

public class Tester {

	static Datenverwaltung dv = new Datenverwaltung();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hallo, Git testen");
//		Thread.sleep(10000);
		dv.dbinfo();
//		ausmachen();
		hinzufuegen();
		suche();
	}

	private static void hinzufuegen() {
		String s1;
		String s2;
		System.out.println("Attribut eingeben: ");
		s1 = sc.next();
		System.out.println("Den Wert: ");
		s2 = sc.next();
		dv.eintrag(s1, s2);
	}
	
	public static void suche() {
		String s3;
		System.out.println("Nach welchem Attribut suchen?: ");
		s3 = sc.next();
		dv.ausgabe(s3);
	}
	
	
}
