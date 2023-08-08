package so;

import java.util.List;

import domain.DomainObject;
import domain.Recipe;

public class SOGetRecipes extends SO{
	private List<DomainObject> list;
    @Override
    protected void performOperation() throws Exception {
        list = dbb.getAll(new Recipe());
    }
    public List<DomainObject> getList(){
        return list;
    }
}
