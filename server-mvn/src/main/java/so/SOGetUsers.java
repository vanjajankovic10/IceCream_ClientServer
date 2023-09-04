package so;

import java.util.List;

import database.DBBroker;
import domain.DomainObject;
import domain.Tehnolog;

public class SOGetUsers extends SO{
	private List<DomainObject> list;
	
	public SOGetUsers() {
		super();
	}
	
	public SOGetUsers(DBBroker dbb) {
		super(dbb);
	}
    @Override
    protected void performOperation() throws Exception {
        list = dbb.getAll((DomainObject)new Tehnolog());
    }
    
    public List<DomainObject> getList(){
        return list;
    }
}
