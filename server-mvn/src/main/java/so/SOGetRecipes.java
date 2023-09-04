package so;

import java.util.List;

import database.DBBroker;
import domain.DomainObject;
import domain.Recipe;

public class SOGetRecipes extends SO{
	private List<DomainObject> list;
	public SOGetRecipes() {
		super();
	}
		
	public SOGetRecipes(DBBroker dbb) {
		super(dbb);
	}
    @Override
    protected void performOperation() throws Exception {
        list = dbb.getAll(new Recipe());
    }
    public List<DomainObject> getList(){
        return list;
    }
}
