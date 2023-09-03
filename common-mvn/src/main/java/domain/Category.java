package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Category implements DomainObject{
	private int categoryID;
    private String name;

    public Category() {
    }

    public Category(int id, String category) {
        this.categoryID = id;
        this.name = category;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
    	if(categoryID < 1) {
    		throw new IllegalArgumentException("CategoryID ne sme biti manji od 1");
    	}
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
    	if(name == null) {
    		throw new NullPointerException("Naziv ne sme biti null");
    	}
        this.name = name;
    }

    @Override
    public String toString() {
        return  name;
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
