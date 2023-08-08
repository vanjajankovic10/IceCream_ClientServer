package so;

import domain.DomainObject;

public class SODeleteRecipe extends SO{
	private DomainObject param;
    private DomainObject deleted;
    public SODeleteRecipe(DomainObject param){
        this.param = param;
    }
    @Override
    protected void performOperation() throws Exception {
        deleted = dbb.deleteObject(param);
    }
    public DomainObject getDeleted(){
        return deleted;
    }
}
