package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Klasa koja predstavlja nacin pakovanja sladoleda.
 * 
 * Klasa implementira interfejs DomainObject.
 * 
 * Pakovanje ima svoj id i naziv pakovanja.
 * 
 * @author Vanja Jankovic
 *
 */
public class Packaging implements DomainObject{
	/**
	 * ID pakovanja kao int.
	 */
	private int packagingID;
	/**
	 * Naziv pakovanja kao String.
	 */
    private String name;
    /**
     * Besparametarski konstruktor koji inicijalizuje objekat klase Packaging.
     */
    public Packaging() {
    }
    /**
     * Konstruktor sa parametrima koji inicijalizuje objekat klase Packaging
     * i postavlja vrednosti id-a i naziva pakovanja sladoleda.
     * 
     * @param id ID pakovanja kao int.
     * @param name Naziv pakovanja kao String.
     */
    public Packaging(int id, String name) {
        this.packagingID = id;
        this.name = name;
    }
    /**
     * Vraca ID pakovanja kao int.
     * 
     * @return ID pakovanja kao int.
     */
    public int getPackagingID() {
        return packagingID;
    }
    /**
     * Postavlja ID pakovanja na prosledjenu vrednost.
     * 
     * @param packagingID ID pakovanja kao int.
     */
    public void setPackagingID(int packagingID) {
    	if(packagingID < 1) {
    		throw new IllegalArgumentException("PackagingID ne sme biti manji od 1");
    	}
        this.packagingID = packagingID;
    }
    /**
     * Vraca naziv pakovanja kao String.
     * 
     * @return Naziv pakovanja kao String.
     */
    public String getName() {
        return name;
    }
    /**
     * Postavlja naziv pakovanja na prosledjenu vrednost.
     * 
     * @param name Naziv pakovanja kao String.
     */
    public void setName(String name) {
    	if(name == null) {
    		throw new NullPointerException("Ime ne sme biti null");
    	}
        this.name = name;
    }
    /**
     * Vraca String koji predstavlja naziv pakovanja sladoleda.
     * 
     * @return Naziv pakovanja sladoleda kao String.
     */
    @Override
    public String toString() {
        return name;
    }
    /**
     * Poredi dva objekta pakovanja po njihovom ID-u i imenu.
     * 
     * @param obj Objekat sa kojim se poredi.
     * 
     * @return 
     *  <ul>
     * 	 <li>true - ako su oba objekta klase Packaging i imaju isti ID i ime. </li>
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
