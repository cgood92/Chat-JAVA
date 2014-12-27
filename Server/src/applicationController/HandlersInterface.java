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
public interface HandlersInterface {
    public void handleIt(HashMap inMap, JSONOutputStream outToClient);
}
