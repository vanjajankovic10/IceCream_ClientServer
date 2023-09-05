package so;

import database.DBBroker;
import domain.DomainObject;

public class SODeleteRecipe extends SO{
	private DomainObject param;
    private DomainObject deleted;
    public SODeleteRecipe(DomainObject param){
    	super();
        this.param = param;
    }
    public SODeleteRecipe(DBBroker dbb){
    	super(dbb);
    }
    @Override
    protected void performOperation() throws Exception {
        deleted = dbb.deleteObject(param);
    }
    public DomainObject getDeleted(){
        return deleted;
    }
    public void setParam(DomainObject param) {
		this.param = param;
	}

    public DomainObject getParam() {
		return param;
	}
}
