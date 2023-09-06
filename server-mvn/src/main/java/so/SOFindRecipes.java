package so;

import java.util.ArrayList;
import java.util.List;

import database.DBBroker;
import domain.DomainObject;
import domain.Recipe;
/**
 * Klasa koja predstavlja sistemsku operaciju za pronalazenje informacija
 * iz baze podataka o receptima sladoleda.
 * 
 * Pretraga se vrsi na osnovu naziva, skracenog naziva, komentara
 * ili pakovanju.
 * 
 * Rezultat sistemske operacije je lista objekata klase Recipe.
 * 
 * Klasa nasledjuje SO koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Vanja Jankovic
 *
 */
public class SOFindRecipes extends SO{
	/**
	 * Lista objekata klase DomainObject u kojoj se cuva rezultat SQL upita.
	 */
	List<DomainObject> list = new ArrayList<>();
	/**
	 * Vrednost kao String po kojoj se pretrazuju recepti.
	 */
    String filter;
    /**
   	 * Konstruktor sa parametrom koji poziva konstruktor nadklase SO i prosledjuje
   	 * joj filter za pretrazivanje kao String.
   	 * 
   	 * @param filter Vrednost kao String po kojoj se pretrazuju recepti.
   	 */
    public SOFindRecipes(String filter){
        this.filter = filter; 
    }
    /**
	 * Besparametarski konstruktor koji poziva konstruktor nadklase SO.
	 */
    public SOFindRecipes() {
    	super();
	}
    /**
	 * Konstruktor sa parametrima koji poziva konstruktor nadklase SO i prosledjuje
	 * joj objekat klase DBBroker i postavlja vrednost filtera na prosledjenu vrednost.
	 * 
	 * @param dbb Objekat klase DBBroker koji omogucava komunikaciju sa bazom podataka.
	 *	@param filter Vrednost kao String po kojoj se pretrazuju recepti. 
	 */
    public SOFindRecipes(DBBroker dbb, String filter) {
    	super(dbb);
    	this.filter = filter;
	}
    /**
     * Poziva operaciju za pretrazivanje recepata po svim atributima.
     * 
     * Rezultat operacije je lista objekata klase DomainObject koji predstavljaju
     * pronadjene recepte.
     * 
     */
    @Override
    protected void performOperation() throws Exception {
        List<DomainObject> allRecipes = dbb.getAll(new Recipe());
        for(DomainObject dom : allRecipes){
            Recipe r = (Recipe) dom;
            if(r.getName().contains(filter) || r.getShortCode().contains(filter) 
                    || r.getComment().contains(filter) || r.getPackaging().getName().contains(filter)){
                list.add(dom);
            }
        }
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
