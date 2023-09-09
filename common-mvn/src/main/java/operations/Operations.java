package operations;
/**
 * Klasa koja sadrzi konstante ciji naziv predstavlja naziv sistemskih
 * operacija koje ce se izvrsavati na serverskoj strani.
 * 
 * Omogucavaju komunikaciju izmedju klijenta i servera zato sto na jedinstven
 * nacin identifikuju operacije.
 * 
 * Konstante su javne staticke finalne celobrojne konstante.
 * 
 * @author Vanja Jankovic
 *
 */
public class Operations {
	/**
	 * Predstavlja operaciju za prijavljivanje tehnologa na sistem.
	 */
	public static final int LOGIN = 1;
	/**
	 * Predstavlja operaciju za vracanje liste kategorija komponenti.
	 */
    public static final int GET_COMPONENT_CATEGORY = 2;
    /**
	 * Predstavlja operaciju za cuvanje komponenti.
	 */
    public static final int SAVE_COMPONENT = 3;
    /**
	 * Predstavlja operaciju za izmenu komponenti.
	 */
    public static final int EDIT_COMPONENT = 4;
    /**
	 * Predstavlja operaciju za vracanje liste komponenti.
	 */
    public static final int GET_COMPONENTS = 5;
    /**
	 * Predstavlja operaciju za pretragu komponenti.
	 */
    public static final int FIND_COMPONENTS = 6;
    /**
	 * Predstavlja operaciju za brisanje komponenti.
	 */
    public static final int DELETE_COMPONENT = 7;
    /**
	 * Predstavlja operaciju za vracanje liste pakovanja.
	 */
    public static final int GET_PACKAGING = 8;    
    /**
	 * Predstavlja operaciju za cuvanje recepta.
	 */
    public static final int SAVE_RECIPE = 9;
    /**
	 * Predstavlja operaciju za izmenu recepta.
	 */
    public static final int EDIT_RECIPE = 10;
    /**
	 * Predstavlja operaciju za vracanje liste recepata.
	 */
    public static final int GET_RECIPES = 11;
    /**
	 * Predstavlja operaciju za brisanje recepta.
	 */
    public static final int DELETE_RECIPE = 12;
    /**
	 * Predstavlja operaciju za pretragu recepata.
	 */
    public static final int FIND_RECIPES = 13;
}
