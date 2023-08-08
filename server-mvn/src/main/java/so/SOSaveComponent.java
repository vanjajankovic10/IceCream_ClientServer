package so;

import domain.DomainObject;

public class SOSaveComponent extends SO{
    private DomainObject param;
    private DomainObject component;
    public SOSaveComponent(DomainObject param){
        this.param = param;
    }

    @Override
    protected void performOperation() throws Exception {
        component = dbb.saveObject(param);
    }
    public void setParam(DomainObject param){
        this.param = param;
    }
    public DomainObject getComponent(){
        return component;
    }
}
