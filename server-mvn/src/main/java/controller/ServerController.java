package controller;

import java.util.List;

import domain.Component;
import domain.DomainObject;
import domain.Recipe;
import domain.Tehnolog;
import so.SODeleteComponent;
import so.SODeleteRecipe;
import so.SOEditComponent;
import so.SOEditRecipe;
import so.SOFindComponents;
import so.SOFindRecipes;
import so.SOGetComponentCategory;
import so.SOGetComponents;
import so.SOGetPackaging;
import so.SOGetRecipes;
import so.SOGetUsers;
import so.SOLogin;
import so.SOLogout;
import so.SOSaveComponent;
import so.SOSaveRecipe;
/**
 * Klasa u okviru koje se pozivaju sve sistemske operacije.
 * 
 * ServerController je zaduzen da izabere i kreira odgovarajucu sistemsku operaciju 
 * u skladu sa zahtevom koji je stigao od klijenta.
 * 
 * ServerController prihvata zahtev i vraca rezultat sistemske operacije koja
 * je izvrsena.
 *
 * @author Vanja Jankovic
 *
 */
public class ServerController {
	/**
	 * Instanca klase ServerController, kreira se samo jednom prilikom prvog zahteva.
	 * 
	 * Svaki sledeci put kada je potreban kontroler na serverskoj strani, vraca se
	 * ova ista.
	 * 
	 */
	private static ServerController instance;
	/**
	 * Lista korisnika na sistemu.
	 * 
	 * Predstavlja listu  tehnologa iz baza.
	 */
    private List<DomainObject> usersList;
    /**
     * Besparametarski konstruktor koji osigurava da se jedina instanca klase
     * ServerController moze napraviti samo pozivom metode getInstance().
     */
    private ServerController(){
        
    }
    /**
     * Vraca instancu klase ServerController. 
     * Ako ne postoji instanca, kreira novu, u suprotnom vraca postojecu.
     * 
     * @return Instanca klase ServerController.
     */
    public static ServerController getInstance(){
        if(instance == null){
            instance = new ServerController();
        }
        return instance;
    }
    /**
     * Vraca listu korisnika na sistemu, odnosno sve tehnologe iz baze.
     * Ako je lista korisnika prazna, metoda ucitava listu i pamti je. 
     * Ako lista nije prazna, vraca postojecu.
     * 
     * @return Lista korisnika sistema kao lista objekata klase DomainObject
     * @throws Exception Ako nastane greska prilikom ucitavanja iz baze.
     */
   public List<DomainObject> getUsersList() throws Exception {
       if (usersList == null) {
            usersList = getUsers();
        }
        return usersList;
    }
   /**
    * Vraca listu korisnika sistema, koju dobija pozivanjem sistemske operacije
    * za ucitavanje korisnika iz baze podataka.
    * 
    * @return Lista korisnika sistema kao lista objekata klase DomainObject.
    * @throws Exception Ako nastane greska prilikom ucitavanja iz baze.
    */
    private List<DomainObject> getUsers() throws Exception {
        SOGetUsers sogu = new SOGetUsers();
        sogu.performSO();
        return sogu.getList();
    }
    /**
     * Poziva sistemsku operaciju za prijavljivanje korisnika na osnovu unetih parametara.
     * Ako postoji tehnolog u bazi podataka cije se korisnicko ime i lozinka podudaraju
     * sa prosledjenim parametrima, vrsi se prijavljivanje datog tehnologa na sistem.
     * Ako ne postoji tehnolog sa datim parametrima, metoda baca Exception.
     * 
     * @param tehn Tehnolog koji pokusava da se prijavi.
     * @return Ulogovani tehnolog kao objekat klase DomainObject.
     * @throws Exception Ako ne postoji tehnolog ili ako je vec ulogovan.
     */
    public DomainObject loginUser(Tehnolog tehn) throws Exception{
        SOLogin sol = new SOLogin();
        sol.setParams(tehn);
        sol.performSO();
        return sol.getUser();
    }
/**
 * Poziva sistemsku operaciju koja vrca listu kategorija komponenti iz baze podataka.
 * 
 * @return Lista kategorija komponenti kao lista objekata klase DomainObject.
 * @throws Exception Ako nastane greska prilikom ucitavanja liste iz baze.
 */
    public List<DomainObject> getComponentCategory() throws Exception {
        SOGetComponentCategory cc = new SOGetComponentCategory();
        cc.performSO();
        return cc.getList();
    }
/**
 * Poziva sistemsku operaciju za cuvanje nove komponente u bazi podataka.
 * 
 * @param c Nova komponenta koja se cuva u bazi podataka.
 * @return Sacuvana komponenta kao objekat klase DomainObject.
 * @throws Exception Ako nastane greska prilikom cuvanja objekta u bazi podataka.
 */
    public DomainObject saveComponent(Component c) throws Exception {
        SOSaveComponent sc = new SOSaveComponent(c);
        sc.performSO();
        return sc.getComponent();
        
    }
/**
 * Poziva sistemsku operaciju za izmenu podataka o komponenti u bazi podataka.
 * 
 * @param co Komponenta ciji podaci u bazi treba da se izmene.
 * @return Izmenjena komponenta kao objekat klase DomainObject.
 * @throws Exception Ako nastane greska prilikom izmene objekta u bazi podataka.
 */
    public DomainObject editComponent(Component co) throws Exception {
        SOEditComponent ec = new SOEditComponent(co);
        ec.performSO();
        return ec.getComponent();
    }
    /**
     * Poziva sistemsku operaciju koja vrca listu komponenti iz baze podataka.
     * 
     * @return Lista komponenti kao lista objekata klase DomainObject.
     * @throws Exception Ako nastane greska prilikom ucitavanja liste iz baze.
     */
    public List<DomainObject> getComponents() throws Exception {
        SOGetComponents gc = new SOGetComponents();
        gc.performSO();
        return gc.getList();
    }
/**
 * Poziva sistemsku operaicju koja pretrazuje komponente u bazi podataka prema
 * odredjenom filteru.
 * 
 * @param filter Vrednost kao String na osnovu koje se pretraga izvrsava.
 * @return Filtrirana lista komponenti kao lista objekata klase DomainObject.
 * @throws Exception Ako nastane greska prilikom pretrage objekata iz baze.
 */
    public List<DomainObject> findComponents(String filter) throws Exception {
        SOFindComponents fc = new SOFindComponents(filter);
        fc.performSO();
        return fc.getList();
    }
/**
 * Poziva sistemsku operaciju za brisanje komponente iz baze podataka.
 * 
 * @param delC Komponenta koju je potrebno izbrisati iz baze podataka.
 * @return Obrisana komponenta kao objekat klase DomainObject.
 * @throws Exception Ako nastane greska prilikom brisanja objekta iz baze.
 */
    public DomainObject deleteComponent(Component delC) throws Exception {
        SODeleteComponent dc = new SODeleteComponent(delC);
        dc.performSO();
        return dc.getDeleted();
    }
    /**
     * Poziva sistemsku operaciju koja vrca listu pakovanja sladoleda iz baze podataka.
     * 
     * @return Lista pakovanja sladoleda kao lista objekata klase DomainObject.
     * @throws Exception Ako nastane greska prilikom ucitavanja liste iz baze.
     */
    public List<DomainObject> getPackaging() throws Exception {
        SOGetPackaging gp = new SOGetPackaging();
        gp.performSO();
        return gp.getList();
    }
    /**
     * Poziva sistemsku operaciju za cuvanje novog recepta u bazi podataka.
     * 
     * @param r Novi recept koja se cuva u bazi podataka.
     * @return Sacuvani recept kao objekat klase DomainObject.
     * @throws Exception Ako nastane greska prilikom cuvanja objekta u bazi podataka.
     */
    public DomainObject saveRecipe(Recipe r) throws Exception {
        SOSaveRecipe sr = new SOSaveRecipe(r);
        sr.performSO();
        return sr.getRecipe();
    }
    /**
     * Poziva sistemsku operaciju za izmenu podataka o receptu u bazi podataka.
     * 
     * @param re Recept ciji podaci u bazi treba da se izmene.
     * @return Izmenjeni recept kao objekat klase DomainObject.
     * @throws Exception Ako nastane greska prilikom izmene objekta u bazi podataka.
     */
    public DomainObject editRecipe(Recipe re) throws Exception {
        SOEditRecipe er = new SOEditRecipe(re);
        er.performSO();
        return er.getRecipe();
    }
    /**
     * Poziva sistemsku operaciju koja vrca listu recepata sladoleda iz baze podataka.
     * 
     * @return Lista recepata sladoleda kao lista objekata klase DomainObject.
     * @throws Exception Ako nastane greska prilikom ucitavanja liste iz baze.
     */
    public List<DomainObject> getRecipes() throws Exception {
        SOGetRecipes gr = new SOGetRecipes();
        gr.performSO();
        return gr.getList();
    }
    /**
     * Poziva sistemsku operaciju za brisanje recepta iz baze podataka.
     * 
     * @param rec Recept koji je potrebno izbrisati iz baze podataka.
     * @return Obrisani recept kao objekat klase DomainObject.
     * @throws Exception Ako nastane greska prilikom brisanja objekta iz baze.
     */
    public DomainObject deleteRecipe(Recipe rec) throws Exception {
        SODeleteRecipe dr = new SODeleteRecipe(rec);
        dr.performSO();
        return dr.getDeleted();
    }
    /**
     * Poziva sistemsku operaicju koja pretrazuje recepte u bazi podataka prema
     * odredjenom filteru.
     * 
     * @param filterR Vrednost kao String na osnovu koje se pretraga izvrsava.
     * @return Filtrirana lista recepata kao lista objekata klase DomainObject.
     * @throws Exception Ako nastane greska prilikom pretrage objekata iz baze.
     */
    public List<DomainObject> findRecipes(String filterR) throws Exception {
        SOFindRecipes fr = new SOFindRecipes(filterR);
        fr.performSO();
        return fr.getList();
    }
/**
 * Poziva sistemsku operaciju za odjavu tehnologa sa sistema.
 * 
 * @param tehnolog Prijavljevi tehnolog kog je potrebno odjaviti.
 * @throws Exception Ako nastane greska prilikom odjavljivanja tehnologa sa sistema.
 */
    public void logoutTehnolog(Tehnolog tehnolog) throws Exception {
        SOLogout lo = new SOLogout(tehnolog);
        lo.performSO();
    }
}
