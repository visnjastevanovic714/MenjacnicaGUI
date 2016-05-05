package menjacnica.interfejs;

import java.util.GregorianCalendar;

import menjacnica.Kurs;

public interface MenjacnicaInterface {

	public void dodajKurs(String naziv,Kurs kurs);
	public void obrisiKurs(String naziv,Kurs kurs);
	public Kurs pronadjiVratiKurs(String naziv,GregorianCalendar dan);
	
}
