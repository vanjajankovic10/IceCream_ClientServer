package so;

import database.DBBroker;
import domain.Component;
import domain.DomainObject;
import domain.Recipe;

public class SOEditRecipe extends SO{
	private DomainObject param;
    private DomainObject recipe;
    public SOEditRecipe(DomainObject param){
        this.param = param;
    }
    public SOEditRecipe() {
    	super();
	}

    public SOEditRecipe(DBBroker dbb) {
    	super(dbb);
	}
    @Override
    protected void performOperation() throws Exception {
        recipe = dbb.editObject(param);
    }
    public DomainObject getParam(){
        return param;
    }
    
    public void setParam(DomainObject param){
        this.param = param;
    }
    public DomainObject getRecipe(){
        return recipe;
    }
    public void preduslov() {
    	if(!(param instanceof Recipe) && param!=null) {
    		throw new IllegalArgumentException("Poslati objekat nije odogvarajuce klase");
    	}
    }
}
