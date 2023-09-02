package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.ServerResponse;
import transfer.UserRequest;

public class ServerCommunication {
		 private static ServerCommunication instance;
		 private static Socket socket;
		 ObjectInputStream in;
		 ObjectOutputStream out;
		 
		 public ServerCommunication() {
			 
		 }
	    
	    public static ServerCommunication getInstance(){
	        if(instance == null){
	            instance = new ServerCommunication();
	        }
	        return instance;
	    }   
	    
	    public Socket getSocket(){
	        return socket;
	    }
	    
	    public void setSocket(Socket socket){
	        try {
	            ServerCommunication.socket = socket;
	            out = new ObjectOutputStream(ServerCommunication.socket.getOutputStream());
	            in = new ObjectInputStream(ServerCommunication.socket.getInputStream());
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	    
	    public void sendRequest(UserRequest ur) throws IOException{
	        out.writeUnshared(ur);
	    }
	    
	    public ServerResponse readResponse() throws IOException, ClassNotFoundException{
	        return (ServerResponse)in.readUnshared();
	    }
}
