/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package applicationController.Handlers;

import applicationController.APC_commBean;
import applicationController.HandlersInterface;
import hibernate.HibernateUtilSingleton;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.quickconnectfamily.json.JSONOutputStream;

/**
 *
 * @author cgood92
 */
public class LoginHandler implements HandlersInterface {
    private APC_commBean commBean;
    public LoginHandler()
    {
        this.commBean = new APC_commBean();
        
    }
    
    @Override
    public void handleIt(HashMap inMap, JSONOutputStream outToClient)
    {
        this.commBean.setCommand("LOGIN");
        HashMap data = new HashMap();
        
//        User user;
//        Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
//        Transaction transaction = session.beginTransaction();
//        Query getUserInfo = session.createQuery("from User where username = '" + ((HashMap) inMap.get("data")).get("userName").toString() + "'");
//        user = (User)getUserInfo.uniqueResult();
//        System.out.println("===========");
//        System.out.println(user);
//        System.out.println("===========");
//        transaction.commit();
        
        if (!((HashMap) inMap.get("data")).get("meetingID").toString().equals("") && !((HashMap) inMap.get("data")).get("userName").toString().equals(""))
        {
            data.put("sessionID", 0001);
            data.put("msg", "Login successful");
        }
        else
        {
            data.put("msg", "The username " + ((HashMap) inMap.get("data")).get("userName").toString() + " with the meeting ID " 
                + ((HashMap) inMap.get("data")).get("meetingID").toString() + " is not a valid login");
        }
        this.commBean.setData(data);
        try
        {
            outToClient.writeObject(this.commBean);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
