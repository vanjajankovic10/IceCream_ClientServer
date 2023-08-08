package database;

import java.sql.Statement;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.DomainObject;

public class DBBroker {
	private Connection conn;
    private static DBBroker instance;
    
    public DBBroker(){
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("resources/db.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }
    public Connection getConnection() throws Exception{
        return conn;
    }
    
    public void stopConnection() throws Exception {
        try {
            conn.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Failed at stopping connection");
        }
    }
    
    public void commitTransaction() throws Exception {
        try {
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed at commiting transaction");
        }
   
    }
    
     public void rollbackTransaction() {
        try {
            conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed rollback transaction");
        }
    }
    
     public List<DomainObject> getAll(DomainObject dom) throws Exception {
        try {
            String query;
            if(dom.columns()==null){
                query = "SELECT * FROM " + dom.getTable();
            }else{
                query = "SELECT "+dom.columns()+" FROM " + dom.getJoin();
            }
            System.out.println(query);
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            List<DomainObject> objects = dom.RSTable(rs);
            s.close();
            return objects;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Failed at loading info about " + dom.getClass().getName());
        }
        
    }
    
     public DomainObject saveObject(DomainObject dom) throws Exception {
         
        try {
            String query = String.format("INSERT INTO %s VALUES (%s)", dom.getTable(), dom.getParams());
            System.out.println(query);
            Statement s = conn.createStatement();
            s.executeUpdate(query);
            ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID() as lastID from " + dom.getTable());
            while (rs.next()) {
                int lastid = rs.getInt("lastID");
                dom.setPKvalue(lastid);
                break;
            }
            s.close();
            return dom;            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Failed at saving object "+dom.getClass().getName());
        }
    }
    public DomainObject editObject(DomainObject dom) throws Exception {
        String query;
        try {
            if(dom.getPK() != null){
                query = String.format("UPDATE %s SET %s WHERE %s = %s", dom.getTable(), dom.getUpdate(), dom.getPK(), dom.getPKvalue());
            }
            else{
                query = String.format("UPDATE %s SET %s WHERE %s",dom.getTable(), dom.getUpdate(), dom.getCompositePK());
            }
            Statement s = conn.createStatement();
            s.executeUpdate(query);
            s.close();
            return dom;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Failed at editing " + dom.getClass().getName());
        }
       
    }
    
    public DomainObject deleteObject(DomainObject dom) throws Exception {     
       String upit;
        try {
            if(dom.getPK() != null){
                 upit = String.format("DELETE FROM %s WHERE %s = %s", dom.getTable(), dom.getPK(), dom.getPKvalue());
            }
            else{
                 upit = String.format("DELETE FROM %s WHERE %s",dom.getTable(), dom.getCompositePK() );
            }
            Statement s = conn.createStatement();
            s.executeUpdate(upit);
            commitTransaction();
            s.close();
            return dom;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Failed at deleting " + dom.getClass().getName() + ".");
        }
    }
    
     public List<DomainObject> getObject(DomainObject dom) throws Exception {
        try {
            String query = "SELECT "+dom.columns()+" FROM " + dom.getJoin()+" WHERE "+dom.getSearchAttribute()+dom.getAttributeValue();
            System.out.println(query);
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            List<DomainObject> objects = dom.RSTable(rs);
            s.close();
            return objects;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Failed at loading info about " + dom.getClass().getName() + ".");
        }
    }
}
