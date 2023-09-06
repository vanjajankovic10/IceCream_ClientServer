package so;

import database.DBBroker;
import domain.DomainObject;
/**
 * Klasa koja predstavlja sistemsku operaciju koja brise recept
 * sladoleda iz baze podataka.
 * 
 * Klasa nasledjuje apstraktnu sistemsku operaciju SO.
 * 
 * @author Vanja Jankovic
 *
 */
public class SODeleteRecipe extends SO{
	/**
	 * Recept sladoleda koja treba da se obrise iz baze.
	 */
	private DomainObject param;
	/**
     * Obrisani recept kao rezultat SQL upita.
     */
    private DomainObject deleted;
    /**
     * Konstruktor koji prima parametar koji predstavlja objekat klase
     * DomainObject.
     * 
     * @param param Objekat klase DomainObject.
     */
    public SODeleteRecipe(DomainObject param){
    	super();
        this.param = param;
    }
    /**
     * Konstruktor koji prima parametar koji predstavlja objekat klase
     * DBBroker.
     * 
     * @param dbb Objekat klase DBBroker.
     */
    public SODeleteRecipe(DBBroker dbb){
    	super(dbb);
    }
    /**
     * Brise objekat klase DomainObject, odnosno recept, iz baze.
     * 
     * Rezultat operacije je obrisani recept iz baze.
     * 
     * @throws Exception Ako dodje do greske prilikom izvrsavanja operacije.
     */
    @Override
    protected void performOperation() throws Exception {
        deleted = dbb.deleteObject(param);
    }
    /**
     * Vraca obrisani recept iz baze podataka.
     * 
     * @return Objekat klase DomainObject koji predstavlja obrisani recept iz baze podataka.
     */
    public DomainObject getDeleted(){
        return deleted;
    }
    /**
     * Postavlja vrednost recepta kog treba obrisati iz baze podataka na prosledjenu
     * vrednost.
     * 
     * @param param Objekat klase DomainObject koji predstavlja recept koji treba obrisati.
     */
    public void setParam(DomainObject param) {
		this.param = param;
	}
    /**
     * Vraca prosledjeni recept koju treba obrisati iz baze podataka.
     * 
     * @return Objekat klase DomainObject koji predstavlja recept koji treba obrisati.
     */
    public DomainObject getParam() {
		return param;
	}
}
