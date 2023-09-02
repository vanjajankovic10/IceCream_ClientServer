package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import forms.ServerForm;

public class StartServerThread extends Thread{
	private ServerSocket serverSocket;
    public static int port = 9000;
    static List<HandleUserRequestsThread> users = new ArrayList<>();
    private static boolean on = false;
    ServerForm sf;
    
    public StartServerThread(ServerForm sf) throws IOException{
        serverSocket = new ServerSocket(port);
        this.sf = sf;
        System.out.println("Server started");
    }

    @Override
    public void run() {
        try{
            
            Socket socket = serverSocket.accept();
            HandleUserRequestsThread handle = new HandleUserRequestsThread(socket,users,sf);
            handle.start();
            users.add(handle);
            System.out.println("User is connected");
        }catch(IOException ex){
            ex.printStackTrace();
        } 
    }
    
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    
    public static boolean isOn(){
        return on;
    }
    public static void setOn (boolean on) {
        StartServerThread.on = on;
    }
    
    public void stopThread(){
        try{
            serverSocket.close();
            for(HandleUserRequestsThread userThread : users){
                userThread.getSocket().close();
            }
        }catch (IOException ex) {
             ex.printStackTrace();
         }
    }
}
