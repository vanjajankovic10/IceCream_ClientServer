package so;

import controller.ServerController;
import domain.DomainObject;
import domain.Tehnolog;
/**
 * Klasa koja predstavlja sistemsku operaciju koja odjavljuje tehnologa
 * sa sistema.
 * 
 * Klasa nasledjuje apstraktnu sistemsku operaciju SO.
 * 
 * @author Korisnik
 *
 */
public class SOLogout extends SO{
	/**
	 * Tehnolog koji treba da se izloguje sa sistem.
	 */
    private DomainObject tehnolog;
    /**
     * Konstruktor koji prima parametar koji predstavlja objekat klase
     * DomainObject.
     * 
     * @param tehnolog Objekat klase DomainObject.
     */
    public SOLogout(DomainObject tehnolog) {
        this.tehnolog = tehnolog;
    }
    /**
     * Odjavljuje tehnologa sa sistema i uklanja ga sa liste objekata DomainObject
     * koji predstavljaju prijavljene korisnike.
     * 
     * Rezultat operacije je objekat klase DomainObject odnosno tehnolog
     * koji je odjavljen sa sistema.
     * 
     */
    @Override
    protected void performOperation() throws Exception {
        int index = ServerController.getInstance().getUsersList().indexOf(tehnolog);
        if(index != -1){
            ((Tehnolog) ServerController.getInstance().getUsersList().get(index)).setLoggedIn(false);
        }
    }
}
