package so;

import database.DBBroker;
import domain.Component;
import domain.DomainObject;

public class SOSaveComponent extends SO{
    private DomainObject param;
    private DomainObject component;
    public SOSaveComponent(DomainObject param){
    	super();
        this.param = param;
    }
    public SOSaveComponent(DBBroker dbb){
    	super(dbb);
    }

    @Override
    protected void performOperation() throws Exception {
    	preduslov();
        component = dbb.saveObject(param);
    }
    public void setParam(DomainObject param){
        this.param = param;
    }
    public DomainObject getParam(){
        return param;
    }
    public DomainObject getComponent(){
        return component;
    }
    
    public void preduslov() throws Exception {
    	if(!(param instanceof Component) && param!=null) {
    		throw new IllegalArgumentException("Poslati objekat nije odogvarajuce klase");
    	}
    }
    
}
