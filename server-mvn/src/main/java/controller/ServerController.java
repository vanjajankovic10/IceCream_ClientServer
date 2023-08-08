package controller;

import java.util.List;

import domain.Component;
import domain.DomainObject;
import domain.Recipe;
import domain.Tehnolog;
import so.SODeleteComponent;
import so.SODeleteRecipe;
import so.SOEditComponent;
import so.SOEditRecipe;
import so.SOFindComponents;
import so.SOFindRecipes;
import so.SOGetComponentCategory;
import so.SOGetComponents;
import so.SOGetPackaging;
import so.SOGetRecipes;
import so.SOGetUsers;
import so.SOLogin;
import so.SOLogout;
import so.SOSaveComponent;
import so.SOSaveRecipe;

public class ServerController {
	private static ServerController instance;
    private List<DomainObject> usersList;
    
    private ServerController(){
        
    }
    
    public static ServerController getInstance(){
        if(instance == null){
            instance = new ServerController();
        }
        return instance;
    }
    
   public List<DomainObject> getUsersList() throws Exception {
       if (usersList == null) {
            usersList = getUsers();
        }
        return usersList;
    }

    private List<DomainObject> getUsers() throws Exception {
        SOGetUsers sogu = new SOGetUsers();
        sogu.performSO();
        return sogu.getList();
    }
    
    public DomainObject loginUser(Tehnolog tehn) throws Exception{
        SOLogin sol = new SOLogin();
        sol.setParams(tehn);
        sol.performSO();
        return sol.getUser();
    }

    public List<DomainObject> getComponentCategory() throws Exception {
        SOGetComponentCategory cc = new SOGetComponentCategory();
        cc.performSO();
        return cc.getList();
    }

    public DomainObject saveComponent(Component c) throws Exception {
        SOSaveComponent sc = new SOSaveComponent(c);
        sc.performSO();
        return sc.getComponent();
        
    }

    public DomainObject editComponent(Component co) throws Exception {
        SOEditComponent ec = new SOEditComponent(co);
        ec.performSO();
        return ec.getComponent();
    }

    public List<DomainObject> getComponents() throws Exception {
        SOGetComponents gc = new SOGetComponents();
        gc.performSO();
        return gc.getList();
    }

    public List<DomainObject> findComponents(String filter) throws Exception {
        SOFindComponents fc = new SOFindComponents(filter);
        fc.performSO();
        return fc.getList();
    }

    public DomainObject deleteComponent(Component delC) throws Exception {
        SODeleteComponent dc = new SODeleteComponent(delC);
        dc.performSO();
        return dc.getDeleted();
    }

    public List<DomainObject> getPackaging() throws Exception {
        SOGetPackaging gp = new SOGetPackaging();
        gp.performSO();
        return gp.getList();
    }

    public DomainObject saveRecipe(Recipe r) throws Exception {
        SOSaveRecipe sr = new SOSaveRecipe(r);
        sr.performSO();
        return sr.getRecipe();
    }

    public DomainObject editRecipe(Recipe re) throws Exception {
        SOEditRecipe er = new SOEditRecipe(re);
        er.performSO();
        return er.getRecipe();
    }

    public List<DomainObject> getRecipes() throws Exception {
        SOGetRecipes gr = new SOGetRecipes();
        gr.performSO();
        return gr.getList();
    }

    public DomainObject deleteRecipe(Recipe rec) throws Exception {
        SODeleteRecipe dr = new SODeleteRecipe(rec);
        dr.performSO();
        return dr.getDeleted();
    }

    public List<DomainObject> findRecipes(String filterR) throws Exception {
        SOFindRecipes fr = new SOFindRecipes(filterR);
        fr.performSO();
        return fr.getList();
    }

    public void logoutTehnolog(Tehnolog tehnolog) throws Exception {
        SOLogout lo = new SOLogout(tehnolog);
        lo.performSO();
    }
}
