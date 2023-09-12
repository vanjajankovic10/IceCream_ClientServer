package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Klasa koja predstavlja kategoriju sastojka sladoleda.
 * 
 * Klasa implementira interfejs DomainObject.
 * 
 * Kategorija ima svoj id i naziv kategorije.
 * 
 * @author Vanja Jankovic
 *
 */
public class Category implements DomainObject{
	/**
	 * ID kategorije kao int.
	 */
	private int categoryID;
	/**
	 * Naziv kategorije kao String.
	 */
    private String name;
    /**
     * Besparametarski konstruktor koji inicijalizuje objekat klase Category.
     */
    public Category() {
    }
    /**
     * Konstruktor sa parametrima koji inicijalizuje objekat klase Category
     * i postavlja vrednosti id-a i naziva kategorije sladoleda.
     * 
     * @param id ID kategorije kao int.
     * @param category Naziv kategorije kao String.
     */
    public Category(int id, String category) {
        
        setCategoryID(id);
        setName(category);
        
    }
    /**
     * Vraca ID kategorije kao int.
     * 
     * @return ID kategorije kao int.
     */
    public int getCategoryID() {
        return categoryID;
    }
    /**
     * Postavlja ID kategorije na prosledjenu vrednost.
     * 
     * @param categoryID ID kategorije kao int.
     * @throws IllegalArgumentException Ako je id manji od 1.
     */
    public void setCategoryID(int categoryID) {
    	if(categoryID < 1) {
    		throw new IllegalArgumentException("CategoryID ne sme biti manji od 1");
    	}
        this.categoryID = categoryID;
    }
    /**
     * Vraca naziv kategorije kao String.
     * 
     * @return Naziv kategorije kao String.
     */
    public String getName() {
        return name;
    }
    /**
     * Postavlja naziv kategorije na prosledjenu vrednost.
     * 
     * @param name Naziv kategorije kao String.
     * @throws NullPointerException Ako je name null.
     */
    public void setName(String name) {
    	if(name == null) {
    		throw new NullPointerException("Naziv ne sme biti null");
    	}
        this.name = name;
    }
    /**
     * Vraca String koji predstavlja naziv kategorije sladoleda.
     * 
     * @return Naziv kategorije sladoleda kao String.
     */
    @Override
    public String toString() {
        return  name;
    }
    /**
     * Poredi dva objekta kategorije po njihovom imenu.
     * 
     * @param obj Objekat sa kojim se poredi.
     * 
     * @return 
     *  <ul>
     * 	 <li>true - ako su oba objekta klase Category i imaju isto ime. </li>
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
        final Category other = (Category) obj;
       
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String getTable() {
        return "category";
    }

    @Override
    public String getParams() {
         return String.format("'%s', '%s'",categoryID, name);
    }

    @Override
    public String getPK() {
        return "categoryID";
    }

    @Override
    public int getPKvalue() {
        return categoryID;
    }

    @Override
    public List<DomainObject> RSTable(ResultSet rs) {
        List<DomainObject> categories = new ArrayList<>();
        try{
            while(rs.next()){
                int categoryID = rs.getInt("categoryID");
                String name = rs.getString("name");
                Category c = new Category(categoryID,name);
                categories.add(c);
            }
        }catch(SQLException ex){
            Logger.getLogger(Component.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error at Category - RSTable");
        }
        return categories;
    }

    @Override
    public String getUpdate() {
     return String.format("categoryID='%s', name='%s'",categoryID, name);
    }

    @Override
    public void setPKvalue(int pk) {
        categoryID = pk;
    }

    @Override
    public String columns() {
        return null;
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
        return "";
    }

    @Override
    public String getCompositePK() {
        return null;
    }
}
