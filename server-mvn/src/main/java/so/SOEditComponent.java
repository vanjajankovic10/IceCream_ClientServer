package so;

import database.DBBroker;
import domain.Component;
import domain.DomainObject;

public class SOEditComponent extends SO{
	private DomainObject param;
    private DomainObject component;
    public SOEditComponent(DomainObject param){
        this.param = param;
    }
    public SOEditComponent() {
    	super();
	}

    public SOEditComponent(DBBroker dbb) {
    	super(dbb);
	}

    @Override
    protected void performOperation() throws Exception {
        component = dbb.editObject(param);
    }
    
    public DomainObject getParam(){
        return param;
    }
    
    public void setParam(DomainObject param){
        this.param = param;
    }
    
    public DomainObject getComponent(){
        return component;
    }
    
    public void preduslov() {
    	if(!(param instanceof Component) && param!=null) {
    		throw new IllegalArgumentException("Poslati objekat nije odogvarajuce klase");
    	}
    }
}
