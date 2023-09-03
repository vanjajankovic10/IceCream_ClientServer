package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tehnolog implements DomainObject{
	private int tehnologID;
    private String name;
    private String surname;
    private String username;
    private String password;
    private boolean loggedIn;

    public Tehnolog() {
    }
    public Tehnolog(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Tehnolog(int id, String name, String surname, String username, String password) {
        this.tehnologID = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }
    
    public int getTehnologID() {
        return tehnologID;
    }

    public void setTehnologID(int tehnologID) {
    	if(tehnologID < 1) {
    		throw new IllegalArgumentException("ID tehnologa ne sme biti manji od 1");
    	}
        this.tehnologID = tehnologID;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
    	if(surname == null) {
    		throw new NullPointerException("Prezime ne sme biti null");
    	}
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
    	if(username == null) {
    		throw new NullPointerException("Username ne sme biti null");
    	}
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
    	if(password == null) {
    		throw new NullPointerException("Password ne sme biti null");
    	}
        this.password = password;
    }

    @Override
    public String toString() {
        return "Tehnolog{" + "id=" + tehnologID + ", name=" + name + ", surname=" + surname + ", username=" + username + ", password=" + password + '}';
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
        final Tehnolog other = (Tehnolog) obj;
        if (this.tehnologID != other.tehnologID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    @Override
    public String getTable() {
        return "tehnolog";
    }

    @Override
    public String getParams() {
        return String.format("'%s', '%s', '%s', '%s', '%s'",
                tehnologID, name, surname, username, password);
    }

    @Override
    public String getPK() {
        return "tehnologID";
    }

    @Override
    public int getPKvalue() {
        return tehnologID;
    }

    @Override
    public List<DomainObject> RSTable(ResultSet rs) {
        List<DomainObject> tehnologs = new ArrayList<>();
        try{
            while(rs.next()){
                int tehnologID = rs.getInt("tehnologID");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Tehnolog t = new Tehnolog(tehnologID,name,surname, username, password);
                tehnologs.add(t);
            }
        }catch(SQLException ex){
            Logger.getLogger(Component.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error at Tehnolog - RSTable");
        }
        return tehnologs;
    }

    @Override
    public String getUpdate() {
        return String.format("tehnologID='%s', name='%s', surname='%s', username='%s', password='%s'",
                tehnologID, name, surname, username, password);
    }

    @Override
    public void setPKvalue(int pk) {
        tehnologID = pk;
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

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
