package so;

import domain.DomainObject;

public class SOEditComponent extends SO{
	private DomainObject param;
    private DomainObject component;
    public SOEditComponent(DomainObject param){
        this.param = param;
    }
    

    @Override
    protected void performOperation() throws Exception {
        component = dbb.editObject(param);
    }
    
    public DomainObject getParam(){
        return param;
    }
    
    public void setParam(DomainObject param){
        this.param = param;
    }
    
    public DomainObject getComponent(){
        return component;
    }
}
