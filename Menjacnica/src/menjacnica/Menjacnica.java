package menjacnica;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import menjacnica.interfejs.MenjacnicaInterface;

public class Menjacnica implements MenjacnicaInterface {

	LinkedList<Valuta> valute = new LinkedList<Valuta>();
	
	@Override
	public void dodajKurs(String naziv, Kurs kurs) {
		if(naziv==null || kurs==null)
			throw new RuntimeException("Greska pri unosu kursa ili naziva valute");
		for(int i=0;i<valute.size();i++){
			if(valute.get(i).getNaziv().equals(naziv))
				valute.get(i).getKursevi().add(kurs);
		}
	}

	@Override
	public void obrisiKurs(String naziv, Kurs kurs) {
		if(naziv==null || kurs==null)
			throw new RuntimeException("Greska pri unosu kursa ili naziva valute");
		for(int i=0;i<valute.size();i++){
			if(valute.get(i).getNaziv().equals(naziv))
				valute.get(i).getKursevi().remove(kurs);
		}
	}

	@Override
	public Kurs pronadjiVratiKurs(String naziv, GregorianCalendar dan) {
		if(naziv==null || dan==null)
			throw new RuntimeException("Greska pri unosu dana ili naziva valute");
		Kurs k = new Kurs();
		for(int i=0;i<valute.size();i++){
			if(valute.get(i).getNaziv().equals(naziv)){
				for(int j=0;j<valute.get(i).getKursevi().size();j++){
					if(valute.get(i).getKursevi().get(j).getDatum().equals(dan))
						k = valute.get(i).getKursevi().get(j);
				}			
			}
				
		}
		return k;
	}

	@Override
	public void ucitajIzFajla(String putanja) {
			try {
				ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(putanja)));

				LinkedList<Valuta> novaLista = (LinkedList<Valuta>) (in.readObject());
				valute.clear();
				valute.addAll(novaLista);

				in.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	@Override
	public void sacuvajUFajl(String putanja) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(putanja)));

			out.writeObject(valute);

			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
