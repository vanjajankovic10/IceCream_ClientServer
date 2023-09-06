package so;

import java.util.List;

import database.DBBroker;
import domain.DomainObject;
import domain.Tehnolog;
/**
 * Klasa koja predstavlja sistemsku operaciju za prikaz informacija
 * iz baze podataka o korisnicima, tj tehnolozima.
 * 
 * Rezultat sistemske operacije je lista objekata klase Tehnolog.
 * 
 * Klasa nasledjuje SO koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Vanja Jankovic
 *
 */
public class SOGetUsers extends SO{
	/**
	 * Lista objekata klase DomainObject u kojoj se cuva rezultat SQL upita.
	 */
	private List<DomainObject> list;
	/**
	 * Besparametarski konstruktor koji poziva konstruktor nadklase SO.
	 */
	public SOGetUsers() {
		super();
	}
	/**
	 * Konstruktor sa parametrom koji poziva konstruktor nadklase SO i prosledjuje
	 * joj objekat klase DBBroker.
	 * 
	 * @param dbb Objekat klase DBBroker koji omogucava komunikaciju sa bazom podataka.
	 */
	public SOGetUsers(DBBroker dbb) {
		super(dbb);
	}
	/**
	 * Vraca sve tehnologe iz baze podataka.
	 * 
	 * Rezultat operacije je lista objekata klase DomainObject u kojoj
	 * se nalaze sve tehnologe iz baze podataka.
	 * 
	 * @throws Exception Ako dodje do greske prilikom izvrsavanja operacije.
	 */
    @Override
    protected void performOperation() throws Exception {
        list = dbb.getAll((DomainObject)new Tehnolog());
    }
    /**
     * Vraca listu objekata klase DomainObject u kojoj se nalazi rezultat operacije.
     * 
     * @return Lista objekata klase DomainObject u kojoj se nalazi rezultat operacije.
     */
    public List<DomainObject> getList(){
        return list;
    }
}
