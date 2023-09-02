package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import controller.ServerController;
import domain.Component;
import domain.DomainObject;
import domain.Recipe;
import domain.Tehnolog;
import forms.ServerForm;
import operations.Operations;
import transfer.ServerResponse;
import transfer.UserRequest;

public class HandleUserRequestsThread extends Thread{
	private Socket socket;
    private List<HandleUserRequestsThread> users;
    ObjectInputStream in;
    ObjectOutputStream out;
    DomainObject user;
    ServerForm sf;

    public HandleUserRequestsThread(Socket socket, List<HandleUserRequestsThread> users, ServerForm sf){
        this.socket = socket;
        this.users = users;
        this.sf = sf;
    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            while(true){
                System.out.println("Waiting for request...");
                UserRequest ur = (UserRequest)in.readUnshared();
                ServerResponse sr = new ServerResponse();
                try{
                    int operation = ur.getOperation();
                    switch(operation){
                        case Operations.LOGIN:
                            Tehnolog tehn = (Tehnolog) ur.getParametar();
                            user = ServerController.getInstance().loginUser(tehn);
                            sr.setData(user);
                            sf.addUser(user);
                            break;
                        case Operations.GET_COMPONENT_CATEGORY:
                            List<DomainObject> listCategory = ServerController.getInstance().getComponentCategory();
                            sr.setData(listCategory);
                            break;
                        case Operations.SAVE_COMPONENT:
                            Component c = (Component) ur.getParametar();
                            DomainObject comp = ServerController.getInstance().saveComponent(c);
                            sr.setData(comp);
                            break;
                        case Operations.EDIT_COMPONENT:
                            Component co = (Component) ur.getParametar();
                            DomainObject compo = ServerController.getInstance().editComponent(co);
                            sr.setData(compo);
                            break;
                        case Operations.GET_COMPONENTS:
                            List<DomainObject> listComponents = ServerController.getInstance().getComponents();
                            sr.setData(listComponents);
                            break;
                        case Operations.FIND_COMPONENTS:
                            String filter = (String) ur.getParametar();
                            List<DomainObject> filtered = ServerController.getInstance().findComponents(filter);
                            sr.setData(filtered);
                            break;
                        case Operations.DELETE_COMPONENT:
                            Component delC = (Component) ur.getParametar();
                            DomainObject objDel = ServerController.getInstance().deleteComponent(delC);
                            sr.setData(delC);
                            break;
                        case Operations.GET_PACKAGING:
                            List<DomainObject> listPackaging = ServerController.getInstance().getPackaging();
                            sr.setData(listPackaging);
                            break;
                        case Operations.SAVE_RECIPE:
                            Recipe r = (Recipe) ur.getParametar();
                            DomainObject rec = ServerController.getInstance().saveRecipe(r);
                            sr.setData(rec);
                            break;
                        case Operations.EDIT_RECIPE:
                            Recipe re = (Recipe) ur.getParametar();
                            DomainObject recip = ServerController.getInstance().editRecipe(re);
                            sr.setData(recip);
                            break;    
                        case Operations.GET_RECIPES:
                            List<DomainObject> listRecipes = ServerController.getInstance().getRecipes();
                            sr.setData(listRecipes);
                            break;
                        case Operations.DELETE_RECIPE:
                            Recipe delR = (Recipe) ur.getParametar();
                            DomainObject deleted = ServerController.getInstance().deleteRecipe(delR);
                            sr.setData(deleted);
                            break;
                        case Operations.FIND_RECIPES:
                            String filterR = (String) ur.getParametar();
                            List<DomainObject> filteredR = ServerController.getInstance().findRecipes(filterR);
                            sr.setData(filteredR);
                            break;
                    }
                    sr.setSuccesfull(1);
                }
                catch(Exception ex){
                    sr.setSuccesfull(-1);
                    sr.setException(ex);
                }
                out.writeUnshared(sr);
            }
            
        }catch(SocketException e){
            try {
                System.out.println("Client is disconnecting...");
                ServerController.getInstance().logoutTehnolog((Tehnolog) user);
                sf.disconnectUser(user);
                in.close();
                out.close();
                socket.close();
                users.remove(this);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DomainObject getUser() {
        return user;
    }

    public void setUser(DomainObject user) {
        this.user = user;
    }
}
