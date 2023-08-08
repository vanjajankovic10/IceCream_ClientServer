package so;

import java.util.ArrayList;
import java.util.List;

import domain.Component;
import domain.DomainObject;

public class SOFindComponents extends SO{
	List<DomainObject> list = new ArrayList<>();
    String filter;
    public SOFindComponents(String filter){
        this.filter = filter; 
    }
    

    @Override
    protected void performOperation() throws Exception {
        List<DomainObject> allComp = dbb.getAll(new Component());
        for(DomainObject ob: allComp){
            Component c = (Component) ob;
            if(c.getName().contains(filter) || c.getShortCode().contains(filter) 
                    || c.getProducer().contains(filter) || c.getCategory().getName().contains(filter)){
                list.add(ob);
            }
        }
    }
    
    public List<DomainObject> getList(){
        return list;
    }
}
