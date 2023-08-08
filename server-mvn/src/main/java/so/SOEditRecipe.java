package so;

import domain.DomainObject;

public class SOEditRecipe extends SO{
	private DomainObject param;
    private DomainObject recipe;
    public SOEditRecipe(DomainObject param){
        this.param = param;
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
}
