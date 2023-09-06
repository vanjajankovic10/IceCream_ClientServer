package so;

import database.DBBroker;
import domain.Component;
import domain.DomainObject;
/**
 * Klasa koja predstavlja sistemsku operaciju za izmenu informacija
 * iz baze podataka o komponentama.
 * 
 * Rezultat sistemske operacije je izmenjeni objekat klase Components.
 * 
 * Klasa nasledjuje SO koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Vanja Jankovic
 *
 */
public class SOEditComponent extends SO{
	/**
	 * Komponenta sladoleda koja treba da se izmeni u bazi.
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
    public SOEditComponent(DomainObject param){
        this.param = param;
    }
    /**
     * Besparametarski konstruktor koji poziva konstruktor nadklase SO.
     */
    public SOEditComponent() {
    	super();
	}
    /**
     * Konstruktor koji prima parametar koji predstavlja objekat klase
     * DBBroker.
     * 
     * @param dbb Objekat klase DBBroker.
     */
    public SOEditComponent(DBBroker dbb) {
    	super(dbb);
	}
    /**
     * Poziva operaciju za izmenu komponente u bazi podataka.
     * 
     * Rezultat operacije je objekat klase DomainObject koji predstavlja
     * izmenjenu komponentu.
     * 
     * @throws Expcetion Ako dodje do greske prilikom izvrsavanja operacije.
     */
    @Override
    protected void performOperation() throws Exception {
        component = dbb.editObject(param);
    }
    /**
     * Vraca prosledjenu komponentu koju treba izmeniti u bazi podataka.
     * 
     * @return Objekat klase DomainObject koji predstavlja komponentnu koju treba izmeniti.
     */
    public DomainObject getParam(){
        return param;
    }
    /**
     * Postavlja vrednost komponente koju treba izmeniti u bazi podataka na prosledjenu
     * vrednost.
     * 
     * @param param Objekat klase DomainObject koji predstavlja komponentu koju treba izmeniti.
     */
    public void setParam(DomainObject param){
        this.param = param;
    }
    /**
     * Vraca izmenjenu komponentu u bazi podataka.
     * 
     * @return Objekat klase DomainObject koji predstavlja izmenjenu komponentu u bazi.
     */
    public DomainObject getComponent(){
        return component;
    }
    /**
     * Proverava preduslov za izvrsavanje sistemske operacije izmene komponente u
     * bazi podataka. Proverava se da li je prosledjeni parametar instanca klase Component
     * i da li je razlicit od null.
     * 
     * @throws IllegalArgumentException Ako preduslovi za izvrsavanje operacije nisu ispunjeni.
     */
    public void preduslov() {
    	if(!(param instanceof Component) && param!=null) {
    		throw new IllegalArgumentException("Poslati objekat nije odogvarajuce klase");
    	}
    }
}
