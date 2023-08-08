package so;

import java.util.List;

import domain.Category;
import domain.DomainObject;

public class SOGetComponentCategory extends SO{
	List<DomainObject> list;
    @Override
    protected void performOperation() throws Exception {
        list = dbb.getAll(new Category());
    }
    public List<DomainObject> getList(){
        return list;
    }
}
