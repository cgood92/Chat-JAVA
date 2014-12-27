/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ChatServer;

import applicationController.ApplicationControllerSingleton;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;

/**
 *
 * @author cgood92
 */
public class SocketIO {
    ApplicationControllerSingleton myAppController;
    
    public SocketIO(){
        myAppController = ApplicationControllerSingleton.getInstance();
    }
    public void initSocket(){
            try {
            //a socket opened on the specified port
            ServerSocket aListeningSocket = new ServerSocket(9292);
            while (true) {
                //wait for a connection
                System.out.println("Waiting for client connectionrequest.");
                Socket clientSocket = aListeningSocket.accept();
                
                //setup the JSON streams for later use.
                JSONInputStream inFromClient  = new JSONInputStream(clientSocket.getInputStream());
                JSONOutputStream outToClient  = new JSONOutputStream(clientSocket.getOutputStream());
                
                //read until the client closes the connection
                while (true) {
                    try
                    {
                        System.out.println("Waiting for a message from the server.");
                        HashMap inMap = (HashMap)inFromClient.readObject();
                        myAppController.handleRequest(inMap, outToClient);
                    }
                    catch(Exception e)
                    {
                        //Do nothing, ignore the exception.  Exception always happens when socket is closed
                        e.printStackTrace();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
