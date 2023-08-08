package so;

import java.util.ArrayList;
import java.util.List;

import domain.DomainObject;
import domain.Recipe;

public class SOFindRecipes extends SO{
	List<DomainObject> list = new ArrayList<>();
    String filter;
    public SOFindRecipes(String filter){
        this.filter = filter; 
    }
    @Override
    protected void performOperation() throws Exception {
        List<DomainObject> allRecipes = dbb.getAll(new Recipe());
        for(DomainObject dom : allRecipes){
            Recipe r = (Recipe) dom;
            if(r.getName().contains(filter) || r.getShortCode().contains(filter) 
                    || r.getComment().contains(filter) || r.getPackaging().getName().contains(filter)){
                list.add(dom);
            }
        }
    }
    public List<DomainObject> getList(){
        return list;
    }
}
