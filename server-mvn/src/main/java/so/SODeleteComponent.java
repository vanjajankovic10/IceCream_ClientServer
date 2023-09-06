package so;

import database.DBBroker;
import domain.DomainObject;
/**
 * Klasa koja predstavlja sistemsku operaciju koja brise komponentu
 * sladoleda iz baze podataka.
 * 
 * Klasa nasledjuje apstraktnu sistemsku operaciju SO.
 * 
 * @author Vanja Jankovic
 *
 */
public class SODeleteComponent extends SO{
	/**
	 * Komponenta sladoleda koja treba da se obrise iz baze.
	 */
	private DomainObject param;
	/**
     * Obrisana komponenta kao rezultat SQL upita.
     */
    private DomainObject deleted;
    /**
     * Konstruktor koji prima parametar koji predstavlja objekat klase
     * DomainObject.
     * 
     * @param param Objekat klase DomainObject.
     */
    public SODeleteComponent(DomainObject param){
    	super();
        this.param = param;
    }
    /**
     * Konstruktor koji prima parametar koji predstavlja objekat klase
     * DBBroker.
     * 
     * @param dbb Objekat klase DBBroker.
     */
    public SODeleteComponent(DBBroker dbb){
    	super(dbb);
    }
    /**
     * Brise objekat klase DomainObject, odnosno komponentu, iz baze.
     * 
     * Rezultat operacije je obrisana komponenta iz baze.
     * 
     * @throws Exception Ako dodje do greske prilikom izvrsavanja operacije.
     */
    @Override
    protected void performOperation() throws Exception {
        deleted = dbb.deleteObject(param);
    }
    /**
     * Vraca obrisanu komponentu iz baze podataka.
     * 
     * @return Objekat klase DomainObject koji predstavlja obrisanu komponentu iz baze podataka.
     */
    public DomainObject getDeleted(){
        return deleted;
    }
    /**
     * Postavlja vrednost komponente koju treba obrisati iz baze podataka na prosledjenu
     * vrednost.
     * 
     * @param param Objekat klase DomainObject koji predstavlja komponente koju treba obrisati.
     */
    public void setParam(DomainObject param) {
		this.param = param;
	}
    /**
     * Vraca prosledjenu komponentu koju treba obrisati iz baze podataka.
     * 
     * @return Objekat klase DomainObject koji predstavlja komponentu koju treba obrisati.
     */
    public DomainObject getParam() {
		return param;
	}
}
