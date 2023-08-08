package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Packaging implements DomainObject{
	private int packagingID;
    private String name;

    public Packaging() {
    }

    public Packaging(int id, String name) {
        this.packagingID = id;
        this.name = name;
    }

    public int getPackagingID() {
        return packagingID;
    }

    public void setPackagingID(int packagingID) {
        this.packagingID = packagingID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
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
        final Packaging other = (Packaging) obj;
        if (this.packagingID != other.packagingID) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String getTable() {
        return "packaging";
    }

    @Override
    public String getParams() {
        return String.format("'%s', '%s'",packagingID, name);
    }

    @Override
    public String getPK() {
        return "packagingID";
    }

    @Override
    public int getPKvalue() {
        return packagingID;
    }

    @Override
    public List<DomainObject> RSTable(ResultSet rs) {
        List<DomainObject> packagings = new ArrayList<>();
        try{
            while(rs.next()){
                int packagingID = rs.getInt("packagingID");
                String name = rs.getString("name");
                Packaging p = new Packaging(packagingID, name);
                packagings.add(p);
            }
        }catch(SQLException ex){
            Logger.getLogger(Component.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error at Packaging - RSTable");
        }
        return packagings;
    }

    @Override
    public String getUpdate() {
        return String.format("packagingID='%s', name='%s'",packagingID, name);
    }

    @Override
    public void setPKvalue(int pk) {
        packagingID = pk;
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
