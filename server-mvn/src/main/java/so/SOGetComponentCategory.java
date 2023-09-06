package so;

import java.util.List;

import database.DBBroker;
import domain.Category;
import domain.DomainObject;
/**
 * Klasa koja predstavlja sistemsku operaciju za prikaz informacija
 * iz baze podataka o kategorijama komponenti.
 * 
 * Rezultat sistemske operacije je lista objekata klase Category.
 * 
 * Klasa nasledjuje SO koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Vanja Jankovic
 *
 */
public class SOGetComponentCategory extends SO{
	/**
	 * Lista objekata klase DomainObject u kojoj se cuva rezultat SQL upita.
	 */
	List<DomainObject> list;
	/**
	 * Besparametarski konstruktor koji poziva konstruktor nadklase SO.
	 */
	public SOGetComponentCategory() {
		super();
	}
	/**
	 * Konstruktor sa parametrom koji poziva konstruktor nadklase SO i prosledjuje
	 * joj objekat klase DBBroker.
	 * 
	 * @param dbb Objekat klase DBBroker koji omogucava komunikaciju sa bazom podataka.
	 */
	public SOGetComponentCategory(DBBroker dbb) {
		super(dbb);
	}
	/**
	 * Vraca sve kategorije iz baze podataka.
	 * 
	 * Rezultat operacije je lista objekata klase DomainObject u kojoj
	 * se nalaze sve kategorije iz baze podataka.
	 * 
	 * @throws Exception Ako dodje do greske prilikom izvrsavanja operacije.
	 */
    @Override
    protected void performOperation() throws Exception {
        list = dbb.getAll(new Category());
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
