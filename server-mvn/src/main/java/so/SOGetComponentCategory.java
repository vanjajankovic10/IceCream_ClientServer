package so;

import java.util.List;

import database.DBBroker;
import domain.Category;
import domain.DomainObject;

public class SOGetComponentCategory extends SO{
	List<DomainObject> list;
	public SOGetComponentCategory() {
		super();
	}
	
	public SOGetComponentCategory(DBBroker dbb) {
		super(dbb);
	}
    @Override
    protected void performOperation() throws Exception {
        list = dbb.getAll(new Category());
    }
    public List<DomainObject> getList(){
        return list;
    }
}
