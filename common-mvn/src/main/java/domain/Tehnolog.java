package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Klasa koja predstavlja korisnika aplikacije, odnosno tehnologa u fabrici sladoleda.
 * 
 * Klasa implementira interfejs DomainObject.
 * 
 * Tehnolog ima svoj id, ime, prezime, korisnicko ime, lozinku i atribut koji oznacava
 * da li je tehnolog ulogovan.
 * 
 * @author Vanja Jankovic
 *
 */
public class Tehnolog implements DomainObject{
	/**
	 * Id tehnologa kao int.
	 */
	private int tehnologID;
	/**
	 * Ime tehnologa kao String.
	 */
    private String name;
    /**
     * Prezime tehnologa kao String.
     */
    private String surname;
    /**
     * Korisnicko ime tehnologa kao String.
     */
    private String username;
    /**
     * Lozinka tehnologa kao String.
     */
    private String password;
    /**
     * Atribut koji oznacava da li je tehnolog ulogovan ili ne.
     * 
     * <ul>
     * 	<li>true - Tehnolog je ulogovan</li>
     * <li>false - Tehnolog nije ulogovan</li>
     * </ul>
     */
    private boolean loggedIn;
/**
 * Besparametarski konstruktor koji inicijalizuje objekat klase Tehnolog.
 */
    public Tehnolog() {
    }
    /**
     * Konstruktor sa parametrima koji inicijalizuje objekat klase Tehnolog i
     * postavlja vrednosti korisnickog imena i lozinke na prosledjene vrednosti.
     * 
     * @param username Korisnicko ime tehnologa kao String.
     * @param password Lozinka tehnologa kao String.
     */
    public Tehnolog(String username, String password) {
    	setUsername(username);
    	setPassword(password);
    }
    /**
     * Konstruktor sa parametrima koji inicijalizuje objekat klase Tehnolog i
     * postavlja vrednosti id-a, imena, prezimena, korisnickog imena i lozinke
     *  na prosledjene vrednosti.
     * 
     * @param id ID tehnologa kao int.
     * @param name Ime tehnologa kao String.
     * @param surname Prezime tehnologa kao String.
     * @param username Korisnicko ime tehnologa kao String.
     * @param password Lozinka korisnika kao String.
     */
    public Tehnolog(int id, String name, String surname, String username, String password) {
        setTehnologID(id);
        setName(name);
        setSurname(surname);
        setUsername(username);
        setPassword(password);
    }
    /**
     * Vraca ID tehnologa kao int.
     * @return ID tehnologa kao int.
     */
    public int getTehnologID() {
        return tehnologID;
    }
/**
 * Postavlja ID tehnologa na prosledjenu vrednost.
 * 
 * @param tehnologID ID tehnologa koji se prosledjuje kao int.
 * @throws IllegalArgumentException Ako je id manji od 1.
 */
    public void setTehnologID(int tehnologID) {
    	if(tehnologID < 1) {
    		throw new IllegalArgumentException("ID tehnologa ne sme biti manji od 1");
    	}
        this.tehnologID = tehnologID;
    }
/**
 * Vraca ime tehnologa kao String.
 * 
 * @return Ime tehnologa kao String.
 */
    public String getName() {
        return name;
    }
    /**
     * Postavlja ime tehnologa na prosledjenu vrednost.
     * 
     * @param name Ime tehnologa kao String.
     * @throws NullPointerException Ako je ime null.
     */
    public void setName(String name) {
    	if(name == null) {
    		throw new NullPointerException("Ime ne sme biti null");
    	}
        this.name = name;
    }
    /**
     * Vraca prezime tehnologa kao String.
     * 
     * @return Prezime tehnologa kao String.
     */
    public String getSurname() {
        return surname;
    }
    /**
     * Postavlja prezime tehnologa na prosledjenu vrednost.
     * 
     * @param surname Prezime tehnologa kao String.
     * @throws NullPointerException Ako je prezime null.
     */
    public void setSurname(String surname) {
    	if(surname == null) {
    		throw new NullPointerException("Prezime ne sme biti null");
    	}
        this.surname = surname;
    }
    /**
     * Vraca korisnicko ime tehnologa kao String.
     * 
     * @return Korisnicko ime tehnologa kao String.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Postavlja korisnicko ime tehnologa na prosledjenu vrednost.
     * 
     * @param username Korisnicko ime tehnologa kao String.
     * @throws NullPointerException Ako je username null.
     */
    public void setUsername(String username) {
    	if(username == null) {
    		throw new NullPointerException("Username ne sme biti null");
    	}
        this.username = username;
    }
    /**
     * Vraca lozinku tehnologa kao String.
     * 
     * @return Lozinka tehnologa kao String.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Postavlja lozinku tehnologa kao String.
     * 
     * @param password Lozinka tehnologa kao String.
     * @throws NullPointerException Ako je password null.
     */
    public void setPassword(String password) {
    	if(password == null) {
    		throw new NullPointerException("Password ne sme biti null");
    	}
        this.password = password;
    }
    /**
     * Vraca String koji sadrzi sve informacije o tehnologu, odnosno sadrzi
     * id, ime, prezime, korisnicko ime i lozinku.
     * 
     * @return Informacije o tehnologu kao String.
     */
    @Override
    public String toString() {
        return "Tehnolog{" + "id=" + tehnologID + ", name=" + name + ", surname=" + surname + ", username=" + username + ", password=" + password + '}';
    }
/**
 * Poredi dva tehnologa po njihovom ID-u, imenu, prezimenu, korisnickom imenu i
 * lozinci. Vraca true ili false.
 * 
 * @param obj Objekat sa kojim se poredi.
 * 
 * @return 
 *  <ul>
 * 	 <li>true - ako su oba objekta klase Tehnolog i imaju isti ID, ime, prezime, korisnicko ime i lozinku. </li>
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
/**
 * Vraca indikator da li je tehnolog ulogovan ili ne.
 * 
 * @return Indikator da li je tehnolog ulogovan.
  * <ul>
	 * <li>true - Tehnolog je ulogovan </li>
	 * <li>false - Tehnolog nije ulogovan</li>
	 * </ul>
 */
    public boolean isLoggedIn() {
        return loggedIn;
    }
/**
 * Postavlja indikator da li je tehnolog ulogovan ili ne.
 * 
 * @param loggedIn Indikator da li je tehnolog ulogovan.
 * <ul>
	 * <li>true - Tehnolog je ulogovan </li>
	 * <li>false - Tehnolog nije ulogovan</li>
	 * </ul>
 */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
