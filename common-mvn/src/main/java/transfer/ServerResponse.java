package transfer;

import java.io.Serializable;
/**
 * Klasa koja predstavlja odgovor servera na klijentski zahtev.
 * 
 * Odgovor se sastoji iz podataka koji se salju klijentu, vrednosti koja 
 * oznacava da li je izvrsavanje operacije uspesno ili ne i od izuzetka
 * koji se moguce desio prilikom izvrsavanja operacije.
 * 
 * Podaci su rezultat izvrsavanja operacije.
 * Vrednost koja se salje kao odgovor moze imati vrednost 1, cime oznacava da
 * je izvrsavanje operacije bilo uspesno, a moze imati i vrednost razlicitu
 * od 1, cime se oznacava neuspesno izvrsavanje operacije.
 * 
 * Klasa ServerResponse implementira interfejs Serializable kako bi se objekat
 * prenosio preko mreze.
 * 
 * @author Vanja Jankovic
 *
 */
public class ServerResponse implements Serializable{
	/**
	 * Podaci koji se salju klijentu kao odgovor.
	 * 
	 * Predstavljaju rezultat izvrsavanja operacije.
	 */
	private Object data;
	/**
	 * Vrednost koja oznacava uspesnost izvrsavanja operacije.
	 * Ako je vrednost 1, operacija je izvrsena uspesno.
	 * Ako je vrednost razlicita od 1, operacija je neuspesna.
	 */
    private int succesfull; // 1 - success, else failed
    /**
     * Izuzetak koji je moguce da se dogodio prilikom izvrsenja
     * operacije.
     * Ako do greske nije doslo, izuzetak je null.
     */
    private Exception exception;
/**
 * Vraca podatke serverskog odgovora.
 * 
 * @return Podaci serverskog odgovora kao objekat klase Object.
 */
    public Object getData() {
        return data;
    }
/**
 * Postavlja vrednost podataka serverskog odgovora.
 * 
 * @param data Podaci serverskog odgovora kao objekat klase Object.
 */
    public void setData(Object data) {
        this.data = data;
    }
/**
 * Vraca vrednost uspesnosti izvrsavanja operacije.
 * 
 * @return Vrednost uspesnosti izvrsavanja operacije kao Integer.
 * <ul>
 * <li>1 - uspesno izvrsena operacija</li>
 * <li>else - neuspesno izvrsena operacija</li>
 * </ul>
 */
    public int getSuccesfull() {
        return succesfull;
    }
    /**
     * Postavlja vrednost uspesnosti izvrsavanja operacije na prosledjenu vrednost.
     * 
     * @param succesfull Vrednost uspesnosti izvrsavanja operacije kao Integer.
     * <ul>
     * <li>1 - uspesno izvrsena operacija</li>
     * <li>else - neuspesno izvrsena operacija</li>
     * </ul>
     */
    public void setSuccesfull(int succesfull) {
        this.succesfull = succesfull;
    }
/**
 * Vraca izuzetak koji se desio prilikom izvrsavanja operacije.
 * 
 * @return Izuzetak koji se desio prilikom izvrsavanja operacije.
 */
    public Exception getException() {
        return exception;
    }
/**
 * Postavlja izuzetak koji se desio prilikom izvrsavanja operacije na prosledjenu vrednost.
 * 
 * @param exception Izuzetak koji se desio prilikom izvrsavanja operacije kao objekat
 * klase Exception.
 */
    public void setException(Exception exception) {
        this.exception = exception;
    }
}
