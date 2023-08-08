package so;

import java.util.List;

import domain.DomainObject;
import domain.Packaging;

public class SOGetPackaging extends SO{
	private List<DomainObject> list;
    @Override
    protected void performOperation() throws Exception {
        list = dbb.getAll(new Packaging());
    }
    
    public List<DomainObject> getList(){
        return list;
    }
}
