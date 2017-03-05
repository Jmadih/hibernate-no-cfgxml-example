package com.example.java.hibernate;

import com.example.java.hibernate.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

class SessionSaveOrUpdateExample {

    public static void main(String[] args) {

        Session session = null;
        Transaction transaction = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            Customer customer = new Customer();
            customer.setId(3L);
            customer.setName("Dwayne Johnson");
            session.saveOrUpdate(customer);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        HibernateUtil.shutdown();


    }

}
