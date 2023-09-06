package so;

import java.util.List;

import controller.ServerController;
import domain.DomainObject;
import domain.Tehnolog;
/**
 * Klasa koja predstavlja sistemsku operaciju koja prijavljuje tehnologa
 * u sistem.
 * 
 * Klasa nasledjuje apstraktnu sistemsku operaciju SO.
 * 
 * @author Vanja Jankovic
 *
 */
public class SOLogin extends SO{
	/**
	 * Tehnolog koji treba da se prijavi na sistem.
	 */
    private DomainObject params;
    /**
     * Prijavljeni tehnolog kao rezultat SQL upita.
     */
    private DomainObject user;
    /**
     * Prijavljuje tehnologa na sistem i dodaje ga u listu objekata DomainObject
     * koji predstavljaju prijavljene korisnike.
     * 
     * Rezultat operacije je objekat klase DomainObject odnosno tehnolog
     * koji je prijavljen na sistem.
     * 
     * @throws Exception Ako je tehnolog vec prijavljen na sistem.
     */
    @Override
    protected void performOperation() throws Exception {
        List<DomainObject> tehnologList = dbb.getAll(new Tehnolog());
        Tehnolog sentTehn = (Tehnolog) params;
        for(DomainObject t : tehnologList){
            
            Tehnolog baseTehn = (Tehnolog)t;
            if(baseTehn.getUsername().equals(sentTehn.getUsername()) &&
                    baseTehn.getPassword().equals(sentTehn.getPassword())){
                user = baseTehn;
                int index = ServerController.getInstance().getUsersList().indexOf(baseTehn);
                Tehnolog listTehn = (Tehnolog) ServerController.getInstance().getUsersList().get(index);
                if(listTehn.isLoggedIn()){
                    throw new Exception("Tehnolog is already logged in!");
                }
                else{
                    listTehn.setLoggedIn(true);
                }
                return;
            }
        }
        user = null;
    }
    /**
     * Vraca prosledjenog tehnologa kog treba prijaviti.
     * 
     * @return Objekat klase DomainObject koji predstavlja tehnologa kog treba prijaviti.
     */
    public DomainObject getParams() {
        return params;
    }
    /**
     * Postavlja vrednst tehnologa kog treba prijaviti na prosledjenu
     * vrednost.
     * 
     * @param params Objekat klase DomainObject koji predstavlja tehnologa kog treba prijaviti.
     */
    public void setParams(DomainObject params) {
        this.params = params;
    }
    /**
     * Vraca prijavljenog tehnologa na sistem.
     * 
     * @return Objekat klase DomainObject koji predstavlja prijavljenog tehnologa.
     */
    public DomainObject getUser() {
        return user;
    }
    /**
     * Postavlja vrednst prijavljenog tehnologa na prosledjenu
     * vrednost.
     * 
     * @param user Objekat klase DomainObject koji predstavlja prijavljenog tehnologa.
     */
    public void setUser(DomainObject user) {
        this.user = user;
    }
    
}
