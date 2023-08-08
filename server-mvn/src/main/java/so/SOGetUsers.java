package so;

import java.util.List;

import domain.DomainObject;
import domain.Tehnolog;

public class SOGetUsers extends SO{
	private List<DomainObject> list;
    @Override
    protected void performOperation() throws Exception {
        list = dbb.getAll(new Tehnolog());
    }
    
    public List<DomainObject> getList(){
        return list;
    }
}
