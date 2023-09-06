package so;

import database.DBBroker;
import domain.DomainObject;
import domain.Recipe;
/**
 * Klasa koja predstavlja sistemsku operaciju za izmenu informacija
 * iz baze podataka o receptu.
 * 
 * Rezultat sistemske operacije je izmenjeni objekat klase Recipe.
 * 
 * Klasa nasledjuje SO koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Vanja Jankovic
 *
 */
public class SOEditRecipe extends SO{
	/**
	 * Recept sladoleda koja treba da se izmeni u bazi.
	 */
	private DomainObject param;
	/**
     * Sacuvani recept kao rezultat SQL upita.
     */
    private DomainObject recipe;
    /**
     * Konstruktor koji prima parametar koji predstavlja objekat klase
     * DomainObject.
     * 
     * @param param Objekat klase DomainObject.
     */
    public SOEditRecipe(DomainObject param){
        this.param = param;
    }
    /**
     * Besparametarski konstruktor koji poziva konstruktor nadklase SO.
     */
    public SOEditRecipe() {
    	super();
	}
    /**
     * Konstruktor koji prima parametar koji predstavlja objekat klase
     * DBBroker.
     * 
     * @param dbb Objekat klase DBBroker.
     */
    public SOEditRecipe(DBBroker dbb) {
    	super(dbb);
	}
    /**
     * Poziva operaciju za izmenu recepta u bazi podataka.
     * 
     * Rezultat operacije je objekat klase DomainObject koji predstavlja
     * izmenjeni recept.
     * 
     * @throws Expcetion Ako dodje do greske prilikom izvrsavanja operacije.
     */
    @Override
    protected void performOperation() throws Exception {
        recipe = dbb.editObject(param);
    }
    /**
     * Vraca prosledjeni recept koje treba izmeniti u bazi podataka.
     * 
     * @return Objekat klase DomainObject koji predstavlja recept koju treba izmeniti.
     */
    public DomainObject getParam(){
        return param;
    }
    /**
     * Postavlja vrednost recepta koji treba izmeniti u bazi podataka na prosledjenu
     * vrednost.
     * 
     * @param param Objekat klase DomainObject koji predstavlja recept koji treba izmeniti.
     */
    public void setParam(DomainObject param){
        this.param = param;
    }
    /**
     * Vraca izmenjeni recept u bazi podataka.
     * 
     * @return Objekat klase DomainObject koji predstavlja izmenjeni recept u bazi.
     */
    public DomainObject getRecipe(){
        return recipe;
    }
    /**
     * Proverava preduslov za izvrsavanje sistemske operacije izmene recepta u
     * bazi podataka. Proverava se da li je prosledjeni parametar instanca klase Recipe
     * i da li je razlicit od null.
     * 
     * @throws IllegalArgumentException Ako preduslovi za izvrsavanje operacije nisu ispunjeni.
     */
    public void preduslov() {
    	if(!(param instanceof Recipe) && param!=null) {
    		throw new IllegalArgumentException("Poslati objekat nije odogvarajuce klase");
    	}
    }
}
