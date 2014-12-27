/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ChatServer;

import applicationController.ApplicationControllerSingleton;
import applicationController.Handlers.GetAllChatsHandler;
import applicationController.Handlers.LoginHandler;
import applicationController.Handlers.SendChatHandler;

/**
 *
 * @author cgood92
 */
public class ChatServer {

    
    public static void main(String[] args) {
        //Set-up application controller
        ApplicationControllerSingleton.getInstance().addHandler("LOGIN", new LoginHandler());
        ApplicationControllerSingleton.getInstance().addHandler("SENDCHAT", new SendChatHandler());
        ApplicationControllerSingleton.getInstance().addHandler("GETALLCHATS", new GetAllChatsHandler());
        
        //Setup socket
        SocketIO mySession = new SocketIO();
        mySession.initSocket();
    }
}
