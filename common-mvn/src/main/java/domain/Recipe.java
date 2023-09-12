package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Klasa koja predstavlja recept sladoleda.
 * 
 * Klasa implementira interfejs DomainObject.
 * 
 * Recept ima svoj id, naziv i skraceni naziv, komentar, tehnologa koji ga je kreirao,
 * kolicinu, listu sastojaka i pakovanje.
 * 
 * @author Vanja Jankovic
 */
public class Recipe implements DomainObject{
	/**
	 * ID recepta kao int.
	 */
	private int recipeID;
	/**
	 * Tehnolog kao objekat klase Tehnolog.
	 */
    private Tehnolog tehnolog;
    /**
     * Naziv recepta kao String.
     */
    private String name;
    /**
     * Skraceni naziv recepta kao String.
     */
    private String shortCode;
    /**
     * Kolicina recepta kao double.
     */
    private double quantity;
    /**
     * Komentar kao String.
     */
    private String comment;
    /**
     * Lista sastojaka koje cine RecipeItem kao List objekata koji
     *  implementiraju interfejs DomainObject.
     */
    private List<DomainObject> components;
    /**
     * Pakovanje sladoleda kao objekat klase Packaging.
     */
    private Packaging packaging;
    /**
     * Besparametarski konstruktor koji inicijalizuje objekat klase Recipe.
     */
    public Recipe() {
    }
    /**
     * Konstruktor sa parametrom koji inicijalizuje objekat klase Recipe i postavlja
     * vrednost id-a na prosledjenu vrednost.
     * 
     * @param id ID recepta kao int.
     */
    public Recipe(int id){
        setRecipeID(id);
    }
    /**
     * Konstruktor sa parametrima koji inicijalizuje objekat klase Recipe
     * i postavlja vrednosti id-a, naziva, skracenog naziva, kolicine, komentara,
     * tehnologa i pakovanja na prosledjene vrednosti.
     * 
     * @param id ID recepta kao int.
     * @param name Naziv recepta kao String.
     * @param shortCode Skraceni naziv recepta kao String.
     * @param quantity Kolicina recepta kao double.
     * @param comment Komentar recepta kao String.
     * @param tehnolog Tehnolog recepta kao objekat klase Tehnolog.
     * @param packaging Pakovanje sladoleda kao objekat klase Packaging.
     */
    public Recipe(int id, String name, String shortCode, double quantity, String comment, Tehnolog tehnolog, Packaging packaging) {
        setRecipeID(id);
        setTehnolog(tehnolog);
        setName(name);
        setShortCode(shortCode);
        setQuantity(quantity);
        setComment(comment);
        setComponents(new ArrayList<>());
        setPackaging(packaging);
    }
    /**
     * Poredi dva objekta recepta sladoleda po njihovom id-u.
     * 
     * @param obj Objekat sa kojim se poredi.
     * 
     * @return 
     *  <ul>
     * 	 <li>true - ako su oba objekta klase Recipe i imaju isti id.</li>
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
        final Recipe other = (Recipe) obj;
        return this.recipeID == other.recipeID;       
    }
    /**
     * Vraca ID recepta kao int.
     * 
     * @return ID recepta kao int.
     */
    public int getRecipeID() {
        return recipeID;
    }
    /**
     * Postavlja ID recepta na prosledjenu vrednost.
     * 
     * @param recipeID ID recepta kao int.
     * @throws IllegalArgumentException Ako je id manji od 1.
     */
    public void setRecipeID(int recipeID) {
    	if(recipeID < 1) {
    		throw new IllegalArgumentException("RecipeID ne sme biti manji od 1");
    	}
        this.recipeID = recipeID;
    }
    /**
     * Vraca Tehnologa recepta kao objekat klase Tehnolog.
     * 
     * @return Tehnolog recepta kao objekat klase Tehnolog.
     */
    public Tehnolog getTehnolog() {
        return tehnolog;
    }
    /**
     * Postavlja Tehnologa recepta na prosledjenu vrednost.
     * 
     * @param tehnolog Tehnolog recepta kao objekat klase Tehnolog.
     * @throws NullPointerException Ako je tehnolog null.
     */
    public void setTehnolog(Tehnolog tehnolog) {
    	if(tehnolog == null) {
    		throw new NullPointerException("Tehnolog ne sme biti null");
    	}
        this.tehnolog = tehnolog;
    }
    /**
     * Vraca naziv recepta kao String.
     * 
     * @return Naziv recepta kao String.
     */
    public String getName() {
        return name;
    }
    /**
     * Postavlja naziv recepta na prosledjenu vrednost.
     * 
     * @param name Naziv recepta kao String.
     * @throws NullPointerException Ako je ime null.
     */
    public void setName(String name) {
    	if(name == null) {
    		throw new NullPointerException("Ime ne sme biti null");
    	}
        this.name = name;
    }
    /**
     * Vraca skraceni naziv recepta kao String.
     * 
     * @return Skraceni naziv recepta kao String.
     */
    public String getShortCode() {
        return shortCode;
    }
    /**
     * Postavlja skraceni naziv recepta na prosledjenu vrednost.
     * 
     * @param shortCode Skraceni naziv recepta kao String.
     * @throws NullPointerException Ako je shortCode null.
     */
    public void setShortCode(String shortCode) {
    	if(shortCode == null) {
    		throw new NullPointerException("ShortCode ne sme biti null");
    	}
        this.shortCode = shortCode;
    }
    /**
     * Vraca kolicinu recepta kao double.
     * 
     * @return Kolicina recepta kao double.
     */
    public double getQuantity() {
        return quantity;
    }
    /**
     * Postavlja kolicinu recepta na prosledjenu vrednost.
     * 
     * @param quantity Kolicina recepta kao double.
     * @throws IllegalArgumentException Ako je quantity manji od 0.
     */
    public void setQuantity(double quantity) {
    	if(quantity < 0) {
    		throw new IllegalArgumentException("Quantity ne sme biti manji od 0");
    	}
        this.quantity = quantity;
    }
    /**
     * Vraca komentar recepta kao String.
     * 
     * @return Komentar recepta kao String.
     */
    public String getComment() {
        return comment;
    }
    /**
    * Postavlja komentar recepta na prosledjenu vrednost.
    * 
    * @param comment Komentar recepta kao String.
    */
    public void setComment(String comment) {
    	
        this.comment = comment;
    }
    /**
     * Vraca pakovanje sladoleda kao objekat klase Packaging.
     * 
     * @return Pakovanje sladoleda kao objekat klase Packaging.
     */
    public Packaging getPackaging() {
        return packaging;
    }
    /**
     * Postavlja pakovanje recepta na prosledjenu vrednost.
     * 
     * @param packaging Pakovanje recepta kao objekat klase Packaging.
     * @throws NullPointerException Ako je packaging null.
     */
    public void setPackaging(Packaging packaging) {
    	if(packaging == null) {
    		throw new NullPointerException("Packaging ne sme biti null");
    	}
        this.packaging = packaging;
    }
    /**
     * Vraca listu stavki recepta sladoleda kao List objekata koji
     *  implementiraju interfejs DomainObject.
     * 
     * @return Lista stavki recepta sladoleda kao List objekata koji
     *  implementiraju interfejs DomainObject.
     */
    public List<DomainObject> getComponents() {
        return components;
    }
    /**
     * Postavlja listu stavki recepta sladoleda na prosledjenu vrednost.
     * 
     * @param components Lista stavki recepta sladoleda kao List objekata koji
     *  implementiraju interfejs DomainObject.
     *  @throws NullPointerException Ako je lista components null.
     */
    public void setComponents(List<DomainObject> components) {
    	if(components == null) {
    		throw new NullPointerException("Components ne sme biti null");
    	}
        this.components = components;
    }
    /**
     * Vraca String sa informacijama o receptu sladoleda, odnosno informacijama
     * o id-u, nazivu recepta, skracenom nazivu, komentaru, tehnologu, pakovanju
     *  i listi stavki recepta.
     * 
     * @return Informacije o receptu kao String.
     */
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
