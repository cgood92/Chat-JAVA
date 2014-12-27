/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package applicationController;

import java.util.HashMap;
import org.quickconnectfamily.json.JSONOutputStream;

/**
 *
 * @author cgood92
 */
public class ApplicationControllerSingleton {
    private static ApplicationControllerSingleton instance = null;
    HashMap<String, HandlersInterface> handlers;
    
    protected ApplicationControllerSingleton()
    {
        this.handlers = new HashMap<String, HandlersInterface>();
    }
    
    public static synchronized ApplicationControllerSingleton getInstance() {
        if(instance == null) 
        {
            instance = new ApplicationControllerSingleton();
        }
        return instance;
    }
    
    public void handleRequest(HashMap inMap, JSONOutputStream outToClient)
    {
        HandlersInterface myHandlersInterface = this.handlers.get(inMap.get("command"));
        if (myHandlersInterface != null)
        {
            myHandlersInterface.handleIt(inMap, outToClient);
        }
        else
        {
            System.out.println("Sorry, but you do not have a handler for that command");
        }
    }
    
    public void addHandler(String name, HandlersInterface handler)
    {
        this.handlers.put(name, handler);
    }
}
