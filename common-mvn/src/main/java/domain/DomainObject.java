package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;
/**
 * Interfejs koji daje specifikaciju svih operacija neophodnih za rad sa upitima
 * prilikom komunikacije izmedju aplikacije i baze podataka.
 * 
 * Nasledjuje interfejs Serializable koji omogucava da se objekat prenosi preko mreze.
 * 
 * @author Vanja Jankovic
 *
 */
public interface DomainObject extends Serializable{
	/**
	 * Vraca naziv tabele iz baze podataka kao String.
	 * 
	 * @return Naziv tabele kao String.
	 */
	public String getTable();
	/**
	 * Vraca vrednosti parametara koji se prosledjuju kao atributi klase.
	 * 
	 * @return Vrednosti atributa klase kao String.
	 */
    public String getParams();
    /**
     * Vraca naziv kolone primarnog kljuca.
     * 
     * @return Naziv primarnog kljuca kao String.
     */
    public String getPK();
    /**
     * Vraca naziv kolona primarnog kljuca.
     * 
     * @return Naziv slozenog primarnog kljuca kao String.
     */
    public String getCompositePK();
    /**
     * Vraca vrednost primarnog kljuca.
     * 
     * @return Vrednost primarnog kljuca kao int.
     */
    public int getPKvalue();
    /**
     * Vraca listu objekata koji predstavljaju ucitane redove iz baze podataka 
     * nakon izvrsavanja SQL upita.
     * 
     * @param rs Objekat koji se prosledjuje metode a sadrzi podatke iz baze 
     * nakon izvrsavanja SQL upita.
     * 
     * @return Lista objekata koji su ucitani na osnovu vrednosti koja se
     * dobija iz baze nakon izvrsavanja SQL upita.
     * 
     */
    public List<DomainObject> RSTable(ResultSet rs);
    /**
     * Vraca String koji sadrzi ime kolona(nazivi atributa) i vrednosti koji
     * su potrebeni za azuriranje reda u bazi podataka.
     * 
     * @return String koji sadrzi ime kolona i njihove nove vrednosti.
     */
    public String getUpdate();
    /**
     * Postavlja vrednost primarnog kljuca na prosledjenu vrednost.
     * 
     * @param pk Vrednost primarnog kljuca koja se prosledjuje kao int.
     */
    public void setPKvalue(int pk);
    /**
     * Vraca nazive svih kolona odredjene tabele koja se koristi u SQL upitu.
     * 
     * @return Nazivi svih kolona tabele odvojeni zarezima kao String.
     */
    public String columns();
    /**
     * Vraca deo upita koji sadrzi naziv kolone na osnovu kojih ce se vrsiti pretraga.
     * 
     * @return Naziv kolona potrebnih za pretragu kao String.
     */
    public String getSearchAttribute();
    /**
     * Vraca vrednost atributa koji se koristi za pretragu objekta.
     * 
     * @return Vrednost atributa za pretragu objekta kao String.
     */
    public String getAttributeValue();
    /**
     * Vraca uslov za JOIN operaciju SQL upita na osnovu kojeg se vrsi spajanje tabela.
     * 
     * @return Uslov za JOIN operaciju kao String.
     */
    public String getJoin();
}
