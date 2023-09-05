package so;

import java.util.List;

import database.DBBroker;
import domain.Component;
import domain.DomainObject;
import domain.Recipe;
import domain.RecipeItem;

public class SOSaveRecipe extends SO{
    private DomainObject param;
    private DomainObject recipe;
    public SOSaveRecipe(DomainObject param){
    	super();
        this.param = param;
    }
    public SOSaveRecipe(DBBroker dbb){
    	super(dbb);
    }
    @Override
    protected void performOperation() throws Exception {
        recipe = dbb.saveObject(param);
        saveItems();
    }
    public void setParam(DomainObject param){
        this.param = param;
    }
    public DomainObject getRecipe(){
        return recipe;
    }
    public DomainObject getParam(){
        return param;
    }
    private void saveItems() throws Exception {
        List<DomainObject> items = ((Recipe)recipe).getComponents();
        for(DomainObject dom : items){
            RecipeItem item = (RecipeItem) dom;
            item.setRecipe((Recipe)recipe);
            dbb.saveObject(item);
        }
    }
    public void preduslov() throws Exception {
    	if(!(param instanceof Recipe) && param!=null) {
    		throw new IllegalArgumentException("Poslati objekat nije odogvarajuce klase");
    	}
    }
}
