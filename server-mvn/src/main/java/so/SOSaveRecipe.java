package so;

import java.util.List;

import database.DBBroker;
import domain.DomainObject;
import domain.Recipe;
import domain.RecipeItem;
/**
 * Klasa koja predstavlja sistemsku operaciju koja pamti recept
 * sladoleda.
 * 
 * Klasa nasledjuje apstraktnu sistemsku operaciju SO.
 * 
 * @author Vanja Jankovic
 *
 */
public class SOSaveRecipe extends SO{
	/**
	 * Recept sladoleda koja treba da se sacuva u bazi.
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
    public SOSaveRecipe(DomainObject param){
    	super();
        this.param = param;
    }
    /**
     * Konstruktor koji prima parametar koji predstavlja objekat klase
     * DBBroker.
     * 
     * @param dbb Objekat klase DBBroker.
     */
    public SOSaveRecipe(DBBroker dbb){
    	super(dbb);
    }
    /**
     * Cuva objekat klase DomainObject, odnosno recept, u bazi.
     * 
     * Rezultat operacije je sacuvani recept u bazi.
     * 
     * @throws Exception Ako dodje do greske prilikom izvrsavanja operacije.
     */
    @Override
    protected void performOperation() throws Exception {
        recipe = dbb.saveObject(param);
        saveItems();
    }
    /**
     * Postavlja vrednost recepta kog treba sacuvati u bazi podataka na prosledjenu
     * vrednost.
     * 
     * @param param Objekat klase DomainObject koji predstavlja recept koji treba sacuvati.
     */
    public void setParam(DomainObject param){
        this.param = param;
    }
    /**
     * Vraca sacuvani recept u bazi podataka.
     * 
     * @return Objekat klase DomainObject koji predstavlja sacuvani recept u bazi.
     */
    public DomainObject getRecipe(){
        return recipe;
    }
    /**
     * Vraca prosledjeni recept koju treba sacuvati u bazi podataka.
     * 
     * @return Objekat klase DomainObject koji predstavlja recept koji treba sacuvati.
     */
    public DomainObject getParam(){
        return param;
    }
    /**
     * Cuva objekte klase DomainObject, odnosno RecipeItem, u bazi.
     * 
     * Rezultat operacije su sacuvane stavke recepta u bazi.
     * 
     * @throws Exception Ako dodje do greske prilikom izvrsavanja operacije.
     */
    private void saveItems() throws Exception {
        List<DomainObject> items = ((Recipe)recipe).getComponents();
        for(DomainObject dom : items){
            RecipeItem item = (RecipeItem) dom;
            item.setRecipe((Recipe)recipe);
            dbb.saveObject(item);
        }
    }
    /**
     * Proverava preduslov za izvrsavanje sistemske operacije cuvanja recepta u
     * bazi podataka. Proverava se da li je prosledjeni parametar instanca klase Recipe
     * i da li je razlicit od null.
     * 
     * @throws Exception Ako preduslovi za izvrsavanje operacije nisu ispunjeni.
     */
    public void preduslov() throws Exception {
    	if(!(param instanceof Recipe) && param!=null) {
    		throw new IllegalArgumentException("Poslati objekat nije odogvarajuce klase");
    	}
    }
}
