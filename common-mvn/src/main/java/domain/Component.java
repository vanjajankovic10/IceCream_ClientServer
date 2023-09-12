package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Klasa koja predstavlja jednu komponentu(sastojak) sladoleda.
 * 
 * Klasa implementira interfejs DomainObject.
 * 
 * Komponenta ima svoj id, naziv, skraceni naziv, proizvodjaca i kategoriju kojoj pripada.
 * 
 * @author Vanja Jankovic
 *
 */
public class Component implements DomainObject{
	/**
	 * ID komponente kao int.
	 */
	 	private int componentID;
	 	/**
	 	 * Naziv komponente kao String.
	 	 */
	    private String name;
	    /**
	     * Skraceni naziv komponente kao String.
	     */
	    private String shortCode;
	    /**
	     * Naziv proizvodjaca kao String.
	     */
	    private String producer;
	    /**
	     * Kategorija komponente kao objekat klasa Category.
	     */
	    private Category category;
	    /**
	     * Besparametarski konstruktor koji inicijalizuje objekat klase Component.
	     */
	    public Component() {
	    }
	    /**
	     * Konstruktor sa parametrima koji inicijalizuje objekat klase Component
	     * i postavlja vrednosti id-a, naziva, skracenog naziva, naziva proizvodjaca
	     * i kategorije na prosledjene vrednosti.
	     * 
	     * @param id ID komponente kao int.
	     * @param name Naziv komponente kao String.
	     * @param shortCode Skraceni naziv komponente kao String.
	     * @param producer Naziv proizvodjaca kao String.
	     * @param category Kategorija komponente kao objekat klase Category.
	     */
	    public Component(int id, String name, String shortCode, String producer, Category category) {
	    	setComponentID(id);
	    	setName(name);
	    	setShortCode(shortCode);
	    	setProducer(producer);
	    	setCategory(category);
	    }
	    /**
	     * Vraca ID komponente kao int.
	     * 
	     * @return ID komponente kao int.
	     */
	    public int getComponentID() {
	        return componentID;
	    }
	    /**
	     * Postavlja ID komponente na prosledjenu vrednost.
	     * 
	     * @param componentID ID komponente kao int.
	     * @throws IllegalArgumentException Ako je id manji od 1.
	     */
	    public void setComponentID(int componentID) {
	    	if(componentID < 1) {
	    		throw new IllegalArgumentException("ComponentID ne sme biti manji od 1");
	    	}
	        this.componentID = componentID;
	    }
	    /**
	     * Vraca naziv komponente kao String.
	     * 
	     * @return Naziv komponente kao String.
	     */
	    public String getName() {
	        return name;
	    }
	    /**
	     * Postavlja naziv komponente na prosledjenu vrednost.
	     * 
	     * @param name Naziv komponente kao String.
	     * @throws NullPointerException Ako je name null.
	     */
	    public void setName(String name) {
	    	if(name == null) {
	    		throw new NullPointerException("Naziv ne sme biti null");
	    	}
	        this.name = name;
	    }
	    /**
	     * Vraca skraceni naziv komponente kao String.
	     * 
	     * @return Skraceni naziv komponente kao String.
	     */
	    public String getShortCode() {
	        return shortCode;
	    }
	    /**
	     * Postavlja skraceni naziv komponente na prosledjenu vrednost.
	     * 
	     * @param shortCode Skraceni naziv komponente kao String.
	     * @throws NullPointerException Ako je shortCode null.
	     */
	    public void setShortCode(String shortCode) {
	    	if(shortCode == null) {
	    		throw new NullPointerException("ShortCode ne sme biti null.");
	    	}
	        this.shortCode = shortCode;
	    }
	    /**
	     * Vraca naziv proizvodjaca kao String.
	     * 
	     * @return Naziv proizvodjaca kao String.
	     */
	    public String getProducer() {
	        return producer;
	    }
	    /**
	     * Postavlja naziv proizvodjaca na prosledjenu vrednost.
	     * 
	     * @param producer Naziv proizvodjaca kao String.
	     * @throws NullPointerException Ako je producer null.
	     */
	    public void setProducer(String producer) {
	    	if(producer == null) {
	    		throw new NullPointerException("Producer ne sme biti null");
	    	}
	        this.producer = producer;
	    }
	    /**
	     * Vraca kategoriju komponente kao objekat klase Category.
	     * 
	     * @return Kategorija komponente kao objekat klase Category.
	     */
	    public Category getCategory() {
	        return category;
	    }
	    /**
	     * Postavlja kategoriju komponente na prosledjenu vrednost.
	     * 
	     * @param category Kategorija komponente kao objekat klase Category.
	     * @throws NullPointerException Ako je category null.
	     */
	    public void setCategory(Category category) {
	    	if(category == null) {
	    		throw new NullPointerException("Category ne sme biti null");
	    	}
	        this.category = category;
	    }
	    /**
	     * Vraca String sa informacijama o komponenti sladoleda, odnosno informacijama
	     * o id-u, nazivu komponente, skracenom nazivu, proizvodjacu i kategoriji.
	     * 
	     * @return Informacije o komponenti sladoleda kao String.
	     */
	    @Override
	    public String toString() {
	        return "Component{" + "id=" + componentID + ", name=" + name + ", shortCode=" + shortCode + ", producer=" + producer + ", category=" + category + '}';
	    }
	    /**
	     * Poredi dva objekta komponente sladoleda po njihovom id-u, nazivu, 
	     * skracenom nazivu, proizvodjacu i kategoriji.
	     * 
	     * @param obj Objekat sa kojim se poredi.
	     * 
	     * @return 
	     *  <ul>
	     * 	 <li>true - ako su oba objekta klase Component i imaju isti id, naizv, skraceni naziv, proizvodjaca i kategoriju </li>
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
	        final Component other = (Component) obj;
	        if (this.componentID != other.componentID) {
	            return false;
	        }
	        if (!Objects.equals(this.name, other.name)) {
	            return false;
	        }
	        if (!Objects.equals(this.shortCode, other.shortCode)) {
	            return false;
	        }
	        if (!Objects.equals(this.producer, other.producer)) {
	            return false;
	        }
	        return Objects.equals(this.category, other.category);
	    }

	    @Override
	    public String getTable() {
	        return "component";
	    }

	    @Override
	    public String getParams() {
	        return String.format("'%s', '%s', '%s', '%s', '%s'",
	                componentID, name, shortCode, producer, category.getCategoryID());
	    }

	    @Override
	    public String getPK() {
	        return "componentID";
	    }

	    @Override
	    public int getPKvalue() {
	        return componentID;
	    }

	    @Override
	    public List<DomainObject> RSTable(ResultSet rs) {
	        List<DomainObject> components = new ArrayList<>();
	        try{
	            while(rs.next()){
	                int componentID = rs.getInt("componentID");
	                String name = rs.getString("name");
	                String shortCode = rs.getString("shortCode");
	                String producer = rs.getString("producer");
	                int categoryID = rs.getInt("categoryID");
	                Component c = new Component(componentID,name,shortCode,producer,new Category(categoryID,rs.getString("categoryName")));
	                components.add(c);
	            }
	        }catch(SQLException ex){
	            Logger.getLogger(Component.class.getName()).log(Level.SEVERE, null, ex);
	            System.out.println("Error at Component - RSTable");
	        }
	        return components;
	    }

	    @Override
	    public String getUpdate() {
	        return String.format("componentID='%s', name='%s', shortCode='%s', producer='%s', categoryID='%s'",
	                componentID, name, shortCode, producer,category.getCategoryID() );
	    }

	    @Override
	    public void setPKvalue(int pk) {
	        this.componentID = pk;
	    }

	    @Override
	    public String columns() {
	        return "comp.*, cat.name AS categoryName ";
	    }

	    @Override
	    public String getSearchAttribute() {
	        return null;
	    }

	    @Override
	    public String getAttributeValue() {
	        return null;

	    }

	    @Override
	    public String getJoin() {
	        return "component comp JOIN category cat ON comp.categoryID = cat.categoryID";
	    }

	    @Override
	    public String getCompositePK() {
	        return null;
	    }
}
