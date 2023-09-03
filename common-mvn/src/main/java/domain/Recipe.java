package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Recipe implements DomainObject{
	private int recipeID;
    private Tehnolog tehnolog;
    private String name;
    private String shortCode;
    private double quantity;
    private String comment;
    private List<DomainObject> components;
    private Packaging packaging;

    public Recipe() {
    }
    public Recipe(int id){
        this.recipeID = id;
    }
    public Recipe(int id, String name, String shortCode, double quantity, String comment, Tehnolog tehnolog, Packaging packaging) {
        this.recipeID = id;
        this.tehnolog = tehnolog;
        this.name = name;
        this.shortCode = shortCode;
        this.quantity = quantity;
        this.comment = comment;
        this.components = new ArrayList<>();
        this.packaging = packaging;
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
        final Recipe other = (Recipe) obj;
        return this.recipeID == other.recipeID;       
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
    	if(recipeID < 1) {
    		throw new IllegalArgumentException("RecipeID ne sme biti manji od 1");
    	}
        this.recipeID = recipeID;
    }

    public Tehnolog getTehnolog() {
        return tehnolog;
    }

    public void setTehnolog(Tehnolog tehnolog) {
    	if(tehnolog == null) {
    		throw new NullPointerException("Tehnolog ne sme biti null");
    	}
        this.tehnolog = tehnolog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
    	if(name == null) {
    		throw new NullPointerException("Ime ne sme biti null");
    	}
        this.name = name;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
    	if(shortCode == null) {
    		throw new NullPointerException("ShortCode ne sme biti null");
    	}
        this.shortCode = shortCode;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
    	if(quantity < 0) {
    		throw new IllegalArgumentException("Quantity ne sme biti manji od 0");
    	}
        this.quantity = quantity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
    	
        this.comment = comment;
    }

    public Packaging getPackaging() {
        return packaging;
    }

    public void setPackaging(Packaging packaging) {
    	if(packaging == null) {
    		throw new NullPointerException("Packaging ne sme biti null");
    	}
        this.packaging = packaging;
    }
    
    public List<DomainObject> getComponents() {
        return components;
    }

    public void setComponents(List<DomainObject> components) {
    	if(components == null) {
    		throw new NullPointerException("Components ne sme biti null");
    	}
        this.components = components;
    }

    @Override
    public String toString() {
        return "Recipe{" + "id=" + recipeID + ", tehnolog=" + tehnolog + ", name=" + name + ", shortCode=" + shortCode + ", quantity=" + quantity + ", comment=" + comment + ", components=" + components + ", packaging=" + packaging + '}';
    }

    @Override
    public String getTable() {
        return "recipe";
    }

    @Override
    public String getParams() {
        return String.format("'%s', '%s', '%s', '%s', '%s', '%s', '%s'",
                 recipeID,name, shortCode, quantity, comment, tehnolog.getTehnologID(), packaging.getPackagingID());
    }

    @Override
    public String getPK() {
        return "recipeID";
    }

    @Override
    public int getPKvalue() {
        return recipeID;
    }

    @Override
    public List<DomainObject> RSTable(ResultSet rs) {
            List<DomainObject> recipes = new ArrayList<>();
        try{
            while(rs.next()){
                
                Component com = new Component(rs.getInt("componentID"),
                                            rs.getString("componentName"),
                                        rs.getString("shortCodeC"),
                                        rs.getString("producer"), new Category(rs.getInt("categoryID"),
                                    rs.getString("categoryName")));
                
                
                Tehnolog t = new Tehnolog(rs.getInt("tehnologID"),
                                        rs.getString("tehnologName"), 
                                        rs.getString("surname"), 
                                        rs.getString("username"), 
                                        rs.getString("password"));
                Packaging p = new Packaging(rs.getInt("packagingID"),
                                    rs.getString("packagingName"));
                Recipe r = new Recipe(rs.getInt("recipeID"),
                                    rs.getString("name"),
                                 rs.getString("shortCode"),
                                  rs.getDouble("quantity"),
                        rs.getString("comment"), t,p);
                RecipeItem ri = new RecipeItem(rs.getInt("itemID"), r,com,rs.getInt("recipeQuantity"));
                
                boolean added = false;
                if(recipes.isEmpty()){
                    r.getComponents().add(ri);
                    recipes.add(r);
                    added = true;
                }
                if(!added){
                    for(DomainObject dom : recipes){
                        Recipe re = (Recipe) dom;
                        if(re.getRecipeID() == r.getRecipeID()){
                            re.getComponents().add(ri);
                            added = true;
                        }
                    }
                }
                if(!added){
                    r.getComponents().add(ri);
                    recipes.add(r);
                }
                
            }
        }catch(SQLException ex){
            Logger.getLogger(Component.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error at Recipe - RSTable");
        }
        return recipes;            

    }

    @Override
    public String getUpdate() {
        return String.format(" recipeID='%s',name='%s', shortCode='%s', quantity ='%s', comment='%s', tehnologID='%s', packagingID='%s'",
                recipeID, name, shortCode, quantity, comment, tehnolog.getTehnologID(), packaging.getPackagingID());
    }

    @Override
    public void setPKvalue(int pk) {
        this.recipeID = pk;
    }

    @Override
    public String columns() {
        return "r.*, t.tehnologID, t.name AS tehnologName, t.surname, t.username, t.password,"
                + " p.packagingID, p.name AS packagingName,"
                + " ri.itemID, ri.recipeID, ri.quantity AS recipeQuantity, ri.componentID, "
                + "c.categoryID, c.name AS categoryName, "
                + "comp.componentID, comp.name AS componentName, comp.shortCode as shortCodeC, comp.producer ";
    }

    @Override
    public String getSearchAttribute() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getAttributeValue() {
       throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getJoin() {
        return "recipe r JOIN tehnolog t ON r.tehnologID = t.tehnologID "
                + "JOIN packaging p ON r.packagingID = p.packagingID "
                + "JOIN recipeitem ri ON ri.recipeID = r.recipeID "
                + "JOIN component comp ON comp.componentID = ri.componentID "
                 + "JOIN category c ON comp.categoryID = c.categoryID ";
    }

    @Override
    public String getCompositePK() {
        return null;
    }
}
