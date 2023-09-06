package so;

import database.DBBroker;
import domain.Component;
import domain.DomainObject;
/**
 * Klasa koja predstavlja sistemsku operaciju koja pamti komponentu
 * sladoleda.
 * 
 * Klasa nasledjuje apstraktnu sistemsku operaciju SO.
 * 
 * @author Vanja Jankovic
 *
 */
public class SOSaveComponent extends SO{
	/**
	 * Komponenta sladoleda koja treba da se sacuva u bazi.
	 */
    private DomainObject param;
    /**
     * Sacuvana komponenta kao rezultat SQL upita.
     */
    private DomainObject component;
    /**
     * Konstruktor koji prima parametar koji predstavlja objekat klase
     * DomainObject.
     * 
     * @param param Objekat klase DomainObject.
     */
    public SOSaveComponent(DomainObject param){
    	super();
        this.param = param;
    }
    /**
     * Konstruktor koji prima parametar koji predstavlja objekat klase
     * DBBroker.
     * 
     * @param dbb Objekat klase DBBroker.
     */
    public SOSaveComponent(DBBroker dbb){
    	super(dbb);
    }
    /**
     * Cuva objekat klase DomainObject, odnosno komponentu, u bazi.
     * 
     * Rezultat operacije je sacuvana komponenta u bazi.
     * 
     * @throws Exception Ako dodje do greske prilikom izvrsavanja operacije.
     */
    @Override
    protected void performOperation() throws Exception {
    	preduslov();
        component = dbb.saveObject(param);
    }
    /**
     * Postavlja vrednost komponente koju treba sacuvati u bazi podataka na prosledjenu
     * vrednost.
     * 
     * @param param Objekat klase DomainObject koji predstavlja komponentu koju treba sacuvati.
     */
    public void setParam(DomainObject param){
        this.param = param;
    }
    /**
     * Vraca prosledjenu komponentu koju treba sacuvati u bazi podataka.
     * 
     * @return Objekat klase DomainObject koji predstavlja komponentnu koju treba sacuvati.
     */
    public DomainObject getParam(){
        return param;
    }
    /**
     * Vraca sacuvanu komponentu u bazi podataka.
     * 
     * @return Objekat klase DomainObject koji predstavlja sacuvanu komponentu u bazi.
     */
    public DomainObject getComponent(){
        return component;
    }
    /**
     * Proverava preduslov za izvrsavanje sistemske operacije cuvanja komponente u
     * bazi podataka. Proverava se da li je prosledjeni parametar instanca klase Component
     * i da li je razlicit od null.
     * 
     * @throws Exception Ako preduslovi za izvrsavanje operacije nisu ispunjeni.
     */
    public void preduslov() throws Exception {
    	if(!(param instanceof Component) && param!=null) {
    		throw new IllegalArgumentException("Poslati objekat nije odogvarajuce klase");
    	}
    }
    
}
