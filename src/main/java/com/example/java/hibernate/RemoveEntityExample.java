package com.example.java.hibernate;

import com.example.java.hibernate.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

class RemoveEntityExample {

    public static void main(String[] args) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            // Delete a persistent object
            Customer customer1 = session.get(Customer.class, 1L);
            if (customer1 != null) {
                session.remove(customer1);
                System.out.println("Customer 1 is removed");
            }

            // Delete a transient object
            Customer customer2 = new Customer();
            customer2.setId(2L);
            session.remove(customer2);
            System.out.println("Customer 2 is removed");

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
