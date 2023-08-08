package so;

import database.DBBroker;

public abstract class SO {
	protected DBBroker dbb;
    public SO(){
        this.dbb = new DBBroker();
    }
    
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
    
    private void commitTransaction() throws Exception {
        dbb.commitTransaction();
    }

    private void rollbackTransaction() {
        dbb.rollbackTransaction();
    }

    private void stopConnection() throws Exception {
        dbb.stopConnection();
    }

    private void getConnection() throws Exception {
        dbb.getConnection();
    }

    
    protected abstract void performOperation() throws Exception;
}
