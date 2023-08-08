package so;

import java.util.List;

import controller.ServerController;
import domain.DomainObject;
import domain.Tehnolog;

public class SOLogin extends SO{
    private DomainObject params;
    private DomainObject user;
    @Override
    protected void performOperation() throws Exception {
        List<DomainObject> tehnologList = dbb.getAll(new Tehnolog());
        Tehnolog sentTehn = (Tehnolog) params;
        for(DomainObject t : tehnologList){
            
            Tehnolog baseTehn = (Tehnolog)t;
            if(baseTehn.getUsername().equals(sentTehn.getUsername()) &&
                    baseTehn.getPassword().equals(sentTehn.getPassword())){
                user = baseTehn;
                int index = ServerController.getInstance().getUsersList().indexOf(baseTehn);
                Tehnolog listTehn = (Tehnolog) ServerController.getInstance().getUsersList().get(index);
                if(listTehn.isLoggedIn()){
                    throw new Exception("Tehnolog is already logged in!");
                }
                else{
                    listTehn.setLoggedIn(true);
                }
                return;
            }
        }
        user = null;
    }

    public DomainObject getParams() {
        return params;
    }

    public void setParams(DomainObject params) {
        this.params = params;
    }

    public DomainObject getUser() {
        return user;
    }

    public void setUser(DomainObject user) {
        this.user = user;
    }
    
}
