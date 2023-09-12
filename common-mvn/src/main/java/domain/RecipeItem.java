package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Klasa koja predstavlja stavku recepta sladoleda.
 * 
 * Klasa implementira interfejs DomainObject.
 * 
 * RecipeItem ima svoj id, referencu na recept kome pripada, referencu na komponentu
 * kojoj pripada i kolicinu, odnosno procentualnu zastupljenost sastojka u 100%.
 * 
 * @author Vanja Jankovic
 * @see domain.Recipe
 */
public class RecipeItem implements DomainObject{
	/**
	 * ID stavke recepta kao int.
	 */
	private int itemID;
	/**
	 * Recept kome pripada stavka recepta kao objekat klase Recipe.
	 */
    private Recipe recipe;
    /**
     * Komponenta na koju se odnosi stavka recepta kao objekat klase Component.
     */
    private Component component;
    /**
     * Procentualna zastupljenost stavke recepta u celokupnom receptu kao double.
     */
    private double quantity;
    /**
     * Besparametarski konstruktor koji inicijalizuje objekat klase RecipeItem.
     */
    public RecipeItem() {
    }
    /**
     * Konstruktor sa parametrom koji inicijalizuje objekat klase RecipeItem
     *  i postavlja vrednost recepta na prosledjenu vrednost.
     * 
     * @param recipe Recept kome pripada stavka recepta kao objekat klase Recipe.
     */
    public RecipeItem(Recipe recipe) {
        setRecipe(recipe);
    }
    /**
     * Konstruktor sa parametrima koji inicijalizuje objekat klase RecipeItem
     * i postavlja vrednosti id-a, recepta, komponente i procentualne zastupljenosti
     * na prosledjene vrednosti.
     * 
     * @param itemID ID stavke recepta kao int.
     * @param recipe Recept kome pripada stavka recepta kao objekat klase Recipe.
     * @param component Komponenta na koju se odnosi stavka recepta kao objekat klase Component
     * @param quantity Procentualna zastupljenost stavke recepta u celokupnom receptu kao double.
     */
    public RecipeItem(int itemID, Recipe recipe, Component component, double quantity) {
    	setItemID(itemID);
    	setRecipe(recipe);
    	setComponent(component);
    	setQuantity(quantity);
    }
    /**
     * Vraca ID stavke recepta kao int.
     * 
     * @return ID stavke recepta kao int.
     */
    public int getItemID() {
        return itemID;
    }
    /**
     * Postavlja ID stavke recepta na prosledjenu vrednost.
     * 
     * @param itemID ID stavke recepta kao int.
     * @throws IllegalArgumentException Ako je id manji od 1.
     */
    public void setItemID(int itemID) {
    	if(itemID < 1) {
    		throw new IllegalArgumentException("ItemID ne sme biti manji od 1");
    	}
        this.itemID = itemID;
    }
    /**
     * Vraca Recept kome pripada stavka recepta kao objekat klase Recipe.
     * 
     * @return Recept kome pripada stavka recepta kao objekat klase Recipe.
     */
    public Recipe getRecipe() {
        return recipe;
    }
    /**
     * Postavlja recept kome pripada stavka recepta na prosledjenu vrednost.
     * 
     * @param recipe Recept kome pripada stavka recepta kao objekat klase Recipe.
     * @throws NullPointerException Ako je recipe null.
     */
    public void setRecipe(Recipe recipe) {
    	if(recipe == null) {
    		throw new NullPointerException("Recipe ne sme biti null");
    	}
        this.recipe = recipe;
    }
    /**
     * Vraca komponentu na koju se odnosi stavka recepta kao objekat klase Component.
     * 
     * @return Komponenta na koju se odnosi stavka recepta kao objekat klase Component.
     */
    public Component getComponent() {
        return component;
    }
    /**
     * Postavlja komponentu na koju se odnosi stavka recepta na prosledjenu vrednost.
     * 
     * @param component Komponenta na koju se odnosi stavka recepta kao objekat klase Component.
     * @throws NullPointerException Ako je component null.
     */
    public void setComponent(Component component) {
    	if(component == null) {
    		throw new NullPointerException("Component ne sme biti null");
    	}
        this.component = component;
    }
    /**
     * Vraca procentualnu zastupljenost stavke recepta u receptu kao double.
     * 
     * @return Procentualna zastupljenost stavke recepta u receptu kao double.
     */
    public double getQuantity() {
        return quantity;
    }
    /**
     * Postavlja procentualnu zastupljenost stavke recepta na prosledjenu vrednost.
     * 
     * @param quantity Procentualna zastupljenost stavke recepta kao double.
     * @throws IllegalArgumentException Ako je quantity manji od 0.
     */
    public void setQuantity(double quantity) {
    	if(quantity < 0 ) {
    		throw new IllegalArgumentException("Quantity ne sme biti manji od 0");
    	}
        this.quantity = quantity;
    }
    /**
     * Vraca String sa informacijama o stavki recepta sladoleda, odnosno informacijama
     * o id-u stavke, receptu kome pripada, komponenti na koju se odnosi, 
     * procentualnoj zastupljenosti stavke u receptu.
     * 
     * @return Informacije o stavki recepta sladoleda kao String.
     */
    @Override
    public String toString() {
        return "RecipeItem{" + "itemID=" + itemID + ", recipe=" + recipe + ", component=" + component + ", quantity=" + quantity + '}';
    }
    /**
     * Poredi dva objekta stavke recepta sladoleda po njihovom id-u, procentualnoj zastupljenoscu, 
     * receptu kome pripadaju i komponentama na koje se odnose.
     * 
     * @param obj Objekat sa kojim se poredi.
     * 
     * @return 
     *  <ul>
     * 	 <li>true - ako su oba objekta klase RecipeItem i imaju isti itemID, recipe, component i quantity </li>
     *	 <li>false - u svim ostalim slucajevima. </li>
     * </ul>
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RecipeItem other = (RecipeItem) obj;
        if (this.itemID != other.itemID) {
            return false;
        }
        if (Double.doubleToLongBits(this.quantity) != Double.doubleToLongBits(other.quantity)) {
            return false;
        }
        if (!Objects.equals(this.recipe, other.recipe)) {
            return false;
        }
        return Objects.equals(this.component, other.component);
    }

    @Override
    public String getTable() {
        return "recipeitem";
    }

    @Override
    public String getParams() {
        return String.format("'%s', '%s', '%s', '%s'",
                itemID, recipe.getRecipeID(), quantity,component.getComponentID());
        }

    @Override
    public String getPK() {
        return null;
    }

    @Override
    public int getPKvalue() {
        return recipe.getRecipeID();
    }

    @Override
    public List<DomainObject> RSTable(ResultSet rs) {
        List<DomainObject> items = new ArrayList<>();
        try{
            while(rs.next()){
                Category cat = new Category(rs.getInt("categoryID"),
                        rs.getString("catName"));
                Component comp = new Component(rs.getInt("componentID"),
                        rs.getString("name"),
                        rs.getString("shortCode"),
                        rs.getString("producer"),cat);
                Recipe r = new Recipe(rs.getInt("recipeID"));
                RecipeItem ri = new RecipeItem(rs.getInt("itemID"), r,comp, rs.getDouble("quantity"));
                items.add(ri);
            }
        }catch(SQLException ex){
            Logger.getLogger(Component.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error at Component - RSTable");
        }
        return items;
    }

    @Override
    public String getUpdate() {
        return String.format("itemID='%s', recipeID='%s', quantity='%s', componentID='%s'",
                itemID, recipe.getRecipeID(), quantity, component.getComponentID());
    
    }

    @Override
    public void setPKvalue(int pk) {
    }

    @Override
    public String columns() {
        return " ri.*, cat.name AS catName, cat.categoryID, c.* ";
    }

    @Override
    public String getSearchAttribute() {
        return "recipeID = ";
    }

    @Override
    public String getAttributeValue() {
        return recipe.getRecipeID()+"";
    }

    @Override
    public String getJoin() {
        return "recipeitem ri JOIN component c ON c.componentID = ri.componentID "
                + "JOIN category cat ON cat.categoryID = c.categoryID ";
                
    }

    @Override
    public String getCompositePK() {
        return String.format(" itemID='%d' AND recipeID='%d' ",  itemID,recipe.getRecipeID());
    }
}
