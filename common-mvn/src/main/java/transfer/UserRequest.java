package transfer;

import java.io.Serializable;
/**
 * Klasa koja predstavlja zahtev od strane klijenta.
 * 
 * Zahtev se sastoji od operacije koja treba da se izvrsi i parametara 
 * potrebnih za izvrsenje operacije. Operacija predstavlja finalnu celobrojnu
 * vrednost iz klase Operations, a parametar je objekat koji klijent salje.
 * 
 * Klasa implementira interfejs Serializable kako bi se objekat prenosio preko mreze.
 * 
 * @author Vanja Jankovic
 *
 */
public class UserRequest implements Serializable{
	/**
	 * Operacija iz klijentskog zahteva kao Integer.
	 * 
	 * Dobija vrednost iz konstante klase Operations koja predstavlja
	 * operaciju koju je potrebno izvrsiti.
	 */
	private int operation;
	/**
	 * Parametar kao Object koji klijent salje kroz klijentski zahtev.
	 */
    private Object parametar;
    /**
     * Vraca operaciju klijentskog zahteva.
     * 
     * @return Operacija klijentskog zahteva kao int iz klase Operations.
     */
    public int getOperation() {
        return operation;
    }
/**
 * Postavlja operaciju klijentskog zahteva.
 * 
 * @param operation Operacija klijentskog zahteva kao int iz klase Operacija.
 */
    public void setOperation(int operation) {
        this.operation = operation;
    }
/**
 * Vraca parametar iz klijentskog zahteva.
 * 
 * @return Parametar iz klijentskog zahteva kao objekat klase Object.
 */
    public Object getParametar() {
        return parametar;
    }
/**
 * Postavlja parametar iz klijentskog zahteva na prosledjenu vrednost.
 * 
 * @param parametar Parametar iz klijentskog zahteva kao objekat klase Object. 
 */
    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }
}
