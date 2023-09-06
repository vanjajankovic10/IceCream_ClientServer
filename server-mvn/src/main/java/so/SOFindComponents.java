package so;

import java.util.ArrayList;
import java.util.List;

import database.DBBroker;
import domain.Component;
import domain.DomainObject;
/**
 * Klasa koja predstavlja sistemsku operaciju za pronalazenje informacija
 * iz baze podataka o komponentama.
 * 
 * Pretraga se vrsi na osnovu naziva, skracenog naziva, nazivu proizvodjaca
 * ili nazivu komponenti.
 * 
 * Rezultat sistemske operacije je lista objekata klase Components.
 * 
 * Klasa nasledjuje SO koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Vanja Jankovic
 *
 */
public class SOFindComponents extends SO{
	/**
	 * Lista objekata klase DomainObject u kojoj se cuva rezultat SQL upita.
	 */
	List<DomainObject> list = new ArrayList<>();
	/**
	 * Vrednost kao String po kojoj se pretrazuju komponente.
	 */
    String filter;
    /**
	 * Konstruktor sa parametrom koji poziva konstruktor nadklase SO i prosledjuje
	 * joj filter za pretrazivanje kao String.
	 * 
	 * @param filter Vrednost kao String po kojoj se pretrazuju komponente.
	 */
    public SOFindComponents(String filter){
        this.filter = filter; 
    }
    /**
	 * Besparametarski konstruktor koji poziva konstruktor nadklase SO.
	 */
    public SOFindComponents() {
    	super();
	}
    /**
	 * Konstruktor sa parametrima koji poziva konstruktor nadklase SO i prosledjuje
	 * joj objekat klase DBBroker i postavlja vrednost filtera na prosledjenu vrednost.
	 * 
	 * @param dbb Objekat klase DBBroker koji omogucava komunikaciju sa bazom podataka.
	 *	@param filter Vrednost kao String po kojoj se pretrazuju komponente. 
	 */
    public SOFindComponents(DBBroker dbb, String filter) {
    	super(dbb);
    	this.filter = filter;
	}
    /**
     * Poziva operaciju za pretrazivanje komponenti po svim atributima.
     * 
     * Rezultat operacije je lista objekata klase DomainObject koji predstavljaju
     * pronadjene komponente.
     * 
     */
    @Override
    protected void performOperation() throws Exception {
        List<DomainObject> allComp = dbb.getAll(new Component());
        for(DomainObject ob: allComp){
            Component c = (Component) ob;
            if(c.getName().contains(filter) || c.getShortCode().contains(filter) 
                    || c.getProducer().contains(filter) || c.getCategory().getName().contains(filter)){
                list.add(ob);
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
