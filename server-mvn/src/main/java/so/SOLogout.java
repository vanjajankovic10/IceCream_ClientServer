package so;

import controller.ServerController;
import domain.DomainObject;
import domain.Tehnolog;

public class SOLogout extends SO{
    private DomainObject tehnolog;

    public SOLogout(DomainObject tehnolog) {
        this.tehnolog = tehnolog;
    }
    
    @Override
    protected void performOperation() throws Exception {
        int index = ServerController.getInstance().getUsersList().indexOf(tehnolog);
        if(index != -1){
            ((Tehnolog) ServerController.getInstance().getUsersList().get(index)).setLoggedIn(false);
        }
    }
}
