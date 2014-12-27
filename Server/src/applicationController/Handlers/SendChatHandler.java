/* * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package applicationController.Handlers;

import applicationController.APC_commBean;
import applicationController.HandlersInterface;
import hibernate.HibernateUtilSingleton;
import hibernateEntities.Meeting;
import hibernateEntities.Message;
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
public class SendChatHandler implements HandlersInterface {
    private APC_commBean commBean;
    public SendChatHandler()
    {
        this.commBean = new APC_commBean();   
    }
    
    @Override
    public void handleIt(HashMap inMap, JSONOutputStream outToClient)
    {
        Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Meeting WHERE meeting_id = :meeting ");
            query.setParameter("meeting", this.commBean.convertLong(((HashMap) inMap.get("data")).get("meeting")));
            Meeting meeting = (Meeting) query.uniqueResult();
            Message msg = new Message();
            msg.setMeeting(meeting);
            msg.setUsername(((HashMap) inMap.get("data")).get("username").toString());
            msg.setMsg(((HashMap) inMap.get("data")).get("msg").toString());
        session.save(msg);
        session.getTransaction().commit();
        
        this.commBean.setCommand("SENDCHAT");
        HashMap data = new HashMap();
        data.put("msg", ((HashMap) inMap.get("data")).get("msg").toString());
        data.put("username", ((HashMap) inMap.get("data")).get("username").toString());
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