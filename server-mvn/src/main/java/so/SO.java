package so;

import database.DBBroker;
/**
 * Apstraktna klasa koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * Definise sablon za izvrsavanje sistemskih operacija i sve sistemske operacije je nasledjuju.
 * Svaka klasa treba da implementira metodu performOperation().
 * 
 * Klasa sadrzi metode za upravljanje transakcijama(commit i rollback) nad bazom podataka.
 * 
 * @author Vanja Jankovic
 *
 */
public abstract class SO {
	/**
	 * Objekat klase DBBroker koji omogucava komunikaciju sa bazom podataka.
	 */
	protected DBBroker dbb;
	/**
	 * Besparametarski konstruktor koji inicijalizuje novi objekat klase DBBroker.
	 */
    public SO(){
        this.dbb = new DBBroker();
    }
    /**
     * Konstruktor koji postavlja vrednost objekta DBBroker na prosledjenu vrednost.
     * 
     * @param dbb Objekat klase DBBroker.
     */
    public SO(DBBroker dbb){
        this.dbb = dbb;
    }
    /**
     * Izvrsava sistemsku operaciju.
     * 
     * Otvara konekciju sa bazom podataka, izvrsava konkretnu operaciju,
     * potvrdjuje transakciju ako je uspesno izvrsena, u suprotnom je 
     * ponistava. Na kraju zatvara konkenciju sa bazom podataka.
     * 
     * @throws Exception Ako se desi greska prilikom izvrsavanja sistemske operacije.
     */
    synchronized public void performSO() throws Exception{
        getConnection();
        try{
            performOperation();
            commitTransaction();
        }
        catch(Exception e){
            rollbackTransaction();
            throw e;
        }
        finally{
            stopConnection();
        }
        
    } 
    /**
     * Potvrdjuje transakciju nad bazom podataka.
     * 
     * @throws Exception Ako se desi greska prilikom potvrdjivanja transakcije.
     */
    private void commitTransaction() throws Exception {
        dbb.commitTransaction();
    }
    /**
     * Ponistava transakciju nad bazom podataka.
     * 
     */
    private void rollbackTransaction() {
        dbb.rollbackTransaction();
    }
    /**
     * Zatvara konekciju sa bazom podataka.
     * 
     * @throws Exception Ako se desi greska prilikom zatvaranja konekcije.
     */
    private void stopConnection() throws Exception {
        dbb.stopConnection();
    }
    /**
     * Otvara konekciju sa bazom podataka.
     * 
     * @throws Exception Ako se desi greska prilikom otvaranja konekcije.
     */
    private void getConnection() throws Exception {
        dbb.getConnection();
    }

    /**
     * Apstraktna metoda za izvrsavanje konkretne operacije.
     * 
     * Svaka klasa koja nasledjuje SO treba da implementira performOperation
     * i izvrsi specificnu operaciju nad bazom podataka karakteristicnu za tu SO.
     *
     * @throws Exception Ako se desi greska prilikom izvrsavanja operacije.
     */
    protected abstract void performOperation() throws Exception;
}
