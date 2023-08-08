package controller;

import java.io.IOException;
import java.util.List;

import communication.ServerCommunication;
import domain.Component;
import domain.DomainObject;
import domain.Recipe;
import domain.Tehnolog;
import operations.Operations;
import transfer.ServerResponse;
import transfer.UserRequest;

public class ClientController {
	private static ClientController instance;
    private ClientController(){
        
    }
    
    public static ClientController getInstance(){
        if(instance == null){
            instance = new ClientController();
        }
        return instance;
    }
    
    private Object sendRequest(int operation, Object parametar) throws IOException, Exception{
        UserRequest ur = new UserRequest();
        ur.setOperation(operation);
        ur.setParametar(parametar);
        ServerCommunication.getInstance().sendRequest(ur);
        ServerResponse sr = ServerCommunication.getInstance().readResponse();
        if(sr.getSuccesfull() == 1){
            return sr.getData();
        }
        else{
            Exception ex = sr.getException();
            throw ex;
        }
        
    }
    
    private Object sendRequest(int operation) throws Exception {
        return sendRequest(operation, null);
    }
    
    public DomainObject login(String username, String password) throws Exception{
        Tehnolog t = new Tehnolog(username, password);
        return (DomainObject) sendRequest(Operations.LOGIN, t);
    }

    public List<DomainObject> getComponentCategory() throws Exception {
        return (List<DomainObject>) sendRequest(Operations.GET_COMPONENT_CATEGORY);
    }
    
    public DomainObject saveComponent(Component c) throws Exception{
        return (DomainObject) sendRequest(Operations.SAVE_COMPONENT, c);
    }

    public Component editComponent(Component c) throws Exception {
        return (Component) sendRequest(Operations.EDIT_COMPONENT, c);
    }

    public List<DomainObject> getComponents() throws Exception {
        return (List<DomainObject>) sendRequest(Operations.GET_COMPONENTS);
    }

    public List<DomainObject> findComponents(String filter) throws Exception {
        return (List<DomainObject>) sendRequest(Operations.FIND_COMPONENTS, filter);
    }

    public Component deleteComponent(Component selectedC) throws Exception {
        return (Component) sendRequest(Operations.DELETE_COMPONENT, selectedC);
    }

    public List<DomainObject> getPackaging() throws Exception {
        return (List<DomainObject>) sendRequest(Operations.GET_PACKAGING);
    }

    public DomainObject saveRecipe(Recipe r) throws Exception{
        return (DomainObject) sendRequest(Operations.SAVE_RECIPE, r);
    }
    
    public Recipe editRecipe(Recipe r) throws Exception {
        return (Recipe) sendRequest(Operations.EDIT_RECIPE, r);
    }

    public List<DomainObject> getRecipes() throws Exception {
        return (List<DomainObject>) sendRequest(Operations.GET_RECIPES);
    }
    
    public Recipe deleteRecipe(Recipe selectedR) throws Exception {
        return (Recipe) sendRequest(Operations.DELETE_RECIPE, selectedR);
    }

    public List<DomainObject> findRecipes(String filter) throws Exception {
        return (List<DomainObject>) sendRequest(Operations.FIND_RECIPES, filter);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
