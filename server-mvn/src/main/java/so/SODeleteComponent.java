package so;

import domain.DomainObject;

public class SODeleteComponent extends SO{
	private DomainObject param;
    private DomainObject deleted;
    public SODeleteComponent(DomainObject param){
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
