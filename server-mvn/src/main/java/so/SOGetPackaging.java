package so;

import java.util.List;

import database.DBBroker;
import domain.DomainObject;
import domain.Packaging;

public class SOGetPackaging extends SO{
	private List<DomainObject> list;
	public SOGetPackaging() {
		super();
	}
	
	public SOGetPackaging(DBBroker dbb) {
		super(dbb);
	}
    @Override
    protected void performOperation() throws Exception {
        list = dbb.getAll(new Packaging());
    }
    
    public List<DomainObject> getList(){
        return list;
    }
}
