package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Component implements DomainObject{
	 private int componentID;
	    private String name;
	    private String shortCode;
	    private String producer;
	    private Category category;

	    public Component() {
	    }
	    
	    public Component(int id, String name, String shortCode, String producer, Category category) {
	        this.componentID = id;
	        this.name = name;
	        this.shortCode = shortCode;
	        this.producer = producer;
	        this.category = category;
	    }
	    
	    public int getComponentID() {
	        return componentID;
	    }

	    public void setComponentID(int componentID) {
	        this.componentID = componentID;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getShortCode() {
	        return shortCode;
	    }

	    public void setShortCode(String shortCode) {
	        this.shortCode = shortCode;
	    }

	    public String getProducer() {
	        return producer;
	    }

	    public void setProducer(String producer) {
	        this.producer = producer;
	    }

	    public Category getCategory() {
	        return category;
	    }

	    public void setCategory(Category category) {
	        this.category = category;
	    }

	    @Override
	    public String toString() {
	        return "Component{" + "id=" + componentID + ", name=" + name + ", shortCode=" + shortCode + ", producer=" + producer + ", category=" + category + '}';
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
