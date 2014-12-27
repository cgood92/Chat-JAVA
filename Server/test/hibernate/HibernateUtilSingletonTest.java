/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate;

import hibernateEntities.Meeting;
import hibernateEntities.Message;
import java.util.HashMap;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cgood92
 */
public class HibernateUtilSingletonTest {
    
    public HibernateUtilSingletonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSessionFactory method, of class HibernateUtilSingleton.
     */
    @Test
    public void testGetSessionFactory() {
        Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Meeting WHERE meeting_id = :meeting ");
            query.setParameter("meeting", 0);
            Meeting meeting = (Meeting) query.uniqueResult();
            assertEquals("Meeting was found", meeting.getName().toString(), "TESTCASE");
            assertFalse("Messages are being correctly joined and mapped", meeting.getMessages().isEmpty());
        session.getTransaction().commit();
    }
    
}
