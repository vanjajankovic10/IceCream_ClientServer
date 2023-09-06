package so;

import java.util.List;

import database.DBBroker;
import domain.DomainObject;
import domain.Recipe;
/**
 * Klasa koja predstavlja sistemsku operaciju za prikaz informacija
 * iz baze podataka o receptima.
 * 
 * Rezultat sistemske operacije je lista objekata klase Recipe.
 * 
 * Klasa nasledjuje SO koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Vanja Jankovic
 *
 */
public class SOGetRecipes extends SO{
	/**
	 * Lista objekata klase DomainObject u kojoj se cuva rezultat SQL upita.
	 */
	private List<DomainObject> list;
	/**
	 * Besparametarski konstruktor koji poziva konstruktor nadklase SO.
	 */
	public SOGetRecipes() {
		super();
	}
	/**
	 * Konstruktor sa parametrom koji poziva konstruktor nadklase SO i prosledjuje
	 * joj objekat klase DBBroker.
	 * 
	 * @param dbb Objekat klase DBBroker koji omogucava komunikaciju sa bazom podataka.
	 */	
	public SOGetRecipes(DBBroker dbb) {
		super(dbb);
	}
	/**
	 * Vraca sve recepte iz baze podataka.
	 * 
	 * Rezultat operacije je lista objekata klase DomainObject u kojoj
	 * se nalaze svi recepti iz baze podataka.
	 * 
	 * @throws Exception Ako dodje do greske prilikom izvrsavanja operacije.
	 */
    @Override
    protected void performOperation() throws Exception {
        list = dbb.getAll(new Recipe());
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
