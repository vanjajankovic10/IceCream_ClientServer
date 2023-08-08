package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecipeItem implements DomainObject{
	private int itemID;
    private Recipe recipe;
    private Component component;
    private double quantity;

    public RecipeItem() {
    }
    public RecipeItem(Recipe recipe) {
        this.recipe = recipe;
    }
    public RecipeItem(int itemID, Recipe recipe, Component component, double quantity) {
        this.itemID = itemID;
        this.recipe = recipe;
        this.component = component;
        this.quantity = quantity;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "RecipeItem{" + "itemID=" + itemID + ", recipe=" + recipe + ", component=" + component + ", quantity=" + quantity + '}';
    }

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
                RecipeItem ri = new RecipeItem(rs.getInt("itemID"), r,comp, quantity);
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
